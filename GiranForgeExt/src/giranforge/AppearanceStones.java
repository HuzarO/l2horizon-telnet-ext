/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  handler.items.ScriptItemHandler
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import giranforge.config.SkinConfig;
import handler.items.ScriptItemHandler;
import l2.gameserver.data.xml.holder.AppearanceHolder;
import l2.gameserver.data.xml.parser.AppearanceParse;
import l2.gameserver.entity.AppearanceEntity;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.item.AppearanceTargetType;
import l2.gameserver.model.item.AppearanceType;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.appearance.AppearancePacket;
import l2.gameserver.request.imp.AppearanceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppearanceStones
extends ScriptItemHandler {
    public static final Logger _log = LoggerFactory.getLogger(AppearanceStones.class);
    protected static AppearanceStones instance = new AppearanceStones();
    protected int[] stones;

    public AppearanceStones() {
        AppearanceParse.getInstance().load();
        this.stones = AppearanceHolder.getInstance().getStones();
    }

    public boolean useItem(Playable playable, ItemInstance itemInstance, boolean b) {
        int itemId = itemInstance.getItemId();
        Player player = playable.getPlayer();
        if (!SkinConfig.ENABLE_SYSTEM) {
            player.sendRedMessage("This system is not active");
            return false;
        }
        AppearanceEntity entity = AppearanceHolder.getInstance().getById(itemId);
        if (entity == null) {
            return false;
        }
        AppearanceTargetType targetType = entity.getTargetType();
        AppearanceType type = entity.getAppearanceType();
        player.addSpecialRequest(new AppearanceRequest(player, itemId));
        player.sendPacket((IStaticPacket)new AppearancePacket(itemId, targetType, type));
        return true;
    }

    public int[] getItemIds() {
        return this.stones;
    }
}

