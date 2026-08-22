package com.l2horizon.CustomQuestsExt;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Element;

import com.l2horizon.CustomQuestsExt.utils.BuffSkill;
import com.l2horizon.CustomQuestsExt.utils.BufferManagerHolder;

import l2.commons.data.xml.AbstractFileParser;
import l2.commons.dbutils.DbUtils;
import l2.gameserver.Config;
import l2.gameserver.database.DatabaseFactory;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Skill;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.tables.SkillTable;

/**
 * Loads and stores available {@link BuffSkill}s for the integrated scheme
 * buffer. Loads and stores Players' buff schemes into _schemesTable (under a
 * {@link String} name and a {@link List} of {@link Integer} skill ids).
 */
public final class BufferManager extends AbstractFileParser<BufferManagerHolder> {

	private static final String LOAD_SCHEMES = "SELECT * FROM buffer_schemes";
	private static final String TRUNCATE_SCHEMES = "TRUNCATE buffer_schemes";
	private static final String INSERT_SCHEME = "INSERT INTO buffer_schemes (object_id, scheme_name, skills) VALUES (?,?,?)";

	private final Map<Integer, Map<String, ArrayList<Integer>>> _schemesTable = new ConcurrentHashMap<>();

	private BufferManager() {
		super(BufferManagerHolder.getInstance());
	}

	@Override
	public File getXMLFile() {
		return new File(Config.DATAPACK_ROOT, "data/buffer_skills.xml");
	}

	@Override
	public String getDTDFileName() {
		return "buffer_skills.dtd";
	}

	@Override
	protected void readData(Element rootElement) throws Exception {
		// Clear existing buffs
		getHolder().clear();

		// Parse all categories
		Iterator<Element> categoryIterator = rootElement.elementIterator("category");

		while (categoryIterator.hasNext()) {
			Element categoryElement = categoryIterator.next();
			String category = categoryElement.attributeValue("type");

			// Parse all buffs in this category
			Iterator<Element> buffIterator = categoryElement.elementIterator("buff");

			while (buffIterator.hasNext()) {
				Element buffElement = buffIterator.next();

				// Parse buff attributes
				int skillId = Integer.parseInt(buffElement.attributeValue("id"));

				// Get skill level (default to max level if not specified)
				String levelAttr = buffElement.attributeValue("level");
				int skillLevel;
				if (levelAttr != null) {
					skillLevel = Integer.parseInt(levelAttr);
				} else {
					skillLevel = SkillTable.getInstance().getMaxLevel(skillId);
				}

				// Get price (default to 0 if not specified)
				String priceAttr = buffElement.attributeValue("price");
				int price = (priceAttr != null) ? Integer.parseInt(priceAttr) : 0;

				// Get description (default to empty string if not specified)
				String desc = buffElement.attributeValue("desc");
				if (desc == null) {
					desc = "";
				}

				// Create and store the BuffSkill
				getHolder().addBuff(skillId, new BuffSkill(skillId, skillLevel, price, category, desc));
			}
		}

		info("Loaded " + getHolder().size() + " available buffs.");

		// Load player schemes from database
		loadSchemes();
	}

	/**
	 * Load player buff schemes from database.
	 */
	private void loadSchemes() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DatabaseFactory.getInstance().getConnection();
			ps = con.prepareStatement(LOAD_SCHEMES);
			rs = ps.executeQuery();

			while (rs.next()) {
				final ArrayList<Integer> schemeList = new ArrayList<>();

				final String[] skills = rs.getString("skills").split(",");
				for (String skill : skills) {
					// Don't feed the skills list if the list is empty.
					if (skill.isEmpty()) {
						break;
					}

					final int skillId = Integer.parseInt(skill);

					// Integrity check to see if the skillId is available as a buff.
					if (getHolder().getBuff(skillId) != null) {
						schemeList.add(skillId);
					}
				}

				setScheme(rs.getInt("object_id"), rs.getString("scheme_name"), schemeList, 10);
			}
		} catch (Exception e) {
			error("Failed to load schemes data.", e);
		} finally {
			DbUtils.closeQuietly(con, ps, rs);
		}
	}

	/**
	 * Save all player buff schemes to database.
	 */
	public void saveSchemes() {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DatabaseFactory.getInstance().getConnection();

			// Delete all entries from database.
			ps = con.prepareStatement(TRUNCATE_SCHEMES);
			ps.execute();
			DbUtils.closeQuietly(ps);

			// Save _schemesTable content.
			ps = con.prepareStatement(INSERT_SCHEME);

			for (Map.Entry<Integer, Map<String, ArrayList<Integer>>> player : _schemesTable.entrySet()) {
				for (Map.Entry<String, ArrayList<Integer>> scheme : player.getValue().entrySet()) {
					// Build a String composed of skill ids separated by a ",".
					StringBuilder sb = new StringBuilder();
					for (int skillId : scheme.getValue()) {
						if (sb.length() > 0) {
							sb.append(",");
						}
						sb.append(skillId);
					}

					ps.setInt(1, player.getKey());
					ps.setString(2, scheme.getKey());
					ps.setString(3, sb.toString());
					ps.addBatch();
				}
			}
			ps.executeBatch();
		} catch (Exception e) {
			error("Failed to save schemes data.", e);
		} finally {
			DbUtils.closeQuietly(con, ps);
		}
	}

	/**
	 * Add or retrieve the Player schemes {@link Map}, then add or update the given
	 * scheme based on the {@link String} name set as parameter.
	 * 
	 * @param playerId   : The Player objectId to check.
	 * @param schemeName : The {@link String} used as scheme name.
	 * @param list       : The {@link ArrayList} of {@link Integer} used as skill
	 *                   ids.
	 * @param maxSchemes : Allowed number of schemes.
	 */
	public void setScheme(int playerId, String schemeName, ArrayList<Integer> list, int maxSchemes) {
		final Map<String, ArrayList<Integer>> schemes = _schemesTable.computeIfAbsent(playerId,
				s -> new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
		if (schemes.size() >= maxSchemes) {
			return;
		}

		schemes.put(schemeName, list);
	}

	/**
	 * @param playerId : The Player objectId to check.
	 * @return the {@link Map} of schemes for a given Player.
	 */
	public Map<String, ArrayList<Integer>> getPlayerSchemes(int playerId) {
		return _schemesTable.get(playerId);
	}

	/**
	 * @param playerId   : The Player objectId to check.
	 * @param schemeName : The scheme name to check.
	 * @return The {@link List} holding skill ids for the given scheme name and
	 *         Player, or empty list if scheme or Player isn't registered.
	 */
	public List<Integer> getScheme(int playerId, String schemeName) {
		final Map<String, ArrayList<Integer>> schemes = _schemesTable.get(playerId);
		if (schemes == null) {
			return Collections.emptyList();
		}

		final ArrayList<Integer> scheme = schemes.get(schemeName);
		if (scheme == null) {
			return Collections.emptyList();
		}

		return scheme;
	}

	/**
	 * Apply all effects of a scheme (retrieved by its Player objectId and
	 * {@link String} name) upon a {@link Creature} target.
	 * 
	 * @param npc        : The {@link NpcInstance} which applies effects.
	 * @param target     : The {@link Creature} benefactor.
	 * @param playerId   : The Player objectId to check.
	 * @param schemeName : The scheme name to check.
	 */
	public void applySchemeEffects(NpcInstance npc, Creature target, int playerId, String schemeName) {
		for (int skillId : getScheme(playerId, schemeName)) {
			final BuffSkill holder = getAvailableBuff(skillId);
			if (holder != null) {
				final Skill skill = holder.getSkill();
				if (skill != null) {
					skill.getEffects(npc, target, false, false);
				}
			}
		}
	}

	/**
	 * @param playerId   : The Player objectId to check.
	 * @param schemeName : The scheme name to check.
	 * @param skillId    : The skill id to check.
	 * @return True if the skill is already registered on the scheme, or false
	 *         otherwise.
	 */
	public boolean getSchemeContainsSkill(int playerId, String schemeName, int skillId) {
		return getScheme(playerId, schemeName).contains(skillId);
	}

	/**
	 * @param groupType : The {@link String} group type of skill ids to return.
	 * @return a {@link List} of skill ids based on the given {@link String}
	 *         groupType.
	 */
	public List<Integer> getSkillsIdsByType(String groupType) {
		final List<Integer> skills = new ArrayList<>();
		for (BuffSkill holder : getHolder().getAllBuffs().values()) {
			if (holder.type().equalsIgnoreCase(groupType)) {
				skills.add(holder.id());
			}
		}
		return skills;
	}

	/**
	 * @return a {@link List} of all available {@link String} buff types.
	 */
	public List<String> getSkillTypes() {
		final List<String> skillTypes = new ArrayList<>();
		for (BuffSkill holder : getHolder().getAllBuffs().values()) {
			if (!skillTypes.contains(holder.type())) {
				skillTypes.add(holder.type());
			}
		}
		return skillTypes;
	}

	/**
	 * @param skillId : The skill id to check.
	 * @return The {@link BuffSkill} for the given skill id, or null if not found.
	 */
	public BuffSkill getAvailableBuff(int skillId) {
		return getHolder().getBuff(skillId);
	}

	/**
	 * @return The {@link Map} of all available buffs.
	 */
	public Map<Integer, BuffSkill> getAvailableBuffs() {
		return getHolder().getAllBuffs();
	}

	public static BufferManager getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder {
		protected static final BufferManager INSTANCE = new BufferManager();
	}
}
