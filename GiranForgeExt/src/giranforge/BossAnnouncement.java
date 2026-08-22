/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.configuration.ExProperties
 *  l2.commons.listener.Listener
 *  l2.gameserver.Announcements
 *  l2.gameserver.listener.actor.OnAttackListener
 *  l2.gameserver.listener.actor.OnDeathListener
 *  l2.gameserver.model.actor.listener.CharListenerList
 *  l2.gameserver.model.instances.RaidBossInstance
 *  l2.gameserver.network.l2.components.ChatType
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.s2c.Say2
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import helpers.ScreenMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import l2.commons.configuration.ExProperties;
import l2.commons.listener.Listener;
import l2.gameserver.Announcements;
import l2.gameserver.listener.actor.OnAttackListener;
import l2.gameserver.listener.actor.OnDeathListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.listener.CharListenerList;
import l2.gameserver.model.instances.RaidBossInstance;
import l2.gameserver.network.l2.components.ChatType;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;
import l2.gameserver.network.l2.s2c.Say2;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BossAnnouncement
implements OnAttackListener,
OnDeathListener,
ScriptFile {
    protected static final Logger _log = LoggerFactory.getLogger(BossAnnouncement.class);
    private static final BossAnnouncement INSTANCE = new BossAnnouncement();
    private final Map<Integer, Map<Integer, Boolean>> rbPercentages = new HashMap<Integer, Map<Integer, Boolean>>();
    private final Map<Integer, Boolean> isBeingAttacked = new HashMap<Integer, Boolean>();
    private static final String CONFIG_FILE = "config/custom/giranforge.properties";
    private static boolean ENABLE_RB_ANNOUNCEMENTS = true;
    private static int MIN_RB_LEVEL = 75;
    private static int DISTANCE_TO_ANNOUNCE = 5000;
    private static boolean SHOW_SCREEN_MESSAGE = false;
    private static int SHOW_SCREEN_MESSAGE_TIME = 5000;

    public static void loadConfigs() {
        ExProperties config = BossAnnouncement.initProperties(CONFIG_FILE);
        ENABLE_RB_ANNOUNCEMENTS = Boolean.parseBoolean(config.getProperty("ENABLE_RB_ANNOUNCEMENTS", "true"));
        MIN_RB_LEVEL = Integer.parseInt(config.getProperty("MIN_RB_LEVEL", "15"));
        DISTANCE_TO_ANNOUNCE = Integer.parseInt(config.getProperty("DISTANCE_TO_ANNOUNCE", "30"));
        SHOW_SCREEN_MESSAGE = Boolean.parseBoolean(config.getProperty("SHOW_SCREEN_MESSAGE", "false"));
        SHOW_SCREEN_MESSAGE_TIME = Integer.parseInt(config.getProperty("SHOW_SCREEN_MESSAGE_TIME", "5000"));
    }

    public static ExProperties initProperties(String filename) {
        ExProperties result = new ExProperties();
        try {
            result.load(new File(filename));
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return result;
    }

    public void onAttack(Creature attacker, Creature target) {
        if (attacker instanceof Player && target instanceof RaidBossInstance) {
            RaidBossInstance raidBoss = (RaidBossInstance)target;
            if (raidBoss.getLevel() < MIN_RB_LEVEL) {
                return;
            }
            double currentHp = raidBoss.getCurrentHp();
            double maxHp = raidBoss.getMaxHp();
            int hpPercentage = (int)(currentHp / maxHp * 100.0);
            if (!this.isBeingAttacked.containsKey(target.getObjectId())) {
                this.isBeingAttacked.put(target.getObjectId(), true);
                String message = "Raid Boss: " + raidBoss.getName() + " has been attacked!";
                this.announceToAll(message, raidBoss);
            }
            Map notifiedPercentages = this.rbPercentages.computeIfAbsent(raidBoss.getObjectId(), k -> new HashMap());
            String message = null;
            if (hpPercentage <= 50 && !notifiedPercentages.containsKey(50)) {
                notifiedPercentages.put(50, true);
                message = "Attention! Raid Boss: " + raidBoss.getName() + " has dropped to 50% HP!";
            } else if (hpPercentage <= 25 && !notifiedPercentages.containsKey(25)) {
                notifiedPercentages.put(25, true);
                message = "Attention! Raid Boss: " + raidBoss.getName() + " has dropped to 25% HP!";
            } else if (hpPercentage <= 10 && !notifiedPercentages.containsKey(10)) {
                notifiedPercentages.put(10, true);
                message = "Attention! Raid Boss: " + raidBoss.getName() + " has dropped to 10% HP!";
            }
            if (message != null) {
                this.announceToAll(message, raidBoss);
            }
        }
    }

    public void onDeath(Creature actor, Creature killer) {
        if (actor instanceof RaidBossInstance) {
            RaidBossInstance raidBoss = (RaidBossInstance)actor;
            if (raidBoss.getLevel() < MIN_RB_LEVEL) {
                return;
            }
            String message = "Raid Boss: " + raidBoss.getName() + " has been defeated!";
            this.announceToAll(message, raidBoss);
            this.rbPercentages.remove(raidBoss.getObjectId());
            this.isBeingAttacked.remove(raidBoss.getObjectId());
        }
    }

    private void announceToAll(String message, RaidBossInstance raidBoss) {
        Say2 announcement = new Say2(0, ChatType.ANNOUNCEMENT, "", message);
        ExShowScreenMessage screenMessage = ScreenMessage.createScreenMessage(message, SHOW_SCREEN_MESSAGE_TIME);
        if (DISTANCE_TO_ANNOUNCE == -1) {
            Announcements.getInstance().announceToAll((IStaticPacket)announcement);
            if (SHOW_SCREEN_MESSAGE) {
                Announcements.getInstance().announceToAll((IStaticPacket)screenMessage);
            }
            return;
        }
        raidBoss.getAroundCharacters(DISTANCE_TO_ANNOUNCE, 200).forEach(arg_0 -> BossAnnouncement.lambda$announceToAll$1(announcement, (IStaticPacket)screenMessage, arg_0));
    }

    public void onLoad() {
        BossAnnouncement.loadConfigs();
        if (ENABLE_RB_ANNOUNCEMENTS) {
            CharListenerList.addGlobal((Listener)INSTANCE);
            _log.info("[Giran Forge]=> Boss Announcement: Loaded.");
        }
    }

    public void onReload() {
        this.onShutdown();
        this.onLoad();
    }

    public void onShutdown() {
        CharListenerList.removeGlobal((Listener)INSTANCE);
    }

    private static /* synthetic */ void lambda$announceToAll$1(Say2 announcement, IStaticPacket screenMessage, Creature player) {
        player.sendPacket((IStaticPacket)announcement);
        if (SHOW_SCREEN_MESSAGE) {
            player.sendPacket(screenMessage);
        }
    }
}

