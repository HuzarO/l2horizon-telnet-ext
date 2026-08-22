package l2.gameserver.model;

import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.CharacterTemplateHolder;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.matching.MatchingRoom;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.network.l2.s2c.*;
import l2.gameserver.templates.PlayerTemplate;
import l2.gameserver.utils.ItemFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extracted setClassId method from Player class.
 * This method handles player class changes, including profession changes,
 * rewards, and all associated system updates.
 */
public class Player_setClassId {
    
    private static final Logger logger = LoggerFactory.getLogger(Player_setClassId.class);
    
    /**
     * Changes the player's class ID with optional validation and rewards.
     * 
     * @param player The player whose class is being changed
     * @param newClassId The new class ID to set
     * @param skipValidation If false, validates that the new class is a child of current class or admin rights are required
     * @param giveRewards If true, gives profession rewards (shadow weapons, social actions, etc.)
     */
    public synchronized void setClassId(Player player, int newClassId, boolean skipValidation, boolean giveRewards) {
        
        // Validation check - ensure class change is valid unless skipValidation is true or admin rights
        if (!skipValidation) {
            ClassId newClass = ClassId.VALUES[newClassId];
            ClassId currentClass = ClassId.VALUES[player.getActiveClassId()];
            
            // Check if new class is valid progression from current class
            if (!newClass.equalsOrChildOf(currentClass)) {
                // Check admin rights
                if (!player.getPlayerAccess().CanChangeClass && !Config.EVERYBODY_HAS_ADMIN_RIGHTS) {
                    Thread.dumpStack();
                    return;
                }
            }
        }
        
        // Check if the new class ID is not already a subclass
        boolean isNewMainClass = !player.getSubClasses().containsKey(newClassId);
        
        if (isNewMainClass) {
            // Get the active class (current class being changed)
            SubClass activeClass = player.getActiveClass();
            
            // Remove current class from subclasses map temporarily
            player.getSubClasses().remove(player.getActiveClassId());
            
            // Update class in database
            player.changeClassInDb(activeClass.getClassId(), newClassId);
            
            // If this is the base (main) class, handle profession bonuses
            if (activeClass.isBase()) {
                player.addClanPointsOnProfession(newClassId);
                
                ItemInstance rewardItem = null;
                ClassId newClassIdObj = ClassId.VALUES[newClassId];
                int classLevel = newClassIdObj.getLevel();
                
                // Level 2 profession (1st class change)
                if (classLevel == 2) {
                    if (giveRewards && Config.ALT_ALLOW_SHADOW_WEAPONS) {
                        rewardItem = ItemFunctions.createItem(8869); // Shadow weapon
                    }
                    
                    // Clear newbie quest variables
                    player.unsetVar("newbieweapon");
                    player.unsetVar("p1q2");
                    player.unsetVar("p1q3");
                    player.unsetVar("p1q4");
                    player.unsetVar("prof1");
                    player.unsetVar("ng1");
                    player.unsetVar("ng2");
                    player.unsetVar("ng3");
                    player.unsetVar("ng4");
                }
                // Level 3 profession (2nd class change)
                else if (classLevel == 3) {
                    if (giveRewards && Config.ALT_ALLOW_SHADOW_WEAPONS) {
                        rewardItem = ItemFunctions.createItem(8870); // Shadow weapon
                    }
                    
                    // Clear profession quest variables
                    player.unsetVar("newbiearmor");
                    player.unsetVar("dd1");
                    player.unsetVar("dd2");
                    player.unsetVar("dd3");
                    player.unsetVar("prof2.1");
                    player.unsetVar("prof2.2");
                    player.unsetVar("prof2.3");
                }
                
                // Give reward item if applicable
                if (rewardItem != null) {
                    rewardItem.setCount(15);
                    player.sendPacket(SystemMessage.obtainItems(rewardItem));
                    player.getInventory().addItem(rewardItem);
                }
            }
            
            // Update the subclass with new class ID
            activeClass.setClassId(newClassId);
            
            // Put the updated class back into subclasses map
            player.getSubClasses().put(newClassId, activeClass);
            
            // Refresh player stats and store to database
            player.refreshOverloaded(true, 0);
            player.storeCharSubClasses();
            
            // Give visual rewards if enabled
            if (giveRewards) {
                // Broadcast social action (level up animation)
                player.broadcastPacket(new SocialAction(player.getObjectId(), 20016));
                player.broadcastPacket(new SocialAction(player.getObjectId(), 3));
                
                // Play quest fanfare sound
                player.sendPacket(new PlaySound("ItemSound.quest_fanfare_2"));
            }
            
            // Always broadcast character info after class change
            player.broadcastCharInfo();
        }
        
        // Update player template based on new class
        boolean isFemale = player.getSex() == 0;
        PlayerTemplate newTemplate = CharacterTemplateHolder.getInstance().getTemplate(
            ClassId.getClassById(newClassId), 
            isFemale
        );
        
        if (newTemplate == null) {
            logger.error("Failed to get template for class ID: " + newClassId);
            return;
        }
        
        player.setTemplate(newTemplate);
        
        // Update party window if in party
        if (player.isInParty()) {
            player.getParty().broadCast(new PartySmallWindowUpdate(player));
        }
        
        // Update clan window if in clan
        if (player.getClan() != null) {
            player.getClan().broadcastToOnlineMembers(new PledgeShowMemberListUpdate(player));
        }
        
        // Update matching room if in one
        MatchingRoom matchingRoom = player.getMatchingRoom();
        if (matchingRoom != null) {
            matchingRoom.broadcastPlayerUpdate(player);
        }
    }
    
    /**
     * Additional method to handle clan academy graduation logic.
     * Called when a player reaches 2nd class transfer while in clan academy.
     * 
     * @param player The player graduating from academy
     * @param newClassId The new class ID (should be level 3)
     */
    public void addClanPointsOnProfession(Player player, int newClassId) {
        Clan clan = player.getClan();
        
        if (clan == null) {
            return;
        }
        
        // Check if clan level is high enough for reputation system
        if (clan.getLevel() < Config.MIN_CLAN_LEVEL_FOR_REPUTATION) {
            return;
        }
        
        // Check if this is a 2nd class transfer (level 3)
        ClassId newClassIdObj = ClassId.VALUES[newClassId];
        if (newClassIdObj.getLevel() != 3) {
            return;
        }
        
        // Calculate reputation points based on level joined academy
        int reputationPoints = 0;
        int lvlJoinedAcademy = player.getLvlJoinedAcademy();
        
        if (lvlJoinedAcademy > 39) {
            reputationPoints = 160;
        } else if (lvlJoinedAcademy > 16) {
            reputationPoints = 400 - ((lvlJoinedAcademy - 16) * 10);
        } else {
            reputationPoints = 400;
        }
        
        // Remove player from clan academy
        clan.removeClanMember(player.getObjectId());
        
        // Create system message for clan
        SystemMessage msg = new SystemMessage(
            SystemMsg.CLAN_ACADEMY_MEMBER_S1_HAS_SUCCESSFULLY_COMPLETED_THE_2ND_CLASS_TRANSFER_AND_OBTAINED_S2_CLAN_REPUTATION_POINTS
        );
        msg.addString(player.getName());
        msg.addNumber(clan.incReputation(reputationPoints, true, "Academy"));
        
        // Add event custom points if configured
        if (Config.EVENT_CLAN_ACADEMY_POINTS > 0) {
            clan.setCustomPoints(clan.getCustomPoints() + Config.EVENT_CLAN_ACADEMY_POINTS);
        }
        
        // Broadcast to clan members
        clan.broadcastToOnlineMembers(msg);
        
        // Remove from clan member list for others
        clan.broadcastToOtherOnlineMembers(new PledgeShowMemberListDelete(player.getName()), player);
        
        // Clear player's clan
        player.setClan(null);
        player.setTitle("");
        
        // Send messages to player
        player.sendPacket(SystemMsg.CONGRATULATIONS_YOU_WILL_NOW_GRADUATE_FROM_THE_CLAN_ACADEMY_AND_LEAVE_YOUR_CURRENT_CLAN);
        player.setLeaveClanTime(0);
        player.broadcastCharInfo();
        player.sendPacket(ExPledgeWaitingListAlarm.STATIC_PACKET);
        player.sendPacket(PledgeShowMemberListDeleteAll.STATIC);
        
        // Give academy graduation reward (Proof of Valor)
        ItemFunctions.addItem(player, 8181, 1, true);
    }
}
