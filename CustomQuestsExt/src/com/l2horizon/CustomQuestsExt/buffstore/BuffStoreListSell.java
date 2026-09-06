package com.l2horizon.CustomQuestsExt.buffstore;

import java.util.List;

import l2.gameserver.model.Player;
import l2.gameserver.model.items.TradeItem;
import l2.gameserver.network.l2.s2c.AbstractItemListPacket;

/**
 * The stock PrivateStoreListSell (0xA1) layout fed with the seller's buff entries:
 * the buyer's window of a Private Store (Buff). Package sale flag 0, store price 0.
 */
public class BuffStoreListSell extends AbstractItemListPacket
{
	private final int _sellerId;
	private final long _buyerAdena;
	private final List<TradeItem> _entries;

	public BuffStoreListSell(Player buyer, Player seller, List<TradeItem> entries)
	{
		_sellerId = seller.getObjectId();
		_buyerAdena = buyer.getAdena();
		_entries = entries;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(161);
		writeD(_sellerId);
		writeD(0);
		writeQ(_buyerAdena);
		writeD(0);
		writeD(_entries.size());
		for(TradeItem item : _entries)
		{
			writeItemInfo(item);
			writeQ(item.getOwnersPrice());
			writeQ(item.getStorePrice());
		}
	}
}
