package com.l2horizon.CustomQuestsExt.buffstore;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.tables.SkillTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * data/buff_store.xml - dummy buff item (81000-81499) -> buff skill.
 */
public final class BuffStoreTable
{
	private static final Logger _log = LoggerFactory.getLogger(BuffStoreTable.class);
	public static final String FILE = "data/buff_store.xml";
	/** the dummy item id range the client treats as buff store entries */
	public static final int ITEM_MIN = 81000;
	public static final int ITEM_MAX = 81499;

	public static final class Buff
	{
		public final int itemId;
		public final int skillId;
		/** fixed cast level, 0 = the seller's learned level */
		public final int level;
		/** minimum seller level */
		public final int minLevel;
		/** per-entry price limits, 0 = the global limits */
		public final long priceMin;
		public final long priceMax;
		public final String name;

		Buff(int itemId, int skillId, int level, int minLevel, long priceMin, long priceMax, String name)
		{
			this.itemId = itemId;
			this.skillId = skillId;
			this.level = level;
			this.minLevel = minLevel;
			this.priceMin = priceMin;
			this.priceMax = priceMax;
			this.name = name;
		}

		public long minPrice()
		{
			return priceMin > 0 ? priceMin : BuffStoreConfig.MIN_PRICE;
		}

		public long maxPrice()
		{
			return priceMax > 0 ? priceMax : BuffStoreConfig.MAX_PRICE;
		}
	}

	private static final BuffStoreTable INSTANCE = new BuffStoreTable();

	private volatile Map<Integer, Buff> _byItem = Collections.emptyMap();
	private volatile Map<Integer, Buff> _bySkill = Collections.emptyMap();

	private BuffStoreTable()
	{
	}

	public static BuffStoreTable getInstance()
	{
		return INSTANCE;
	}

	public static boolean isDummyItem(int itemId)
	{
		return itemId >= ITEM_MIN && itemId <= ITEM_MAX;
	}

	public void load()
	{
		Map<Integer, Buff> byItem = new TreeMap<Integer, Buff>();
		Map<Integer, Buff> bySkill = new HashMap<Integer, Buff>();
		File file = new File(FILE);
		if(!file.exists())
		{
			_log.warn("BuffStoreTable: " + FILE + " not found, the buff store has no buffs");
			_byItem = Collections.emptyMap();
			_bySkill = Collections.emptyMap();
			return;
		}
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setIgnoringComments(true);
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			NodeList nodes = doc.getElementsByTagName("buff");
			for(int i = 0; i < nodes.getLength(); i++)
			{
				Element e = (Element) nodes.item(i);
				int itemId = Integer.parseInt(e.getAttribute("item").trim());
				int skillId = Integer.parseInt(e.getAttribute("skill").trim());
				int level = intAttr(e, "level", 0);
				int minLevel = intAttr(e, "minLevel", 0);
				long priceMin = longAttr(e, "priceMin", 0L);
				long priceMax = longAttr(e, "priceMax", 0L);
				String name = e.getAttribute("name");
				if(!isDummyItem(itemId))
				{
					_log.warn("BuffStoreTable: item " + itemId + " is outside " + ITEM_MIN + "-" + ITEM_MAX + ", skipped");
					continue;
				}
				if(ItemHolder.getInstance().getTemplate(itemId) == null)
				{
					_log.warn("BuffStoreTable: no item template " + itemId + " (skill " + skillId + "), skipped");
					continue;
				}
				if(SkillTable.getInstance().getInfo(skillId, level > 0 ? level : 1) == null)
				{
					_log.warn("BuffStoreTable: no skill " + skillId + " (item " + itemId + "), skipped");
					continue;
				}
				if(byItem.containsKey(itemId) || bySkill.containsKey(skillId))
				{
					_log.warn("BuffStoreTable: duplicate item " + itemId + " / skill " + skillId + ", skipped");
					continue;
				}
				if(name == null || name.isEmpty())
					name = ItemHolder.getInstance().getTemplate(itemId).getName();
				Buff buff = new Buff(itemId, skillId, level, minLevel, priceMin, priceMax, name);
				byItem.put(itemId, buff);
				bySkill.put(skillId, buff);
			}
		}
		catch(Exception e)
		{
			_log.error("BuffStoreTable: cannot read " + FILE, e);
		}
		_byItem = Collections.unmodifiableMap(byItem);
		_bySkill = Collections.unmodifiableMap(bySkill);
		_log.info("BuffStoreTable: loaded " + byItem.size() + " sellable buffs");
	}

	private static int intAttr(Element e, String name, int def)
	{
		String v = e.getAttribute(name);
		return v == null || v.trim().isEmpty() ? def : Integer.parseInt(v.trim());
	}

	private static long longAttr(Element e, String name, long def)
	{
		String v = e.getAttribute(name);
		return v == null || v.trim().isEmpty() ? def : Long.parseLong(v.trim());
	}

	public Buff getByItem(int itemId)
	{
		return _byItem.get(itemId);
	}

	public Buff getBySkill(int skillId)
	{
		return _bySkill.get(skillId);
	}

	/** all buffs in dummy item id order */
	public Collection<Buff> getAll()
	{
		return _byItem.values();
	}

	public int size()
	{
		return _byItem.size();
	}
}
