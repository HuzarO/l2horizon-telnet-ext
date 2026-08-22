package com.l2horizon.CustomQuestsExt.handlers.user;

import l2.commons.util.Rnd;
import l2.gameserver.handler.usercommands.IUserCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.Dice;
import l2.gameserver.network.l2.s2c.SystemMessage;

public class RollUserCommand implements IUserCommandHandler {
	private static final int[] USER_COMMANDS = { 2024, 2025 };

	@Override
	public int[] getUserCommandList() {
		return USER_COMMANDS;
	}

	@Override
	public boolean useUserCommand(int id, Player player) {
		if (player.isOlyParticipant()) {
			player.sendPacket(SystemMsg.YOU_CANNOT_USE_THAT_ITEM_IN_A_GRAND_OLYMPIAD_MATCH);
			return false;
		}

		if (player.isSitting()) {
			player.sendPacket(SystemMsg.YOU_CANNOT_MOVE_WHILE_SITTING);
			return false;
		}

		if (id == 2024) {
			return rollDice(player);
		} else if (id == 2025) {
			if (!player.isInParty() || player.getParty() == null) {
				player.sendMessage("Can only be used in party!");
				return false;
			}

			if (player.getParty().getPartyLeader().getObjectId() != player.getObjectId()) {
				player.sendMessage("Party Roll can only be used by party leader!");
				return false;
			}

			player.getParty().getPartyMembers().forEach((partyMember) -> {
				rollDice(partyMember);
			});

			return true;
		}

		return false;
	}

	private static final boolean rollDice(Player player) {
		int number = Rnd.get(1, 100);
		if (number == 0) {
			player.sendPacket(SystemMsg.YOU_MAY_NOT_THROW_THE_DICE_AT_THIS_TIME_TRY_AGAIN_LATER);
			return false;
		}

		player.broadcastPacket(
				new Dice(player.getObjectId(), 0, number, player.getX() - 30, player.getY() - 30, player.getZ()),
				new SystemMessage(SystemMsg.C1_HAS_ROLLED_A_S2).addString(player.getName()).addNumber(number));

		return true;
	}
}