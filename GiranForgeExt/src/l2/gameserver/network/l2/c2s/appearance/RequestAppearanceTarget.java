/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.templates.item.support.Grade
 */
package l2.gameserver.network.l2.c2s.appearance;

import giranforge.config.SkinConfig;
import l2.gameserver.data.xml.holder.AppearanceHolder;
import l2.gameserver.entity.AppearanceEntity;
import l2.gameserver.exceptions.UserException;
import l2.gameserver.model.Player;
import l2.gameserver.model.item.AppearanceType;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.appearance.ExAppearanceTarget;
import l2.gameserver.request.imp.AppearanceRequest;
import l2.gameserver.request.validate.AppearanceStone;
import l2.gameserver.templates.item.support.Grade;

public class RequestAppearanceTarget
extends L2GameClientPacket {
    protected int objectId;

    protected void readImpl() throws Exception {
        this.objectId = this.readD();
    }

    protected void runImpl() throws Exception {
        Player player = ((GameClient)this._client).getActiveChar();
        if (player == null) {
            return;
        }
        try {
            if (!SkinConfig.ENABLE_SYSTEM) {
                throw new UserException("This system is not active");
            }
            AppearanceRequest request = player.getSpecialRequest(AppearanceRequest.class);
            if (request == null) {
                return;
            }
            int stoneId = request.getStoneId();
            AppearanceEntity entity = AppearanceHolder.getInstance().getById(stoneId);
            if (entity == null) {
                throw new UserException("Modification route not found");
            }
            ItemInstance itemInstance = player.getInventory().getItemByObjectId(this.objectId);
            if (itemInstance == null) {
                throw new UserException("You don't have the necessary item");
            }
            if (SkinConfig.checkIsBlocked(itemInstance.getItemId()) && entity.getAppearanceType() != AppearanceType.RESTORE) {
                throw new UserException("This item is not permitted in the skins system");
            }
            if (!SkinConfig.ACCEPT_NO_GRADE_ITEMS && itemInstance.getTemplate().getItemGrade() == Grade.NONE) {
                throw new UserException("Non-grade items cannot be used");
            }
            if (itemInstance.getVisibleItemId() != itemInstance.getItemId() && entity.getAppearanceType() != AppearanceType.RESTORE) {
                throw new UserException("The selected item already has a skin applied");
            }
            if (entity.getAppearanceType() == AppearanceType.RESTORE && itemInstance.getVisibleItemId() == itemInstance.getItemId()) {
                throw new UserException("The selected item does not have a skin applied");
            }
            if (!AppearanceStone.checkConditions(player, itemInstance, entity)) {
                throw new UserException();
            }
            long commission = entity.getCommission();
            request.setCommission(commission);
            player.sendPacket((IStaticPacket)new ExAppearanceTarget(1, commission));
        }
        catch (UserException userException) {
            if (userException.getMessage() != null) {
                player.sendRedMessage(userException.getMessage());
            }
            player.sendPacket((IStaticPacket)ExAppearanceTarget.FAIL);
        }
        catch (Exception ignored) {
            player.sendPacket((IStaticPacket)ExAppearanceTarget.FAIL);
        }
    }
}

