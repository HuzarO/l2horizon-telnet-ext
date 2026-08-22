/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.data.xml.AbstractFileParser
 *  l2.commons.data.xml.AbstractHolder
 *  l2.gameserver.data.xml.holder.ItemHolder
 *  l2.gameserver.templates.item.ItemTemplate
 *  org.dom4j.Element
 */
package l2.gameserver.data.xml.parser;

import java.io.File;

import org.dom4j.Element;

import l2.commons.data.xml.AbstractFileParser;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.data.xml.holder.SkinsHolder;
import l2.gameserver.entity.SkinEntity;
import l2.gameserver.templates.item.ItemTemplate;

public class SkinsAppearanceParse
        extends AbstractFileParser<SkinsHolder> {
    protected static SkinsAppearanceParse instance = new SkinsAppearanceParse();

    protected SkinsAppearanceParse() {
        super(SkinsHolder.getInstance());
    }

    public static SkinsAppearanceParse getInstance() {
        return instance;
    }

    public File getXMLFile() {
        return new File("data/appearance/appearance_skins.xml");
    }

    public String getDTDFileName() {
        return "";
    }

    public void load() {
        super.load();
    }

    protected void readData(Element root) throws Exception {
        for (Element element : root.elements("skin")) {
            int id = Integer.parseInt(element.attributeValue("id"));
            ItemTemplate item = ItemHolder.getInstance().getTemplate(id);
            if (item == null)
                continue;
            SkinsHolder.getInstance().addData(new SkinEntity(id));
        }
    }
}
