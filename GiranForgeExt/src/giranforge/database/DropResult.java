/*
 * Decompiled with CFR 0.152.
 */
package giranforge.database;

public class DropResult {
    double chance;
    long minDrop;
    long maxDrop;

    DropResult(double chance, long minDrop, long maxDrop) {
        this.chance = chance;
        this.minDrop = minDrop;
        this.maxDrop = maxDrop;
    }
}

