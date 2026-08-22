/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.items.attachment.FlagItemAttachment
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.tables.SkillTable
 */
package l2.gameserver.network.l2.c2s;

import l2.gameserver.custom.SubclassManager;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.items.attachment.FlagItemAttachment;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.tables.SkillTable;

public class RequestMagicSkillUse
extends L2GameClientPacket {
    private Integer skillId;
    private boolean isCtrlPressed;
    private boolean isShiftPressed;

    protected void readImpl() {
        this.skillId = this.readD();
        this.isCtrlPressed = this.readD() != 0;
        this.isShiftPressed = this.readC() != 0;
    }

    protected void runImpl() {
        Player activeChar = ((GameClient)this.getClient()).getActiveChar();
        if (activeChar != null) {
            activeChar.setActive();
            if (activeChar.isOutOfControl()) {
                activeChar.sendActionFailed();
            } else {
                if (this.skillId >= 1566 && this.skillId <= 1572) {
                    if (SubclassManager.getInstance().canChangeSubclass(activeChar, this.skillId)) {
                        SubclassManager.getInstance().castSubclassChangeSkill(activeChar, this.skillId, this.isCtrlPressed, this.isShiftPressed);
                        activeChar.updateChangeClassDelay();
                    } else {
                        activeChar.sendActionFailed();
                    }
                    return;
                }
                Skill skill = SkillTable.getInstance().getInfo(this.skillId.intValue(), activeChar.getSkillLevel(this.skillId));
                if (skill != null || !activeChar.getCostumeList().useCostume(this.skillId.intValue())) {
                    if (skill != null) {
                        if (!skill.isActive() && !skill.isToggle()) {
                            return;
                        }
                        FlagItemAttachment flagAttachment = activeChar.getActiveWeaponFlagAttachment();
                        if (flagAttachment != null && !flagAttachment.canCast(activeChar, skill)) {
                            activeChar.sendActionFailed();
                            return;
                        }
                        if ((activeChar.getTransformation() != 0 || activeChar.isCursedWeaponEquipped()) && !activeChar.getAllSkills().contains(skill)) {
                            return;
                        }
                        if (skill.isToggle() && activeChar.getEffectList().getEffectsBySkill(skill) != null) {
                            activeChar.getEffectList().stopEffect(skill.getId());
                            activeChar.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.S1_IS_ABORTED).addSkillName(skill.getId(), skill.getLevel()));
                            activeChar.sendActionFailed();
                            return;
                        }
                        Creature creature = skill.getAimingTarget((Creature)((Object)activeChar), activeChar.getTarget());
                        activeChar.setGroundSkillLoc(null);
                        activeChar.getAI().Cast(skill, creature, this.isCtrlPressed, this.isShiftPressed);
                    } else {
                        activeChar.sendActionFailed();
                    }
                }
            }
        }
    }
}

