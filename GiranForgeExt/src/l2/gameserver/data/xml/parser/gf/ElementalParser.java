/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.data.xml.AbstractFileParser
 *  l2.commons.data.xml.AbstractHolder
 *  l2.gameserver.model.base.Element
 *  org.dom4j.Element
 */
package l2.gameserver.data.xml.parser.gf;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import org.dom4j.Element;

import l2.commons.data.xml.AbstractFileParser;
import l2.gameserver.data.xml.holder.gf.ElementalHolder;
import l2.gameserver.templates.item.support.elemental.ElementalStone;

public class ElementalParser
        extends AbstractFileParser<ElementalHolder> {
    protected static final ElementalParser instance = new ElementalParser();
    protected AtomicBoolean isLoaded = new AtomicBoolean(false);

    protected ElementalParser() {
        super(ElementalHolder.getInstance());
    }

    public static ElementalParser getInstance() {
        return instance;
    }

    public void load() {
        if (this.isLoaded.compareAndSet(false, true)) {
            super.load();
        }
    }

    public File getXMLFile() {
        return new File("data/elemental/stones.xml");
    }

    public String getDTDFileName() {
        return "";
    }

    protected void readData(Element root) throws Exception {
        for (Element element : root.elements("attribute_stone")) {
            int id = Integer.parseInt(element.attributeValue("id"));
            int chance = Integer.parseInt(element.attributeValue("chance"));
            int increase = Integer.parseInt(element.attributeValue("increase"));
            String typeString = element.attributeValue("element");
            l2.gameserver.model.base.Element type = this.getType(typeString);
            int maxWeapon = Integer.parseInt(element.attributeValue("max_enchant_weapon"));
            int maxArmor = Integer.parseInt(element.attributeValue("max_enchant_armor"));
            ElementalStone elementalStone = new ElementalStone(id, chance, increase, type, maxWeapon, maxArmor);
            ElementalHolder.getInstance().addData(id, elementalStone);
        }
    }

    public l2.gameserver.model.base.Element getType(String typeString) {
        return switch (typeString) {
            case "FIRE" -> l2.gameserver.model.base.Element.FIRE;
            case "WATER" -> l2.gameserver.model.base.Element.WATER;
            case "EARTH" -> l2.gameserver.model.base.Element.EARTH;
            case "WIND" -> l2.gameserver.model.base.Element.WIND;
            case "UNHOLY" -> l2.gameserver.model.base.Element.UNHOLY;
            case "HOLY" -> l2.gameserver.model.base.Element.HOLY;
            default -> null;
        };
    }
}
