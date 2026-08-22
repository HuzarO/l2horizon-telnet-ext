/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  gnu.trove.set.hash.TIntHashSet
 *  l2.gameserver.instancemanager.RaidBossSpawnManager
 *  l2.gameserver.instancemanager.RaidBossSpawnManager$Status
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.s2c;

import gnu.trove.set.hash.TIntHashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import l2.gameserver.instancemanager.RaidBossSpawnManager;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExRaidBossSpawnInfo
extends L2GameServerPacket {
    private static final Logger _log = LoggerFactory.getLogger(ExRaidBossSpawnInfo.class);
    private final Map<Integer, Integer> ba = new LinkedHashMap<Integer, Integer>();

    public ExRaidBossSpawnInfo() {
        RaidBossSpawnManager var1 = RaidBossSpawnManager.getInstance();
        Map<Integer, ?> var2 = var1.getSpawnTable();
        new TIntHashSet();
        for (Integer var5 : var2.keySet()) {
            RaidBossSpawnManager.Status var6 = var1.getRaidBossStatusId(var5.intValue());
            switch (var6) {
                case ALIVE: {
                    this.ba.put(var5, 1);
                    break;
                }
                case DEAD: {
                    this.ba.put(var5, 0);
                    break;
                }
                case UNDEFINED: {
                    this.ba.put(var5, 2);
                }
            }
        }
    }

    protected void writeImpl() {
        this.writeEx(441);
        this.writeD(this.ba.size());
        for (Map.Entry<Integer, Integer> var2 : this.ba.entrySet()) {
            int var3 = Integer.parseInt(var2.getKey().toString() + var2.getValue().toString());
            this.writeD(var3);
        }
    }
}

