package events.AdventureHelper;

import l2.gameserver.instancemanager.SpawnManager;
import l2.gameserver.model.Player;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdventureHelper extends Functions implements ScriptFile {

    private static final Logger _log = LoggerFactory.getLogger(AdventureHelper.class);
    private static final String SPAWN_GROUP = "[test_2nd_class]";

    public AdventureHelper() {
        super();
    }

    public void startEvent() {
        Player player = getSelf();
        
        if (!player.getPlayerAccess().IsEventGm) {
            return;
        }

        if (SetActive("AdventureHelper", true)) {
            spawnEventManagers();
            _log.info("Event 'Adventure Helper' started.");
            player.sendMessage("Event 'Adventure Helper' already started.");
            
            if (player.isLangRus()) {
                player.sendMessage("В стартовых городах появились НПЦ Miss Queen и новички могут получать оружие");
            } else {
                player.sendMessage("The Miss Queen NPC appeared in the starting cities and Newbies can receiving weapons.");
            }
        } else {
            player.sendMessage("Event 'Adventure Helper' already started.");
        }
        
        show("admin/events/events.htm", player);
    }

    public void stopEvent() {
        Player player = getSelf();
        
        if (!player.getPlayerAccess().IsEventGm) {
            return;
        }

        if (SetActive("AdventureHelper", false)) {
            unSpawnEventManagers();
            _log.info("Event 'Adventure Helper' stopped.");
            
            if (player.isLangRus()) {
                player.sendMessage("NPC Miss Queen исчезли");
            } else {
                player.sendMessage("NPC Miss Queen disappeared.");
            }
        } else {
            _log.info("Event 'Adventure Helper' not started.");
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
        return IsActive("AdventureHelper");
    }

    @Override
    public void onLoad() {
        if (isActive()) {
            spawnEventManagers();
            _log.info("Loaded Event: Adventure Helper [state: activated]");
        } else {
            _log.info("Loaded Event: Adventure Helper [state: deactivated]");
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
