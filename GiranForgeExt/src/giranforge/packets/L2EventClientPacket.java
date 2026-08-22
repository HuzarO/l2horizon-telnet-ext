/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge.packets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import l2.gameserver.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class L2EventClientPacket {
    protected static final Logger _log = LoggerFactory.getLogger(L2EventClientPacket.class);
    private static final Map<Integer, Object> packets = new HashMap<Integer, Object>();
    protected String[] _args;
    protected int _index = 1;
    protected Player _player;

    public static void handleEventPacket(Player player, String[] args) {
        try {
            Class<?> handlerClass;
            if (args.length == 0) {
                _log.warn("Empty event packet received from player: {}", (Object)player.getName());
                return;
            }
            int opcode = Integer.parseInt(args[0]);
            Object handlerObj = packets.get(opcode);
            if (handlerObj == null) {
                _log.warn("Unknown Event Packet Opcode {} ({})", (Object)opcode, (Object)player.getName());
                return;
            }
            if (handlerObj instanceof Class) {
                handlerClass = (Class<?>)handlerObj;
            } else if (handlerObj instanceof String) {
                String className = (String)handlerObj;
                try {
                    handlerClass = Class.forName(className);
                }
                catch (ClassNotFoundException e) {
                    _log.error("Handler class {} not found for opcode {}. Module may not be installed.", (Object)className, (Object)opcode);
                    return;
                }
                catch (NoClassDefFoundError e) {
                    _log.error("Handler class {} could not be loaded for opcode {}. Missing dependencies.", (Object)className, (Object)opcode);
                    return;
                }
            } else {
                _log.error("Invalid handler type for opcode {}: {}", (Object)opcode, handlerObj.getClass());
                return;
            }
            L2EventClientPacket handler = (L2EventClientPacket)handlerClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            handler._args = args;
            handler._player = player;
            handler.readImpl();
            handler.runImpl();
        }
        catch (Exception e) {
            _log.error("Error handling event packet from player: {}", (Object)player.getName(), (Object)e);
        }
    }

    private static String createPacket(String className) {
        return "giranforge.packets.c2s." + className;
    }

    protected abstract void readImpl();

    protected abstract void runImpl();

    protected String getArgsString() {
        return Arrays.toString(this._args);
    }

    protected int readInt() {
        try {
            return Integer.parseInt(this._args[this._index++]);
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("Failed to parse int at index " + (this._index - 1) + ": " + this._args[this._index - 1]);
        }
    }

    protected long readLong() {
        try {
            return Long.parseLong(this._args[this._index++]);
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("Failed to parse long at index " + (this._index - 1) + ": " + this._args[this._index - 1]);
        }
    }

    protected String readString() {
        if (this._index >= this._args.length) {
            throw new ArrayIndexOutOfBoundsException("Read beyond buffer. Index: " + this._index + ", Size: " + this._args.length);
        }
        return this._args[this._index++];
    }

    protected double readDouble() {
        try {
            return Double.parseDouble(this._args[this._index++]);
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("Failed to parse double at index " + (this._index - 1) + ": " + this._args[this._index - 1]);
        }
    }

    protected Player getPlayer() {
        return this._player;
    }

    static {
        packets.put(1, "packets.c2s.StaminaSystem");
    }
}

