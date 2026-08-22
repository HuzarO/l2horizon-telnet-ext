/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.components.NpcString
 *  l2.gameserver.network.l2.s2c.NpcStringContainer
 */
package l2.gameserver.network.l2.s2c;

import l2.gameserver.network.l2.components.NpcString;
import l2.gameserver.network.l2.s2c.NpcStringContainer;

public class ExShowScreenMessage
extends NpcStringContainer {
    public static final int SYSMSG_TYPE = 0;
    public static final int STRING_TYPE = 1;
    private final int _type;
    private final int yH;
    private final boolean fa;
    private final boolean fb;
    private final ScreenMessageAlign a;
    private final int _time;

    @Deprecated
    public ExShowScreenMessage(String var1, int var2, ScreenMessageAlign var3, boolean var4) {
        this(var1, var2, var3, var4, 1, -1, false);
    }

    @Deprecated
    public ExShowScreenMessage(String var1, int var2, ScreenMessageAlign var3, boolean var4, int var5, int var6, boolean var7) {
        super(NpcString.NONE, new String[]{var1});
        this._type = var5;
        this.yH = var6;
        this._time = var2;
        this.a = var3;
        this.fa = var4;
        this.fb = var7;
    }

    public ExShowScreenMessage(NpcString var1, int var2, ScreenMessageAlign var3, String ... var4) {
        this(var1, var2, var3, true, 1, -1, false, var4);
    }

    public ExShowScreenMessage(NpcString var1, int var2, ScreenMessageAlign var3, boolean var4, String ... var5) {
        this(var1, var2, var3, var4, 1, -1, false, var5);
    }

    public ExShowScreenMessage(NpcString var1, int var2, ScreenMessageAlign var3, boolean var4, boolean var5, String ... var6) {
        this(var1, var2, var3, var4, 1, -1, var5, var6);
    }

    public ExShowScreenMessage(String message) {
        this(NpcString.NONE, 0, ScreenMessageAlign.BOTTOM_RIGHT, false, 1, -1, false, message);
    }

    public ExShowScreenMessage(NpcString var1, int var2, ScreenMessageAlign var3, boolean var4, int var5, int var6, boolean var7, String ... var8) {
        super(var1, var8);
        this._type = var5;
        this.yH = var6;
        this._time = var2;
        this.a = var3;
        this.fa = var4;
        this.fb = var7;
    }

    protected final void writeImpl() {
        this.writeEx(57);
        this.writeD(this._type);
        this.writeD(this.yH);
        this.writeD(this.a.ordinal() + 1);
        this.writeD(0);
        this.writeD(this.fa ? 0 : 1);
        this.writeD(0);
        this.writeD(0);
        this.writeD(this.fb ? 1 : 0);
        this.writeD(this._time);
        this.writeD(1);
        this.writeElements();
    }

    public static enum ScreenMessageAlign {
        TOP_LEFT,
        TOP_CENTER,
        TOP_RIGHT,
        MIDDLE_LEFT,
        MIDDLE_CENTER,
        MIDDLE_RIGHT,
        BOTTOM_CENTER,
        BOTTOM_RIGHT;

    }
}

