package l2.gameserver.network.l2.c2s;

import l2.gameserver.Config;
import l2.gameserver.handler.bbs.CommunityBoardManager;
import l2.gameserver.handler.bbs.ICommunityBoardHandler;
import l2.gameserver.handler.bypass.BypassHandler;
import l2.gameserver.handler.bypass.IBypassHandler;
import l2.gameserver.instancemanager.BypassManager;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles bypass commands sent from the client to the server.
 * This packet is reconstructed from bytecode to match the exact JVM execution logic.
 */
public class RequestBypassToServer extends L2GameClientPacket {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBypassToServer.class);
    
    private BypassManager.DecodedBypass decodedBypass = null;
    private String _bypass;

    protected void readImpl() {
        this._bypass = this.readS();
    }

    protected void runImpl() {
        GameClient client = (GameClient) this.getClient();
        Player player = client.getActiveChar();
        
        if (player == null) {
            return;
        }
        
        try {
            // Bytecode lines 18-51: Validate and decode bypass
            if (this._bypass == null || this._bypass.isEmpty()) {
                return;
            }
            
            this.decodedBypass = client.decodeBypass(this._bypass);
            if (this.decodedBypass == null) {
                return;
            }
            
            // Bytecode lines 52-85: Resolve NPC instance
            NpcInstance npc = player.getLastNpc();
            GameObject target = player.getTarget();
            
            if (npc == null && target != null && target.isNpc()) {
                npc = (NpcInstance) target;
            }
            
            // Bytecode lines 86-172: Handle via BypassHandler system
            BypassHandler.BypassResult bypassResult = BypassHandler.getInstance().getBypass(this.decodedBypass.bypass);
            
            if (bypassResult != null) {
                IBypassHandler handler = bypassResult.handler;
                
                // Bytecode lines 113-127: Check if handler requires NPC
                if (handler.requiresNpc()) {
                    if (npc == null) {
                        return;
                    }
                }
                
                // Bytecode lines 128-150: Check if handler requires NPC validation
                if (handler.requiresNpcCheck()) {
                    if (npc == null || !NpcInstance.canBypassCheck(player, npc)) {
                        return;
                    }
                }
                
                // Bytecode line 167: Execute the bypass handler
                handler.handle(player, npc, this.decodedBypass.bypass, bypassResult.params);
                return;
            }
            
            // Bytecode lines 173-263: Handle Community Board (BBS) bypasses
            if (this.decodedBypass.bbs) {
                if (!Config.COMMUNITYBOARD_ENABLED) {
                    player.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.THE_COMMUNITY_SERVER_IS_CURRENTLY_OFFLINE));
                    return;
                }
                
                if (player.isGM()) {
                    Functions.sendDebugMessage(player, "BBS Bypass: " + this.decodedBypass.bypass);
                }
                
                ICommunityBoardHandler handler = CommunityBoardManager.getInstance().getCommunityHandler(this.decodedBypass.bypass, player);
                
                if (handler != null) {
                    handler.onBypassCommand(player, this.decodedBypass.bypass);
                }
                return;
            }
            
            // Bytecode lines 264-284: Log unknown bypass warning
            LOGGER.warn("Unknown bypass: {}", this.decodedBypass.bypass);
            
        } catch (Exception e) {
            // Bytecode lines 287-352: Exception handling
            String errorMessage = "Bad RequestBypassToServer: " + this.decodedBypass.bypass;
            
            GameObject target = player != null ? player.getTarget() : null;
            
            if (target != null && target.isNpc()) {
                errorMessage = errorMessage + " via NPC #" + ((NpcInstance) target).getNpcId();
            }
            
            LOGGER.error(errorMessage, e);
        }
    }
}
