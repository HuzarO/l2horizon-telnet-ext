package l2.gameserver.network.l2.c2s;

import java.util.ArrayList;
import java.util.List;

import com.l2horizon.CustomQuestsExt.buffstore.BuffStoreManager;

import l2.commons.math.SafeMath;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.TradeItem;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ExPrivateStoreSellingResult;
import l2.gameserver.utils.Log;
import l2.gameserver.utils.TradeHelper;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Classpath override of the server.jar packet (0x83, Buy in a private store sell
 * window). The stock item purchase is recreated 1:1 from the decompiled original; the
 * only addition is the Buff Store path taken when the seller's store type is 10.
 */
public class RequestPrivateStoreBuy extends L2GameClientPacket
{
	private int _sellerId;
	private int _count;
	private int[] _items;
	private long[] _itemQ;
	private long[] _itemP;

	@Override
	protected void readImpl()
	{
		_sellerId = readD();
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
			if(_itemQ[i] < 1L || _itemP[i] < 1L || ArrayUtils.indexOf(_items, _items[i]) < i)
			{
				_count = 0;
				break;
			}
		}
	}

	@Override
	protected void runImpl()
	{
		Player buyer = ((GameClient) getClient()).getActiveChar();
		if(buyer == null || _count == 0)
			return;
		if(buyer.isActionsDisabled())
		{
			buyer.sendActionFailed();
			return;
		}
		if(buyer.getTradeManager().isInStoreMode())
		{
			buyer.sendPacket((IStaticPacket) SystemMsg.WHILE_OPERATING_A_PRIVATE_STORE_OR_WORKSHOP_YOU_CANNOT_DISCARD_DESTROY_OR_TRADE_AN_ITEM);
			return;
		}
		if(buyer.isInTrade())
		{
			buyer.sendActionFailed();
			return;
		}
		if(buyer.isFishing())
		{
			buyer.sendPacket((IStaticPacket) SystemMsg.YOU_CANNOT_DO_THAT_WHILE_FISHING_);
			return;
		}

		GameObject object = buyer.getVisibleObject(_sellerId);
		Player seller = object != null && object.isPlayer() ? (Player) object : null;
		if(seller != null && BuffStoreManager.isBuffStore(seller))
		{
			BuffStoreManager.getInstance().buy(buyer, seller, _count, _items, _itemQ, _itemP);
			return;
		}
		if(seller == null || (seller.getTradeManager().getPrivateStoreType() != 1 && seller.getTradeManager().getPrivateStoreType() != 8) || !seller.isInRangeZ(buyer, 200L))
		{
			buyer.sendPacket((IStaticPacket) SystemMsg.THE_ATTEMPT_TO_TRADE_HAS_FAILED);
			buyer.sendActionFailed();
			return;
		}

		List<TradeItem> sellList = seller.getTradeManager().getSellList();
		if(sellList.isEmpty())
		{
			buyer.sendPacket((IStaticPacket) SystemMsg.THE_ATTEMPT_TO_TRADE_HAS_FAILED);
			buyer.sendActionFailed();
			return;
		}

		List<TradeItem> buyList = new ArrayList<TradeItem>();
		long totalPrice = 0L;
		int slots = 0;
		long weight = 0L;
		buyer.getInventory().writeLock();
		seller.getInventory().writeLock();
		try
		{
			boolean valid = true;
			loop: for(int i = 0; i < _count; i++)
			{
				int objectId = _items[i];
				long count = _itemQ[i];
				long price = _itemP[i];
				for(TradeItem si : sellList)
				{
					if(si.getObjectId() != objectId || si.getOwnersPrice() != price)
						continue;
					if(count > si.getCount())
					{
						valid = false;
						break loop;
					}
					ItemInstance item = seller.getInventory().getItemByObjectId(objectId);
					if(item == null || item.getCount() < count || !item.canBeTraded(seller))
					{
						valid = false;
						break loop;
					}
					totalPrice = SafeMath.addAndCheck(totalPrice, SafeMath.mulAndCheck(count, price));
					weight = SafeMath.addAndCheck(weight, SafeMath.mulAndCheck(count, item.getTemplate().getWeight()));
					if(!item.isStackable() || buyer.getInventory().getItemByItemId(item.getItemId()) == null)
						slots++;
					TradeItem bi = new TradeItem();
					bi.setObjectId(objectId);
					bi.setItemId(item.getItemId());
					bi.setCount(count);
					bi.setOwnersPrice(price);
					buyList.add(bi);
					continue loop;
				}
			}

			if(!valid || buyList.size() != _count || (seller.getTradeManager().getPrivateStoreType() == 8 && buyList.size() != sellList.size()))
			{
				buyer.sendPacket((IStaticPacket) SystemMsg.THE_ATTEMPT_TO_TRADE_HAS_FAILED);
				buyer.sendActionFailed();
				return;
			}
			if(!buyer.getInventory().validateWeight(weight))
			{
				buyer.sendPacket((IStaticPacket) SystemMsg.YOU_HAVE_EXCEEDED_THE_WEIGHT_LIMIT);
				buyer.sendActionFailed();
				return;
			}
			if(!buyer.getInventory().validateCapacity(slots))
			{
				buyer.sendPacket((IStaticPacket) SystemMsg.YOUR_INVENTORY_IS_FULL);
				buyer.sendActionFailed();
				return;
			}
			if(!buyer.reduceAdena(totalPrice))
			{
				buyer.sendPacket((IStaticPacket) SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				buyer.sendActionFailed();
				return;
			}

			for(TradeItem bi : buyList)
			{
				ItemInstance item = seller.getInventory().removeItemByObjectId(bi.getObjectId(), bi.getCount());
				for(TradeItem si : sellList)
				{
					if(si.getObjectId() != bi.getObjectId())
						continue;
					si.setCount(si.getCount() - bi.getCount());
					if(si.getCount() < 1L)
						sellList.remove(si);
					break;
				}
				Log.LogItem(seller, Log.ItemLog.PrivateStoreSell, item, item.getCount(), bi.getOwnersPrice());
				Log.LogItem(buyer, Log.ItemLog.PrivateStoreBuy, item, item.getCount(), bi.getOwnersPrice());
				seller.sendPacket((IStaticPacket) new ExPrivateStoreSellingResult(bi.getObjectId(), bi.getCount(), buyer.getName()));
				buyer.getInventory().addItem(item);
				TradeHelper.purchaseItem(buyer, seller, bi);
			}

			long tax = TradeHelper.getTax(seller, totalPrice);
			if(tax > 0L)
			{
				totalPrice -= tax;
				seller.sendMessage(new CustomMessage("trade.HavePaidTax", seller).addNumber(tax));
			}
			seller.addAdena(totalPrice);
			seller.getTradeManager().saveTradeList();
		}
		catch(ArithmeticException e)
		{
			buyList.clear();
			buyer.sendPacket((IStaticPacket) SystemMsg.INCORRECT_ITEM_COUNT);
			return;
		}
		finally
		{
			seller.getInventory().writeUnlock();
			buyer.getInventory().writeUnlock();
		}

		TradeHelper.onTradeEnded(seller, buyer);
		seller.sendChanges();
		buyer.sendChanges();
		buyer.sendActionFailed();
	}
}
