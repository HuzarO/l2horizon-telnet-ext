/*
 * Decompiled with CFR 0.152.
 */
package l2.gameserver.network.l2.s2c;

import l2.gameserver.network.l2.components.CustomSystemMsg;
import l2.gameserver.network.l2.s2c.CustomSysMsgContainer;

public class CustomSystemMessage
extends CustomSysMsgContainer<CustomSystemMessage> {
    public CustomSystemMessage(CustomSystemMsg var1) {
        super(var1);
    }

    protected void writeImpl() {
        this.writeC(98);
        this.writeElements();
    }
}

