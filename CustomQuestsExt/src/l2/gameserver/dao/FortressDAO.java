package l2.gameserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.dao.JdbcEntityState;
import l2.commons.dbutils.DbUtils;
import l2.gameserver.database.DatabaseFactory;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.tables.ClanTable;

/**
 * Persistence for Fortress residences, recreated from the L2Scripts High Five
 * FortressDAO (rev 2268) with two adaptations for this core:
 * - owner_clan_id column: fortress ownership lives on the fortress row, because this
 *   core's clan_data has no hasFortress column and Clan has no fortress accessors.
 * - the table is created on first use, so the extension works without a manual
 *   sql/install step (the install script is still provided for reference).
 */
public class FortressDAO
{
	private static final Logger _log = LoggerFactory.getLogger(FortressDAO.class);
	private static final FortressDAO _instance = new FortressDAO();

	public static final String CREATE_SQL_QUERY = "CREATE TABLE IF NOT EXISTS `fortress` (" //
			+ "`id` smallint unsigned NOT NULL DEFAULT '0'," //
			+ "`name` varchar(45) NOT NULL," //
			+ "`owner_clan_id` int NOT NULL DEFAULT '0'," //
			+ "`state` tinyint unsigned NOT NULL DEFAULT '0'," //
			+ "`castle_id` int NOT NULL DEFAULT '0'," //
			+ "`last_siege_date` bigint NOT NULL DEFAULT '0'," //
			+ "`own_date` bigint NOT NULL DEFAULT '0'," //
			+ "`siege_date` bigint NOT NULL DEFAULT '0'," //
			+ "`supply_count` bigint NOT NULL DEFAULT '0'," //
			+ "`facility_0` int NOT NULL DEFAULT '0'," //
			+ "`facility_1` int NOT NULL DEFAULT '0'," //
			+ "`facility_2` int NOT NULL DEFAULT '0'," //
			+ "`facility_3` int NOT NULL DEFAULT '0'," //
			+ "`facility_4` int NOT NULL DEFAULT '0'," //
			+ "`cycle` int NOT NULL DEFAULT '0'," //
			+ "`reward_count` int NOT NULL DEFAULT '0'," //
			+ "`paid_cycle` int NOT NULL DEFAULT '0'," //
			+ "`supply_spawn` bigint NOT NULL DEFAULT '0'," //
			+ "PRIMARY KEY (`id`))";
	public static final String SELECT_SQL_QUERY = "SELECT * FROM fortress WHERE id = ?";
	public static final String SELECT_OWNER_SQL_QUERY = "SELECT owner_clan_id FROM fortress WHERE id = ? LIMIT 1";
	public static final String REPLACE_SQL_QUERY = "REPLACE INTO fortress (id, name, owner_clan_id, state, castle_id, last_siege_date, own_date, siege_date, supply_count, facility_0, facility_1, facility_2, facility_3, facility_4, cycle, reward_count, paid_cycle, supply_spawn) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private boolean _tableChecked = false;

	/**
	 * The Combat Flag (9819) exists only while a fortress siege is running; a
	 * server restart mid-siege ends the siege but leaves an equipped flag in the
	 * carrier's persisted inventory as an ordinary weapon. Sieges never survive a
	 * restart on this core, so every 9819 row at boot is stale - wipe them.
	 */
	public void deleteStrayCombatFlags()
	{
		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("DELETE FROM items WHERE item_id = 9819");
			int deleted = statement.executeUpdate();
			if(deleted > 0)
				_log.info("FortressDAO: removed " + deleted + " stray Combat Flag(s) from player inventories.");
		}
		catch(Exception e)
		{
			_log.error("FortressDAO: could not clean stray Combat Flags", e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
	}

	public static FortressDAO getInstance()
	{
		return _instance;
	}

	private synchronized void checkTable()
	{
		if(_tableChecked)
			return;
		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement(CREATE_SQL_QUERY);
			statement.execute();
			_tableChecked = true;
		}
		catch(Exception e)
		{
			_log.error("FortressDAO.checkTable():" + e, e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
	}

	/**
	 * Resolves the owning clan from fortress.owner_clan_id, mirroring how
	 * ClanDataDAO.getOwner() serves Castle/ClanHall. Called from Fortress.loadData().
	 */
	public Clan getOwner(Fortress fortress)
	{
		checkTable();

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement(SELECT_OWNER_SQL_QUERY);
			statement.setInt(1, fortress.getId());
			rset = statement.executeQuery();
			if(rset.next())
			{
				int ownerId = rset.getInt("owner_clan_id");
				if(ownerId > 0)
				{
					Clan owner = ClanTable.getInstance().getClan(ownerId);
					if(owner == null)
						_log.warn("FortressDAO: fortress " + fortress.getId() + " owner clan " + ownerId + " no longer exists.");
					return owner;
				}
			}
		}
		catch(Exception e)
		{
			_log.error("FortressDAO.getOwner(Fortress):" + e, e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement, rset);
		}
		return null;
	}

	public void select(Fortress fortress)
	{
		checkTable();

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement(SELECT_SQL_QUERY);
			statement.setInt(1, fortress.getId());
			rset = statement.executeQuery();
			if(rset.next())
			{
				fortress.setFortState(rset.getInt("state"), rset.getInt("castle_id"));
				fortress.setCycle(rset.getInt("cycle"));
				fortress.setRewardCount(rset.getInt("reward_count"));
				fortress.setPaidCycle(rset.getInt("paid_cycle"));
				fortress.setSupplyCount(rset.getInt("supply_count"));
				fortress.setSupplySpawn(rset.getLong("supply_spawn"));
				fortress.getSiegeDate().setTimeInMillis(rset.getLong("siege_date"));
				fortress.getLastSiegeDate().setTimeInMillis(rset.getLong("last_siege_date"));
				fortress.getOwnDate().setTimeInMillis(rset.getLong("own_date"));
				for(int i = 0; i < Fortress.FACILITY_MAX; i++)
					fortress.setFacilityLevel(i, rset.getInt("facility_" + i));
			}
		}
		catch(Exception e)
		{
			_log.error("FortressDAO.select(Fortress):" + e, e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement, rset);
		}
	}

	public void update(Fortress fortress)
	{
		if(!fortress.getJdbcState().isUpdatable())
			return;

		fortress.setJdbcState(JdbcEntityState.STORED);
		update0(fortress);
	}

	private void update0(Fortress fortress)
	{
		checkTable();

		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement(REPLACE_SQL_QUERY);

			int i = 0;
			statement.setInt(++i, fortress.getId());
			statement.setString(++i, fortress.getName());
			statement.setInt(++i, fortress.getOwnerId());
			statement.setInt(++i, fortress.getContractState());
			statement.setInt(++i, fortress.getCastleId());
			statement.setLong(++i, fortress.getLastSiegeDate().getTimeInMillis());
			statement.setLong(++i, fortress.getOwnDate().getTimeInMillis());
			statement.setLong(++i, fortress.getSiegeDate().getTimeInMillis());
			statement.setInt(++i, fortress.getSupplyCount());
			statement.setInt(++i, fortress.getFacilityLevel(0));
			statement.setInt(++i, fortress.getFacilityLevel(1));
			statement.setInt(++i, fortress.getFacilityLevel(2));
			statement.setInt(++i, fortress.getFacilityLevel(3));
			statement.setInt(++i, fortress.getFacilityLevel(4));
			statement.setInt(++i, fortress.getCycle());
			statement.setInt(++i, fortress.getRewardCount());
			statement.setInt(++i, fortress.getPaidCycle());
			statement.setLong(++i, fortress.getSupplySpawn());
			statement.execute();
		}
		catch(Exception e)
		{
			_log.warn("FortressDAO#update0(Fortress): " + e, e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
	}
}
