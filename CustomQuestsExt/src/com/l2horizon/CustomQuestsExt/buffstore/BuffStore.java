package com.l2horizon.CustomQuestsExt.buffstore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import l2.gameserver.model.Player;

/**
 * Per-player buff store state. The entries and the bubble title are persisted in
 * the player variables "buffstore" ("item:price;item:price;...") and
 * "buffstoretitle", so the store survives a relog, a restart and offline trade.
 */
public final class BuffStore
{
	public static final String VAR_ENTRIES = "buffstore";
	public static final String VAR_TITLE = "buffstoretitle";

	public static final class Entry
	{
		public final int itemId;
		public final int skillId;
		public final long price;

		public Entry(int itemId, int skillId, long price)
		{
			this.itemId = itemId;
			this.skillId = skillId;
			this.price = price;
		}
	}

	private volatile List<Entry> _entries = Collections.emptyList();
	private volatile String _title = "";
	/** true between the manage list being sent and Start / Stop */
	private volatile boolean _setupPending;
	/** the player's store type is 10 right now */
	private volatile boolean _open;
	/** set while the extension itself resets the store type */
	private volatile boolean _closingByUs;
	/** players who already received the bubble text (bubble task only) */
	private volatile Set<Integer> _notified = Collections.emptySet();

	public List<Entry> getEntries()
	{
		return _entries;
	}

	public void setEntries(List<Entry> entries)
	{
		_entries = entries == null || entries.isEmpty() ? Collections.<Entry> emptyList() : Collections.unmodifiableList(new ArrayList<Entry>(entries));
	}

	public Entry getEntry(int itemId)
	{
		for(Entry e : _entries)
			if(e.itemId == itemId)
				return e;
		return null;
	}

	public String getTitle()
	{
		return _title;
	}

	public void setTitle(String title)
	{
		_title = title == null ? "" : title;
	}

	public boolean isSetupPending()
	{
		return _setupPending;
	}

	public void setSetupPending(boolean pending)
	{
		_setupPending = pending;
	}

	public boolean isOpen()
	{
		return _open;
	}

	public void setOpen(boolean open)
	{
		_open = open;
	}

	public boolean isClosingByUs()
	{
		return _closingByUs;
	}

	public void setClosingByUs(boolean closing)
	{
		_closingByUs = closing;
	}

	public Set<Integer> getNotified()
	{
		return _notified;
	}

	public void setNotified(Set<Integer> notified)
	{
		_notified = notified;
	}

	public void clearNotified()
	{
		_notified = Collections.emptySet();
	}

	/** loads the persisted entries and title of the player */
	public void load(Player player)
	{
		List<Entry> list = new ArrayList<Entry>();
		String raw = player.getVar(VAR_ENTRIES);
		if(raw != null && !raw.isEmpty())
		{
			for(String part : raw.split(";"))
			{
				String[] kv = part.split(":");
				if(kv.length != 2)
					continue;
				try
				{
					int itemId = Integer.parseInt(kv[0].trim());
					long price = Long.parseLong(kv[1].trim());
					BuffStoreTable.Buff buff = BuffStoreTable.getInstance().getByItem(itemId);
					if(buff != null && price > 0L)
						list.add(new Entry(itemId, buff.skillId, price));
				}
				catch(NumberFormatException e)
				{
					// ignore a damaged part
				}
			}
		}
		setEntries(list);
		String title = player.getVar(VAR_TITLE);
		setTitle(title == null ? "" : title);
	}

	/** persists the entries and title of the player */
	public void save(Player player)
	{
		if(_entries.isEmpty())
			player.unsetVar(VAR_ENTRIES);
		else
		{
			StringBuilder sb = new StringBuilder();
			for(Entry e : _entries)
			{
				if(sb.length() > 0)
					sb.append(';');
				sb.append(e.itemId).append(':').append(e.price);
			}
			player.setVar(VAR_ENTRIES, sb.toString(), -1L);
		}
		if(_title.isEmpty())
			player.unsetVar(VAR_TITLE);
		else
			player.setVar(VAR_TITLE, _title, -1L);
	}
}
