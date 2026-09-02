/*
 * Decompiled with CFR 0.152.
 */
package l2.gameserver.network.l2.s2c;

import java.util.ArrayList;
import java.util.List;
import l2.gameserver.Config;
import l2.gameserver.model.pledge.Alliance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.model.pledge.SubUnit;
import l2.gameserver.model.pledge.UnitMember;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class PledgeShowMemberListAll
extends L2GameServerPacket {
    private int \uff49\u0406ll\uff49\u0406l11;
    private int l\u04c01\u04c0I\u04c0\u04c0\u0406\uff49\uff49;
    private int _level;
    private int \uff49\u0406\uff4c1\u04c01\uff4c1\u0406I\uff49\uff4c;
    private int \u04c01\uff4c1\u04061\u0406\uff4cl1\uff4c11l;
    private int \uff49Il\uff4c1\u04c0\uff49\u04c01;
    private int l\uff4c1I\uff49l\u0406\uff49\uff49;
    private int I\u0406\uff49\u04c0\uff49I\uff4c\uff4c\uff49I;
    private int \u0406I\u0406Il\uff4c\uff49\uff49\u0406\u0406;
    private int II\uff4c\u040611\u04c01l\u04c0\u04c0;
    private String \u0406\u0406l1\uff4cIII\uff49lI\u04c0;
    private String \u04c0\uff4c\uff4c1\uff491I1l1\uff491;
    private String l\uff4c\uff49\uff49lI\uff4cl\uff49\uff4c\u0406l;
    private int \u04c01I\u04c0\uff49\uff4c\u04c0\u04c0;
    private int \uff49I\uff49111\uff49\uff4cI1;
    private boolean l\u0406lI\u04c0\u04c0I1\uff4c\uff4c;
    private List<PledgePacketMember> \u0406\uff49\uff49\u04c0I\uff4c\uff4c1\u04061;

    public PledgeShowMemberListAll(Clan clan, SubUnit subUnit) {
        this.\u04c01I\u04c0\uff49\uff4c\u04c0\u04c0 = subUnit.getType();
        this.\uff49\u0406ll\uff49\u0406l11 = clan.getClanId();
        this.\u0406\u0406l1\uff4cIII\uff49lI\u04c0 = subUnit.getName();
        this.\u04c0\uff4c\uff4c1\uff491I1l1\uff491 = subUnit.getLeaderName();
        this.l\u04c01\u04c0I\u04c0\u04c0\u0406\uff49\uff49 = clan.getCrestId();
        this._level = clan.getLevel();
        this.I\u0406\uff49\u04c0\uff49I\uff4c\uff4c\uff49I = clan.getCastle();
        this.\u0406I\u0406Il\uff4c\uff49\uff49\u0406\u0406 = clan.getHasHideout();
        this.\uff49\u0406\uff4c1\u04c01\uff4c1\u0406I\uff49\uff4c = clan.getRank();
        this.\u04c01\uff4c1\u04061\u0406\uff4cl1\uff4c11l = clan.getReputationScore();
        this.II\uff4c\u040611\u04c01l\u04c0\u04c0 = clan.isAtWarOrUnderAttack();
        this.l\u0406lI\u04c0\u04c0I1\uff4c\uff4c = clan.isPlacedForDisband();
        Alliance alliance = clan.getAlliance();
        if (alliance != null) {
            this.\uff49Il\uff4c1\u04c0\uff49\u04c01 = alliance.getAllyId();
            this.l\uff4c\uff49\uff49lI\uff4cl\uff49\uff4c\u0406l = alliance.getAllyName();
            this.l\uff4c1I\uff49l\u0406\uff49\uff49 = alliance.getAllyCrestId();
        }
        this.\u0406\uff49\uff49\u04c0I\uff4c\uff4c1\u04061 = new ArrayList<PledgePacketMember>(subUnit.size());
        for (UnitMember unitMember : subUnit.getUnitMembers()) {
            this.\u0406\uff49\uff49\u04c0I\uff4c\uff4c1\u04061.add(new PledgePacketMember(unitMember));
        }
    }

    @Override
    protected final void writeImpl() {
        this.writeC(90);
        this.writeD(this.\u04c01I\u04c0\uff49\uff4c\u04c0\u04c0 == 0);
        this.writeD(this.\uff49\u0406ll\uff49\u0406l11);
        this.writeD(Config.REQUEST_ID);
        this.writeD(this.\u04c01I\u04c0\uff49\uff4c\u04c0\u04c0);
        this.writeS(this.\u0406\u0406l1\uff4cIII\uff49lI\u04c0);
        this.writeS(this.\u04c0\uff4c\uff4c1\uff491I1l1\uff491);
        this.writeD(this.l\u04c01\u04c0I\u04c0\u04c0\u0406\uff49\uff49);
        this.writeD(this._level);
        this.writeD(this.I\u0406\uff49\u04c0\uff49I\uff4c\uff4c\uff49I);
        this.writeD(0);
        this.writeD(this.\u0406I\u0406Il\uff4c\uff49\uff49\u0406\u0406);
        this.writeD(0);
        this.writeD(this.\uff49\u0406\uff4c1\u04c01\uff4c1\u0406I\uff49\uff4c);
        this.writeD(this.\u04c01\uff4c1\u04061\u0406\uff4cl1\uff4c11l);
        this.writeD(this.l\u0406lI\u04c0\u04c0I1\uff4c\uff4c ? 3 : 0);
        this.writeD(0);
        this.writeD(this.\uff49Il\uff4c1\u04c0\uff49\u04c01);
        this.writeS(this.l\uff4c\uff49\uff49lI\uff4cl\uff49\uff4c\u0406l);
        this.writeD(this.l\uff4c1I\uff49l\u0406\uff49\uff49);
        this.writeD(this.II\uff4c\u040611\u04c01l\u04c0\u04c0);
        this.writeD(0);
        this.writeD(this.\u0406\uff49\uff49\u04c0I\uff4c\uff4c1\u04061.size());
        for (PledgePacketMember pledgePacketMember : this.\u0406\uff49\uff49\u04c0I\uff4c\uff4c1\u04061) {
            this.writeS(pledgePacketMember._name);
            this.writeD(pledgePacketMember._level);
            this.writeD(pledgePacketMember.\uff49\u0406\u0406I\u04c0l\uff4c\u0406\uff4cI\u0406);
            this.writeD(pledgePacketMember.Il1\u04c0l\u0406l1\uff49\uff4c\u04c0I1\u0406);
            this.writeD(pledgePacketMember.\uff49\uff49\uff491\u04c0IlI\u0406\u0406\uff49\uff49\u04c0);
            this.writeD(pledgePacketMember.\u0406\uff49II1\uff4c\u0406I);
            this.writeD(pledgePacketMember.l\u0406I\uff4c1\u0406\u04c01\u0406I1\u0406I ? 1 : 0);
            this.writeC(0);
        }
    }

    private class PledgePacketMember {
        private String _name;
        private int _level;
        private int \uff49\u0406\u0406I\u04c0l\uff4c\u0406\uff4cI\u0406;
        private int Il1\u04c0l\u0406l1\uff49\uff4c\u04c0I1\u0406;
        private int \uff49\uff49\uff491\u04c0IlI\u0406\u0406\uff49\uff49\u04c0;
        private int \u0406\uff49II1\uff4c\u0406I;
        private boolean l\u0406I\uff4c1\u0406\u04c01\u0406I1\u0406I;

        public PledgePacketMember(UnitMember unitMember) {
            this._name = unitMember.getName();
            this._level = unitMember.getLevel();
            this.\uff49\u0406\u0406I\u04c0l\uff4c\u0406\uff4cI\u0406 = unitMember.getClassId();
            this.Il1\u04c0l\u0406l1\uff49\uff4c\u04c0I1\u0406 = unitMember.getSex();
            this.\uff49\uff49\uff491\u04c0IlI\u0406\u0406\uff49\uff49\u04c0 = 0;
            this.\u0406\uff49II1\uff4c\u0406I = unitMember.isOnline() ? unitMember.getObjectId() : 0;
            this.l\u0406I\uff4c1\u0406\u04c01\u0406I1\u0406I = unitMember.getSponsor() != 0;
        }
    }
}

