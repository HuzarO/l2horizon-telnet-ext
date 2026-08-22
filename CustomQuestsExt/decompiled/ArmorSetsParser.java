package l2.gameserver.data.xml.parser;

import l2.commons.data.xml.AbstractFileParser;
import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.ArmorSetsHolder;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.model.ArmorSet;
import l2.gameserver.model.Skill;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.templates.item.ItemTemplate;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.io.File;
import java.util.*;

/**
 * Parser for armor sets configuration from XML.
 * Handles armor set definitions including pieces, skills, and enchant bonuses.
 */
public final class ArmorSetsParser extends AbstractFileParser<ArmorSetsHolder> {
    
    private static final ArmorSetsParser INSTANCE = new ArmorSetsParser();
    
    public static ArmorSetsParser getInstance() {
        return INSTANCE;
    }
    
    private ArmorSetsParser() {
        super(ArmorSetsHolder.getInstance());
    }
    
    @Override
    public File getXMLFile() {
        return new File(Config.DATAPACK_ROOT, "data/armor_sets.xml");
    }
    
    @Override
    public String getDTDFileName() {
        return "armor_sets.dtd";
    }
    
    /**
     * Parses a semicolon-separated list of item IDs into a set of ItemTemplates.
     * 
     * @param itemIds Semicolon-separated item IDs (e.g., "1234;5678;9012")
     * @return Set of ItemTemplate objects
     */
    private Set<ItemTemplate> parseItemIds(String itemIds) {
        Set<ItemTemplate> items = new HashSet<>(1);
        StringTokenizer st = new StringTokenizer(itemIds, ";");
        
        while (st.hasMoreTokens()) {
            String itemIdStr = StringUtils.trimToEmpty(st.nextToken());
            ItemTemplate template = ItemHolder.getInstance().getTemplate(Integer.parseInt(itemIdStr));
            items.add(template);
        }
        
        return items;
    }
    
    /**
     * Parses a semicolon-separated list of skills into a set of Skill objects.
     * Format: "skillId-level;skillId-level;..."
     * 
     * @param skillsStr Semicolon-separated skills (e.g., "1234-5;6789-10")
     * @return Set of Skill objects
     */
    private Set<Skill> parseSkills(String skillsStr) {
        Set<Skill> skills = new HashSet<>(1);
        StringTokenizer st = new StringTokenizer(skillsStr, ";");
        
        while (st.hasMoreTokens()) {
            String skillStr = StringUtils.trimToEmpty(st.nextToken());
            
            if (skillStr.isEmpty()) {
                continue;
            }
            
            // Find the separator between skill ID and level
            int separatorIndex = skillStr.indexOf('-');
            
            if (separatorIndex < 1) {
                _log.warn("Unknown skill: " + skillStr);
                continue;
            }
            
            // Parse skill ID and level
            int skillId = Integer.parseInt(skillStr.substring(0, separatorIndex));
            int skillLevel = Integer.parseInt(skillStr.substring(separatorIndex + 1));
            
            Skill skill = SkillTable.getInstance().getInfo(skillId, skillLevel);
            skills.add(skill);
        }
        
        return skills;
    }
    
    /**
     * Helper method to add items to a set from an XML element attribute.
     * 
     * @param element The XML element
     * @param itemsSet The set to add items to
     * @param attributeName The attribute name to read
     */
    private void addItemsFromAttribute(Element element, Set<ItemTemplate> itemsSet, String attributeName) {
        String attributeValue = element.attributeValue(attributeName);
        
        if (attributeValue != null) {
            itemsSet.addAll(parseItemIds(attributeValue));
        }
    }
    
    @Override
    protected void readData(Element rootElement) throws Exception {
        Iterator<Element> setIterator = rootElement.elementIterator("set");
        
        while (setIterator.hasNext()) {
            Element setElement = setIterator.next();
            
            // Map of armor slots to item templates
            // Key: slot ID (0=underwear, 1=head, 6=chest, 10=gloves, 11=legs, 12=feet)
            Map<Integer, Set<ItemTemplate>> armorPieces = new HashMap<>();
            
            // Skills and shield items
            Set<Skill> skills = Collections.emptySet();
            Set<ItemTemplate> shieldItems = Collections.emptySet();
            Set<Skill> shieldSkills = Collections.emptySet();
            
            // Enchant level skills (enchant1skills through enchant128skills)
            Map<Integer, Set<Skill>> enchantSkills = new HashMap<>();
            
            // Parts skills (parts1skills through parts7skills)
            Map<Integer, Set<Skill>> partsSkills = new LinkedHashMap<>();
            
            // Parse set ID
            int setId = Integer.parseInt(setElement.attributeValue("id"));
            
            // Parse armor pieces for each slot
            // Chest (slot 6)
            addItemsFromAttribute(
                setElement,
                armorPieces.computeIfAbsent(6, k -> new HashSet<>()),
                "chest"
            );
            
            // Legs (slot 11)
            addItemsFromAttribute(
                setElement,
                armorPieces.computeIfAbsent(11, k -> new HashSet<>()),
                "legs"
            );
            
            // Head (slot 1)
            addItemsFromAttribute(
                setElement,
                armorPieces.computeIfAbsent(1, k -> new HashSet<>()),
                "head"
            );
            
            // Gloves (slot 10)
            addItemsFromAttribute(
                setElement,
                armorPieces.computeIfAbsent(10, k -> new HashSet<>()),
                "gloves"
            );
            
            // Feet (slot 12)
            addItemsFromAttribute(
                setElement,
                armorPieces.computeIfAbsent(12, k -> new HashSet<>()),
                "feet"
            );
            
            // Underwear (slot 0)
            addItemsFromAttribute(
                setElement,
                armorPieces.computeIfAbsent(0, k -> new HashSet<>()),
                "underwear"
            );
            
            // Parse shield items
            String shieldAttr = setElement.attributeValue("shield");
            if (shieldAttr != null) {
                shieldItems = parseItemIds(shieldAttr);
            }
            
            // Parse skills
            String skillsAttr = setElement.attributeValue("skills");
            if (skillsAttr != null) {
                skills = parseSkills(skillsAttr);
            }
            
            // Parse shield skills
            String shieldSkillsAttr = setElement.attributeValue("shield_skills");
            if (shieldSkillsAttr != null) {
                shieldSkills = parseSkills(shieldSkillsAttr);
            }
            
            // Parse enchant level skills (enchant1skills through enchant128skills)
            for (int enchantLevel = 1; enchantLevel < 128; enchantLevel++) {
                String enchantSkillsAttr = setElement.attributeValue(
                    String.format("enchant%dskills", enchantLevel)
                );
                
                if (enchantSkillsAttr != null) {
                    enchantSkills.put(enchantLevel, parseSkills(enchantSkillsAttr));
                }
            }
            
            // Parse parts skills (parts1skills through parts7skills)
            for (int partsCount = 1; partsCount <= 7; partsCount++) {
                String partsSkillsAttr = setElement.attributeValue(
                    String.format("parts%dskills", partsCount)
                );
                
                if (partsSkillsAttr != null) {
                    partsSkills.put(partsCount, parseSkills(partsSkillsAttr));
                }
            }
            
            // Create and add the armor set
            ArmorSet armorSet = new ArmorSet(
                setId,
                armorPieces,
                skills,
                shieldItems,
                shieldSkills,
                enchantSkills,
                partsSkills
            );
            
            getHolder().addArmorSet(armorSet);
        }
    }
}
