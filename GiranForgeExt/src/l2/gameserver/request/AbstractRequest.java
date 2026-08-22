/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.ThreadPoolManager
 */
package l2.gameserver.request;

import l2.gameserver.ThreadPoolManager;
import l2.gameserver.model.Player;

public abstract class AbstractRequest {
    private final Player player;

    public AbstractRequest(Player player) {
        this.player = player;
    }

    public void scheduleTimeout(long delayMillis) {
        ThreadPoolManager.getInstance().schedule(() -> {
            this.player.removeSpecialRequest(this.getClass());
            this.onTimeout();
        }, delayMillis);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void onTimeout() {
    }
}

