/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.handler.items.IItemHandler
 *  l2.gameserver.handler.items.ItemHandler
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package handler;

import l2.gameserver.handler.items.IItemHandler;
import l2.gameserver.handler.items.ItemHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyItemHandler {
    protected static final Logger _log = LoggerFactory.getLogger(LazyItemHandler.class);

    public static void register(IItemHandler handler) {
        ItemHandler.getInstance().registerItemHandler(handler);
        _log.info("ItemHandler: {} registered", (Object)handler.getClass().getSimpleName());
    }
}

