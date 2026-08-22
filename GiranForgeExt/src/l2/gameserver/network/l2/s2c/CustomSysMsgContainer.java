/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.s2c;

import java.util.ArrayList;
import java.util.List;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.CustomSystemMsg;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CustomSysMsgContainer<T extends CustomSysMsgContainer<T>>
extends L2GameServerPacket {
    private final Logger db = LoggerFactory.getLogger(CustomSysMsgContainer.class);
    protected CustomSystemMsg _message;
    protected List<IArgument> _arguments;

    protected CustomSysMsgContainer(CustomSystemMsg var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("SystemMsg is null");
        }
        this._message = var1;
        this._arguments = new ArrayList<IArgument>(this._message.size());
    }

    protected void writeElements() {
        if (this._message.size() != this._arguments.size()) {
            throw new IllegalArgumentException("Wrong count of arguments: " + this._message, new Exception());
        }
        this.writeH(this._message.id());
        this.writeC(this._arguments.size());
        for (IArgument var2 : this._arguments) {
            var2.write(this);
        }
    }

    public L2GameServerPacket packet(Player var1) {
        if (this._message.size() != this._arguments.size()) {
            this.db.debug("Wrong count of arguments: {}", (Object)this._message, (Object)new Exception());
            return null;
        }
        return this;
    }

    public T addString(String var1) {
        return this.add(new StringArgument(var1));
    }

    protected T add(IArgument var1) {
        this._arguments.add(var1);
        return (T)((Object)this);
    }

    public static abstract class IArgument {
        void write(CustomSysMsgContainer<?> var1) {
            ((CustomSysMsgContainer)var1).writeC(this.getType().ordinal());
            this.writeData(var1);
        }

        abstract Types getType();

        abstract void writeData(CustomSysMsgContainer<?> var1);
    }

    private static class StringArgument
    extends IArgument {
        private final String fw;

        public StringArgument(String var1) {
            this.fw = var1 == null ? "null" : var1;
        }

        @Override
        void writeData(CustomSysMsgContainer<?> var1) {
            ((CustomSysMsgContainer)var1).writeS(this.fw);
        }

        @Override
        Types getType() {
            return Types.TEXT;
        }
    }

    public static enum Types {
        TEXT,
        NUMBER,
        U_17,
        U_18,
        U_19,
        BYTE;

    }
}

