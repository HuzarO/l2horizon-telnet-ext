package l2.gameserver.network.l2.c2s;

import java.util.concurrent.CopyOnWriteArrayList;

import com.l2horizon.CustomQuestsExt.buffstore.BuffStoreManager;

import l2.commons.math.SafeMath;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.TradeItem;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ExPrivateStoreSetWholeMsg;
import l2.gameserver.network.l2.s2c.PrivateStoreManageListSell;
import l2.gameserver.network.l2.s2c.PrivateStoreMsgSell;
import l2.gameserver.utils.TradeHelper;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Classpath override of the server.jar packet (0x31, Start in the private store sell
 * setup window). The stock item-store logic is recreated 1:1 from the decompiled
 * original; the only addition is the Buff Store path taken when the list holds dummy
 * buff items (81000-81499), i.e. Start was pressed in the /buff setup window.
 */
public class SetPrivateStoreSellList extends L2GameClientPacket
{
	private int _count;
	private boolean _package;
	private int[] _items;
	private long[] _itemQ;
	private long[] _itemP;

	@Override
	protected void readImpl()
	{
		_package = readD() == 1;
		_count = readD();
		if(_count * 20 > _buf.remaining() || _count > Short.MAX_VALUE || _count < 1)
		{
			_count = 0;
			return;
		}
		_items = new int[_count];
		_itemQ = new long[_count];
		_itemP = new long[_count];
		for(int i = 0; i < _count; i++)
		{
			_items[i] = readD();
			_itemQ[i] = readQ();
			_itemP[i] = readQ();
			if(_itemQ[i] < 1L || _itemP[i] < 0L || ArrayUtils.indexOf(_items, _items[i]) < i)
			{
				_count = 0;
				break;
			}
		}
	}

	@Override
	protected void runImpl()
	{
		Player player = ((GameClient) getClient()).getActiveChar();
		if(player == null)
			return;

		BuffStoreManager buffStore = BuffStoreManager.getInstance();
		if(_count > 0 && BuffStoreManager.anyDummyItem(_items, _count))
		{
			buffStore.start(player, _package, _count, _items, _itemQ, _itemP);
			return;
		}
		buffStore.clearSetupPending(player);
		if(_count == 0)
			return;

		if(!TradeHelper.checksIfCanOpenStore(player, _package ? 8 : 1))
		{
			if(player.getTradeManager().isInStoreMode())
				player.getTradeManager().setPrivateStoreType(0);
			player.sendPacket(new PrivateStoreManageListSell(true, player, _package), new PrivateStoreManageListSell(false, player, _package));
			player.sendActionFailed();
			return;
		}

		CopyOnWriteArrayList<TradeItem> sellList = new CopyOnWriteArrayList<TradeItem>();
		long totalPrice = 0L;
		player.getInventory().writeLock();
		try
		{
			for(int i = 0; i < _count; i++)
			{
				int objectId = _items[i];
				long count = _itemQ[i];
				long price = _itemP[i];
				ItemInstance item = player.getInventory().getItemByObjectId(objectId);
				if(item == null || item.getCount() < count || !item.canBeTraded(player) || item.getItemId() == 57)
					continue;
				TradeItem tradeItem = new TradeItem(item);
				tradeItem.setCount(count);
				tradeItem.setOwnersPrice(price);
				totalPrice = SafeMath.addAndCheck(totalPrice, SafeMath.mulAndCheck(count, price));
				sellList.add(tradeItem);
			}
		}
		catch(ArithmeticException e)
		{
			sellList.clear();
			player.sendPacket((IStaticPacket) SystemMsg.YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_INPUTTED);
			return;
		}
		finally
		{
			player.getInventory().writeUnlock();
		}

		if(sellList.size() > player.getTradeLimit() || SafeMath.addAndCheck(totalPrice, player.getAdena()) >= Long.MAX_VALUE)
		{
			player.sendPacket(new PrivateStoreManageListSell(true, player, _package), new PrivateStoreManageListSell(false, player, _package));
			return;
		}

		if(!sellList.isEmpty())
		{
			player.getTradeManager().setSellList(_package, sellList);
			player.getTradeManager().saveTradeList();
			player.getTradeManager().setPrivateStoreType(_package ? 8 : 1);
			player.broadcastPacket(_package ? new ExPrivateStoreSetWholeMsg(player) : new PrivateStoreMsgSell(player));
		}
		player.sendActionFailed();
	}
}
