/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.model.items.PcInventory
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.templates.item.support.Grade
 */
package l2.gameserver.network.l2.c2s.appearance;

import giranforge.config.SkinConfig;
import l2.gameserver.data.xml.holder.AppearanceHolder;
import l2.gameserver.data.xml.holder.SkinsHolder;
import l2.gameserver.entity.AppearanceEntity;
import l2.gameserver.exceptions.UserException;
import l2.gameserver.model.Player;
import l2.gameserver.model.item.AppearanceType;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.appearance.ExAppearanceExtraction;
import l2.gameserver.request.imp.AppearanceRequest;
import l2.gameserver.request.validate.AppearanceStone;
import l2.gameserver.templates.item.support.Grade;

public class RequestAppearanceExtraction
extends L2GameClientPacket {
    private int _targetItemObjId;
    private int _extractItemObjId;

    protected void readImpl() throws Exception {
        this._targetItemObjId = this.readD();
        this._extractItemObjId = this.readD();
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
                throw new UserException("The request could not be validated");
            }
            if (this._targetItemObjId == this._extractItemObjId) {
                throw new UserException();
            }
            int stoneId = request.getStoneId();
            AppearanceEntity entity = AppearanceHolder.getInstance().getById(stoneId);
            if (entity == null) {
                throw new UserException("Modification route not found");
            }
            PcInventory pcInventory = player.getInventory();
            ItemInstance target = pcInventory.getItemByObjectId(this._targetItemObjId);
            ItemInstance extract = pcInventory.getItemByObjectId(this._extractItemObjId);
            if (target == null) {
                throw new UserException("You don't have the necessary item");
            }
            if (extract == null) {
                throw new UserException("You don't have the appearance item");
            }
            if (SkinConfig.RESTRICT_TO_REGISTERED_COSMETICS && SkinsHolder.getInstance().getById(extract.getItemId()) == null) {
                throw new UserException("This item cannot be used as a skin");
            }
            if (SkinConfig.checkIsBlocked(extract.getItemId()) && entity.getAppearanceType() != AppearanceType.RESTORE) {
                throw new UserException("This item is not permitted in the skins system");
            }
            if (!SkinConfig.ACCEPT_NO_GRADE_ITEMS && extract.getTemplate().getItemGrade() == Grade.NONE) {
                throw new UserException("Non-grade items cannot be used");
            }
            if (!SkinConfig.ACCEPT_ENCHANTED_SKIN && extract.getEnchantLevel() != 0) {
                throw new UserException("You cannot use enchanted items as skins");
            }
            if (target.getItemId() == extract.getItemId()) {
                throw new UserException();
            }
            if (!AppearanceStone.checkConditions(player, extract, entity)) {
                throw new UserException();
            }
            if (target.getTemplate().getBodyPart() != extract.getTemplate().getBodyPart()) {
                throw new UserException();
            }
            if (target.getTemplate().getItemClass() != extract.getTemplate().getItemClass()) {
                throw new UserException();
            }
            if (extract.isWeapon() && extract.isHeroWeapon()) {
                throw new UserException("You cannot use hero weapons as skins");
            }
            if (target.getTemplate().getItemType() != extract.getTemplate().getItemType() && (!SkinConfig.ACCEPT_DIFFERENT_TYPES || extract.isWeapon())) {
                throw new UserException();
            }
            request.setTargetObjId(this._targetItemObjId);
            request.setExtractionObjId(this._extractItemObjId);
            player.sendPacket((IStaticPacket)ExAppearanceExtraction.SUCCESS);
        }
        catch (UserException userException) {
            if (userException.getMessage() != null) {
                player.sendRedMessage(userException.getMessage());
            }
            player.sendPacket((IStaticPacket)ExAppearanceExtraction.FAIL);
        }
        catch (Exception e) {
            player.sendPacket((IStaticPacket)ExAppearanceExtraction.FAIL);
        }
    }
}

