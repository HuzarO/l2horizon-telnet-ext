package com.l2horizon.CustomQuestsExt.handlers.user;

import l2.gameserver.handler.usercommands.IUserCommandHandler;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;

public class TacticalSignUserCommand implements IUserCommandHandler {
	private static final int[] USER_COMMANDS = { 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023 };

	@Override
	public int[] getUserCommandList() {
		return USER_COMMANDS;
	}

	@Override
	public boolean useUserCommand(int id, Player player) {
		if (!player.isInParty()) {
			return false;
		}
		
		int tacticalSignId = 0;
		
		switch(id) {
		case 2000:
		case 2012:
			tacticalSignId = 5;
			break;
			
		case 2001:
		case 2013:
			tacticalSignId = 6;
			break;
			
		case 2002:
		case 2014:
			tacticalSignId = 7;
			break;
			
		case 2003:
		case 2015:
			tacticalSignId = 8;
			break;
			
		case 2004:
		case 2016:
			tacticalSignId = 9;
			break;
			
		case 2005:
		case 2017:
			tacticalSignId = 10;
			break;
			
		case 2006:
		case 2018:
			tacticalSignId = 11;
			break;
			
		case 2007:
		case 2019:
			tacticalSignId = 12;
			break;
			
		case 2008:
		case 2020:
			tacticalSignId = 13;
			break;
			
		case 2009:
		case 2021:
			tacticalSignId = 14;
			break;
			
		case 2010:
		case 2022:
			tacticalSignId = 15;
			break;
			
		case 2011:
		case 2023:
			tacticalSignId = 16;
			break;
		}
		
		if(tacticalSignId == 0) {
			return false;
		}
		
		if(id == 2000 || id == 2001 || id == 2002 || id == 2003 || id == 2004 || id == 2005 || id == 2006 || id == 2007|| id == 2008 || id == 2009 || id == 2010 || id == 2011) {
			if (player.getTarget() == null || !player.getTarget().isCreature()) {
				return false;
			}
			
			if(player.getTarget() instanceof Creature creatureTarget) {
				player.getParty().addTacticalSign(player, tacticalSignId, creatureTarget);
			} else {
				return false;
			}
			
			return true;
		} else if(id == 2012 || id == 2013 || id == 2014 || id == 2015 || id == 2016 || id == 2017 || id == 2018 || id == 2019 || id == 2020 || id == 2021 || id == 2022 || id == 2023) {
			player.getParty().setTargetBasedOnTacticalSignId(player, tacticalSignId);
			
			return true;
		}
		
		return false;
	}
}