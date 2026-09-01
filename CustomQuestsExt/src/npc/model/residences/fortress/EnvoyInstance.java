package npc.model.residences.fortress;

import l2.commons.dao.JdbcEntityState;
import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import l2.gameserver.model.entity.residence.Castle;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.entity.residence.Residence;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Castle envoy spawned in a freshly captured fortress: the new owner's leader
 * chooses between independence and a contract with the related castle.
 * Ported from the H5 fortress EnvoyInstance.
 */
public class EnvoyInstance extends NpcInstance
{
	public static final int COND_LEADER = 0;
	public static final int COND_FAIL = 1;

	private final int _castleId;
	private final String _mainDialog;
	private final String _failDialog;
	private final String _successContractDialog;
	private final String _successIndependentDialog;
	private final String _failContractDialog;

	public EnvoyInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
		_castleId = template.getAIParams().getInteger("castle_id");
		_mainDialog = template.getAIParams().getString("main_dialog");
		_failDialog = template.getAIParams().getString("fail_dialog");
		_successContractDialog = template.getAIParams().getString("success_contract_dialog");
		_successIndependentDialog = template.getAIParams().getString("success_independent_dialog");
		_failContractDialog = template.getAIParams().getString("fail_contract_dialog");
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;

		int cond = getCond(player);
		switch(cond)
		{
			case COND_LEADER:
				final int castleId;
				final int state;
				final String fileName;
				if(command.equalsIgnoreCase("yes"))
				{
					Residence castle = ResidenceHolder.getInstance().getResidence(Castle.class, _castleId);
					if(castle == null || castle.getOwnerId() == 0)
					{
						castleId = -1;
						state = Fortress.NOT_DECIDED;
						fileName = _failContractDialog;
					}
					else
					{
						castleId = castle.getId();
						state = Fortress.CONTRACT_WITH_CASTLE;
						fileName = _successContractDialog;
					}
				}
				else
				{
					castleId = 0;
					state = Fortress.INDEPENDENT;
					fileName = _successIndependentDialog;
				}
				if(state != Fortress.NOT_DECIDED)
				{
					Fortress fortress = FortressUtils.getFortress(this);
					fortress.setFortState(state, castleId);
					fortress.setJdbcState(JdbcEntityState.UPDATED);
					fortress.update();
					FortressSiegeEvent event = fortress.getSiegeEvent();
					if(event != null)
						event.despawnEnvoy();
				}
				player.sendPacket(new NpcHtmlMessage(player, this, fileName, 0));
				break;
			case COND_FAIL:
				player.sendPacket(new NpcHtmlMessage(player, this, _failDialog, 0));
				break;
		}
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
		String filename = null;
		int cond = getCond(player);
		switch(cond)
		{
			case COND_LEADER:
				filename = _mainDialog;
				break;
			case COND_FAIL:
				filename = _failDialog;
				break;
		}
		player.sendPacket(new NpcHtmlMessage(player, this, filename, val));
	}

	protected int getCond(Player player)
	{
		Residence residence = FortressUtils.getFortress(this);
		if(residence == null)
			return COND_FAIL;
		Clan residenceOwner = residence.getOwner();
		if(residenceOwner != null && player.getClan() == residenceOwner && residenceOwner.getLeaderId() == player.getObjectId())
			return COND_LEADER;
		return COND_FAIL;
	}
}
