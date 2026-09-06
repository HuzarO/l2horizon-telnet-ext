package com.l2horizon.CustomQuestsExt.buffstore;

import l2.gameserver.model.Player;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

/**
 * The stock PrivateStoreMsgSell (0xA2) layout with the buff store title: the reply to
 * the Message button that keeps the client's message prefill working.
 */
public class BuffStoreMsgSell extends L2GameServerPacket
{
	private final int _objectId;
	private final String _title;

	public BuffStoreMsgSell(Player player, String title)
	{
		_objectId = player.getObjectId();
		_title = title == null ? "" : title;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(162);
		writeD(_objectId);
		writeS(_title);
	}
}
