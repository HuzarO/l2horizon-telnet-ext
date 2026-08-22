/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.util.Rnd
 *  l2.gameserver.Config
 *  l2.gameserver.data.xml.holder.EnchantItemHolder
 *  l2.gameserver.data.xml.holder.OneDayRewardHolder
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
 */
package l2.gameserver.network.l2.c2s;

import l2.commons.util.Rnd;
import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.EnchantItemHolder;
import l2.gameserver.data.xml.holder.OneDayRewardHolder;
import l2.gameserver.model.Creature;
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
import l2.gameserver.network.l2.s2c.InventoryUpdate;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.network.l2.s2c.MagicSkillUse;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.templates.item.support.EnchantCatalyzer;
import l2.gameserver.templates.item.support.EnchantScroll;
import l2.gameserver.templates.item.support.EnchantScrollOnFailAction;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Log;

public class RequestEnchantItem
extends L2GameClientPacket {
    private int gj;
    private int rh;

    protected void readImpl() {
        this.gj = this.readD();
        this.rh = this.readD();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void runImpl() {
        Player var2;
        GameClient var1 = (GameClient)this.getClient();
        if (var1 != null && (var2 = var1.getActiveChar()) != null) {
            if (var2.isActionsDisabled()) {
                var2.setEnchantScroll(null);
                var2.sendActionFailed();
            } else if (var2.isInTrade()) {
                var2.setEnchantScroll(null);
                var2.sendActionFailed();
            } else if (var2.isInStoreMode()) {
                var2.setEnchantScroll(null);
                var2.sendPacket((IStaticPacket)EnchantResult.CANCEL);
                var2.sendPacket((IStaticPacket)SystemMsg.YOU_CANNOT_ENCHANT_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
                var2.sendActionFailed();
            } else if (!var2.isTradeBannedByGM() && !var2.isSelfRestricted(true)) {
                PcInventory var3 = var2.getInventory();
                var3.writeLock();
                try {
                    ItemInstance var4 = var3.getItemByObjectId(this.gj);
                    ItemInstance var5 = this.rh > 0 ? var3.getItemByObjectId(this.rh) : null;
                    ItemInstance var6 = var2.getEnchantScroll();
                    if (var4 != null && var6 != null) {
                        EnchantScroll var7 = EnchantItemHolder.getInstance().getEnchantScroll(var6.getItemId());
                        if (var7 == null) {
                            var2.sendActionFailed();
                            return;
                        }
                        if (!var4.canBeEnchanted(false)) {
                            var2.sendPacket((IStaticPacket)EnchantResult.CANCEL);
                            var2.sendPacket((IStaticPacket)SystemMsg.INAPPROPRIATE_ENCHANT_CONDITIONS);
                            var2.sendActionFailed();
                            return;
                        }
                        if (!var7.isUsableWith(var4, (EnchantCatalyzer)null)) {
                            var2.sendPacket((IStaticPacket)EnchantResult.CANCEL);
                            var2.sendPacket((IStaticPacket)SystemMsg.DOES_NOT_FIT_STRENGTHENING_CONDITIONS_OF_THE_SCROLL);
                            var2.sendActionFailed();
                            return;
                        }
                        EnchantCatalyzer var8 = var5 != null ? EnchantItemHolder.getInstance().getEnchantCatalyzer(var5.getItemId()) : null;
                        double var9 = 1.0 + var7.getChanceMod();
                        int var11 = var4.getEnchantLevel() + Rnd.get((int)var7.getIncrement(), (int)var7.getIncrementMax());
                        var9 *= var2.getEnchantBonusMul();
                        if (var8 != null) {
                            var9 += var8.getChanceMod();
                        }
                        if (var3.destroyItem(var6, 1L) && (var5 == null || var8 == null || var3.destroyItem(var5, 1L))) {
                            if (var8 != null && !var8.isUsableWith(var4)) {
                                var8 = null;
                            }
                            if (!var7.isUsableWith(var4, var8)) {
                                var2.sendPacket((IStaticPacket)EnchantResult.CANCEL);
                                var2.sendPacket((IStaticPacket)SystemMsg.DOES_NOT_FIT_STRENGTHENING_CONDITIONS_OF_THE_SCROLL);
                                var2.sendActionFailed();
                                return;
                            }
                            double var12 = var7.getEnchantChance(var4);
                            if (!var7.isInfallible() && !Rnd.chance((double)(var12 * var9))) {
                                EnchantScrollOnFailAction var14 = var7.getOnFailAction();
                                if (var8 != null && var14 != EnchantScrollOnFailAction.NONE) {
                                    var14 = var8.getResultType();
                                }
                                switch (var14) {
                                    case CRYSTALIZE: {
                                        this.a(var2, var4);
                                        return;
                                    }
                                    case RESET: {
                                        this.b(var2, var4, var7.getFailResultLevel());
                                        return;
                                    }
                                    case NONE: {
                                        this.a(var2, var4, var7.isCloseEnchantWindowOnFail());
                                        return;
                                    }
                                }
                                return;
                            }
                            if (var7.getIncrement() > 1 && var11 > var7.getMaxLvl()) {
                                this.a(var2, var4, var7.getMaxLvl());
                                return;
                            }
                            this.a(var2, var4, var11);
                            return;
                        }
                        var2.sendPacket((IStaticPacket)EnchantResult.CANCEL);
                        var2.sendPacket((IStaticPacket)SystemMsg.INAPPROPRIATE_ENCHANT_CONDITIONS);
                        var2.sendActionFailed();
                        return;
                    }
                    var2.sendActionFailed();
                }
                finally {
                    var3.writeUnlock();
                    var2.updateStats();
                }
            } else {
                var2.setEnchantScroll(null);
                var2.sendActionFailed();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(Player var1, ItemInstance var2, int var3) {
        PcInventory var4 = var1.getInventory();
        ItemInstance var5 = var1.getEnchantScroll();
        if (var3 >= 65535) {
            var1.sendPacket(new IStaticPacket[]{EnchantResult.CANCEL, SystemMsg.DOES_NOT_FIT_STRENGTHENING_CONDITIONS_OF_THE_SCROLL});
            var1.sendActionFailed();
        } else {
            boolean var6 = var2.isEquipped();
            int var7 = var2.getEquipSlot();
            if (var6) {
                var2.setEquipped(false);
                var4.getListeners().onUnequip(var7, var2);
            }
            try {
                var2.setEnchantLevel(var3);
                Log.LogItem((Player)var1, (Log.ItemLog)Log.ItemLog.EnchantSuccess, (ItemInstance)var2, (long)1L, (long)var2.getReferencePrice(), (int)var5.getItemId());
            }
            finally {
                if (var6) {
                    var4.getListeners().onEquip(var7, var2);
                    var2.setEquipped(true);
                }
                var2.save();
            }
            var1.sendPacket((IStaticPacket)new InventoryUpdate().addModifiedItem(var2));
            var1.sendPacket((IStaticPacket)new EnchantResult(var2.getEnchantLevel()));
            var1.getListeners().onItemEnchantSuccessListener(var2.getItemId(), var2.getEnchantLevel());
            if (Config.SHOW_ENCHANT_EFFECT_RESULT) {
                RequestEnchantItem.b(var1, var2);
            }
            OneDayRewardHolder.getInstance().fireRequirements(var1, (Creature)null, EnchantItemRequirement.class);
        }
    }

    private void a(Player var1, ItemInstance var2) {
        PcInventory var3 = var1.getInventory();
        boolean var4 = var2.isEquipped();
        int var5 = var2.getItemId();
        int var6 = var2.getEnchantLevel();
        int var7 = var2.getCrystalItemId();
        int var8 = var2.getTemplate().getCrystalCount();
        ItemInstance var9 = var1.getEnchantScroll();
        if (var4) {
            var1.sendDisarmMessage(var2);
            var3.unEquipItem(var2);
        }
        Log.LogItem((Player)var1, (Log.ItemLog)Log.ItemLog.EnchantCrystallize, (ItemInstance)var2, (long)1L, (long)var2.getReferencePrice(), (int)var9.getItemId());
        if (!var3.destroyItem(var2, 1L)) {
            var1.sendActionFailed();
        } else if (var7 > 0 && var8 > 0) {
            int var10 = (int)((double)var8 * 0.87);
            if (var6 > 3 && Config.CRYSTALLIZE_BONUS_AT_ENCHANT) {
                var10 = (int)((double)var10 + (double)var8 * 0.25 * (double)(var6 - 3));
            }
            if (var10 < 1) {
                var10 = 1;
            }
            var1.sendPacket(new IStaticPacket[]{new EnchantResult(1, var7, (long)var10), ((SystemMessage)new SystemMessage(SystemMsg.THE_ENCHANTMENT_HAS_FAILED__YOUR_S1_S2_HAS_BEEN_CRYSTALLIZED).addNumber(var6)).addItemName(var5)});
            ItemFunctions.addItem((Playable)var1, (int)var7, (long)var10, (boolean)true);
        } else {
            var1.sendPacket(new IStaticPacket[]{EnchantResult.FAILED_NO_CRYSTALS, new SystemMessage(SystemMsg.THE_ENCHANTMENT_HAS_FAILED_YOUR_S1_HAS_BEEN_CRYSTALLIZED).addItemName(var2.getItemId())});
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void b(Player var1, ItemInstance var2, int var3) {
        PcInventory var4 = var1.getInventory();
        boolean var5 = var2.isEquipped();
        int var6 = var2.getEquipSlot();
        int var7 = Math.min(var2.getEnchantLevel(), var3);
        ItemInstance var8 = var1.getEnchantScroll();
        if (var5) {
            var2.setEquipped(false);
            var4.getListeners().onUnequip(var6, var2);
        }
        try {
            var2.setEnchantLevel(var7);
            Log.LogItem((Player)var1, (Log.ItemLog)Log.ItemLog.EnchantReset, (ItemInstance)var2, (long)1L, (long)var2.getReferencePrice(), (int)var8.getItemId());
        }
        finally {
            if (var5) {
                var4.getListeners().onEquip(var6, var2);
                var2.setEquipped(true);
            }
            var2.save();
        }
        var1.sendPacket(new IStaticPacket[]{new InventoryUpdate().addModifiedItem(var2), EnchantResult.BLESSED_FAILED, SystemMsg.THE_BLESSED_ENCHANT_FAILED});
    }

    private void a(Player var1, ItemInstance var2, boolean var3) {
        ItemInstance var4 = var1.getEnchantScroll();
        Log.LogItem((Player)var1, (Log.ItemLog)Log.ItemLog.EnchantFail, (ItemInstance)var2, (long)1L, (long)var2.getReferencePrice(), (int)var4.getItemId());
        if (!var3) {
            var1.sendPacket(new IStaticPacket[]{EnchantResult.ANCIENT_FAILED, SystemMsg.ENCHANT_FAILED_THE_ENCHANT_SKILL_FOR_THE_CORRESPONDING_ITEM_WILL_BE_EXACTLY_RETAINED});
        } else {
            var1.sendPacket(new IStaticPacket[]{EnchantResult.CANCEL, SystemMsg.ENCHANT_FAILED_THE_ENCHANT_SKILL_FOR_THE_CORRESPONDING_ITEM_WILL_BE_EXACTLY_RETAINED});
        }
    }

    private static final void b(Player var0, ItemInstance var1) {
        if (var1.getTemplate().getType2() == 0) {
            if (Config.SHOW_ENCHANT_EFFECT_RESULT_EVERY_NEXT_SUCCESS ? var1.getEnchantLevel() != Config.WEAPON_FIRST_ENCHANT_EFFECT_LEVEL && var1.getEnchantLevel() < Config.WEAPON_SECOND_ENCHANT_EFFECT_LEVEL : var1.getEnchantLevel() != Config.WEAPON_FIRST_ENCHANT_EFFECT_LEVEL && var1.getEnchantLevel() != Config.WEAPON_SECOND_ENCHANT_EFFECT_LEVEL) {
                return;
            }
            var0.broadcastPacket(new L2GameServerPacket[]{new MagicSkillUse((Creature)((Object)var0), (Creature)((Object)var0), 2025, 1, 500, 1500L)});
            var0.broadCastCustomMessage("_C1_HAS_SUCCESSFULLY_ENCHANTED_A_S2_S3", var0, new Object[]{var0, var1, var1.getEnchantLevel()});
        } else {
            if (Config.SHOW_ENCHANT_EFFECT_RESULT_EVERY_NEXT_SUCCESS ? var1.getEnchantLevel() < Config.ARMOR_ENCHANT_EFFECT_LEVEL : var1.getEnchantLevel() != Config.ARMOR_ENCHANT_EFFECT_LEVEL) {
                return;
            }
            var0.broadcastPacket(new L2GameServerPacket[]{new MagicSkillUse((Creature)((Object)var0), (Creature)((Object)var0), 2025, 1, 500, 1500L)});
            var0.broadCastCustomMessage("_C1_HAS_SUCCESSFULLY_ENCHANTED_A_S2_S3", var0, new Object[]{var0, var1, var1.getEnchantLevel()});
        }
    }
}

