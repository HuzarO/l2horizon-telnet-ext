package com.l2horizon.CustomQuestsExt.buffstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.l2horizon.CustomQuestsExt.buffstore.BuffStore.Entry;
import com.l2horizon.CustomQuestsExt.buffstore.BuffStoreTable.Buff;

import l2.commons.math.SafeMath;
import l2.commons.threading.RunnableImpl;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.ai.CtrlIntention;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.handler.usercommands.UserCommandHandler;
import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.listener.actor.player.OnPlayerExitListener;
import l2.gameserver.listener.actor.player.OnSetPrivateStoreType;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.World;
import l2.gameserver.model.actor.listener.CharListenerList;
import l2.gameserver.model.items.TradeItem;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ExPrivateStoreSellingResult;
import l2.gameserver.network.l2.s2c.ExPrivateStoreSetWholeMsg;
import l2.gameserver.network.l2.s2c.MagicSkillUse;
import l2.gameserver.network.l2.s2c.StatusUpdate;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Log;
import l2.gameserver.utils.Strings;
import l2.gameserver.utils.TradeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Private Store (Buff): private store type 10 riding on the stock private-store-sell
 * packets with the dummy buff items of data/buff_store.xml (objectId = itemId = dummy id,
 * count 1, store price 0).
 *
 * <ul>
 * <li>/buff (user command 171) - {@link #openSetup}: the stock manage-list layout with
 * section A = the buffs the player has learned, section B = the saved entries;</li>
 * <li>Start - {@link #start} from the shadowed SetPrivateStoreSellList: validates the
 * list, persists it, sets store type 10 (the core sits the player) and broadcasts the
 * bubble text (ExPrivateStoreSetWholeMsg, the package-sale message slot);</li>
 * <li>Stop / ESC - {@link #quit} from the shadowed RequestPrivateStoreQuitSell;</li>
 * <li>context menu close (the core's RequestActionUse resets the type) - the setup
 * window is re-sent with the previous entries;</li>
 * <li>Message - {@link #setTitle};</li>
 * <li>a buyer's double-click - {@link #onSellerAction} / {@link #sendList}, then
 * {@link #buy} from the shadowed RequestPrivateStoreBuy: adena moves, the seller
 * casts the buff on the buyer at the seller's learned level, both sides get the stock
 * sale messages and the seller the stock selling result, the store stays open.</li>
 * </ul>
 * Nothing here touches the inventory or the core trade lists. The core resets the
 * store type itself on death, teleport, logout and Olympiad, which is enough since
 * every buff store rule keys on the store type.
 */
public final class BuffStoreManager implements OnSetPrivateStoreType, OnPlayerEnterListener, OnPlayerExitListener
{
	private static final Logger _log = LoggerFactory.getLogger(BuffStoreManager.class);
	/** the private store type of an open buff store (client constant) */
	public static final int STORE_TYPE = 10;
	/** client limit of the store message */
	public static final int TITLE_MAX = 29;
	private static final String ACTION_USE_PACKET = "l2.gameserver.network.l2.c2s.RequestActionUse";
	private static final long APPROACH_TIMEOUT = 15000L;
	private static final long APPROACH_TICK = 300L;

	private static final BuffStoreManager INSTANCE = new BuffStoreManager();

	private final Map<Integer, BuffStore> _stores = new ConcurrentHashMap<Integer, BuffStore>();
	private boolean _initialized;

	private BuffStoreManager()
	{
	}

	public static BuffStoreManager getInstance()
	{
		return INSTANCE;
	}

	public void init()
	{
		if(_initialized)
			return;
		_initialized = true;
		if(!BuffStoreConfig.ENABLED)
		{
			_log.info("BuffStore: disabled (BuffStoreEnabled = False)");
			return;
		}
		UserCommandHandler.getInstance().registerUserCommandHandler(new BuffStoreUserCommand());
		CharListenerList.addGlobal(this);
		BuffStoreOfflineCommand.install();
		long period = BuffStoreConfig.BUBBLE_REFRESH_SECONDS * 1000L;
		ThreadPoolManager.getInstance().scheduleAtFixedRate(new BubbleTask(), period, period);
		_log.info("BuffStore: enabled, " + BuffStoreTable.getInstance().size() + " sellable buffs, /buff = user command " + BuffStoreUserCommand.COMMAND_ID);
	}

	public static boolean isBuffStore(Player player)
	{
		return player != null && player.getTradeManager().getPrivateStoreType() == STORE_TYPE;
	}

	public static boolean anyDummyItem(int[] items, int count)
	{
		if(items == null)
			return false;
		for(int i = 0; i < count && i < items.length; i++)
			if(BuffStoreTable.isDummyItem(items[i]))
				return true;
		return false;
	}

	/** the store state of the player, loaded from the player variables on first use */
	public BuffStore getStore(Player player)
	{
		BuffStore store = _stores.get(player.getObjectId());
		if(store == null)
		{
			store = new BuffStore();
			store.load(player);
			store.setOpen(isBuffStore(player));
			BuffStore old = _stores.putIfAbsent(player.getObjectId(), store);
			if(old != null)
				store = old;
		}
		return store;
	}

	public boolean isSetupPending(Player player)
	{
		BuffStore store = _stores.get(player.getObjectId());
		return store != null && store.isSetupPending();
	}

	/** the /buff setup window is no longer waiting for Start (a stock store list was sent instead) */
	public void clearSetupPending(Player player)
	{
		BuffStore store = _stores.get(player.getObjectId());
		if(store != null)
			store.setSetupPending(false);
	}

	/** buffs the player may list: BuffStoreBaseSlots plus the learned level of Expand Buff Store */
	public static int slotLimit(Player player)
	{
		int level = BuffStoreConfig.EXPAND_SKILL_ID > 0 ? player.getSkillLevel(BuffStoreConfig.EXPAND_SKILL_ID) : 0;
		return BuffStoreConfig.BASE_SLOTS + Math.max(0, level);
	}

	private static boolean canSell(Player player, Buff buff)
	{
		return player.getKnownSkill(buff.skillId) != null && player.getLevel() >= buff.minLevel;
	}

	private static TradeItem dummy(int itemId, long price)
	{
		TradeItem item = new TradeItem();
		item.setObjectId(itemId);
		item.setItemId(itemId);
		item.setCount(1L);
		item.setOwnersPrice(price);
		item.setReferencePrice(0L);
		return item;
	}

	/** the entries the seller can still deliver, as packet items */
	private static List<TradeItem> sellList(Player seller, BuffStore store)
	{
		List<TradeItem> list = new ArrayList<TradeItem>();
		for(Entry e : store.getEntries())
		{
			Buff buff = BuffStoreTable.getInstance().getByItem(e.itemId);
			if(buff != null && canSell(seller, buff))
				list.add(dummy(e.itemId, e.price));
		}
		return list;
	}

	// ------------------------------------------------------------------ setup

	/**
	 * Sends the Private Store (Buff) setup window. An open store of any kind is closed
	 * first, exactly like the stock "Private Store - Sell" action does.
	 *
	 * @param reopen true when re-sent right after a close (the stand-up task is running)
	 */
	public void openSetup(Player player, boolean reopen)
	{
		if(!BuffStoreConfig.ENABLED)
		{
			player.sendMessage(new CustomMessage("buffstore.disabled", player));
			return;
		}
		if(!reopen && player.getSittingTask())
		{
			player.sendActionFailed();
			return;
		}
		BuffStore store = getStore(player);
		if(player.getTradeManager().isInStoreMode())
			close(player, store);
		else if(!TradeHelper.checksIfCanOpenStore(player, STORE_TYPE))
		{
			player.sendActionFailed();
			return;
		}
		List<TradeItem> saved = sellList(player, store);
		List<TradeItem> available = new ArrayList<TradeItem>();
		for(Buff buff : BuffStoreTable.getInstance().getAll())
		{
			if(!canSell(player, buff))
				continue;
			boolean listed = false;
			for(TradeItem item : saved)
				if(item.getObjectId() == buff.itemId)
				{
					listed = true;
					break;
				}
			if(!listed)
				available.add(dummy(buff.itemId, 0L));
		}
		if(saved.isEmpty() && available.isEmpty())
		{
			player.sendMessage(new CustomMessage("buffstore.noBuffs", player));
			player.sendActionFailed();
			return;
		}
		store.setSetupPending(true);
		player.sendPacket(new BuffStoreManageListSell(true, player, saved, available), new BuffStoreManageListSell(false, player, saved, available));
	}

	/** Start pressed in the setup window (shadowed SetPrivateStoreSellList, buff path). */
	public void start(Player player, boolean packageSale, int count, int[] items, long[] counts, long[] prices)
	{
		BuffStore store = getStore(player);
		store.setSetupPending(false);
		if(!BuffStoreConfig.ENABLED || count <= 0)
		{
			player.sendActionFailed();
			return;
		}
		if(packageSale)
		{
			fail(player, new CustomMessage("buffstore.invalid", player));
			return;
		}
		int limit = slotLimit(player);
		if(count > limit)
		{
			fail(player, new CustomMessage("buffstore.tooMany", player).addNumber(limit));
			return;
		}
		List<Entry> list = new ArrayList<Entry>(count);
		Set<Integer> seen = new HashSet<Integer>();
		for(int i = 0; i < count; i++)
		{
			int itemId = items[i];
			Buff buff = BuffStoreTable.isDummyItem(itemId) ? BuffStoreTable.getInstance().getByItem(itemId) : null;
			if(buff == null || counts[i] != 1L || !seen.add(itemId))
			{
				fail(player, new CustomMessage("buffstore.invalid", player));
				return;
			}
			if(!canSell(player, buff))
			{
				fail(player, new CustomMessage("buffstore.notLearned", player).addString(buff.name));
				return;
			}
			long price = prices[i];
			long min = buff.minPrice();
			long max = buff.maxPrice();
			if(price < min || price > max)
			{
				fail(player, new CustomMessage("buffstore.price", player).addString(buff.name).addNumber(min).addNumber(max));
				return;
			}
			list.add(new Entry(itemId, buff.skillId, price));
		}
		if(!TradeHelper.checksIfCanOpenStore(player, STORE_TYPE))
		{
			player.sendActionFailed();
			return;
		}
		store.setEntries(list);
		store.save(player);
		store.clearNotified();
		player.getTradeManager().setPrivateStoreType(STORE_TYPE);
		player.broadcastPacket(new ExPrivateStoreSetWholeMsg(player, store.getTitle()));
		player.sendActionFailed();
	}

	/** a rejected Start: message, then the setup window again like the stock handler */
	private void fail(Player player, CustomMessage message)
	{
		player.sendMessage(message);
		player.sendActionFailed();
		openSetup(player, true);
	}

	/** Stop / ESC / window close (shadowed RequestPrivateStoreQuitSell). Silent when no buff store is open. */
	public void quit(Player player)
	{
		BuffStore store = _stores.get(player.getObjectId());
		if(store != null)
			store.setSetupPending(false);
		if(isBuffStore(player))
			close(player, store == null ? getStore(player) : store);
	}

	/** resets the store type ourselves (the core stands the player up and broadcasts CharInfo) */
	private void close(Player player, BuffStore store)
	{
		store.setSetupPending(false);
		store.setClosingByUs(true);
		try
		{
			player.getTradeManager().setPrivateStoreType(0);
		}
		finally
		{
			store.setClosingByUs(false);
		}
	}

	/** Message button (shadowed SetPrivateStoreMsgSell / SetPrivateStoreWholeMsg). */
	public void setTitle(Player player, String text)
	{
		BuffStore store = getStore(player);
		String title = text == null ? "" : Strings.stripToSingleLine(text);
		if(title == null)
			title = "";
		title = title.trim();
		if(title.length() > TITLE_MAX)
			title = title.substring(0, TITLE_MAX);
		store.setTitle(title);
		store.save(player);
		player.sendPacket(new BuffStoreMsgSell(player, title));
		if(isBuffStore(player))
			player.broadcastPacket(new ExPrivateStoreSetWholeMsg(player, title));
	}

	// ------------------------------------------------------------------ buyer side

	/** A buyer double-clicked a buff seller they already have targeted (shadowed Action). */
	public void onSellerAction(Player buyer, Player seller, boolean shift)
	{
		if(inRange(buyer, seller))
		{
			sendList(buyer, seller);
			buyer.sendActionFailed();
			return;
		}
		if(shift)
		{
			buyer.sendActionFailed();
			return;
		}
		buyer.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, seller, null);
		ThreadPoolManager.getInstance().schedule(new ApproachTask(buyer, seller, System.currentTimeMillis() + APPROACH_TIMEOUT), APPROACH_TICK);
	}

	private static boolean inRange(Player buyer, Player seller)
	{
		return buyer.getRealDistance(seller) <= seller.getActingRange();
	}

	/** the buyer's window: the stock buy-list layout with the seller's entries */
	public void sendList(Player buyer, Player seller)
	{
		if(!isBuffStore(seller))
		{
			buyer.sendActionFailed();
			return;
		}
		// the seller's current MP first, the client shows it in the buff window
		buyer.sendPacket(seller.makeStatusUpdate(StatusUpdate.CUR_MP, StatusUpdate.MAX_MP), new BuffStoreListSell(buyer, seller, sellList(seller, getStore(seller))));
	}

	/**
	 * Buy pressed in a buff seller's window (shadowed RequestPrivateStoreBuy, buff path).
	 * The buyer's own state (store mode, trade, fishing) is already checked by the packet.
	 */
	public void buy(Player buyer, Player seller, int count, int[] items, long[] counts, long[] prices)
	{
		if(!BuffStoreConfig.ENABLED || count <= 0 || buyer == seller || !isBuffStore(seller) || !seller.isInRangeZ(buyer, 200L))
		{
			tradeFailed(buyer);
			return;
		}
		if(buyer.isDead() || seller.isDead())
		{
			buyer.sendMessage(new CustomMessage("buffstore.sellerBusy", buyer));
			buyer.sendActionFailed();
			return;
		}
		BuffStore store = getStore(seller);
		if(store.getEntries().isEmpty())
		{
			tradeFailed(buyer);
			return;
		}
		List<Entry> bought = new ArrayList<Entry>(count);
		List<Buff> buffs = new ArrayList<Buff>(count);
		List<Skill> skills = new ArrayList<Skill>(count);
		long total = 0L;
		double mpNeeded = 0.0;
		Map<Integer, Long> itemsNeeded = new HashMap<Integer, Long>();
		try
		{
			for(int i = 0; i < count; i++)
			{
				Entry entry = store.getEntry(items[i]);
				if(entry == null || counts[i] != 1L || prices[i] != entry.price)
				{
					tradeFailed(buyer);
					return;
				}
				for(Entry b : bought)
					if(b.itemId == entry.itemId)
					{
						tradeFailed(buyer);
						return;
					}
				Buff buff = BuffStoreTable.getInstance().getByItem(entry.itemId);
				if(buff == null || !canSell(seller, buff))
				{
					tradeFailed(buyer);
					return;
				}
				Skill skill = buff.level > 0 ? SkillTable.getInstance().getInfo(buff.skillId, buff.level) : seller.getKnownSkill(buff.skillId);
				if(skill == null)
				{
					tradeFailed(buyer);
					return;
				}
				total = SafeMath.addAndCheck(total, entry.price);
				// the seller must be able to cast every buff of this purchase like a real cast:
				// MP, the skill's consumables and the weapon the skill requires
				if(BuffStoreConfig.CONSUME_MP)
				{
					mpNeeded += mpCost(skill);
					if(seller.getCurrentMp() < mpNeeded)
					{
						buyer.sendMessage(new CustomMessage("buffstore.noMp", buyer).addString(seller.getName()).addString(buff.name));
						seller.sendMessage(new CustomMessage("buffstore.sellerNoMp", seller).addString(buff.name));
						buyer.sendActionFailed();
						return;
					}
				}
				if(BuffStoreConfig.CHECK_WEAPON && !wieldsAllowedWeapon(seller, skill))
				{
					buyer.sendMessage(new CustomMessage("buffstore.noWeapon", buyer).addString(seller.getName()).addString(buff.name));
					seller.sendMessage(new CustomMessage("buffstore.sellerNoWeapon", seller).addString(buff.name));
					buyer.sendActionFailed();
					return;
				}
				if(BuffStoreConfig.CONSUME_ITEMS)
				{
					int[] consumeIds = skill.getItemConsumeId();
					int[] consumeCounts = skill.getItemConsume();
					if(consumeIds != null && consumeCounts != null && consumeCounts.length > 0 && consumeCounts[0] > 0)
					{
						for(int c = 0; c < consumeIds.length && c < consumeCounts.length; c++)
						{
							long needed = itemsNeeded.containsKey(consumeIds[c]) ? itemsNeeded.get(consumeIds[c]) : 0L;
							needed += consumeCounts[c];
							itemsNeeded.put(consumeIds[c], needed);
							if(ItemFunctions.getItemCount(seller, consumeIds[c]) < needed)
							{
								ItemTemplate consumable = ItemHolder.getInstance().getTemplate(consumeIds[c]);
								String itemName = consumable == null ? String.valueOf(consumeIds[c]) : consumable.getName();
								buyer.sendMessage(new CustomMessage("buffstore.noItems", buyer).addString(seller.getName()).addString(itemName).addString(buff.name));
								seller.sendMessage(new CustomMessage("buffstore.sellerNoItems", seller).addString(itemName).addString(buff.name));
								buyer.sendActionFailed();
								return;
							}
						}
					}
				}
				bought.add(entry);
				buffs.add(buff);
				skills.add(skill);
			}
		}
		catch(ArithmeticException e)
		{
			buyer.sendPacket((IStaticPacket) SystemMsg.INCORRECT_ITEM_COUNT);
			buyer.sendActionFailed();
			return;
		}
		if(buyer.getAdena() < total || !buyer.reduceAdena(total, true))
		{
			buyer.sendPacket((IStaticPacket) SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			buyer.sendActionFailed();
			return;
		}
		long tax = BuffStoreConfig.TAX_PERCENT > 0.0 ? (long) (total * BuffStoreConfig.TAX_PERCENT / 100.0) : 0L;
		long income = total - tax;
		if(income > 0L)
			seller.addAdena(income, true);
		for(int i = 0; i < bought.size(); i++)
		{
			Entry entry = bought.get(i);
			Buff buff = buffs.get(i);
			Skill skill = skills.get(i);
			if(BuffStoreConfig.CAST_ANIMATION)
			{
				int level = skill.getDisplayLevel() >= 100 ? skill.getBaseLevel() : skill.getDisplayLevel();
				seller.broadcastPacket(new MagicSkillUse(seller, buyer, skill.getDisplayId(), level, 0, 0L));
			}
			if(BuffStoreConfig.CONSUME_MP)
				seller.reduceCurrentMp(mpCost(skill), null); // no attacker: like the private workshop, no attack stance
			if(BuffStoreConfig.CONSUME_ITEMS)
			{
				int[] consumeIds = skill.getItemConsumeId();
				int[] consumeCounts = skill.getItemConsume();
				if(consumeIds != null && consumeCounts != null && consumeCounts.length > 0 && consumeCounts[0] > 0)
					for(int c = 0; c < consumeIds.length && c < consumeCounts.length; c++)
						ItemFunctions.removeItem(seller, consumeIds[c], consumeCounts[c], true);
			}
			skill.getEffects(seller, buyer, false, false);
			// the stock private-store feedback: the sale messages name the dummy item, which the
			// client shows as the buff name, and the selling result fills the seller's sale log
			SystemMessage sold = new SystemMessage(SystemMsg.S2_IS_SOLD_TO_C1_FOR_THE_PRICE_OF_S3_ADENA);
			sold.addString(buyer.getName());
			sold.addItemName(entry.itemId);
			sold.addNumber(entry.price);
			seller.sendPacket(sold);
			SystemMessage purchased = new SystemMessage(SystemMsg.S2_HAS_BEEN_PURCHASED_FROM_C1_AT_THE_PRICE_OF_S3_ADENA);
			purchased.addString(seller.getName());
			purchased.addItemName(entry.itemId);
			purchased.addNumber(entry.price);
			buyer.sendPacket(purchased);
			if(BuffStoreConfig.SELLING_RESULT)
				seller.sendPacket(new ExPrivateStoreSellingResult(entry.itemId, 1L, buyer.getName()));
			Log.add(seller.getName() + " sold buff " + buff.name + " (skill " + skill.getId() + " lv " + skill.getLevel() + ") to " + buyer.getName() + " for " + entry.price + " adena", "buffstore");
		}
		if(tax > 0L)
			seller.sendMessage(new CustomMessage("buffstore.tax", seller).addNumber(tax));
		if(BuffStoreConfig.CONSUME_MP)
		{
			// the seller's MP after the casts: to the seller like the private workshop does after a
			// craft (StatusUpdate CUR_MP), and to the buyer, whose window shows the seller's MP
			seller.sendStatusUpdate(false, false, StatusUpdate.CUR_MP);
			buyer.sendPacket(seller.makeStatusUpdate(StatusUpdate.CUR_MP, StatusUpdate.MAX_MP));
		}
		buyer.sendActionFailed();
	}

	/** the skill's full MP cost (initial + cast) times BuffStoreMpMultiplier */
	private static double mpCost(Skill skill)
	{
		return skill.getMpConsume() * BuffStoreConfig.MP_MULTIPLIER;
	}

	/** the core's weapon dependency: no requirement, or the active or secondary weapon is of an allowed type */
	private static boolean wieldsAllowedWeapon(Player seller, Skill skill)
	{
		long allowed = skill.getWeaponsAllowed();
		if(allowed == 0L)
			return true;
		if(seller.getActiveWeaponInstance() != null && seller.getActiveWeaponItem() != null && (seller.getActiveWeaponItem().getItemType().mask() & allowed) != 0L)
			return true;
		return seller.getSecondaryWeaponInstance() != null && seller.getSecondaryWeaponItem() != null && (seller.getSecondaryWeaponItem().getItemType().mask() & allowed) != 0L;
	}

	private static void tradeFailed(Player buyer)
	{
		buyer.sendPacket((IStaticPacket) SystemMsg.THE_ATTEMPT_TO_TRADE_HAS_FAILED);
		buyer.sendActionFailed();
	}

	// ------------------------------------------------------------------ listeners

	@Override
	public void onSetPrivateStoreType(Player player, int type)
	{
		if(type == STORE_TYPE)
		{
			BuffStore store = getStore(player);
			store.setOpen(true);
			store.setSetupPending(false);
			return;
		}
		BuffStore store = _stores.get(player.getObjectId());
		if(store == null)
			return;
		boolean wasOpen = store.isOpen();
		store.setOpen(false);
		store.setSetupPending(false);
		store.clearNotified();
		// the core's RequestActionUse ("Private Store - Sell" / context menu close) resets the
		// type and re-sends the stock item manage list: stock semantics are "close and edit",
		// so the buff setup window follows with the previous entries
		if(wasOpen && type == 0 && !store.isClosingByUs() && player.isOnline() && !player.isInOfflineMode() && closedByActionUse())
			ThreadPoolManager.getInstance().schedule(new ReopenTask(player), 300L);
	}

	private static boolean closedByActionUse()
	{
		try
		{
			return StackWalker.getInstance().walk(frames -> frames.anyMatch(f -> ACTION_USE_PACKET.equals(f.getClassName())));
		}
		catch(RuntimeException e)
		{
			return false;
		}
	}

	@Override
	public void onPlayerEnter(Player player)
	{
		if(!isBuffStore(player))
			return;
		BuffStore store = getStore(player);
		if(!BuffStoreConfig.ENABLED || !BuffStoreConfig.RESTORE_ON_LOGIN || sellList(player, store).isEmpty())
		{
			close(player, store);
			return;
		}
		store.setOpen(true);
		store.clearNotified();
		player.sendPacket(new ExPrivateStoreSetWholeMsg(player, store.getTitle()));
	}

	@Override
	public void onPlayerExit(Player player)
	{
		_stores.remove(player.getObjectId());
	}

	// ------------------------------------------------------------------ tasks

	/** re-sends the setup window after the core closed the store through RequestActionUse */
	private final class ReopenTask extends RunnableImpl
	{
		private final Player _player;
		private int _attempts;

		ReopenTask(Player player)
		{
			_player = player;
		}

		@Override
		public void runImpl()
		{
			if(!_player.isOnline() || _player.isInOfflineMode() || _player.isDead() || _player.isTeleporting() || _player.getTradeManager().isInStoreMode())
				return;
			// let the stand-up of the closed store finish first
			if(_player.getSittingTask() && _attempts++ < 20)
			{
				ThreadPoolManager.getInstance().schedule(this, APPROACH_TICK);
				return;
			}
			openSetup(_player, true);
		}
	}

	/** walks the buyer to the seller (the core's interact intention) and opens the window on arrival */
	private final class ApproachTask extends RunnableImpl
	{
		private final Player _buyer;
		private final Player _seller;
		private final long _deadline;

		ApproachTask(Player buyer, Player seller, long deadline)
		{
			_buyer = buyer;
			_seller = seller;
			_deadline = deadline;
		}

		@Override
		public void runImpl()
		{
			if(!_buyer.isOnline() || _buyer.isDead() || !isBuffStore(_seller) || _buyer.getTarget() != _seller)
				return;
			if(inRange(_buyer, _seller))
			{
				sendList(_buyer, _seller);
				_buyer.sendActionFailed();
				return;
			}
			if(System.currentTimeMillis() > _deadline || !_buyer.getAI().isIntendingInteract(_seller))
				return;
			ThreadPoolManager.getInstance().schedule(this, APPROACH_TICK);
		}
	}

	/**
	 * The core replays the store message only for its own store types when a seller comes
	 * into view; for buff stores the bubble text is sent to every player who newly sees
	 * an open buff store.
	 */
	private final class BubbleTask extends RunnableImpl
	{
		@Override
		public void runImpl()
		{
			for(Player seller : GameObjectsStorage.getAllPlayersForIterate())
			{
				if(!isBuffStore(seller))
					continue;
				BuffStore store = getStore(seller);
				Set<Integer> known = store.getNotified();
				Set<Integer> now = new HashSet<Integer>();
				ExPrivateStoreSetWholeMsg msg = null;
				for(Player other : World.getAroundPlayers(seller))
				{
					if(other == seller)
						continue;
					now.add(other.getObjectId());
					if(known.contains(other.getObjectId()))
						continue;
					if(msg == null)
						msg = new ExPrivateStoreSetWholeMsg(seller, store.getTitle());
					other.sendPacket(msg);
				}
				store.setNotified(now);
			}
		}
	}
}
