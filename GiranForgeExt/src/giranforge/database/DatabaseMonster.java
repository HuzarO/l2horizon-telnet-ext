/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Config
 *  l2.gameserver.data.xml.holder.NpcHolder
 *  l2.gameserver.model.GameObjectsStorage
 *  l2.gameserver.model.instances.MonsterInstance
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.model.reward.RewardData
 *  l2.gameserver.model.reward.RewardGroup
 *  l2.gameserver.model.reward.RewardList
 *  l2.gameserver.model.reward.RewardType
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.templates.npc.NpcTemplate
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Config.GiranForgeConfig;
import giranforge.MobWithItems;
import helpers.ScreenMessage;
import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.NpcHolder;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Player;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.reward.RewardData;
import l2.gameserver.model.reward.RewardGroup;
import l2.gameserver.model.reward.RewardList;
import l2.gameserver.model.reward.RewardType;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.templates.npc.NpcTemplate;

public class DatabaseMonster {
	protected static DatabaseMonster instance = new DatabaseMonster();
	private final Logger _log = LoggerFactory.getLogger(DatabaseMonster.class);

	public static DatabaseMonster getInstance() {
		return instance;
	}

	public void getMobList(Player player) {
		List<String> mobList = Arrays.stream(MobWithItems.getAllMobsWithItems()).filter(this::isValidMonster)
				.map(this::formatMobData).toList();
		this.sendMobListInChunks(player, mobList);
	}

	private boolean isValidMonster(MobWithItems monster) {
		return monster.getId() > 0 && !GiranForgeConfig.HIDDEN_MOBS.contains(monster.getId())
				&& this.hasValidName(monster) && this.hasValidStats(monster) && monster.isMonster();
	}

	private boolean hasValidName(MobWithItems monster) {
		return monster.getName() != null && !monster.getName().trim().isEmpty();
	}

	private boolean hasValidStats(MobWithItems monster) {
		return monster.getLevel() > 0 && monster.getExpReward() > 0L && monster.getSpReward() >= 0L
				&& monster.getMaxHp() > 0 && monster.getMaxMp() > 0 && monster.getPAtk() > 0 && monster.getPDef() > 0
				&& monster.getMAtk() > 0 && monster.getMDef() > 0;
	}

	private String formatMobData(MobWithItems mob) {
		return String.format("%d:%s:%d", mob.getId(), mob.getName().replaceAll(" ", "_"), mob.getLevel());
	}

	private void sendMobListInChunks(Player player, List<String> mobList) {
		int CHUNK_SIZE = 10;
		for (int i = 0; i < mobList.size(); i += 10) {
			int end = Math.min(i + 10, mobList.size());
			String chunk = String.join((CharSequence) ",", mobList.subList(i, end));
			player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37661, "MobList=" + chunk));
		}
	}

	public void loadMonsterWithReward() {
		NpcTemplate[] mobTemplates;
		for (NpcTemplate mobTemplate : mobTemplates = NpcHolder.getInstance().getAll()) {
			MonsterInstance mobInstance = new MonsterInstance(mobTemplate.getNpcId(), mobTemplate);
			if (!mobInstance.isMonster() || mobInstance.getColHeight() == 0.0 || mobInstance.getExpReward() == 0L
					|| mobInstance.getSpReward() == 0L) {
				mobInstance.decayMe();
				mobInstance.deleteMe();
				continue;
			}
			RewardList sweepRewardList = mobInstance.getTemplate().getRewardList(RewardType.SWEEP);
			RewardList notRatedNotGroupedRewardList = mobInstance.getTemplate()
					.getRewardList(RewardType.NOT_RATED_NOT_GROUPED);
			RewardList notRatedGroupedRewardList = mobInstance.getTemplate()
					.getRewardList(RewardType.NOT_RATED_GROUPED);
			RewardList ratedNotGroupedRewardList = mobInstance.getTemplate()
					.getRewardList(RewardType.RATED_NOT_GROUPED);
			RewardList ratedGroupedRewardList = mobInstance.getTemplate().getRewardList(RewardType.RATED_GROUPED);
			RewardList disabeldRewardList = mobInstance.getTemplate().getRewardList(RewardType.DISABLED);
			Map<RewardType, RewardList> rewardMap = Arrays
					.stream(new RewardList[] { sweepRewardList, notRatedNotGroupedRewardList, notRatedGroupedRewardList,
							ratedNotGroupedRewardList, ratedGroupedRewardList, disabeldRewardList })
					.filter(Objects::nonNull).collect(Collectors.toMap(RewardList::getType, rewardList -> rewardList));
			MobWithItems mobWithItems = new MobWithItems(mobInstance.getNpcId(), mobInstance);
			rewardMap.forEach((rewardType, rewardList) -> {
				if (rewardList != null) {
					List<RewardGroup> rewardDataList = rewardList.stream().toList();
					for (RewardGroup rewardGroup : rewardDataList) {
						List<RewardData> rewardData = rewardGroup.getItems();
						for (RewardData reward : rewardData) {
							mobWithItems.addItem(reward, (RewardType) rewardType, rewardGroup.getChance());
						}
					}
				}
			});
			mobInstance.decayMe();
			mobInstance.deleteMe();
		}
	}

	public void searchMobsByDropName(Player player, String[] args) {
		String itemName = args[0].replaceAll("_", " ");
		boolean sweepOnly = args.length > 1 && Objects.equals(args[1], "sweep");
		MobWithItems[] mobsWithItems = MobWithItems.getAllMobsWithItems();
		List<Integer> mobIds = Arrays.stream(mobsWithItems).filter(mob -> mob.hasItem(itemName, sweepOnly) != -1)
				.map(MobWithItems::getId).toList();
		String message = "MobIDs=" + String.join((CharSequence) ",", mobIds.stream().map(String::valueOf).toList());
		player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37663, message));
	}

	public void getMobLocation(Player player, String[] args) {
		int mobId = Integer.parseInt(args[0]);
		StringBuilder message = new StringBuilder();
		boolean raid = this.isRaidBoss(mobId);
		if (raid) {
			message.append("MobID=").append(mobId);
			message.append(" IsRaid=true");
			message.append(" NoSpawns=true");
			player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37695, message.toString()));
			return;
		}
		ArrayList<String> locations = new ArrayList<String>();
		List<NpcInstance> monsters = GameObjectsStorage.getAllByNpcId((int) mobId, (boolean) true);
		if (!monsters.isEmpty()) {
			int count = 0;
			for (NpcInstance npc : monsters) {
				if (count >= 10)
					break;
				locations.add(npc.getX() + "," + npc.getY() + "," + npc.getZ());
				++count;
			}
		}
		if (locations.isEmpty()) {
			message.append("MobID=").append(mobId);
			message.append(" IsRaid=false");
			message.append(" NoSpawns=true");
			player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37695, message.toString()));
			return;
		}
		message.append("MobID=").append(mobId);
		message.append(" Locations=").append(String.join((CharSequence) ";", locations));
		message.append(" IsRaid=false");
		player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37695, message.toString()));
	}

	public void getMobData(Player player, int monsterId) {
		this.getDropData(player, monsterId);
		MobWithItems mobWithItems = MobWithItems.getMobWithItems(monsterId);
		if (mobWithItems == null) {
			this._log.warn("Player {} tried to get info from Mob with ID {} not found in database.",
					(Object) player.getName(), (Object) monsterId);
			return;
		}
		String message = "Size=" + mobWithItems.getFinalZoom() + " MobID=" + mobWithItems.getId() + " Name="
				+ mobWithItems.getName().replaceAll(" ", "_") + " Level=" + mobWithItems.getLevel() + " xp="
				+ mobWithItems.getExpReward() + " sp=" + mobWithItems.getSpReward() + " hp=" + mobWithItems.getMaxHp()
				+ " mp=" + mobWithItems.getMaxMp() + " pAtk=" + mobWithItems.getPAtk() + " pDef="
				+ mobWithItems.getPDef() + " mAtk=" + mobWithItems.getMAtk() + " mDef=" + mobWithItems.getMDef()
				+ " type=" + mobWithItems.getType() + " loc=" + mobWithItems.getLoc() + " isRaid="
				+ this.isRaidBoss(monsterId);
		player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37666, message));
	}

	public void getDropData(Player player, int monsterId) {
		MobWithItems mobWithItems = MobWithItems.getMobWithItems(monsterId);
		if (mobWithItems == null || mobWithItems.allDrops.isEmpty()) {
			player.sendMessage("Something went wrong, please try again.");
			return;
		}
		StringBuilder message = new StringBuilder();
		boolean isRaid = false;
		boolean isSiegeGuard = false;
		try {
			NpcInstance npc;
			NpcInstance npcInstance = npc = player.getTarget() instanceof NpcInstance ? (NpcInstance) player.getTarget()
					: null;
			if (npc != null) {
				isRaid = npc.isRaid();
				isSiegeGuard = npc.isSiegeGuard();
			}
		} catch (Exception exception) {
			// empty catch block
		}
		for (MobWithItems.DropInfo drop : mobWithItems.allDrops) {
			double finalChance;
			long minDrop = drop.reward.getMinDrop();
			long maxDrop = drop.reward.getMaxDrop();
			switch (drop.rewardType) {
			case SWEEP: {
				finalChance = this.calculateSweepChanceForUser(drop, player);
				break;
			}
			case RATED_GROUPED: {
				DropResult groupedResult = this.calculateRatedGroupedChanceForUser(drop, player, isRaid, isSiegeGuard);
				finalChance = groupedResult.chance;
				minDrop = groupedResult.minDrop;
				maxDrop = groupedResult.maxDrop;
				break;
			}
			case NOT_RATED_GROUPED: {
				finalChance = this.calculateNotRatedGroupedChanceForUser(drop);
				break;
			}
			case NOT_RATED_NOT_GROUPED: {
				finalChance = this.calculateNotRatedNotGroupedChanceForUser(drop);
				break;
			}
			case RATED_NOT_GROUPED: {
				DropResult result = this.calculateRatedNotGroupedChanceForUser(drop, player, isRaid, isSiegeGuard);
				finalChance = result.chance;
				minDrop = result.minDrop;
				maxDrop = result.maxDrop;
				break;
			}
			default: {
				finalChance = drop.reward.getChance() / 1000000.0;
			}
			}
			message.delete(0, message.length());
			message.append("MobID=").append(mobWithItems.getId());
			message.append(" dropType=").append(drop.rewardType.name());
			message.append(" itemID=").append(drop.reward.getItemId());
			double partsPerMillion = finalChance * 1000000.0;
			message.append(" chance=").append(Math.round(partsPerMillion));
			message.append(" min=").append(minDrop);
			message.append(" max=").append(maxDrop);
			player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37662, message.toString()));
		}
	}

	private boolean isRaidBoss(int npcId) {
		try {
			NpcTemplate tpl = NpcHolder.getInstance().getTemplate(npcId);
			return tpl != null && tpl.isRaid;
		} catch (Exception e) {
			return false;
		}
	}

	private DropResult calculateRatedNotGroupedChanceForUser(MobWithItems.DropInfo drop, Player player, boolean isRaid,
			boolean isSiegeGuard) {
		double baseChance = drop.reward.getChance() / 1000000.0;
		double rateMultiplier = drop.reward.getItem().isAdena()
				? Config.RATE_DROP_RATED_NOT_GROUPED_ADENA * player.getRateAdena()
				: (drop.reward.getItem().isSealStone()
						? Config.RATE_DROP_RATED_NOT_GROUPED_STONES * player.getRateSealStones()
						: (isRaid
								? Config.RATE_DROP_RATED_NOT_GROUPED_BOSSES
										* (double) player.getBonus().getDropRaidItems()
								: (isSiegeGuard ? Config.RATE_DROP_RATED_NOT_GROUPED_GUARDS
										: Config.RATE_DROP_RATED_NOT_GROUPED_ITEMS * player.getRateItems())));
		double finalChance = baseChance * rateMultiplier;
		long minDrop = drop.reward.getMinDrop();
		long maxDrop = drop.reward.getMaxDrop();
		if (drop.reward.getItem().isAdena()) {
			minDrop = (long) Math.floor((double) minDrop * rateMultiplier);
			maxDrop = (long) Math.ceil((double) maxDrop * rateMultiplier);
		}
		if (finalChance > 1.0 && !drop.reward.getItem().isAdena()) {
			double overflowMultiplier = finalChance;
			finalChance = 1.0;
			minDrop = (long) Math.floor((double) minDrop * overflowMultiplier);
			maxDrop = (long) Math.ceil((double) maxDrop * overflowMultiplier);
		}
		finalChance = Math.min(1.0, finalChance);
		return new DropResult(finalChance, minDrop, maxDrop);
	}

	private double calculateNotRatedNotGroupedChanceForUser(MobWithItems.DropInfo drop) {
		return drop.reward.getChance() / 1000000.0;
	}

	private double calculateNotRatedGroupedChanceForUser(MobWithItems.DropInfo drop) {
		double baseChance = drop.reward.getChance() / 1000000.0;
		return baseChance * (drop.groupChance / 1000000.0);
	}

	private double calculateSweepChanceForUser(MobWithItems.DropInfo drop, Player player) {
		double baseChance = drop.reward.getChance() / 1000000.0;
		double groupModifiedChance = baseChance * (drop.groupChance / 1000000.0);
		double rateMultiplier = Config.RATE_DROP_SPOIL * player.getRateSpoil();
		return Math.min(1.0, groupModifiedChance * rateMultiplier);
	}

	private DropResult calculateRatedGroupedChanceForUser(MobWithItems.DropInfo drop, Player player, boolean isRaid,
			boolean isSiegeGuard) {
		double baseChance = drop.reward.getChance() / 1000000.0;
		double groupModifiedChance = baseChance * (drop.groupChance / 1000000.0);
		double rateMultiplier = drop.reward.getItem().isAdena() ? Config.RATE_DROP_ADENA * player.getRateAdena()
				: (drop.reward.getItem().isSealStone() ? Config.RATE_DROP_SEAL_STONES * player.getRateSealStones()
						: (isRaid ? Config.RATE_DROP_RAIDBOSS * (double) player.getBonus().getDropRaidItems()
								: (isSiegeGuard ? Config.RATE_DROP_SIEGE_GUARD
										: Config.RATE_DROP_ITEMS * player.getRateItems())));
		double finalChance = groupModifiedChance * rateMultiplier;
		long minDrop = drop.reward.getMinDrop();
		long maxDrop = drop.reward.getMaxDrop();
		if (drop.reward.getItem().isAdena()) {
			minDrop = (long) Math.floor((double) minDrop * rateMultiplier);
			maxDrop = (long) Math.ceil((double) maxDrop * rateMultiplier);
		}
		if (finalChance > 1.0 && !drop.reward.getItem().isAdena()) {
			double overflowMultiplier = finalChance;
			finalChance = 1.0;
			minDrop = (long) Math.floor((double) minDrop * overflowMultiplier);
			maxDrop = (long) Math.ceil((double) maxDrop * overflowMultiplier);
		}
		finalChance = Math.min(1.0, finalChance);
		return new DropResult(finalChance, minDrop, maxDrop);
	}
}
