/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.data.xml.holder.ItemHolder
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.model.items.PcInventory
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.s2c.InventoryUpdate
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.templates.item.ItemTemplate
 *  l2.gameserver.templates.item.support.Grade
 *  l2.gameserver.utils.ItemFunctions
 */
package l2.gameserver.network.l2.c2s.appearance;

import giranforge.config.SkinConfig;
import l2.gameserver.data.xml.holder.AppearanceHolder;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.data.xml.holder.SkinsHolder;
import l2.gameserver.entity.AppearanceEntity;
import l2.gameserver.exceptions.UserException;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.item.AppearanceType;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.InventoryUpdate;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.network.l2.s2c.appearance.ExAppearanceResult;
import l2.gameserver.request.imp.AppearanceRequest;
import l2.gameserver.request.validate.AppearanceStone;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.templates.item.support.Grade;
import l2.gameserver.utils.ItemFunctions;

public class RequestAppearanceModify
extends L2GameClientPacket {
    private int _targetItemObjId;

    protected void readImpl() throws Exception {
        this._targetItemObjId = this.readD();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
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
                throw new UserException("You don't have an active request!");
            }
            int target = request.getTargetObjId();
            int extract = request.getExtractionObjId();
            int stoneId = request.getStoneId();
            long commission = request.getCommission();
            AppearanceEntity entity = AppearanceHolder.getInstance().getById(stoneId);
            if (entity == null) {
                throw new UserException("Modification route not found");
            }
            PcInventory pcInventory = player.getInventory();
            ItemInstance stoneItem = pcInventory.getItemByItemId(stoneId);
            if (stoneItem == null) {
                throw new UserException("You don't have a trading stone");
            }
            if (commission > 0L && player.getAdena() < commission) {
                throw new UserException("You don't have enough adena");
            }
            AppearanceType appearanceType = entity.getAppearanceType();
            switch (appearanceType) {
                case RESTORE: {
                    ItemInstance itemInstance = pcInventory.getItemByObjectId(this._targetItemObjId);
                    if (itemInstance == null) {
                        throw new UserException("You don't have the necessary item");
                    }
                    if (itemInstance.getVisibleItemId() == itemInstance.getItemId()) {
                        throw new UserException("The selected item is unchanged");
                    }
                    int skinId = itemInstance.getVisibleItemId();
                    ItemTemplate template = ItemHolder.getInstance().getTemplate(skinId);
                    if (template == null) {
                        throw new UserException("The return item no longer exists");
                    }
                    itemInstance.setVisibleItemId(0);
                    if (entity.isRefund()) {
                        ItemInstance returnItem = ItemFunctions.createItem((int)skinId);
                        pcInventory.addItem(returnItem);
                        player.sendPacket((IStaticPacket)SystemMessage.obtainItems((ItemInstance)returnItem));
                    }
                    this.updateInventory(player, itemInstance);
                    ItemFunctions.removeItem((Playable)player, (int)stoneId, (long)1L, (boolean)true);
                    if (commission > 0L) {
                        player.reduceAdena(commission, true);
                    }
                    player.sendPacket((IStaticPacket)new ExAppearanceResult(1, itemInstance.getItemId(), stoneId));
                    return;
                }
                case NORMAL: 
                case BLESSED: {
                    ItemInstance targetItem = player.getInventory().getItemByObjectId(target);
                    ItemInstance extractItem = player.getInventory().getItemByObjectId(extract);
                    if (targetItem == null) throw new UserException("Error retrieving transaction data");
                    if (extractItem == null) {
                        throw new UserException("Error retrieving transaction data");
                    }
                    if (target != this._targetItemObjId) {
                        throw new UserException("Invalid requisition data");
                    }
                    if (SkinConfig.checkIsBlocked(targetItem.getItemId()) && entity.getAppearanceType() != AppearanceType.RESTORE) {
                        throw new UserException("This item is not permitted in the skins system");
                    }
                    if (SkinConfig.RESTRICT_TO_REGISTERED_COSMETICS && SkinsHolder.getInstance().getById(extractItem.getItemId()) == null) {
                        throw new UserException("This item cannot be used as a skin");
                    }
                    if (!SkinConfig.ACCEPT_NO_GRADE_ITEMS && targetItem.getTemplate().getItemGrade() == Grade.NONE) {
                        throw new UserException("Non-grade items cannot be used");
                    }
                    if (SkinConfig.checkIsBlocked(extractItem.getItemId()) && entity.getAppearanceType() != AppearanceType.RESTORE) {
                        throw new UserException("This item is not permitted in the skins system");
                    }
                    if (!SkinConfig.ACCEPT_NO_GRADE_ITEMS && extractItem.getTemplate().getItemGrade() == Grade.NONE) {
                        throw new UserException("Non-grade items cannot be used");
                    }
                    if (!SkinConfig.ACCEPT_ENCHANTED_SKIN && extractItem.getEnchantLevel() != 0) {
                        throw new UserException("You cannot use enchanted items as skins");
                    }
                    if (!AppearanceStone.checkConditions(player, extractItem, entity)) {
                        throw new UserException();
                    }
                    if (!AppearanceStone.checkConditions(player, targetItem, entity)) {
                        throw new UserException();
                    }
                    ItemFunctions.removeItem((Playable)player, (int)stoneId, (long)1L, (boolean)true);
                    if (commission > 0L) {
                        player.reduceAdena(commission, true);
                    }
                    ItemInstance skinApply = pcInventory.removeItemByObjectId(extract, 1L);
                    skinApply.delete();
                    player.sendPacket((IStaticPacket)SystemMessage.removeItems((int)extractItem.getItemId(), (long)1L));
                    targetItem.setVisibleItemId(extractItem.getItemId());
                    this.updateInventory(player, targetItem);
                    player.sendPacket((IStaticPacket)new ExAppearanceResult(1, targetItem.getItemId(), extractItem.getItemId()));
                    return;
                }
            }
            return;
        }
        catch (UserException userException) {
            player.sendRedMessage(userException.getMessage());
            player.sendPacket((IStaticPacket)ExAppearanceResult.FAIL);
            return;
        }
        catch (RuntimeException e) {
            player.sendPacket((IStaticPacket)ExAppearanceResult.FAIL);
            throw new RuntimeException(e);
        }
        finally {
            player.removeSpecialRequest(AppearanceRequest.class);
        }
    }

    protected void updateInventory(Player player, ItemInstance targetItem) {
        boolean isEquipped = targetItem.isEquipped();
        if (isEquipped) {
            player.getInventory().unEquipItem(targetItem);
            player.getInventory().equipItem(targetItem);
        }
        player.sendPacket((IStaticPacket)new InventoryUpdate().addModifiedItem(targetItem));
    }
}

