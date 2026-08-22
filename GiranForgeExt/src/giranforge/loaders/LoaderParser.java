/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.handler.admincommands.AdminCommandHandler
 *  l2.gameserver.handler.admincommands.IAdminCommandHandler
 *  l2.gameserver.scripts.ScriptFile
 */
package giranforge.loaders;

import giranforge.AdminReloadGf;
import giranforge.config.SkinConfig;
import l2.gameserver.data.xml.parser.NormalUpgradeSystemParse;
import l2.gameserver.data.xml.parser.SkinsAppearanceParse;
import l2.gameserver.data.xml.parser.gf.EssenceCountDownParser;
import l2.gameserver.handler.admincommands.AdminCommandHandler;
import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.scripts.ScriptFile;

public class LoaderParser
implements ScriptFile {
    public void onLoad() {
        SkinConfig.loadSkinConfig();
        SkinsAppearanceParse.getInstance().load();
        NormalUpgradeSystemParse.getInstance().load();
        EssenceCountDownParser.getInstance().load();
        AdminCommandHandler.getInstance().registerAdminCommandHandler((IAdminCommandHandler)new AdminReloadGf());
    }

    public void onReload() {
    }

    public void onShutdown() {
    }
}

