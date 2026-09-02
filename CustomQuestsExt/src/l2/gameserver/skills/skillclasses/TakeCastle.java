package l2.gameserver.skills.skillclasses;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.World;
import l2.gameserver.model.entity.events.impl.CastleSiegeEvent;
import l2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import l2.gameserver.model.entity.events.objects.FortressCombatFlagObject;
import l2.gameserver.model.entity.events.objects.SiegeClanObject;
import l2.gameserver.model.entity.events.objects.StaticObjectObject;
import l2.gameserver.model.instances.StaticObjectInstance;
import l2.gameserver.model.entity.residence.Fortress;
import npc.model.residences.fortress.FlagPoleInstance;
import l2.gameserver.model.items.attachment.ItemAttachment;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.tables.GmListTable;
import l2.gameserver.templates.StatsSet;
import l2.gameserver.utils.Log;

/**
 * Classpath override of the server.jar TakeCastle skill class. The castle
 * (Seal of Ruler) path below is recreated 1:1 from the decompiled original (see
 * decompiled/TakeCastle_decompiled.java in the repo). On top of it, a fortress
 * branch is added, ported from the L2Scripts High Five TakeFortress skill class:
 * this core has no TAKEFORTRESS SkillType, so the fortress Flag Display skill
 * (3318) is declared with skillType="TAKECASTLE" and lands here. The branch is
 * taken only when the caster's target is a fortress flag pole (StaticObject
 * type 3), so Seal of Ruler behavior is unchanged.
 */
public class TakeCastle extends Skill
{
	private static final Logger _log = LoggerFactory.getLogger(TakeCastle.class);

	public TakeCastle(StatsSet set)
	{
		super(set);
	}

	@Override
	public boolean checkCondition(Creature activeChar, Creature target, boolean forceUse, boolean dontMove, boolean first)
	{
		if(!super.checkCondition(activeChar, target, forceUse, dontMove, first))
			return false;

		if(activeChar == null || !activeChar.isPlayer())
			return false;

		if(isFlagPole(activeChar.getTarget()))
			return checkFortressCondition(activeChar, target, first);

		Player player = (Player) activeChar;
		if(player.getClan() == null || !player.isClanLeader())
		{
			activeChar.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addSkillName(this));
			return false;
		}

		CastleSiegeEvent siegeEvent = player.getEvent(CastleSiegeEvent.class);
		if(siegeEvent == null)
		{
			activeChar.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addSkillName(this));
			return false;
		}

		CastleSiegeEvent siegeEvent2 = target.getEvent(CastleSiegeEvent.class);
		if(siegeEvent2 != siegeEvent)
		{
			activeChar.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addSkillName(this));
			return false;
		}

		if(siegeEvent.getSiegeClan(CastleSiegeEvent.ATTACKERS, player.getClan()) == null)
		{
			activeChar.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addSkillName(this));
			return false;
		}

		if(player.isMounted())
		{
			activeChar.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addSkillName(this));
			return false;
		}

		if(!player.isInRangeZ(target, 185L) || player.getZDeltaSq(target.getZ()) > 2500L)
		{
			player.sendPacket((IStaticPacket) SystemMsg.YOUR_TARGET_IS_OUT_OF_RANGE);
			return false;
		}

		if(first)
		{
			siegeEvent.broadcastTo(SystemMsg.THE_OPPOSING_CLAN_HAS_STARTED_TO_ENGRAVE_THE_HOLY_ARTIFACT, CastleSiegeEvent.DEFENDERS);
			String message = "TakeCastle: caster: " + activeChar.getName() + ", loc:" + activeChar.getLoc() + ", castle: " + siegeEvent.getName() + ", target: " + target;
			_log.debug(message);
			GmListTable.broadcastMessageToGMs(message);
		}

		return true;
	}

	@Override
	public void useSkill(Creature activeChar, List<Creature> targets)
	{
		if(isFlagPole(activeChar.getTarget()))
		{
			useFortressSkill(activeChar);
			return;
		}

		for(Creature target : targets)
		{
			CastleSiegeEvent siegeEvent;
			Player player;
			if(target == null || !target.isArtefact() || !activeChar.isInRangeZ(target, 185L) || activeChar.getZDeltaSq(target.getZ()) > 2500L || (siegeEvent = (player = (Player) activeChar).getEvent(CastleSiegeEvent.class)) == null)
				continue;
			siegeEvent.broadcastTo((L2GameServerPacket) new SystemMessage(SystemMsg.CLAN_S1_HAS_SUCCESSFULLY_ENGRAVED_THE_HOLY_ARTIFACT).addString(player.getClan().getName()), CastleSiegeEvent.ATTACKERS, CastleSiegeEvent.DEFENDERS);
			siegeEvent.processStep(player.getClan());
		}
	}

	private static boolean isFlagPole(GameObject target)
	{
		// the retail type-3 static object, or the visible npc pole this build
		// uses because the Classic client maps carry no flag pole prop
		return target instanceof StaticObjectInstance && ((StaticObjectInstance) target).getType() == 3 || target instanceof FlagPoleInstance;
	}

	/** Ported from the H5 TakeFortress.checkCondition. */
	private boolean checkFortressCondition(Creature activeChar, Creature target, boolean first)
	{
		GameObject flagPole = activeChar.getTarget();
		if(!isFlagPole(flagPole))
		{
			activeChar.sendPacket((IStaticPacket) SystemMsg.THE_TARGET_IS_NOT_A_FLAGPOLE_SO_A_FLAG_CANNOT_BE_DISPLAYED);
			return false;
		}

		if(first)
		{
			List<Creature> around = World.getAroundCharacters(flagPole, getCastRange() * 2, 100);
			for(Creature ch : around)
			{
				if(ch != activeChar && ch.isCastingNow() && ch.getCastingSkill() == this)
				{
					activeChar.sendPacket((IStaticPacket) SystemMsg.A_FLAG_IS_ALREADY_BEING_DISPLAYED_ANOTHER_FLAG_CANNOT_BE_DISPLAYED);
					return false;
				}
			}
		}

		Player player = (Player) activeChar;
		if(player.getClan() == null)
		{
			activeChar.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addSkillName(this));
			return false;
		}

		FortressSiegeEvent siegeEvent = player.getEvent(FortressSiegeEvent.class);
		if(siegeEvent == null)
		{
			activeChar.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addSkillName(this));
			return false;
		}

		if(player.isMounted())
		{
			activeChar.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addSkillName(this));
			return false;
		}

		ItemAttachment attach = player.getActiveWeaponFlagAttachment();
		if(!(attach instanceof FortressCombatFlagObject) || ((FortressCombatFlagObject) attach).getEvent() != siegeEvent)
		{
			activeChar.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addSkillName(this));
			return false;
		}

		if(!player.isInRangeZ(flagPole, getCastRange()))
		{
			player.sendPacket((IStaticPacket) SystemMsg.YOUR_TARGET_IS_OUT_OF_RANGE);
			return false;
		}

		if(first)
			siegeEvent.broadcastTo(new SystemMessage(SystemMsg.S1_CLAN_IS_TRYING_TO_DISPLAY_A_FLAG).addString(player.getClan().getName()), CastleSiegeEvent.DEFENDERS);

		return true;
	}

	/** Ported from the H5 TakeFortress.useSkill. */
	private void useFortressSkill(Creature activeChar)
	{
		GameObject flagPole = activeChar.getTarget();
		if(!isFlagPole(flagPole))
			return;

		Player player = (Player) activeChar;
		FortressSiegeEvent siegeEvent = player.getEvent(FortressSiegeEvent.class);
		if(siegeEvent == null)
			return;

		if(flagPole instanceof FlagPoleInstance)
		{
			Fortress fortress = ((FlagPoleInstance) flagPole).getFortress();
			if(fortress == null || fortress != siegeEvent.getResidence())
				return;
		}
		else
		{
			StaticObjectObject object = siegeEvent.getFirstObject(FortressSiegeEvent.FLAG_POLE);
			if(object == null || ((StaticObjectInstance) flagPole).getUId() != object.getUId())
				return;
		}

		Log.add("TakeCastle[fortress]: caster: " + player.getName() + ", clan: " + player.getClan().getName() + ", fortress: " + siegeEvent.getName(), "fortress");
		siegeEvent.processStep(player.getClan());
	}
}
