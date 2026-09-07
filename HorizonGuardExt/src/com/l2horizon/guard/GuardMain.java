package com.l2horizon.guard;

import java.nio.file.Path;

/** Bootstrap checks the patched classes and config before Lucera opens its listeners. */
public final class GuardMain {
    public static void main(String[] args) throws Exception {
        Path config = Path.of(System.getProperty("horizon.guard.config", "config/guard.properties"));
        if (args.length == 1 && args[0].equals("--check-config")) { GuardHooks.check(config); return; }
        GuardHooks.start(config);
        Runtime.getRuntime().addShutdownHook(new Thread(GuardHooks::shutdown, "guard-shutdown"));
        try { l2.gameserver.GameServer.main(args); }
        catch (Exception | Error e) { GuardHooks.shutdown(); throw e; }
    }
}
