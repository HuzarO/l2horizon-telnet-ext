/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.data.xml.AbstractFileParser
 *  l2.commons.data.xml.AbstractHolder
 *  l2.gameserver.templates.item.support.Grade
 *  org.dom4j.Element
 */
package l2.gameserver.data.xml.parser;

import java.io.File;

import org.dom4j.Element;

import l2.commons.data.xml.AbstractFileParser;
import l2.gameserver.data.xml.holder.AppearanceHolder;
import l2.gameserver.entity.AppearanceEntity;
import l2.gameserver.model.item.AppearanceTargetType;
import l2.gameserver.model.item.AppearanceType;
import l2.gameserver.templates.item.support.Grade;

public class AppearanceParse
        extends AbstractFileParser<AppearanceHolder> {
    protected static AppearanceParse instance = new AppearanceParse();

    protected AppearanceParse() {
        super(AppearanceHolder.getInstance());
    }

    public static AppearanceParse getInstance() {
        return instance;
    }

    public File getXMLFile() {
        return new File("data/appearance/appearance_stones.xml");
    }

    public String getDTDFileName() {
        return "";
    }

    public void load() {
        super.load();
    }

    protected void readData(Element root) throws Exception {
        for (Element element : root.elements("stone")) {
            AppearanceTargetType targetType;
            int id = Integer.parseInt(element.attributeValue("id"));
            long commission = Long.parseLong(element.attributeValue("commission"));
            String crystal = element.attributeValue("crystal_type");
            String typeStr = element.attributeValue("type");
            boolean refund = true;
            Grade crystalGrade = null;
            if (crystal != null) {
                crystalGrade = switch (crystal = crystal.toLowerCase()) {
                    case "none" -> Grade.NONE;
                    case "d" -> Grade.D_GRADE;
                    case "c" -> Grade.C_GRADE;
                    case "b" -> Grade.B_GRADE;
                    case "a" -> Grade.A_GRADE;
                    case "s" -> Grade.S_GRADE;
                    default -> null;
                };
            }
            AppearanceType type = switch (typeStr) {
                case "weapon" -> {
                    targetType = AppearanceTargetType.WEAPON;
                    yield AppearanceType.NORMAL;
                }
                case "armor" -> {
                    targetType = AppearanceTargetType.ARMOR;
                    yield AppearanceType.NORMAL;
                }
                case "restore" -> {
                    targetType = AppearanceTargetType.NONE;
                    yield AppearanceType.RESTORE;
                }
                default -> {
                    targetType = null;
                    yield null;
                }
            };
            if (typeStr.equals("restore") && element.attributeValue("refund") != null) {
                refund = Boolean.parseBoolean(element.attributeValue("refund"));
            }
            if (targetType == null)
                continue;
            AppearanceEntity entity = new AppearanceEntity(id, commission, crystalGrade, targetType, type, refund);
            AppearanceHolder.getInstance().addData(entity);
        }
    }
}
