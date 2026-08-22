package handler.items;

import l2.commons.util.Rnd;
import l2.gameserver.ai.PlayerAI;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.data.xml.holder.MultiSellHolder;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.*;
import l2.gameserver.scripts.Functions;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.templates.item.ActionType;
import l2.gameserver.templates.item.ItemTemplate;
import org.napile.primitive.sets.impl.CArrayIntSet;

public class ItemActionHandler extends SimpleItemHandler {
    
    public ItemActionHandler() {
        super();
    }
    
    @Override
    public int[] getItemIds() {
        CArrayIntSet itemIds = new CArrayIntSet();
        
        ItemTemplate[] templates = ItemHolder.getInstance().getAllTemplates();
        for (ItemTemplate template : templates) {
            if (template == null) {
                continue;
            }
            
            ActionType actionType = template.getDefaultAction();
            switch (actionType) {
                case SHOW_SSQSTATUS:
                case SHOW_HTML:
                case SHOW_XMAS_SEAL:
                case SHOW_CALC:
                case DICE:
                case SOULSHOT:
                case SPIRITSHOT:
                case SUMMON_SOULSHOT:
                case SUMMON_SPIRITSHOT:
                case START_SSQ:
                case SHOW_ADVENTURER_GUIDE_BOOK:
                    itemIds.add(template.getItemId());
                    break;
                default:
                    break;
            }
        }
        
        return itemIds.toArray();
    }
    
    @Override
    protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl) {
        if (player == null || !player.isPlayer()) {
            return false;
        }
        
        ItemTemplate template = item.getTemplate();
        if (template == null) {
            return false;
        }
        
        ActionType actionType = template.getDefaultAction();
        switch (actionType) {
            case SHOW_HTML:
            case SHOW_ADVENTURER_GUIDE_BOOK:
                return showHtml(player, item);
            case SHOW_SSQSTATUS:
                return showSSQStatus(player);
            case SHOW_XMAS_SEAL:
                return showXmasSeal(player, item);
            case SHOW_CALC:
                return showCalculator(player, item);
            case DICE:
                return rollDice(player, item);
            case SOULSHOT:
                return soulCrystal(player);
            case SPIRITSHOT:
            case SUMMON_SOULSHOT:
                return changeNicknameColor(player, item);
            case SUMMON_SPIRITSHOT:
                return showMiniMap(player, item);
            case START_SSQ:
                return showMultiSell(player, item);
            default:
                return false;
        }
    }
    
    private boolean showHtml(Player player, ItemInstance item) {
        Functions.show("help/" + item.getItemId() + ".htm", player, null);
        player.sendActionFailed();
        return true;
    }
    
    private boolean showSSQStatus(Player player) {
        player.sendPacket(new SSQStatus(player, 1));
        return true;
    }
    
    private boolean showXmasSeal(Player player, ItemInstance item) {
        player.sendPacket(new ShowXMasSeal(item.getItemId()));
        return true;
    }
    
    private boolean showCalculator(Player player, ItemInstance item) {
        player.sendPacket(new ShowCalc(item.getItemId()));
        return true;
    }
    
    private boolean rollDice(Player player, ItemInstance item) {
        if (player.isOlyParticipant()) {
            player.sendPacket(SystemMsg.YOU_CANNOT_USE_THAT_ITEM_IN_A_GRAND_OLYMPIAD_MATCH);
            return false;
        }
        
        if (player.isSitting()) {
            player.sendPacket(SystemMsg.YOU_CANNOT_MOVE_WHILE_SITTING);
            return false;
        }
        
        int number = Rnd.get(1, 6);
        if (number == 0) {
            player.sendPacket(SystemMsg.YOU_MAY_NOT_THROW_THE_DICE_AT_THIS_TIME_TRY_AGAIN_LATER);
            return false;
        }
        
        player.broadcastPacket(
            new Dice(
                player.getObjectId(),
                item.getItemId(),
                number,
                player.getX() - 30,
                player.getY() - 30,
                player.getZ()
            ),
            new SystemMessage(SystemMsg.C1_HAS_ROLLED_A_S2)
                .addString(player.getName())
                .addNumber(number)
        );
        
        return true;
    }
    
    private boolean soulCrystal(Player player) {
        GameObject target = player.getTarget();
        if (target == null || !target.isMonster()) {
            player.sendPacket(SystemMsg.THAT_IS_AN_INCORRECT_TARGET);
            return false;
        }
        
        MonsterInstance monster = (MonsterInstance) player.getTarget();
        if (!monster.isDead()) {
            player.sendPacket(SystemMsg.THAT_IS_AN_INCORRECT_TARGET);
            return false;
        }
        
        Skill skill = SkillTable.getInstance().getInfo(2098, 1);
        if (skill != null && skill.checkCondition(player, monster, false, false, true)) {
            PlayerAI ai = player.getAI();
            ai.Cast(skill, monster);
            return true;
        }
        
        return false;
    }
    
    private boolean changeNicknameColor(Player player, ItemInstance item) {
        player.sendPacket(new ExChangeNicknameNColor(item.getItemId()));
        return true;
    }
    
    private boolean showMiniMap(Player player, ItemInstance item) {
        player.sendPacket(new ShowMiniMap(player, item.getItemId()));
        return true;
    }
    
    private boolean showMultiSell(Player player, ItemInstance item) {
        MultiSellHolder.getInstance().SeparateAndSend(item.getItemId(), player, 0.0);
        return true;
    }
}
