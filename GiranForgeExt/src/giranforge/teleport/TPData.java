/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.templates.StatsSet
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge.teleport;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import helpers.XMLDocument;
import l2.gameserver.templates.StatsSet;

public class TPData extends XMLDocument {
	private static final Logger _log = LoggerFactory.getLogger(TPData.class);
	private final Map<String, TPLocation> _teleports = new HashMap<String, TPLocation>();

	protected TPData() {
		this.load();
	}

	public void reload() {
		this._teleports.clear();
		this.load();
	}

	@Override
	protected void load() {
		this.loadDocument("./data/xml/tp.xml");
		_log.info("[Giran Forge]=> Loaded [" + this._teleports.size() + "] teleport locations.");
	}

	@Override
	protected void parseDocument(Document doc, File file) {
		StatsSet set = new StatsSet();
		Node n = doc.getFirstChild();
		for (Node o = n.getFirstChild(); o != null; o = o.getNextSibling()) {
			if (!"teleport".equalsIgnoreCase(o.getNodeName()))
				continue;
			TPData.parseAndFeed(o.getAttributes(), set);
			this._teleports.put(set.getString("id"), new TPLocation(set));
			set.clear();
		}
	}

	public TPLocation getTeleportLocation(String id) {
		return this._teleports.get(id);
	}

	public static TPData getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder {
		protected static final TPData INSTANCE = new TPData();

		private SingletonHolder() {
		}
	}
}
