/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.handler.usercommands.IUserCommandHandler
 *  l2.gameserver.model.GameObject
 */
package tactical.sign;

import l2.gameserver.handler.usercommands.IUserCommandHandler;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;

public class UserTacticalSign
implements IUserCommandHandler {
    private static final int[] USER_COMMANDS = new int[]{2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023};

    public int[] getUserCommandList() {
        return USER_COMMANDS;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean useUserCommand(int id, Player player) {
        int n;
        if (!player.isInParty()) {
            return false;
        }
        switch (id) {
            case 2000: 
            case 2012: {
                n = 5;
                break;
            }
            case 2001: 
            case 2013: {
                n = 6;
                break;
            }
            case 2002: 
            case 2014: {
                n = 7;
                break;
            }
            case 2003: 
            case 2015: {
                n = 8;
                break;
            }
            case 2004: 
            case 2016: {
                n = 9;
                break;
            }
            case 2005: 
            case 2017: {
                n = 10;
                break;
            }
            case 2006: 
            case 2018: {
                n = 11;
                break;
            }
            case 2007: 
            case 2019: {
                n = 12;
                break;
            }
            case 2008: 
            case 2020: {
                n = 13;
                break;
            }
            case 2009: 
            case 2021: {
                n = 14;
                break;
            }
            case 2010: 
            case 2022: {
                n = 15;
                break;
            }
            case 2011: 
            case 2023: {
                n = 16;
                break;
            }
            default: {
                return false;
            }
        }
        int tacticalSignId = n;
        if (tacticalSignId == 0) {
            return false;
        }
        if (id == 2000 || id == 2001 || id == 2002 || id == 2003 || id == 2004 || id == 2005 || id == 2006 || id == 2007 || id == 2008 || id == 2009 || id == 2010 || id == 2011) {
            if (player.getTarget() == null) return false;
            if (!player.getTarget().isCreature()) {
                return false;
            }
            GameObject gameObject = player.getTarget();
            if (!(gameObject instanceof Creature)) return false;
            Creature creatureTarget = (Creature)gameObject;
            player.getParty().addTacticalSign(player, tacticalSignId, creatureTarget);
            return true;
        } else {
            player.getParty().setTargetBasedOnTacticalSignId(player, tacticalSignId);
        }
        return true;
    }
}

