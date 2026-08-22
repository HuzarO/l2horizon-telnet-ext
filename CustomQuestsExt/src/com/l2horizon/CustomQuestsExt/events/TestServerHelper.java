package com.l2horizon.CustomQuestsExt.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.gameserver.instancemanager.SpawnManager;
import l2.gameserver.model.Player;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;

public class TestServerHelper extends Functions implements ScriptFile {

	private static final Logger logger = LoggerFactory.getLogger(TestServerHelper.class);
	private static final String SPAWN_GROUP = "[test_2nd_class]";

	public TestServerHelper() {
		super();
	}

	public void startEvent() {
		Player player = getSelf();

		if (!player.getPlayerAccess().IsEventGm) {
			return;
		}

		if (SetActive("TestServerHelper", true)) {
			spawnEventManagers();
			logger.info("Event 'Test Server Helper' started.");
			player.sendMessage("Event 'Test Server Helper' already started.");

			player.sendMessage("The Miss Queen NPC appeared in the starting cities and Newbies can receiving weapons.");
		} else {
			player.sendMessage("Event 'Test Server Helper' already started.");
		}

		show("admin/events/events.htm", player);
	}

	public void stopEvent() {
		Player player = getSelf();

		if (!player.getPlayerAccess().IsEventGm) {
			return;
		}

		if (SetActive("TestServerHelper", false)) {
			unSpawnEventManagers();
			logger.info("Event 'Test Server Helper' stopped.");

			player.sendMessage("NPC Miss Queen disappeared.");
		} else {
			logger.info("Event 'Test Server Helper' not started.");
		}

		show("admin/events/events.htm", player);
	}

	private void unSpawnEventManagers() {
		SpawnManager.getInstance().despawn(SPAWN_GROUP);
	}

	private void spawnEventManagers() {
		SpawnManager.getInstance().spawn(SPAWN_GROUP);
	}

	private static boolean isActive() {
		return IsActive("TestServerHelper");
	}

	@Override
	public void onLoad() {
		if (isActive()) {
			spawnEventManagers();
			logger.info("Loaded Event: Test Server Helper [state: activated]");
		} else {
			logger.info("Loaded Event: Test Server Helper [state: deactivated]");
		}
	}

	@Override
	public void onReload() {
		unSpawnEventManagers();
	}

	@Override
	public void onShutdown() {
		unSpawnEventManagers();
	}
}
