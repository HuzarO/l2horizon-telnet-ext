/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.components;

import java.util.NoSuchElementException;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.CustomSystemMessage;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public enum CustomSystemMsg implements IStaticPacket
{
    SCREEN_S1(8000),
    RED_S1(8200),
    ORANGE_S1(8201),
    GREEN_S1(8202),
    BLUE_S1(8203),
    S1_ENCHANT_IS_SUCCESSFUL(5198);

    private final L2GameServerPacket b;
    private final int sZ;
    private final int ta;

    private CustomSystemMsg(int var3) {
        this.sZ = var3;
        if (!this.name().contains("S4") && !this.name().contains("C4")) {
            if (!this.name().contains("S3") && !this.name().contains("C3")) {
                if (!this.name().contains("S2") && !this.name().contains("C2")) {
                    if (!this.name().contains("S1") && !this.name().contains("C1")) {
                        this.ta = 0;
                        this.b = new CustomSystemMessage(this);
                    } else {
                        this.ta = 1;
                        this.b = null;
                    }
                } else {
                    this.ta = 2;
                    this.b = null;
                }
            } else {
                this.ta = 3;
                this.b = null;
            }
        } else {
            this.ta = 4;
            this.b = null;
        }
    }

    public int id() {
        return this.sZ;
    }

    public int size() {
        return this.ta;
    }

    public static CustomSystemMsg valueOf(int var0) {
        CustomSystemMsg[] var1 = CustomSystemMsg.values();
        int var2 = var1.length;
        for (CustomSystemMsg var4 : var1) {
            if (var4.id() != var0) continue;
            return var4;
        }
        throw new NoSuchElementException("CustomSystemMsg not found: " + var0);
    }

    public static int getId(int var0) {
        return var0;
    }

    @Deprecated
    public int getId() {
        return this.sZ;
    }

    public L2GameServerPacket packet(Player var1) {
        if (this.b == null) {
            throw new NoSuchElementException("Running CustomSystemMsg.packet(Player), but message require arguments: " + this.name());
        }
        return this.b;
    }
}

