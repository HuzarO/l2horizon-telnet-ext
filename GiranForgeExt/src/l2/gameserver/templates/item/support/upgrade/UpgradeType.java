/*
 * Decompiled with CFR 0.152.
 */
package l2.gameserver.templates.item.support.upgrade;

public enum UpgradeType {
    RARE,
    NORMAL,
    SPECIAL;


    public static UpgradeType getById(int type) {
        return switch (type) {
            case 0 -> RARE;
            case 1 -> NORMAL;
            case 2 -> SPECIAL;
            default -> null;
        };
    }
}

