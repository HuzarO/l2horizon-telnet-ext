package l2.gameserver.model.entity.events.objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.gameserver.geodata.GeoEngine;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.entity.events.GlobalEvent;
import l2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.attachment.FlagItemAttachment;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Location;

/**
 * The fortress Combat Flag (item 9819) dropped at the flag pole when all barracks
 * fall. Ported from the L2Scripts High Five FortressCombatFlagObject, adapted to this
 * core's SpawnableObject/FlagItemAttachment contracts (GlobalEvent-typed callbacks,
 * additional onEnterPeace hook).
 */
public class FortressCombatFlagObject implements SpawnableObject, FlagItemAttachment
{
	private static final Logger _log = LoggerFactory.getLogger(FortressCombatFlagObject.class);
	public static final int ITEM_ID = 9819;

	private ItemInstance _item;
	private final Location _location;

	private GlobalEvent _event;

	public FortressCombatFlagObject(Location location)
	{
		_location = location;
	}

	@Override
	public void spawnObject(GlobalEvent event)
	{
		if(_item != null)
		{
			_log.info("FortressCombatFlagObject: can't spawn twice: " + event);
			return;
		}
		ItemInstance item = ItemFunctions.createItem(ITEM_ID);
		if(item == null)
		{
			_log.warn("FortressCombatFlagObject: item " + ITEM_ID + " does not exist, flag not spawned: " + event);
			return;
		}
		_item = item;
		_item.setAttachment(this);
		_item.dropMe(null, groundLocation());
		_item.setDropTime(0);

		_event = event;
	}

	@Override
	public void despawnObject(GlobalEvent event)
	{
		if(_item == null)
			return;

		Player owner = GameObjectsStorage.getPlayer(_item.getOwnerId());
		if(owner != null)
		{
			owner.getInventory().destroyItem(_item);
			owner.sendDisarmMessage(_item);
		}

		_item.setAttachment(null);
		_item.delete();

		_item.deleteMe();
		_item = null;

		_event = null;
	}

	@Override
	public void refreshObject(GlobalEvent event)
	{
	}

	@Override
	public void onLogout(Player player)
	{
		onDeath(player, null);
	}

	@Override
	public void onDeath(Player owner, Creature killer)
	{
		owner.getInventory().removeItem(_item);

		_item.setOwnerId(0);

		owner.sendPacket(new SystemMessage(SystemMsg.YOU_HAVE_DROPPED_S1).addItemName(_item.getItemId()));

		_item.dropMe(null, groundLocation());
		_item.setDropTime(0);
	}

	@Override
	public void onEnterPeace(Player player)
	{
		// the combat flag cannot be carried into a peace zone - it returns to the flag pole
		onDeath(player, null);
	}

	@Override
	public boolean canPickUp(Player player)
	{
		if(player.getActiveWeaponFlagAttachment() != null)
			return false;
		FortressSiegeEvent event = player.getEvent(FortressSiegeEvent.class);
		if(event == null || event != _event)
			return false;
		SiegeClanObject object = event.getSiegeClan(FortressSiegeEvent.ATTACKERS, player.getClan());
		if(object == null)
			return false;
		return true;
	}

	@Override
	public void pickUp(Player player)
	{
		player.getInventory().equipItem(_item);

		FortressSiegeEvent event = player.getEvent(FortressSiegeEvent.class);
		if(event != null)
			event.broadcastTo(new SystemMessage(SystemMsg.C1_HAS_ACQUIRED_THE_FLAG).addName(player), FortressSiegeEvent.ATTACKERS, FortressSiegeEvent.DEFENDERS);
	}

	@Override
	public boolean canAttack(Player player)
	{
		player.sendPacket(SystemMsg.THAT_WEAPON_CANNOT_PERFORM_ANY_ATTACKS);
		return false;
	}

	@Override
	public boolean canCast(Player player, Skill skill)
	{
		Skill[] skills = player.getActiveWeaponItem() == null ? null : player.getActiveWeaponItem().getAttachedSkills();
		if(skills == null || !org.apache.commons.lang3.ArrayUtils.contains(skills, skill))
		{
			player.sendPacket(SystemMsg.THAT_WEAPON_CANNOT_USE_ANY_OTHER_SKILL_EXCEPT_THE_WEAPONS_SKILL);
			return false;
		}
		return true;
	}

	@Override
	public void setItem(ItemInstance item)
	{
		// ignored
	}

	/**
	 * The H5 flag drop points sit on H5 terrain; snap to this pack's geodata so
	 * the flags lie on the Classic client's ground instead of floating.
	 */
	private Location groundLocation()
	{
		return _location.clone().setZ(GeoEngine.getHeight(_location.x, _location.y, _location.z, 0));
	}

	public GlobalEvent getEvent()
	{
		return _event;
	}

	public ItemInstance getItem()
	{
		return _item;
	}
}
