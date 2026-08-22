/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.data.xml.AbstractFileParser
 *  l2.commons.data.xml.AbstractHolder
 *  l2.gameserver.Config
 *  org.dom4j.Element
 */
package l2.gameserver.data.xml.parser;

import java.io.File;
import java.util.ArrayList;

import org.dom4j.Element;

import l2.commons.data.xml.AbstractFileParser;
import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.NormalUpgradeHolder;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeData;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeEntity;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeMaterial;

public class NormalUpgradeSystemParse
        extends AbstractFileParser<NormalUpgradeHolder> {
    protected static NormalUpgradeSystemParse instance = new NormalUpgradeSystemParse();

    protected NormalUpgradeSystemParse() {
        super(NormalUpgradeHolder.getInstance());
    }

    public static NormalUpgradeSystemParse getInstance() {
        return instance;
    }

    protected void readData(Element rootElement) {
        for (Element element : rootElement.elements("equipment_upgrade")) {
            int id = Integer.parseInt(element.attributeValue("id"));
            int requiredId = Integer.parseInt(element.attributeValue("item"));
            int requiredEnchant = Integer.parseInt(element.attributeValue("enchant"));
            long commission = Long.parseLong(element.attributeValue("commission"));
            int chance = Integer.parseInt(element.attributeValue("chance"));
            ArrayList<NormalUpgradeMaterial> materials = new ArrayList<NormalUpgradeMaterial>();
            ArrayList<NormalUpgradeData> successList = new ArrayList<NormalUpgradeData>();
            ArrayList<NormalUpgradeData> failList = new ArrayList<NormalUpgradeData>();
            Element failElement = element.element("fail");
            if (failElement != null) {
                for (Element failItem2 : failElement.elements("item")) {
                    String failIdStr = failItem2.attributeValue("id");
                    String failEnchantStr = failItem2.attributeValue("enchant");
                    String failQuantityStr = failItem2.attributeValue("quantity");
                    if (failIdStr == null || failQuantityStr == null)
                        continue;
                    int failId = Integer.parseInt(failIdStr);
                    int failEnchant = failEnchantStr != null ? Integer.parseInt(failEnchantStr) : 0;
                    long failQuantity = Long.parseLong(failQuantityStr);
                    NormalUpgradeData normalUpgradeFail = new NormalUpgradeData(failId, failEnchant, failQuantity);
                    failList.add(normalUpgradeFail);
                }
            }
            Element successElement = element.element("success");
            for (Element successItem : successElement.elements("item")) {
                String successIdStr = successItem.attributeValue("id");
                String successEnchantStr = successItem.attributeValue("enchant");
                String successQuantityStr = successItem.attributeValue("quantity");
                int successId = Integer.parseInt(successIdStr);
                int successEnchant = successEnchantStr != null ? Integer.parseInt(successEnchantStr) : 0;
                long successQuantity = Long.parseLong(successQuantityStr);
                NormalUpgradeData normalUpgradeSuccess = new NormalUpgradeData(successId, successEnchant,
                        successQuantity);
                successList.add(normalUpgradeSuccess);
            }
            Element materialsElement = element.element("materials");
            for (Element materialItem : materialsElement.elements("item")) {
                String materialIdStr = materialItem.attributeValue("id");
                String materialQuantityStr = materialItem.attributeValue("quantity");
                int materialId = Integer.parseInt(materialIdStr);
                long materialQuantity = Long.parseLong(materialQuantityStr);
                NormalUpgradeMaterial material = new NormalUpgradeMaterial(materialId, materialQuantity);
                materials.add(material);
            }
            NormalUpgradeEntity entity = new NormalUpgradeEntity(id, requiredId, requiredEnchant, commission, chance,
                    materials, successList, failList);
            Element bonusElements = element.element("bonus");
            if (bonusElements != null) {
                String bonusStr = bonusElements.attributeValue("chance");
                int bonus = bonusStr == null ? 0 : Integer.parseInt(bonusStr);
                entity.setBonus(bonus);
                for (Element bonusElement : bonusElements.elements("item")) {
                    int bonusId = Integer.parseInt(bonusElement.attributeValue("id"));
                    long bonusQuantity = Long.parseLong(bonusElement.attributeValue("quantity"));
                    String bonusEnchantStr = bonusElement.attributeValue("enchant");
                    int bonusEnchant = bonusEnchantStr == null ? 0 : Integer.parseInt(bonusEnchantStr);
                    NormalUpgradeData data = new NormalUpgradeData(bonusId, bonusEnchant, bonusQuantity);
                    entity.addBonusReward(data);
                }
            }
            NormalUpgradeHolder.getInstance().addData(id, entity);
        }
    }

    public File getXMLFile() {
        return new File(Config.DATAPACK_ROOT, "data/upgrade/upgrade_normal_system.xml");
    }

    public String getDTDFileName() {
        return "";
    }
}
