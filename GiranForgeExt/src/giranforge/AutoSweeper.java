/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.listener.Listener
 *  l2.gameserver.listener.actor.OnDeathListener
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.actor.listener.CharListenerList
 *  l2.gameserver.model.instances.MonsterInstance
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.scripts.ScriptFile
 */
package giranforge;

import java.util.List;

import l2.commons.listener.Listener;
import l2.gameserver.listener.actor.OnDeathListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.actor.listener.CharListenerList;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.scripts.ScriptFile;

public class AutoSweeper implements ScriptFile {
	private static final int FESTIVE_SWEEPER_ID = 444;
	private static final int SWEEPER_ID = 42;
	public OnDeathListener autoSpoil = (creature, killer) -> {
		if (creature != null && killer != null && creature instanceof MonsterInstance) {
			Player player;
			MonsterInstance monsterInstance = (MonsterInstance) creature;
			if (killer instanceof Player
					&& (player = (Player) ((Object) ((Object) ((Object) killer)))).getFarmSystem().isAutofarming()) {
				Skill festiveSweeper = player.getKnownSkill(444);
				if (festiveSweeper != null) {
					this.tryUseFestiveSweeper(player, festiveSweeper, (Creature) monsterInstance);
					return;
				}
				Skill sweeper = player.getKnownSkill(42);
				if (sweeper != null) {
					this.tryUseSweeper(player, monsterInstance, sweeper);
					return;
				}
				if (!player.isInParty()) {
					return;
				}
				for (Player partyMember : player.getParty()) {
					if (partyMember.getKnownSkill(444) != null) {
						this.tryUseFestiveSweeper(partyMember, partyMember.getKnownSkill(444),
								(Creature) monsterInstance);
						return;
					}
					if (partyMember.getKnownSkill(42) == null)
						continue;
					this.tryUseSweeper(partyMember, monsterInstance, partyMember.getKnownSkill(42));
					return;
				}
			}
		}
	};

	public void tryUseSweeper(Player player, MonsterInstance target, Skill sweeper) {
		if (target.isSpoiled(player) && target.isSweepActive()) {
			player.doCast(sweeper, (Creature) target, true);
		}
	}

	public void tryUseFestiveSweeper(Player player, Skill festiveSweeper, Creature target) {
		boolean hasSpoilDrop = false;
		List<NpcInstance> npcRadius = player.getAroundNpc(600, 300);
		for (NpcInstance npcInstance : npcRadius) {
			MonsterInstance monster;
			if (!npcInstance.isMonster() || !npcInstance.isDead() || !(npcInstance instanceof MonsterInstance)
					|| !(monster = (MonsterInstance) npcInstance).isSpoiled(player) || !monster.isSweepActive())
				continue;
			hasSpoilDrop = true;
			break;
		}
		if (hasSpoilDrop) {
			player.doCast(festiveSweeper, target, true);
		}
	}

	public void onLoad() {
		CharListenerList.addGlobal((Listener) this.autoSpoil);
	}

	public void onReload() {
		this.onShutdown();
		this.onLoad();
	}

	public void onShutdown() {
		CharListenerList.removeGlobal((Listener) this.autoSpoil);
	}
}
