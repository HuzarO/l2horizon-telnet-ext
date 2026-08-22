/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.util.Rnd
 *  l2.gameserver.Config
 *  l2.gameserver.data.xml.holder.EnchantItemHolder
 *  l2.gameserver.data.xml.holder.OneDayRewardHolder
 *  l2.gameserver.model.GameObjectsStorage
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.entity.oneDayReward.requirement.EnchantItemRequirement
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.model.items.PcInventory
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.EnchantResult
 *  l2.gameserver.network.l2.s2c.InventoryUpdate
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 *  l2.gameserver.network.l2.s2c.MagicSkillUse
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.templates.item.support.EnchantCatalyzer
 *  l2.gameserver.templates.item.support.EnchantScroll
 *  l2.gameserver.templates.item.support.EnchantScrollOnFailAction
 *  l2.gameserver.utils.ItemFunctions
 *  l2.gameserver.utils.Log
 *  l2.gameserver.utils.Log$ItemLog
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.c2s;

import Config.GiranForgeConfig;
import helpers.ScreenMessage;
import java.util.List;
import l2.commons.util.Rnd;
import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.EnchantItemHolder;
import l2.gameserver.data.xml.holder.OneDayRewardHolder;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.oneDayReward.requirement.EnchantItemRequirement;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.EnchantResult;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;
import l2.gameserver.network.l2.s2c.InventoryUpdate;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.network.l2.s2c.MagicSkillUse;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.templates.item.support.EnchantCatalyzer;
import l2.gameserver.templates.item.support.EnchantScroll;
import l2.gameserver.templates.item.support.EnchantScrollOnFailAction;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GFRequestEnchantItem
extends L2GameClientPacket {
    private static final Logger _log = LoggerFactory.getLogger(GFRequestEnchantItem.class);
    private int enchantItemObjectId;
    private int catalyzerItemObjectId;

    protected void readImpl() {
        this.enchantItemObjectId = this.readD();
        this.catalyzerItemObjectId = this.readD();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void runImpl() {
        Player player;
        GameClient gameClient = (GameClient)this.getClient();
        if (gameClient != null && (player = gameClient.getActiveChar()) != null) {
            if (player.isActionsDisabled()) {
                player.setEnchantScroll(null);
                player.sendActionFailed();
            } else if (player.isInTrade()) {
                player.setEnchantScroll(null);
                player.sendActionFailed();
            } else if (player.isInStoreMode()) {
                player.setEnchantScroll(null);
                player.sendPacket((IStaticPacket)EnchantResult.CANCEL);
                player.sendPacket((IStaticPacket)SystemMsg.YOU_CANNOT_ENCHANT_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
                player.sendActionFailed();
            } else {
                PcInventory inventory = player.getInventory();
                inventory.writeLock();
                try {
                    ItemInstance targetItem = inventory.getItemByObjectId(this.enchantItemObjectId);
                    ItemInstance catalyzerItem = this.catalyzerItemObjectId > 0 ? inventory.getItemByObjectId(this.catalyzerItemObjectId) : null;
                    ItemInstance enchantScroll = player.getEnchantScroll();
                    if (targetItem != null && enchantScroll != null) {
                        EnchantScroll scrollTemplate = EnchantItemHolder.getInstance().getEnchantScroll(enchantScroll.getItemId());
                        if (scrollTemplate == null) {
                            player.sendActionFailed();
                            return;
                        }
                        if (!targetItem.canBeEnchanted(false)) {
                            player.sendPacket((IStaticPacket)EnchantResult.CANCEL);
                            player.sendPacket((IStaticPacket)SystemMsg.INAPPROPRIATE_ENCHANT_CONDITIONS);
                            player.sendActionFailed();
                            return;
                        }
                        if (!scrollTemplate.isUsableWith(targetItem, (EnchantCatalyzer)null)) {
                            player.sendPacket((IStaticPacket)EnchantResult.CANCEL);
                            player.sendPacket((IStaticPacket)SystemMsg.DOES_NOT_FIT_STRENGTHENING_CONDITIONS_OF_THE_SCROLL);
                            player.sendActionFailed();
                            return;
                        }
                        EnchantCatalyzer catalyzerTemplate = catalyzerItem != null ? EnchantItemHolder.getInstance().getEnchantCatalyzer(catalyzerItem.getItemId()) : null;
                        double chanceMultiplier = 1.0 + scrollTemplate.getChanceMod();
                        int newEnchantLevel = targetItem.getEnchantLevel() + Rnd.get((int)scrollTemplate.getIncrement(), (int)scrollTemplate.getIncrementMax());
                        chanceMultiplier *= player.getEnchantBonusMul();
                        if (catalyzerTemplate != null) {
                            chanceMultiplier += catalyzerTemplate.getChanceMod();
                        }
                        if (!inventory.destroyItem(enchantScroll, 1L) || catalyzerItem != null && catalyzerTemplate != null && !inventory.destroyItem(catalyzerItem, 1L)) {
                            player.sendPacket((IStaticPacket)EnchantResult.CANCEL);
                            player.sendPacket((IStaticPacket)SystemMsg.INAPPROPRIATE_ENCHANT_CONDITIONS);
                            player.sendActionFailed();
                            return;
                        }
                        if (catalyzerTemplate != null && !catalyzerTemplate.isUsableWith(targetItem)) {
                            catalyzerTemplate = null;
                        }
                        if (!scrollTemplate.isUsableWith(targetItem, catalyzerTemplate)) {
                            player.sendPacket((IStaticPacket)EnchantResult.CANCEL);
                            player.sendPacket((IStaticPacket)SystemMsg.DOES_NOT_FIT_STRENGTHENING_CONDITIONS_OF_THE_SCROLL);
                            player.sendActionFailed();
                            return;
                        }
                        double enchantChance = scrollTemplate.getEnchantChance(targetItem);
                        if (!scrollTemplate.isInfallible() && !Rnd.chance((double)(enchantChance * chanceMultiplier))) {
                            EnchantScrollOnFailAction failAction = scrollTemplate.getOnFailAction();
                            if (catalyzerTemplate != null && failAction != EnchantScrollOnFailAction.NONE) {
                                failAction = catalyzerTemplate.getResultType();
                            }
                            switch (failAction) {
                                case CRYSTALIZE: {
                                    this.handleCrystallization(player, targetItem);
                                    return;
                                }
                                case RESET: {
                                    this.handleEnchantReset(player, targetItem, scrollTemplate.getFailResultLevel());
                                    return;
                                }
                                case NONE: {
                                    this.handleEnchantFailure(player, targetItem, scrollTemplate.isCloseEnchantWindowOnFail());
                                    return;
                                }
                            }
                            return;
                        }
                        if (scrollTemplate.getIncrement() > 1 && newEnchantLevel > scrollTemplate.getMaxLvl()) {
                            this.handleSuccessfulEnchant(player, targetItem, scrollTemplate.getMaxLvl());
                            return;
                        }
                        this.handleSuccessfulEnchant(player, targetItem, newEnchantLevel);
                        return;
                    }
                    player.sendActionFailed();
                }
                finally {
                    inventory.writeUnlock();
                    player.updateStats();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void handleSuccessfulEnchant(Player player, ItemInstance targetItem, int newEnchantLevel) {
        PcInventory inventory = player.getInventory();
        ItemInstance enchantScroll = player.getEnchantScroll();
        if (newEnchantLevel >= 65535) {
            player.sendPacket(new IStaticPacket[]{EnchantResult.CANCEL, SystemMsg.DOES_NOT_FIT_STRENGTHENING_CONDITIONS_OF_THE_SCROLL});
            player.sendActionFailed();
        } else {
            boolean isEquipped = targetItem.isEquipped();
            int equipSlot = targetItem.getEquipSlot();
            if (isEquipped) {
                targetItem.setEquipped(false);
                inventory.getListeners().onUnequip(equipSlot, targetItem);
            }
            try {
                targetItem.setEnchantLevel(newEnchantLevel);
                Log.LogItem((Player)player, (Log.ItemLog)Log.ItemLog.EnchantSuccess, (ItemInstance)targetItem, (long)1L, (long)targetItem.getReferencePrice(), (int)enchantScroll.getItemId());
            }
            finally {
                if (isEquipped) {
                    inventory.getListeners().onEquip(equipSlot, targetItem);
                    targetItem.setEquipped(true);
                }
                targetItem.save();
            }
            player.sendPacket((IStaticPacket)new InventoryUpdate().addModifiedItem(targetItem));
            player.sendPacket((IStaticPacket)new EnchantResult(targetItem.getEnchantLevel()));
            player.getListeners().onItemEnchantSuccessListener(targetItem.getItemId(), targetItem.getEnchantLevel());
            this.checkAndAnnounceEnchant(player, targetItem, newEnchantLevel);
            if (Config.SHOW_ENCHANT_EFFECT_RESULT) {
                GFRequestEnchantItem.showEnchantEffect(player, targetItem);
            }
            OneDayRewardHolder.getInstance().fireRequirements(player, (Creature)null, EnchantItemRequirement.class);
        }
    }

    private void checkAndAnnounceEnchant(Player player, ItemInstance targetItem, int enchantLevel) {
        block4: {
            List announceLevels = GiranForgeConfig.ANNOUNCE_AT_ENCHANTS.stream().toList();
            boolean shouldAnnounce = announceLevels.contains(enchantLevel);
            try {
                if (shouldAnnounce) {
                    String playerName = player.getName();
                    String itemName = targetItem.getName();
                    String itemClassId = String.valueOf(targetItem.getItemId());
                    int itemServerId = targetItem.getObjectId();
                    String additionalItemName = targetItem.getTemplate().getAdditionalName();
                    String message = "UserName=" + playerName.replaceAll(" ", "_") + " ItemName=" + itemName.replaceAll(" ", "_") + " EnchantCount=" + enchantLevel + " ItemClassID=" + itemClassId + " ItemServerID=" + itemServerId + " AdditionalItemName=" + additionalItemName + ";";
                    ExShowScreenMessage enchantBroadcastMsg = ScreenMessage.customEvent(37668, message);
                    for (Player gamePlayer : GameObjectsStorage.getAllPlayersForIterate()) {
                        if (gamePlayer == null || !gamePlayer.isOnline()) continue;
                        gamePlayer.sendPacket((IStaticPacket)enchantBroadcastMsg);
                    }
                }
            }
            catch (Exception e) {
                if (!GiranForgeConfig.DEBUG_MODE) break block4;
                _log.error("Error during enchant announcement: ", (Throwable)e);
            }
        }
    }

    private void handleCrystallization(Player player, ItemInstance targetItem) {
        PcInventory inventory = player.getInventory();
        boolean isEquipped = targetItem.isEquipped();
        int itemId = targetItem.getItemId();
        int enchantLevel = targetItem.getEnchantLevel();
        int crystalItemId = targetItem.getCrystalItemId();
        int crystalCount = targetItem.getTemplate().getCrystalCount();
        ItemInstance enchantScroll = player.getEnchantScroll();
        if (isEquipped) {
            player.sendDisarmMessage(targetItem);
            inventory.unEquipItem(targetItem);
        }
        Log.LogItem((Player)player, (Log.ItemLog)Log.ItemLog.EnchantCrystallize, (ItemInstance)targetItem, (long)1L, (long)targetItem.getReferencePrice(), (int)enchantScroll.getItemId());
        if (!inventory.destroyItem(targetItem, 1L)) {
            player.sendActionFailed();
        } else if (crystalItemId > 0 && crystalCount > 0) {
            int bonusCrystalCount = (int)((double)crystalCount * 0.87);
            if (enchantLevel > 3 && Config.CRYSTALLIZE_BONUS_AT_ENCHANT) {
                bonusCrystalCount = (int)((double)bonusCrystalCount + (double)crystalCount * 0.25 * (double)(enchantLevel - 3));
            }
            if (bonusCrystalCount < 1) {
                bonusCrystalCount = 1;
            }
            player.sendPacket(new IStaticPacket[]{new EnchantResult(1, crystalItemId, (long)bonusCrystalCount), ((SystemMessage)new SystemMessage(SystemMsg.THE_ENCHANTMENT_HAS_FAILED__YOUR_S1_S2_HAS_BEEN_CRYSTALLIZED).addNumber(enchantLevel)).addItemName(itemId)});
            ItemFunctions.addItem((Playable)player, (int)crystalItemId, (long)bonusCrystalCount, (boolean)true);
        } else {
            player.sendPacket(new IStaticPacket[]{EnchantResult.FAILED_NO_CRYSTALS, new SystemMessage(SystemMsg.THE_ENCHANTMENT_HAS_FAILED_YOUR_S1_HAS_BEEN_CRYSTALLIZED).addItemName(targetItem.getItemId())});
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void handleEnchantReset(Player player, ItemInstance targetItem, int resetLevel) {
        PcInventory inventory = player.getInventory();
        boolean isEquipped = targetItem.isEquipped();
        int equipSlot = targetItem.getEquipSlot();
        int newEnchantLevel = Math.min(targetItem.getEnchantLevel(), resetLevel);
        ItemInstance enchantScroll = player.getEnchantScroll();
        if (isEquipped) {
            targetItem.setEquipped(false);
            inventory.getListeners().onUnequip(equipSlot, targetItem);
        }
        try {
            targetItem.setEnchantLevel(newEnchantLevel);
            Log.LogItem((Player)player, (Log.ItemLog)Log.ItemLog.EnchantReset, (ItemInstance)targetItem, (long)1L, (long)targetItem.getReferencePrice(), (int)enchantScroll.getItemId());
        }
        finally {
            if (isEquipped) {
                inventory.getListeners().onEquip(equipSlot, targetItem);
                targetItem.setEquipped(true);
            }
            targetItem.save();
        }
        player.sendPacket(new IStaticPacket[]{new InventoryUpdate().addModifiedItem(targetItem), EnchantResult.BLESSED_FAILED, SystemMsg.THE_BLESSED_ENCHANT_FAILED});
    }

    private void handleEnchantFailure(Player player, ItemInstance targetItem, boolean closeEnchantWindow) {
        ItemInstance enchantScroll = player.getEnchantScroll();
        Log.LogItem((Player)player, (Log.ItemLog)Log.ItemLog.EnchantFail, (ItemInstance)targetItem, (long)1L, (long)targetItem.getReferencePrice(), (int)enchantScroll.getItemId());
        if (!closeEnchantWindow) {
            player.sendPacket(new IStaticPacket[]{EnchantResult.ANCIENT_FAILED, SystemMsg.ENCHANT_FAILED_THE_ENCHANT_SKILL_FOR_THE_CORRESPONDING_ITEM_WILL_BE_EXACTLY_RETAINED});
        } else {
            player.sendPacket(new IStaticPacket[]{EnchantResult.CANCEL, SystemMsg.ENCHANT_FAILED_THE_ENCHANT_SKILL_FOR_THE_CORRESPONDING_ITEM_WILL_BE_EXACTLY_RETAINED});
        }
    }

    private static final void showEnchantEffect(Player player, ItemInstance targetItem) {
        if (targetItem.getTemplate().getType2() == 0) {
            if (Config.SHOW_ENCHANT_EFFECT_RESULT_EVERY_NEXT_SUCCESS ? targetItem.getEnchantLevel() != Config.WEAPON_FIRST_ENCHANT_EFFECT_LEVEL && targetItem.getEnchantLevel() < Config.WEAPON_SECOND_ENCHANT_EFFECT_LEVEL : targetItem.getEnchantLevel() != Config.WEAPON_FIRST_ENCHANT_EFFECT_LEVEL && targetItem.getEnchantLevel() != Config.WEAPON_SECOND_ENCHANT_EFFECT_LEVEL) {
                return;
            }
            player.broadcastPacket(new L2GameServerPacket[]{new MagicSkillUse((Creature)((Object)player), (Creature)((Object)player), 2025, 1, 500, 1500L)});
            player.broadCastCustomMessage("_C1_HAS_SUCCESSFULLY_ENCHANTED_A_S2_S3", player, new Object[]{player, targetItem, targetItem.getEnchantLevel()});
        } else {
            if (Config.SHOW_ENCHANT_EFFECT_RESULT_EVERY_NEXT_SUCCESS ? targetItem.getEnchantLevel() < Config.ARMOR_ENCHANT_EFFECT_LEVEL : targetItem.getEnchantLevel() != Config.ARMOR_ENCHANT_EFFECT_LEVEL) {
                return;
            }
            player.broadcastPacket(new L2GameServerPacket[]{new MagicSkillUse((Creature)((Object)player), (Creature)((Object)player), 2025, 1, 500, 1500L)});
            player.broadCastCustomMessage("_C1_HAS_SUCCESSFULLY_ENCHANTED_A_S2_S3", player, new Object[]{player, targetItem, targetItem.getEnchantLevel()});
        }
    }
}

