/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.util.Rnd
 *  l2.gameserver.data.xml.holder.ItemHolder
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.model.items.PcInventory
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.s2c.ExUpgradeSystemResult
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.templates.item.ItemTemplate
 *  l2.gameserver.utils.ItemFunctions
 *  l2.gameserver.utils.Log
 *  l2.gameserver.utils.Log$ItemLog
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.c2s.upgrade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.util.Rnd;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.data.xml.holder.NormalUpgradeHolder;
import l2.gameserver.exceptions.UserException;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.ExUpgradeSystemResult;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.network.l2.s2c.upgrade.ExUpgradeSystemNormalResult;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeData;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeEntity;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeMaterial;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeResult;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Log;

public class RequestNormalUpgradeSystem
        extends L2GameClientPacket {
    private static final Logger _log = LoggerFactory.getLogger(RequestNormalUpgradeSystem.class);
    protected int _objectId;
    protected int _type;
    protected int _upgradeId;

    protected void readImpl() throws Exception {
        this._objectId = this.readD();
        this._type = this.readD();
        this._upgradeId = this.readD();
    }

    protected void runImpl() throws Exception {
        Player player = ((GameClient) this._client).getActiveChar();
        if (player == null) {
            return;
        }
        try {
            if (player.isDead() || player.isOlyParticipant() || player.isFlying() || player.isMounted()) {
                throw new UserException("You cannot use this service right now");
            }
            PcInventory inventory = player.getInventory();
            NormalUpgradeEntity entity = NormalUpgradeHolder.getInstance().getById(this._upgradeId);
            if (entity == null) {
                throw new UserException("Upgrade route not found");
            }
            if (entity.getChance() != 100 && entity.getFailData().isEmpty()) {
                throw new UserException("XML file has no failure reward");
            }
            ItemInstance requiredItem = inventory.getItemByObjectId(this._objectId);
            if (requiredItem == null) {
                throw new UserException("You do not have the required item");
            }
            if (requiredItem.getItemId() != entity.getRequired()) {
                throw new UserException("Invalid upgrade data");
            }
            if (entity.getRequiredEnchant() != 0 && requiredItem.getEnchantLevel() != entity.getRequiredEnchant()) {
                throw new UserException("Incorrect enchant level");
            }
            long adena = inventory.getAdena();
            if (adena < entity.getCommission()) {
                throw new UserException("You do not have enough Adena");
            }
            for (NormalUpgradeMaterial material : entity.getMaterials()) {
                if (ItemFunctions.getItemCount((Playable) player, (int) material.id()) >= material.quantity())
                    continue;
                throw new UserException("You do not have the required materials");
            }
            player.reduceAdena(entity.getCommission(), true);
            player.getInventory().destroyItemByObjectId(requiredItem.getObjectId(), 1L);
            player.sendPacket((IStaticPacket) SystemMessage.removeItems((int) requiredItem.getItemId(), (long) 1L));
            for (NormalUpgradeMaterial upgradeMaterial : entity.getMaterials()) {
                ItemFunctions.removeItem((Playable) player, (int) upgradeMaterial.id(),
                        (long) upgradeMaterial.quantity(), (boolean) true);
            }
            List<NormalUpgradeData> dataItems;
            if (entity.getChance() == 100 || Rnd.chance((int) entity.getChance())) {
                dataItems = entity.getSuccessData();
                this.createResultItems(player, entity, dataItems, true);
            } else {
                dataItems = entity.getFailData();
                this.createResultItems(player, entity, dataItems, false);
            }
        } catch (UserException UserException2) {
            player.sendRedMessage(UserException2.getMessage());
            player.sendPacket((IStaticPacket) new ExUpgradeSystemResult(0, 0));
        } catch (Exception exception) {
            _log.error(exception.getMessage());
        }
    }

    private List<NormalUpgradeResult> sendItem(Player player, NormalUpgradeData data) {
        ArrayList<NormalUpgradeResult> resultList = new ArrayList<NormalUpgradeResult>();
        ItemTemplate template = ItemHolder.getInstance().getTemplate(data.id());
        if (template == null) {
            return Collections.emptyList();
        }
        if (template.isStackable()) {
            List<ItemInstance> addedItems = ItemFunctions.addItem((Playable) player, (ItemTemplate) template,
                    (long) data.quantity(), (boolean) true);
            for (ItemInstance added : addedItems) {
                Log.LogItem((Player) player, (Log.ItemLog) Log.ItemLog.UpgradeEquipment, (ItemInstance) added);
                resultList
                        .add(new NormalUpgradeResult(added.getObjectId(), added.getItemId(), 0, (int) data.quantity()));
            }
        } else {
            int i = 0;
            while ((long) i < data.quantity()) {
                ItemInstance newItem = ItemFunctions.createItem((int) data.id());
                if (data.enchant() > 0) {
                    newItem.setEnchantLevel(data.enchant());
                }
                player.getInventory().addItem(newItem);
                player.sendPacket((IStaticPacket) SystemMessage.obtainItems((ItemInstance) newItem));
                Log.LogItem((Player) player, (Log.ItemLog) Log.ItemLog.UpgradeEquipment, (ItemInstance) newItem);
                resultList.add(new NormalUpgradeResult(newItem.getObjectId(), data.id(), data.enchant(), 1));
                ++i;
            }
        }
        return resultList;
    }

    private void createResultItems(Player player, NormalUpgradeEntity entity, List<NormalUpgradeData> dataItems,
            boolean success) {
        ArrayList<NormalUpgradeResult> resultList = new ArrayList<NormalUpgradeResult>();
        for (NormalUpgradeData data : dataItems) {
            List<NormalUpgradeResult> process = this.sendItem(player, data);
            resultList.addAll(process);
        }
        if (success && entity.hasBonus() && Rnd.chance((int) entity.getBonus())) {
            for (NormalUpgradeData bonusItem : entity.getBonusReward()) {
                List<NormalUpgradeResult> bonusProcess = this.sendItem(player, bonusItem);
                resultList.addAll(bonusProcess);
            }
        }
        player.sendPacket((IStaticPacket) new ExUpgradeSystemNormalResult(success, entity.getId(), resultList));
    }
}
