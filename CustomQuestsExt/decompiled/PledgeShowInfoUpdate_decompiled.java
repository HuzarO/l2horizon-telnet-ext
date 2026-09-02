/*
 * Decompiled with CFR 0.152.
 */
package l2.gameserver.network.l2.s2c;

import l2.gameserver.Config;
import l2.gameserver.model.pledge.Alliance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class PledgeShowInfoUpdate
extends L2GameServerPacket {
    private int clan_id;
    private int \uff49\u0406\u0406\u04c0\u04c0I\u04c0I\u04c0\u04c0\u0406II\u0406;
    private int \u0406\uff49\u04c0\u0406\uff4c\uff4cIII\u0406I1;
    private int \u04061\uff4c\u04c0\uff491\uff49\uff4cIl;
    private int crest_id;
    private int ally_id;
    private int ally_crest;
    private int \u04c0\uff491\uff4c\uff49\uff491ll\uff4c\uff4c\uff4cI\uff4c;
    private int \uff49I\uff49111\uff49\uff4cI1;
    private String ally_name = "";
    private int \u0406Il\uff4c\u04c0\u04c01\u0406\uff4cl\uff4c\uff49;
    private int I\uff49l\uff49l\uff4c\u0406\uff49\uff4c\uff49\uff4c\u04c01;
    private int l\uff49\uff49\uff4cl\uff49II;
    private boolean l\u0406lI\u04c0\u04c0I1\uff4c\uff4c;

    public PledgeShowInfoUpdate(Clan clan) {
        this.clan_id = clan.getClanId();
        this.\uff49\u0406\u0406\u04c0\u04c0I\u04c0I\u04c0\u04c0\u0406II\u0406 = clan.getLevel();
        this.\u0406Il\uff4c\u04c0\u04c01\u0406\uff4cl\uff4c\uff49 = clan.getCastle();
        this.I\uff49l\uff49l\uff4c\u0406\uff49\uff4c\uff49\uff4c\u04c01 = clan.getHasHideout();
        this.\u0406\uff49\u04c0\u0406\uff4c\uff4cIII\u0406I1 = clan.getRank();
        this.\u04061\uff4c\u04c0\uff491\uff49\uff4cIl = clan.getReputationScore();
        this.crest_id = clan.getCrestId();
        this.ally_id = clan.getAllyId();
        this.\u04c0\uff491\uff4c\uff49\uff491ll\uff4c\uff4c\uff4cI\uff4c = clan.isAtWar();
        this.l\u0406lI\u04c0\u04c0I1\uff4c\uff4c = clan.isPlacedForDisband();
        Alliance alliance = clan.getAlliance();
        if (alliance != null) {
            this.ally_name = alliance.getAllyName();
            this.ally_crest = alliance.getAllyCrestId();
        }
    }

    @Override
    protected final void writeImpl() {
        this.writeC(142);
        this.writeD(this.clan_id);
        this.writeD(Config.REQUEST_ID);
        this.writeD(this.crest_id);
        this.writeD(this.\uff49\u0406\u0406\u04c0\u04c0I\u04c0I\u04c0\u04c0\u0406II\u0406);
        this.writeD(this.\u0406Il\uff4c\u04c0\u04c01\u0406\uff4cl\uff4c\uff49);
        this.writeD(0);
        this.writeD(this.I\uff49l\uff49l\uff4c\u0406\uff49\uff4c\uff49\uff4c\u04c01);
        this.writeD(0);
        this.writeD(this.\u0406\uff49\u04c0\u0406\uff4c\uff4cIII\u0406I1);
        this.writeD(this.\u04061\uff4c\u04c0\uff491\uff49\uff4cIl);
        this.writeD(this.l\u0406lI\u04c0\u04c0I1\uff4c\uff4c ? 3 : 0);
        this.writeD(0);
        this.writeD(this.ally_id);
        this.writeS(this.ally_name);
        this.writeD(this.ally_crest);
        this.writeD(this.\u04c0\uff491\uff4c\uff49\uff491ll\uff4c\uff4c\uff4cI\uff4c);
        this.writeD(0);
        this.writeD(0);
    }
}

