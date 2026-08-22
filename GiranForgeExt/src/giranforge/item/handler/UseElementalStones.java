/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  gnu.trove.TIntHashSet
 *  handler.items.ScriptItemHandler
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.network.l2.components.IStaticPacket
 */
package giranforge.item.handler;

import Config.GiranForgeConfig;
import giranforge.packets.L2EventPacket;
import gnu.trove.TIntHashSet;
import handler.items.ScriptItemHandler;
import l2.gameserver.data.xml.holder.gf.ElementalHolder;
import l2.gameserver.data.xml.parser.gf.ElementalParser;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.elements.ExAttributeOrdinal;
import l2.gameserver.network.l2.s2c.elements.ExChooseAttributeItem;
import l2.gameserver.templates.item.support.elemental.ElementalStone;

public class UseElementalStones
extends ScriptItemHandler {
    protected int[] stones;

    public UseElementalStones() {
        TIntHashSet stonesId = new TIntHashSet();
        ElementalParser.getInstance().load();
        for (ElementalStone elementalStone : ElementalHolder.getInstance().getAll()) {
            stonesId.add(elementalStone.id());
        }
        this.stones = stonesId.toArray();
    }

    public boolean useItem(Playable creature, ItemInstance itemInstance, boolean b) {
        Player player = creature.getPlayer();
        if (player == null) {
            return false;
        }
        int stoneId = itemInstance.getItemId();
        ElementalStone stone = ElementalHolder.getInstance().getById(stoneId);
        if (stone == null) {
            return false;
        }
        if (GiranForgeConfig.ENABLE_ELEMENT) {
            player.setAttributeStone(stoneId);
            player.sendPacket((L2EventPacket)new ExAttributeOrdinal());
            player.sendPacket((IStaticPacket)new ExChooseAttributeItem(player, itemInstance, stone));
        } else {
            player.sendRedMessage("The element system is not active");
        }
        return false;
    }

    public int[] getItemIds() {
        return this.stones;
    }
}

