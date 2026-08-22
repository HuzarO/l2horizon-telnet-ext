/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 */
package l2.gameserver.network.l2.c2s.appearance;

import l2.gameserver.model.Player;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.request.imp.AppearanceRequest;

public class RequestAppearanceCancel
extends L2GameClientPacket {
    protected void readImpl() throws Exception {
    }

    protected void runImpl() throws Exception {
        Player player = ((GameClient)this._client).getActiveChar();
        if (player == null) {
            return;
        }
        player.removeSpecialRequest(AppearanceRequest.class);
    }
}

