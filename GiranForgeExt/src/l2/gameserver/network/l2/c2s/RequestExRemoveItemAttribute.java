/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.base.Element
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.model.items.PcInventory
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.s2c.ExBaseAttributeCancelResult
 *  l2.gameserver.network.l2.s2c.InventoryUpdate
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.c2s;

import Config.GiranForgeConfig;
import giranforge.exception.UserException;
import l2.gameserver.model.Player;
import l2.gameserver.model.base.Element;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.ExBaseAttributeCancelResult;
import l2.gameserver.network.l2.s2c.InventoryUpdate;
import l2.gameserver.network.l2.s2c.elements.ExAttributeCancelWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestExRemoveItemAttribute
extends L2GameClientPacket {
    public static final Logger _log = LoggerFactory.getLogger(RequestExRemoveItemAttribute.class);
    protected int objectId;
    protected int attributeId;

    protected void readImpl() throws Exception {
        this.objectId = this.readD();
        this.attributeId = this.readD();
    }

    protected void runImpl() throws Exception {
        Player player = ((GameClient)this.getClient()).getActiveChar();
        if (player == null) {
            return;
        }
        try {
            PcInventory pcInventory = player.getInventory();
            ItemInstance itemInstance = pcInventory.getItemByObjectId(this.objectId);
            long removeFee = GiranForgeConfig.REMOVE_ATTRIBUTE_FEE;
            if (itemInstance == null) {
                throw new UserException("You do not have the required item");
            }
            if (player.getAdena() < removeFee) {
                throw new UserException("You do not have enough Adena");
            }
            Element element = Element.getElementById((int)this.attributeId);
            if (element == null) {
                throw new UserException("Invalid attribute");
            }
            if (itemInstance.getAttributeElementValue(element, false) == 0) {
                throw new UserException("This item has no attributes");
            }
            boolean isEquipped = false;
            if (itemInstance.isEquipped()) {
                isEquipped = true;
                pcInventory.unEquipItem(itemInstance);
            }
            if (isEquipped) {
                pcInventory.equipItem(itemInstance);
            }
            player.reduceAdena(removeFee, true);
            itemInstance.setAttributeElement(element, 0);
            boolean hasMoreElements = itemInstance.getAttributeElement() != Element.NONE;
            player.sendPacket((IStaticPacket)new InventoryUpdate().addModifiedItem(itemInstance));
            if (hasMoreElements) {
                player.sendPacket((IStaticPacket)new ExBaseAttributeCancelResult(true, itemInstance, element));
            } else {
                player.sendPacket((IStaticPacket)new ExAttributeCancelWindow(player));
            }
            player.updateStats();
        }
        catch (UserException userException) {
            player.sendActionFailed();
            player.sendRedMessage(userException.getMessage());
        }
        catch (Exception e) {
            _log.error(e.getMessage());
        }
    }
}

