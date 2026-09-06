package com.l2horizon.CustomQuestsExt.buffstore;

import java.util.List;

import l2.gameserver.model.Player;
import l2.gameserver.model.items.TradeItem;
import l2.gameserver.network.l2.s2c.AbstractItemListPacket;

/**
 * The stock PrivateStoreListSell (0xA1) layout fed with the seller's buff entries: the
 * buyer's window of a Private Store (Buff). Package sale flag 0, store price 0. The
 * header is the stock one unless BuffStoreListMp says where the seller's current and
 * maximum MP go; any layout other than Stock needs a client that parses 0xA1 that way
 * for buff sellers (the stock parser reads the second D as the package-sale flag).
 */
public class BuffStoreListSell extends AbstractItemListPacket
{
	private final int _sellerId;
	private final int _curMp;
	private final int _maxMp;
	private final long _buyerAdena;
	private final List<TradeItem> _entries;
	private final BuffStoreConfig.ListMp _layout;

	public BuffStoreListSell(Player buyer, Player seller, List<TradeItem> entries)
	{
		_sellerId = seller.getObjectId();
		_curMp = (int) seller.getCurrentMp();
		_maxMp = seller.getMaxMp();
		_buyerAdena = buyer.getAdena();
		_entries = entries;
		_layout = BuffStoreConfig.LIST_MP;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(161);
		writeD(_sellerId);
		switch(_layout)
		{
			case CRAFT:
				writeD(_curMp);
				writeD(_maxMp);
				writeQ(_buyerAdena);
				break;
			case INSERT:
				writeD(_curMp);
				writeD(_maxMp);
				writeD(0);
				writeQ(_buyerAdena);
				writeD(0);
				break;
			case UNKNOWN:
				writeD(0);
				writeQ(_buyerAdena);
				writeD(_curMp);
				break;
			case STOCK:
			default:
				writeD(0);
				writeQ(_buyerAdena);
				writeD(0);
				break;
		}
		writeD(_entries.size());
		for(TradeItem item : _entries)
		{
			writeItemInfo(item);
			writeQ(item.getOwnersPrice());
			writeQ(item.getStorePrice());
		}
	}
}
