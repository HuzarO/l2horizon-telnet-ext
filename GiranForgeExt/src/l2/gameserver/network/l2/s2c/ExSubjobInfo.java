/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.SubClass
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import l2.gameserver.model.Player;
import l2.gameserver.model.SubClass;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class ExSubjobInfo
extends L2GameServerPacket {
    private final int currentClassId;
    private final int activeClassType;
    private final int playerRace;
    private final List<SubInfo> subClassList = new ArrayList<SubInfo>(4);

    public ExSubjobInfo(Player player) {
        this.currentClassId = player.getClassId().getId();
        this.playerRace = player.getRace().ordinal();
        SubClass activeClass = player.getActiveClass();
        this.activeClassType = activeClass.isBase() ? 0 : 1;
        Map<Integer, SubClass> subClasses = player.getSubClasses();
        int classIndex = 0;
        ArrayList<SubClass> sortedSubClasses = new ArrayList<SubClass>(subClasses.values());
        sortedSubClasses.sort((a, b) -> Integer.compare(a.getClassId(), b.getClassId()));
        for (SubClass subClass : sortedSubClasses) {
            SubInfo subInfo = new SubInfo();
            subInfo.index = classIndex;
            subInfo.classId = subClass.getClassId();
            subInfo.level = subClass.getLevel();
            subInfo.type = this.getSubClassType(subClass);
            this.subClassList.add(subInfo);
            classIndex = (byte)(classIndex + 1);
        }
    }

    protected void writeImpl() {
        this.writeEx(234);
        this.writeC(this.activeClassType);
        this.writeD(this.currentClassId);
        this.writeD(this.playerRace);
        this.writeD(this.subClassList.size());
        for (SubInfo subInfo : this.subClassList) {
            this.writeD(subInfo.index);
            this.writeD(subInfo.classId);
            this.writeD(subInfo.level);
            this.writeC(subInfo.type);
        }
    }

    private int getSubClassType(SubClass subClass) {
        if (subClass.isBase()) {
            return 0;
        }
        try {
            Method isDualMethod = subClass.getClass().getMethod("isDual", new Class[0]);
            Boolean isDual = (Boolean)isDualMethod.invoke((Object)subClass, new Object[0]);
            if (isDual != null && isDual.booleanValue()) {
                return 1;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return 2;
    }

    private static class SubInfo {
        int index;
        int classId;
        int level;
        int type;

        private SubInfo() {
        }
    }
}

