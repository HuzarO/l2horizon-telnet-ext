/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Config
 *  l2.gameserver.dao.CharacterVariablesDAO
 *  l2.gameserver.model.World
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.GameClient$GameClientState
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.CustomMessage
 *  l2.gameserver.network.l2.s2c.ActionFail
 *  l2.gameserver.network.l2.s2c.CharSelected
 *  l2.gameserver.network.l2.s2c.Ex2ndPasswordCheck
 *  l2.gameserver.network.l2.s2c.Ex2ndPasswordCheck$Ex2ndPasswordCheckResult
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 *  l2.gameserver.utils.AutoBan
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.c2s;

import Config.GiranForgeConfig;
import java.time.LocalDateTime;
import l2.gameserver.Config;
import l2.gameserver.dao.CharacterVariablesDAO;
import l2.gameserver.model.Player;
import l2.gameserver.model.World;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.s2c.ActionFail;
import l2.gameserver.network.l2.s2c.CharSelected;
import l2.gameserver.network.l2.s2c.Ex2ndPasswordCheck;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.utils.AutoBan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterSelected
extends L2GameClientPacket {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterSelected.class);
    private int selectedSlot;

    protected void readImpl() {
        this.selectedSlot = this.readD();
    }

    protected void runImpl() {
        GameClient client = (GameClient)this.getClient();
        if (client.getActiveChar() == null) {
            int objectId = client.getObjectIdForSlot(this.selectedSlot);
            if (AutoBan.isBanned((int)objectId)) {
                this.sendPacket(ActionFail.STATIC);
                return;
            }
            String hwidLock = CharacterVariablesDAO.getInstance().getVar(objectId, "hwidlock@");
            if (!(hwidLock == null || hwidLock.isEmpty() || client.getHwid() == null || client.getHwid().isEmpty() || hwidLock.equalsIgnoreCase(client.getHwid()))) {
                this.sendPacket((L2GameServerPacket)new ExShowScreenMessage("HWID is locked.", 10000, ExShowScreenMessage.ScreenMessageAlign.TOP_CENTER, true));
                this.sendPacket(ActionFail.STATIC);
                return;
            }
            String ipLock = CharacterVariablesDAO.getInstance().getVar(objectId, "iplock@");
            if (!(ipLock == null || ipLock.isEmpty() || client.getIpAddr() == null || client.getIpAddr().isEmpty() || ipLock.equalsIgnoreCase(client.getIpAddr()))) {
                this.sendPacket((L2GameServerPacket)new ExShowScreenMessage("IP address is locked.", 10000, ExShowScreenMessage.ScreenMessageAlign.TOP_CENTER, true));
                this.sendPacket(ActionFail.STATIC);
                return;
            }
            if (Config.USE_SECOND_PASSWORD_AUTH && !client.isSecondPasswordAuthed()) {
                if (client.getSecondPasswordAuth().isSecondPasswordSet()) {
                    client.sendPacket((L2GameServerPacket)new Ex2ndPasswordCheck(Ex2ndPasswordCheck.Ex2ndPasswordCheckResult.CHECK));
                } else {
                    client.sendPacket((L2GameServerPacket)new Ex2ndPasswordCheck(Ex2ndPasswordCheck.Ex2ndPasswordCheckResult.CREATE));
                }
                return;
            }
            LocalDateTime now = LocalDateTime.now();
            if (Config.OPEN_SERVER_TIME != null && now.isBefore(Config.OPEN_SERVER_TIME) && !Config.OPEN_SERVER_ALLOWED_CHARS.contains(objectId)) {
                CustomMessage openMessage = new CustomMessage("service_server_opened", null, new Object[0]).addString(String.valueOf(Config.OPEN_SERVER_TIME));
                this.sendPacket((L2GameServerPacket)new ExShowScreenMessage(openMessage == null ? "Missing data/strings service_server_opened" : String.valueOf(openMessage), 20000, ExShowScreenMessage.ScreenMessageAlign.TOP_CENTER, true));
                this.sendPacket(ActionFail.STATIC);
                return;
            }
            Player existing = World.getPlayer((int)objectId);
            if (existing != null && (existing.isInOfflineHunting() || existing.isInOfflineMode())) {
                existing.setInOfflineHunting(false);
                existing.setOfflineMode(false);
                existing.kick();
            }
            this.cleanupOfflineFarmRecord(objectId);
            Player player = client.loadCharFromDisk(this.selectedSlot);
            if (player == null) {
                this.sendPacket(ActionFail.STATIC);
                return;
            }
            if (player.getAccessLevel() < 0) {
                player.setAccessLevel(0);
            }
            client.setState(GameClient.GameClientState.IN_GAME);
            client.sendPacket((L2GameServerPacket)new CharSelected(player, client.getSessionKey().playOkID1));
        }
    }

    private void cleanupOfflineFarmRecord(int objectId) {
        block5: {
            try {
                String offlineFarmData = CharacterVariablesDAO.getInstance().getVar(objectId, "offlineFarm");
                if (offlineFarmData == null || offlineFarmData.isEmpty()) break block5;
                try {
                    CharacterVariablesDAO.getInstance().deleteVar(objectId, "offlineFarm");
                    if (GiranForgeConfig.DEBUG_MODE) {
                        LOGGER.info("Cleaned up offline farm record for character ID: {}", (Object)objectId);
                    }
                }
                catch (Exception e) {
                    LOGGER.warn("Failed to remove offline farm record for character ID: {}", (Object)objectId);
                }
            }
            catch (Exception e) {
                LOGGER.error("Error cleaning up offline farm record for character ID: {}", (Object)objectId, (Object)e);
            }
        }
    }
}

