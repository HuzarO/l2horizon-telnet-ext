/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.threading.RunnableImpl
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Zone$ZoneType
 *  l2.gameserver.scripts.Functions
 */
package l2.gameserver.model.fish;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import l2.commons.threading.RunnableImpl;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.Zone;
import l2.gameserver.scripts.Functions;

public class AutoFishManager
extends Functions {
    private static final AutoFishManager INSTANCE = new AutoFishManager();
    private final ConcurrentHashMap<Player, ScheduledFuture<?>> autoFishTask = new ConcurrentHashMap();

    private AutoFishManager() {
    }

    public static AutoFishManager getInstance() {
        return INSTANCE;
    }

    public void startFish(Player player) {
        if (player == null) {
            return;
        }
        if (this.autoFishTask.containsKey((Object)player)) {
            this.stopFishing(player);
            return;
        }
        if (!player.isInZone(Zone.ZoneType.FISHING)) {
            player.sendMessage("You are not in a fishing zone.");
            return;
        }
        Skill fishingSkill = player.getKnownSkill(1312);
        if (fishingSkill != null && fishingSkill.checkCondition((Creature)((Object)player), (Creature)((Object)player), false, false, false)) {
            player.doCast(fishingSkill, (Creature)((Object)player), true);
            player.sendMessage("Fishing started!");
            ScheduledFuture task = ThreadPoolManager.getInstance().scheduleAtFixedRate((Runnable)((Object)new FishingTask(player)), 3000L, 1000L);
            ScheduledFuture activeTask = this.autoFishTask.put(player, task);
            if (activeTask != null) {
                activeTask.cancel(true);
            }
        } else {
            player.sendMessage("Cannot start fishing. Check your conditions.");
        }
    }

    public void stopFishing(Player player) {
        if (player == null) {
            return;
        }
        ScheduledFuture<?> task = this.autoFishTask.remove((Object)player);
        player.abortCast(true, true);
        player.abortAttack(true, true);
        player.stopFishing();
        if (task != null) {
            task.cancel(true);
            player.sendMessage("Fishing stopped!");
        }
    }

    private class FishingTask
    extends RunnableImpl {
        private final Player player;

        public FishingTask(Player player) {
            this.player = player;
        }

        public void runImpl() {
            int castSkillId;
            Skill skill;
            if (!this.player.isFishing()) {
                if (this.player.isDead() || !this.player.isInZone(Zone.ZoneType.FISHING)) {
                    AutoFishManager.this.stopFishing(this.player);
                    return;
                }
                if (!this.player.getFishing().isInCombat()) {
                    Skill fish = this.player.getKnownSkill(1312);
                    if (fish == null) {
                        AutoFishManager.this.stopFishing(this.player);
                        return;
                    }
                    this.player.doCast(fish, (Creature)((Object)this.player), true);
                }
            }
            if (this.player.getFishing().isInCombat() && (skill = this.player.getKnownSkill(castSkillId = this.player.isPumpFailed ? 1314 : 1313)) != null) {
                this.player.doCast(skill, (Creature)((Object)this.player), true);
            }
        }
    }
}

