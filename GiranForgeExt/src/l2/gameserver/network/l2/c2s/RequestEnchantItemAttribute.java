/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.util.Rnd
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.base.Element
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.s2c.InventoryUpdate
 *  l2.gameserver.utils.ItemFunctions
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.c2s;

import Config.GiranForgeConfig;
import giranforge.exception.UserException;
import l2.commons.util.Rnd;
import l2.gameserver.data.xml.holder.gf.ElementalHolder;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.base.Element;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.InventoryUpdate;
import l2.gameserver.network.l2.s2c.elements.ExAttributeResult;
import l2.gameserver.templates.item.support.elemental.ElementalStone;
import l2.gameserver.utils.ItemFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestEnchantItemAttribute
extends L2GameClientPacket {
    public static final Logger _log = LoggerFactory.getLogger(RequestEnchantItemAttribute.class);
    protected int objectId;
    protected long stoneQty;

    protected void readImpl() {
        this.objectId = this.readD();
        this.stoneQty = this.readQ();
    }

    protected void runImpl() {
        Player player = ((GameClient)this.getClient()).getActiveChar();
        if (player == null) {
            return;
        }
        if (!GiranForgeConfig.ENABLE_ELEMENT) {
            return;
        }
        try {
            int currentLevel;
            if (this.objectId == -1 || this.stoneQty == 0L) {
                return;
            }
            ItemInstance itemInstance = player.getInventory().getItemByObjectId(this.objectId);
            if (itemInstance == null) {
                throw new UserException("You do not have the required item");
            }
            Integer idStone = player.getAttributeStone();
            if (idStone == null) {
                throw new UserException("Invalid request, please try again");
            }
            ElementalStone elementalStone = ElementalHolder.getInstance().getById(idStone);
            if (elementalStone == null) {
                throw new UserException("This attribute stone is not registered");
            }
            long quantity = ItemFunctions.getItemCount((Playable)player, (int)idStone);
            if (quantity < this.stoneQty) {
                throw new UserException("You do not have enough attribute stone");
            }
            int crystalId = itemInstance.getCrystalType().ordinal();
            if (crystalId < GiranForgeConfig.ATTRIBUTE_CRYSTAL_ORDINAL) {
                throw new UserException("This item is not suitable for this attribute");
            }
            Element applyElement = elementalStone.getElement(itemInstance.isWeapon());
            if (itemInstance.getAttributeElementValue(Element.getReverseElement((Element)applyElement), false) != 0) {
                throw new UserException("Another elemental power has already been added. This elemental power cannot be added");
            }
            if (itemInstance.isWeapon() ? itemInstance.getAttributeElement() != Element.NONE && itemInstance.getAttributeElement() != applyElement : itemInstance.isArmor() && itemInstance.getAttributeElementValue(Element.getReverseElement((Element)applyElement), false) != 0) {
                throw new UserException("Another elemental power has already been added. This elemental power cannot be added");
            }
            int totalUsed = 0;
            int success = 0;
            int failStones = 0;
            int newLevel = currentLevel = itemInstance.getAttributeElementValue(applyElement, false);
            int maxLevel = itemInstance.isWeapon() ? elementalStone.maxWeapon() : elementalStone.maxArmor();
            int i = 0;
            while ((long)i < this.stoneQty && newLevel < maxLevel) {
                if (Rnd.chance((int)elementalStone.chance())) {
                    newLevel += elementalStone.increase();
                    ++success;
                } else {
                    ++failStones;
                }
                ++totalUsed;
                ++i;
            }
            ItemFunctions.removeItem((Playable)player, (int)elementalStone.id(), (long)totalUsed, (boolean)true);
            itemInstance.setAttributeElement(applyElement, newLevel);
            player.sendPacket((IStaticPacket)new ExAttributeResult(itemInstance.isWeapon(), applyElement, currentLevel, newLevel, success, failStones));
            player.sendPacket((IStaticPacket)new InventoryUpdate().addModifiedItem(itemInstance));
        }
        catch (UserException userException) {
            player.sendRedMessage(userException.getMessage());
        }
        catch (Exception e) {
            _log.error(e.getMessage());
        }
    }
}

