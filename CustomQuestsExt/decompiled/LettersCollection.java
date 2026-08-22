package events.l2day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import l2.commons.lang.reference.HardReference;
import l2.commons.util.Rnd;
import l2.gameserver.Announcements;
import l2.gameserver.Config;
import l2.gameserver.handler.bypass.INpcHtmlAppendHandler;
import l2.gameserver.instancemanager.SpawnManager;
import l2.gameserver.listener.actor.OnDeathListener;
import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.listener.CharListenerList;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.reward.RewardData;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.templates.npc.NpcTemplate;

public class LettersCollection extends Functions implements INpcHtmlAppendHandler, OnDeathListener, OnPlayerEnterListener, ScriptFile {
	private static final Logger logger = LoggerFactory.getLogger(LettersCollection.class);
	
	private static boolean isEventActive;
	
	private static final String EVENT_NAME = "LettersCollection";
	private static final String ANNOUNCE_EVENT_STARTED = "scripts.events.l2day.AnnounceEventStarted";
	private static final String ANNOUNCE_EVENT_STOPPED = "scripts.events.l2day.AnnounceEventStoped";
	private static final String EVENT_SPAWN_GROUP = "[event_letter_collection_spawn]";
	
	private static final Map<String, Integer[][]> wordsMap = new HashMap<>();
	private static final Map<String, RewardData[]> rewardsMap = new HashMap<>();
	private static final List<LetterDrop> letterDrops = new ArrayList<>();
	
	public LettersCollection() {
		super();
	}
	
	@Override
	public void onLoad() {
		CharListenerList.addGlobal(this);
		
		if (isActive()) {
			loadWords();
			loadRewards();
			loadLettersDrop();
			isEventActive = true;
			spawnEventManagers();
			logger.info("Loaded Event: LettersCollection [state: activated]");
		} else {
			logger.info("Loaded Event: LettersCollection [state: deactivated]");
		}
	}
	
	protected static boolean isActive() {
		return IsActive(EVENT_NAME);
	}
	
	protected void spawnEventManagers() {
		SpawnManager.getInstance().spawn(EVENT_SPAWN_GROUP);
	}
	
	protected void unSpawnEventManagers() {
		SpawnManager.getInstance().despawn(EVENT_SPAWN_GROUP);
	}
	
	@Override
	public void onReload() {
		unSpawnEventManagers();
	}
	
	@Override
	public void onShutdown() {
		unSpawnEventManagers();
	}
	
	@Override
	public void onDeath(Creature victim, Creature killer) {
		if (!isEventActive) {
			return;
		}
		
		if (!simpleCheckDrop(victim, killer)) {
			return;
		}
		
		List<LetterDrop> drops = new ArrayList<>(letterDrops);
		if (drops.isEmpty()) {
			return;
		}
		
		LetterDrop drop = drops.get(Rnd.get(drops.size()));
		
		double chance = drop.chance * ((NpcTemplate)victim.getTemplate()).rateHp;
		if (Rnd.chance(chance)) {
			((NpcInstance)victim).dropItem(killer.getPlayer(), drop.id, 1L);
		}
	}
	
	public void startEvent() {
		Player player = getSelf();
		if (!player.getPlayerAccess().IsEventGm) {
			return;
		}
		
		if (SetActive(EVENT_NAME, true)) {
			loadWords();
			loadRewards();
			loadLettersDrop();
			spawnEventManagers();
			Announcements.getInstance().announceByCustomMessage(ANNOUNCE_EVENT_STARTED, null);
		} else {
			player.sendMessage("Event 'LettersCollection' already started.");
		}
		
		isEventActive = true;
		show("admin/events/events.htm", player);
	}
	
	public void stopEvent() {
		Player player = getSelf();
		if (!player.getPlayerAccess().IsEventGm) {
			return;
		}
		
		if (SetActive(EVENT_NAME, false)) {
			unSpawnEventManagers();
			Announcements.getInstance().announceByCustomMessage(ANNOUNCE_EVENT_STOPPED, null);
		} else {
			player.sendMessage("Event 'LettersCollection' not started.");
		}
		
		isEventActive = false;
		show("admin/events/events.htm", player);
	}
	
	public void exchange(String[] params) {
		Player player = getSelf();
		
		if (!player.isQuestContinuationPossible(true)) {
			return;
		}
		
		if (!NpcInstance.canBypassCheck(player, player.getLastNpc())) {
			return;
		}
		
		String word = params[0];
		
		Integer[][] letters = wordsMap.get(word);
		if (letters == null) {
			player.sendMessage("Wrong word.");
			return;
		}
		
		// Check if player has all required letters
		for (Integer[] letter : letters) {
			int itemId = letter[0];
			int quantity = letter[1];
			
			if (getItemCount(player, itemId) < quantity) {
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_REQUIRED_ITEMS);
				return;
			}
		}
		
		// Remove letters from inventory
		for (Integer[] letter : letters) {
			int itemId = letter[0];
			int quantity = letter[1];
			removeItem(player, itemId, quantity, false);
		}
		
		// Give reward
		RewardData[] rewards = rewardsMap.get(word);
		if (rewards == null || rewards.length == 0) {
			player.sendMessage("There is no reward set for this word.");
			return;
		}
		
		// Calculate total chance
		int totalChance = 0;
		for (RewardData reward : rewards) {
			totalChance += (int)reward.getChance();
		}
		
		// Roll for reward
		int roll = Rnd.get(totalChance);
		int currentChance = 0;
		
		for (RewardData reward : rewards) {
			currentChance += (int)reward.getChance();
			if (currentChance > roll) {
				addItem(player, reward.getItemId(), Rnd.get(reward.getMinDrop(), reward.getMaxDrop()));
				return;
			}
		}
	}
	
	@Override
	public void onPlayerEnter(Player player) {
		if (isEventActive) {
			Announcements.getInstance().announceToPlayerByCustomMessage(player, ANNOUNCE_EVENT_STARTED, null);
		}
	}
	
	@Override
	public String getHtmlAppends(Integer npcId) {
		if (!isEventActive) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder("<br1>");
		
		for (String word : wordsMap.keySet()) {
			sb.append("[scripts_");
			sb.append(getClass().getName());
			sb.append(":exchange ");
			sb.append(word);
			sb.append("|");
			sb.append(word);
			sb.append("]<br1>");
		}
		
		return sb.toString();
	}
	
	public void loadRewards() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("data/events/l2day/l2day_rewards.xml");
			
			NodeList rewardNodes = doc.getElementsByTagName("reward");
			
			for (int i = 0; i < rewardNodes.getLength(); i++) {
				Element rewardElement = (Element)rewardNodes.item(i);
				String word = rewardElement.getAttribute("word");
				
				List<RewardData> rewards = new ArrayList<>();
				
				NodeList itemNodes = rewardElement.getElementsByTagName("item");
				for (int j = 0; j < itemNodes.getLength(); j++) {
					Element itemElement = (Element)itemNodes.item(j);
					
					int id = Integer.parseInt(itemElement.getAttribute("id"));
					int min = Integer.parseInt(itemElement.getAttribute("min"));
					int max = Integer.parseInt(itemElement.getAttribute("max"));
					int chance = Integer.parseInt(itemElement.getAttribute("chance"));
					
					rewards.add(new RewardData(id, min, max, chance));
				}
				
				rewardsMap.put(word, rewards.toArray(new RewardData[0]));
			}
		} catch (Exception e) {
			logger.error("Loading error rewards.xml: ", e);
		}
	}
	
	public void loadWords() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("data/events/l2day/l2day_words.xml");
			
			NodeList wordNodes = doc.getElementsByTagName("word");
			
			for (int i = 0; i < wordNodes.getLength(); i++) {
				Element wordElement = (Element)wordNodes.item(i);
				String name = wordElement.getAttribute("name");
				
				List<Integer[]> letters = new ArrayList<>();
				
				NodeList letterNodes = wordElement.getElementsByTagName("letter");
				for (int j = 0; j < letterNodes.getLength(); j++) {
					Element letterElement = (Element)letterNodes.item(j);
					
					int id = Integer.parseInt(letterElement.getAttribute("id"));
					int quantity = Integer.parseInt(letterElement.getAttribute("quantity"));
					
					letters.add(new Integer[] { id, quantity });
				}
				
				wordsMap.put(name, letters.toArray(new Integer[0][0]));
			}
		} catch (Exception e) {
			logger.error("Loading error words.xml: ", e);
		}
	}
	
	public void loadLettersDrop() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("data/events/l2day/l2day_letters_drop.xml");
			
			NodeList letterNodes = doc.getElementsByTagName("letter");
			
			for (int i = 0; i < letterNodes.getLength(); i++) {
				Element letterElement = (Element)letterNodes.item(i);
				
				int id = Integer.parseInt(letterElement.getAttribute("id"));
				double chance = Double.parseDouble(letterElement.getAttribute("chance"));
				
				letterDrops.add(new LetterDrop(id, chance));
			}
		} catch (Exception e) {
			logger.error("Loading error letters_drop.xml: ", e);
		}
	}
	
	@Override
	public int[] getNpcIds() {
		return Config.EVENT_L2DAY_LETTER_NPC_ID;
	}
	
	@Override
	public String getAppend(Player player, int npcId, int npcObjectId) {
		LettersCollection instance = new LettersCollection();
		instance.self = player.getRef();
		return instance.getHtmlAppends(npcObjectId);
	}
	
	private static class LetterDrop {
		final int id;
		final double chance;
		
		LetterDrop(int id, double chance) {
			this.id = id;
			this.chance = chance;
		}
	}
}
