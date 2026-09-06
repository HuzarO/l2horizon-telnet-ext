package com.l2horizon.CustomQuestsExt.buffstore;

import java.util.List;

import l2.gameserver.model.Player;
import l2.gameserver.model.items.TradeItem;
import l2.gameserver.network.l2.s2c.AbstractItemListPacket;

/**
 * The stock PrivateStoreManageListSell (0xA0) layout fed with synthetic buff entries:
 * part 1 = header + the buffs already in the store (with prices), part 2 = the buffs
 * the seller can add. Package sale flag 0, store price 0 (the client warns about
 * "unusual" prices otherwise).
 */
public class BuffStoreManageListSell extends AbstractItemListPacket
{
	private final boolean _header;
	private final int _objectId;
	private final long _adena;
	private final List<TradeItem> _sellList;
	private final List<TradeItem> _available;

	public BuffStoreManageListSell(boolean header, Player player, List<TradeItem> sellList, List<TradeItem> available)
	{
		_header = header;
		_objectId = player.getObjectId();
		_adena = player.getAdena();
		_sellList = sellList;
		_available = available;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(160);
		writeC(_header ? 1 : 2);
		if(_header)
		{
			writeD(_objectId);
			writeD(0);
			writeQ(_adena);
			writeD(_sellList.size());
			for(TradeItem item : _sellList)
			{
				writeItemInfo(item);
				writeQ(item.getOwnersPrice());
				writeQ(item.getStorePrice());
			}
			writeD(_available.size());
		}
		else
		{
			writeD(_available.size());
			writeD(_available.size());
			for(TradeItem item : _available)
			{
				writeItemInfo(item);
				writeQ(item.getStorePrice());
			}
		}
	}
}
