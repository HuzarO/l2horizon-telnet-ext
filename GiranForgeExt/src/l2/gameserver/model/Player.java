/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.collections.LazyArrayList
 *  l2.commons.collections.MultiValueSet
 *  l2.commons.dbutils.DbUtils
 *  l2.commons.lang.reference.HardReference
 *  l2.commons.lang.reference.HardReferences
 *  l2.commons.threading.RunnableImpl
 *  l2.commons.util.Rnd
 *  l2.gameserver.Announcements
 *  l2.gameserver.Config
 *  l2.gameserver.GameTimeController
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.ai.CtrlEvent
 *  l2.gameserver.ai.CtrlIntention
 *  l2.gameserver.ai.NextAction
 *  l2.gameserver.ai.PlayerAI
 *  l2.gameserver.dao.AccountBonusDAO
 *  l2.gameserver.dao.CharacterDAO
 *  l2.gameserver.dao.CharacterGroupReuseDAO
 *  l2.gameserver.dao.CharacterPostFriendDAO
 *  l2.gameserver.dao.CharacterSkillsDAO
 *  l2.gameserver.dao.CharacterTPBookmarkDAO
 *  l2.gameserver.dao.CharacterVariablesDAO
 *  l2.gameserver.dao.EffectsDAO
 *  l2.gameserver.dao.InstanceReuseDAO
 *  l2.gameserver.dao.PetDAO
 *  l2.gameserver.dao.PetEffectDAO
 *  l2.gameserver.data.xml.holder.ArmorSetsHolder
 *  l2.gameserver.data.xml.holder.CharacterTemplateHolder
 *  l2.gameserver.data.xml.holder.CrystalGradeDataHolder
 *  l2.gameserver.data.xml.holder.EventHolder
 *  l2.gameserver.data.xml.holder.HennaHolder
 *  l2.gameserver.data.xml.holder.InstantZoneHolder
 *  l2.gameserver.data.xml.holder.ItemHolder
 *  l2.gameserver.data.xml.holder.LanguageHolder
 *  l2.gameserver.data.xml.holder.MultiSellHolder$MultiSellListContainer
 *  l2.gameserver.data.xml.holder.NpcHolder
 *  l2.gameserver.data.xml.holder.OneDayRewardHolder
 *  l2.gameserver.data.xml.holder.PetDataHolder
 *  l2.gameserver.data.xml.holder.RecipeHolder
 *  l2.gameserver.data.xml.holder.ResidenceHolder
 *  l2.gameserver.data.xml.holder.SkillAcquireHolder
 *  l2.gameserver.database.DatabaseFactory
 *  l2.gameserver.database.mysql
 *  l2.gameserver.handler.items.IItemHandler
 *  l2.gameserver.handler.items.IRefineryHandler
 *  l2.gameserver.idfactory.IdFactory
 *  l2.gameserver.instancemanager.CursedWeaponsManager
 *  l2.gameserver.instancemanager.DimensionalRiftManager
 *  l2.gameserver.instancemanager.MatchingRoomManager
 *  l2.gameserver.instancemanager.QuestManager
 *  l2.gameserver.instancemanager.ReflectionManager
 *  l2.gameserver.instancemanager.SellBuffsManager
 *  l2.gameserver.instancemanager.VipManager
 *  l2.gameserver.listener.actor.player.OnAnswerListener
 *  l2.gameserver.listener.actor.player.impl.ReviveAnswerListener
 *  l2.gameserver.listener.actor.player.impl.ScriptAnswerListener
 *  l2.gameserver.listener.actor.player.impl.SummonAnswerListener
 *  l2.gameserver.model.ArmorSet
 *  l2.gameserver.model.DeathPenalty
 *  l2.gameserver.model.Effect
 *  l2.gameserver.model.Effect$EEffectSlot
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.GameObjectTasks$EndCustomHeroTask
 *  l2.gameserver.model.GameObjectTasks$EndSitDownTask
 *  l2.gameserver.model.GameObjectTasks$EndStandUpTask
 *  l2.gameserver.model.GameObjectTasks$HourlyTask
 *  l2.gameserver.model.GameObjectTasks$KickTask
 *  l2.gameserver.model.GameObjectTasks$MountFeedTask
 *  l2.gameserver.model.GameObjectTasks$PvPFlagTask
 *  l2.gameserver.model.GameObjectTasks$UnJailTask
 *  l2.gameserver.model.GameObjectTasks$WaterTask
 *  l2.gameserver.model.GameObjectsStorage
 *  l2.gameserver.model.Party
 *  l2.gameserver.model.PetData
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.PlayerGroup
 *  l2.gameserver.model.PremiumItem
 *  l2.gameserver.model.Recipe
 *  l2.gameserver.model.Request
 *  l2.gameserver.model.Request$L2RequestType
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Skill$AddedSkill
 *  l2.gameserver.model.Skill$SkillMagicType
 *  l2.gameserver.model.SkillLearn
 *  l2.gameserver.model.SubClass
 *  l2.gameserver.model.Summon
 *  l2.gameserver.model.World
 *  l2.gameserver.model.WorldRegion
 *  l2.gameserver.model.Zone
 *  l2.gameserver.model.Zone$ZoneType
 *  l2.gameserver.model.actor.instances.player.Bonus
 *  l2.gameserver.model.actor.instances.player.CharacterBlockList
 *  l2.gameserver.model.actor.instances.player.CostumeCollectionManager
 *  l2.gameserver.model.actor.instances.player.CostumeList
 *  l2.gameserver.model.actor.instances.player.FriendList
 *  l2.gameserver.model.actor.instances.player.Macro
 *  l2.gameserver.model.actor.instances.player.MacroList
 *  l2.gameserver.model.actor.instances.player.ShortCut
 *  l2.gameserver.model.actor.instances.player.ShortCutList
 *  l2.gameserver.model.actor.instances.player.TpBookMark
 *  l2.gameserver.model.actor.instances.player.tasks.EnableUserRelationTask
 *  l2.gameserver.model.actor.listener.PlayerListenerList
 *  l2.gameserver.model.actor.recorder.PlayerStatsChangeRecorder
 *  l2.gameserver.model.base.AcquireType
 *  l2.gameserver.model.base.ClassId
 *  l2.gameserver.model.base.Element
 *  l2.gameserver.model.base.Experience
 *  l2.gameserver.model.base.InvisibleType
 *  l2.gameserver.model.base.PlayerAccess
 *  l2.gameserver.model.base.Race
 *  l2.gameserver.model.base.RestartType
 *  l2.gameserver.model.base.TeamType
 *  l2.gameserver.model.chat.chatfilter.ChatMsg
 *  l2.gameserver.model.entity.DimensionalRift
 *  l2.gameserver.model.entity.Reflection
 *  l2.gameserver.model.entity.SevenSignsFestival.DarknessFestival
 *  l2.gameserver.model.entity.boat.Boat
 *  l2.gameserver.model.entity.events.GlobalEvent
 *  l2.gameserver.model.entity.events.impl.DuelEvent
 *  l2.gameserver.model.entity.events.impl.SiegeEvent
 *  l2.gameserver.model.entity.oly.CompetitionState
 *  l2.gameserver.model.entity.oly.HeroController
 *  l2.gameserver.model.entity.oly.NoblesController
 *  l2.gameserver.model.entity.oly.OlyController
 *  l2.gameserver.model.entity.oly.Participant
 *  l2.gameserver.model.entity.oly.ParticipantPool
 *  l2.gameserver.model.entity.oly.Stadium
 *  l2.gameserver.model.entity.oneDayReward.OneDayRewardStore
 *  l2.gameserver.model.entity.oneDayReward.requirement.ObtainLevelRequirement
 *  l2.gameserver.model.entity.oneDayReward.requirement.PvpPointsRequirement
 *  l2.gameserver.model.entity.residence.Castle
 *  l2.gameserver.model.entity.residence.ClanHall
 *  l2.gameserver.model.entity.residence.Residence
 *  l2.gameserver.model.instances.FestivalMonsterInstance
 *  l2.gameserver.model.instances.GuardInstance
 *  l2.gameserver.model.instances.MonsterInstance
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.model.instances.PetBabyInstance
 *  l2.gameserver.model.instances.PetInstance
 *  l2.gameserver.model.instances.ReflectionBossInstance
 *  l2.gameserver.model.instances.StaticObjectInstance
 *  l2.gameserver.model.instances.TamedBeastInstance
 *  l2.gameserver.model.instances.TrapInstance
 *  l2.gameserver.model.items.ItemContainer
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.model.items.LockType
 *  l2.gameserver.model.items.ManufactureItem
 *  l2.gameserver.model.items.PcFreight
 *  l2.gameserver.model.items.PcInventory
 *  l2.gameserver.model.items.PcRefund
 *  l2.gameserver.model.items.PcWarehouse
 *  l2.gameserver.model.items.TradeItem
 *  l2.gameserver.model.items.Warehouse
 *  l2.gameserver.model.items.Warehouse$WarehouseType
 *  l2.gameserver.model.items.attachment.FlagItemAttachment
 *  l2.gameserver.model.items.attachment.PickableAttachment
 *  l2.gameserver.model.matching.MatchingRoom
 *  l2.gameserver.model.pledge.Alliance
 *  l2.gameserver.model.pledge.Clan
 *  l2.gameserver.model.pledge.Privilege
 *  l2.gameserver.model.pledge.RankPrivs
 *  l2.gameserver.model.pledge.SubUnit
 *  l2.gameserver.model.pledge.UnitMember
 *  l2.gameserver.model.quest.Quest
 *  l2.gameserver.model.quest.QuestEventType
 *  l2.gameserver.model.quest.QuestState
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.components.ChatType
 *  l2.gameserver.network.l2.components.CustomMessage
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SceneMovie
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.AbnormalStatusUpdate
 *  l2.gameserver.network.l2.s2c.AcquireSkillList
 *  l2.gameserver.network.l2.s2c.ActionFail
 *  l2.gameserver.network.l2.s2c.AutoAttackStart
 *  l2.gameserver.network.l2.s2c.CameraMode
 *  l2.gameserver.network.l2.s2c.ChairSit
 *  l2.gameserver.network.l2.s2c.ChangeWaitType
 *  l2.gameserver.network.l2.s2c.CharInfo
 *  l2.gameserver.network.l2.s2c.ConfirmDlg
 *  l2.gameserver.network.l2.s2c.EtcStatusUpdate
 *  l2.gameserver.network.l2.s2c.ExAbnormalStatusUpdateFromTarget
 *  l2.gameserver.network.l2.s2c.ExAutoSoulShot
 *  l2.gameserver.network.l2.s2c.ExBasicActionList
 *  l2.gameserver.network.l2.s2c.ExDuelUpdateUserInfo
 *  l2.gameserver.network.l2.s2c.ExMagicAttackInfo
 *  l2.gameserver.network.l2.s2c.ExNewSkillToLearnByLevelUp
 *  l2.gameserver.network.l2.s2c.ExOlympiadMode
 *  l2.gameserver.network.l2.s2c.ExOlympiadUserInfo
 *  l2.gameserver.network.l2.s2c.ExPCCafePointInfo
 *  l2.gameserver.network.l2.s2c.ExPlayAnimation
 *  l2.gameserver.network.l2.s2c.ExPledgeCount
 *  l2.gameserver.network.l2.s2c.ExPledgeWaitingListAlarm
 *  l2.gameserver.network.l2.s2c.ExPrivateStoreSetWholeMsg
 *  l2.gameserver.network.l2.s2c.ExQuestItemList
 *  l2.gameserver.network.l2.s2c.ExSetCompassZoneCode
 *  l2.gameserver.network.l2.s2c.ExStartScenePlayer
 *  l2.gameserver.network.l2.s2c.ExTeleportToLocationActivate
 *  l2.gameserver.network.l2.s2c.ExUserInfoAbnormalVisualEffect
 *  l2.gameserver.network.l2.s2c.ExUserInfoCubic
 *  l2.gameserver.network.l2.s2c.ExVitalityPointInfo
 *  l2.gameserver.network.l2.s2c.ExVoteSystemInfo
 *  l2.gameserver.network.l2.s2c.ExWorldChatCnt
 *  l2.gameserver.network.l2.s2c.GetItem
 *  l2.gameserver.network.l2.s2c.HennaInfo
 *  l2.gameserver.network.l2.s2c.InventoryUpdate
 *  l2.gameserver.network.l2.s2c.ItemList
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 *  l2.gameserver.network.l2.s2c.LeaveWorld
 *  l2.gameserver.network.l2.s2c.MagicSkillUse
 *  l2.gameserver.network.l2.s2c.MyTargetSelected
 *  l2.gameserver.network.l2.s2c.NpcInfo
 *  l2.gameserver.network.l2.s2c.ObserverEnd
 *  l2.gameserver.network.l2.s2c.ObserverStart
 *  l2.gameserver.network.l2.s2c.PartySmallWindowUpdate
 *  l2.gameserver.network.l2.s2c.PartySpelled
 *  l2.gameserver.network.l2.s2c.PlaySound
 *  l2.gameserver.network.l2.s2c.PledgeShowMemberListDelete
 *  l2.gameserver.network.l2.s2c.PledgeShowMemberListDeleteAll
 *  l2.gameserver.network.l2.s2c.PledgeShowMemberListUpdate
 *  l2.gameserver.network.l2.s2c.PrivateStoreListBuy
 *  l2.gameserver.network.l2.s2c.PrivateStoreListSell
 *  l2.gameserver.network.l2.s2c.PrivateStoreMsgBuy
 *  l2.gameserver.network.l2.s2c.PrivateStoreMsgSell
 *  l2.gameserver.network.l2.s2c.QuestList
 *  l2.gameserver.network.l2.s2c.RadarControl
 *  l2.gameserver.network.l2.s2c.ReceiveVipInfo
 *  l2.gameserver.network.l2.s2c.RecipeShopMsg
 *  l2.gameserver.network.l2.s2c.RecipeShopSellList
 *  l2.gameserver.network.l2.s2c.RelationChanged
 *  l2.gameserver.network.l2.s2c.Ride
 *  l2.gameserver.network.l2.s2c.SendTradeDone
 *  l2.gameserver.network.l2.s2c.ServerClose
 *  l2.gameserver.network.l2.s2c.SetupGauge
 *  l2.gameserver.network.l2.s2c.ShortBuffStatusUpdate
 *  l2.gameserver.network.l2.s2c.ShortCutInit
 *  l2.gameserver.network.l2.s2c.ShortCutRegister
 *  l2.gameserver.network.l2.s2c.SkillCoolTime
 *  l2.gameserver.network.l2.s2c.SocialAction
 *  l2.gameserver.network.l2.s2c.SpecialCamera
 *  l2.gameserver.network.l2.s2c.StatusUpdate
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.network.l2.s2c.TargetSelected
 *  l2.gameserver.network.l2.s2c.TargetUnselected
 *  l2.gameserver.network.l2.s2c.TeleportToLocation
 *  l2.gameserver.network.l2.s2c.UserInfo
 *  l2.gameserver.network.l2.s2c.UserInfoType
 *  l2.gameserver.network.l2.s2c.ValidateLocation
 *  l2.gameserver.scripts.Events
 *  l2.gameserver.skills.AbnormalEffect
 *  l2.gameserver.skills.EffectType
 *  l2.gameserver.skills.TimeStamp
 *  l2.gameserver.skills.effects.EffectCubic
 *  l2.gameserver.skills.skillclasses.Transformation
 *  l2.gameserver.stats.Formulas
 *  l2.gameserver.stats.Stats
 *  l2.gameserver.stats.funcs.FuncTemplate
 *  l2.gameserver.tables.ClanTable
 *  l2.gameserver.tables.SkillTable
 *  l2.gameserver.tables.SkillTreeTable
 *  l2.gameserver.taskmanager.AutoSaveManager
 *  l2.gameserver.taskmanager.LazyPrecisionTaskManager
 *  l2.gameserver.templates.CharTemplate
 *  l2.gameserver.templates.FishTemplate
 *  l2.gameserver.templates.Henna
 *  l2.gameserver.templates.InstantZone
 *  l2.gameserver.templates.PlayerTemplate
 *  l2.gameserver.templates.item.ArmorTemplate$ArmorType
 *  l2.gameserver.templates.item.ItemTemplate
 *  l2.gameserver.templates.item.WeaponTemplate
 *  l2.gameserver.templates.item.WeaponTemplate$WeaponType
 *  l2.gameserver.templates.item.support.Grade
 *  l2.gameserver.templates.npc.NpcTemplate
 *  l2.gameserver.utils.GameStats
 *  l2.gameserver.utils.ItemFunctions
 *  l2.gameserver.utils.Language
 *  l2.gameserver.utils.Location
 *  l2.gameserver.utils.Log
 *  l2.gameserver.utils.Log$ItemLog
 *  l2.gameserver.utils.Strings
 *  l2.gameserver.utils.TeleportUtils
 *  l2.gameserver.utils.Util
 *  org.apache.commons.lang3.ArrayUtils
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.commons.lang3.math.NumberUtils
 *  org.apache.commons.lang3.tuple.ImmutablePair
 *  org.apache.commons.lang3.tuple.Pair
 *  org.napile.primitive.Containers
 *  org.napile.primitive.iterators.IntIterator
 *  org.napile.primitive.maps.IntObjectMap
 *  org.napile.primitive.maps.IntObjectMap$Entry
 *  org.napile.primitive.maps.impl.CHashIntObjectMap
 *  org.napile.primitive.sets.IntSet
 *  org.napile.primitive.sets.impl.HashIntSet
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.model;

import Config.GiranForgeConfig;
import giranforge.packets.L2EventPacket;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import l2.commons.collections.LazyArrayList;
import l2.commons.collections.MultiValueSet;
import l2.commons.dbutils.DbUtils;
import l2.commons.lang.reference.HardReference;
import l2.commons.lang.reference.HardReferences;
import l2.commons.threading.RunnableImpl;
import l2.commons.util.Rnd;
import l2.gameserver.Announcements;
import l2.gameserver.Config;
import l2.gameserver.GameTimeController;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.ai.CtrlEvent;
import l2.gameserver.ai.CtrlIntention;
import l2.gameserver.ai.NextAction;
import l2.gameserver.ai.PlayerAI;
import l2.gameserver.dao.AccountBonusDAO;
import l2.gameserver.dao.CharacterDAO;
import l2.gameserver.dao.CharacterGroupReuseDAO;
import l2.gameserver.dao.CharacterPostFriendDAO;
import l2.gameserver.dao.CharacterSkillsDAO;
import l2.gameserver.dao.CharacterTPBookmarkDAO;
import l2.gameserver.dao.CharacterVariablesDAO;
import l2.gameserver.dao.EffectsDAO;
import l2.gameserver.dao.InstanceReuseDAO;
import l2.gameserver.dao.PetDAO;
import l2.gameserver.dao.PetEffectDAO;
import l2.gameserver.data.xml.holder.ArmorSetsHolder;
import l2.gameserver.data.xml.holder.CharacterTemplateHolder;
import l2.gameserver.data.xml.holder.CrystalGradeDataHolder;
import l2.gameserver.data.xml.holder.EventHolder;
import l2.gameserver.data.xml.holder.HennaHolder;
import l2.gameserver.data.xml.holder.InstantZoneHolder;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.data.xml.holder.LanguageHolder;
import l2.gameserver.data.xml.holder.MultiSellHolder;
import l2.gameserver.data.xml.holder.NpcHolder;
import l2.gameserver.data.xml.holder.OneDayRewardHolder;
import l2.gameserver.data.xml.holder.PetDataHolder;
import l2.gameserver.data.xml.holder.RecipeHolder;
import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.data.xml.holder.SkillAcquireHolder;
import l2.gameserver.database.DatabaseFactory;
import l2.gameserver.database.mysql;
import l2.gameserver.handler.items.IItemHandler;
import l2.gameserver.handler.items.IRefineryHandler;
import l2.gameserver.idfactory.IdFactory;
import l2.gameserver.instancemanager.CursedWeaponsManager;
import l2.gameserver.instancemanager.DimensionalRiftManager;
import l2.gameserver.instancemanager.MatchingRoomManager;
import l2.gameserver.instancemanager.QuestManager;
import l2.gameserver.instancemanager.ReflectionManager;
import l2.gameserver.instancemanager.SellBuffsManager;
import l2.gameserver.instancemanager.VipManager;
import l2.gameserver.listener.actor.player.OnAnswerListener;
import l2.gameserver.listener.actor.player.impl.ReviveAnswerListener;
import l2.gameserver.listener.actor.player.impl.ScriptAnswerListener;
import l2.gameserver.listener.actor.player.impl.SummonAnswerListener;
import l2.gameserver.model.ArmorSet;
import l2.gameserver.model.Creature;
import l2.gameserver.model.DeathPenalty;
import l2.gameserver.model.Effect;
import l2.gameserver.model.Fishing;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.GameObjectTasks;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Party;
import l2.gameserver.model.PetData;
import l2.gameserver.model.Playable;
import l2.gameserver.model.PlayerGroup;
import l2.gameserver.model.PremiumItem;
import l2.gameserver.model.Recipe;
import l2.gameserver.model.Request;
import l2.gameserver.model.Skill;
import l2.gameserver.model.SkillLearn;
import l2.gameserver.model.SubClass;
import l2.gameserver.model.Summon;
import l2.gameserver.model.World;
import l2.gameserver.model.WorldRegion;
import l2.gameserver.model.Zone;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;
import l2.gameserver.model.actor.instances.player.Bonus;
import l2.gameserver.model.actor.instances.player.CharacterBlockList;
import l2.gameserver.model.actor.instances.player.CostumeCollectionManager;
import l2.gameserver.model.actor.instances.player.CostumeList;
import l2.gameserver.model.actor.instances.player.FriendList;
import l2.gameserver.model.actor.instances.player.Macro;
import l2.gameserver.model.actor.instances.player.MacroList;
import l2.gameserver.model.actor.instances.player.ShortCut;
import l2.gameserver.model.actor.instances.player.ShortCutList;
import l2.gameserver.model.actor.instances.player.TpBookMark;
import l2.gameserver.model.actor.instances.player.tasks.EnableUserRelationTask;
import l2.gameserver.model.actor.listener.PlayerListenerList;
import l2.gameserver.model.actor.recorder.PlayerStatsChangeRecorder;
import l2.gameserver.model.base.AcquireType;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.base.Element;
import l2.gameserver.model.base.Experience;
import l2.gameserver.model.base.InvisibleType;
import l2.gameserver.model.base.PlayerAccess;
import l2.gameserver.model.base.Race;
import l2.gameserver.model.base.RestartType;
import l2.gameserver.model.base.TeamType;
import l2.gameserver.model.chat.chatfilter.ChatMsg;
import l2.gameserver.model.entity.DimensionalRift;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.entity.SevenSignsFestival.DarknessFestival;
import l2.gameserver.model.entity.boat.Boat;
import l2.gameserver.model.entity.events.GlobalEvent;
import l2.gameserver.model.entity.events.impl.DuelEvent;
import l2.gameserver.model.entity.events.impl.SiegeEvent;
import l2.gameserver.model.entity.oly.CompetitionState;
import l2.gameserver.model.entity.oly.HeroController;
import l2.gameserver.model.entity.oly.NoblesController;
import l2.gameserver.model.entity.oly.OlyController;
import l2.gameserver.model.entity.oly.Participant;
import l2.gameserver.model.entity.oly.ParticipantPool;
import l2.gameserver.model.entity.oly.Stadium;
import l2.gameserver.model.entity.oneDayReward.OneDayRewardStore;
import l2.gameserver.model.entity.oneDayReward.requirement.ObtainLevelRequirement;
import l2.gameserver.model.entity.oneDayReward.requirement.PvpPointsRequirement;
import l2.gameserver.model.entity.residence.Castle;
import l2.gameserver.model.entity.residence.ClanHall;
import l2.gameserver.model.entity.residence.Residence;
import l2.gameserver.model.instances.FestivalMonsterInstance;
import l2.gameserver.model.instances.GuardInstance;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.instances.PetBabyInstance;
import l2.gameserver.model.instances.PetInstance;
import l2.gameserver.model.instances.ReflectionBossInstance;
import l2.gameserver.model.instances.StaticObjectInstance;
import l2.gameserver.model.instances.TamedBeastInstance;
import l2.gameserver.model.instances.TrapInstance;
import l2.gameserver.model.items.ItemContainer;
import l2.gameserver.model.items.ItemInfo;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.LockType;
import l2.gameserver.model.items.ManufactureItem;
import l2.gameserver.model.items.PcFreight;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.model.items.PcRefund;
import l2.gameserver.model.items.PcWarehouse;
import l2.gameserver.model.items.TradeItem;
import l2.gameserver.model.items.Warehouse;
import l2.gameserver.model.items.attachment.FlagItemAttachment;
import l2.gameserver.model.items.attachment.PickableAttachment;
import l2.gameserver.model.matching.MatchingRoom;
import l2.gameserver.model.pledge.Alliance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.model.pledge.Privilege;
import l2.gameserver.model.pledge.RankPrivs;
import l2.gameserver.model.pledge.SubUnit;
import l2.gameserver.model.pledge.UnitMember;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestEventType;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.components.ChatType;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.CustomSystemMsg;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SceneMovie;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.AbnormalStatusUpdate;
import l2.gameserver.network.l2.s2c.AcquireSkillList;
import l2.gameserver.network.l2.s2c.ActionFail;
import l2.gameserver.network.l2.s2c.AutoAttackStart;
import l2.gameserver.network.l2.s2c.CameraMode;
import l2.gameserver.network.l2.s2c.ChairSit;
import l2.gameserver.network.l2.s2c.ChangeWaitType;
import l2.gameserver.network.l2.s2c.CharInfo;
import l2.gameserver.network.l2.s2c.ConfirmDlg;
import l2.gameserver.network.l2.s2c.CustomSystemMessage;
import l2.gameserver.network.l2.s2c.EtcStatusUpdate;
import l2.gameserver.network.l2.s2c.ExAbnormalStatusUpdateFromTarget;
import l2.gameserver.network.l2.s2c.ExAutoSoulShot;
import l2.gameserver.network.l2.s2c.ExBasicActionList;
import l2.gameserver.network.l2.s2c.ExDuelUpdateUserInfo;
import l2.gameserver.network.l2.s2c.ExMagicAttackInfo;
import l2.gameserver.network.l2.s2c.ExNewSkillToLearnByLevelUp;
import l2.gameserver.network.l2.s2c.ExOlympiadMode;
import l2.gameserver.network.l2.s2c.ExOlympiadUserInfo;
import l2.gameserver.network.l2.s2c.ExPCCafePointInfo;
import l2.gameserver.network.l2.s2c.ExPlayAnimation;
import l2.gameserver.network.l2.s2c.ExPledgeCount;
import l2.gameserver.network.l2.s2c.ExPledgeWaitingListAlarm;
import l2.gameserver.network.l2.s2c.ExPrivateStoreSetWholeMsg;
import l2.gameserver.network.l2.s2c.ExQuestItemList;
import l2.gameserver.network.l2.s2c.ExSetCompassZoneCode;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;
import l2.gameserver.network.l2.s2c.ExStartScenePlayer;
import l2.gameserver.network.l2.s2c.ExStorageMaxCount;
import l2.gameserver.network.l2.s2c.ExTeleportToLocationActivate;
import l2.gameserver.network.l2.s2c.ExUserInfoAbnormalVisualEffect;
import l2.gameserver.network.l2.s2c.ExUserInfoCubic;
import l2.gameserver.network.l2.s2c.ExVitalityPointInfo;
import l2.gameserver.network.l2.s2c.ExVoteSystemInfo;
import l2.gameserver.network.l2.s2c.ExWorldChatCnt;
import l2.gameserver.network.l2.s2c.GetItem;
import l2.gameserver.network.l2.s2c.HennaInfo;
import l2.gameserver.network.l2.s2c.InventoryUpdate;
import l2.gameserver.network.l2.s2c.ItemList;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.network.l2.s2c.LeaveWorld;
import l2.gameserver.network.l2.s2c.MagicSkillUse;
import l2.gameserver.network.l2.s2c.MyTargetSelected;
import l2.gameserver.network.l2.s2c.NpcInfo;
import l2.gameserver.network.l2.s2c.ObserverEnd;
import l2.gameserver.network.l2.s2c.ObserverStart;
import l2.gameserver.network.l2.s2c.PartySmallWindowUpdate;
import l2.gameserver.network.l2.s2c.PartySpelled;
import l2.gameserver.network.l2.s2c.PlaySound;
import l2.gameserver.network.l2.s2c.PledgeShowMemberListDelete;
import l2.gameserver.network.l2.s2c.PledgeShowMemberListDeleteAll;
import l2.gameserver.network.l2.s2c.PledgeShowMemberListUpdate;
import l2.gameserver.network.l2.s2c.PrivateStoreListBuy;
import l2.gameserver.network.l2.s2c.PrivateStoreListSell;
import l2.gameserver.network.l2.s2c.PrivateStoreMsgBuy;
import l2.gameserver.network.l2.s2c.PrivateStoreMsgSell;
import l2.gameserver.network.l2.s2c.QuestList;
import l2.gameserver.network.l2.s2c.RadarControl;
import l2.gameserver.network.l2.s2c.ReceiveVipInfo;
import l2.gameserver.network.l2.s2c.RecipeShopMsg;
import l2.gameserver.network.l2.s2c.RecipeShopSellList;
import l2.gameserver.network.l2.s2c.RelationChanged;
import l2.gameserver.network.l2.s2c.Ride;
import l2.gameserver.network.l2.s2c.SendTradeDone;
import l2.gameserver.network.l2.s2c.ServerClose;
import l2.gameserver.network.l2.s2c.SetupGauge;
import l2.gameserver.network.l2.s2c.ShortBuffStatusUpdate;
import l2.gameserver.network.l2.s2c.ShortCutInit;
import l2.gameserver.network.l2.s2c.ShortCutRegister;
import l2.gameserver.network.l2.s2c.SkillCoolTime;
import l2.gameserver.network.l2.s2c.SkillList;
import l2.gameserver.network.l2.s2c.SocialAction;
import l2.gameserver.network.l2.s2c.SpecialCamera;
import l2.gameserver.network.l2.s2c.StatusUpdate;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.network.l2.s2c.TargetSelected;
import l2.gameserver.network.l2.s2c.TargetUnselected;
import l2.gameserver.network.l2.s2c.TeleportToLocation;
import l2.gameserver.network.l2.s2c.UserInfo;
import l2.gameserver.network.l2.s2c.UserInfoType;
import l2.gameserver.network.l2.s2c.ValidateLocation;
import l2.gameserver.request.AbstractRequest;
import l2.gameserver.scripts.Events;
import l2.gameserver.skills.AbnormalEffect;
import l2.gameserver.skills.EffectType;
import l2.gameserver.skills.TimeStamp;
import l2.gameserver.skills.effects.EffectCubic;
import l2.gameserver.skills.skillclasses.Transformation;
import l2.gameserver.stats.Formulas;
import l2.gameserver.stats.Stats;
import l2.gameserver.stats.funcs.FuncTemplate;
import l2.gameserver.tables.ClanTable;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.tables.SkillTreeTable;
import l2.gameserver.taskmanager.AutoSaveManager;
import l2.gameserver.taskmanager.LazyPrecisionTaskManager;
import l2.gameserver.templates.CharTemplate;
import l2.gameserver.templates.FishTemplate;
import l2.gameserver.templates.Henna;
import l2.gameserver.templates.InstantZone;
import l2.gameserver.templates.PlayerTemplate;
import l2.gameserver.templates.item.ArmorTemplate;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.templates.item.WeaponTemplate;
import l2.gameserver.templates.item.support.Grade;
import l2.gameserver.templates.npc.NpcTemplate;
import l2.gameserver.utils.GameStats;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Language;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.Log;
import l2.gameserver.utils.Strings;
import l2.gameserver.utils.TeleportUtils;
import l2.gameserver.utils.Util;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.napile.primitive.Containers;
import org.napile.primitive.iterators.IntIterator;
import org.napile.primitive.maps.IntObjectMap;
import org.napile.primitive.maps.impl.CHashIntObjectMap;
import org.napile.primitive.sets.IntSet;
import org.napile.primitive.sets.impl.HashIntSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player
extends Playable
implements PlayerGroup {
    public static final int DEFAULT_TITLE_COLOR = 0xFFFF77;
    public static final int MAX_POST_FRIEND_SIZE = 100;
    public static final int MAX_FRIEND_SIZE = 128;
    public static final String NO_TRADERS_VAR = "notraders";
    public static final String ANTISPAM_VAR = "antispam";
    public static final String CUSTOM_HERO_END_TIME_VAR = "CustomHeroEndTime";
    public static final String ANIMATION_OF_CAST_RANGE_VAR = "buffAnimRange";
    public static final String NO_SHOTS_ANIMATION_VAR = "noShotsAnim";
    public static final String HIDE_HAIR_ACCESSORY = "hideAccessory";
    public static final String LAST_PVP_PK_KILL_VAR_NAME = "LastPVPPKKill";
    public static final String USED_WORLD_CHAT_POINTS = "used_world_chat_points";
    public static final String BONUS_WORLD_CHAT_REUSE = "world_chat_reuse";
    public static final String USED_MAIL_SEND_POINTS = "used_mail_points";
    public static final String USED_MAIL_SEND_REUSE = "used_mail_reuse";
    public static final String SNOOP_TARGET = "snoop_target";
    public static final String TELEPORT_BOOKMARK = "teleport_bookmark";
    public static final String NO_RESTART_ZONE_LOGOUT_TIMESTAMP = "no_restart_zone_logout_time";
    public static final String VIP_POINTS = "VipPoints";
    public static final String VIP_EXPIRATION = "VipExpiration";
    public static final String VIP_ITEM_BOUGHT = "VipItemBought";
    public static final String VITALITY_ITEMS_USED = "VitalityItemsUsed";
    public static final int OBSERVER_NONE = 0;
    public static final int OBSERVER_STARTING = 1;
    public static final int OBSERVER_STARTED = 3;
    public static final int OBSERVER_LEAVING = 2;
    public static final int STORE_PRIVATE_NONE = 0;
    public static final int STORE_PRIVATE_SELL = 1;
    public static final int STORE_PRIVATE_BUY = 3;
    public static final int STORE_PRIVATE_MANUFACTURE = 5;
    public static final int STORE_OBSERVING_GAMES = 7;
    public static final int STORE_PRIVATE_SELL_PACKAGE = 8;
    public static final int STORE_PRIVATE_SELL_BUFF = 9;
    public static final int RANK_VAGABOND = 0;
    public static final int RANK_VASSAL = 1;
    public static final int RANK_HEIR = 2;
    public static final int RANK_KNIGHT = 3;
    public static final int RANK_WISEMAN = 4;
    public static final int RANK_BARON = 5;
    public static final int RANK_VISCOUNT = 6;
    public static final int RANK_COUNT = 7;
    public static final int RANK_MARQUIS = 8;
    public static final int LANG_ENG = 0;
    public static final int LANG_RUS = 1;
    public static final int LANG_UNK = -1;
    public static final int PLAYER_SEX_MALE = 0;
    public static final int PLAYER_SEX_FEMALE = 1;
    public static final int[] EXPERTISE_LEVELS = new int[]{0, 20, 40, 52, 61, 76, Integer.MAX_VALUE};
    private static final Logger logger = LoggerFactory.getLogger(Player.class);
    private static final String var_3116 = "<not connected>";
    private final PcInventory var_3152;
    private final Warehouse var_3153;
    private final ItemContainer var_3154;
    private final PcFreight var_3155;
    private final Deque<ChatMsg> var_3157;
    private final Map<Integer, Recipe> var_3158;
    private final Map<Integer, Recipe> var_3159;
    private final Map<String, QuestState> var_3161;
    private final ShortCutList var_3162;
    private final MacroList var_3163;
    private final Henna[] var_3173;
    private final CharacterBlockList var_3214;
    private final FriendList var_3215;
    private final CostumeList var_3216;
    private final Fishing var_3228;
    private final Lock var_3238;
    private final Map<Integer, Long> var_3272;
    private final AutoFarmContext var_3273;
    private final AtomicReference<MoveToLocationOffloadData> var_3284;
    private final MultiValueSet<String> var_3294;
    private final Map<Integer, PremiumItem> var_3160;
    private final AtomicInteger var_3164;
    private final AtomicBoolean var_3208;
    private final Set<Integer> var_3211;
    private final AtomicInteger var_3213;
    private final Bonus var_3221;
    private final Map<Integer, Skill> var_3250;
    private final List<String> var_3263;
    private final IntObjectMap<TimeStamp> var_3268;
    private final OneDayRewardStore var_3274;
    private final IntSet var_3282;
    private final AtomicBoolean var_3319;
    public Map<Integer, SubClass> _classlist;
    public volatile Grade _expertiseGrade = null;
    public int _telemode = 0;
    public boolean entering = true;
    public Location _stablePoint = null;
    public int[] _loto;
    public int[] _race;
    public boolean isPumpFailed;
    protected int _baseClass = -1;
    protected SubClass _activeClass = null;
    protected int _pvpFlag;
    protected Map<Class<? extends AbstractRequest>, AbstractRequest> _specialRequests = new ConcurrentHashMap<Class<? extends AbstractRequest>, AbstractRequest>();
    volatile boolean sittingTaskLaunched;
    Map<Integer, Skill> _transformationSkills;
    private GameClient _netConnection;
    private String var_3118;
    private int var_3119;
    private int var_3120;
    private int var_3121;
    private int var_3122;
    private int var_3123;
    private int var_3124;
    private boolean var_3125 = false;
    private int var_3126;
    private Stadium var_3127;
    private volatile Participant var_3128;
    private long var_3129;
    private long var_3130;
    private long var_3131;
    private long var_3132;
    private long var_3133;
    private long var_3134;
    private long var_3135;
    private long var_3136;
    private long var_3137;
    private long var_3138;
    private long var_3139;
    private int var_3140;
    private int var_3141;
    private int var_3142 = -1;
    private double var_3143;
    private String var_3144;
    private int var_3145;
    private boolean var_3146;
    private int var_3147 = 0;
    private int var_3148;
    private boolean var_3149;
    private boolean var_3150;
    private boolean var_3151;
    private long var_3156 = 0L;
    private String var_3165;
    private List<ManufactureItem> var_3166;
    private String var_3167;
    private List<TradeItem> var_3168;
    private List<TradeItem> var_3169;
    private String var_3170;
    private List<TradeItem> var_3171;
    private List<TradeItem> var_3172;
    private int var_3174;
    private int var_3175;
    private int var_3176;
    private int var_3177;
    private int var_3178;
    private int var_3179;
    private Party var_3180;
    private Location var_3181;
    private Clan var_3182;
    private int var_3183 = 0;
    private int var_3184 = -128;
    private int var_3185 = 0;
    private int var_3186 = 0;
    private int var_3187 = 0;
    private int var_3188;
    private PlayerAccess var_3189;
    private boolean var_3190 = false;
    private boolean var_3191 = false;
    private boolean var_3192 = false;
    private boolean var_3193 = false;
    private Summon var_3194 = null;
    private boolean var_3195;
    private Map<Integer, EffectCubic> var_3196 = null;
    private int var_3197 = 0;
    private Request var_3198;
    private ItemInstance var_3199;
    private WeaponTemplate var_3200;
    private Map<Integer, String> var_3201;
    private volatile int var_3202 = 0;
    private volatile int var_3203 = 0;
    private ItemInstance var_3204 = null;
    private IRefineryHandler var_3205 = null;
    private Warehouse.WarehouseType var_3206;
    private boolean var_3207 = false;
    private HardReference<NpcInstance> var_3209;
    private MultiSellHolder.MultiSellListContainer var_3210 = null;
    private WorldRegion var_3212;
    private CostumeCollectionManager var_3217;
    private boolean var_3218 = false;
    private Boat var_3219;
    private Location var_3220;
    private Future<?> var_3222;
    private boolean var_3223;
    private StaticObjectInstance var_3224;
    private boolean var_3225 = false;
    private byte[] var_3226;
    private int var_3227 = 0;
    private boolean var_3229;
    private Future<?> var_3230;
    private Future<?> var_3231;
    private Future<?> var_3232;
    private Future<?> var_3233;
    private Future<?> var_3234;
    private Future<?> var_3235;
    private Future<?> var_3236;
    private volatile boolean var_3237 = false;
    private int var_3239;
    private boolean var_3240 = false;
    private int var_3241;
    private int var_3242;
    private String var_3243;
    private String var_3244;
    private boolean var_3245;
    private boolean var_3246 = false;
    private int var_3247;
    private int var_3248;
    private int var_3249;
    private int var_3251;
    private long var_3252 = 0L;
    private int var_3253 = 0;
    private int var_3254 = 0;
    private int var_3255 = 1500;
    private Future<?> var_3256;
    private int var_3257;
    private boolean var_3258;
    private boolean var_3259;
    private int var_3260;
    private volatile InvisibleType var_3261;
    private IntObjectMap<String> var_3262;
    private boolean var_3264 = false;
    private boolean var_3265 = false;
    private long var_3266;
    private long var_3267;
    private Pair<Integer, OnAnswerListener> var_3269 = null;
    private boolean var_3270 = false;
    private MatchingRoom var_3271;
    private List<TpBookMark> var_3275;
    private boolean var_3276 = false;
    private Map<Skill, Long> var_3277 = null;
    private byte var_3278 = 0;
    private boolean var_3279 = false;
    private int var_3280 = 0;
    private int var_3281 = 0;
    private Future<?> var_3283;
    private ScheduledFuture<?> var_3285;
    private int var_3286;
    private Future<?> var_3287;
    private int var_3288;
    private int var_3289;
    private int var_3290;
    private int var_3291;
    private int var_3292;
    private ScheduledFuture<?> var_3293 = null;
    private boolean var_3295 = false;
    private boolean var_3296 = false;
    private int var_3297 = 0;
    private int var_3298 = 0;
    private boolean var_3299 = false;
    private boolean var_3300 = false;
    private boolean var_3301 = false;
    private int var_3302 = 0;
    private long var_3303 = 0L;
    private Future<?> var_3304 = null;
    private long var_3305;
    private Location var_3306;
    private Location var_3307;
    private int var_3308 = 0;
    private Future<?> var_3309;
    private long var_3310;
    private TamedBeastInstance var_3311;
    private long var_3312 = 0L;
    private Location var_3313;
    private int var_3314;
    private int var_3315 = 0;
    private int var_3316 = 0;
    private boolean var_3317;
    private ItemInstance var_3318 = null;
    private Map<Integer, Long> var_3320;
    private Future<?> var_3321;
    private int var_3322 = 0;
    private Map<String, String> var_3323;
    private long var_3324 = 0L;
    private long var_3325 = 0L;
    private boolean inOfflineHunting;
    private Integer enchantAttribute = null;
    private long lastSubclassChange = 0L;
    private ScheduledFuture<?> _attendanceTask;

    public Player(int var1, PlayerTemplate var2, String var3) {
        super(var1, (CharTemplate)var2);
        this._classlist = new HashMap<Integer, SubClass>(4);
        this.var_3143 = Config.ALT_VITALITY_LEVELS[4];
        this.var_3144 = Config.DISCONNECTED_PLAYER_TITLE;
        this.var_3145 = Config.DISCONNECTED_PLAYER_TITLE_COLOR;
        this.var_3149 = Config.AUTO_LOOT;
        this.var_3150 = Config.AUTO_LOOT_HERBS;
        this.var_3151 = Config.AUTO_LOOT_ADENA;
        this.var_3152 = new PcInventory(this);
        this.var_3153 = new PcWarehouse(this);
        this.var_3154 = new PcRefund(this);
        this.var_3155 = new PcFreight(this);
        this.var_3157 = new LinkedList<ChatMsg>();
        this.var_3158 = new TreeMap<Integer, Recipe>();
        this.var_3159 = new TreeMap<Integer, Recipe>();
        this.var_3160 = new TreeMap<Integer, PremiumItem>();
        this.var_3161 = new HashMap<String, QuestState>();
        this.var_3162 = new ShortCutList(this);
        this.var_3163 = new MacroList(this);
        this.var_3164 = new AtomicInteger(0);
        this.var_3166 = Collections.emptyList();
        this.var_3168 = Collections.emptyList();
        this.var_3169 = Collections.emptyList();
        this.var_3171 = Collections.emptyList();
        this.var_3172 = Collections.emptyList();
        this.var_3173 = new Henna[3];
        this.var_3189 = new PlayerAccess();
        this.var_3201 = new HashMap<Integer, String>(8);
        this.var_3208 = new AtomicBoolean();
        this.var_3209 = HardReferences.emptyRef();
        this.var_3211 = new CopyOnWriteArraySet<Integer>();
        this.var_3213 = new AtomicInteger(0);
        this._loto = new int[5];
        this._race = new int[2];
        this.var_3214 = new CharacterBlockList(this);
        this.var_3215 = new FriendList(this);
        this.var_3216 = new CostumeList(this);
        this.var_3221 = new Bonus();
        this.var_3226 = ArrayUtils.EMPTY_BYTE_ARRAY;
        this.var_3228 = new Fishing(this);
        this.var_3238 = new ReentrantLock();
        this.var_3250 = new HashMap<Integer, Skill>();
        this._transformationSkills = new HashMap<Integer, Skill>();
        this.var_3261 = InvisibleType.NONE;
        this.var_3262 = Containers.emptyIntObjectMap();
        this.var_3263 = new ArrayList<String>();
        this.var_3268 = new CHashIntObjectMap<>();
        this.var_3272 = new ConcurrentHashMap<Integer, Long>();
        this.var_3273 = new AutoFarmContext(this);
        this.var_3274 = new OneDayRewardStore(this);
        this.var_3275 = Collections.emptyList();
        this.var_3282 = new HashIntSet();
        this.var_3284 = new AtomicReference<>();
        this.var_3294 = new MultiValueSet<>();
        this.var_3319 = new AtomicBoolean();
        this.var_3118 = var3;
        this.var_3140 = 0xFFFFFF;
        this.var_3141 = 0xFFFF77;
        this._baseClass = this.getClassId().getId();
        this.inOfflineHunting = false;
    }

    private Player(int var1, PlayerTemplate var2) {
        this(var1, var2, null);
        this._ai = new PlayerAI(this);
        if (!Config.EVERYBODY_HAS_ADMIN_RIGHTS) {
            this.setPlayerAccess((PlayerAccess)Config.gmlist.get(var1));
        } else {
            this.setPlayerAccess((PlayerAccess)Config.gmlist.get(0));
        }
    }

    public static Player create(int var0, int var1, String var2, String var3, int var4, int var5, int var6) {
        PlayerTemplate var7 = CharacterTemplateHolder.getInstance().getTemplate(ClassId.getClassById((int)var0), var1 == 0);
        Player var8 = new Player(IdFactory.getInstance().getNextId(), var7, var2);
        var8.setName(var3);
        var8.setBaseClassId(var0);
        var8.setTitle("");
        var8.setHairStyle(var4);
        var8.setHairColor(var5);
        var8.setFace(var6);
        var8.setCreateTime(System.currentTimeMillis());
        return !CharacterDAO.getInstance().insert(var8) ? null : var8;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Player restore(int var0) {
        ResultSet var6;
        ResultSet var5;
        Statement var4;
        Statement var3;
        Connection var2;
        Player var1;
        block54: {
            var1 = null;
            var2 = null;
            var3 = null;
            var4 = null;
            var5 = null;
            var6 = null;
            try {
                int var18;
                var2 = DatabaseFactory.getInstance().getConnection();
                var3 = var2.createStatement();
                var4 = var2.createStatement();
                var5 = var3.executeQuery("SELECT * FROM `characters` WHERE `obj_Id`=" + var0 + " LIMIT 1");
                var6 = var4.executeQuery("SELECT `class_id` FROM `character_subclasses` WHERE `char_obj_id`=" + var0 + " AND `isBase`=1 LIMIT 1");
                if (!var5.next() || !var6.next()) break block54;
                boolean var7 = var5.getInt("sex") == 1;
                int var8 = var5.getInt("base_class_id");
                PlayerTemplate var9 = CharacterTemplateHolder.getInstance().getTemplate(ClassId.getClassById((int)var8), !var7);
                var1 = new Player(var0, var9);
                CharacterVariablesDAO.getInstance().loadVariables(var0, var1.getVars());
                var1.var_3272.putAll(InstanceReuseDAO.getInstance().load(var1));
                var1.func173();
                var1.setTpBookmarkSize(var5.getInt("bookmarks"));
                var1.var_3275 = CharacterTPBookmarkDAO.getInstance().select(var1);
                var1.var_3215.restore();
                var1.var_3262 = CharacterPostFriendDAO.getInstance().select(var1);
                CharacterGroupReuseDAO.getInstance().select(var1);
                var1.setBaseClassId(var8);
                var1.var_3118 = var5.getString("account_name");
                String var10 = var5.getString("char_name");
                var1.setName(var10);
                var1.setFace(var5.getInt("face"));
                var1.setHairStyle(var5.getInt("hairStyle"));
                var1.setHairColor(var5.getInt("hairColor"));
                var1.setHeading(0);
                var1.setKarma(var5.getInt("karma"), false);
                var1.setPvpKills(var5.getInt("pvpkills"));
                var1.setPkKills(var5.getInt("pkkills"));
                var1.setLeaveClanTime(var5.getLong("leaveclan") * 1000L);
                if (var1.getLeaveClanTime() > 0L && var1.canJoinClan()) {
                    var1.setLeaveClanTime(0L);
                }
                var1.setDeleteClanTime(var5.getLong("deleteclan") * 1000L);
                if (var1.getDeleteClanTime() > 0L && var1.canCreateClan()) {
                    var1.setDeleteClanTime(0L);
                }
                var1.setNoChannel(var5.getLong("nochannel") * 1000L);
                if (var1.getNoChannel() > 0L && var1.getNoChannelRemained() < 0L) {
                    var1.setNoChannel(0L);
                }
                var1.setOnlineTime(var5.getLong("onlinetime") * 1000L);
                int var11 = var5.getInt("clanid");
                if (var11 > 0) {
                    var1.setClan(ClanTable.getInstance().getClan(var11));
                    var1.setPledgeType(var5.getInt("pledge_type"));
                    var1.setPowerGrade(var5.getInt("pledge_rank"));
                    var1.setLvlJoinedAcademy(var5.getInt("lvl_joined_academy"));
                    var1.setApprentice(var5.getInt("apprentice"));
                }
                var1.setCreateTime(var5.getLong("createtime") * 1000L);
                var1.setDeleteTimer(var5.getInt("deletetime"));
                var1.setTitle(var5.getString("title"));
                if (var1.getVar("titlecolor") != null) {
                    var1.setTitleColor(Integer.decode("0x" + var1.getVar("titlecolor")));
                }
                if (var1.getVar("namecolor") == null) {
                    if (var1.isGM()) {
                        var1.setNameColor(Config.GM_NAME_COLOUR);
                    } else if (var1.getClan() != null && var1.getClan().getLeaderId() == var1.getObjectId()) {
                        var1.setNameColor(Config.CLANLEADER_NAME_COLOUR);
                    } else {
                        var1.setNameColor(Config.NORMAL_NAME_COLOUR);
                    }
                } else {
                    var1.setNameColor(Integer.decode("0x" + var1.getVar("namecolor")));
                }
                if (Config.AUTO_LOOT_INDIVIDUAL) {
                    var1.var_3149 = var1.getVarB("AutoLoot", Config.AUTO_LOOT);
                    var1.var_3150 = var1.getVarB("AutoLootHerbs", Config.AUTO_LOOT_HERBS);
                    var1.var_3151 = var1.getVarB("AutoLootAdena", Config.AUTO_LOOT_ADENA);
                }
                var1.setFistsWeaponItem(var1.findFistsWeaponItem(var8));
                var1.setUptime(System.currentTimeMillis());
                var1.setLastAccess(var5.getLong("lastAccess"));
                int var12 = var5.getInt("rec_left");
                int var13 = var5.getInt("rec_have");
                var1.setKeyBindings(var5.getBytes("key_bindings"));
                var1.setPcBangPoints(var5.getInt("pcBangPoints"));
                var1.setRaidBossPoints(var5.getInt("raidBossPoints"));
                var1.restoreRecipeBook();
                boolean var14 = false;
                var1.setNoble(NoblesController.getInstance().isNobles(var1));
                if (Config.OLY_ENABLED) {
                    var1.setHero(HeroController.getInstance().isCurrentHero(var1));
                    if (var1.isHero()) {
                        HeroController.getInstance().loadDiary(var1.getObjectId());
                    }
                }
                if (Config.ALT_ALLOW_CUSTOM_HERO && !var1.isHero() && var1.getVar(CUSTOM_HERO_END_TIME_VAR) != null) {
                    long var15 = var1.getVarLong(CUSTOM_HERO_END_TIME_VAR, 0L);
                    long var17 = var15 - System.currentTimeMillis() / 1000L;
                    if (var17 > 0L) {
                        var1.setCustomHero(true, var17, Config.ALT_ALLOW_CUSTOM_HERO_SKILLS);
                        var14 = !Config.ALT_ALLOW_CUSTOM_HERO_SKILLS;
                    } else {
                        var1.setCustomHero(false, 0L, true);
                        var14 = true;
                    }
                }
                var1.updatePledgeClass();
                int var41 = 0;
                String var16 = var1.getVar("jailed");
                boolean var42 = false;
                if (!StringUtils.isBlank((CharSequence)var16) && (var18 = var16.indexOf(59)) > 0) {
                    long var19 = Long.parseLong(var16.substring(0, var18)) - System.currentTimeMillis();
                    if (var19 > 0L) {
                        var1.startUnjailTask(var1, (int)var19);
                        Location var21 = Location.findPointToStay((GameObject)var1, (Location)Config.SERVICE_JAIL_COORDINATES, (int)50, (int)200);
                        var1.setXYZ(var21.getX(), var21.getY(), var21.getZ());
                        var41 = ReflectionManager.JAIL.getId();
                        var1.sitDown(null);
                        var1.block();
                        var42 = true;
                    } else {
                        var1.startUnjailTask(var1, 1000L);
                    }
                }
                if (!var42) {
                    var1.setXYZ(var5.getInt("x"), var5.getInt("y"), var5.getInt("z"));
                    String var43 = var1.getVar("reflection");
                    if (var43 != null && (var41 = Integer.parseInt(var43)) > 0) {
                        String var52 = var1.getVar("backCoords");
                        if (var52 != null) {
                            var1.setLoc(Location.parseLoc((String)var52));
                            var1.unsetVar("backCoords");
                        }
                        var41 = 0;
                    }
                }
                var1.setReflection(var41);
                EventHolder.getInstance().findEvent(var1);
                Quest.restoreQuestStates((Player)var1);
                var1.getInventory().restore();
                Player.restoreCharSubClasses(var1);
                var1.func160();
                var1.restoreGivableAndReceivedRec(var12, var13);
                for (ItemInstance var55 : var1.getInventory().getPaperdollItems()) {
                    if (var55 == null || !var55.isCursed()) continue;
                    CursedWeaponsManager.getInstance().checkPlayer(var1, var55);
                }
                var1.setVitality(var5.getInt("vitality") + (int)((double)(System.currentTimeMillis() / 1000L - var5.getLong("lastAccess")) / 15.0));
                try {
                    String var45 = var1.getVar("ExpandInventory");
                    if (var45 != null) {
                        var1.setExpandInventory(Integer.parseInt(var45));
                    }
                }
                catch (Exception var38) {
                    logger.error("", (Throwable)var38);
                }
                try {
                    String var46 = var1.getVar("ExpandWarehouse");
                    if (var46 != null) {
                        var1.setExpandWarehouse(Integer.parseInt(var46));
                    }
                }
                catch (Exception var37) {
                    logger.error("", (Throwable)var37);
                }
                try {
                    String var47 = var1.getVar(ANIMATION_OF_CAST_RANGE_VAR);
                    if (var47 != null) {
                        var1.setBuffAnimRange(Integer.parseInt(var47));
                    }
                }
                catch (Exception var36) {
                    logger.error("", (Throwable)var36);
                }
                try {
                    var1.setNoShotsAnim(var1.getVarB(NO_SHOTS_ANIMATION_VAR, false));
                }
                catch (Exception var35) {
                    logger.error("", (Throwable)var35);
                }
                try {
                    String var48 = var1.getVar(HIDE_HAIR_ACCESSORY);
                    if (var48 != null) {
                        var1.setHideHeadAccessories(Boolean.parseBoolean(var48));
                    }
                }
                catch (Exception var34) {
                    logger.error("", (Throwable)var34);
                }
                try {
                    String var49 = var1.getVar(NO_TRADERS_VAR);
                    if (var49 != null) {
                        var1.setNotShowTraders(Boolean.parseBoolean(var49));
                    }
                }
                catch (Exception var33) {
                    logger.error("", (Throwable)var33);
                }
                try {
                    String var50 = var1.getVar("pet");
                    if (var50 != null) {
                        var1.setPetControlItem(Integer.parseInt(var50));
                    }
                }
                catch (Exception var32) {
                    logger.error("", (Throwable)var32);
                }
                for (Map.Entry<Integer, String> var54 : CharacterDAO.getInstance().listCharactersByAccountName(var1.getAccountName()).entrySet()) {
                    if (((Integer)var54.getKey()).equals(var0)) continue;
                    var1.var_3201.put((Integer)var54.getKey(), (String)var54.getValue());
                }
                if (var14) {
                    HeroController.removeSkills((Player)var1);
                }
                var1.getBlockList().restore();
                var1.var_3163.restore();
                var1.refreshExpertisePenalty();
                var1.refreshOverloaded();
                var1.getWarehouse().restore();
                var1.getFreight().restore();
                var1.restoreTradeList();
                if (var1.getVar("storemode") != null) {
                    var1.setPrivateStoreType(Integer.parseInt(var1.getVar("storemode")));
                    var1.setSitting(true);
                }
                if (Config.EX_COSTUME_SYSTEM) {
                    var1.getCostumeList().load();
                    var1.setCostumeCollectionManager(new CostumeCollectionManager(var1));
                }
                var1.getFarmSystem().checkFarmTask();
                if (var1.getVar("lang@") == null) {
                    var1.setVar("lang@", LanguageHolder.getInstance().getDefaultLanguage().getShortName(), -1L);
                }
                if (Config.SERVICES_ENABLE_NO_CARRIER && var1.getVar("noCarrier") == null) {
                    var1.setVar("noCarrier", Config.SERVICES_NO_CARRIER_DEFAULT_TIME, -1L);
                }
                if (Config.SERVICES_PK_ANNOUNCE <= 0 || var1.getVar("PvPAnnounce") != null) break block54;
                var1.setVar("PvPAnnounce", "1", -1L);
            }
            catch (Exception var39) {
                try {
                    logger.error("Could not restore char data!", (Throwable)var39);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly((AutoCloseable[])new AutoCloseable[]{var4, var6, var3, var5, var2});
                    throw throwable;
                }
                DbUtils.closeQuietly((AutoCloseable[])new AutoCloseable[]{var4, var6, var3, var5, var2});
            }
        }
        DbUtils.closeQuietly((AutoCloseable[])new AutoCloseable[]{var4, var6, var3, var5, var2});
        return var1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void restoreCharSubClasses(Player var0) {
        Connection var1 = null;
        PreparedStatement var2 = null;
        ResultSet var3 = null;
        try {
            var1 = DatabaseFactory.getInstance().getConnection();
            var2 = var1.prepareStatement("SELECT `class_id`,`exp`,`sp`,`curHp`,`curCp`,`curMp`,`active`,`isBase`,`death_penalty` FROM `character_subclasses` WHERE `char_obj_id`=?");
            var2.setInt(1, var0.getObjectId());
            var3 = var2.executeQuery();
            SubClass var4 = null;
            while (var3.next()) {
                boolean var6;
                SubClass var5 = new SubClass();
                var5.setBase(var3.getInt("isBase") != 0);
                var5.setClassId(var3.getInt("class_id"));
                var5.setExp(var3.getLong("exp"));
                var5.setSp((long)var3.getInt("sp"));
                var5.setHp(var3.getDouble("curHp"));
                var5.setMp(var3.getDouble("curMp"));
                var5.setCp(var3.getDouble("curCp"));
                var5.setDeathPenalty(new DeathPenalty(var0, var3.getInt("death_penalty")));
                boolean bl = var6 = var3.getInt("active") != 0;
                if (var6) {
                    var4 = var5;
                }
                var0.getSubClasses().put(var5.getClassId(), var5);
            }
            if (var0.getSubClasses().isEmpty()) {
                throw new Exception("There are no one subclass for player: " + var0);
            }
            int var12 = var0.getBaseClassId();
            if (var12 == -1) {
                throw new Exception("There are no base subclass for player: " + var0);
            }
            if (var4 != null) {
                var0.setActiveSubClass(var4.getClassId(), false);
            }
            if (var0.getActiveClass() == null) {
                SubClass var13 = var0.getSubClasses().get(var12);
                var13.setActive(true);
                var0.setActiveSubClass(var13.getClassId(), false);
            }
            NoblesController.getInstance().checkNobleClass(var0);
        }
        catch (Exception var10) {
            try {
                logger.warn("Could not restore char sub-classes: " + var10);
                logger.error("", (Throwable)var10);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var1, var2, var3);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var1, (Statement)var2, (ResultSet)var3);
        }
        DbUtils.closeQuietly((Connection)var1, (Statement)var2, (ResultSet)var3);
    }

    public boolean hideHeadAccessories() {
        return this.var_3246;
    }

    public void setHideHeadAccessories(boolean var1) {
        this.var_3246 = var1;
    }

    public int getSlotOneId() {
        return this.var_3247;
    }

    public void setSlotOneId(int var1) {
        this.var_3247 = var1;
    }

    public int getSlotTwoId() {
        return this.var_3248;
    }

    public void setSlotTwoId(int var1) {
        this.var_3248 = var1;
    }

    public int buffAnimRange() {
        return this.var_3255;
    }

    public void setBuffAnimRange(int var1) {
        this.var_3255 = var1;
    }

    public boolean isNoShotsAnim() {
        return this.var_3259;
    }

    public void setNoShotsAnim(boolean var1) {
        this.var_3259 = var1;
    }

    public HardReference<Player> getRef() {
        return (HardReference<Player>)super.getRef();
    }

    public String getAccountName() {
        return this._netConnection == null ? this.var_3118 : this._netConnection.getLogin();
    }

    public String getIP() {
        return this._netConnection == null ? var_3116 : this._netConnection.getIpAddr();
    }

    public Map<Integer, String> getAccountChars() {
        return this.var_3201;
    }

    public final PlayerTemplate getTemplate() {
        return (PlayerTemplate)this._template;
    }

    public PlayerTemplate getBaseTemplate() {
        return (PlayerTemplate)this._baseTemplate;
    }

    public void changeSex() {
        this._template = CharacterTemplateHolder.getInstance().getTemplate(this.getClassId(), this.getSex() != 0);
    }

    public PlayerAI getAI() {
        return (PlayerAI)this._ai;
    }

    public void doCast(Skill var1, Creature var2, boolean var3) {
        if (var1 != null) {
            super.doCast(var1, var2, var3);
            this.triggerAfterTeleportProtection();
            this.triggerNoCarrierProtection();
        }
    }

    public void altUseSkill(Skill var1, Creature var2) {
        super.altUseSkill(var1, var2);
        this.triggerAfterTeleportProtection();
        this.triggerNoCarrierProtection();
    }

    public void sendReuseMessage(Skill var1) {
        TimeStamp var2;
        if (!this.isCastingNow() && (var2 = this.getSkillReuse(var1)) != null && var2.hasNotPassed()) {
            long var3 = var2.getReuseCurrent();
            if ((Config.ALT_SHOW_REUSE_MSG || var3 >= 10000L) && var3 >= 500L) {
                long var5 = var3 / 3600000L;
                long var7 = (var3 - var5 * 3600000L) / 60000L;
                long var9 = (long)Math.ceil((double)(var3 - var5 * 3600000L - var7 * 60000L) / 1000.0);
                if (var5 > 0L) {
                    this.sendPacket((IStaticPacket)((SystemMessage)((SystemMessage)((SystemMessage)new SystemMessage(SystemMsg.THERE_ARE_S2_HOURS_S3_MINUTES_AND_S4_SECONDS_REMAINING_IN_S1S_REUSE_TIME).addSkillName(var1.getId(), var1.getDisplayLevel())).addNumber(var5)).addNumber(var7)).addNumber(var9));
                } else if (var7 > 0L) {
                    this.sendPacket((IStaticPacket)((SystemMessage)((SystemMessage)new SystemMessage(SystemMsg.THERE_ARE_S2_MINUTES_S3_SECONDS_REMAINING_IN_S1S_REUSE_TIME).addSkillName(var1.getId(), var1.getDisplayLevel())).addNumber(var7)).addNumber(var9));
                } else {
                    this.sendPacket((IStaticPacket)((SystemMessage)new SystemMessage(SystemMsg.THERE_ARE_S2_SECONDS_REMAINING_IN_S1S_REUSE_TIME).addSkillName(var1.getId(), var1.getDisplayLevel())).addNumber(var9));
                }
            }
        }
    }

    public final int getLevel() {
        return this._activeClass == null ? 1 : this._activeClass.getLevel();
    }

    public int getSex() {
        return this.getTemplate().isMale ? 0 : 1;
    }

    public int getFace() {
        return this.var_3122;
    }

    public void setFace(int var1) {
        this.var_3122 = var1;
    }

    public int getHairColor() {
        return this.var_3124;
    }

    public void setHairColor(int var1) {
        this.var_3124 = var1;
    }

    public int getHairStyle() {
        return this.var_3123;
    }

    public void setHairStyle(int var1) {
        this.var_3123 = var1;
    }

    public void offline() {
        Party var6;
        if (this._netConnection != null) {
            this._netConnection.setActiveChar(null);
            this._netConnection.close(ServerClose.STATIC);
            this.setNetConnection(null);
        }
        if (Config.SERVICES_OFFLINE_TRADE_NAME_COLOR_CHANGE) {
            this.setNameColor(Config.SERVICES_OFFLINE_TRADE_NAME_COLOR);
        }
        if (Config.SERVICES_OFFLINE_TRADE_ABNORMAL != AbnormalEffect.NULL) {
            this.startAbnormalEffect(Config.SERVICES_OFFLINE_TRADE_ABNORMAL);
        }
        this.setOfflineMode(true);
        this.setVar("offline", String.valueOf(System.currentTimeMillis() / 1000L), -1L);
        if (Config.SELLBUFF_ENABLED && this.isSellingBuffs()) {
            StringBuilder var1 = new StringBuilder();
            for (Map.Entry<Skill, Long> var3 : this.getBuffs4Sale().entrySet()) {
                var1.append(String.format("%d:%d", var3.getKey().getId(), var3.getValue())).append(';');
            }
            this.setVar("offlinebuffs", var1.toString(), -1L);
        }
        if (Config.SERVICES_OFFLINE_TRADE_SECONDS_TO_KICK > 0L) {
            this.startKickTask(Config.SERVICES_OFFLINE_TRADE_SECONDS_TO_KICK * 1000L);
        }
        if ((var6 = this.getParty()) != null) {
            if (this.isFestivalParticipant()) {
                var6.broadcastMessageToPartyMembers(this.getName() + " has been removed from the upcoming festival.");
            }
            this.leaveParty();
        }
        if (this.getPet() != null) {
            this.getPet().unSummon();
        }
        CursedWeaponsManager.getInstance().doLogout(this);
        if (this.isOlyParticipant()) {
            this.getOlyParticipant().OnDisconnect(this);
        }
        MatchingRoomManager.getInstance().removeFromWaitingList(this);
        this.broadcastCharInfo();
        this.stopWaterTask();
        this.stopBonusTask();
        this.stopHourlyTask();
        this.stopVitalityTask();
        this.stopPcBangPointsTask();
        this.stopAutoSaveTask();
        this.stopQuestTimers();
        try {
            this.getInventory().store();
        }
        catch (Throwable var5) {
            logger.error("", var5);
        }
        try {
            this.store(false);
        }
        catch (Throwable var4) {
            logger.error("", var4);
        }
    }

    public void kick() {
        if (this._netConnection != null) {
            this._netConnection.close(LeaveWorld.STATIC);
            this.setNetConnection(null);
        }
        this.func159();
        this.deleteMe();
    }

    public void restart() {
        if (this._netConnection != null) {
            this._netConnection.setActiveChar(null);
            this.setNetConnection(null);
        }
        this.func159();
        this.deleteMe();
    }

    public void logout() {
        if (this._netConnection != null) {
            this._netConnection.close(ServerClose.STATIC);
            this.setNetConnection(null);
        }
        this.func159();
        this.deleteMe();
    }

    private void func159() {
        if (!this.var_3208.getAndSet(true)) {
            MatchingRoom var14;
            FlagItemAttachment var13;
            SubUnit var3;
            UnitMember var4;
            Summon var2;
            Party var1;
            if (Config.ALLOW_CURSED_WEAPONS && Config.DROP_CURSED_WEAPONS_ON_LOGOUT && this.isCursedWeaponEquipped()) {
                this.setPvpFlag(0);
                CursedWeaponsManager.getInstance().dropPlayer(this);
            }
            this.setNetConnection(null);
            this.setIsOnline(false);
            this.getListeners().onExit();
            if (this.isFlying() && !this.checkLandingState()) {
                this._stablePoint = TeleportUtils.getRestartLocation((Player)this, (RestartType)RestartType.TO_VILLAGE);
            }
            if (this.isCastingNow()) {
                this.abortCast(true, true);
            }
            if ((var1 = this.getParty()) != null) {
                if (this.isFestivalParticipant()) {
                    var1.broadcastMessageToPartyMembers(this.getName() + " has been removed from the upcoming festival.");
                }
                this.leaveParty();
            }
            if (Config.OLY_ENABLED && OlyController.getInstance().isCompetitionsActive()) {
                if (this.isOlyParticipant()) {
                    this.getOlyParticipant().OnDisconnect(this);
                }
                if (ParticipantPool.getInstance().isRegistred(this)) {
                    ParticipantPool.getInstance().onLogout(this);
                }
            }
            CursedWeaponsManager.getInstance().doLogout(this);
            if (this.isOlyObserver()) {
                this.leaveOlympiadObserverMode();
            }
            if (this.isInObserverMode()) {
                this.leaveObserverMode();
            }
            this.stopFishing();
            if (this._stablePoint != null) {
                this.teleToLocation(this._stablePoint);
            }
            if ((var2 = this.getPet()) != null) {
                if (Config.ALT_SAVE_SERVITOR_BUFF) {
                    var2.saveEffects();
                }
                var2.unSummon();
            }
            if (this.isMounted()) {
                PetDAO.getInstance().updateMount(this.getMountObjId(), this.getMountCurrentFed());
            }
            this.var_3215.notifyFriends(false);
            if (this.isProcessingRequest()) {
                this.getRequest().cancel();
            }
            this.stopAllTimers();
            if (this.isInZone(Zone.ZoneType.no_restart)) {
                this.setVar(NO_RESTART_ZONE_LOGOUT_TIMESTAMP, System.currentTimeMillis(), -1L);
            }
            if (this.isInBoat()) {
                this.getBoat().removePlayer(this);
            }
            UnitMember unitMember = var4 = (var3 = this.getSubUnit()) == null ? null : var3.getUnitMember(this.getObjectId());
            if (var4 != null) {
                int var5 = var4.getSponsor();
                int var6 = this.getApprentice();
                PledgeShowMemberListUpdate var7 = new PledgeShowMemberListUpdate(this);
                ExPledgeCount var8 = new ExPledgeCount(Math.max(1, this.var_3182.getOnlineMembers(0).size() - 1));
                for (Player var10 : this.var_3182.getOnlineMembers(this.getObjectId())) {
                    var10.sendPacket(new IStaticPacket[]{var7, var8});
                    if (var10.getObjectId() == var5) {
                        var10.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOUR_APPRENTICE_C1_HAS_LOGGED_OUT).addString(this._name));
                        continue;
                    }
                    if (var10.getObjectId() != var6) continue;
                    var10.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOUR_SPONSOR_C1_HAS_LOGGED_OUT).addString(this._name));
                }
                for (Player var17 : this.var_3182.getOnlineMembers(this.getObjectId())) {
                    var17.sendPacket((IStaticPacket)var7);
                    if (var17.getObjectId() == var5) {
                        var17.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOUR_APPRENTICE_C1_HAS_LOGGED_OUT).addString(this._name));
                        continue;
                    }
                    if (var17.getObjectId() != var6) continue;
                    var17.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOUR_SPONSOR_C1_HAS_LOGGED_OUT).addString(this._name));
                }
                var4.setPlayerInstance(this, true);
            }
            if ((var13 = this.getActiveWeaponFlagAttachment()) != null) {
                var13.onLogout(this);
            }
            if (CursedWeaponsManager.getInstance().getCursedWeapon(this.getCursedWeaponEquippedId()) != null) {
                CursedWeaponsManager.getInstance().getCursedWeapon(this.getCursedWeaponEquippedId()).setPlayer(null);
            }
            if ((var14 = this.getMatchingRoom()) != null) {
                if (var14.getLeader() == this) {
                    var14.disband();
                } else {
                    var14.removeMember(this, false);
                }
            }
            this.setMatchingRoom(null);
            MatchingRoomManager.getInstance().removeFromWaitingList(this);
            this.destroyAllTraps();
            this.stopPvPFlag();
            Reflection var15 = this.getReflection();
            if (var15 != ReflectionManager.DEFAULT) {
                if (var15.getReturnLoc() != null) {
                    this._stablePoint = var15.getReturnLoc();
                }
                var15.removeObject((GameObject)this);
            }
            try {
                this.getInventory().store();
                this.getRefund().clear();
            }
            catch (Throwable var12) {
                logger.error("", var12);
            }
            try {
                this.store(false);
            }
            catch (Throwable var11) {
                logger.error("", var11);
            }
        }
    }

    public Collection<Recipe> getDwarvenRecipeBook() {
        return this.var_3158.values();
    }

    public Collection<Recipe> getCommonRecipeBook() {
        return this.var_3159.values();
    }

    public int recipesCount() {
        return this.var_3159.size() + this.var_3158.size();
    }

    public boolean hasRecipe(Recipe var1) {
        return this.var_3158.containsValue(var1) || this.var_3159.containsValue(var1);
    }

    public boolean findRecipe(int var1) {
        return this.var_3158.containsKey(var1) || this.var_3159.containsKey(var1);
    }

    public void registerRecipe(Recipe var1, boolean var2) {
        if (var1 != null) {
            switch (var1.getType()) {
                case ERT_COMMON: {
                    this.var_3159.put(var1.getId(), var1);
                    break;
                }
                case ERT_DWARF: {
                    this.var_3158.put(var1.getId(), var1);
                    break;
                }
                default: {
                    return;
                }
            }
            if (var2) {
                mysql.set((String)"REPLACE INTO character_recipebook (char_id, id) VALUES(?,?)", (Object[])new Object[]{this.getObjectId(), var1.getId()});
            }
        }
    }

    public void unregisterRecipe(int var1) {
        if (this.var_3158.containsKey(var1)) {
            mysql.set((String)"DELETE FROM `character_recipebook` WHERE `char_id`=? AND `id`=? LIMIT 1", (Object[])new Object[]{this.getObjectId(), var1});
            this.var_3158.remove(var1);
        } else if (this.var_3159.containsKey(var1)) {
            mysql.set((String)"DELETE FROM `character_recipebook` WHERE `char_id`=? AND `id`=? LIMIT 1", (Object[])new Object[]{this.getObjectId(), var1});
            this.var_3159.remove(var1);
        } else {
            logger.warn("Attempted to remove unknown RecipeList" + var1);
        }
    }

    public QuestState getQuestState(Quest var1) {
        return this.getQuestState(var1.getName());
    }

    public QuestState getQuestState(String var1) {
        QuestState var2;
        this.questRead.lock();
        try {
            var2 = this.var_3161.get(var1);
        }
        finally {
            this.questRead.unlock();
        }
        return var2;
    }

    public QuestState getQuestState(Class<?> var1) {
        return this.getQuestState(var1.getSimpleName());
    }

    public boolean isQuestCompleted(String var1) {
        QuestState var2 = this.getQuestState(var1);
        return var2 != null && var2.isCompleted();
    }

    public boolean isQuestCompleted(Class<?> var1) {
        QuestState var2 = this.getQuestState(var1);
        return var2 != null && var2.isCompleted();
    }

    public void setQuestState(QuestState var1) {
        this.questWrite.lock();
        try {
            this.var_3161.put(var1.getQuest().getName(), var1);
        }
        finally {
            this.questWrite.unlock();
        }
    }

    public void removeQuestState(String var1) {
        this.questWrite.lock();
        try {
            this.var_3161.remove(var1);
        }
        finally {
            this.questWrite.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Quest[] getAllActiveQuests() {
        ArrayList<Quest> var1 = new ArrayList<Quest>(this.var_3161.size());
        this.questRead.lock();
        try {
            for (QuestState var3 : this.var_3161.values()) {
                if (!var3.isStarted()) continue;
                var1.add(var3.getQuest());
            }
        }
        finally {
            this.questRead.unlock();
        }
        return var1.toArray(new Quest[0]);
    }

    public QuestState[] getAllQuestsStates() {
        QuestState[] var1;
        this.questRead.lock();
        try {
            var1 = this.var_3161.values().toArray(new QuestState[0]);
        }
        finally {
            this.questRead.unlock();
        }
        return var1;
    }

    public List<QuestState> getQuestsForEvent(NpcInstance var1, QuestEventType var2) {
        ArrayList<QuestState> var3 = new ArrayList<QuestState>();
        Quest[] var4 = var1.getTemplate().getEventQuests(var2);
        if (var4 != null) {
            for (Quest var9 : var4) {
                QuestState var5 = this.getQuestState(var9.getName());
                if (var5 == null || var5.isCompleted()) continue;
                var3.add(this.getQuestState(var9.getName()));
            }
        }
        return var3;
    }

    public void processQuestEvent(String var1, String var2, NpcInstance var3) {
        QuestState var4;
        if (var2 == null) {
            var2 = "";
        }
        if ((var4 = this.getQuestState(var1)) == null) {
            Quest var5 = QuestManager.getQuest((String)var1);
            if (var5 == null) {
                logger.warn("Quest " + var1 + " not found!");
                return;
            }
            var4 = var5.newQuestState(this, 1);
        }
        if (var4 != null && !var4.isCompleted()) {
            var4.getQuest().notifyEvent(var2, var4, var3);
            if (var4.getQuest().getQuestIntId() != 255 && var4.getQuest().getQuestIntId() != 999) {
                this.sendPacket((IStaticPacket)new QuestList(this));
            }
        }
    }

    public boolean isQuestContinuationPossible(boolean var1) {
        if (this.getWeightPenalty() < 3 && !((double)this.getInventoryLimit() * 0.8 < (double)this.getInventory().getSize()) && !((double)Config.QUEST_INVENTORY_MAXIMUM * 0.9 < (double)this.getInventory().getQuestSize())) {
            return true;
        }
        if (var1) {
            this.sendPacket((IStaticPacket)SystemMsg.PROGRESS_IN_A_QUEST_IS_POSSIBLE_ONLY_WHEN_YOUR_INVENTORYS_WEIGHT_AND_SLOT_COUNT_ARE_LESS_THAN_80_PERCENT_OF_CAPACITY);
        }
        return false;
    }

    public void stopQuestTimers() {
        for (QuestState var4 : this.getAllQuestsStates()) {
            if (var4.isStarted()) {
                var4.pauseQuestTimers();
                continue;
            }
            var4.stopQuestTimers();
        }
    }

    public void resumeQuestTimers() {
        for (QuestState var4 : this.getAllQuestsStates()) {
            var4.resumeQuestTimers();
        }
    }

    public Collection<ShortCut> getAllShortCuts() {
        return this.var_3162.getAllShortCuts();
    }

    public ShortCut[] getShortCuts() {
        return this.var_3162.getShortCuts();
    }

    public ShortCut getShortCut(int var1, int var2) {
        return this.var_3162.getShortCut(var1, var2);
    }

    public void registerShortCut(ShortCut var1) {
        this.var_3162.registerShortCut(var1);
    }

    public void deleteShortCut(int var1, int var2) {
        this.var_3162.deleteShortCut(var1, var2);
    }

    public void registerMacro(Macro var1) {
        this.var_3163.registerMacro(var1);
    }

    public void deleteMacro(int var1) {
        this.var_3163.deleteMacro(var1);
    }

    public MacroList getMacroses() {
        return this.var_3163;
    }

    public boolean isCastleLord(int var1) {
        return this.var_3182 != null && this.isClanLeader() && this.var_3182.getCastle() == var1;
    }

    public int getPkKills() {
        return this.var_3120;
    }

    public void setPkKills(int var1) {
        this.var_3120 = var1;
    }

    public long getCreateTime() {
        return this.var_3129;
    }

    public void setCreateTime(long var1) {
        this.var_3129 = var1;
    }

    public int getDeleteTimer() {
        return this.var_3126;
    }

    public void setDeleteTimer(int var1) {
        this.var_3126 = var1;
    }

    public int getCurrentLoad() {
        return this.getInventory().getTotalWeight();
    }

    public long getLastAccess() {
        return this.var_3139;
    }

    public void setLastAccess(long var1) {
        this.var_3139 = var1;
    }

    public boolean isRecommended(Player var1) {
        return this.var_3282.contains(var1.getObjectId());
    }

    public int getReceivedRec() {
        return this.var_3280;
    }

    public void setReceivedRec(int var1) {
        this.var_3280 = var1;
    }

    public int getGivableRec() {
        return this.var_3281;
    }

    public void setGivableRec(int var1) {
        this.var_3281 = var1;
    }

    public void updateRecommends() {
        this.var_3282.clear();
        if (this.getLevel() >= 40) {
            this.var_3281 = 9;
            this.var_3280 = Math.max(0, this.var_3280 - 4);
        } else if (this.getLevel() >= 20) {
            this.var_3281 = 6;
            this.var_3280 = Math.max(0, this.var_3280 - 2);
        } else if (this.getLevel() >= 10) {
            this.var_3281 = 3;
            this.var_3280 = Math.max(0, this.var_3280 - 1);
        } else {
            this.var_3281 = 0;
            this.var_3280 = 0;
        }
    }

    public void restoreGivableAndReceivedRec(int var1, int var2) {
        this.var_3281 = var1;
        this.var_3280 = var2;
        Calendar var3 = Calendar.getInstance();
        var3.set(11, Config.REC_FLUSH_HOUR);
        var3.set(12, Config.REC_FLUSH_MINUTE);
        var3.set(13, 0);
        var3.set(14, 0);
        long var4 = Math.round((System.currentTimeMillis() / 1000L - this.getLastAccess()) / 86400L);
        if (var4 == 0L && this.getLastAccess() < var3.getTimeInMillis() / 1000L && System.currentTimeMillis() > var3.getTimeInMillis()) {
            ++var4;
        }
        int var6 = 0;
        while ((long)var6 < var4) {
            this.updateRecommends();
            ++var6;
        }
        if (var4 > 0L) {
            this.restartDailyCounters(true);
        }
    }

    public void giveRecommendation(Player var1) {
        if (var1 != null && this.getGivableRec() > 0 && var1.getReceivedRec() < 255 && !this.var_3282.contains(var1.getObjectId())) {
            this.var_3282.add(var1.getObjectId());
            this.setGivableRec(this.getGivableRec() - 1);
            this.sendUserInfo(true, UserInfoType.SOCIAL);
            var1.setReceivedRec(var1.getReceivedRec() + 1);
            var1.sendPacket((IStaticPacket)new ExVoteSystemInfo(var1));
            var1.broadcastUserInfo(true, UserInfoType.SOCIAL);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void func160() {
        Connection var1 = null;
        PreparedStatement var2 = null;
        ResultSet var3 = null;
        try {
            var1 = DatabaseFactory.getInstance().getConnection();
            var2 = var1.prepareStatement("SELECT `targetId` AS `recommendedObjId` FROM `character_recommends` WHERE `objId` = ?");
            var2.setInt(1, this.getObjectId());
            var3 = var2.executeQuery();
            this.var_3282.clear();
            while (var3.next()) {
                int var4 = var3.getInt("recommendedObjId");
                this.var_3282.add(var4);
            }
        }
        catch (SQLException var8) {
            try {
                logger.error("Can't load recommended characters", (Throwable)var8);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var1, var2, var3);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var1, (Statement)var2, var3);
        }
        DbUtils.closeQuietly((Connection)var1, (Statement)var2, (ResultSet)var3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void func161() {
        PreparedStatement var2;
        Connection var1;
        block5: {
            var1 = null;
            var2 = null;
            try {
                var1 = DatabaseFactory.getInstance().getConnection();
                var2 = var1.prepareStatement("DELETE FROM `character_recommends` WHERE `objId` = ?");
                var2.setInt(1, this.getObjectId());
                var2.executeUpdate();
                DbUtils.close((Statement)var2);
                if (this.var_3282.isEmpty()) break block5;
                var2 = var1.prepareStatement("INSERT INTO `character_recommends` (`objId`, `targetId`) VALUES (?, ?)");
                IntIterator var3 = this.var_3282.iterator();
                while (var3.hasNext()) {
                    var2.setInt(1, this.getObjectId());
                    var2.setInt(2, var3.next());
                    var2.executeUpdate();
                }
            }
            catch (SQLException var7) {
                try {
                    logger.error("Can't store recommended characters", (Throwable)var7);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly((Connection)var1, var2);
                    throw throwable;
                }
                DbUtils.closeQuietly((Connection)var1, (Statement)var2);
            }
        }
        DbUtils.closeQuietly((Connection)var1, (Statement)var2);
    }

    public void restartDailyCounters(boolean var1) {
        if (!var1) {
            this.sendUserInfo(true);
        }
        if (Config.ALLOW_MAIL) {
            this.setVar(USED_MAIL_SEND_POINTS, 0, -1L);
        }
        if (Config.ENABLE_WORLD_CHAT) {
            this.setVar(USED_WORLD_CHAT_POINTS, 0, -1L);
            if (!var1) {
                this.sendPacket((IStaticPacket)new ExWorldChatCnt(0));
            }
        }
        if (Config.PRIME_SHOP_VIP_SYSTEM_ENABLED) {
            this.setVar(VIP_ITEM_BOUGHT, 0, -1L);
            VipManager.getInstance().checkVipLevelExpiration(this);
        }
    }

    public int getKarma() {
        return this.var_3119;
    }

    public void setKarma(int var1, boolean var2) {
        if (var1 < 0) {
            var1 = 0;
        }
        if (this.var_3119 != var1) {
            this.var_3119 = var1;
            if (var2) {
                this.sendChanges();
            }
            if (this.getPet() != null) {
                this.getPet().broadcastCharInfo();
            }
        }
    }

    public int getMaxLoad() {
        int var1 = this.getCON();
        return var1 < 1 ? (int)(31000.0 * Config.MAXLOAD_MODIFIER) : (int)this.calcStat(Stats.MAX_LOAD, Math.pow(1.029993928, var1) * 30495.627366 * Config.MAXLOAD_MODIFIER, (Creature)((Object)this), null);
    }

    public void updateEffectIcons() {
        if (!this.entering && !this.isLogoutStarted()) {
            if (Config.USER_INFO_INTERVAL == 0L) {
                if (this.var_3283 != null) {
                    this.var_3283.cancel(false);
                    this.var_3283 = null;
                }
                this.func162();
            } else if (this.var_3283 == null) {
                this.var_3283 = ThreadPoolManager.getInstance().schedule((Runnable)((Object)new UpdateEffectIcons()), Config.USER_INFO_INTERVAL);
            }
        }
    }

    private void func162() {
        PartySpelled var1 = new PartySpelled((Playable)this, false);
        AbnormalStatusUpdate var2 = new AbnormalStatusUpdate();
        ExAbnormalStatusUpdateFromTarget var3 = new ExAbnormalStatusUpdateFromTarget((Creature)((Object)this));
        Collection<Effect> var4 = this.getEffectList().getAllFirstEffects();
        for (Effect.EEffectSlot var8 : Effect.EEffectSlot.VALUES) {
            for (Effect var10 : var4) {
                if (!var10.isInUse() || var10.getEffectSlot() != var8) continue;
                if (var10.isStackTypeMatch(new String[]{"HpRecoverCast"})) {
                    this.sendPacket((IStaticPacket)new ShortBuffStatusUpdate(var10));
                } else {
                    var10.addIcon(var2);
                    var10.addIcon(var3);
                }
                if (this.var_3180 == null) continue;
                var10.addPartySpelledIcon(var1);
            }
        }
        if (this.getTarget() == this) {
            this.sendPacket(new IStaticPacket[]{var2, var3});
        } else {
            this.sendPacket((IStaticPacket)var2);
        }
        if (this.var_3180 != null) {
            this.var_3180.broadCast(new IStaticPacket[]{var1});
        }
        if (this.isVisible()) {
            List<Player> var11 = World.getAroundPlayers((GameObject)this);
            for (int var13 = 0; var13 < var11.size(); ++var13) {
                Player var12 = (Player)((Object)var11.get(var13));
                if (var12.getTarget() != this) continue;
                var12.sendPacket((IStaticPacket)var3);
            }
        }
        if (this.isOlyParticipant()) {
            this.getOlyParticipant().getCompetition().broadcastEffectIcons(this, var4);
        }
    }

    public int getWeightPenalty() {
        return this.getSkillLevel(4270, 0);
    }

    public void refreshOverloaded() {
        if (!this.isLogoutStarted() && this.getMaxLoad() > 0) {
            this.setOverloaded(this.getCurrentLoad() > this.getMaxLoad());
            double var1 = 100.0 * ((double)this.getCurrentLoad() - this.calcStat(Stats.MAX_NO_PENALTY_LOAD, 0.0, (Creature)((Object)this), null)) / (double)this.getMaxLoad();
            int var3 = 0;
            var3 = var1 < 50.0 ? 0 : (var1 < 66.6 ? 1 : (var1 < 80.0 ? 2 : (var1 < 100.0 ? 3 : 4)));
            int var4 = this.getWeightPenalty();
            if (var4 != var3) {
                if (var3 > 0) {
                    super.addSkill(SkillTable.getInstance().getInfo(4270, var3));
                } else {
                    super.removeSkill(this.getKnownSkill(4270));
                }
                this.sendSkillList();
                this.sendEtcStatusUpdate();
                this.updateStats();
            }
        }
    }

    public Grade getExpertiseGrade() {
        return this._expertiseGrade;
    }

    public int getArmorsExpertisePenalty() {
        return this.var_3203;
    }

    public int getWeaponsExpertisePenalty() {
        return this.var_3202;
    }

    public int getExpertisePenalty(ItemInstance var1) {
        if (var1.getTemplate().getType2() == 0) {
            return this.getWeaponsExpertisePenalty();
        }
        return var1.getTemplate().getType2() != 1 && var1.getTemplate().getType2() != 2 ? 0 : this.getArmorsExpertisePenalty();
    }

    public void refreshExpertisePenalty() {
        if (!this.isLogoutStarted() && !Config.DISABLE_GRADE_PENALTY) {
            ItemInstance[] var6;
            int var1 = (int)this.calcStat(Stats.GRADE_EXPERTISE_LEVEL, this.getLevel(), null, null);
            Grade var2 = CrystalGradeDataHolder.getInstance().getGradeByLevel(var1);
            boolean var3 = false;
            if (this._expertiseGrade != var2) {
                this._expertiseGrade = var2;
                if (var2.ordinal() > 0) {
                    this.addSkill(SkillTable.getInstance().getInfo(239, var2.ordinal()), false);
                    var3 = true;
                }
            }
            int var4 = 0;
            int var5 = 0;
            block4: for (ItemInstance var10 : var6 = this.getInventory().getPaperdollItems()) {
                Grade var11;
                int var12;
                if (var10 == null || (var12 = (var11 = var10.getCrystalType()).ordinal() - this._expertiseGrade.ordinal()) <= 0) continue;
                switch (var10.getTemplate().getType2()) {
                    case 0: {
                        if (var12 <= var4) continue block4;
                        var4 = var12;
                        continue block4;
                    }
                    case 1: 
                    case 2: {
                        ++var5;
                    }
                }
            }
            if (var4 > 4) {
                var4 = 4;
            }
            if (var5 > 4) {
                var5 = 4;
            }
            if (this.var_3202 != var4) {
                this.var_3202 = var4;
                if (var4 > 0) {
                    super.addSkill(SkillTable.getInstance().getInfo(6209, this.var_3202));
                } else {
                    super.removeSkill(this.getKnownSkill(6209));
                }
                var3 = true;
            }
            if (this.var_3203 != var5) {
                this.var_3203 = var5;
                if (var5 > 0) {
                    super.addSkill(SkillTable.getInstance().getInfo(6213, this.var_3203));
                } else {
                    super.removeSkill(this.getKnownSkill(6213));
                }
                var3 = true;
            }
            if (var3) {
                this.getInventory().validateItemsSkills();
                this.sendSkillList();
                this.sendEtcStatusUpdate();
                this.updateStats();
            }
        }
    }

    public int getPvpKills() {
        return this.var_3121;
    }

    public void setPvpKills(int var1) {
        this.var_3121 = var1;
    }

    public ClassId getClassId() {
        return this.getTemplate().classId;
    }

    public void addClanPointsOnProfession(int var1) {
        if (this.getLvlJoinedAcademy() != 0 && this.var_3182 != null && this.var_3182.getLevel() >= Config.MIN_CLAN_LEVEL_FOR_REPUTATION && ClassId.VALUES[var1].getLevel() == 2) {
            this.var_3182.incReputation(100, true, "Academy");
        } else if (this.getLvlJoinedAcademy() != 0 && this.var_3182 != null && this.var_3182.getLevel() >= Config.MIN_CLAN_LEVEL_FOR_REPUTATION && ClassId.VALUES[var1].getLevel() == 3) {
            int var2 = 0;
            var2 = this.getLvlJoinedAcademy() > 39 ? 160 : (this.getLvlJoinedAcademy() > 16 ? 400 - (this.getLvlJoinedAcademy() - 16) * 10 : 400);
            this.var_3182.removeClanMember(this.getObjectId());
            SystemMessage var3 = new SystemMessage(SystemMsg.CLAN_ACADEMY_MEMBER_S1_HAS_SUCCESSFULLY_COMPLETED_THE_2ND_CLASS_TRANSFER_AND_OBTAINED_S2_CLAN_REPUTATION_POINTS);
            var3.addString(this.getName());
            var3.addNumber(this.var_3182.incReputation(var2, true, "Academy"));
            if (Config.EVENT_CLAN_ACADEMY_POINTS > 0) {
                this.var_3182.setCustomPoints(this.var_3182.getCustomPoints() + Config.EVENT_CLAN_ACADEMY_POINTS);
            }
            this.var_3182.broadcastToOnlineMembers(new L2GameServerPacket[]{var3});
            this.var_3182.broadcastToOtherOnlineMembers((L2GameServerPacket)new PledgeShowMemberListDelete(this.getName()), this);
            this.setClan(null);
            this.setTitle("");
            this.sendPacket((IStaticPacket)SystemMsg.CONGRATULATIONS_YOU_WILL_NOW_GRADUATE_FROM_THE_CLAN_ACADEMY_AND_LEAVE_YOUR_CURRENT_CLAN);
            this.setLeaveClanTime(0L);
            this.broadcastCharInfo();
            this.sendPacket((IStaticPacket)ExPledgeWaitingListAlarm.STATIC_PACKET);
            this.sendPacket((IStaticPacket)PledgeShowMemberListDeleteAll.STATIC);
            ItemFunctions.addItem((Playable)this, (int)8181, (long)1L, (boolean)true);
        }
    }

    public synchronized void setClassId(int var1, boolean var2, boolean var3) {
        if (!(var2 || ClassId.VALUES[var1].equalsOrChildOf(ClassId.VALUES[this.getActiveClassId()]) || this.getPlayerAccess().CanChangeClass || Config.EVERYBODY_HAS_ADMIN_RIGHTS)) {
            Thread.dumpStack();
        } else {
            PlayerTemplate var7;
            boolean var4;
            boolean bl = var4 = !this.getSubClasses().containsKey(var1);
            if (var4) {
                SubClass var5 = this.getActiveClass();
                this.getSubClasses().remove(this.getActiveClassId());
                this.changeClassInDb(var5.getClassId(), var1);
                if (var5.isBase()) {
                    this.addClanPointsOnProfession(var1);
                    ItemInstance var6 = null;
                    if (ClassId.VALUES[var1].getLevel() == 2) {
                        if (var3 && Config.ALT_ALLOW_SHADOW_WEAPONS) {
                            var6 = ItemFunctions.createItem((int)8869);
                        }
                        this.unsetVar("newbieweapon");
                        this.unsetVar("p1q2");
                        this.unsetVar("p1q3");
                        this.unsetVar("p1q4");
                        this.unsetVar("prof1");
                        this.unsetVar("ng1");
                        this.unsetVar("ng2");
                        this.unsetVar("ng3");
                        this.unsetVar("ng4");
                    } else if (ClassId.VALUES[var1].getLevel() == 3) {
                        if (var3 && Config.ALT_ALLOW_SHADOW_WEAPONS) {
                            var6 = ItemFunctions.createItem((int)8870);
                        }
                        this.unsetVar("newbiearmor");
                        this.unsetVar("dd1");
                        this.unsetVar("dd2");
                        this.unsetVar("dd3");
                        this.unsetVar("prof2.1");
                        this.unsetVar("prof2.2");
                        this.unsetVar("prof2.3");
                    }
                    if (var6 != null) {
                        var6.setCount(15L);
                        this.sendPacket((IStaticPacket)SystemMessage.obtainItems((ItemInstance)var6));
                        this.getInventory().addItem(var6);
                    }
                }
                var5.setClassId(var1);
                this.getSubClasses().put(var1, var5);
                this.func163(true, 0);
                this.storeCharSubClasses();
                if (var3) {
                    this.broadcastPacket(new L2GameServerPacket[]{new SocialAction(this.getObjectId(), 20016)});
                    this.broadcastPacket(new L2GameServerPacket[]{new SocialAction(this.getObjectId(), 3)});
                    this.sendPacket((IStaticPacket)new PlaySound("ItemSound.quest_fanfare_2"));
                }
                this.broadcastCharInfo();
            }
            if ((var7 = CharacterTemplateHolder.getInstance().getTemplate(ClassId.getClassById((int)var1), this.getSex() == 0)) == null) {
                logger.error("Missing template for classId: " + var1);
            } else {
                this._template = var7;
                if (this.isInParty()) {
                    this.getParty().broadCast(new IStaticPacket[]{new PartySmallWindowUpdate(this)});
                }
                if (this.getClan() != null) {
                    this.getClan().broadcastToOnlineMembers(new L2GameServerPacket[]{new PledgeShowMemberListUpdate(this)});
                }
                if (this.var_3271 != null) {
                    this.var_3271.broadcastPlayerUpdate(this);
                }
                if (this.isNoble()) {
                    NoblesController.getInstance().checkNobleClass(this);
                }
                this.sendSkillList();
                this.getListeners().onSetClass(var1);
            }
        }
    }

    public long getExp() {
        return this._activeClass == null ? 0L : this._activeClass.getExp();
    }

    public long getMaxExp() {
        return this._activeClass == null ? Experience.LEVEL[Experience.getMaxLevel() + 1] : this._activeClass.getMaxExp();
    }

    public ItemInstance getEnchantScroll() {
        return this.var_3204;
    }

    public void setEnchantScroll(ItemInstance var1) {
        this.var_3204 = var1;
    }

    public IRefineryHandler getRefineryHandler() {
        return this.var_3205;
    }

    public void setRefineryHandler(IRefineryHandler var1) {
        this.var_3205 = var1;
    }

    public WeaponTemplate getFistsWeaponItem() {
        return this.var_3200;
    }

    public void setFistsWeaponItem(WeaponTemplate var1) {
        this.var_3200 = var1;
    }

    public WeaponTemplate findFistsWeaponItem(int var1) {
        if (var1 >= 0 && var1 <= 9) {
            return (WeaponTemplate)ItemHolder.getInstance().getTemplate(246);
        }
        if (var1 >= 10 && var1 <= 17) {
            return (WeaponTemplate)ItemHolder.getInstance().getTemplate(251);
        }
        if (var1 >= 18 && var1 <= 24) {
            return (WeaponTemplate)ItemHolder.getInstance().getTemplate(244);
        }
        if (var1 >= 25 && var1 <= 30) {
            return (WeaponTemplate)ItemHolder.getInstance().getTemplate(249);
        }
        if (var1 >= 31 && var1 <= 37) {
            return (WeaponTemplate)ItemHolder.getInstance().getTemplate(245);
        }
        if (var1 >= 38 && var1 <= 43) {
            return (WeaponTemplate)ItemHolder.getInstance().getTemplate(250);
        }
        if (var1 >= 44 && var1 <= 48) {
            return (WeaponTemplate)ItemHolder.getInstance().getTemplate(248);
        }
        if (var1 >= 49 && var1 <= 52) {
            return (WeaponTemplate)ItemHolder.getInstance().getTemplate(252);
        }
        return var1 >= 53 && var1 <= 57 ? (WeaponTemplate)ItemHolder.getInstance().getTemplate(247) : null;
    }

    public void addExpAndCheckBonus(MonsterInstance var1, double var2, double var4, double var6) {
        if (this._activeClass != null) {
            double var8 = 0.0;
            int var10 = var1.getLevel();
            if (Config.ALT_VITALITY_ENABLED) {
                var8 = var1.isRaid() ? 0.0 : (double)this.getVitalityLevel() / 2.0;
                var8 *= Config.ALT_VITALITY_RATE;
                if (var2 > 0.0) {
                    if (!var1.isRaid()) {
                        if (!this.getVarB("NoExp") || this.getExp() != Experience.LEVEL[this.getLevel() + 1] - 1L) {
                            double var11 = var2 / (double)(var10 * var10) * 100.0 / 9.0;
                            var11 *= Config.ALT_VITALITY_CONSUME_RATE;
                            if (this.getEffectList().getEffectByType(EffectType.Vitality) != null) {
                                var11 = -var11;
                            }
                            this.setVitality(this.getVitality() - var11 * var6);
                        }
                    } else {
                        this.setVitality(this.getVitality() + (double)Config.ALT_VITALITY_RAID_BONUS);
                    }
                }
            }
            long var21 = (long)(var2 * (Config.RATE_XP * this.getRateExp() + var8));
            long var13 = (long)(var4 * (Config.RATE_SP * this.getRateSp() + var8));
            long var15 = (long)(var2 * Config.RATE_XP * this.getRateExp());
            long var17 = (long)(var4 * Config.RATE_SP * this.getRateSp());
            this.addExpAndSp(var21, var13, var21 - var15, var13 - var17, false, true);
        }
    }

    public void addExpAndSp(long var1, long var3) {
        this.addExpAndSp(var1, var3, 0L, 0L, false, false);
    }

    public void addExpAndSp(long var1, long var3, long var5, long var7, boolean var9, boolean var10) {
        if (this._activeClass != null) {
            int var14;
            if (var9) {
                var1 = (long)((double)var1 * Config.RATE_XP * this.getRateExp());
                var3 = (long)((double)var3 * Config.RATE_SP * this.getRateSp());
            }
            Summon var11 = this.getPet();
            boolean var12 = false;
            if (var1 > 0L) {
                if (var10 && var11 != null && !var11.isDead()) {
                    if (var11.getNpcId() == 12564) {
                        var11.addExpAndSp(var1, 0L);
                        var1 = 0L;
                    } else if (var11.isPet() && var11.getExpPenalty() > 0.0) {
                        if (var11.getLevel() > this.getLevel() - 20 && var11.getLevel() < this.getLevel() + 5) {
                            var11.addExpAndSp((long)((double)var1 * var11.getExpPenalty()), 0L);
                            var1 = (long)((double)var1 * (1.0 - var11.getExpPenalty()));
                        } else {
                            var11.addExpAndSp((long)((double)var1 * var11.getExpPenalty() / 5.0), 0L);
                            var1 = (long)((double)var1 * (1.0 - var11.getExpPenalty() / 5.0));
                        }
                    } else if (var11.isSummon()) {
                        var1 = (long)((double)var1 * (1.0 - var11.getExpPenalty()));
                    }
                }
                long var13 = this.getVarB("NoExp") ? Experience.LEVEL[this.getLevel() + 1] - 1L : this.getMaxExp();
                var1 = Math.min(var1, var13 - this.getExp());
            }
            int var16 = this._activeClass.getLevel();
            this._activeClass.addExp(var1);
            this._activeClass.addSp(var3);
            if (var1 <= 0L || var3 <= 0L || var5 <= 0L && var7 <= 0L) {
                if (var3 > 0L && var1 == 0L) {
                    this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_HAVE_ACQUIRED_S1_SP).addNumber(var3));
                } else if (var3 > 0L && var1 > 0L) {
                    this.sendPacket((IStaticPacket)((SystemMessage)new SystemMessage(SystemMsg.YOU_HAVE_EARNED_S1_EXPERIENCE_AND_S2_SP).addNumber(var1)).addNumber(var3));
                } else if (var3 == 0L && var1 > 0L) {
                    this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_HAVE_EARNED_S1_EXPERIENCE).addNumber(var1));
                }
            } else {
                this.sendPacket((IStaticPacket)((SystemMessage)((SystemMessage)((SystemMessage)new SystemMessage(SystemMsg.YOU_HAVE_ACQUIRED_S1_EXP_BONUS_S2_AND_S3_SP_BONUS_S4).addNumber(var1)).addNumber(var5)).addNumber(var3)).addNumber((int)var7));
            }
            if (var1 < 0L) {
                this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.EXPERIENCE_HAS_DECREASED_BY_S1).addNumber(Math.abs(var1)));
            }
            if (var3 < 0L) {
                this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOUR_SP_HAS_DECREASED_BY_S1).addNumber(Math.abs(var3)));
            }
            if ((var14 = this._activeClass.getLevel()) != var16) {
                int var15 = var14 - var16;
                if (var14 > var16) {
                    this.func170(var14);
                }
                this.func171(var15, var16);
                this.getListeners().onLevelUp(var14);
            }
            this.updateStats();
            if (var11 != null && var12) {
                var11.broadcastCharInfo();
            }
            this.getListeners().onGainExpSp(var1, var3);
        }
    }

    private void func163(boolean var1, int var2) {
        boolean var3 = false;
        boolean var4 = false;
        ArrayList<Skill> var5 = new ArrayList<Skill>();
        if (Config.AUTO_LEARN_SKILLS) {
            int var6 = 0;
            Collection<SkillLearn> var7 = SkillAcquireHolder.getInstance().getAvailableSkills(this, ClassId.VALUES[this.getActiveClassId()], AcquireType.NORMAL, null, 0);
            while (var7.size() > var6) {
                var6 = 0;
                for (SkillLearn var9 : var7) {
                    Skill var10 = SkillTable.getInstance().getInfo(var9.getId(), var9.getLevel());
                    if (var10 != null && var10.getCanLearn(this.getClassId()) && var9.canAutoLearn()) {
                        this.addSkill(var10, false);
                        var5.add(var10);
                        continue;
                    }
                    ++var6;
                }
                var7 = SkillAcquireHolder.getInstance().getAvailableSkills(this, AcquireType.NORMAL);
            }
            var3 = true;
        } else {
            for (SkillLearn var13 : SkillAcquireHolder.getInstance().getAvailableSkills(this, AcquireType.NORMAL)) {
                if (var13.getCost() == 0 && var13.getItemId() == 0) {
                    Skill var14 = SkillTable.getInstance().getInfo(var13.getId(), var13.getLevel());
                    if (var14 == null) continue;
                    this.addSkill(var14, false);
                    var5.add(var14);
                    if (this.getAllShortCuts().size() > 0 && var14.getLevel() > 1) {
                        for (ShortCut var16 : this.getAllShortCuts()) {
                            if (var16.getId() != var14.getId() || var16.getType() != 2) continue;
                            ShortCut var11 = new ShortCut(var16.getSlot(), var16.getPage(), var16.getType(), var16.getId(), var14.getLevel(), 1);
                            this.sendPacket((IStaticPacket)new ShortCutRegister(this, var11));
                            this.registerShortCut(var11);
                        }
                    }
                    var3 = true;
                    continue;
                }
                if (var2 <= 0 || var13.getMinLevel() <= var2 || var13.getLevel() != 1) continue;
                var4 = true;
            }
        }
        if (!var5.isEmpty()) {
            CharacterSkillsDAO.getInstance().store(this, var5);
        }
        if (var1 && var3) {
            this.sendSkillList();
        }
        this.updateStats();
        if (var4) {
            this.sendPacket((IStaticPacket)ExNewSkillToLearnByLevelUp.STATIC);
        }
    }

    public Race getRace() {
        return this.getBaseTemplate().race;
    }

    public int getIntSp() {
        return (int)this.getSp();
    }

    public long getSp() {
        return this._activeClass == null ? 0L : this._activeClass.getSp();
    }

    public void setSp(long var1) {
        if (this._activeClass != null) {
            this._activeClass.setSp(var1);
        }
    }

    public int getClanId() {
        return this.var_3182 == null ? 0 : this.var_3182.getClanId();
    }

    public long getLeaveClanTime() {
        return this.var_3132;
    }

    public void setLeaveClanTime(long var1) {
        this.var_3132 = var1;
    }

    public long getDeleteClanTime() {
        return this.var_3133;
    }

    public void setDeleteClanTime(long var1) {
        this.var_3133 = var1;
    }

    public long getOnlineBeginTime() {
        return this.var_3131;
    }

    public long getOnlineTime() {
        return this.var_3130;
    }

    public void setOnlineTime(long var1) {
        this.var_3130 = var1;
        this.var_3131 = System.currentTimeMillis();
    }

    public long getOnlineCurrentTime() {
        return this.var_3130 + (System.currentTimeMillis() - this.var_3131);
    }

    public long getNoChannel() {
        return this.var_3134;
    }

    public void setNoChannel(long var1) {
        this.var_3134 = var1;
        if (this.var_3134 > 2145909600000L || this.var_3134 < 0L) {
            this.var_3134 = -1L;
        }
        this.var_3135 = this.var_3134 > 0L ? System.currentTimeMillis() : 0L;
    }

    public long getNoChannelRemained() {
        if (this.var_3134 == 0L) {
            return 0L;
        }
        if (this.var_3134 < 0L) {
            return -1L;
        }
        long var1 = this.var_3134 - System.currentTimeMillis() + this.var_3135;
        return var1 < 0L ? 0L : var1;
    }

    public void setAntiSpam(long var1, boolean var3) {
        if (var1 > 0L) {
            this.var_3136 = var1;
            this.var_3137 = System.currentTimeMillis();
        } else if (var1 < 0L) {
            this.var_3136 = -1L;
            this.var_3137 = System.currentTimeMillis();
        } else {
            this.var_3136 = 0L;
            this.var_3137 = 0L;
        }
        if (var3) {
            if (var1 != 0L) {
                this.setVar(ANTISPAM_VAR, var1, -1L);
            } else {
                this.unsetVar(ANTISPAM_VAR);
            }
        }
    }

    public long getAntiSpam() {
        return this.var_3136;
    }

    public long getAntiSpamRemained() {
        if (this.var_3136 <= 0L) {
            return this.var_3136 == 0L ? 0L : -1L;
        }
        return Math.max(0L, this.var_3136 - System.currentTimeMillis() + this.var_3137);
    }

    public void setLeaveClanCurTime() {
        this.var_3132 = System.currentTimeMillis();
    }

    public void setDeleteClanCurTime() {
        this.var_3133 = System.currentTimeMillis();
    }

    public boolean canJoinClan() {
        if (this.var_3132 == 0L) {
            return true;
        }
        if (System.currentTimeMillis() - this.var_3132 >= Config.CLAN_LEAVE_TIME_PERNALTY) {
            this.var_3132 = 0L;
            return true;
        }
        return false;
    }

    public boolean canCreateClan() {
        if (this.var_3133 == 0L) {
            return true;
        }
        if (System.currentTimeMillis() - this.var_3133 >= Config.NEW_CLAN_CREATE_PENALTY) {
            this.var_3133 = 0L;
            return true;
        }
        return false;
    }

    public IStaticPacket canJoinParty(Player var1) {
        Request var2 = this.getRequest();
        if (var2 != null && var2.isInProgress() && var2.getOtherPlayer(this) != var1) {
            return SystemMsg.WAITING_FOR_ANOTHER_REPLY.packet(var1);
        }
        if (!this.isBlockAll() && !this.getMessageRefusal()) {
            if (this.isInParty()) {
                return new SystemMessage(SystemMsg.C1_IS_A_MEMBER_OF_ANOTHER_PARTY_AND_CANNOT_BE_INVITED).addName((GameObject)this);
            }
            if (var1.getReflection() != this.getReflection() && var1.getReflection() != ReflectionManager.DEFAULT && this.getReflection() != ReflectionManager.DEFAULT) {
                return SystemMsg.INVALID_TARGET.packet(var1);
            }
            if (!this.isCursedWeaponEquipped() && !var1.isCursedWeaponEquipped()) {
                if (!var1.isOlyParticipant() && !this.isOlyParticipant()) {
                    if (var1.getPlayerAccess().CanJoinParty && this.getPlayerAccess().CanJoinParty) {
                        if (this.getTeam() != TeamType.NONE) {
                            return SystemMsg.INVALID_TARGET.packet(var1);
                        }
                        return !this.isPartyRefusal() && !var1.isPartyRefusal() ? null : SystemMsg.INVALID_TARGET.packet(var1);
                    }
                    return SystemMsg.INVALID_TARGET.packet(var1);
                }
                return SystemMsg.A_USER_CURRENTLY_PARTICIPATING_IN_THE_OLYMPIAD_CANNOT_SEND_PARTY_AND_FRIEND_INVITATIONS.packet(var1);
            }
            return SystemMsg.INVALID_TARGET.packet(var1);
        }
        return SystemMsg.THAT_PERSON_IS_IN_MESSAGE_REFUSAL_MODE.packet(var1);
    }

    public PcInventory getInventory() {
        return this.var_3152;
    }

    public long getWearedMask() {
        return this.var_3152.getWearedMask();
    }

    public PcFreight getFreight() {
        return this.var_3155;
    }

    public void removeItemFromShortCut(int var1) {
        this.var_3162.deleteShortCutByObjectId(var1);
    }

    public void removeSkillFromShortCut(int var1) {
        this.var_3162.deleteShortCutBySkillId(var1);
    }

    public boolean isSitting() {
        return this.var_3223;
    }

    public void setSitting(boolean var1) {
        this.var_3223 = var1;
    }

    public boolean getSittingTask() {
        return this.sittingTaskLaunched;
    }

    public void sitDown(StaticObjectInstance var1) {
        if (!(this.isSitting() || this.sittingTaskLaunched || this.isAlikeDead())) {
            if (!(this.isStunned() || this.isSleeping() || this.isParalyzed() || this.isAttackingNow() || this.isCastingNow() || this.isMoving())) {
                this.resetWaitSitTime();
                this.getAI().setIntention(CtrlIntention.AI_INTENTION_REST, null, null);
                if (var1 == null) {
                    this.broadcastPacket(new L2GameServerPacket[]{new ChangeWaitType((Creature)((Object)this), 0)});
                } else {
                    this.broadcastPacket(new L2GameServerPacket[]{new ChairSit(this, var1)});
                }
                this.var_3224 = var1;
                this.setSitting(true);
                this.sittingTaskLaunched = true;
                ThreadPoolManager.getInstance().schedule((Runnable)new GameObjectTasks.EndSitDownTask(this), 2500L);
            } else {
                this.getAI().setNextAction(NextAction.REST, null, null, false, false);
            }
        }
    }

    public void standUp() {
        if (this.isSitting() && !this.sittingTaskLaunched && !this.isInStoreMode() && !this.isAlikeDead()) {
            this.getAI().clearNextAction();
            this.broadcastPacket(new L2GameServerPacket[]{new ChangeWaitType((Creature)((Object)this), 1)});
            this.var_3224 = null;
            this.sittingTaskLaunched = true;
            ThreadPoolManager.getInstance().schedule((Runnable)new GameObjectTasks.EndStandUpTask(this), 2500L);
        }
    }

    protected Creature.MoveToLocationAction createMoveToLocation(Location var1, int var2, boolean var3) {
        boolean var4 = !Config.ALLOW_GEODATA;
        Location var5 = this.getLoc();
        Location var6 = var1.clone();
        if (this.isInBoat()) {
            var2 = (int)((double)var2 + (var5.distance(var6) - (double)(3 * this.getBoat().getActingRange())));
            var4 = true;
        }
        return Config.MOVE_OFFLOAD_MTL_PC ? new MoveToLocationActionForOffload((Creature)((Object)this), var5, var6, var4, var2, var3) : new Creature.MoveToLocationAction((Creature)((Object)this), var5, var6, var4, var2, var3);
    }

    public void moveBackwardToLocationForPacket(Location var1, boolean var2) {
        if (this.isMoving() && Config.MOVE_OFFLOAD_MTL_PC) {
            this.var_3284.set(new MoveToLocationOffloadData(var1, 0, var2));
        } else {
            this.moveToLocation(var1, 0, var2);
        }
    }

    public void updateWaitSitTime() {
        if (this.var_3148 < 200) {
            this.var_3148 += 2;
        }
    }

    public int getWaitSitTime() {
        return this.var_3148;
    }

    public void resetWaitSitTime() {
        this.var_3148 = 0;
    }

    public Warehouse getWarehouse() {
        return this.var_3153;
    }

    public ItemContainer getRefund() {
        return this.var_3154;
    }

    public long getAdena() {
        return this.getInventory().getAdena();
    }

    public boolean reduceAdena(long var1) {
        return this.reduceAdena(var1, false);
    }

    public boolean reduceAdena(long var1, boolean var3) {
        if (var1 < 0L) {
            return false;
        }
        if (var1 == 0L) {
            return true;
        }
        boolean var4 = this.getInventory().reduceAdena(var1);
        if (var3 && var4) {
            this.sendPacket((IStaticPacket)SystemMessage.removeItems((int)57, (long)var1));
        }
        return var4;
    }

    public ItemInstance addAdena(long var1) {
        return this.addAdena(var1, false);
    }

    public ItemInstance addAdena(long var1, boolean var3) {
        if (var1 < 1L) {
            return null;
        }
        ItemInstance var4 = this.getInventory().addAdena(var1);
        if (var4 != null && var3) {
            this.sendPacket((IStaticPacket)SystemMessage.obtainItems((int)57, (long)var1, (int)0));
        }
        return var4;
    }

    public GameClient getNetConnection() {
        return this._netConnection;
    }

    public void setNetConnection(GameClient client) {
        this.setNetConnection(client, true);
    }

    public void setNetConnection(GameClient client, boolean stopAutoFarm) {
        if (client == null && stopAutoFarm) {
            this.getFarmSystem().stopFarmTask();
        }
        this._netConnection = client;
    }

    public int getRevision() {
        return this._netConnection == null ? 0 : this._netConnection.getRevision();
    }

    public boolean isConnected() {
        return this._netConnection != null && this._netConnection.isConnected();
    }

    public void onAction(Player var1, boolean var2) {
        if (this.isFrozen()) {
            var1.sendActionFailed();
        } else if (Events.onAction((Player)var1, (GameObject)this, (boolean)var2)) {
            var1.sendActionFailed();
        } else if (var1.getTarget() != this) {
            var1.setTarget((GameObject)this);
            if (var1.getTarget() == this) {
                var1.sendPacket(new IStaticPacket[]{new MyTargetSelected(this.getObjectId(), 0), new ExAbnormalStatusUpdateFromTarget((Creature)((Object)this), true), this.makeStatusUpdate(9, 10, 11, 12)});
            } else {
                var1.sendActionFailed();
            }
        } else if (this.getPrivateStoreType() != 0) {
            if (this.getRealDistance((GameObject)var1) > (double)this.getActingRange() && var1.getAI().getIntention() != CtrlIntention.AI_INTENTION_INTERACT) {
                if (!var2) {
                    var1.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, (Object)this);
                } else {
                    var1.sendActionFailed();
                }
            } else {
                var1.doInteract((GameObject)this);
            }
        } else if (this.isAutoAttackable((Creature)((Object)var1))) {
            var1.getAI().Attack((GameObject)this, false, var2);
        } else if (var1 != this) {
            if (var1.getAI().getIntention() == CtrlIntention.AI_INTENTION_FOLLOW && var1.getFollowTarget() == this) {
                var1.sendActionFailed();
            } else if (!var2) {
                var1.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, (Object)this);
            } else {
                var1.sendActionFailed();
            }
        } else {
            var1.sendActionFailed();
        }
    }

    public void broadcastStatusUpdate() {
        if (this.needStatusUpdate()) {
            DuelEvent var2;
            StatusUpdate var1 = this.makeStatusUpdate(10, 12, 34, 9, 11, 33);
            this.sendPacket((IStaticPacket)var1.setAttackerObject((GameObject)this));
            if (this.isInParty()) {
                this.getParty().broadcastToPartyMembers(this, (L2GameServerPacket)new PartySmallWindowUpdate(this));
            }
            if ((var2 = (DuelEvent)this.getEvent(DuelEvent.class)) != null) {
                var2.sendPacket((IStaticPacket)new ExDuelUpdateUserInfo(this), new String[]{this.getTeam().revert().name()});
            }
            if (this.isOlyCompetitionStarted()) {
                this.broadcastPacket(new L2GameServerPacket[]{new ExOlympiadUserInfo(this)});
            }
        }
    }

    public void broadcastCharInfo() {
        this.broadcastUserInfo(false, new UserInfoType[0]);
    }

    public void broadcastUserInfo(boolean var1, UserInfoType ... var2) {
        this.sendUserInfo(var1, var2 != null && var2.length > 0 ? var2 : UserInfoType.VALUES);
        if (this.isVisible() && !this.isInvisible()) {
            if (Config.BROADCAST_CHAR_INFO_INTERVAL == 0L) {
                var1 = true;
            }
            if (var1) {
                if (this.var_3285 != null) {
                    this.var_3285.cancel(false);
                    this.var_3285 = null;
                }
                this.func164();
            } else if (this.var_3285 == null) {
                this.var_3285 = ThreadPoolManager.getInstance().schedule((Runnable)((Object)new BroadcastCharInfoTask()), Config.BROADCAST_CHAR_INFO_INTERVAL);
            }
        }
    }

    public boolean isPolymorphed() {
        return this.var_3286 != 0;
    }

    public int getPolyId() {
        return this.var_3286;
    }

    public void setPolyId(int var1) {
        this.var_3286 = var1;
        this.teleToLocation(this.getLoc());
        this.broadcastUserInfo(true, new UserInfoType[0]);
    }

    private void func164() {
        if (this.isVisible() && !this.isInvisible()) {
            L2GameServerPacket var1 = this.isPolymorphed() ? new NpcInfo(this) : new CharInfo(this);
            for (Player var3 : World.getAroundPlayers((GameObject)this)) {
                var3.sendPacket((IStaticPacket)var1);
                var3.sendPacket((IStaticPacket)new RelationChanged().add(this, var3));
            }
        }
    }

    public void setLastNpcInteractionTime() {
        this.var_3156 = System.currentTimeMillis();
    }

    public boolean canMoveAfterInteraction() {
        return this.var_3156 + 1000L < System.currentTimeMillis();
    }

    public void broadcastRelation() {
        if (this.isVisible() && !this.isInvisible()) {
            for (Player var2 : World.getAroundPlayers((GameObject)this)) {
                RelationChanged var3 = new RelationChanged();
                var3.add(this, var2);
                var2.sendPacket((IStaticPacket)var3);
            }
        }
    }

    public void sendEtcStatusUpdate() {
        if (this.isVisible()) {
            this.sendPacket((IStaticPacket)new EtcStatusUpdate(this));
        }
    }

    private void func165() {
        this.func166(UserInfoType.VALUES);
    }

    private void func166(UserInfoType ... var1) {
        this.sendPacket((IStaticPacket)new UserInfo(this, var1));
    }

    public void sendUserInfo(boolean var1) {
        if (this.isVisible() && !this.entering && !this.isLogoutStarted()) {
            if (Config.USER_INFO_INTERVAL != 0L && !var1) {
                if (this.var_3287 == null) {
                    this.var_3287 = ThreadPoolManager.getInstance().schedule((Runnable)((Object)new UserInfoTask()), Config.USER_INFO_INTERVAL);
                }
            } else {
                if (this.var_3287 != null) {
                    this.var_3287.cancel(false);
                    this.var_3287 = null;
                }
                this.func165();
            }
        }
    }

    public void sendUserInfo(boolean var1, UserInfoType ... var2) {
        if (this.isVisible() && !this.entering && !this.isLogoutStarted()) {
            if (Config.USER_INFO_INTERVAL != 0L && !var1) {
                if (this.var_3287 == null) {
                    this.var_3287 = ThreadPoolManager.getInstance().schedule((Runnable)((Object)new UserInfoTask()), Config.USER_INFO_INTERVAL);
                }
            } else {
                if (this.var_3287 != null) {
                    this.var_3287.cancel(false);
                    this.var_3287 = null;
                }
                this.func166(var2);
            }
        }
    }

    public StatusUpdate makeStatusUpdate(int ... var1) {
        StatusUpdate var2 = new StatusUpdate((Creature)((Object)this));
        block12: for (int var6 : var1) {
            switch (var6) {
                case 9: {
                    var2.addAttribute(var6, (int)this.getCurrentHp());
                    continue block12;
                }
                case 10: {
                    var2.addAttribute(var6, this.getMaxHp());
                    continue block12;
                }
                case 11: {
                    var2.addAttribute(var6, (int)this.getCurrentMp());
                    continue block12;
                }
                case 12: {
                    var2.addAttribute(var6, this.getMaxMp());
                }
                default: {
                    continue block12;
                }
                case 14: {
                    var2.addAttribute(var6, this.getCurrentLoad());
                    continue block12;
                }
                case 15: {
                    var2.addAttribute(var6, this.getMaxLoad());
                    continue block12;
                }
                case 26: {
                    var2.addAttribute(var6, this._pvpFlag);
                    continue block12;
                }
                case 27: {
                    var2.addAttribute(var6, -this.getKarma());
                    continue block12;
                }
                case 33: {
                    var2.addAttribute(var6, (int)this.getCurrentCp());
                    continue block12;
                }
                case 34: {
                    var2.addAttribute(var6, this.getMaxCp());
                }
            }
        }
        return var2;
    }

    public void sendStatusUpdate(boolean var1, boolean var2, int ... var3) {
        StatusUpdate var4;
        if (var3.length != 0 && (!this.entering || var1) && (var4 = this.makeStatusUpdate(var3)).hasAttributes()) {
            ArrayList<StatusUpdate> var5 = new ArrayList<StatusUpdate>(var2 ? 2 : 1);
            if (var2 && this.getPet() != null) {
                var5.add(this.getPet().makeStatusUpdate(var3));
            }
            var5.add(var4);
            if (!var1) {
                this.sendPacket(var5);
            } else if (this.entering) {
                this.broadcastPacketToOthers(var5.toArray(new L2GameServerPacket[0]));
            } else {
                this.broadcastPacket(var5.toArray(new L2GameServerPacket[0]));
            }
        }
    }

    public int getAllyId() {
        return this.var_3182 == null ? 0 : this.var_3182.getAllyId();
    }

    public void sendPacket(IStaticPacket var1) {
        L2GameServerPacket var2;
        if (this.isConnected() && !this.func167((IStaticPacket)(var2 = var1.packet(this)))) {
            this._netConnection.sendPacket(var2);
        }
    }

    public void sendPacket(IStaticPacket ... var1) {
        if (this.isConnected()) {
            for (IStaticPacket var5 : var1) {
                L2GameServerPacket var6;
                if (var5 == null || this.func167((IStaticPacket)(var6 = var5.packet(this)))) continue;
                this._netConnection.sendPacket(var6);
            }
        }
    }

    public void sendEventPacket(L2EventPacket ... packet) {
        if (this.isConnected()) {
            for (L2EventPacket p : packet) {
                ExShowScreenMessage buildPacket = p.buildPacket();
                this.sendPacket((IStaticPacket)buildPacket);
            }
        }
    }

    public void sendEventPacket(L2EventPacket packet) {
        if (this.isConnected()) {
            ExShowScreenMessage buildPacket = packet.buildPacket();
            this.sendPacket((IStaticPacket)buildPacket);
        }
    }

    public void sendPacket(L2EventPacket packet) {
        if (this.isConnected()) {
            ExShowScreenMessage buildPacket = packet.buildPacket();
            this.sendPacket((IStaticPacket)buildPacket);
        }
    }

    public void sendPacket(L2EventPacket ... packet) {
        if (this.isConnected()) {
            for (L2EventPacket p : packet) {
                ExShowScreenMessage buildPacket = p.buildPacket();
                this.sendPacket((IStaticPacket)buildPacket);
            }
        }
    }

    private boolean func167(IStaticPacket var1) {
        return var1 == null;
    }

    public void sendPacket(List<? extends IStaticPacket> var1) {
        if (this.isConnected()) {
            for (IStaticPacket iStaticPacket : var1) {
                L2GameServerPacket var4;
                if (iStaticPacket == null || this.func167((IStaticPacket)(var4 = iStaticPacket.packet(this)))) continue;
                this._netConnection.sendPacket(var4);
            }
        }
    }

    public void doInteract(GameObject var1) {
        if (var1 != null && !this.isActionsDisabled()) {
            if (var1.isPlayer()) {
                Player var2 = (Player)var1;
                if (this.getRealDistance(var1) <= (double)var1.getActingRange()) {
                    switch (var2.getPrivateStoreType()) {
                        case 1: 
                        case 8: {
                            if (this.isSellingBuffs()) {
                                SellBuffsManager.getInstance().sendBuffMenu(this, var2, 0);
                                break;
                            }
                            if (var2.isSellingBuffs()) {
                                SellBuffsManager.getInstance().sendBuffMenu(this, var2, 0);
                                break;
                            }
                            this.sendPacket((IStaticPacket)new PrivateStoreListSell(this, var2));
                        }
                        default: {
                            break;
                        }
                        case 3: {
                            this.sendPacket((IStaticPacket)new PrivateStoreListBuy(this, var2));
                            break;
                        }
                        case 5: {
                            this.sendPacket((IStaticPacket)new RecipeShopSellList(this, var2));
                        }
                    }
                    this.sendActionFailed();
                } else if (!this.getAI().isIntendingInteract((GameObject)var2)) {
                    this.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, (Object)var2);
                }
            } else {
                var1.onAction(this, false);
            }
        } else {
            this.sendActionFailed();
        }
    }

    public void doAutoLootOrDrop(ItemInstance var1, NpcInstance var2) {
        boolean var3;
        boolean bl = var3 = var2.isFlying() || this.getReflection().isAutolootForced();
        if (!Config.DISABLE_AUTO_LOOT_FOR_MONSTER_IDS.contains(var2.getNpcId()) && (!var2.isRaid() && !(var2 instanceof ReflectionBossInstance) || Config.AUTO_LOOT_FROM_RAIDS || var1.isHerb() || var3)) {
            if (var1.isHerb()) {
                if (!(this.var_3150 || var3 || this.getAutoLootHerbStat())) {
                    var1.dropToTheGround(this, var2);
                } else {
                    Skill[] var4;
                    for (Skill var8 : var4 = var1.getTemplate().getAttachedSkills()) {
                        this.altUseSkill(var8, (Creature)((Object)this));
                        if (this.getPet() == null || !this.getPet().isSummon() || this.getPet().isDead()) continue;
                        this.getPet().altUseSkill(var8, (Creature)this.getPet());
                    }
                    var1.deleteMe();
                }
            } else if (!var3 && ArrayUtils.contains((int[])Config.AUTO_LOOT_EXCLUDE_ITEM_IDS, (int)var1.getItemId())) {
                var1.dropToTheGround(this, var2);
            } else if (var3 || this.var_3149 || this.getAutoLootStat() || ArrayUtils.contains((int[])Config.AUTO_LOOT_MONEY_ITEM_IDS, (int)var1.getItemId()) && (this.var_3151 || this.getAutoLootAdenaStat())) {
                if (!this.isInParty()) {
                    if (!this.pickupItem(var1, Log.ItemLog.Pickup)) {
                        var1.dropToTheGround(this, var2);
                        return;
                    }
                    this.broadcastPickUpMsg(var1);
                } else {
                    this.getParty().distributeItem(this, var1, var2);
                }
            } else {
                var1.dropToTheGround(this, var2);
            }
        } else {
            var1.dropToTheGround(this, var2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void doPickupItem(GameObject var1) {
        if (!var1.isItem()) {
            logger.warn("trying to pickup wrong target." + this.getTarget());
        } else {
            ItemInstance var12;
            this.sendActionFailed();
            this.stopMove();
            Request var2 = this.getRequest();
            if (var2 != null && var2.isInProgress()) {
                if (var2.isTypeOf(Request.L2RequestType.TRADE)) {
                    Player var3 = var2.getOtherPlayer(this);
                    this.sendPacket((IStaticPacket)SendTradeDone.FAIL);
                    var3.sendPacket((IStaticPacket)SendTradeDone.FAIL);
                    this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_CANNOT_PICK_UP_OR_USE_ITEMS_WHILE_TRADING));
                }
                var2.cancel();
            }
            ItemInstance itemInstance = var12 = (ItemInstance)var1;
            synchronized (itemInstance) {
                if (var12.isVisible()) {
                    if (!ItemFunctions.checkIfCanPickup((Playable)this, (ItemInstance)var12)) {
                        SystemMessage var14;
                        if (var12.getItemId() == 57) {
                            var14 = new SystemMessage(SystemMsg.YOU_HAVE_FAILED_TO_PICK_UP_S1_ADENA);
                            var14.addNumber(var12.getCount());
                        } else {
                            var14 = new SystemMessage(SystemMsg.YOU_HAVE_FAILED_TO_PICK_UP_S1);
                            var14.addItemName(var12.getItemId());
                        }
                        this.sendPacket((IStaticPacket)var14);
                    } else if (var12.isHerb()) {
                        Skill[] var13;
                        for (Skill var9 : var13 = var12.getTemplate().getAttachedSkills()) {
                            this.altUseSkill(var9, (Creature)((Object)this));
                            if (this.getPet() == null || !this.getPet().isSummon() || this.getPet().isDead()) continue;
                            this.getPet().altUseSkill(var9, (Creature)this.getPet());
                        }
                        this.broadcastPacket(new L2GameServerPacket[]{new GetItem(var12, this.getObjectId())});
                        var12.deleteMe();
                    } else {
                        FlagItemAttachment var5;
                        FlagItemAttachment flagItemAttachment = var5 = var12.getAttachment() instanceof FlagItemAttachment ? (FlagItemAttachment)var12.getAttachment() : null;
                        if (this.isInParty() && var5 == null) {
                            this.getParty().distributeItem(this, var12, null);
                        } else if (this.pickupItem(var12, Log.ItemLog.Pickup)) {
                            this.broadcastPacket(new L2GameServerPacket[]{new GetItem(var12, this.getObjectId())});
                            this.broadcastPickUpMsg(var12);
                            var12.pickupMe();
                        }
                    }
                }
            }
        }
    }

    public boolean pickupItem(ItemInstance var1, Log.ItemLog var2) {
        PickableAttachment var3;
        PickableAttachment pickableAttachment = var3 = var1.getAttachment() instanceof PickableAttachment ? (PickableAttachment)var1.getAttachment() : null;
        if (!ItemFunctions.canAddItem((Player)this, (ItemInstance)var1)) {
            return false;
        }
        Log.LogItem((Player)this, (Log.ItemLog)var2, (ItemInstance)var1);
        this.sendPacket((IStaticPacket)SystemMessage.obtainItems((ItemInstance)var1));
        this.getInventory().addItem(var1);
        this.getListeners().onItemPickup(var1);
        if (var3 != null) {
            var3.pickUp(this);
        }
        this.sendChanges();
        return true;
    }

    public void setObjectTarget(GameObject var1) {
        this.setTarget(var1);
        if (var1 != null && var1 == this.getTarget()) {
            if (var1.isNpc()) {
                NpcInstance var2 = (NpcInstance)var1;
                this.sendPacket((IStaticPacket)new MyTargetSelected(var2.getObjectId(), this.getLevel() - var2.getLevel()));
                this.sendPacket((IStaticPacket)var2.makeStatusUpdate(new int[]{9, 10}));
                this.sendPacket(new IStaticPacket[]{new ValidateLocation((Creature)var2), ActionFail.STATIC});
            } else {
                this.sendPacket((IStaticPacket)new MyTargetSelected(var1.getObjectId(), 0));
            }
        }
    }

    public void setTarget(GameObject var1) {
        GameObject var5;
        Party var2;
        if (var1 != null && !var1.isVisible()) {
            var1 = null;
        }
        if (var1 instanceof FestivalMonsterInstance && !this.isFestivalParticipant()) {
            var1 = null;
        }
        if ((var2 = this.getParty()) != null && var2.isInDimensionalRift()) {
            int var3 = var2.getDimensionalRift().getType();
            int var4 = var2.getDimensionalRift().getCurrentRoom();
            if (var1 != null && !DimensionalRiftManager.getInstance().getRoom(var3, var4).checkIfInZone(var1.getX(), var1.getY(), var1.getZ())) {
                var1 = null;
            }
        }
        if ((var5 = this.getTarget()) != null) {
            if (var5.equals((Object)var1)) {
                return;
            }
            if (var5.isCreature()) {
                ((Creature)var5).removeStatusListener((Creature)((Object)this));
            }
            this.broadcastPacket(new L2GameServerPacket[]{new TargetUnselected((GameObject)this)});
        }
        if (var1 != null) {
            if (var1.isCreature()) {
                ((Creature)var1).addStatusListener(this);
            }
            this.broadcastPacketToOthers(new L2GameServerPacket[]{new TargetSelected(this.getObjectId(), var1.getObjectId(), this.getLoc())});
        }
        super.setTarget(var1);
    }

    public ItemInstance getActiveWeaponInstance() {
        return this.getInventory().getPaperdollItem(5);
    }

    public WeaponTemplate getActiveWeaponItem() {
        ItemInstance var1 = this.getActiveWeaponInstance();
        return var1 == null ? this.getFistsWeaponItem() : (WeaponTemplate)var1.getTemplate();
    }

    public ItemInstance getSecondaryWeaponInstance() {
        return this.getInventory().getPaperdollItem(7);
    }

    public WeaponTemplate getSecondaryWeaponItem() {
        ItemInstance var1 = this.getSecondaryWeaponInstance();
        if (var1 == null) {
            return this.getFistsWeaponItem();
        }
        ItemTemplate var2 = var1.getTemplate();
        return var2 instanceof WeaponTemplate ? (WeaponTemplate)var2 : null;
    }

    public boolean isWearingArmor(ArmorTemplate.ArmorType var1) {
        ItemInstance var2 = this.getInventory().getPaperdollItem(6);
        if (var2 == null) {
            return var1 == ArmorTemplate.ArmorType.NONE;
        }
        if (var2.getItemType() != var1) {
            return false;
        }
        if (var2.getBodyPart() == 32768L) {
            return true;
        }
        if (var2.getBodyPart() == 131072L) {
            return true;
        }
        ItemInstance var3 = this.getInventory().getPaperdollItem(11);
        return var3 == null ? var1 == ArmorTemplate.ArmorType.NONE : var3.getItemType() == var1;
    }

    public void reduceCurrentHp(double var1, Creature var3, Skill var4, boolean var5, boolean var6, boolean var7, boolean var8, boolean var9, boolean var10, boolean var11) {
        if (!(var3 == null || this.isDead() || var3.isDead() && !var10)) {
            if (var3.isPlayer() && Math.abs(var3.getLevel() - this.getLevel()) > 10) {
                if (var3.getKarma() > 0 && this.getEffectList().getEffectsBySkillId(5182) != null && !this.isInZone(Zone.ZoneType.SIEGE)) {
                    return;
                }
                if (this.getKarma() > 0 && var3.getEffectList().getEffectsBySkillId(5182) != null && !var3.isInZone(Zone.ZoneType.SIEGE)) {
                    return;
                }
            }
            super.reduceCurrentHp(var1, var3, var4, var5, var6, var7, var8, var9, var10, var11);
        }
    }

    protected void onReduceCurrentHp(double var1, Creature var3, Skill var4, boolean var5, boolean var6, boolean var7) {
        DuelEvent var12;
        double var8 = var1;
        if (var6) {
            this.standUp();
            if (this.isFakeDeath()) {
                this.breakFakeDeath();
            }
        }
        if (var3.isPlayable() && !var7 && this.getCurrentCp() > 0.0) {
            double var10 = this.getCurrentCp();
            if (var10 >= var1) {
                var10 -= var1;
                var1 = 0.0;
            } else {
                var1 -= var10;
                var10 = 0.0;
            }
            this.setCurrentCp(var10);
        }
        double var14 = this.getCurrentHp();
        if (this.isOlyParticipant()) {
            if (this.isOlyCompetitionStarted() && !this.getOlyParticipant().OnDamaged(this, var3, var1, var14, var8)) {
                return;
            }
            if (!this.getOlyParticipant().isAlive()) {
                return;
            }
        }
        if ((var12 = (DuelEvent)this.getEvent(DuelEvent.class)) != null && var14 - var1 <= 1.0) {
            this.setCurrentHp(1.0, false);
            var12.onDie(this);
        } else {
            super.onReduceCurrentHp(var1, var3, var4, var5, var6, var7);
        }
    }

    public boolean isAlikeDead() {
        return this.var_3147 == 1 || super.isAlikeDead();
    }

    public boolean isMovementDisabled() {
        return this.isFakeDeath() || super.isMovementDisabled();
    }

    public boolean isActionsDisabled() {
        return this.isFakeDeath() || super.isActionsDisabled();
    }

    public void doAttack(Creature var1) {
        if (!this.isFakeDeath() && !this.isInMountTransform()) {
            super.doAttack(var1);
        }
    }

    public void onHitTimer(Creature var1, int var2, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7) {
        if (this.isFakeDeath()) {
            this.sendActionFailed();
        } else {
            super.onHitTimer(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public boolean isFakeDeath() {
        return this.var_3147 != 0;
    }

    public void setFakeDeath(int var1) {
        this.var_3147 = var1;
    }

    public void breakFakeDeath() {
        this.getEffectList().stopAllSkillEffects(EffectType.FakeDeath);
    }

    private void func168(Creature var1) {
        if (!(!Config.ALT_GAME_DELEVEL || this.isInZoneBattle() || this.isInZone(Zone.ZoneType.fun) && !Config.FUN_ZONE_CAN_LOST_EXP || this.isActionBlocked("exp_lost"))) {
            this.deathPenalty(var1);
        }
    }

    public final boolean atWarWith(Player var1) {
        return this.var_3182 != null && var1.getClan() != null && this.getPledgeType() != -1 && var1.getPledgeType() != -1 && this.var_3182.isAtWarWith(var1.getClan().getClanId());
    }

    public boolean atMutualWarWith(Player var1) {
        return this.var_3182 != null && var1.getClan() != null && this.getPledgeType() != -1 && var1.getPledgeType() != -1 && this.var_3182.isAtWarWith(var1.getClan().getClanId()) && var1.getClan().isAtWarWith(this.var_3182.getClanId());
    }

    public void doPurePk(Player var1) {
        block4: {
            super.doPurePk(var1);
            var1.setPkKills(var1.getPkKills() + 1);
            var1.getInventory().validateItems();
            if (Config.SERVICES_PK_ANNOUNCE <= 0) break block4;
            if (Config.SERVICES_PK_ANNOUNCE == 1) {
                for (Player var3 : GameObjectsStorage.getAllPlayersForIterate()) {
                    if (!var3.getVarB("PvPAnnounce")) continue;
                    Announcements.getInstance().announceToPlayerByCustomMessage(var3, "player.pkannounce", new String[]{var1.getName(), this.getName()}, ChatType.ANNOUNCEMENT);
                }
            } else {
                String var5 = new CustomMessage("service.pk_kill_announce", this, new Object[]{this.getName()}).addString(var1.getName()).toString();
                for (Player var4 : GameObjectsStorage.getAllPlayersForIterate()) {
                    if (!var4.getVarB("PvPAnnounce")) continue;
                    var4.sendPacket((IStaticPacket)((SystemMessage)new SystemMessage(SystemMsg.S2_S1).addString(var5)).addZoneName(var1.getLoc()));
                }
            }
        }
    }

    private final void func169(Player var1, boolean var2) {
        if (var2) {
            this.doPurePk(var1);
            var1.getListeners().onPvpPkKill(this, true);
        } else {
            if (Config.PVP_INCREASE_SAME_IP_CHECK && StringUtils.isNotEmpty((CharSequence)this.getIP()) && Objects.equals(this.getIP(), var1.getIP())) {
                return;
            }
            if (Config.PVP_INCREASE_SAME_HWID_CHECK && !Objects.isNull(this.getNetConnection()) && !Objects.isNull(var1.getNetConnection()) && StringUtils.isNotEmpty((CharSequence)this.getNetConnection().getHwid()) && Objects.equals(this.getNetConnection().getHwid(), var1.getNetConnection().getHwid())) {
                return;
            }
            var1.setPvpKills(var1.getPvpKills() + Config.PVP_POINTS_AMOUNT_ADD);
            OneDayRewardHolder.getInstance().fireRequirements(var1, null, PvpPointsRequirement.class);
            var1.getListeners().onPvpPkKill(this, false);
            if (Config.SERVICES_PK_ANNOUNCE > 0) {
                if (Config.SERVICES_PK_ANNOUNCE == 1) {
                    for (Player var4 : GameObjectsStorage.getAllPlayersForIterate()) {
                        if (!var4.getVarB("PvPAnnounce")) continue;
                        Announcements.getInstance().announceToPlayerByCustomMessage(var4, "service.pvp_pk_kill_announce", new String[]{var1.getName(), this.getName()}, ChatType.ANNOUNCEMENT);
                    }
                } else {
                    String var10 = new CustomMessage("service.pvp_pk_kill_announce", this, new Object[]{this.getName()}).addString(var1.getName()).toString();
                    for (Player var5 : GameObjectsStorage.getAllPlayersForIterate()) {
                        if (!var5.getVarB("PvPAnnounce")) continue;
                        var5.sendPacket((IStaticPacket)((SystemMessage)new SystemMessage(SystemMsg.S2_S1).addString(var10)).addZoneName(var1.getLoc()));
                    }
                }
            }
        }
        if (Config.SERVICES_PK_KILL_BONUS_ENABLE || Config.SERVICES_PVP_KILL_BONUS_ENABLE) {
            boolean var11 = true;
            boolean var13 = true;
            if (Config.SERVICES_PK_PVP_BONUS_TIE_IF_SAME_IP) {
                boolean bl = var11 = this.getIP() == null && var1.getIP() != null || this.getIP() != null && !this.getIP().equals(var1.getIP());
            }
            if (Config.SERVICES_PK_PVP_BONUS_TIE_IF_SAME_HWID) {
                String var14 = this.getNetConnection() != null ? this.getNetConnection().getHwid() : null;
                String var6 = var1.getNetConnection() != null ? var1.getNetConnection().getHwid() : null;
                var13 = var14 == null && var6 == null || var14 != null && !var14.equals(var6);
            }
            long var15 = System.currentTimeMillis();
            long var7 = var1.getVarLong(LAST_PVP_PK_KILL_VAR_NAME, 0L);
            if (this.isConnected() && var11 && var13 && var15 - var7 > Config.SERVICES_PK_KILL_BONUS_INTERVAL) {
                if (var2) {
                    if (Config.SERVICES_PK_KILL_BONUS_REWARD_ITEM != null && Config.SERVICES_PK_KILL_BONUS_REWARD_ITEM.length > 0) {
                        for (int var16 = 0; var16 < Config.SERVICES_PK_KILL_BONUS_REWARD_ITEM.length; ++var16) {
                            if (Config.SERVICES_PK_KILL_BONUS_REWARD_ITEM[var16] <= 0 || !Rnd.chance((double)Config.SERVICES_PK_KILL_BONUS_REWARD_CHANCE[var16])) continue;
                            ItemFunctions.addItem((Playable)var1, (int)Config.SERVICES_PK_KILL_BONUS_REWARD_ITEM[var16], (long)Config.SERVICES_PK_KILL_BONUS_REWARD_COUNT[var16], (boolean)true);
                        }
                    }
                } else if (Config.SERVICES_PVP_KILL_BONUS_REWARD_ITEM != null && Config.SERVICES_PVP_KILL_BONUS_REWARD_ITEM.length > 0) {
                    for (int var9 = 0; var9 < Config.SERVICES_PVP_KILL_BONUS_REWARD_ITEM.length; ++var9) {
                        if (Config.SERVICES_PVP_KILL_BONUS_REWARD_ITEM[var9] <= 0 || !Rnd.chance((double)Config.SERVICES_PVP_KILL_BONUS_REWARD_CHANCE[var9])) continue;
                        ItemFunctions.addItem((Playable)var1, (int)Config.SERVICES_PVP_KILL_BONUS_REWARD_ITEM[var9], (long)Config.SERVICES_PVP_KILL_BONUS_REWARD_COUNT[var9], (boolean)true);
                    }
                }
                var1.setVar(LAST_PVP_PK_KILL_VAR_NAME, var15, -1L);
            }
        }
    }

    public void checkAddItemToDrop(List<ItemInstance> var1, List<ItemInstance> var2, int var3) {
        for (int var4 = 0; var4 < var3 && !var2.isEmpty(); ++var4) {
            var1.add(var2.remove(Rnd.get((int)var2.size())));
        }
    }

    public FlagItemAttachment getActiveWeaponFlagAttachment() {
        ItemInstance var1 = this.getActiveWeaponInstance();
        return var1 != null && var1.getAttachment() instanceof FlagItemAttachment ? (FlagItemAttachment)var1.getAttachment() : null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void doPKPVPManage(Creature var1) {
        FlagItemAttachment var2 = this.getActiveWeaponFlagAttachment();
        if (var2 != null) {
            var2.onDeath(this, (Creature)((Object)var1));
        }
        if (var1 != null && var1 != this.var_3194 && var1 != this && (!this.isInZoneBattle() && !((Creature)((Object)var1)).isInZoneBattle() || Config.BATTLE_ZONE_PVP_COUNT)) {
            boolean var3 = this.isInZone(Zone.ZoneType.fun);
            if (!(!Config.FUN_ZONE_PVP_COUNT && var3 || var1 instanceof Summon && (var1 = var1.getPlayer()) == null)) {
                boolean var24;
                if (var1.isPlayer()) {
                    Player var4 = (Player)((Object)var1);
                    int var5 = this.getLevel() - var4.getLevel() >= 20 ? Config.CRP_REWARD_ON_WAR_KILL_OVER_LEVEL : Config.CRP_REWARD_ON_WAR_KILL;
                    boolean var6 = this.atMutualWarWith(var4);
                    if (var6 && var4.getClan().getReputationScore() > 0 && this.var_3182.getLevel() >= Config.MIN_CLAN_LEVEL_FOR_DECLARED_WAR && this.var_3182.getReputationScore() > 0 && var4.getClan().getLevel() >= Config.MIN_CLAN_LEVEL_FOR_DECLARED_WAR) {
                        this.var_3182.broadcastToOnlineMembers(new L2GameServerPacket[]{((SystemMessage)new SystemMessage(SystemMsg.BECAUSE_C1_WAS_KILLED_BY_A_CLAN_MEMBER_OF_S2_CLAN_REPUTATION_DECREASED_BY_1).addString(this.getName())).addString(var4.getClan().getName())});
                        this.var_3182.incReputation(-var5, true, "ClanWar");
                        var4.getClan().broadcastToOnlineMembers(new L2GameServerPacket[]{((SystemMessage)new SystemMessage(SystemMsg.BECAUSE_A_CLAN_MEMBER_OF_S1_WAS_KILLED_BY_C2_CLAN_REPUTATION_INCREASED_BY_1).addString(this.getClan().getName())).addString(var4.getName())});
                        var4.getClan().incReputation(var5, true, "ClanWar");
                        if (Config.EVENT_CLAN_WAR_POINTS > 0) {
                            var4.getClan().setCustomPoints(var4.getClan().getCustomPoints() + Config.EVENT_CLAN_WAR_POINTS);
                        }
                        ClanTable.getInstance().updateClanWarKillCounter(var4.getClan(), this.var_3182);
                    }
                    if (!Config.SIEGE_ZONE_PVP_COUNT && this.isOnSiegeField()) {
                        return;
                    }
                    if (Config.FUN_ZONE_PVP_COUNT && var3 || Config.BATTLE_ZONE_PVP_COUNT && (this.isInZoneBattle() || ((Creature)((Object)var1)).isInZoneBattle()) || Config.SIEGE_ZONE_PVP_COUNT && this.isOnSiegeField()) {
                        this.func169(var4, false);
                        var4.sendChanges();
                        return;
                    }
                    if (this._pvpFlag <= 0 && !var6) {
                        this.func169(var4, this.var_3119 <= 0);
                    } else {
                        this.func169(var4, false);
                    }
                    var4.sendChanges();
                }
                int var23 = this.var_3119;
                boolean bl = var24 = var1.isPlayable() || var1 instanceof GuardInstance;
                if (!(var1.isMonster() && !Config.DROP_ITEMS_ON_DIE || var24 && (this.var_3120 < Config.MIN_PK_TO_ITEMS_DROP || var23 == 0 && Config.KARMA_NEEDED_TO_DROP) || this.isFestivalParticipant() || !var1.isMonster() && !var24 || !Config.KARMA_DROP_GM && this.isGM() || Config.ITEM_ANTIDROP_FROM_PK > 0 && this.getInventory().getItemByItemId(Config.ITEM_ANTIDROP_FROM_PK) != null)) {
                    int var25 = var24 ? Config.KARMA_DROP_ITEM_LIMIT : 1;
                    double var7 = var24 ? (double)this.var_3120 * Config.KARMA_DROPCHANCE_MOD + Config.KARMA_DROPCHANCE_BASE : Config.NORMAL_DROPCHANCE_BASE;
                    int var9 = 0;
                    int var10 = 0;
                    int var11 = 0;
                    for (int var12 = 0; (double)var12 < Math.ceil(var7 / 100.0) && var12 < var25; ++var12) {
                        if (!Rnd.chance((double)var7)) continue;
                        int var13 = Rnd.get((int)(Config.DROPCHANCE_EQUIPPED_WEAPON + Config.DROPCHANCE_EQUIPMENT + Config.DROPCHANCE_ITEM)) + 1;
                        if (var13 > Config.DROPCHANCE_EQUIPPED_WEAPON + Config.DROPCHANCE_EQUIPMENT) {
                            ++var11;
                            continue;
                        }
                        if (var13 > Config.DROPCHANCE_EQUIPPED_WEAPON) {
                            ++var9;
                            continue;
                        }
                        ++var10;
                    }
                    LazyArrayList<ItemInstance> var26 = new LazyArrayList<>();
                    LazyArrayList<ItemInstance> var27 = new LazyArrayList<>();
                    LazyArrayList<ItemInstance> var14 = new LazyArrayList<>();
                    LazyArrayList<ItemInstance> var15 = new LazyArrayList<>();
                    this.getInventory().writeLock();
                    try {
                        for (ItemInstance var19 : this.getInventory().getItems()) {
                            if (!var19.canBeDropped(this, true) || Config.KARMA_LIST_NONDROPPABLE_ITEMS.contains(var19.getItemId())) continue;
                            if (var19.getTemplate().getType2() == 0) {
                                var15.add(var19);
                                continue;
                            }
                            if (var19.getTemplate().getType2() != 1 && var19.getTemplate().getType2() != 2) {
                                if (var19.getTemplate().getType2() != 5) continue;
                                var27.add(var19);
                                continue;
                            }
                            var14.add(var19);
                        }
                        this.checkAddItemToDrop(var26, var15, var10);
                        this.checkAddItemToDrop(var26, var14, var9);
                        this.checkAddItemToDrop(var26, var27, var11);
                        if (!var26.isEmpty()) {
                            for (ItemInstance var29 : var26) {
                                if (var29.isAugmented() && !Config.ALT_ALLOW_DROP_AUGMENTED) {
                                    var29.setVariationStat1(0);
                                    var29.setVariationStat2(0);
                                }
                                if (var29.isEnsouled() && !Config.ALT_ALLOW_DROP_ENSOULED) {
                                    var29.setEnsoulSlotN1(0);
                                    var29.setEnsoulSlotN2(0);
                                    var29.setEnsoulSlotBm(0);
                                }
                                if (var29.getVisibleItemId() > 0 && !Config.ALT_ALLOW_DROP_APPAREANCED) {
                                    var29.setVisibleItemId(0);
                                }
                                var29 = this.getInventory().removeItem(var29);
                                Log.LogItem((Player)this, (Log.ItemLog)Log.ItemLog.PvPDrop, (ItemInstance)var29);
                                if (var29.getEnchantLevel() > 0) {
                                    this.sendPacket((IStaticPacket)((SystemMessage)new SystemMessage(SystemMsg.YOU_HAVE_DROPPED_S1_S2).addNumber(var29.getEnchantLevel())).addItemName(var29.getItemId()));
                                } else {
                                    this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_HAVE_DROPPED_S1).addItemName(var29.getItemId()));
                                }
                                if (!(var1.isPlayable() && (Config.AUTO_LOOT && Config.AUTO_LOOT_PK || this.isInFlyingTransform()))) {
                                    var29.dropToTheGround((Playable)this, Location.findAroundPosition((GameObject)this, (int)Config.KARMA_RANDOM_DROP_LOCATION_LIMIT));
                                    continue;
                                }
                                var1.getPlayer().getInventory().addItem(var29);
                                Log.LogItem((Player)this, (Log.ItemLog)Log.ItemLog.Pickup, (ItemInstance)var29);
                                var1.getPlayer().sendPacket((IStaticPacket)SystemMessage.obtainItems((ItemInstance)var29));
                            }
                        }
                    }
                    finally {
                        this.getInventory().writeUnlock();
                    }
                }
            }
        }
    }

    protected void onDeath(Creature var1) {
        Quest var5;
        this.getDeathPenalty().checkCharmOfLuck();
        this.getFarmSystem().stopFarmTask();
        if (this.isInStoreMode()) {
            this.setPrivateStoreType(0);
        }
        if (this.isProcessingRequest()) {
            Request var2 = this.getRequest();
            if (this.isInTrade()) {
                Player var3 = var2.getOtherPlayer(this);
                this.sendPacket((IStaticPacket)SendTradeDone.FAIL);
                var3.sendPacket((IStaticPacket)SendTradeDone.FAIL);
            }
            var2.cancel();
        }
        this.setAgathion(0);
        boolean var4 = true;
        if (Config.ALLOW_CURSED_WEAPONS) {
            if (this.isCursedWeaponEquipped()) {
                CursedWeaponsManager.getInstance().dropPlayer(this);
                var4 = false;
            } else if (var1 != null && var1.isPlayer() && var1.isCursedWeaponEquipped()) {
                CursedWeaponsManager.getInstance().increaseKills(((Player)((Object)var1)).getCursedWeaponEquippedId());
                var4 = false;
            }
        }
        if (var4) {
            this.doPKPVPManage(var1);
            this.func168(var1);
        }
        this.getDeathPenalty().notifyDead(var1);
        if (Config.REMOVE_FORCE_CHARGE_ON_DEAD) {
            this.setIncreasedForce(0);
        }
        if (this.isInParty() && this.getParty().isInReflection() && this.getParty().getReflection() instanceof DimensionalRift) {
            ((DimensionalRift)this.getParty().getReflection()).memberDead(this);
        }
        this.stopWaterTask();
        this.func180();
        if (!this.isSalvation() && this.isOnSiegeField() && this.isCharmOfCourage()) {
            this.setCharmOfCourage(false);
        }
        if (this.getLevel() < 6 && (var5 = QuestManager.getQuest((int)255)) != null) {
            this.processQuestEvent(var5.getName(), "CE30", null);
        }
        super.onDeath(var1);
    }

    public void restoreExp() {
        this.restoreExp(100.0);
    }

    public void restoreExp(double var1) {
        if (var1 != 0.0) {
            int var3 = 0;
            String var4 = this.getVar("lostexp");
            if (var4 != null) {
                var3 = Integer.parseInt(var4);
                this.unsetVar("lostexp");
            }
            if (var3 != 0) {
                this.addExpAndSp((long)((double)var3 * var1 / 100.0), 0L);
            }
        }
    }

    public void deathPenalty(Creature var1) {
        if (var1 != null) {
            SiegeEvent<?,?> var9;
            boolean var2 = var1.getPlayer() != null && this.atWarWith(var1.getPlayer());
            double var3 = this.getDeathPenalty().getLevel() * Config.ALT_DEATH_PENALTY_C5_EXPERIENCE_PENALTY;
            var3 = var3 < 2.0 ? 1.0 : (var3 /= 2.0);
            double var5 = Config.LOOSE_EXP_ON_DEATH[this.getLevel()];
            int var7 = this.getLevel();
            if (Config.ALT_DEATH_PENALTY) {
                var5 = var5 * Config.RATE_XP + (double)this.var_3120 * Config.ALT_PK_DEATH_RATE;
            }
            if (this.isFestivalParticipant() || var2) {
                var5 /= 4.0;
            }
            int var8 = (int)Math.round((double)(Experience.LEVEL[var7 + 1] - Experience.LEVEL[var7]) * var5 / 100.0);
            var8 = (int)((double)var8 * var3);
            var8 = (int)this.calcStat(Stats.EXP_LOST, var8, var1, null);
            if (this.isOnSiegeField() && (var9 = (SiegeEvent<?,?>)this.getEvent(SiegeEvent.class)) != null) {
                var8 = 0;
            }
            long var18 = this.getExp();
            this.addExpAndSp(-var8, 0L);
            long var11 = var18 - this.getExp();
            if (!this.isCursedWeaponEquipped() && var8 > 0 && this.getKarma() > 0) {
                int var13 = Config.KARMA_STATIC_LOST_ON_DEATH;
                int var14 = Formulas.calculateKarmaLost((Player)this, (long)var8);
                if (var13 != -1) {
                    this.decreaseKarma(Math.max(var13, 0));
                } else {
                    this.decreaseKarma(Math.max(var14, 0));
                }
            }
            if (var11 > 0L) {
                this.setVar("lostexp", String.valueOf(var11), -1L);
            }
        }
    }

    public Request getRequest() {
        return this.var_3198;
    }

    public void setRequest(Request var1) {
        this.var_3198 = var1;
    }

    public boolean isBusy() {
        return this.isProcessingRequest() || this.isOutOfControl() || this.isOlyParticipant() || this.getTeam() != TeamType.NONE || this.isInStoreMode() || this.isInDuel() || this.getMessageRefusal() || this.isBlockAll() || this.isInvisible();
    }

    public boolean isProcessingRequest() {
        if (this.var_3198 == null) {
            return false;
        }
        return this.var_3198.isInProgress();
    }

    public boolean isInTrade() {
        return this.isProcessingRequest() && this.getRequest().isTypeOf(Request.L2RequestType.TRADE);
    }

    public List<L2GameServerPacket> addVisibleObject(GameObject var1, Creature var2) {
        return !this.isLogoutStarted() && var1 != null && var1.getObjectId() != this.getObjectId() && var1.isVisible() ? var1.addPacketList(this, var2) : Collections.emptyList();
    }

    public List<L2GameServerPacket> addPacketList(Player var1, Creature var2) {
        if (this.isInvisible() && var1.getObjectId() != this.getObjectId()) {
            return Collections.emptyList();
        }
        if (this.getPrivateStoreType() != 0 && var1.getVarB(NO_TRADERS_VAR)) {
            return Collections.emptyList();
        }
        if (this.isInObserverMode() && this.getCurrentRegion() != this.getObserverRegion() && this.getObserverRegion() == var1.getCurrentRegion()) {
            return Collections.emptyList();
        }
        ArrayList<L2GameServerPacket> var3 = new ArrayList<L2GameServerPacket>();
        if (var1.getObjectId() != this.getObjectId()) {
            var3.add((L2GameServerPacket)(this.isPolymorphed() ? new NpcInfo(this) : new CharInfo(this)));
        }
        if (this.isSitting() && this.var_3224 != null) {
            var3.add((L2GameServerPacket)new ChairSit(this, this.var_3224));
        }
        if (this.getPrivateStoreType() != 0) {
            if (this.getPrivateStoreType() == 3) {
                var3.add((L2GameServerPacket)new PrivateStoreMsgBuy(this));
            } else if (this.getPrivateStoreType() == 1) {
                var3.add((L2GameServerPacket)new PrivateStoreMsgSell(this));
            } else if (this.getPrivateStoreType() == 8) {
                var3.add((L2GameServerPacket)new ExPrivateStoreSetWholeMsg(this));
            } else if (this.getPrivateStoreType() == 5) {
                var3.add((L2GameServerPacket)new RecipeShopMsg(this));
            }
            if (var1.isInZonePeace()) {
                return var3;
            }
        }
        if (this.isCastingNow()) {
            Creature var4 = this.getCastingTarget();
            Skill var5 = this.getCastingSkill();
            long var6 = this.getAnimationEndTime();
            if (var5 != null && var4 != null && var4.isCreature() && this.getAnimationEndTime() > 0L) {
                var3.add((L2GameServerPacket)new MagicSkillUse((Creature)((Object)this), var4, var5, (int)(var6 - System.currentTimeMillis()), 0L));
            }
        }
        if (this.isInCombat()) {
            var3.add((L2GameServerPacket)new AutoAttackStart(this.getObjectId()));
        }
        var3.add((L2GameServerPacket)new RelationChanged().add(this, var1));
        if (this.isInBoat()) {
            var3.add(this.getBoat().getOnPacket(this, this.getInBoatPosition()));
        } else if (this.isMoving() || this.isFollowing()) {
            var3.add(this.movePacket());
        }
        if (this.isInMountTransform()) {
            var3.add((L2GameServerPacket)new CharInfo(this));
        }
        return var3;
    }

    public List<L2GameServerPacket> removeVisibleObject(GameObject var1, List<L2GameServerPacket> var2) {
        if (!this.isLogoutStarted() && var1 != null && var1.getObjectId() != this.getObjectId()) {
            List<L2GameServerPacket> var3;
            List<L2GameServerPacket> list = var3 = var2 == null ? var1.deletePacketList() : var2;
            if (this.isFollowing() && this.getFollowTarget() == var1) {
                this.stopMove();
            }
            this.getAI().notifyEvent(CtrlEvent.EVT_FORGET_OBJECT, (Object)var1);
            return var3;
        }
        return null;
    }

    private void func170(int var1) {
        if (var1 < Experience.LEVEL.length - 1) {
            this.sendPacket((IStaticPacket)SystemMsg.YOUR_LEVEL_HAS_INCREASED);
            this.broadcastPacket(new L2GameServerPacket[]{new SocialAction(this.getObjectId(), 2122)});
        }
    }

    private void func171(int var1, int var2) {
        if (var1 > 0) {
            this.setCurrentHpMp(this.getMaxHp(), this.getMaxMp());
            this.setCurrentCp(this.getMaxCp());
            Quest var3 = QuestManager.getQuest((int)255);
            if (var3 != null) {
                this.processQuestEvent(var3.getName(), "CE40", null);
            }
            this.sendPacket((IStaticPacket)new ExVoteSystemInfo(this));
            OneDayRewardHolder.getInstance().fireRequirements(this, null, ObtainLevelRequirement.class);
        } else if (var1 < 0) {
            this.checkSkills();
        }
        if (this.isInParty()) {
            this.getParty().recalculatePartyData();
        }
        if (this.var_3182 != null) {
            this.var_3182.broadcastToOnlineMembers(new L2GameServerPacket[]{new PledgeShowMemberListUpdate(this)});
        }
        if (this.var_3271 != null) {
            this.var_3271.broadcastPlayerUpdate(this);
        }
        this.func163(true, var2);
        this.getListeners().onSetLevel(this.getLevel());
    }

    public void checkSkills() {
        if (!Config.ALT_WEAK_SKILL_LEARN) {
            for (Skill var4 : this.getAllSkillsArray()) {
                SkillTreeTable.checkSkill((Player)this, (Skill)var4);
            }
            this.sendSkillList();
        }
    }

    public void startTimers() {
        this.startAutoSaveTask();
        this.startPcBangPointsTask();
        this.startBonusTask();
        this.getInventory().startTimers();
        this.resumeQuestTimers();
    }

    public void stopAllTimers() {
        try {
            this.setAgathion(0);
            this.stopWaterTask();
            this.stopBonusTask();
            this.stopHourlyTask();
            this.stopKickTask();
            this.func180();
            this.stopPcBangPointsTask();
            this.stopAutoSaveTask();
            this.getInventory().stopAllTimers();
            this.stopQuestTimers();
            this.func181();
            this.stopUnjailTask();
            this.getFarmSystem().stopFarmTask();
            if (this.var_3304 != null) {
                this.var_3304.cancel(false);
                this.var_3304 = null;
            }
        }
        catch (Exception var2) {
            logger.error("Error stopping timers for player: " + this.getName(), (Throwable)var2);
        }
    }

    public Summon getPet() {
        return this.var_3194;
    }

    public void setPet(Summon var1) {
        boolean var2 = this.var_3194 != null && this.var_3194.isPet();
        this.unsetVar("pet");
        this.var_3194 = var1;
        this.autoShot();
        if (var1 == null) {
            if (var2) {
                if (this.isLogoutStarted() && this.getPetControlItem() != null) {
                    this.setVar("pet", String.valueOf(this.getPetControlItem().getObjectId()), -1L);
                }
                this.setPetControlItem(null);
            }
            this.getEffectList().stopEffect(4140);
        }
    }

    public void scheduleDelete() {
        long var1 = 0L;
        if (Config.SERVICES_ENABLE_NO_CARRIER) {
            var1 = NumberUtils.toInt((String)this.getVar("noCarrier"), (int)Config.SERVICES_NO_CARRIER_DEFAULT_TIME);
        }
        this.scheduleDelete(var1 * 1000L);
    }

    public void scheduleDelete(long var1) {
        if (!this.isLogoutStarted() && !this.isInOfflineMode()) {
            this.broadcastCharInfo();
            ThreadPoolManager.getInstance().schedule((Runnable)new RunnableImpl(){

                public void runImpl() {
                    if (!Player.this.isConnected()) {
                        Player.this.func159();
                        Player.this.deleteMe();
                    }
                }
            }, var1);
            if (var1 > 0L && this.getTeam() == TeamType.NONE && !this.isOlyParticipant() && !this.isInAnyZone(new Zone.ZoneType[]{Zone.ZoneType.peace_zone, Zone.ZoneType.SIEGE, Zone.ZoneType.offshore})) {
                this.setNoCarrierProtectionTime(System.currentTimeMillis() + 1000L * Math.min(var1, Config.NO_CARRIER_PROTECTION_TIME));
            }
        }
    }

    protected void onDelete() {
        try {
            super.onDelete();
            WorldRegion var1 = this.getObserverRegion();
            if (var1 != null) {
                var1.removeObject((GameObject)this);
            }
            this.var_3215.notifyFriends(false);
            this.var_3152.clear();
            this.var_3153.clear();
            this.var_3194 = null;
            this.var_3199 = null;
            this.var_3200 = null;
            this.var_3201 = null;
            this.var_3204 = null;
            this.var_3209 = HardReferences.emptyRef();
            this.var_3212 = null;
            this._transformationSkills.clear();
            this.var_3294.clear();
            this.var_3262.clear();
            if (this.var_3320 != null) {
                this.var_3320.clear();
            }
            this.var_3268.clear();
            this.var_3161.clear();
            this.var_3158.clear();
            this.var_3159.clear();
            this.var_3160.clear();
            this.var_3263.clear();
            this.var_3272.clear();
            this.var_3157.clear();
            this.var_3211.clear();
            if (this.var_3196 != null) {
                this.var_3196.clear();
            }
            if (this.var_3277 != null) {
                this.var_3277.clear();
            }
            if (this.var_3311 != null) {
                this.var_3311.deleteMe();
                this.var_3311 = null;
            }
            this.var_3162.clear();
        }
        catch (Exception var2) {
            logger.error("Error in onDelete for player: " + this.getName(), (Throwable)var2);
        }
    }

    public List<TradeItem> getTradeList() {
        return this.var_3172;
    }

    public void setTradeList(List<TradeItem> var1) {
        this.var_3172 = var1;
    }

    public String getSellStoreName() {
        return this.var_3167;
    }

    public void setSellStoreName(String var1) {
        this.var_3167 = Strings.stripToSingleLine((String)var1);
    }

    public void setSellList(boolean var1, List<TradeItem> var2) {
        if (var1) {
            this.var_3169 = var2;
        } else {
            this.var_3168 = var2;
        }
    }

    public List<TradeItem> getSellList() {
        switch (this.getPrivateStoreType()) {
            case 1: {
                return this.getSellList(false);
            }
            case 8: {
                return this.getSellList(true);
            }
        }
        return Collections.emptyList();
    }

    public List<TradeItem> getSellList(boolean var1) {
        return var1 ? this.var_3169 : this.var_3168;
    }

    public String getBuyStoreName() {
        return this.var_3170;
    }

    public void setBuyStoreName(String var1) {
        this.var_3170 = Strings.stripToSingleLine((String)var1);
    }

    public List<TradeItem> getBuyList() {
        List<TradeItem> var1 = this.var_3171;
        return var1 != null ? var1 : Collections.emptyList();
    }

    public void setBuyList(List<TradeItem> var1) {
        this.var_3171 = var1;
    }

    public String getManufactureName() {
        return this.var_3165;
    }

    public void setManufactureName(String var1) {
        this.var_3165 = Strings.stripToSingleLine((String)var1);
    }

    public List<ManufactureItem> getCreateList() {
        return this.var_3166;
    }

    public void setCreateList(List<ManufactureItem> var1) {
        this.var_3166 = var1;
    }

    public boolean isInStoreMode() {
        return this.var_3164.get() != 0;
    }

    public int getPrivateStoreType() {
        return this.var_3164.get();
    }

    public void setPrivateStoreType(int var1) {
        int var2 = this.var_3164.get();
        if (var2 != var1 && this.var_3164.compareAndSet(var2, var1)) {
            if (var1 != 0) {
                if (var2 == 0) {
                    this.sitDown(null);
                    this.broadcastCharInfo();
                }
                this.setVar("storemode", String.valueOf(var1), -1L);
            } else if (var2 != 0) {
                this.unsetVar("storemode");
                if (!this.isDead()) {
                    this.standUp();
                    this.broadcastCharInfo();
                }
            }
        }
        this.getListeners().onSetPrivateStoreType(var1);
    }

    public Clan getClan() {
        return this.var_3182;
    }

    public void setClan(Clan var1) {
        Clan var2;
        if (this.var_3182 != var1 && this.var_3182 != null) {
            this.unsetVar("canWhWithdraw");
        }
        if ((var2 = this.var_3182) != null && var1 == null) {
            for (Skill var6 : var2.getAllSkills()) {
                this.removeSkill(var6, false);
            }
        }
        this.var_3182 = var1;
        if (var1 == null) {
            this.var_3184 = -128;
            this.var_3183 = 0;
            this.var_3185 = 0;
            this.var_3187 = 0;
            this.getInventory().validateItems();
        } else if (!var1.isAnyMember(this.getObjectId())) {
            this.setClan(null);
            if (!this.isNoble()) {
                this.setTitle("");
            }
        }
    }

    public SubUnit getSubUnit() {
        return this.var_3182 == null ? null : this.var_3182.getSubUnit(this.var_3184);
    }

    public ClanHall getClanHall() {
        int var1 = this.var_3182 != null ? this.var_3182.getHasHideout() : 0;
        return (ClanHall)ResidenceHolder.getInstance().getResidence(ClanHall.class, var1);
    }

    public Castle getCastle() {
        int var1 = this.var_3182 != null ? this.var_3182.getCastle() : 0;
        return (Castle)ResidenceHolder.getInstance().getResidence(Castle.class, var1);
    }

    public Alliance getAlliance() {
        return this.var_3182 == null ? null : this.var_3182.getAlliance();
    }

    public boolean isClanLeader() {
        return this.var_3182 != null && this.getObjectId() == this.var_3182.getLeaderId();
    }

    public boolean isAllyLeader() {
        return this.getAlliance() != null && this.getAlliance().getLeader().getLeaderId() == this.getObjectId();
    }

    public void reduceArrowCount() {
        if (this.var_3199 == null || !this.var_3199.getTemplate().isQuiver() || this.var_3199 != this.getInventory().getPaperdollItem(7)) {
            this.sendPacket((IStaticPacket)SystemMsg.YOU_CAREFULLY_NOCK_AN_ARROW);
            if (!this.getInventory().destroyItemByObjectId(this.getInventory().getPaperdollObjectId(7), 1L)) {
                this.getInventory().setPaperdollItem(7, null);
                this.var_3199 = null;
            }
        }
    }

    protected boolean checkAndEquipArrows() {
        if (this.getInventory().getPaperdollItem(7) == null) {
            ItemInstance var1 = this.getActiveWeaponInstance();
            if (var1 != null && var1.getItemType() == WeaponTemplate.WeaponType.BOW) {
                this.var_3199 = this.getInventory().findArrowForBow(var1.getTemplate());
            }
            if (this.var_3199 != null) {
                this.getInventory().setPaperdollItem(7, this.var_3199);
            }
        } else {
            this.var_3199 = this.getInventory().getPaperdollItem(7);
        }
        return this.var_3199 != null;
    }

    public long getUptime() {
        return System.currentTimeMillis() - this.var_3138;
    }

    public void setUptime(long var1) {
        this.var_3138 = var1;
    }

    public boolean isInParty() {
        return this.var_3180 != null;
    }

    public void joinParty(Party var1) {
        if (var1 != null) {
            var1.addPartyMember(this);
        }
    }

    public void leaveParty() {
        if (this.isInParty()) {
            this.var_3180.removePartyMember(this, false);
        }
    }

    public Party getParty() {
        return this.var_3180;
    }

    public void setParty(Party var1) {
        this.var_3180 = var1;
    }

    public Location getLastPartyPosition() {
        return this.var_3181;
    }

    public void setLastPartyPosition(Location var1) {
        this.var_3181 = var1;
    }

    public boolean isGM() {
        return this.var_3189 != null && this.var_3189.IsGM;
    }

    public int getAccessLevel() {
        return this.var_3188;
    }

    public void setAccessLevel(int var1) {
        this.var_3188 = var1;
    }

    public PlayerAccess getPlayerAccess() {
        return this.var_3189;
    }

    public void setPlayerAccess(PlayerAccess var1) {
        this.var_3189 = var1 != null ? var1 : new PlayerAccess();
        this.setAccessLevel(!this.isGM() && !this.var_3189.Menu ? 0 : 100);
    }

    public double getLevelMod() {
        return (89.0 + (double)this.getLevel()) / 100.0;
    }

    public void updateStats() {
        if (!this.entering && !this.isLogoutStarted()) {
            this.refreshOverloaded();
            this.refreshExpertisePenalty();
            super.updateStats();
        }
    }

    public void sendChanges() {
        if (!this.entering && !this.isLogoutStarted()) {
            super.sendChanges();
        }
    }

    public void updateKarma(boolean var1) {
        this.sendStatusUpdate(true, true, 27);
        if (var1) {
            this.broadcastRelation();
        }
    }

    public boolean isOnline() {
        return this.var_3207;
    }

    public void setIsOnline(boolean var1) {
        this.var_3207 = var1;
    }

    public void setOnlineStatus(boolean var1) {
        this.var_3207 = var1;
        this.func172();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void func172() {
        Connection var1 = null;
        PreparedStatement var2 = null;
        try {
            var1 = DatabaseFactory.getInstance().getConnection();
            var2 = var1.prepareStatement("UPDATE `characters` SET `online`=?, `lastAccess`=? WHERE `obj_id`=?");
            var2.setInt(1, this.isOnline() && !this.isInOfflineMode() ? 1 : 0);
            var2.setLong(2, System.currentTimeMillis() / 1000L);
            var2.setInt(3, this.getObjectId());
            var2.execute();
        }
        catch (Exception var7) {
            try {
                logger.error("", (Throwable)var7);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var1, var2);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var1, (Statement)var2);
        }
        DbUtils.closeQuietly((Connection)var1, (Statement)var2);
    }

    public void increaseKarma(long var1) {
        boolean var3 = this.var_3119 == 0;
        long var4 = (long)this.var_3119 + var1;
        if (var4 > Integer.MAX_VALUE) {
            var4 = Integer.MAX_VALUE;
        }
        if (this.var_3119 == 0 && var4 > 0L) {
            if (this._pvpFlag > 0) {
                this._pvpFlag = 0;
                if (this.var_3309 != null) {
                    this.var_3309.cancel(true);
                    this.var_3309 = null;
                }
                this.sendStatusUpdate(true, true, 26);
            }
            this.var_3119 = (int)var4;
        } else {
            this.var_3119 = (int)var4;
        }
        this.updateKarma(var3);
    }

    public void decreaseKarma(int var1) {
        boolean var2 = this.var_3119 > 0;
        this.var_3119 -= var1;
        if (this.var_3119 <= 0) {
            this.var_3119 = 0;
            this.updateKarma(var2);
        } else {
            this.updateKarma(false);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void func173() {
        Connection var1 = null;
        PreparedStatement var2 = null;
        ResultSet var3 = null;
        try {
            var1 = DatabaseFactory.getInstance().getConnection();
            var2 = var1.prepareStatement("SELECT `itemNum`, `itemId`, `itemCount`, `itemSender` FROM `character_premium_items` WHERE `charId`=?");
            var2.setInt(1, this.getObjectId());
            var3 = var2.executeQuery();
            while (var3.next()) {
                int var4 = var3.getInt("itemNum");
                int var5 = var3.getInt("itemId");
                long var6 = var3.getLong("itemCount");
                String var8 = var3.getString("itemSender");
                PremiumItem var9 = new PremiumItem(var5, var6, var8);
                this.var_3160.put(var4, var9);
            }
        }
        catch (Exception var13) {
            try {
                logger.error("", (Throwable)var13);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var1, var2, var3);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var1, (Statement)var2, var3);
        }
        DbUtils.closeQuietly((Connection)var1, (Statement)var2, (ResultSet)var3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updatePremiumItem(int var1, long var2) {
        Connection var4 = null;
        PreparedStatement var5 = null;
        try {
            var4 = DatabaseFactory.getInstance().getConnection();
            var5 = var4.prepareStatement("UPDATE `character_premium_items` SET `itemCount`=? WHERE `charId`=? AND `itemNum`=?");
            var5.setLong(1, var2);
            var5.setInt(2, this.getObjectId());
            var5.setInt(3, var1);
            var5.execute();
        }
        catch (Exception var10) {
            try {
                logger.error("", (Throwable)var10);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var4, var5);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var4, (Statement)var5);
        }
        DbUtils.closeQuietly((Connection)var4, (Statement)var5);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void deletePremiumItem(int var1) {
        Connection var2 = null;
        PreparedStatement var3 = null;
        try {
            var2 = DatabaseFactory.getInstance().getConnection();
            var3 = var2.prepareStatement("DELETE FROM `character_premium_items` WHERE `charId`=? AND `itemNum`=?");
            var3.setInt(1, this.getObjectId());
            var3.setInt(2, var1);
            var3.execute();
        }
        catch (Exception var8) {
            try {
                logger.error("", (Throwable)var8);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var2, var3);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var2, (Statement)var3);
        }
        DbUtils.closeQuietly((Connection)var2, (Statement)var3);
    }

    public Map<Integer, PremiumItem> getPremiumItemList() {
        return this.var_3160;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void store(boolean var1) {
        block11: {
            if (this.var_3238.tryLock()) {
                try {
                    Connection var2 = null;
                    PreparedStatement var3 = null;
                    try {
                        var2 = DatabaseFactory.getInstance().getConnection();
                        var3 = var2.prepareStatement("UPDATE characters SET face=?,hairStyle=?,hairColor=?,x=?,y=?,z=?,karma=?,pvpkills=?,pkkills=?,rec_have=?,rec_left=?,rec_bonus_time=?,hunting_bonus_time=?,rec_tick_cnt=?,hunting_bonus=?,clanid=?,deletetime=?,title=?,accesslevel=?,online=?,leaveclan=?,deleteclan=?,nochannel=?,onlinetime=?,pledge_type=?,pledge_rank=?,lvl_joined_academy=?,apprentice=?,key_bindings=?,pcBangPoints=?,raidBossPoints=?,char_name=?,bookmarks=?,vitality=? WHERE obj_Id=? LIMIT 1");
                        var3.setInt(1, this.getFace());
                        var3.setInt(2, this.getHairStyle());
                        var3.setInt(3, this.getHairColor());
                        if (this._stablePoint == null) {
                            var3.setInt(4, this.getX());
                            var3.setInt(5, this.getY());
                            var3.setInt(6, this.getZ());
                        } else {
                            var3.setInt(4, this._stablePoint.x);
                            var3.setInt(5, this._stablePoint.y);
                            var3.setInt(6, this._stablePoint.z);
                        }
                        var3.setInt(7, this.getKarma());
                        var3.setInt(8, this.getPvpKills());
                        var3.setInt(9, this.getPkKills());
                        var3.setInt(10, this.getReceivedRec());
                        var3.setInt(11, this.getGivableRec());
                        var3.setInt(12, 0);
                        var3.setInt(13, 0);
                        var3.setInt(14, 0);
                        var3.setInt(15, 0);
                        var3.setInt(16, this.getClanId());
                        var3.setInt(17, this.getDeleteTimer());
                        var3.setString(18, this._title);
                        var3.setInt(19, this.var_3188);
                        var3.setInt(20, this.isOnline() && !this.isInOfflineMode() ? 1 : 0);
                        var3.setLong(21, this.getLeaveClanTime() / 1000L);
                        var3.setLong(22, this.getDeleteClanTime() / 1000L);
                        var3.setLong(23, this.var_3134 > 0L ? this.getNoChannelRemained() / 1000L : this.var_3134);
                        var3.setInt(24, (int)(this.var_3131 > 0L ? (this.var_3130 + System.currentTimeMillis() - this.var_3131) / 1000L : this.var_3130 / 1000L));
                        var3.setInt(25, this.getPledgeType());
                        var3.setInt(26, this.getPowerGrade());
                        var3.setInt(27, this.getLvlJoinedAcademy());
                        var3.setInt(28, this.getApprentice());
                        var3.setBytes(29, this.getKeyBindings());
                        var3.setInt(30, this.getPcBangPoints());
                        var3.setInt(31, this.getRaidBossPoints());
                        var3.setString(32, this.getName());
                        var3.setInt(33, this.getTpBookmarkSize());
                        var3.setInt(34, (int)this.getVitality());
                        var3.setInt(35, this.getObjectId());
                        var3.executeUpdate();
                        GameStats.increaseUpdatePlayerBase();
                        if (!var1) {
                            EffectsDAO.getInstance().insert((Playable)this);
                            CharacterGroupReuseDAO.getInstance().insert(this);
                            this.storeDisableSkills();
                        }
                        this.storeCharSubClasses();
                        this.func161();
                        this.getCostumeList().save();
                    }
                    catch (Exception var13) {
                        try {
                            logger.error("Could not store char data: " + this + "!", (Throwable)var13);
                        }
                        catch (Throwable throwable) {
                            DbUtils.closeQuietly((Connection)var2, var3);
                            throw throwable;
                        }
                        DbUtils.closeQuietly((Connection)var2, (Statement)var3);
                        break block11;
                    }
                    DbUtils.closeQuietly((Connection)var2, (Statement)var3);
                }
                finally {
                    this.var_3238.unlock();
                }
            }
        }
    }

    public Skill addSkill(Skill var1, boolean var2) {
        if (var1 == null) {
            return null;
        }
        Skill var3 = super.addSkill(var1);
        if (var1.equals((Object)var3)) {
            return var3;
        }
        if (var2) {
            this.func174(var1, var3);
        }
        return var3;
    }

    public Skill removeSkill(Skill var1, boolean var2) {
        return var1 == null ? null : this.removeSkill(var1.getId(), var2);
    }

    public Skill removeSkill(int var1, boolean var2) {
        Skill var3 = super.removeSkillById(Integer.valueOf(var1));
        if (!var2) {
            return var3;
        }
        if (var3 != null) {
            CharacterSkillsDAO.getInstance().delete(this, var1);
        }
        return var3;
    }

    public void removeSkills(boolean var1, Skill ... var2) {
        if (var2 != null && var2.length != 0) {
            this.removeSkills(Arrays.asList(var2), var1);
        }
    }

    public void removeSkills(Collection<Skill> var1, boolean var2) {
        if (var1 != null && !var1.isEmpty()) {
            List<Integer> var3 = var1.stream().map(Skill::getId).collect(Collectors.toList());
            for (Skill var5 : var1) {
                super.removeSkillById(Integer.valueOf(var5.getId()));
            }
            if (var2) {
                CharacterSkillsDAO.getInstance().delete(this, var3);
            }
        }
    }

    private void func174(Skill var1, Skill var2) {
        if (var1 == null) {
            logger.warn("could not store new skill. its NULL");
        } else {
            CharacterSkillsDAO.getInstance().store(this, new Skill[]{var1});
        }
    }

    private void func175() {
        this.func176(this.getActiveClassId());
    }

    private void func176(int var1) {
        ArrayList<Skill> var2 = new ArrayList<Skill>();
        for (Skill var4 : CharacterSkillsDAO.getInstance().getCharacterSkills(this.getObjectId(), var1)) {
            if (!(this.isGM() || Config.ALT_WEAK_SKILL_LEARN || SkillAcquireHolder.getInstance().isSkillPossible(this, var4))) {
                Logger var10000 = logger;
                String var10001 = var4.toString();
                var10000.warn("Skill " + var10001 + " not possible for player " + this + " with classId " + this.getActiveClassId());
                var2.add(var4);
                this.removeSkillFromShortCut(var4.getId());
                continue;
            }
            super.addSkill(var4);
        }
        if (!var2.isEmpty()) {
            this.removeSkills(var2, true);
        }
        if (this.getActiveClassId() == var1) {
            this.updateNobleSkills();
            this.updatePremiumSkills();
            if (this.var_3218 && (!this.isSubClassActive() || Config.ALT_ALLOW_HERO_SKILLS_ON_SUB_CLASS) && (HeroController.getInstance().isCurrentHero(this) || Config.ALT_ALLOW_CUSTOM_HERO_SKILLS)) {
                HeroController.addSkills((Player)this);
            }
            if (this.var_3182 != null) {
                this.var_3182.addSkillsQuietly(this);
                if (this.var_3182.getLeaderId() == this.getObjectId() && this.var_3182.getLevel() >= Config.MIN_CLAN_LEVEL_FOR_SIEGE_REGISTRATION) {
                    Clan.addClanLeaderSkills((Player)this);
                }
            }
            this.addSkill(SkillTable.getInstance().getInfo(17192, 1));
            if (Config.UNSTUCK_SKILL && this.getSkillLevel(1050) < 0) {
                this.addSkill(SkillTable.getInstance().getInfo(2099, 1));
            }
            if (Config.BLOCK_BUFF_SKILL) {
                this.addSkill(SkillTable.getInstance().getInfo(5088, 1));
            }
            if (Config.NOBLES_BUFF_SKILL) {
                this.addSkill(SkillTable.getInstance().getInfo(1323, 1));
            }
            for (int var5 = 0; var5 < Config.ADDITIONALS_SKILLS.length; var5 += 2) {
                this.addSkill(SkillTable.getInstance().getInfo(Config.ADDITIONALS_SKILLS[var5], Config.ADDITIONALS_SKILLS[var5 + 1]));
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void storeDisableSkills() {
        PreparedStatement var2;
        Connection var1;
        block8: {
            var1 = null;
            var2 = null;
            try {
                var1 = DatabaseFactory.getInstance().getConnection();
                var2 = var1.prepareStatement("DELETE FROM `character_skills_save` WHERE `char_obj_id` = ? AND (`class_index`=? OR `class_index`=-1) AND `end_time` < ?");
                var2.setInt(1, this.getObjectId());
                var2.setInt(2, this.getActiveClassId());
                var2.setLong(3, System.currentTimeMillis());
                var2.executeUpdate();
                DbUtils.close((Statement)var2);
                if (this.getSkillReuses0().isEmpty()) break block8;
                var2 = var1.prepareStatement("REPLACE INTO `character_skills_save`(`char_obj_id`, `skill_id`, `skill_level`, `class_index`, `end_time`, `reuse_delay_org`) VALUES\t(?,?,?,?,?,?)");
                CHashIntObjectMap<TimeStamp> var3 = new CHashIntObjectMap<>();
                IntObjectMap<TimeStamp> intObjectMap = this.getSkillReuses0();
                synchronized (intObjectMap) {
                    var3.putAll(this.getSkillReuses0());
                }
                for (TimeStamp var5 : var3.values()) {
                    Skill var6 = SkillTable.getInstance().getInfo(var5.getId(), var5.getLevel());
                    if (var6 == null) continue;
                    var2.setInt(1, this.getObjectId());
                    var2.setInt(2, var6.getId());
                    var2.setInt(3, var6.getLevel());
                    var2.setInt(4, !var6.isSharedClassReuse() ? this.getActiveClassId() : -1);
                    var2.setLong(5, var5.getEndTime());
                    var2.setLong(6, var5.getReuseBasic());
                    var2.executeUpdate();
                }
            }
            catch (Exception var12) {
                try {
                    logger.warn("Could not store disable skills data: " + var12);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly((Connection)var1, var2);
                    throw throwable;
                }
                DbUtils.closeQuietly((Connection)var1, (Statement)var2);
            }
        }
        DbUtils.closeQuietly((Connection)var1, (Statement)var2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void restoreDisableSkills() {
        this.getSkillReuses0().clear();
        Connection var1 = null;
        PreparedStatement var2 = null;
        ResultSet var3 = null;
        try {
            var1 = DatabaseFactory.getInstance().getConnection();
            var2 = var1.prepareStatement("SELECT `skill_id`, `skill_level`, `end_time`, `reuse_delay_org` FROM `character_skills_save` WHERE `char_obj_id`=? AND (`class_index`=? OR `class_index`=-1)");
            var2.setInt(1, this.getObjectId());
            var2.setInt(2, this.getActiveClassId());
            var3 = var2.executeQuery();
            while (var3.next()) {
                int var4 = var3.getInt("skill_id");
                int var5 = var3.getInt("skill_level");
                long var6 = var3.getLong("end_time");
                long var8 = var3.getLong("reuse_delay_org");
                long var10 = System.currentTimeMillis();
                Skill var12 = SkillTable.getInstance().getInfo(var4, var5);
                if (var12 == null || var6 - var10 <= 500L) continue;
                this.getSkillReuses0().put(var12.hashCode(), new TimeStamp(var12, var6, var8));
            }
            DbUtils.close((Statement)var2);
            var2 = var1.prepareStatement("DELETE FROM `character_skills_save` WHERE `char_obj_id` = ? AND (`class_index`=? OR `class_index`=-1) AND `end_time` < ?");
            var2.setInt(1, this.getObjectId());
            var2.setInt(2, this.getActiveClassId());
            var2.setLong(3, System.currentTimeMillis());
            var2.executeUpdate();
        }
        catch (Exception var16) {
            try {
                logger.error("Could not restore active skills data!", (Throwable)var16);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var1, var2, var3);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var1, (Statement)var2, var3);
        }
        DbUtils.closeQuietly((Connection)var1, (Statement)var2, (ResultSet)var3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void func177() {
        Connection var1 = null;
        PreparedStatement var2 = null;
        ResultSet var3 = null;
        try {
            var1 = DatabaseFactory.getInstance().getConnection();
            var2 = var1.prepareStatement("SELECT `slot`, `symbol_id` FROM `character_hennas` WHERE `char_obj_id`=? AND `class_index`=?");
            var2.setInt(1, this.getObjectId());
            var2.setInt(2, this.getActiveClassId());
            var3 = var2.executeQuery();
            for (int var4 = 0; var4 < 3; ++var4) {
                this.var_3173[var4] = null;
            }
            while (var3.next()) {
                Henna var6;
                int var5;
                int var12 = var3.getInt("slot");
                if (var12 < 1 || var12 > 3 || (var5 = var3.getInt("symbol_id")) == 0 || (var6 = HennaHolder.getInstance().getHenna(var5)) == null) continue;
                this.var_3173[var12 - 1] = var6;
            }
        }
        catch (Exception var10) {
            try {
                logger.warn("could not restore henna: " + var10);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var1, var2, var3);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var1, (Statement)var2, var3);
        }
        DbUtils.closeQuietly((Connection)var1, (Statement)var2, (ResultSet)var3);
        this.func178();
    }

    public int getHennaEmptySlots() {
        int var1 = 1 + this.getClassId().level();
        for (int var2 = 0; var2 < 3; ++var2) {
            if (this.var_3173[var2] == null) continue;
            --var1;
        }
        if (var1 <= 0) {
            return 0;
        }
        return var1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean removeHenna(int var1) {
        block5: {
            if (var1 < 1 || var1 > 3) break block5;
            if (this.var_3173[--var1] == null) {
                return false;
            }
            Henna var2 = this.var_3173[var1];
            int var3 = var2.getDyeId();
            this.var_3173[var1] = null;
            Connection var4 = null;
            PreparedStatement var5 = null;
            try {
                var4 = DatabaseFactory.getInstance().getConnection();
                var5 = var4.prepareStatement("DELETE FROM `character_hennas` where `char_obj_id`=? and `slot`=? and `class_index`=?");
                var5.setInt(1, this.getObjectId());
                var5.setInt(2, var1 + 1);
                var5.setInt(3, this.getActiveClassId());
                var5.execute();
            }
            catch (Exception var10) {
                try {
                    logger.warn("could not remove char henna: " + var10, (Throwable)var10);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly((Connection)var4, var5);
                    throw throwable;
                }
                DbUtils.closeQuietly((Connection)var4, (Statement)var5);
            }
            DbUtils.closeQuietly((Connection)var4, (Statement)var5);
            this.func178();
            this.sendPacket((IStaticPacket)new HennaInfo(this));
            this.sendUserInfo(true);
            ItemFunctions.addItem((Playable)this, (int)var3, (long)(var2.getDrawCount() / 2L), (boolean)true);
            Log.LogItem((Player)this, (Log.ItemLog)Log.ItemLog.HennaRemove, (int)var2.getSymbolId(), (long)1L);
            return true;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean addHenna(Henna var1) {
        if (this.getHennaEmptySlots() == 0) {
            this.sendPacket((IStaticPacket)SystemMsg.NO_SLOT_EXISTS_TO_DRAW_THE_SYMBOL);
            return false;
        }
        for (int var2 = 0; var2 < 3; ++var2) {
            if (this.var_3173[var2] != null) continue;
            this.var_3173[var2] = var1;
            this.func178();
            Connection var3 = null;
            PreparedStatement var4 = null;
            try {
                var3 = DatabaseFactory.getInstance().getConnection();
                var4 = var3.prepareStatement("INSERT INTO `character_hennas` (`char_obj_id`, `symbol_id`, `slot`, `class_index`) VALUES (?,?,?,?)");
                var4.setInt(1, this.getObjectId());
                var4.setInt(2, var1.getSymbolId());
                var4.setInt(3, var2 + 1);
                var4.setInt(4, this.getActiveClassId());
                var4.execute();
            }
            catch (Exception var9) {
                try {
                    logger.warn("could not save char henna: " + var9);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly((Connection)var3, var4);
                    throw throwable;
                }
                DbUtils.closeQuietly((Connection)var3, (Statement)var4);
            }
            DbUtils.closeQuietly((Connection)var3, (Statement)var4);
            this.sendPacket((IStaticPacket)new HennaInfo(this));
            this.sendUserInfo(true);
            Log.LogItem((Player)this, (Log.ItemLog)Log.ItemLog.HennaAdded, (int)var1.getDyeId(), (long)1L);
            return true;
        }
        return false;
    }

    private void func178() {
        this.var_3175 = 0;
        this.var_3174 = 0;
        this.var_3179 = 0;
        this.var_3177 = 0;
        this.var_3178 = 0;
        this.var_3176 = 0;
        for (int var1 = 0; var1 < 3; ++var1) {
            Henna var2 = this.var_3173[var1];
            if (var2 == null || !var2.isForThisClass(this)) continue;
            this.var_3175 += var2.getStatINT();
            this.var_3174 += var2.getStatSTR();
            this.var_3177 += var2.getStatMEN();
            this.var_3179 += var2.getStatCON();
            this.var_3178 += var2.getStatWIT();
            this.var_3176 += var2.getStatDEX();
        }
        if (this.var_3175 > Config.LIMIT_HENNA_INT) {
            this.var_3175 = Config.LIMIT_HENNA_INT;
        }
        if (this.var_3174 > Config.LIMIT_HENNA_STR) {
            this.var_3174 = Config.LIMIT_HENNA_STR;
        }
        if (this.var_3177 > Config.LIMIT_HENNA_MEN) {
            this.var_3177 = Config.LIMIT_HENNA_MEN;
        }
        if (this.var_3179 > Config.LIMIT_HENNA_CON) {
            this.var_3179 = Config.LIMIT_HENNA_CON;
        }
        if (this.var_3178 > Config.LIMIT_HENNA_WIT) {
            this.var_3178 = Config.LIMIT_HENNA_WIT;
        }
        if (this.var_3176 > Config.LIMIT_HENNA_DEX) {
            this.var_3176 = Config.LIMIT_HENNA_DEX;
        }
    }

    public Henna getHenna(int var1) {
        return var1 >= 1 && var1 <= 3 ? this.var_3173[var1 - 1] : null;
    }

    public int getHennaStatINT() {
        return this.var_3175;
    }

    public int getHennaStatSTR() {
        return this.var_3174;
    }

    public int getHennaStatCON() {
        return this.var_3179;
    }

    public int getHennaStatMEN() {
        return this.var_3177;
    }

    public int getHennaStatWIT() {
        return this.var_3178;
    }

    public int getHennaStatDEX() {
        return this.var_3176;
    }

    public boolean consumeItem(int var1, long var2) {
        if (this.getInventory().destroyItemByItemId(var1, var2)) {
            if (!ItemHolder.getInstance().getTemplate(var1).isHideConsumeMessage()) {
                this.sendPacket((IStaticPacket)SystemMessage.removeItems((int)var1, (long)var2));
            }
            return true;
        }
        return false;
    }

    public boolean consumeItemMp(int var1, int var2) {
        for (ItemInstance var6 : this.getInventory().getPaperdollItems()) {
            if (var6 == null || var6.getItemId() != var1) continue;
            int var7 = var6.getDuration() - var2;
            if (var7 < 0) break;
            var6.setDuration(var7);
            this.sendPacket((IStaticPacket)new InventoryUpdate().addModifiedItem(var6));
            return true;
        }
        return false;
    }

    public boolean isMageClass() {
        ClassId var1 = this.getClassId();
        return var1.isMage();
    }

    public boolean isMounted() {
        return this.var_3288 > 0;
    }

    public final boolean isRiding() {
        return this.var_3195;
    }

    public final void setRiding(boolean var1) {
        this.var_3195 = var1;
    }

    public boolean checkLandingState() {
        if (this.isInZone(Zone.ZoneType.no_landing)) {
            return false;
        }
        SiegeEvent<?,?> var1 = (SiegeEvent<?,?>)this.getEvent(SiegeEvent.class);
        if (var1 != null) {
            Residence var2 = var1.getResidence();
            return var2 != null && this.getClan() != null && this.isClanLeader() && this.getClan().getCastle() == var2.getId();
        }
        return true;
    }

    public void setMount(int var1, int var2) {
        Integer var3 = PetDataHolder.getInstance().getMaxLevel(var1);
        if (var3 != null) {
            this.setMount(var1, var2, var3, -1);
        }
    }

    public void setMount(int var1, int var2, int var3, int var4) {
        if (!this.isCursedWeaponEquipped()) {
            PetData var5 = PetDataHolder.getInstance().getInfo(var1, var3);
            if (var5 != null) {
                if (var5.isWyvern()) {
                    this.setFlying(true);
                    this.setLoc(this.getLoc().changeZ(32));
                    this.addSkill(SkillTable.getInstance().getInfo(4289, 1), false);
                } else if (!var5.isStrider() && !var5.isGreatWolf()) {
                    logger.warn("Unknown mount:" + var1);
                } else {
                    this.setRiding(true);
                }
            }
            if (var1 > 0) {
                this.unEquipWeapon();
            }
            this.var_3288 = var1;
            this.var_3289 = var2;
            this.var_3290 = var3;
            this.var_3291 = var4;
            this.var_3292 = var5.getFeedMax();
            this.broadcastUserInfo(true, new UserInfoType[0]);
            if (this.var_3291 >= 0) {
                this.sendPacket((IStaticPacket)new SetupGauge((Creature)((Object)this), 3, this.var_3291 * 10000, this.var_3292 * 10000));
                this.func179();
            }
            this.broadcastPacket(new L2GameServerPacket[]{new Ride(this)});
            this.broadcastUserInfo(true, new UserInfoType[0]);
            this.sendPacket((IStaticPacket)new ExUserInfoAbnormalVisualEffect(this));
            this.sendSkillList();
        }
    }

    public void dismount() {
        if (this.isMounted()) {
            this.setFlying(false);
            this.setRiding(false);
            if (this.getTransformation() > 0) {
                this.setTransformation(0);
            }
            this.removeSkillById(4289);
            this.getEffectList().stopEffect(4258);
            this.sendPacket((IStaticPacket)new SetupGauge((Creature)((Object)this), 3, 0, 0));
            this.func180();
            PetDAO.getInstance().updateMount(this.var_3289, this.var_3291);
            this.var_3288 = 0;
            this.var_3289 = 0;
            this.var_3290 = 0;
            this.var_3291 = -1;
            this.broadcastPacket(new L2GameServerPacket[]{new Ride(this)});
            this.broadcastUserInfo(true, new UserInfoType[0]);
            this.sendSkillList();
        }
    }

    public void unEquipWeapon() {
        ItemInstance var1 = this.getSecondaryWeaponInstance();
        if (var1 != null) {
            this.sendDisarmMessage(var1);
            this.getInventory().unEquipItem(var1);
        }
        if ((var1 = this.getActiveWeaponInstance()) != null) {
            this.sendDisarmMessage(var1);
            this.getInventory().unEquipItem(var1);
        }
        this.abortAttack(true, true);
        this.abortCast(true, true);
    }

    public int getSpeed(int var1) {
        if (this.isMounted()) {
            PetData var2 = PetDataHolder.getInstance().getInfo(this.var_3288, this.var_3290);
            if (var2 == null) {
                return 0;
            }
            var1 = var2.getMountSpeedG();
            if (this.isInWater()) {
                var1 = var2.getMountSpeedS();
            } else if (this.isFlying()) {
                var1 = var2.getMountSpeedF();
            }
            double var3 = 1.0;
            if (var2.getLevel() > this.getLevel() && var2.getLevel() - this.getLevel() > 10) {
                var3 = 0.5;
            }
            return (int)this.calcStat(Stats.RUN_SPEED, (double)var1 * var3, null, null);
        }
        return super.getSpeed(var1);
    }

    public int getMountNpcId() {
        return this.var_3288;
    }

    public int getMountObjId() {
        return this.var_3289;
    }

    public int getMountLevel() {
        return this.var_3290;
    }

    public int getMountCurrentFed() {
        return this.var_3291;
    }

    public void setMountCurrentFed(int var1) {
        this.var_3291 = var1;
    }

    public int getMountMaxFed() {
        return this.var_3292;
    }

    private void func179() {
        this.func180();
        if (this.isMounted() && this.getMountCurrentFed() >= 0) {
            this.var_3293 = ThreadPoolManager.getInstance().schedule((Runnable)new GameObjectTasks.MountFeedTask(this), 10000L);
        }
    }

    private void func180() {
        ScheduledFuture<?> var1 = this.var_3293;
        if (var1 != null) {
            var1.cancel(false);
            this.var_3293 = null;
        }
    }

    public void updateMountFed() {
        if (!this.isDead() && this.isMounted() && this.var_3291 >= 0) {
            if (this.var_3291 == 0) {
                this.sendPacket((IStaticPacket)SystemMsg.YOU_ARE_OUT_OF_FEED);
                this.dismount();
            } else {
                if (this.var_3291 * 100 <= this.getMountMaxFed() * 55) {
                    ItemInstance var4;
                    ItemInstance[] itemInstanceArray = this.getInventory().getItems();
                    int n = itemInstanceArray.length;
                    for (int i = 0; !(i >= n || (var4 = itemInstanceArray[i]).getTemplate().isPetFood() && var4.getTemplate().testCondition((Playable)this, var4, false) && var4.getTemplate().getHandler().useItem((Playable)this, var4, false)); ++i) {
                    }
                }
                PetData var5 = PetDataHolder.getInstance().getInfo(this.getMountNpcId(), this.getMountLevel());
                this.var_3291 = Math.max(this.var_3291 - (this.isInCombat() ? var5.getFeedBattle() : var5.getFeedNormal()), 0);
                this.sendPacket((IStaticPacket)new SetupGauge((Creature)((Object)this), 3, this.var_3291 * 10000, this.var_3292 * 10000));
                this.func179();
            }
        }
    }

    public void sendDisarmMessage(ItemInstance var1) {
        if (var1.getEnchantLevel() > 0) {
            SystemMessage var2 = new SystemMessage(SystemMsg.THE_EQUIPMENT_S1_S2_HAS_BEEN_REMOVED);
            var2.addNumber(var1.getEnchantLevel());
            var2.addItemName(var1.getItemId());
            this.sendPacket((IStaticPacket)var2);
        } else {
            SystemMessage var3 = new SystemMessage(SystemMsg.S1_HAS_BEEN_DISARMED);
            var3.addItemName(var1.getItemId());
            this.sendPacket((IStaticPacket)var3);
        }
    }

    public Warehouse.WarehouseType getUsingWarehouseType() {
        return this.var_3206;
    }

    public void setUsingWarehouseType(Warehouse.WarehouseType var1) {
        this.var_3206 = var1;
    }

    public Collection<EffectCubic> getCubics() {
        return this.var_3196 == null ? Collections.emptyList() : this.var_3196.values();
    }

    public void addCubic(EffectCubic var1) {
        if (this.var_3196 == null) {
            this.var_3196 = new ConcurrentHashMap<Integer, EffectCubic>(3);
        }
        this.var_3196.put(var1.getId(), var1);
    }

    public void removeCubic(int var1) {
        if (this.var_3196 != null) {
            this.var_3196.remove(var1);
        }
    }

    public EffectCubic getCubic(int var1) {
        return this.var_3196 == null ? null : this.var_3196.get(var1);
    }

    public String toString() {
        String var10000 = this.getName();
        return var10000 + "[" + this.getObjectId() + "]";
    }

    public int getWeaponEnchantEffect() {
        ItemInstance var1 = this.getActiveWeaponInstance();
        return var1 == null ? 0 : Math.min(127, var1.getEnchantLevel());
    }

    public int getArmorSetEnchantLevel() {
        ItemInstance var1 = this.getInventory().getPaperdollItem(6);
        if (var1 == null) {
            return 0;
        }
        ArmorSet var2 = ArmorSetsHolder.getInstance().getArmorSetByChestItemId(var1.getItemId());
        if (var2 == null) {
            return 0;
        }
        Integer var3 = var2.getEnchantLevel(this);
        return var3 == null ? 0 : var3;
    }

    public NpcInstance getLastNpc() {
        return (NpcInstance)this.var_3209.get();
    }

    public void setLastNpc(NpcInstance var1) {
        this.var_3209 = var1 == null ? HardReferences.emptyRef() : var1.getRef();
    }

    public MultiSellHolder.MultiSellListContainer getMultisell() {
        return this.var_3210;
    }

    public void setMultisell(MultiSellHolder.MultiSellListContainer var1) {
        this.var_3210 = var1;
    }

    public boolean isFestivalParticipant() {
        return this.getReflection() instanceof DarknessFestival;
    }

    public boolean unChargeShots(boolean var1) {
        ItemInstance var2 = this.getActiveWeaponInstance();
        if (var2 == null) {
            return false;
        }
        if (var1) {
            var2.setChargedSpiritshot(0);
        } else {
            var2.setChargedSoulshot(0);
        }
        this.autoShot();
        return true;
    }

    public boolean unChargeFishShot() {
        ItemInstance var1 = this.getActiveWeaponInstance();
        if (var1 == null) {
            return false;
        }
        var1.setChargedFishshot(false);
        this.autoShot();
        return true;
    }

    public void autoShot() {
        for (Integer var2 : this.var_3211) {
            IItemHandler var4;
            ItemInstance var3 = this.getInventory().getItemByItemId(var2.intValue());
            if (var3 == null) {
                this.removeAutoSoulShot(var2);
                continue;
            }
            if (!var3.getTemplate().testCondition((Playable)this, var3, false) || (var4 = var3.getTemplate().getHandler()) == null) continue;
            var4.useItem((Playable)this, var3, false);
        }
    }

    public boolean getChargedFishShot() {
        ItemInstance var1 = this.getActiveWeaponInstance();
        return var1 != null && var1.getChargedFishshot();
    }

    public boolean getChargedSoulShot() {
        ItemInstance var1 = this.getActiveWeaponInstance();
        return var1 != null && var1.getChargedSoulshot() == 1;
    }

    public int getChargedSpiritShot() {
        ItemInstance var1 = this.getActiveWeaponInstance();
        return var1 == null ? 0 : var1.getChargedSpiritshot();
    }

    public void addAutoSoulShot(Integer var1) {
        this.var_3211.add(var1);
        this.getListeners().onAutoSoulShot(var1.intValue(), true);
    }

    public void removeAutoSoulShot(Integer var1) {
        this.var_3211.remove(var1);
        this.getListeners().onAutoSoulShot(var1.intValue(), false);
    }

    public Set<Integer> getAutoSoulShot() {
        return this.var_3211;
    }

    public InvisibleType getInvisibleType() {
        return this.var_3261;
    }

    public void setInvisibleType(InvisibleType var1) {
        this.var_3261 = var1;
    }

    public int getClanPrivileges() {
        if (this.var_3182 == null) {
            return 0;
        }
        if (this.isClanLeader()) {
            return 0xFFFFFE;
        }
        if (this.var_3185 >= 1 && this.var_3185 <= 9) {
            RankPrivs var1 = this.var_3182.getRankPrivs(this.var_3185);
            return var1 != null ? var1.getPrivs() : 0;
        }
        return 0;
    }

    public void teleToClosestTown() {
        this.teleToLocation(TeleportUtils.getRestartLocation((Player)this, (RestartType)RestartType.TO_VILLAGE), ReflectionManager.DEFAULT);
    }

    public void teleToCastle() {
        this.teleToLocation(TeleportUtils.getRestartLocation((Player)this, (RestartType)RestartType.TO_CASTLE), ReflectionManager.DEFAULT);
    }

    public void teleToClanhall() {
        this.teleToLocation(TeleportUtils.getRestartLocation((Player)this, (RestartType)RestartType.TO_CLANHALL), ReflectionManager.DEFAULT);
    }

    public void sendMessage(CustomMessage var1) {
        this.sendMessage(var1.toString());
    }

    public void teleToLocation(int var1, int var2, int var3, int var4) {
        if (!this.isDeleted()) {
            super.teleToLocation(var1, var2, var3, var4);
        }
    }

    public boolean onTeleported() {
        if (!super.onTeleported()) {
            return false;
        }
        if (this.isFakeDeath()) {
            this.breakFakeDeath();
        }
        if (this.isInBoat()) {
            this.setLoc(this.getBoat().getLoc());
        }
        this.setNonAggroTime(System.currentTimeMillis() + Config.NONAGGRO_TIME_ONTELEPORT);
        this.spawnMe();
        this.setLastClientPosition(this.getLoc());
        this.setLastServerPosition(this.getLoc());
        if (this.isPendingRevive()) {
            this.doRevive();
        }
        this.sendActionFailed();
        this.getAI().notifyEvent(CtrlEvent.EVT_TELEPORTED);
        if (this.isLockedTarget() && this.getTarget() != null) {
            this.sendPacket((IStaticPacket)new MyTargetSelected(this.getTarget().getObjectId(), 0));
        }
        this.sendUserInfo(true);
        if (this.getPet() != null) {
            this.getPet().teleportToOwner();
        }
        if (Config.ALT_TELEPORT_PROTECTION && !this.isInAnyZone(new Zone.ZoneType[]{Zone.ZoneType.peace_zone, Zone.ZoneType.SIEGE, Zone.ZoneType.offshore}) && !this.isOlyParticipant()) {
            this.setAfterTeleportPortectionTime(System.currentTimeMillis() + 1000L * Config.ALT_TELEPORT_PROTECTION_TIME);
            this.sendMessage(new CustomMessage("alt.teleport_protect", this, new Object[]{Config.ALT_TELEPORT_PROTECTION_TIME}));
        }
        if (Config.RESEND_MSU_AFTER_TELEPORT > 0) {
            final HardReference<Player> var1 = this.getRef();
            ThreadPoolManager.getInstance().schedule((Runnable)new RunnableImpl(){

                public void runImpl() {
                    Player var1x = (Player)((Object)var1.get());
                    if (var1x != null && !var1x.isTeleporting()) {
                        LinkedList<MagicSkillUse> var2 = new LinkedList<MagicSkillUse>();
                        for (Player var4 : World.getAroundPlayers((GameObject)var1x)) {
                            if (var4 == null || var4.isTeleporting() || !var4.isCastingNow()) continue;
                            Creature var5 = var4.getCastingTarget();
                            Skill var6 = var4.getCastingSkill();
                            long var7 = var4.getAnimationEndTime();
                            if (var6 == null || var5 == null || !var5.isCreature() || var7 <= 0L) continue;
                            var2.add(new MagicSkillUse((Creature)((Object)var4), var5, var6, (int)(var7 - System.currentTimeMillis()), 0L));
                        }
                        var1x.sendActionFailed();
                        if (!var2.isEmpty()) {
                            var1x.sendPacket(var2);
                        }
                    }
                }
            }, (long)Config.RESEND_MSU_AFTER_TELEPORT);
        }
        return true;
    }

    public boolean enterObserverMode(Location var1) {
        WorldRegion var2 = World.getRegion((Location)var1);
        if (var2 == null) {
            return false;
        }
        if (!this.var_3213.compareAndSet(0, 1)) {
            return false;
        }
        this.setTarget(null);
        this.stopMove();
        this.sitDown(null);
        this.setFlying(true);
        World.removeObjectsFromPlayer((Player)this);
        this.setObserverRegion(var2);
        this.broadcastCharInfo();
        this.sendPacket((IStaticPacket)new ObserverStart(var1));
        return true;
    }

    public void appearObserverMode() {
        if (this.var_3213.compareAndSet(1, 3)) {
            WorldRegion var1 = this.getCurrentRegion();
            WorldRegion var2 = this.getObserverRegion();
            if (!var2.equals(var1)) {
                var2.addObject((GameObject)this);
            }
            World.showObjectsToPlayer((Player)this);
            if (this.isOlyObserver()) {
                for (Player var4 : this.getOlyObservingStadium().getPlayers()) {
                    if (!var4.isOlyCompetitionStarted()) continue;
                    this.sendPacket((IStaticPacket)new ExOlympiadUserInfo(var4));
                }
            }
        }
    }

    public void leaveObserverMode() {
        if (this.var_3213.compareAndSet(3, 2)) {
            WorldRegion var1 = this.getCurrentRegion();
            WorldRegion var2 = this.getObserverRegion();
            if (!var2.equals(var1)) {
                var2.removeObject((GameObject)this);
            }
            World.removeObjectsFromPlayer((Player)this);
            this.setObserverRegion(null);
            this.setTarget(null);
            this.stopMove();
            this.sendPacket((IStaticPacket)new ObserverEnd(this.getLoc()));
        }
    }

    public void returnFromObserverMode() {
        if (this.var_3213.compareAndSet(2, 0)) {
            this.setLastClientPosition(null);
            this.setLastServerPosition(null);
            this.unblock();
            this.standUp();
            this.setFlying(false);
            this.broadcastCharInfo();
            World.showObjectsToPlayer((Player)this);
        }
    }

    public void enterOlympiadObserverMode(Stadium var1) {
        WorldRegion var2 = World.getRegion((Location)var1.getObservingLoc());
        if (var2 != null && this.var_3127 == null && this.var_3213.compareAndSet(0, 1)) {
            this.setTarget(null);
            this.setLastNpc(null);
            this.stopMove();
            this.var_3127 = var1;
            World.removeObjectsFromPlayer((Player)this);
            this.setObserverRegion(var2);
            this.block();
            this.broadcastCharInfo();
            this.setReflection((Reflection)var1);
            this.setLastClientPosition(null);
            this.setLastServerPosition(null);
            this.sendPacket(new IStaticPacket[]{new ExOlympiadMode(3), new TeleportToLocation((GameObject)this, var1.getObservingLoc()), new ExTeleportToLocationActivate((GameObject)this, var1.getObservingLoc())});
        }
    }

    public void switchOlympiadObserverArena(Stadium var1) {
        if (this.var_3127 != null && var1 != this.var_3127) {
            WorldRegion var2 = World.getRegion((Location)this.var_3127.getObservingLoc());
            if (this.var_3213.compareAndSet(3, 0)) {
                if (var2 != null) {
                    var2.removeObject((GameObject)this);
                    var2.removeFromPlayers((GameObject)this);
                }
                this.var_3127 = null;
                World.removeObjectsFromPlayer((Player)this);
                this.sendPacket((IStaticPacket)new ExOlympiadMode(0));
                this.enterOlympiadObserverMode(var1);
            }
        }
    }

    public void leaveOlympiadObserverMode() {
        if (this.var_3127 != null && this.var_3213.compareAndSet(3, 2)) {
            WorldRegion var1 = this.getCurrentRegion();
            WorldRegion var2 = this.getObserverRegion();
            if (var2 != null && var1 != null && !var2.equals(var1)) {
                var2.removeObject((GameObject)this);
            }
            World.removeObjectsFromPlayer((Player)this);
            this.setObserverRegion(null);
            this.var_3127 = null;
            this.setTarget(null);
            this.stopMove();
            this.sendPacket((IStaticPacket)new ExOlympiadMode(0));
            this.setReflection(ReflectionManager.DEFAULT);
            this.sendPacket(new IStaticPacket[]{new TeleportToLocation((GameObject)this, this.getLoc()), new ExTeleportToLocationActivate((GameObject)this, this.getLoc())});
        }
    }

    public boolean isOlyObserver() {
        return this.var_3127 != null;
    }

    public Stadium getOlyObservingStadium() {
        return this.var_3127;
    }

    public boolean isInObserverMode() {
        return this.var_3213.get() > 0;
    }

    public int getObserverMode() {
        return this.var_3213.get();
    }

    public Participant getOlyParticipant() {
        return this.var_3128;
    }

    public boolean isOlyParticipant() {
        return this.var_3128 != null;
    }

    public void setOlyParticipant(Participant var1) {
        this.var_3128 = var1;
    }

    public boolean isOlyCompetitionStarted() {
        return this.isOlyParticipant() && this.var_3128.getCompetition().getState() == CompetitionState.PLAYING;
    }

    public boolean isOlyCompetitionStandby() {
        return this.isOlyParticipant() && this.var_3128.getCompetition().getState() == CompetitionState.STAND_BY;
    }

    public boolean isOlyCompetitionPreparing() {
        return this.isOlyParticipant() && (this.var_3128.getCompetition().getState() == CompetitionState.INIT || this.var_3128.getCompetition().getState() == CompetitionState.STAND_BY);
    }

    public boolean isOlyCompetitionFinished() {
        return this.isOlyParticipant() && this.var_3128.getCompetition().getState() == CompetitionState.FINISH;
    }

    public boolean isLooseOlyCompetition() {
        if (this.isOlyParticipant()) {
            if (this.isOlyCompetitionFinished()) {
                return !this.var_3128.isAlive();
            }
            return this.var_3128.isPlayerLoose(this);
        }
        return false;
    }

    public WorldRegion getObserverRegion() {
        return this.var_3212;
    }

    public void setObserverRegion(WorldRegion var1) {
        this.var_3212 = var1;
    }

    public int getTeleMode() {
        return this._telemode;
    }

    public void setTeleMode(int var1) {
        this._telemode = var1;
    }

    public void setLoto(int var1, int var2) {
        this._loto[var1] = var2;
    }

    public int getLoto(int var1) {
        return this._loto[var1];
    }

    public void setRace(int var1, int var2) {
        this._race[var1] = var2;
    }

    public int getRace(int var1) {
        return this._race[var1];
    }

    public boolean getMessageRefusal() {
        return this.var_3190;
    }

    public void setMessageRefusal(boolean var1) {
        this.var_3190 = var1;
    }

    public boolean getTradeRefusal() {
        return this.var_3191;
    }

    public void setTradeRefusal(boolean var1) {
        this.var_3191 = var1;
    }

    public boolean isPartyRefusal() {
        return this.var_3192;
    }

    public void setPartyRefusal(boolean var1) {
        this.var_3192 = var1;
    }

    public boolean isBlockAll() {
        return this.var_3193;
    }

    public void setBlockAll(boolean var1) {
        this.var_3193 = var1;
    }

    public CharacterBlockList getBlockList() {
        return this.var_3214;
    }

    private void func181() {
        if (this.var_3236 != null) {
            this.var_3236.cancel(true);
            this.var_3236 = null;
        }
    }

    public void setCustomHero(boolean var1, long var2, boolean var4) {
        if (!this.isHero() && var1 && var2 > 0L) {
            this.setVar(CUSTOM_HERO_END_TIME_VAR, System.currentTimeMillis() / 1000L + var2, -1L);
            this.setHero(true);
            if (var4) {
                HeroController.addSkills((Player)this);
            }
            this.var_3236 = ThreadPoolManager.getInstance().schedule((Runnable)new GameObjectTasks.EndCustomHeroTask(this), var2 * 1000L);
        } else if (!var1) {
            this.unsetVar(CUSTOM_HERO_END_TIME_VAR);
            this.func181();
            if (HeroController.getInstance().isCurrentHero(this)) {
                return;
            }
            this.setHero(false);
            if (var4) {
                HeroController.removeSkills((Player)this);
            }
            HeroController.checkHeroWeaponary((Player)this);
        }
    }

    public boolean isCustomHero() {
        String var1 = this.getVar(CUSTOM_HERO_END_TIME_VAR);
        if (var1 != null) {
            long var2 = Long.parseLong(var1);
            return var2 > System.currentTimeMillis() / 1000L;
        }
        return false;
    }

    public boolean isCustomEventParticipant() {
        return this.var_3237;
    }

    public void setCustomEventParticipant(boolean var1) {
        this.var_3237 = var1;
    }

    public boolean isHero() {
        return this.var_3218;
    }

    public void setHero(boolean var1) {
        this.var_3218 = var1;
    }

    public void updatePremiumSkills() {
        boolean var1 = this.hasBonus();
        if (!var1) {
            for (SkillLearn var3 : SkillAcquireHolder.getInstance().getAllSkillLearn(this, this.getClassId(), AcquireType.PREMIUM_ACCOUNT)) {
                Skill var4 = SkillTable.getInstance().getInfo(var3.getId(), var3.getLevel());
                if (var4 == null) continue;
                this.removeSkill(var4, false);
            }
        } else {
            for (SkillLearn var6 : SkillAcquireHolder.getInstance().getAvailableSkills(this, AcquireType.PREMIUM_ACCOUNT)) {
                Skill var7 = SkillTable.getInstance().getInfo(var6.getId(), var6.getLevel());
                if (var7 == null || this.getSkillLevel(var7.getId()) >= var7.getLevel()) continue;
                this.addSkill(var7, false);
            }
        }
    }

    public void updateNobleSkills() {
        boolean var1 = this.isNoble();
        if (!var1) {
            for (SkillLearn var3 : SkillAcquireHolder.getInstance().getAllSkillLearn(this, this.getClassId(), AcquireType.NOBLES)) {
                Skill var4 = SkillTable.getInstance().getInfo(var3.getId(), var3.getLevel());
                if (var4 == null) continue;
                this.removeSkill(var4, true);
            }
        } else {
            for (SkillLearn var6 : SkillAcquireHolder.getInstance().getAvailableSkills(this, AcquireType.NOBLES)) {
                Skill var7 = SkillTable.getInstance().getInfo(var6.getId(), var6.getLevel());
                if (var7 == null || this.getSkillLevel(var7.getId()) >= var7.getLevel()) continue;
                this.addSkill(var7, true);
            }
        }
    }

    public boolean isNoble() {
        return this.var_3225;
    }

    public void setNoble(boolean var1) {
        this.var_3225 = var1;
        if (var1) {
            this.broadcastPacket(new L2GameServerPacket[]{new SocialAction(this.getObjectId(), 20016)});
        }
    }

    public int getSubLevel() {
        return this.isSubClassActive() ? this.getLevel() : 0;
    }

    public int getPledgeType() {
        return this.var_3184;
    }

    public void setPledgeType(int var1) {
        this.var_3184 = var1;
    }

    public int getLvlJoinedAcademy() {
        return this.var_3186;
    }

    public void setLvlJoinedAcademy(int var1) {
        this.var_3186 = var1;
    }

    public int getPledgeClass() {
        return this.var_3183;
    }

    public EPledgeRank getPledgeRank() {
        return EPledgeRank.getPledgeRank(this.getPledgeClass());
    }

    public void updatePledgeClass() {
        int var1 = this.var_3182 == null ? -1 : this.var_3182.getLevel();
        boolean var2 = this.var_3182 != null && Clan.isAcademy((int)this.var_3184);
        boolean var3 = this.var_3182 != null && Clan.isRoyalGuard((int)this.var_3184);
        boolean var4 = this.var_3182 != null && Clan.isOrderOfKnights((int)this.var_3184);
        boolean var5 = false;
        boolean var6 = false;
        boolean var7 = false;
        SubUnit var8 = this.getSubUnit();
        if (var8 != null) {
            UnitMember var9 = var8.getUnitMember(this.getObjectId());
            if (var9 == null) {
                Logger var10000 = logger;
                int var10001 = this.var_3182.getClanId();
                var10000.warn("Player: unitMember null, clan: " + var10001 + "; pledgeType: " + var8.getType());
                return;
            }
            var5 = Clan.isRoyalGuard((int)var9.getLeaderOf());
            var6 = Clan.isOrderOfKnights((int)var9.getLeaderOf());
            var7 = var9.getLeaderOf() == 0;
        }
        switch (var1) {
            case -1: {
                this.var_3183 = 0;
                break;
            }
            case 0: 
            case 1: 
            case 2: 
            case 3: {
                if (var7) {
                    this.var_3183 = 2;
                    break;
                }
                this.var_3183 = 1;
                break;
            }
            case 4: {
                if (var7) {
                    this.var_3183 = 3;
                    break;
                }
                this.var_3183 = 2;
                break;
            }
            case 5: {
                if (var7) {
                    this.var_3183 = 4;
                    break;
                }
                if (var2) {
                    this.var_3183 = 1;
                    break;
                }
                this.var_3183 = 2;
                break;
            }
            case 6: {
                if (var7) {
                    this.var_3183 = 5;
                    break;
                }
                if (var2) {
                    this.var_3183 = 1;
                    break;
                }
                if (var5) {
                    this.var_3183 = 4;
                    break;
                }
                if (var3) {
                    this.var_3183 = 2;
                    break;
                }
                this.var_3183 = 3;
                break;
            }
            case 7: {
                if (var7) {
                    this.var_3183 = 7;
                    break;
                }
                if (var2) {
                    this.var_3183 = 1;
                    break;
                }
                if (var5) {
                    this.var_3183 = 6;
                    break;
                }
                if (var3) {
                    this.var_3183 = 3;
                    break;
                }
                if (var6) {
                    this.var_3183 = 5;
                    break;
                }
                if (var4) {
                    this.var_3183 = 2;
                    break;
                }
                this.var_3183 = 4;
                break;
            }
            case 8: {
                this.var_3183 = var7 ? 8 : (var2 ? 1 : (var5 ? 7 : (var3 ? 4 : (var6 ? 6 : (var4 ? 3 : 5)))));
            }
        }
        if (this.var_3218 && this.var_3183 < 8) {
            this.var_3183 = 8;
        } else if (this.var_3225 && this.var_3183 < 5) {
            this.var_3183 = 5;
        }
    }

    public int getPowerGrade() {
        return this.var_3185;
    }

    public void setPowerGrade(int var1) {
        this.var_3185 = var1;
    }

    public int getApprentice() {
        return this.var_3187;
    }

    public void setApprentice(int var1) {
        this.var_3187 = var1;
    }

    public int getSponsor() {
        return this.var_3182 == null ? 0 : this.var_3182.getAnyMember(this.getObjectId()).getSponsor();
    }

    public int getNameColor() {
        return this.isInObserverMode() ? Color.black.getRGB() : this.var_3140;
    }

    public void setNameColor(int var1) {
        if (var1 != Config.NORMAL_NAME_COLOUR && var1 != Config.CLANLEADER_NAME_COLOUR && var1 != Config.GM_NAME_COLOUR && var1 != Config.SERVICES_OFFLINE_TRADE_NAME_COLOR) {
            this.setVar("namecolor", Integer.toHexString(var1), -1L);
        } else if (var1 == Config.NORMAL_NAME_COLOUR) {
            this.unsetVar("namecolor");
        }
        this.var_3140 = var1;
    }

    public void setVar(String var1, String var2, long var3) {
        this.var_3294.put(var1, var2);
        CharacterVariablesDAO.getInstance().setVar(this.getObjectId(), var1, var2, var3);
    }

    public void setVar(String var1, int var2, long var3) {
        this.setVar(var1, String.valueOf(var2), var3);
    }

    public void setVar(String var1, long var2, long var4) {
        this.setVar(var1, String.valueOf(var2), var4);
    }

    public void unsetVar(String var1) {
        if (var1 != null && this.var_3294.remove((Object)var1) != null) {
            CharacterVariablesDAO.getInstance().deleteVar(this.getObjectId(), var1);
        }
    }

    public String getVar(String var1) {
        return this.var_3294.getString(var1, null);
    }

    public boolean getVarB(String var1, boolean var2) {
        String var3 = this.var_3294.getString(var1, null);
        if (var3 == null) {
            return var2;
        }
        return !var3.equals("0") && !var3.equalsIgnoreCase("false");
    }

    public boolean getVarB(String var1) {
        String var2 = this.var_3294.getString(var1, null);
        return var2 != null && !var2.equals("0") && !var2.equalsIgnoreCase("false");
    }

    public long getVarLong(String var1) {
        return this.getVarLong(var1, 0L);
    }

    public long getVarLong(String var1, long var2) {
        long var4 = var2;
        String var6 = this.getVar(var1);
        if (var6 != null) {
            var4 = Long.parseLong(var6);
        }
        return var4;
    }

    public int getVarInt(String var1) {
        return this.getVarInt(var1, 0);
    }

    public int getVarInt(String var1, int var2) {
        int var3 = var2;
        String var4 = this.getVar(var1);
        if (var4 != null) {
            var3 = Integer.parseInt(var4);
        }
        return var3;
    }

    public MultiValueSet<String> getVars() {
        return this.var_3294;
    }

    public String getLang() {
        return this.getVar("lang@");
    }

    public String getHWIDLock() {
        return this.getVar("hwidlock@");
    }

    public void setHWIDLock(String var1) {
        if (var1 == null) {
            this.unsetVar("hwidlock@");
        } else {
            this.setVar("hwidlock@", var1, -1L);
        }
    }

    public String getIPLock() {
        return this.getVar("iplock@");
    }

    public void setIPLock(String var1) {
        if (var1 == null) {
            this.unsetVar("iplock@");
        } else {
            this.setVar("iplock@", var1, -1L);
        }
    }

    public Language getLanguage() {
        String var1 = this.getVar("lang@");
        if (var1 == null) {
            return LanguageHolder.getInstance().getDefaultLanguage();
        }
        Language var2 = LanguageHolder.getInstance().getLanguage(var1);
        return var2 != null ? var2 : LanguageHolder.getInstance().getDefaultLanguage();
    }

    public boolean isLangRus() {
        return "ru".equalsIgnoreCase(this.getLanguage().getShortName());
    }

    public int isAtWarWith(Integer var1) {
        return this.var_3182 != null && this.var_3182.isAtWarWith(var1.intValue()) ? 1 : 0;
    }

    public int isAtWar() {
        return this.var_3182 != null && this.var_3182.isAtWarOrUnderAttack() > 0 ? 1 : 0;
    }

    public void stopWaterTask() {
        if (this.var_3230 != null) {
            this.var_3230.cancel(false);
            this.var_3230 = null;
            this.sendPacket((IStaticPacket)new SetupGauge((Creature)((Object)this), 2, 0));
            this.sendChanges();
        }
    }

    public void startWaterTask() {
        if (this.isDead()) {
            this.stopWaterTask();
        } else if (Config.ALLOW_WATER && this.var_3230 == null) {
            int var1 = (int)(this.calcStat(Stats.BREATH, 86.0, null, null) * 1000.0);
            this.sendPacket((IStaticPacket)new SetupGauge((Creature)((Object)this), 2, var1));
            if (this.getTransformation() > 0 && this.getTransformationTemplate() > 0 && !this.isCursedWeaponEquipped()) {
                this.setTransformation(0);
            }
            this.var_3230 = ThreadPoolManager.getInstance().scheduleAtFixedRate((Runnable)new GameObjectTasks.WaterTask(this), (long)var1, 1000L);
            this.sendChanges();
        }
    }

    public void doRevive(double var1) {
        this.restoreExp(var1);
        this.doRevive();
    }

    public void doRevive() {
        super.doRevive();
        this.unsetVar("lostexp");
        this.func179();
        this.updateEffectIcons();
        this.autoShot();
    }

    public void reviveRequest(Player var1, double var2, boolean var4, int var5) {
        ReviveAnswerListener var6;
        ReviveAnswerListener reviveAnswerListener = var6 = this.var_3269 != null && this.var_3269.getValue() instanceof ReviveAnswerListener ? (ReviveAnswerListener)this.var_3269.getValue() : null;
        if (var6 != null) {
            if (var6.isForPet() == var4 && var6.getPower() >= var2) {
                var1.sendPacket((IStaticPacket)SystemMsg.RESURRECTION_HAS_ALREADY_BEEN_PROPOSED);
                return;
            }
            if (var4 && !var6.isForPet()) {
                var1.sendPacket((IStaticPacket)SystemMsg.A_PET_CANNOT_BE_RESURRECTED_WHILE_ITS_OWNER_IS_IN_THE_PROCESS_OF_RESURRECTING);
                return;
            }
            if (var4 && this.isDead()) {
                var1.sendPacket((IStaticPacket)SystemMsg.WHILE_A_PET_IS_BEING_RESURRECTED_IT_CANNOT_HELP_IN_RESURRECTING_ITS_MASTER);
                return;
            }
        }
        if (var4 && this.getPet() != null && this.getPet().isDead() || !var4 && this.isDead()) {
            ConfirmDlg var7 = new ConfirmDlg(SystemMsg.C1_IS_MAKING_AN_ATTEMPT_TO_RESURRECT_YOU_IF_YOU_CHOOSE_THIS_PATH_S2_EXPERIENCE_WILL_BE_RETURNED_FOR_YOU, var5);
            ((ConfirmDlg)var7.addName((GameObject)var1)).addString(Math.round(var2) + " percent");
            this.ask(var7, (OnAnswerListener)new ReviveAnswerListener(this, var2, var4, var5));
        }
    }

    public void summonCharacterRequest(Creature var1, Location var2, int var3) {
        ConfirmDlg var4 = new ConfirmDlg(SystemMsg.C1_WISHES_TO_SUMMON_YOU_FROM_S2, 60000);
        ((ConfirmDlg)var4.addName((GameObject)var1)).addZoneName(var2);
        this.ask(var4, (OnAnswerListener)new SummonAnswerListener(this, var2, (long)var3, 60000));
    }

    public void scriptRequest(String var1, String var2, Object[] var3) {
        this.ask((ConfirmDlg)new ConfirmDlg(SystemMsg.S1, 30000).addString(var1), (OnAnswerListener)new ScriptAnswerListener(this, var2, var3, 30000L));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updateNoChannel(long var1) {
        this.setNoChannel(var1);
        Connection var3 = null;
        PreparedStatement var4 = null;
        try {
            var3 = DatabaseFactory.getInstance().getConnection();
            var4 = var3.prepareStatement("UPDATE characters SET nochannel = ? WHERE obj_Id=?");
            var4.setLong(1, this.var_3134 > 0L ? this.var_3134 / 1000L : this.var_3134);
            var4.setInt(2, this.getObjectId());
            var4.executeUpdate();
        }
        catch (Exception var9) {
            try {
                logger.warn("Could not activate nochannel:" + var9);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var3, var4);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var3, (Statement)var4);
        }
        DbUtils.closeQuietly((Connection)var3, (Statement)var4);
        this.sendPacket((IStaticPacket)new EtcStatusUpdate(this));
    }

    public boolean canTalkWith(Player var1) {
        return this.var_3134 >= 0L || var1 == this;
    }

    public Deque<ChatMsg> getMessageBucket() {
        return this.var_3157;
    }

    public boolean isInBoat() {
        return this.var_3219 != null;
    }

    public Boat getBoat() {
        return this.var_3219;
    }

    public void setBoat(Boat var1) {
        this.var_3219 = var1;
    }

    protected L2GameServerPacket stopMovePacket() {
        if (this.isInBoat()) {
            this.getBoat().inStopMovePacket(this);
        }
        return super.stopMovePacket();
    }

    public Location getInBoatPosition() {
        return this.var_3220;
    }

    public void setInBoatPosition(Location var1) {
        this.var_3220 = var1;
    }

    public Map<Integer, SubClass> getSubClasses() {
        return this._classlist;
    }

    public SubClass getBaseSubClass() {
        for (Map.Entry<Integer, SubClass> var2 : this.getSubClasses().entrySet()) {
            if (!var2.getValue().isBase()) continue;
            return var2.getValue();
        }
        throw new IllegalStateException("No base subclass for player " + this);
    }

    public int getBaseClassId() {
        return this._baseClass;
    }

    public void setBaseClassId(int var1) {
        this._baseClass = var1;
    }

    public SubClass getActiveClass() {
        return this._activeClass;
    }

    public void setActiveClass(SubClass var1) {
        this._activeClass = var1;
    }

    public int getActiveClassId() {
        return this.getActiveClass().getClassId();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void changeClassInDb(int var1, int var2) {
        Connection var3 = null;
        PreparedStatement var4 = null;
        try {
            var3 = DatabaseFactory.getInstance().getConnection();
            PreparedStatement var11 = var3.prepareStatement("UPDATE `character_subclasses` SET `class_id`=? WHERE `char_obj_id`=? AND `class_id`=?");
            var11.setInt(1, var2);
            var11.setInt(2, this.getObjectId());
            var11.setInt(3, var1);
            var11.executeUpdate();
            DbUtils.close((Statement)var11);
            PreparedStatement var12 = var3.prepareStatement("DELETE FROM `character_hennas` WHERE `char_obj_id`=? AND `class_index`=?");
            var12.setInt(1, this.getObjectId());
            var12.setInt(2, var2);
            var12.executeUpdate();
            DbUtils.close((Statement)var12);
            PreparedStatement var13 = var3.prepareStatement("UPDATE `character_hennas` SET `class_index`=? WHERE `char_obj_id`=? AND `class_index`=?");
            var13.setInt(1, var2);
            var13.setInt(2, this.getObjectId());
            var13.setInt(3, var1);
            var13.executeUpdate();
            DbUtils.close((Statement)var13);
            PreparedStatement var14 = var3.prepareStatement("DELETE FROM `character_shortcuts` WHERE `object_id`=? AND `class_index`=?");
            var14.setInt(1, this.getObjectId());
            var14.setInt(2, var2);
            var14.executeUpdate();
            DbUtils.close((Statement)var14);
            PreparedStatement var15 = var3.prepareStatement("UPDATE `character_shortcuts` SET `class_index`=? WHERE `object_id`=? AND `class_index`=?");
            var15.setInt(1, var2);
            var15.setInt(2, this.getObjectId());
            var15.setInt(3, var1);
            var15.executeUpdate();
            DbUtils.close((Statement)var15);
            CharacterSkillsDAO.getInstance().deleteAllByClass(this.getObjectId(), var2);
            PreparedStatement var16 = var3.prepareStatement("UPDATE `character_skills` SET `class_index`=? WHERE `char_obj_id`=? AND `class_index`=?");
            var16.setInt(1, var2);
            var16.setInt(2, this.getObjectId());
            var16.setInt(3, var1);
            var16.executeUpdate();
            DbUtils.close((Statement)var16);
            PreparedStatement var17 = var3.prepareStatement("DELETE FROM `character_effects_save` WHERE `object_id`=? AND `id`=?");
            var17.setInt(1, this.getObjectId());
            var17.setInt(2, var2);
            var17.executeUpdate();
            DbUtils.close((Statement)var17);
            PreparedStatement var18 = var3.prepareStatement("UPDATE `character_effects_save` SET `id`=? WHERE `object_id`=? AND `id`=?");
            var18.setInt(1, var2);
            var18.setInt(2, this.getObjectId());
            var18.setInt(3, var1);
            var18.executeUpdate();
            DbUtils.close((Statement)var18);
            PreparedStatement var19 = var3.prepareStatement("DELETE FROM `character_skills_save` WHERE `char_obj_id`=? AND `class_index`=?");
            var19.setInt(1, this.getObjectId());
            var19.setInt(2, var2);
            var19.executeUpdate();
            DbUtils.close((Statement)var19);
            var4 = var3.prepareStatement("UPDATE `character_skills_save` SET `class_index`=? WHERE `char_obj_id`=? AND `class_index`=?");
            var4.setInt(1, var2);
            var4.setInt(2, this.getObjectId());
            var4.setInt(3, var1);
            var4.executeUpdate();
            DbUtils.close((Statement)var4);
            DbUtils.closeQuietly((Connection)var3, (Statement)var4);
        }
        catch (SQLException var9) {
            logger.error("", (Throwable)var9);
        }
        finally {
            DbUtils.closeQuietly((Connection)var3, var4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void storeCharSubClasses() {
        SubClass var1 = this.getActiveClass();
        if (var1 != null) {
            var1.setCp(this.getCurrentCp());
            var1.setHp(this.getCurrentHp());
            var1.setMp(this.getCurrentMp());
            var1.setActive(true);
            this.getSubClasses().put(this.getActiveClassId(), var1);
        } else {
            Logger var10000 = logger;
            int var10001 = this.getActiveClassId();
            var10000.warn("Could not store char sub data, main class " + var10001 + " not found for " + this);
        }
        Connection var2 = null;
        Statement var3 = null;
        try {
            var2 = DatabaseFactory.getInstance().getConnection();
            var3 = var2.createStatement();
            for (SubClass var6 : this.getSubClasses().values()) {
                StringBuilder var4 = new StringBuilder("UPDATE character_subclasses SET ");
                var4.append("exp=").append(var6.getExp()).append(",");
                var4.append("sp=").append(var6.getSp()).append(",");
                var4.append("curHp=").append(var6.getHp()).append(",");
                var4.append("curMp=").append(var6.getMp()).append(",");
                var4.append("curCp=").append(var6.getCp()).append(",");
                var4.append("level=").append(var6.getLevel()).append(",");
                var4.append("active=").append(var6.isActive() ? 1 : 0).append(",");
                var4.append("isBase=").append(var6.isBase() ? 1 : 0).append(",");
                var4.append("death_penalty=").append(var6.getDeathPenalty(this).getLevelOnSaveDB());
                var4.append(" WHERE char_obj_id=").append(this.getObjectId()).append(" AND class_id=").append(var6.getClassId()).append(" LIMIT 1");
                var3.executeUpdate(var4.toString());
            }
            StringBuilder var12 = new StringBuilder("UPDATE character_subclasses SET ");
            var12.append("maxHp=").append(this.getMaxHp()).append(",");
            var12.append("maxMp=").append(this.getMaxMp()).append(",");
            var12.append("maxCp=").append(this.getMaxCp());
            var12.append(" WHERE char_obj_id=").append(this.getObjectId()).append(" AND active=1 LIMIT 1");
            var3.executeUpdate(var12.toString());
        }
        catch (Exception var10) {
            try {
                logger.warn("Could not store char sub data: " + var10);
                logger.error("", (Throwable)var10);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var2, var3);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var2, (Statement)var3);
        }
        DbUtils.closeQuietly((Connection)var2, (Statement)var3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean addSubClass(int var1, boolean var2) {
        PreparedStatement var6;
        Connection var5;
        ClassId var3;
        if (this._classlist.size() >= Config.ALT_GAME_BASE_SUB) {
            return false;
        }
        var3 = ClassId.VALUES[var1];
        SubClass var4 = new SubClass();
        var4.setBase(false);
        if (var3.getRace() == null) {
            return false;
        }
        var4.setClassId(var1);
        this._classlist.put(var1, var4);
        var5 = null;
        var6 = null;
        try {
                var5 = DatabaseFactory.getInstance().getConnection();
                var6 = var5.prepareStatement("INSERT INTO  `character_subclasses`  (\t`char_obj_id`,   `class_id`,   `exp`,   `sp`,   `curHp`,   `curMp`,   `curCp`,   `maxHp`,   `maxMp`,   `maxCp`,   `level`,   `active`,   `isBase`,   `death_penalty`)VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                var6.setInt(1, this.getObjectId());
                var6.setInt(2, var4.getClassId());
                var6.setLong(3, var4.getExp());
                var6.setInt(4, 0);
                var6.setDouble(5, this.getCurrentHp());
                var6.setDouble(6, this.getCurrentMp());
                var6.setDouble(7, this.getCurrentCp());
                var6.setDouble(8, this.getCurrentHp());
                var6.setDouble(9, this.getCurrentMp());
                var6.setDouble(10, this.getCurrentCp());
                var6.setInt(11, var4.getLevel());
                var6.setInt(12, 0);
                var6.setInt(13, 0);
                var6.setInt(14, 0);
                var6.execute();
            }
            catch (Exception var16) {
                logger.warn("Could not add character sub-class: " + var16, (Throwable)var16);
                DbUtils.closeQuietly((Connection)var5, (Statement)var6);
                return false;
            }
            finally {
                DbUtils.closeQuietly((Connection)var5, (Statement)var6);
            }
        this.setActiveSubClass(var1, var2);
        ArrayList<Skill> var7 = new ArrayList<Skill>();
        boolean var18 = true;
        int var9 = 0;
        Collection<SkillLearn> var10 = SkillAcquireHolder.getInstance().getAvailableSkills(this, AcquireType.NORMAL);
        while (var10.size() > var9) {
            for (SkillLearn var12 : var10) {
                Skill var13 = SkillTable.getInstance().getInfo(var12.getId(), var12.getLevel());
                if (var13 != null && var13.getCanLearn(var3)) {
                    this.addSkill(var13, false);
                    var7.add(var13);
                    continue;
                }
                if (!var18) continue;
                ++var9;
            }
            var18 = false;
            var10 = SkillAcquireHolder.getInstance().getAvailableSkills(this, AcquireType.NORMAL);
        }
        if (!var7.isEmpty()) {
            CharacterSkillsDAO.getInstance().store(this, var7);
        }
        this.sendSkillList();
        this.setCurrentHpMp(this.getMaxHp(), this.getMaxMp(), true);
        this.setCurrentCp(this.getMaxCp());
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean modifySubClass(int var1, int var2) {
        SubClass var3 = this._classlist.get(var1);
        if (var3 != null && !var3.isBase()) {
            Connection var4 = null;
            PreparedStatement var5 = null;
            try {
                var4 = DatabaseFactory.getInstance().getConnection();
                PreparedStatement var12 = var4.prepareStatement("DELETE FROM `character_subclasses` WHERE `char_obj_id`=? AND `class_id`=? AND `isBase` = 0");
                var12.setInt(1, this.getObjectId());
                var12.setInt(2, var1);
                var12.execute();
                DbUtils.close((Statement)var12);
                CharacterSkillsDAO.getInstance().deleteAllByClass(this.getObjectId(), var1);
                PreparedStatement var13 = var4.prepareStatement("DELETE FROM `character_skills_save` WHERE `char_obj_id`=? AND `class_index`=?");
                var13.setInt(1, this.getObjectId());
                var13.setInt(2, var1);
                var13.execute();
                DbUtils.close((Statement)var13);
                PreparedStatement var14 = var4.prepareStatement("DELETE FROM `character_effects_save` WHERE `object_id`=? AND `id`=?");
                var14.setInt(1, this.getObjectId());
                var14.setInt(2, var1);
                var14.execute();
                DbUtils.close((Statement)var14);
                PreparedStatement var15 = var4.prepareStatement("DELETE FROM `character_hennas` WHERE `char_obj_id`=? AND `class_index`=?");
                var15.setInt(1, this.getObjectId());
                var15.setInt(2, var1);
                var15.execute();
                DbUtils.close((Statement)var15);
                var5 = var4.prepareStatement("DELETE FROM `character_shortcuts` WHERE `object_id`=? AND `class_index`=?");
                var5.setInt(1, this.getObjectId());
                var5.setInt(2, var1);
                var5.execute();
                DbUtils.close((Statement)var5);
                DbUtils.closeQuietly((Connection)var4, (Statement)var5);
            }
            catch (Exception var10) {
                logger.warn("Could not delete char sub-class: " + var10);
                logger.error("", (Throwable)var10);
            }
            finally {
                DbUtils.closeQuietly((Connection)var4, var5);
            }
            this._classlist.remove(var1);
            return var2 <= 0 || this.addSubClass(var2, false);
        }
        return false;
    }

    public void setActiveSubClass(int var1, boolean var2) {
        SubClass var3 = this.getSubClasses().get(var1);
        if (var3 != null) {
            try {
                if (this.getActiveClass() != null) {
                    QuestState var11;
                    String var9;
                    EffectsDAO.getInstance().insert((Playable)this);
                    this.storeDisableSkills();
                    if (Config.ALT_MULTI_SKILLS_TRANSFER) {
                        for (Skill var5 : this._skills.values()) {
                            if (!var5.isMultiClassSkill()) continue;
                            this.var_3250.put(var5.getId(), var5);
                        }
                    }
                    if (QuestManager.getQuest((int)422) != null && (var9 = QuestManager.getQuest((int)422).getName()) != null && (var11 = this.getQuestState(var9)) != null) {
                        var11.exitCurrentQuest(true);
                    }
                }
            }
            catch (Exception var8) {
                logger.warn("", (Throwable)var8);
            }
            SubClass var10 = this.getActiveClass();
            if (var10 != null) {
                var10.setActive(false);
                if (var2) {
                    var10.setCp(this.getCurrentCp());
                    var10.setHp(this.getCurrentHp());
                    var10.setMp(this.getCurrentMp());
                    this.getSubClasses().put(this.getActiveClassId(), var10);
                }
            }
            var3.setActive(true);
            this.setActiveClass(var3);
            this.getSubClasses().put(this.getActiveClassId(), var3);
            this.setClassId(var1, false, false);
            this.removeAllSkills();
            this.getEffectList().stopAllEffects();
            if (this.getPet() != null && (this.getPet().isSummon() || Config.ALT_IMPROVED_PETS_LIMITED_USE && (PetDataHolder.getInstance().getInfo(this.getPet().getNpcId()).isImprovedBabyKookaburra() && !this.isMageClass() || PetDataHolder.getInstance().getInfo(this.getPet().getNpcId()).isImprovedBabyBuffalo() && this.isMageClass()))) {
                this.getPet().unSummon();
            }
            this.setAgathion(0);
            this.func175();
            if (Config.ALT_MULTI_SKILLS_TRANSFER) {
                ArrayList<Skill> var12 = new ArrayList<>();
                for (Skill var7 : this.var_3250.values()) {
                    this.addSkill(var7, false);
                    var12.add(var7);
                }
                CharacterSkillsDAO.getInstance().store(this, var12);
            }
            if (Config.ALT_SUBLASS_SKILL_TRANSFER && this.getActiveClassId() == var1) {
                for (SubClass var16 : this.getSubClasses().values()) {
                    if (var16.getClassId() == var1) continue;
                    this.func176(var16.getClassId());
                }
            }
            this.func163(false, 0);
            this.var_3203 = 0;
            this.var_3202 = 0;
            this.checkSkills();
            this.sendPacket((IStaticPacket)new ExStorageMaxCount(this));
            this.refreshExpertisePenalty();
            if (Config.EX_COSTUME_SYSTEM && this.var_3217 != null) {
                this.var_3217.onSubclassChange();
            }
            this.sendSkillList();
            this.getInventory().refreshEquip();
            this.getInventory().validateItems();
            for (int var14 = 0; var14 < 3; ++var14) {
                this.var_3173[var14] = null;
            }
            this.func177();
            this.sendPacket((IStaticPacket)new HennaInfo(this));
            EffectsDAO.getInstance().restoreEffects((Playable)this);
            this.restoreDisableSkills();
            this.setCurrentHpMp(var3.getHp(), var3.getMp());
            this.setCurrentCp(var3.getCp());
            this.var_3162.restore();
            this.sendPacket((IStaticPacket)new ShortCutInit(this));
            for (int var17 : this.getAutoSoulShot()) {
                this.sendPacket((IStaticPacket)new ExAutoSoulShot(var17, true, 0));
            }
            this.sendPacket((IStaticPacket)new SkillCoolTime(this));
            this.broadcastPacket(new L2GameServerPacket[]{new SocialAction(this.getObjectId(), 2122)});
            this.getDeathPenalty().restore(this);
            this.setIncreasedForce(0);
            this.startHourlyTask();
            this.broadcastCharInfo();
            this.updateEffectIcons();
            this.updateStats();
        }
    }

    public void startKickTask(long var1) {
        this.stopKickTask();
        this.var_3232 = ThreadPoolManager.getInstance().schedule((Runnable)new GameObjectTasks.KickTask(this), var1);
    }

    public void stopKickTask() {
        if (this.var_3232 != null) {
            this.var_3232.cancel(false);
            this.var_3232 = null;
        }
    }

    public void startBonusTask() {
        if (Config.SERVICES_RATE_ENABLED) {
            AccountBonusDAO.getInstance().load(this.getAccountName(), this.getBonus());
            long var1 = this.getBonus().getBonusExpire();
            if (var1 > System.currentTimeMillis() / 1000L) {
                if (this.var_3222 == null) {
                    this.var_3222 = LazyPrecisionTaskManager.getInstance().startBonusExpirationTask(this);
                    this.updatePremiumSkills();
                    this.sendSkillList();
                }
            } else if (var1 > 0L) {
                AccountBonusDAO.getInstance().delete(this.getAccountName());
            }
        }
    }

    public void stopBonusTask() {
        if (this.var_3222 != null) {
            this.var_3222.cancel(false);
            this.var_3222 = null;
        }
    }

    public int getInventoryLimit() {
        return (int)this.calcStat(Stats.INVENTORY_LIMIT, 0.0, null, null);
    }

    public int getWarehouseLimit() {
        return (int)this.calcStat(Stats.STORAGE_LIMIT, 0.0, null, null);
    }

    public int getTradeLimit() {
        return (int)this.calcStat(Stats.TRADE_LIMIT, 0.0, null, null);
    }

    public int getDwarvenRecipeLimit() {
        return (int)this.calcStat(Stats.DWARVEN_RECIPE_LIMIT, 50.0, null, null) + Config.ALT_ADD_RECIPES;
    }

    public int getCommonRecipeLimit() {
        return (int)this.calcStat(Stats.COMMON_RECIPE_LIMIT, 50.0, null, null) + Config.ALT_ADD_RECIPES;
    }

    public int getBeltInventoryIncrease() {
        ItemInstance var1 = this.getInventory().getPaperdollItem(29);
        if (var1 != null && var1.getTemplate().getAttachedSkills() != null) {
            for (Skill var5 : var1.getTemplate().getAttachedSkills()) {
                for (FuncTemplate var9 : var5.getAttachedFuncs()) {
                    if (var9._stat != Stats.INVENTORY_LIMIT) continue;
                    return (int)var9._value;
                }
            }
        }
        return 0;
    }

    public Element getAttackElement() {
        return Formulas.getAttackElement((Creature)((Object)this), null);
    }

    public int getAttack(Element var1) {
        return var1 == Element.NONE ? 0 : (int)this.calcStat(var1.getAttack(), 0.0, null, null);
    }

    public int getDefence(Element var1) {
        return var1 == Element.NONE ? 0 : (int)this.calcStat(var1.getDefence(), 0.0, null, null);
    }

    public boolean getAndSetLastItemAuctionRequest() {
        if (this.var_3267 + 2000L < System.currentTimeMillis()) {
            this.var_3267 = System.currentTimeMillis();
            return true;
        }
        this.var_3267 = System.currentTimeMillis();
        return false;
    }

    public int getNpcId() {
        return -2;
    }

    public GameObject getVisibleObject(int var1) {
        if (this.getObjectId() == var1) {
            return this;
        }
        Object var2 = null;
        if (this.getTargetId() == var1) {
            var2 = this.getTarget();
        }
        if (var2 == null && this.var_3180 != null) {
            for (Player var4 : this.var_3180.getPartyMembers()) {
                if (var4 == null || var4.getObjectId() != var1) continue;
                var2 = var4;
                break;
            }
        }
        if (var2 == null) {
            var2 = World.getAroundObjectById((GameObject)this, (int)var1);
        }
        return (GameObject)(var2 != null && var2 instanceof Creature && !((Creature)var2).isInvisible() ? var2 : null);
    }

    public int getPAtk(Creature var1) {
        double var2 = this.getActiveWeaponInstance() == null ? (double)(this.isMageClass() ? 3 : 4) : 0.0;
        return (int)this.calcStat(Stats.POWER_ATTACK, var2, var1, null);
    }

    public int getPDef(Creature var1) {
        double var2 = 4.0;
        ItemInstance var4 = this.getInventory().getPaperdollItem(6);
        if (var4 == null) {
            var2 += this.isMageClass() ? 15.0 : 31.0;
        }
        if (this.getInventory().getPaperdollItem(11) == null && (var4 == null || var4.getBodyPart() != 32768L)) {
            var2 += this.isMageClass() ? 8.0 : 18.0;
        }
        if (this.getInventory().getPaperdollItem(1) == null) {
            var2 += 12.0;
        }
        if (this.getInventory().getPaperdollItem(10) == null) {
            var2 += 8.0;
        }
        if (this.getInventory().getPaperdollItem(12) == null) {
            var2 += 7.0;
        }
        return (int)this.calcStat(Stats.POWER_DEFENCE, var2, var1, null);
    }

    public int getMDef(Creature var1, Skill var2) {
        double var3 = 0.0;
        if (this.getInventory().getPaperdollItem(9) == null) {
            var3 += 9.0;
        }
        if (this.getInventory().getPaperdollItem(8) == null) {
            var3 += 9.0;
        }
        if (this.getInventory().getPaperdollItem(4) == null) {
            var3 += 13.0;
        }
        if (this.getInventory().getPaperdollItem(14) == null) {
            var3 += 5.0;
        }
        if (this.getInventory().getPaperdollItem(13) == null) {
            var3 += 5.0;
        }
        return (int)this.calcStat(Stats.MAGIC_DEFENCE, var3, var1, var2);
    }

    public int getPAtkSpd() {
        int var1;
        if (this.isMounted() && (var1 = this.getMountCurrentFed()) >= 0 && var1 * 55 < this.getMountMaxFed() * 100) {
            return super.getPAtkSpd() / 2;
        }
        return super.getPAtkSpd();
    }

    public boolean isSubClassActive() {
        return !this.getActiveClass().isBase();
    }

    public String getTitle() {
        return super.getTitle();
    }

    public int getTitleColor() {
        return this.var_3141;
    }

    public void setTitleColor(int var1) {
        if (var1 != 0xFFFF77) {
            this.setVar("titlecolor", Integer.toHexString(var1), -1L);
        } else {
            this.unsetVar("titlecolor");
        }
        this.var_3141 = var1;
    }

    public String getDisconnectedTitle() {
        return this.var_3144;
    }

    public void setDisconnectedTitle(String var1) {
        this.var_3144 = var1;
    }

    public int getDisconnectedTitleColor() {
        return this.var_3145;
    }

    public void setDisconnectedTitleColor(int var1) {
        this.var_3145 = var1;
    }

    public boolean isCursedWeaponEquipped() {
        return this.var_3227 != 0;
    }

    public int getCursedWeaponEquippedId() {
        return this.var_3227;
    }

    public void setCursedWeaponEquippedId(int var1) {
        this.var_3227 = var1;
    }

    public boolean isImmobilized() {
        return super.isImmobilized() || this.isOverloaded() || this.isSitting() || this.isFishing();
    }

    public boolean isBlocked() {
        return super.isBlocked() || this.isInMovie() || this.isInObserverMode() || this.isTeleporting() || this.isLogoutStarted();
    }

    public boolean isInvul() {
        return super.isInvul() || this.isInMovie() || this.getAfterTeleportPortectionTime() > System.currentTimeMillis() || this.getNoCarrierProtectionTime() > System.currentTimeMillis();
    }

    public boolean isResurectProhibited() {
        return this.var_3295;
    }

    public void setResurectProhibited(boolean var1) {
        this.var_3295 = var1;
    }

    public boolean isOverloaded() {
        return this.var_3146;
    }

    public void setOverloaded(boolean var1) {
        this.var_3146 = var1;
    }

    public boolean isFishing() {
        return this.var_3229;
    }

    public Fishing getFishing() {
        return this.var_3228;
    }

    public void setFishing(boolean var1) {
        this.var_3229 = var1;
    }

    public void startFishing(FishTemplate var1, int var2) {
        this.var_3228.setFish(var1);
        this.var_3228.setLureId(var2);
        this.var_3228.startFishing();
    }

    public void stopFishing() {
        this.var_3228.stopFishing();
    }

    public Location getFishLoc() {
        return this.var_3228.getFishLoc();
    }

    public Bonus getBonus() {
        return this.var_3221;
    }

    public boolean hasBonus() {
        return !this.var_3221.isExpired();
    }

    public double getRateAdena() {
        return this.calcStat(Stats.ADENA_REWARD_MULTIPLIER, this.var_3180 == null ? (double)this.var_3221.getDropAdena() : this.var_3180._rateAdena);
    }

    public double getRateItems() {
        return this.calcStat(Stats.ITEM_REWARD_MULTIPLIER, this.var_3180 == null ? (double)this.var_3221.getDropItems() : this.var_3180._rateDrop);
    }

    public double getRateExp() {
        return this.calcStat(Stats.EXP, this.var_3180 == null ? (double)this.var_3221.getRateXp() : this.var_3180._rateExp, null, null);
    }

    public double getRateSp() {
        return this.calcStat(Stats.SP, this.var_3180 == null ? (double)this.var_3221.getRateSp() : this.var_3180._rateSp, null, null);
    }

    public double getRateSpoil() {
        return this.calcStat(Stats.SPOIL_REWARD_MULTIPLIER, this.var_3180 == null ? (double)this.var_3221.getDropSpoil() : this.var_3180._rateSpoil);
    }

    public double getRateSealStones() {
        return this.calcStat(Stats.SEAL_STONES_REWARD_MULTIPLIER, this.var_3180 == null ? (double)this.var_3221.getDropSealStones() : this.var_3180._rateSealStones, null, null);
    }

    public double getRaidRateExp() {
        return this.calcStat(Stats.RAID_EXP, this.var_3180 == null ? (double)this.var_3221.getRateRaidXp() : this.var_3180._rateRaidExp, null, null);
    }

    public double getRaidRateSp() {
        return this.calcStat(Stats.RAID_SP, this.var_3180 == null ? (double)this.var_3221.getRateRaidSp() : this.var_3180._rateRaidSp, null, null);
    }

    public double getQuestRateDrop() {
        return this.calcStat(Stats.QUEST_DROP_MULTIPLIER, this.var_3221.getQuestDropRate(), null, null);
    }

    public double getEnchantBonusMul() {
        return this.calcStat(Stats.ENCHANT_BONUS_MULTIPLIER, this.var_3221.getEnchantItemMul());
    }

    public double getEnchantSkillBonusMul() {
        return this.calcStat(Stats.ENCHANT_SKILL_BONUS_MULTIPLIER, this.var_3221.getEnchantSkillMul());
    }

    public boolean isMaried() {
        return this.var_3296;
    }

    public void setMaried(boolean var1) {
        this.var_3296 = var1;
    }

    public boolean isMaryRequest() {
        return this.var_3299;
    }

    public void setMaryRequest(boolean var1) {
        this.var_3299 = var1;
    }

    public boolean isMaryAccepted() {
        return this.var_3300;
    }

    public void setMaryAccepted(boolean var1) {
        this.var_3300 = var1;
    }

    public int getPartnerId() {
        return this.var_3297;
    }

    public void setPartnerId(int var1) {
        this.var_3297 = var1;
    }

    public int getCoupleId() {
        return this.var_3298;
    }

    public void setCoupleId(int var1) {
        this.var_3298 = var1;
    }

    public boolean isUndying() {
        return this.var_3125;
    }

    public void setUndying(boolean var1) {
        if (this.isGM()) {
            this.var_3125 = var1;
        }
    }

    public void resetReuse() {
        this.getSkillReuses0().clear();
        this.var_3268.clear();
    }

    public DeathPenalty getDeathPenalty() {
        return this._activeClass == null ? null : this._activeClass.getDeathPenalty(this);
    }

    public boolean isCharmOfCourage() {
        return this.var_3301;
    }

    public void setCharmOfCourage(boolean var1) {
        this.var_3301 = var1;
        if (!var1) {
            this.getEffectList().stopEffect(5041);
        }
        this.sendEtcStatusUpdate();
    }

    public int getIncreasedForce() {
        return this.var_3302;
    }

    public void setIncreasedForce(int var1) {
        if (this.var_3302 != var1) {
            var1 = Math.min(var1, 7);
            if ((var1 = Math.max(var1, 0)) != 0 && var1 > this.var_3302) {
                this.var_3303 = System.currentTimeMillis();
                if (this.var_3304 == null) {
                    this.var_3304 = ThreadPoolManager.getInstance().schedule((Runnable)new ForceCleanupTask(), 600000L);
                }
                this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOUR_FORCE_HAS_INCREASED_TO_LEVEL_S1).addNumber(var1));
            }
            this.var_3302 = var1;
            this.sendEtcStatusUpdate();
        }
    }

    public boolean isFalling() {
        return System.currentTimeMillis() - this.var_3305 < 5000L;
    }

    public void falling(int var1) {
        if (!(!Config.DAMAGE_FROM_FALLING || this.isDead() || this.isFlying() || this.isInWater() || this.isInBoat())) {
            this.var_3305 = System.currentTimeMillis();
            int var2 = (int)this.calcStat(Stats.FALL, (double)this.getMaxHp() / 2000.0 * (double)var1, null, null);
            if (var2 > 0) {
                int var3 = (int)this.getCurrentHp();
                if (var3 - var2 < 1) {
                    this.setCurrentHp(1.0, false);
                } else {
                    this.setCurrentHp(var3 - var2, false);
                }
                this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_RECEIVED_S1_FALLING_DAMAGE).addNumber(var2));
            }
        }
    }

    public void checkHpMessages(double var1, double var3) {
        int[] var5 = new int[]{30, 30};
        int[] var6 = new int[]{290, 291};
        double var7 = this.getMaxHp() / 100;
        double var9 = var1 / var7;
        double var11 = var3 / var7;
        boolean var13 = false;
        for (int var14 = 0; var14 < var6.length; ++var14) {
            int var15 = this.getSkillLevel(var6[var14]);
            if (var15 <= 0) continue;
            if (var9 > (double)var5[var14] && var11 <= (double)var5[var14]) {
                this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.SINCE_YOUR_HP_HAS_DECREASED_THE_EFFECT_OF_S1_CAN_BE_FELT).addSkillName(var6[var14], var15));
                var13 = true;
                continue;
            }
            if (!(var9 <= (double)var5[var14]) || !(var11 > (double)var5[var14])) continue;
            this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.SINCE_YOUR_HP_HAS_INCREASED_THE_EFFECT_OF_S1_WILL_DISAPPEAR).addSkillName(var6[var14], var15));
            var13 = true;
        }
        if (var13) {
            this.sendChanges();
        }
    }

    public void checkDayNightMessages() {
        int var1 = this.getSkillLevel(294);
        if (var1 > 0) {
            if (GameTimeController.getInstance().isNowNight()) {
                this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.IT_IS_NOW_MIDNIGHT_AND_THE_EFFECT_OF_S1_CAN_BE_FELT).addSkillName(294, var1));
            } else {
                this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.IT_IS_DAWN_AND_THE_EFFECT_OF_S1_WILL_NOW_DISAPPEAR).addSkillName(294, var1));
            }
        }
        this.sendChanges();
    }

    public int getZoneMask() {
        return this.var_3239;
    }

    protected void onUpdateZones(List<Zone> var1, List<Zone> var2) {
        super.onUpdateZones(var1, var2);
        if (!var1.isEmpty() || !var2.isEmpty()) {
            FlagItemAttachment var14;
            boolean var3 = (this.var_3239 & 0x4000) == 16384;
            boolean var4 = (this.var_3239 & 0x100) == 256;
            boolean var5 = (this.var_3239 & 0x800) == 2048;
            boolean var6 = (this.var_3239 & 0x1000) == 4096;
            boolean var7 = this.isInCombatZone();
            boolean var8 = this.isInDangerArea();
            boolean var9 = this.isInZone(Zone.ZoneType.fun);
            boolean var10 = this.isOnSiegeField() || var9;
            boolean var11 = this.isInPeaceZone();
            boolean var12 = this.isInSSQZone();
            int var13 = this.var_3239;
            this.var_3239 = 0;
            if (var7) {
                this.var_3239 |= 0x4000;
            }
            if (var8) {
                this.var_3239 |= 0x100;
            }
            if (var10) {
                this.var_3239 |= 0x800;
            }
            if (var11) {
                this.var_3239 |= 0x1000;
            }
            if (var12) {
                this.var_3239 |= 0x2000;
            }
            if (var13 != this.var_3239) {
                this.sendPacket((IStaticPacket)new ExSetCompassZoneCode(this));
            }
            if (var3 != var7) {
                this.broadcastRelation();
            }
            if (var4 != var8) {
                this.sendPacket((IStaticPacket)new EtcStatusUpdate(this));
            }
            if (var5 != var10) {
                this.broadcastRelation();
                if (var10) {
                    this.sendPacket((IStaticPacket)SystemMsg.YOU_HAVE_ENTERED_A_COMBAT_ZONE);
                    if (Config.FUN_ZONE_FLAG_ON_ENTER && var9 && !this.isTeleporting() && this.getPvpFlag() == 0) {
                        this.startPvPFlag(null);
                    }
                } else {
                    this.sendPacket((IStaticPacket)SystemMsg.YOU_HAVE_LEFT_A_COMBAT_ZONE);
                    if (!this.isTeleporting() && this.getPvpFlag() == 0) {
                        this.startPvPFlag(null);
                    }
                }
            }
            if (var11 && !var6 && (var14 = this.getActiveWeaponFlagAttachment()) != null) {
                var14.onEnterPeace(this);
            }
            if (var6 != var11) {
                if (var11) {
                    this.startVitalityTask();
                } else {
                    this.stopVitalityTask();
                }
            }
            if (this.isInWater()) {
                this.startWaterTask();
            } else {
                this.stopWaterTask();
            }
        }
    }

    public void startAutoSaveTask() {
        if (Config.AUTOSAVE && this.var_3231 == null) {
            this.var_3231 = AutoSaveManager.getInstance().addAutoSaveTask(this);
        }
    }

    public void stopAutoSaveTask() {
        if (this.var_3231 != null) {
            this.var_3231.cancel(false);
        }
        this.var_3231 = null;
    }

    public void startVitalityTask() {
        if (Config.ALT_VITALITY_ENABLED && this.var_3234 == null) {
            this.var_3234 = LazyPrecisionTaskManager.getInstance().addVitalityRegenTask(this);
        }
    }

    public void stopVitalityTask() {
        if (this.var_3234 != null) {
            this.var_3234.cancel(false);
        }
        this.var_3234 = null;
    }

    public void startPcBangPointsTask() {
        if (Config.ALT_PCBANG_POINTS_ENABLED && Config.ALT_PCBANG_POINTS_DELAY > 0) {
            String var2;
            GameClient var1;
            if (Config.ALT_PCBANG_CHECK_HWID && (var1 = this.getNetConnection()) != null && !StringUtils.isBlank((CharSequence)(var2 = var1.getHwid()))) {
                for (Player var4 : GameObjectsStorage.getAllPlayersForIterate()) {
                    GameClient var5;
                    if (var4 == null || var4 == this || !var4.isOnline() || (var5 = var4.getNetConnection()) == null || !StringUtils.equalsIgnoreCase((CharSequence)var2, (CharSequence)var5.getHwid())) continue;
                    return;
                }
            }
            if (this.var_3233 == null) {
                this.var_3233 = LazyPrecisionTaskManager.getInstance().addPCCafePointsTask(this);
            }
        }
    }

    public void stopPcBangPointsTask() {
        if (this.var_3233 != null) {
            this.var_3233.cancel(false);
        }
        this.var_3233 = null;
    }

    public void startUnjailTask(Player var1, long var2) {
        if (this.var_3235 != null) {
            this.var_3235.cancel(false);
        }
        this.var_3235 = ThreadPoolManager.getInstance().schedule((Runnable)new GameObjectTasks.UnJailTask(var1), var2);
    }

    public void stopUnjailTask() {
        if (this.var_3235 != null) {
            this.var_3235.cancel(false);
        }
        this.var_3235 = null;
    }

    public void sendMessage(String var1) {
        this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.S1).addString(var1));
    }

    public Location getLastClientPosition() {
        return this.var_3306;
    }

    public void setLastClientPosition(Location var1) {
        this.var_3306 = var1;
    }

    public Location getLastServerPosition() {
        return this.var_3307;
    }

    public void setLastServerPosition(Location var1) {
        this.var_3307 = var1;
    }

    public int getUseSeed() {
        return this.var_3308;
    }

    public void setUseSeed(int var1) {
        this.var_3308 = var1;
    }

    public int getRelation(Player var1) {
        Party var3;
        int var2 = 0;
        if (this.getClan() != null) {
            var2 |= 0x40;
            if (this.getClan() == var1.getClan()) {
                var2 |= 0x100;
            }
            if (this.getClan().getAllyId() != 0) {
                var2 |= 0x10000;
            }
        }
        if (this.isClanLeader()) {
            var2 |= 0x80;
        }
        if ((var3 = this.getParty()) != null && var3 == var1.getParty()) {
            var2 |= 0x20;
            switch (var3.getPartyMembers().indexOf((Object)this)) {
                case 0: {
                    var2 |= 0x10;
                    break;
                }
                case 1: {
                    var2 |= 8;
                    break;
                }
                case 2: {
                    var2 |= 7;
                    break;
                }
                case 3: {
                    var2 |= 6;
                    break;
                }
                case 4: {
                    var2 |= 5;
                    break;
                }
                case 5: {
                    var2 |= 4;
                    break;
                }
                case 6: {
                    var2 |= 3;
                    break;
                }
                case 7: {
                    var2 |= 2;
                    break;
                }
                case 8: {
                    var2 |= 1;
                }
            }
        }
        Clan var4 = this.getClan();
        Clan var5 = var1.getClan();
        if (var4 != null && var5 != null && var1.getPledgeType() != -1 && this.getPledgeType() != -1 && var5.isAtWarWith(var4.getClanId())) {
            var2 |= 0x4000;
            if (var4.isAtWarWith(var5.getClanId())) {
                var2 |= 0x8000;
            }
        }
        for (GlobalEvent var7 : this.getEvents()) {
            var2 = var7.getRelation(this, var1, var2);
        }
        return var2;
    }

    public long getlastPvpAttack() {
        return this.var_3310;
    }

    public void startPvPFlag(Creature var1) {
        if (this.var_3119 <= 0) {
            long var2 = System.currentTimeMillis();
            if (var1 != null && var1.getPvpFlag() != 0) {
                var2 -= (long)Math.max(0, Config.PVP_TIME - Config.PVP_FLAG_ON_UN_FLAG_TIME);
            }
            if (this._pvpFlag == 0 || this.var_3310 <= var2) {
                if (this.getFarmSystem().isAutofarming() && Config.AUTO_FARM_STOP_ON_PVP_FLAG) {
                    this.getFarmSystem().stopFarmTask();
                }
                this.var_3310 = var2;
                this.updatePvPFlag(1);
                if (this.var_3309 == null) {
                    this.var_3309 = ThreadPoolManager.getInstance().scheduleAtFixedRate((Runnable)new GameObjectTasks.PvPFlagTask(this), 1000L, 1000L);
                }
            }
        }
    }

    public void stopPvPFlag() {
        if (this.var_3309 != null) {
            this.var_3309.cancel(false);
            this.var_3309 = null;
        }
        this.updatePvPFlag(0);
    }

    public void updatePvPFlag(int var1) {
        if (this._pvpFlag != var1) {
            this.setPvpFlag(var1);
            this.sendStatusUpdate(true, true, 26);
            this.broadcastRelation();
        }
    }

    public int getPvpFlag() {
        return this._pvpFlag;
    }

    public void setPvpFlag(int var1) {
        this._pvpFlag = var1;
    }

    public boolean isInDuel() {
        return this.getEvent(DuelEvent.class) != null;
    }

    public TamedBeastInstance getTrainedBeast() {
        return this.var_3311;
    }

    public void setTrainedBeast(TamedBeastInstance var1) {
        this.var_3311 = var1;
    }

    public long getLastAttackPacket() {
        return this.var_3312;
    }

    public void setLastAttackPacket() {
        this.var_3312 = System.currentTimeMillis();
    }

    public byte[] getKeyBindings() {
        return this.var_3226;
    }

    public void setKeyBindings(byte[] var1) {
        if (var1 == null) {
            var1 = ArrayUtils.EMPTY_BYTE_ARRAY;
        }
        this.var_3226 = var1;
    }

    private void func182(Skill var1) {
        if (var1 == null || !var1.isBaseTransformation()) {
            for (Effect var3 : this.getEffectList().getAllEffects()) {
                if (var3 == null || !var3.getSkill().isToggle()) continue;
                var3.exit();
            }
        }
    }

    public boolean isInFlyingTransform() {
        return this.var_3241 == 8 || this.var_3241 == 9 || this.var_3241 == 260;
    }

    public boolean isInMountTransform() {
        return this.var_3245;
    }

    public void setInMountTransform(boolean var1) {
        this.var_3245 = var1;
    }

    public int getTransformation() {
        return this.var_3241;
    }

    public void setTransformation(int var1) {
        if (var1 != this.var_3241 && (this.var_3241 == 0 || var1 == 0)) {
            if (var1 == 0) {
                for (Effect var3 : this.getEffectList().getAllEffects()) {
                    if (var3 == null || var3.getEffectType() != EffectType.Transformation || var3.calc() == 0.0) continue;
                    var3.exit();
                    this.func182(var3.getSkill());
                    break;
                }
                if (!this._transformationSkills.isEmpty()) {
                    for (Skill var16 : this._transformationSkills.values()) {
                        if (var16.isCommon() || SkillAcquireHolder.getInstance().isSkillPossible(this, var16) || var16.isHeroic()) continue;
                        super.removeSkill(var16);
                    }
                    this._transformationSkills.clear();
                }
            } else {
                if (this.isCursedWeaponEquipped()) {
                    this.func182(null);
                } else {
                    for (Effect var17 : this.getEffectList().getAllEffects()) {
                        if (var17 == null || var17.getEffectType() != EffectType.Transformation) continue;
                        if (var17.getSkill() instanceof Transformation && ((Transformation)var17.getSkill()).isDisguise) {
                            for (Skill var23 : this.getAllSkills()) {
                                if (var23 == null || !var23.isActive() && !var23.isToggle()) continue;
                                this._transformationSkills.put(var23.getId(), var23);
                            }
                        } else {
                            for (Skill.AddedSkill var7 : var17.getSkill().getAddedSkills()) {
                                if (var7.level == 0) {
                                    int var8 = this.getSkillLevel(var7.id);
                                    if (var8 <= 0) continue;
                                    this._transformationSkills.put(var7.id, SkillTable.getInstance().getInfo(var7.id, var8));
                                    continue;
                                }
                                if (var7.level == -2) {
                                    int var24 = Math.max(var17.getSkill().getMagicLevel(), 40);
                                    int var9 = SkillTable.getInstance().getBaseLevel(var7.id);
                                    int var10 = 1;
                                    var10 = var9 > 3 ? (var10 += this.getLevel() - var24) : (var10 += (this.getLevel() - var24) / ((76 - var24) / var9));
                                    var10 = Math.min(Math.max(var10, 1), var9);
                                    this._transformationSkills.put(var7.id, SkillTable.getInstance().getInfo(var7.id, var10));
                                    continue;
                                }
                                this._transformationSkills.put(var7.id, var7.getSkill());
                            }
                        }
                        this.func182(var17.getSkill());
                        break;
                    }
                }
                if (!this.isOlyParticipant() && this.isCursedWeaponEquipped() && this.var_3218 && this.getActiveClass().isBase()) {
                    for (SkillLearn var18 : SkillAcquireHolder.getInstance().getAvailableSkills(this, AcquireType.HERO)) {
                        Skill var22 = SkillTable.getInstance().getInfo(var18.getId(), var18.getLevel());
                        if (var22 == null || this.getSkillLevel(var22.getId()) >= var22.getLevel()) continue;
                        this._transformationSkills.put(var22.getId(), var22);
                    }
                }
                for (Skill var19 : this._transformationSkills.values()) {
                    this.addSkill(var19, false);
                }
            }
            this.var_3241 = var1;
            this.sendPacket((IStaticPacket)new ExBasicActionList(this));
            this.sendSkillList();
            this.sendPacket((IStaticPacket)new ShortCutInit(this));
            var iterator = this.getAutoSoulShot().iterator();
            while (iterator.hasNext()) {
                int var20 = (Integer)iterator.next();
                this.sendPacket((IStaticPacket)new ExAutoSoulShot(var20, true, 0));
            }
            this.broadcastUserInfo(true, new UserInfoType[0]);
            this.sendPacket((IStaticPacket)new ExUserInfoAbnormalVisualEffect(this));
        }
    }

    public String getTransformationName() {
        return this.var_3243;
    }

    public void setTransformationName(String var1) {
        this.var_3243 = var1;
    }

    public String getTransformationTitle() {
        return this.var_3244;
    }

    public void setTransformationTitle(String var1) {
        this.var_3244 = var1;
    }

    public int getTransformationTemplate() {
        return this.var_3242;
    }

    public void setTransformationTemplate(int var1) {
        this.var_3242 = var1;
    }

    public final Collection<Skill> getAllSkills() {
        if (this.var_3241 == 0 && !this.isCursedWeaponEquipped()) {
            return super.getAllSkills();
        }
        HashMap<Integer, Skill> var1 = new HashMap<Integer, Skill>();
        for (Skill var3 : super.getAllSkills()) {
            if (var3 == null || var3.isActive() || var3.isToggle()) continue;
            var1.put(var3.getId(), var3);
        }
        var1.putAll(this._transformationSkills);
        return var1.values();
    }

    public void setAgathion(int var1) {
        if (this.var_3197 != var1) {
            this.var_3197 = var1;
            this.broadcastCharInfo();
            this.sendPacket((IStaticPacket)new ExUserInfoCubic(this));
        }
    }

    public int getAgathionId() {
        return this.var_3197;
    }

    public int getPcBangPoints() {
        return this.var_3249;
    }

    public void setPcBangPoints(int var1) {
        this.var_3249 = var1;
    }

    public void setPcBangPoints(int var1, String var2) {
        var1 = Math.min(Config.LIM_PC_BANG_POINTS, var1);
        if (var2 != null && !var2.isEmpty()) {
            Log.add((String)(this._name + "|" + (var1 - this.var_3249) + "|" + var1 + "|" + var2), (String)"pcBang");
        }
        if (var1 > this.var_3249) {
            this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_ACQUIRED_S1_PC_BANG_POINT).addNumber(var1 - this.var_3249));
        }
        this.setPcBangPoints(var1);
    }

    public void addPcBangPoints(int var1, boolean var2) {
        if (var2) {
            var1 = (int)((double)var1 * Config.ALT_PCBANG_POINTS_BONUS_DOUBLE_RATE);
        }
        if (var1 + this.var_3249 > Config.LIM_PC_BANG_POINTS) {
            var1 = Config.LIM_PC_BANG_POINTS - this.var_3249;
        }
        this.var_3249 += var1;
        if (var1 > 0) {
            this.sendPacket((IStaticPacket)new SystemMessage(var2 ? SystemMsg.DOUBLE_POINTS_YOU_ACQUIRED_S1_PC_BANG_POINT : SystemMsg.YOU_ACQUIRED_S1_PC_BANG_POINT).addNumber(var1));
            this.sendPacket((IStaticPacket)new ExPCCafePointInfo(this, var1, 1, 2, 12));
        } else {
            this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THE_PC_BANG_POINTS_ACCUMULATION_PERIOD_HAS_EXPIRED));
        }
    }

    public boolean reducePcBangPoints(int var1) {
        if (this.var_3249 < var1) {
            return false;
        }
        this.var_3249 -= var1;
        this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_ARE_USING_S1_POINT).addNumber(var1));
        this.sendPacket((IStaticPacket)new ExPCCafePointInfo(this, 0, 1, 2, 12));
        return true;
    }

    public int getRaidBossPoints() {
        return this.var_3251;
    }

    public void setRaidBossPoints(int var1) {
        this.var_3251 = var1;
    }

    public void addRaidBossPoints(int var1) {
        if ((long)this.getRaidBossPoints() + (long)var1 > Integer.MAX_VALUE) {
            this.sendPacket((IStaticPacket)SystemMsg.YOU_HAVE_REACHED_THE_MAXIMUM_AMOUNT_OF_RAID_POINTS_AND_CAN_ACQUIRE_NO_MORE);
        } else {
            this.setRaidBossPoints(this.getRaidBossPoints() + var1);
            this.sendUserInfo(true, UserInfoType.STATS);
        }
    }

    public boolean reduceRaidBossPoints(int var1) {
        if (this.var_3251 < var1) {
            return false;
        }
        this.var_3251 -= var1;
        this.sendUserInfo(true, UserInfoType.STATS);
        this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_CONSUMED_S1_RAID_POINTS).addNumber(var1));
        return true;
    }

    public void broadcastPlayerJump(Player var1) {
        long var2 = System.currentTimeMillis();
        if (var1.isPlayer() && var2 - var1.var_3252 >= 800L) {
            this.broadcastPacket(new L2GameServerPacket[]{ExPlayAnimation.jump((Player)var1)});
            var1.var_3252 = var2;
        }
    }

    public Location getGroundSkillLoc() {
        return this.var_3313;
    }

    public void setGroundSkillLoc(Location var1) {
        this.var_3313 = var1;
    }

    public boolean isLogoutStarted() {
        return this.var_3208.get();
    }

    public void setOfflineMode(boolean var1) {
        if (!var1) {
            this.unsetVar("offline");
        }
        this.var_3240 = var1;
    }

    public boolean isInOfflineMode() {
        return this.var_3240;
    }

    public void saveTradeList() {
        Object var1 = "";
        if (this.var_3168 != null && !this.var_3168.isEmpty()) {
            for (TradeItem var3 : this.var_3168) {
                var1 = (String)var1 + var3.getObjectId() + ";" + var3.getCount() + ";" + var3.getOwnersPrice() + ":";
            }
            this.setVar("selllist", (String)var1, -1L);
            var1 = "";
            if (this.var_3172 != null && this.getSellStoreName() != null) {
                this.setVar("sellstorename", this.getSellStoreName(), -1L);
            }
        } else {
            this.unsetVar("selllist");
        }
        if (this.var_3169 != null && !this.var_3169.isEmpty()) {
            for (TradeItem var7 : this.var_3169) {
                var1 = (String)var1 + var7.getObjectId() + ";" + var7.getCount() + ";" + var7.getOwnersPrice() + ":";
            }
            this.setVar("packageselllist", (String)var1, -1L);
            var1 = "";
            if (this.var_3172 != null && this.getSellStoreName() != null) {
                this.setVar("sellstorename", this.getSellStoreName(), -1L);
            }
        } else {
            this.unsetVar("packageselllist");
        }
        if (this.var_3171 != null && !this.var_3171.isEmpty()) {
            for (TradeItem var8 : this.var_3171) {
                var1 = (String)var1 + var8.getItemId() + ";" + var8.getCount() + ";" + var8.getOwnersPrice() + ";" + var8.getEnchantLevel() + ":";
            }
            this.setVar("buylist", (String)var1, -1L);
            var1 = "";
            if (this.var_3172 != null && this.getBuyStoreName() != null) {
                this.setVar("buystorename", this.getBuyStoreName(), -1L);
            }
        } else {
            this.unsetVar("buylist");
        }
        if (this.var_3166 != null && !this.var_3166.isEmpty()) {
            for (ManufactureItem var9 : this.var_3166) {
                var1 = (String)var1 + var9.getRecipeId() + ";" + var9.getCost() + ":";
            }
            this.setVar("createlist", (String)var1, -1L);
            if (this.getManufactureName() != null) {
                this.setVar("manufacturename", this.getManufactureName(), -1L);
            }
        } else {
            this.unsetVar("createlist");
        }
    }

    public void restoreTradeList() {
        String var1 = this.getVar("selllist");
        if (var1 != null) {
            String[] var2;
            this.var_3168 = new CopyOnWriteArrayList<TradeItem>();
            for (String var6 : var2 = var1.split(":")) {
                String[] var7;
                if (var6.equals("") || (var7 = var6.split(";")).length < 3) continue;
                int var8 = Integer.parseInt(var7[0]);
                long var9 = Long.parseLong(var7[1]);
                long var11 = Long.parseLong(var7[2]);
                ItemInstance var13 = this.getInventory().getItemByObjectId(var8);
                if (var9 < 1L || var13 == null) continue;
                if (var9 > var13.getCount()) {
                    var9 = var13.getCount();
                }
                TradeItem var14 = new TradeItem(var13);
                var14.setCount(var9);
                var14.setOwnersPrice(var11);
                this.var_3168.add(var14);
            }
            var1 = this.getVar("sellstorename");
            if (var1 != null) {
                this.setSellStoreName(var1);
            }
        }
        if ((var1 = this.getVar("packageselllist")) != null) {
            String[] var22;
            this.var_3169 = new CopyOnWriteArrayList<TradeItem>();
            for (String var34 : var22 = var1.split(":")) {
                String[] var37;
                if (var34.equals("") || (var37 = var34.split(";")).length < 3) continue;
                int var40 = Integer.parseInt(var37[0]);
                long var43 = Long.parseLong(var37[1]);
                long var45 = Long.parseLong(var37[2]);
                ItemInstance var46 = this.getInventory().getItemByObjectId(var40);
                if (var43 < 1L || var46 == null) continue;
                if (var43 > var46.getCount()) {
                    var43 = var46.getCount();
                }
                TradeItem var47 = new TradeItem(var46);
                var47.setCount(var43);
                var47.setOwnersPrice(var45);
                this.var_3169.add(var47);
            }
            var1 = this.getVar("sellstorename");
            if (var1 != null) {
                this.setSellStoreName(var1);
            }
        }
        if ((var1 = this.getVar("buylist")) != null) {
            String[] var23;
            this.var_3171 = new CopyOnWriteArrayList<TradeItem>();
            for (String var35 : var23 = var1.split(":")) {
                String[] var38;
                if (var35.equals("") || (var38 = var35.split(";")).length < 3) continue;
                TradeItem var41 = new TradeItem();
                var41.setItemId(Integer.parseInt(var38[0]));
                var41.setCount(Long.parseLong(var38[1]));
                var41.setOwnersPrice(Long.parseLong(var38[2]));
                if (var38.length >= 4) {
                    var41.setEnchantLevel(Integer.parseInt(var38[3]));
                }
                this.var_3171.add(var41);
            }
            var1 = this.getVar("buystorename");
            if (var1 != null) {
                this.setBuyStoreName(var1);
            }
        }
        if ((var1 = this.getVar("createlist")) != null) {
            String[] var24;
            this.var_3166 = new CopyOnWriteArrayList<ManufactureItem>();
            for (String var36 : var24 = var1.split(":")) {
                String[] var39;
                if (var36.equals("") || (var39 = var36.split(";")).length < 2) continue;
                int var42 = Integer.parseInt(var39[0]);
                long var44 = Long.parseLong(var39[1]);
                if (!this.findRecipe(var42)) continue;
                this.var_3166.add(new ManufactureItem(var42, var44));
            }
            var1 = this.getVar("manufacturename");
            if (var1 != null) {
                this.setManufactureName(var1);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void restoreRecipeBook() {
        Connection var1 = null;
        PreparedStatement var2 = null;
        ResultSet var3 = null;
        try {
            var1 = DatabaseFactory.getInstance().getConnection();
            var2 = var1.prepareStatement("SELECT `id` FROM `character_recipebook` WHERE `char_id`=?");
            var2.setInt(1, this.getObjectId());
            var3 = var2.executeQuery();
            while (var3.next()) {
                int var4 = var3.getInt("id");
                Recipe var5 = RecipeHolder.getInstance().getRecipeById(var4);
                this.registerRecipe(var5, false);
            }
        }
        catch (Exception var9) {
            try {
                logger.warn("count not recipe skills:" + var9);
                logger.error("", (Throwable)var9);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var1, var2, var3);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var1, (Statement)var2, (ResultSet)var3);
        }
        DbUtils.closeQuietly((Connection)var1, (Statement)var2, (ResultSet)var3);
    }

    public int getMountType() {
        PetData var1 = PetDataHolder.getInstance().getInfo(this.getMountNpcId());
        if (var1 == null) {
            return 0;
        }
        return var1.isGreatWolf() ? 3 : (var1.isWyvern() ? 2 : (var1.isStrider() ? 1 : 0));
    }

    public double getColRadius() {
        NpcTemplate var4;
        int var3;
        if (this.getTransformation() != 0) {
            NpcTemplate var2;
            int var1 = this.getTransformationTemplate();
            if (var1 != 0 && (var2 = NpcHolder.getInstance().getTemplate(var1)) != null) {
                return var2.collisionRadius;
            }
        } else if (this.isMounted() && (var3 = this.getMountNpcId()) != 0 && (var4 = NpcHolder.getInstance().getTemplate(var3)) != null) {
            return var4.collisionRadius;
        }
        return this.getBaseTemplate().collisionRadius;
    }

    public double getColHeight() {
        NpcTemplate var4;
        int var3;
        if (this.getTransformation() != 0) {
            NpcTemplate var2;
            int var1 = this.getTransformationTemplate();
            if (var1 != 0 && (var2 = NpcHolder.getInstance().getTemplate(var1)) != null) {
                return var2.collisionHeight;
            }
        } else if (this.isMounted() && (var3 = this.getMountNpcId()) != 0 && (var4 = NpcHolder.getInstance().getTemplate(var3)) != null) {
            return var4.collisionHeight;
        }
        return this.getBaseTemplate().collisionHeight;
    }

    public void setReflection(Reflection var1) {
        if (this.getReflection() != var1) {
            super.setReflection(var1);
            if (this.var_3194 != null && !this.var_3194.isDead()) {
                this.var_3194.setReflection(var1);
            }
            if (var1 != ReflectionManager.DEFAULT) {
                String var2 = this.getVar("reflection");
                if (var2 == null || !var2.equals(String.valueOf(var1.getId()))) {
                    this.setVar("reflection", String.valueOf(var1.getId()), -1L);
                }
            } else {
                this.unsetVar("reflection");
            }
            if (this.getActiveClass() != null) {
                this.getInventory().validateItems();
                if (this.getPet() != null && (this.getPet().getNpcId() == 14916 || this.getPet().getNpcId() == 14917)) {
                    this.getPet().unSummon();
                }
            }
        }
    }

    public int getBuyListId() {
        return this.var_3314;
    }

    public void setBuyListId(int var1) {
        this.var_3314 = var1;
    }

    public int getVitalityLevel() {
        return Config.ALT_VITALITY_ENABLED ? this.var_3142 : 0;
    }

    public double getVitality() {
        return Config.ALT_VITALITY_ENABLED ? this.var_3143 : 0.0;
    }

    public void setVitality(double var1) {
        if (Config.ALT_VITALITY_ENABLED) {
            if ((var1 = Math.max(Math.min(var1, (double)Config.ALT_VITALITY_LEVELS[4]), 0.0)) >= this.var_3143 || this.getLevel() >= 10) {
                if (var1 != this.var_3143) {
                    if (var1 == 0.0) {
                        this.sendPacket((IStaticPacket)SystemMsg.YOUR_VITALITY_IS_FULLY_EXHAUSTED);
                    } else if (var1 == (double)Config.ALT_VITALITY_LEVELS[4]) {
                        this.sendPacket((IStaticPacket)SystemMsg.YOUR_VITALITY_IS_AT_MAXIMUM);
                    }
                }
                this.var_3143 = var1;
            }
            int var3 = 0;
            if (this.var_3143 >= (double)Config.ALT_VITALITY_LEVELS[3]) {
                var3 = 4;
            } else if (this.var_3143 >= (double)Config.ALT_VITALITY_LEVELS[2]) {
                var3 = 3;
            } else if (this.var_3143 >= (double)Config.ALT_VITALITY_LEVELS[1]) {
                var3 = 2;
            } else if (this.var_3143 >= (double)Config.ALT_VITALITY_LEVELS[0]) {
                var3 = 1;
            }
            if (this.var_3142 != var3) {
                if (this.var_3142 != -1) {
                    this.sendPacket((IStaticPacket)(var3 < this.var_3142 ? SystemMsg.YOUR_VITALITY_HAS_DECREASED : SystemMsg.YOUR_VITALITY_HAS_INCREASED));
                }
                this.var_3142 = var3;
            }
            this.sendPacket((IStaticPacket)new ExVitalityPointInfo((int)this.var_3143));
        }
    }

    public void addVitality(double var1) {
        this.setVitality(this.getVitality() + var1);
    }

    public int getVitalityItemsUsed() {
        return this.getVarInt(VITALITY_ITEMS_USED, 0);
    }

    public void setVitalityItemsUsed(int var1) {
        this.setVar(VITALITY_ITEMS_USED, var1, -1L);
    }

    public int getIncorrectValidateCount() {
        return this.var_3315;
    }

    public int setIncorrectValidateCount(int var1) {
        this.var_3315 = var1;
        return this.var_3315;
    }

    public int getExpandInventory() {
        return this.var_3253;
    }

    public void setExpandInventory(int var1) {
        this.var_3253 = var1;
    }

    public int getExpandWarehouse() {
        return this.var_3254;
    }

    public void setExpandWarehouse(int var1) {
        this.var_3254 = var1;
    }

    public void enterMovieMode() {
        if (!this.isInMovie() && Config.SHOW_BOSS_SCENES) {
            this.setTarget(null);
            this.stopMove();
            this.setIsInMovie(true);
            this.sendPacket((IStaticPacket)new CameraMode(1));
        }
    }

    public void leaveMovieMode() {
        this.setIsInMovie(false);
        this.sendPacket((IStaticPacket)new CameraMode(0));
        this.broadcastCharInfo();
    }

    public void specialCamera(GameObject var1, int var2, int var3, int var4, int var5, int var6) {
        if (Config.SHOW_BOSS_SCENES) {
            this.sendPacket((IStaticPacket)new SpecialCamera(var1.getObjectId(), var2, var3, var4, var5, var6));
        }
    }

    public void specialCamera(GameObject var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
        if (Config.SHOW_BOSS_SCENES) {
            this.sendPacket((IStaticPacket)new SpecialCamera(var1.getObjectId(), var2, var3, var4, var5, var6, var7, var8, var9, var10));
        }
    }

    public int getMovieId() {
        return this.var_3316;
    }

    public void setMovieId(int var1) {
        this.var_3316 = var1;
    }

    public boolean isInMovie() {
        return this.var_3317;
    }

    public void setIsInMovie(boolean var1) {
        this.var_3317 = var1;
    }

    public void showQuestMovie(SceneMovie var1) {
        if (!this.isInMovie()) {
            this.sendActionFailed();
            this.setTarget(null);
            this.stopMove();
            this.setMovieId(var1.getId());
            this.setIsInMovie(true);
            this.sendPacket((IStaticPacket)var1.packet(this));
        }
    }

    public void showQuestMovie(int var1) {
        if (!this.isInMovie()) {
            this.sendActionFailed();
            this.setTarget(null);
            this.stopMove();
            this.setMovieId(var1);
            this.setIsInMovie(true);
            this.sendPacket((IStaticPacket)new ExStartScenePlayer(var1));
        }
    }

    public void setAutoLoot(boolean var1) {
        if (Config.AUTO_LOOT_INDIVIDUAL) {
            this.var_3149 = var1;
            this.setVar("AutoLoot", String.valueOf(var1), -1L);
        }
    }

    public void setAutoLootHerbs(boolean var1) {
        if (Config.AUTO_LOOT_INDIVIDUAL) {
            this.var_3150 = var1;
            this.setVar("AutoLootHerbs", String.valueOf(var1), -1L);
        }
    }

    public void setAutoLootAdena(boolean var1) {
        if (Config.AUTO_LOOT_INDIVIDUAL) {
            this.var_3151 = var1;
            this.setVar("AutoLootAdena", String.valueOf(var1), -1L);
        }
    }

    public boolean isAutoLootEnabled() {
        return this.var_3149;
    }

    public boolean isAutoLootHerbsEnabled() {
        return this.var_3150;
    }

    public boolean isAutoLootAdenaEnabled() {
        return this.var_3151;
    }

    public final void reName(String var1, boolean var2) {
        this.setName(var1);
        if (var2) {
            this.saveNameToDB();
        }
        if (this.isNoble()) {
            NoblesController.getInstance().renameNoble(this.getObjectId(), var1);
        }
        if (this.isHero()) {
            HeroController.getInstance().renameHero(this.getObjectId(), var1);
        }
        this.broadcastUserInfo(false, new UserInfoType[0]);
        this.broadcastCharInfo();
    }

    public boolean getOpenCloak() {
        if (Config.ALT_OPEN_CLOAK_SLOT) {
            return true;
        }
        return (int)this.calcStat(Stats.CLOAK_SLOT, 0.0, null, null) > 0;
    }

    public boolean getAutoLootStat() {
        return (int)this.calcStat(Stats.AUTO_LOOT, 0.0, null, null) > 0;
    }

    public boolean getAutoLootHerbStat() {
        return (int)this.calcStat(Stats.AUTO_LOOT_HERB, 0.0, null, null) > 0;
    }

    public boolean getAutoLootAdenaStat() {
        return (int)this.calcStat(Stats.AUTO_LOOT_ADENA, 0.0, null, null) > 0;
    }

    public int getTalismanCount() {
        return (int)this.calcStat(Stats.TALISMANS_LIMIT, 0.0, null, null);
    }

    public int getBroochCount() {
        return (int)this.calcStat(Stats.BROOCH_LIMIT, 0.0, null, null);
    }

    public int getAgathionCharmCount() {
        return (int)this.calcStat(Stats.AGATHION_CHARM_LIMIT, 0.0, null, null);
    }

    public int getWorldChatBonus() {
        return (int)this.calcStat(Stats.WORLD_CHAT_BONUSES, 0.0, null, null);
    }

    public int getVipBonusSilverDrop() {
        return (int)this.calcStat(Stats.VIP_SILVER_DROP_MULTIPLIER, 0.0, null, null);
    }

    public int getVipBonusGoldDrop() {
        return (int)this.calcStat(Stats.VIP_GOLD_DROP_MULTIPLIER, 0.0, null, null);
    }

    public final void reName(String var1) {
        this.reName(var1, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void saveNameToDB() {
        Connection var1 = null;
        PreparedStatement var2 = null;
        try {
            var1 = DatabaseFactory.getInstance().getConnection();
            var2 = var1.prepareStatement("UPDATE characters SET `char_name` = ? WHERE `obj_Id` = ?");
            var2.setString(1, this.getName());
            var2.setInt(2, this.getObjectId());
            var2.executeUpdate();
        }
        catch (Exception var7) {
            try {
                logger.error("", (Throwable)var7);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection)var1, var2);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection)var1, (Statement)var2);
        }
        DbUtils.closeQuietly((Connection)var1, (Statement)var2);
    }

    public Player getPlayer() {
        return this;
    }

    public final void disableDrop(int var1) {
        this.var_3266 = System.currentTimeMillis() + (long)var1;
    }

    public final boolean isDropDisabled() {
        return this.var_3266 > System.currentTimeMillis();
    }

    public ItemInstance getPetControlItem() {
        return this.var_3318;
    }

    public void setPetControlItem(int var1) {
        this.setPetControlItem(this.getInventory().getItemByObjectId(var1));
    }

    public void setPetControlItem(ItemInstance var1) {
        this.var_3318 = var1;
    }

    public boolean isActive() {
        return this.var_3319.get();
    }

    public void setActive() {
        this.setNonAggroTime(0L);
        if (!this.var_3319.getAndSet(true)) {
            this.func183();
        }
    }

    private void func183() {
        this.setNonAggroTime(System.currentTimeMillis() + Config.NONAGGRO_TIME_ONLOGIN);
        if (this.getPetControlItem() != null) {
            ThreadPoolManager.getInstance().execute((Runnable)new RunnableImpl(){

                public void runImpl() {
                    if (Player.this.getPetControlItem() != null) {
                        Player.this.summonPet();
                    }
                }
            });
        }
    }

    public void summonPet() {
        PetInstance var4;
        NpcTemplate var3;
        PetData var2;
        ItemInstance var1;
        if (this.getPet() == null && (var1 = this.getPetControlItem()) != null && (var2 = PetDataHolder.getInstance().getByControlItemId(this.getPetControlItem())) != null && (var3 = NpcHolder.getInstance().getTemplate(var2.getID())) != null && (var4 = PetDAO.getInstance().restore(var1, var3, this)) != null) {
            this.setPet((Summon)var4);
            var4.setTitle(this.getName());
            if (!var4.isRespawned()) {
                var4.setCurrentHp((double)var4.getMaxHp(), false);
                var4.setCurrentMp((double)var4.getMaxMp());
                var4.setCurrentFed(var4.getMaxFed());
                var4.updateControlItem();
                var4.store();
            }
            var4.getInventory().restore();
            var4.setNonAggroTime(System.currentTimeMillis() + Config.NONAGGRO_TIME_ONTELEPORT);
            var4.setReflection(this.getReflection());
            var4.spawnMe(Location.findPointToStay((GameObject)this, (int)50, (int)70));
            var4.setRunning();
            var4.setFollowMode(true);
            var4.getInventory().validateItems();
            if (var4 instanceof PetBabyInstance) {
                ((PetBabyInstance)var4).startBuffTask();
            }
            PetEffectDAO.getInstance().select(var4);
        }
    }

    public Collection<TrapInstance> getTraps() {
        if (this.var_3320 == null) {
            return null;
        }
        ArrayList<TrapInstance> var1 = new ArrayList<TrapInstance>(this.getTrapsCount());
        for (Integer var4 : this.var_3320.keySet()) {
            TrapInstance var2 = (TrapInstance)GameObjectsStorage.get((Long)this.var_3320.get(var4));
            if (var2 != null) {
                var1.add(var2);
                continue;
            }
            this.var_3320.remove(var4);
        }
        return var1;
    }

    public int getTrapsCount() {
        return this.var_3320 == null ? 0 : this.var_3320.size();
    }

    public void addTrap(TrapInstance var1) {
        if (this.var_3320 == null) {
            this.var_3320 = new HashMap<Integer, Long>();
        }
        this.var_3320.put(var1.getObjectId(), var1.getStoredId());
    }

    public void removeTrap(TrapInstance var1) {
        Map<Integer, Long> var2 = this.var_3320;
        if (var2 != null && !var2.isEmpty()) {
            var2.remove(var1.getObjectId());
        }
    }

    public void destroyFirstTrap() {
        Integer var4;
        TrapInstance var2;
        Iterator<Integer> var3;
        Map<Integer, Long> var1 = this.var_3320;
        if (var1 != null && !var1.isEmpty() && (var3 = var1.keySet().iterator()).hasNext() && (var2 = (TrapInstance)GameObjectsStorage.get((Long)var1.get(var4 = var3.next()))) != null) {
            var2.deleteMe();
        }
    }

    public void destroyAllTraps() {
        Map<Integer, Long> var1 = this.var_3320;
        if (var1 != null && !var1.isEmpty()) {
            ArrayList<TrapInstance> var2 = new ArrayList<TrapInstance>();
            for (Integer var4 : var1.keySet()) {
                var2.add((TrapInstance)GameObjectsStorage.get((Long)var1.get(var4)));
            }
            for (TrapInstance var6 : var2) {
                if (var6 == null) continue;
                var6.deleteMe();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public PlayerListenerList getListeners() {
        if (this.listeners == null) {
            Player player = this;
            synchronized (player) {
                if (this.listeners == null) {
                    this.listeners = new PlayerListenerList(this);
                }
            }
        }
        return (PlayerListenerList)this.listeners;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public PlayerStatsChangeRecorder getStatsRecorder() {
        if (this._statsRecorder == null) {
            Player player = this;
            synchronized (player) {
                if (this._statsRecorder == null) {
                    this._statsRecorder = new PlayerStatsChangeRecorder(this);
                }
            }
        }
        return (PlayerStatsChangeRecorder)this._statsRecorder;
    }

    public int getHoursInGame() {
        ++this.var_3322;
        return this.var_3322;
    }

    public void startHourlyTask() {
        this.var_3321 = ThreadPoolManager.getInstance().scheduleAtFixedRate((Runnable)new GameObjectTasks.HourlyTask(this), 3600000L, 3600000L);
    }

    public void stopHourlyTask() {
        if (this.var_3321 != null) {
            this.var_3321.cancel(false);
            this.var_3321 = null;
        }
    }

    public long getPremiumPoints() {
        return Config.PRIME_SHOP_VIP_POINT_ITEM_ID > 0 ? ItemFunctions.getItemCount((Playable)this, (int)Config.PRIME_SHOP_VIP_POINT_ITEM_ID) : 0L;
    }

    public void reducePremiumPoints(int var1) {
        if (Config.PRIME_SHOP_VIP_POINT_ITEM_ID > 0) {
            ItemFunctions.removeItem((Playable)this, (int)Config.PRIME_SHOP_VIP_POINT_ITEM_ID, (long)var1, (boolean)true);
        }
    }

    public String getSessionVar(String var1) {
        return this.var_3323 == null ? null : this.var_3323.get(var1);
    }

    public void setSessionVar(String var1, String var2) {
        if (this.var_3323 == null) {
            this.var_3323 = new ConcurrentHashMap<String, String>();
        }
        if (var2 != null && !var2.isEmpty()) {
            this.var_3323.put(var1, var2);
        } else {
            this.var_3323.remove(var1);
        }
    }

    public CostumeCollectionManager getCostumeCollectionManager() {
        if (this.var_3217 == null) {
            this.var_3217 = new CostumeCollectionManager(this);
        }
        return this.var_3217;
    }

    public void setCostumeCollectionManager(CostumeCollectionManager var1) {
        this.var_3217 = var1;
    }

    public CostumeList getCostumeList() {
        return this.var_3216;
    }

    public FriendList getFriendList() {
        return this.var_3215;
    }

    public boolean isNotShowTraders() {
        return this.var_3264;
    }

    public void setNotShowTraders(boolean var1) {
        this.var_3264 = var1;
    }

    public boolean isDebug() {
        return this.var_3265;
    }

    public void setDebug(boolean var1) {
        this.var_3265 = var1;
    }

    public void sendSkillList() {
        this.sendSkillList(0);
    }

    public void sendSkillList(int var1) {
        this.sendPacket(new IStaticPacket[]{new SkillList(this, var1), new AcquireSkillList(this)});
    }

    public void sendItemList(boolean var1) {
        ItemInstance[] var2 = this.getInventory().getItems();
        LockType var3 = this.getInventory().getLockType();
        int[] var4 = this.getInventory().getLockItems();
        LinkedList<ItemInfo> var5 = new LinkedList<ItemInfo>();
        LinkedList<ItemInfo> var6 = new LinkedList<ItemInfo>();
        int var7 = 0;
        for (ItemInstance var11 : var2) {
            if (var11.getTemplate().isQuest()) {
                ItemInfo var12 = new ItemInfo(var11);
                var12.setEquipSlot(var7++);
                var6.add(var12);
                continue;
            }
            var5.add(new ItemInfo(var11));
        }
        this.sendPacket((IStaticPacket)new ItemList(true, var5, var1, var3, var4));
        this.sendPacket((IStaticPacket)new ItemList(false, var5, var1, var3, var4));
        this.sendPacket((IStaticPacket)new ExQuestItemList(true, var6, var3, var4));
        this.sendPacket((IStaticPacket)new ExQuestItemList(false, var6, var3, var4));
    }

    public boolean isPlayer() {
        return true;
    }

    public void startAttackStanceTask() {
        this.startAttackStanceTask0();
        Summon var1 = this.getPet();
        if (var1 != null) {
            var1.startAttackStanceTask0();
        }
    }

    public void displayGiveDamageMessage(Creature var1, int var2, boolean var3, boolean var4, boolean var5, boolean var6) {
        var2 = var1.modifyDisplayedDamage((Creature)((Object)this), var2);
        super.displayGiveDamageMessage(var1, var2, var3, var4, var5, var6);
        if (var3) {
            if (var6) {
                this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.MAGIC_CRITICAL_HIT));
                this.sendPacket((IStaticPacket)new ExMagicAttackInfo(this.getObjectId(), var1.getObjectId(), 1));
            } else {
                this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.C1_LANDED_A_CRITICAL_HIT).addName((GameObject)this));
            }
        }
        if (var4) {
            this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.C1S_ATTACK_WENT_ASTRAY).addName((GameObject)this));
        } else if (!var1.isDamageBlocked()) {
            this.sendPacket((IStaticPacket)((SystemMessage)((SystemMessage)((SystemMessage)new SystemMessage(SystemMsg.C1_HAS_DONE_S3_POINTS_OF_DAMAGE_TO_C2_S4).addName((GameObject)this)).addName((GameObject)var1)).addNumber(var2)).addVisibleDamage((GameObject)this, (GameObject)var1, -var2));
        }
        if (var1.isPlayer()) {
            if (var5 && var2 > 1) {
                var1.sendPacket((IStaticPacket)SystemMsg.YOUR_SHIELD_DEFENSE_HAS_SUCCEEDED);
            } else if (var5 && var2 == 1) {
                var1.sendPacket((IStaticPacket)SystemMsg.YOUR_EXCELLENT_SHIELD_DEFENSE_WAS_A_SUCCESS);
            }
        }
    }

    public void displayReceiveDamageMessage(Creature var1, int var2) {
        if (var1 != this) {
            this.sendPacket((IStaticPacket)((SystemMessage)((SystemMessage)new SystemMessage(SystemMsg.C1_HAS_RECEIVED_S3_DAMAGE_FROM_C2).addName((GameObject)this)).addName((GameObject)var1)).addNumber((long)var2));
        }
    }

    public IntObjectMap<String> getPostFriends() {
        return this.var_3262;
    }

    public boolean isSharedGroupDisabled(int var1) {
        TimeStamp var2 = (TimeStamp)this.var_3268.get(var1);
        if (var2 == null) {
            return false;
        }
        if (var2.hasNotPassed()) {
            return true;
        }
        this.var_3268.remove(var1);
        return false;
    }

    public TimeStamp getSharedGroupReuse(int var1) {
        return (TimeStamp)this.var_3268.get(var1);
    }

    public void addSharedGroupReuse(int var1, TimeStamp var2) {
        this.var_3268.put(var1, var2);
    }

    public Collection<IntObjectMap.Entry<TimeStamp>> getSharedGroupReuses() {
        return this.var_3268.entrySet();
    }

    public void sendReuseMessage(ItemInstance var1) {
        TimeStamp var2 = this.getSharedGroupReuse(var1.getTemplate().getReuseGroup());
        if (var2 != null && var2.hasNotPassed()) {
            long var3 = var2.getReuseCurrent();
            long var5 = TimeUnit.MILLISECONDS.toHours(var3);
            long var7 = TimeUnit.MILLISECONDS.toMinutes(var3 - TimeUnit.HOURS.toMillis(var5));
            long var9 = TimeUnit.MILLISECONDS.toSeconds(var3 - TimeUnit.MINUTES.toMillis(var7) - TimeUnit.HOURS.toMillis(var5));
            if (var5 > 0L) {
                this.sendPacket((IStaticPacket)((SystemMessage)((SystemMessage)((SystemMessage)new SystemMessage(var1.getTemplate().getReuseType().getMessages()[2]).addItemName(var1.getTemplate().getItemId())).addNumber(var5)).addNumber(var7)).addNumber(var9));
            } else if (var7 > 0L) {
                this.sendPacket((IStaticPacket)((SystemMessage)((SystemMessage)new SystemMessage(var1.getTemplate().getReuseType().getMessages()[1]).addItemName(var1.getTemplate().getItemId())).addNumber(var7)).addNumber(var9));
            } else {
                this.sendPacket((IStaticPacket)((SystemMessage)new SystemMessage(var1.getTemplate().getReuseType().getMessages()[0]).addItemName(var1.getTemplate().getItemId())).addNumber(Math.max(1L, var9)));
            }
        }
    }

    public void ask(ConfirmDlg var1, OnAnswerListener var2) {
        if (this.var_3269 == null) {
            int var3 = Rnd.nextInt();
            this.var_3269 = new ImmutablePair<>(var3, var2);
            var1.setRequestId(var3);
            this.sendPacket((IStaticPacket)var1);
        }
    }

    public Pair<Integer, OnAnswerListener> getAskListener(boolean var1) {
        if (!var1) {
            return this.var_3269;
        }
        Pair<Integer, OnAnswerListener> var2 = this.var_3269;
        this.var_3269 = null;
        return var2;
    }

    public boolean isDead() {
        if (!this.isOlyParticipant()) {
            return this.isInDuel() ? this.getCurrentHp() <= 1.0 : super.isDead();
        }
        return this.isOlyCompetitionStarted() && this.isLooseOlyCompetition();
    }

    public boolean hasPrivilege(Privilege var1) {
        return this.var_3182 != null && (this.getClanPrivileges() & var1.mask()) == var1.mask();
    }

    public MatchingRoom getMatchingRoom() {
        return this.var_3271;
    }

    public void setMatchingRoom(MatchingRoom var1) {
        this.var_3271 = var1;
        if (var1 == null) {
            this.var_3270 = false;
        }
    }

    public boolean isMatchingRoomWindowOpened() {
        return this.var_3270;
    }

    public void setMatchingRoomWindowOpened(boolean var1) {
        this.var_3270 = var1;
    }

    public void dispelBuffs() {
        for (Effect var2 : this.getEffectList().getAllEffects()) {
            if (var2.getSkill().isOffensive() || var2.getSkill().isNewbie() || !var2.isCancelable() || var2.getSkill().isPreservedOnDeath()) continue;
            this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THE_EFFECT_OF_S1_HAS_BEEN_REMOVED).addSkillName(var2.getSkill().getId(), var2.getSkill().getLevel()));
            var2.exit();
        }
        if (this.getPet() != null) {
            for (Effect var4 : this.getPet().getEffectList().getAllEffects()) {
                if (var4.getSkill().isOffensive() || var4.getSkill().isNewbie() || !var4.isCancelable() || var4.getSkill().isPreservedOnDeath()) continue;
                var4.exit();
            }
        }
    }

    public void setInstanceReuse(int var1, long var2) {
        this.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.INSTANT_ZONE_S1_ENTRY_HAS_BEEN_RESTRICTED).addName((GameObject)this));
        this.var_3272.put(var1, var2);
        InstanceReuseDAO.getInstance().setReuse(this, var1, var2);
    }

    public void removeInstanceReuse(int var1) {
        if (this.var_3272.remove(var1) != null) {
            InstanceReuseDAO.getInstance().removeReuse(this, var1);
        }
    }

    public void removeAllInstanceReuses() {
        this.var_3272.clear();
        InstanceReuseDAO.getInstance().removeAllReuse(this);
    }

    public void removeInstanceReusesByGroupId(int var1) {
        Iterator<Integer> iterator = InstantZoneHolder.getInstance().getSharedReuseInstanceIdsByGroup(var1).iterator();
        while (iterator.hasNext()) {
            int var3 = (Integer)iterator.next();
            if (this.getInstanceReuse(var3) == null) continue;
            this.removeInstanceReuse(var3);
        }
    }

    public Long getInstanceReuse(int var1) {
        return this.var_3272.get(var1);
    }

    public Map<Integer, Long> getInstanceReuses() {
        return this.var_3272;
    }

    public Reflection getActiveReflection() {
        for (Reflection var4 : ReflectionManager.getInstance().getAll()) {
            if (var4 == null || !ArrayUtils.contains((int[])var4.getVisitors(), (int)this.getObjectId())) continue;
            return var4;
        }
        return null;
    }

    public boolean canEnterInstance(int var1) {
        InstantZone var2 = InstantZoneHolder.getInstance().getInstantZone(var1);
        if (this.isDead()) {
            return false;
        }
        if (ReflectionManager.getInstance().size() > Config.MAX_REFLECTIONS_COUNT) {
            this.sendMessage(new CustomMessage("THE_MAXIMUM_NUMBER_OF_INSTANCE_ZONES_HAS_BEEN_EXCEEDED", this, new Object[0]));
            return false;
        }
        if (var2 == null) {
            this.sendPacket((IStaticPacket)SystemMsg.SYSTEM_ERROR);
            return false;
        }
        if (ReflectionManager.getInstance().getCountByIzId(var1) >= var2.getMaxChannels()) {
            this.sendMessage(new CustomMessage("THE_MAXIMUM_NUMBER_OF_INSTANCE_ZONES_HAS_BEEN_EXCEEDED", this, new Object[0]));
            return false;
        }
        return var2.getEntryType().canEnter(this, var2);
    }

    public boolean canReenterInstance(int var1) {
        InstantZone var2 = InstantZoneHolder.getInstance().getInstantZone(var1);
        if (this.getActiveReflection() != null && this.getActiveReflection().getInstancedZoneId() != var1) {
            this.sendMessage(new CustomMessage("YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON", this, new Object[0]));
            return false;
        }
        if (var2.isDispelBuffs()) {
            this.dispelBuffs();
        }
        return var2.getEntryType().canReEnter(this, var2);
    }

    public int getBattlefieldChatId() {
        return this.var_3260;
    }

    public void setBattlefieldChatId(int var1) {
        this.var_3260 = var1;
    }

    public void broadCast(IStaticPacket ... var1) {
        this.sendPacket(var1);
    }

    public Iterator<Player> iterator() {
        return Collections.singleton(this).iterator();
    }

    public PlayerGroup getPlayerGroup() {
        if (this.getParty() != null) {
            return this.getParty().getCommandChannel() != null ? this.getParty().getCommandChannel() : this.getParty();
        }
        return this;
    }

    public boolean isActionBlocked(String var1) {
        return this.var_3263.contains(var1);
    }

    public void blockActions(String ... var1) {
        Collections.addAll(this.var_3263, var1);
    }

    public void unblockActions(String ... var1) {
        for (String var5 : var1) {
            this.var_3263.remove(var5);
        }
    }

    public void addRadar(int var1, int var2, int var3) {
        this.sendPacket((IStaticPacket)new RadarControl(0, 1, var1, var2, var3));
    }

    public void addRadarWithMap(int var1, int var2, int var3) {
        this.sendPacket((IStaticPacket)new RadarControl(0, 2, var1, var2, var3));
    }

    public long getAfterTeleportPortectionTime() {
        return this.var_3324;
    }

    public void setAfterTeleportPortectionTime(long var1) {
        this.var_3324 = var1;
    }

    public void triggerAfterTeleportProtection() {
        if (Config.ALT_TELEPORT_PROTECTION && this.getAfterTeleportPortectionTime() > System.currentTimeMillis()) {
            this.setAfterTeleportPortectionTime(0L);
            this.sendMessage(new CustomMessage("alt.teleport_protect_gonna", this, new Object[0]));
        }
    }

    public long getNoCarrierProtectionTime() {
        return this.var_3325;
    }

    public void setNoCarrierProtectionTime(long var1) {
        this.var_3325 = var1;
    }

    public void triggerNoCarrierProtection() {
        if (Config.SERVICES_ENABLE_NO_CARRIER && Config.NO_CARRIER_PROTECTION && this.getNoCarrierProtectionTime() > System.currentTimeMillis()) {
            this.setNoCarrierProtectionTime(0L);
            this.sendMessage(new CustomMessage("alt.no_carrier_protect_gonna", this, new Object[0]));
        }
    }

    public boolean isUserRelationActive() {
        return this.var_3256 == null;
    }

    public void startEnableUserRelationTask(long var1, SiegeEvent<?, ?> var3) {
        if (this.var_3256 == null) {
            this.var_3256 = ThreadPoolManager.getInstance().schedule((Runnable)new EnableUserRelationTask(this, var3), var1);
        }
    }

    public void stopEnableUserRelationTask() {
        if (this.var_3256 != null) {
            this.var_3256.cancel(false);
            this.var_3256 = null;
        }
    }

    public int getTpBookmarkSize() {
        return this.var_3257;
    }

    public void setTpBookmarkSize(int var1) {
        this.var_3257 = var1;
    }

    public List<TpBookMark> getTpBookMarks() {
        return this.var_3275;
    }

    public OneDayRewardStore getOneDayRewardStore() {
        return this.var_3274;
    }

    public boolean isTradeBannedByGM() {
        long var2;
        String var1 = this.getVar("tradeBan");
        long var4 = System.currentTimeMillis();
        if (!(StringUtils.isBlank((CharSequence)var1) || (var2 = Long.parseLong(var1)) != -1L && var2 < var4)) {
            if (var2 == -1L) {
                this.sendMessage(new CustomMessage("common.TradeBannedPermanently", this, new Object[0]));
            } else {
                this.sendMessage(new CustomMessage("common.TradeBanned", this, new Object[0]).addString(Util.formatTime((int)((int)TimeUnit.MILLISECONDS.toSeconds(var2 - var4)))));
            }
            return true;
        }
        return false;
    }

    public AutoFarmContext getFarmSystem() {
        return this.var_3273;
    }

    public boolean isSellingBuffs() {
        return this.var_3276 && this.var_3277 != null && !this.var_3277.isEmpty();
    }

    public void setSellingBuffs(boolean var1) {
        this.var_3276 = var1;
    }

    public Map<Skill, Long> getBuffs4Sale() {
        return this.var_3277 == null ? (this.var_3277 = new LinkedHashMap<Skill, Long>()) : this.var_3277;
    }

    public Player setBuffs4Sale(Map<Skill, Long> var1) {
        this.var_3277 = var1;
        return this;
    }

    public byte getVipLevel() {
        return this.var_3278;
    }

    public void setVipLevel(byte var1) {
        this.var_3278 = var1;
    }

    public long getVipPoints() {
        return this.getVarLong(VIP_POINTS, 0L);
    }

    public long getVipTierExpiration() {
        return this.getVarLong(VIP_EXPIRATION, 0L);
    }

    public void setVipTierExpiration(long var1) {
        this.setVar(VIP_EXPIRATION, var1, -1L);
    }

    public boolean canReceiveGift(Player var1) {
        if (!Config.PRIME_SHOP_VIP_SYSTEM_ENABLED) {
            return false;
        }
        if (var1.getVipLevel() <= 0) {
            return false;
        }
        return var1.getVarLong(VIP_ITEM_BOUGHT) <= 0L;
    }

    public void updateVipPoints(long var1) {
        if (var1 != 0L) {
            VipManager var3 = VipManager.getInstance();
            byte var4 = var3.getVipLevel(this.getVipPoints());
            this.setVar(VIP_POINTS, this.getVipPoints() + var1, -1L);
            byte var5 = var3.getVipLevel(this.getVipPoints());
            if (var5 != var4) {
                this.var_3278 = var5;
                if (var5 > 0) {
                    this.setVar(VIP_EXPIRATION, Instant.now().plus(30L, ChronoUnit.DAYS).toEpochMilli(), -1L);
                    var3.manageVipLevelSkill(this);
                } else {
                    this.setVar(VIP_EXPIRATION, 0, -1L);
                }
            }
            this.sendPacket((IStaticPacket)new ReceiveVipInfo(this, var3));
        }
    }

    public boolean isSelfRestricted() {
        return this.var_3279;
    }

    public void setSelfRestricted(boolean var1) {
        if (this.var_3279 != var1) {
            this.var_3279 = var1;
            if (var1) {
                this.setVar("@UserIsLocked", "1", -1L);
            } else {
                this.unsetVar("@UserIsLocked");
            }
        }
    }

    public boolean isSelfRestricted(boolean var1) {
        if (this.var_3279 && var1) {
            this.sendMessage(new CustomMessage("common.PlayerLocked", this, new Object[0]));
        }
        return this.var_3279;
    }

    public void loadSelfRestricted() {
        String var1 = this.getVar("@UserIsLocked");
        this.var_3279 = !StringUtils.isBlank((CharSequence)var1);
    }

    public double getMpConsumeCostValue(Skill.SkillMagicType var1) {
        return switch (var1) {
            case PHYSIC -> this.calcStat(Stats.MP_PHYSICAL_SKILL_CONSUME, 10000.0) / 10000.0 * 100.0 - 100.0;
            case MAGIC -> this.calcStat(Stats.MP_MAGIC_SKILL_CONSUME, 10000.0) / 10000.0 * 100.0 - 100.0;
            case SPECIAL -> 0.0;
            case MUSIC -> this.calcStat(Stats.MP_DANCE_SKILL_CONSUME, 10000.0) / 10000.0 * 100.0 - 100.0;
            default -> throw new IncompatibleClassChangeError();
        };
    }

    public boolean isInOfflineHunting() {
        return this.inOfflineHunting;
    }

    public void setInOfflineHunting(boolean inOfflineMode) {
        if (!inOfflineMode) {
            this.unsetVar("offlineFarm");
        }
        this.inOfflineHunting = inOfflineMode;
    }

    public void sendRedMessage(String message) {
        this.sendPacket((IStaticPacket)new CustomSystemMessage(CustomSystemMsg.RED_S1).addString(message));
    }

    public void sendOrangeMessage(String message) {
        this.sendPacket((IStaticPacket)new CustomSystemMessage(CustomSystemMsg.ORANGE_S1).addString(message));
    }

    public void sendGreenMessage(String message) {
        this.sendPacket((IStaticPacket)new CustomSystemMessage(CustomSystemMsg.GREEN_S1).addString(message));
    }

    public void sendBlueMessage(String message) {
        this.sendPacket((IStaticPacket)new CustomSystemMessage(CustomSystemMsg.BLUE_S1).addString(message));
    }

    public void offlineFarm() {
        if (this._netConnection != null) {
            if (GiranForgeConfig.ENABLE_OFFLINE_FARM_TITLE) {
                String title = GiranForgeConfig.OFFLINE_FARM_TITLE.isBlank() ? this.getTitle() : GiranForgeConfig.OFFLINE_FARM_TITLE;
                this.setDisconnectedTitle(title);
                this.setDisconnectedTitleColor(GiranForgeConfig.OFFLINE_FARM_TITLE_COLOR);
            }
            this._netConnection.setActiveChar(null);
            this._netConnection.close(ServerClose.STATIC);
            this.setNetConnection(null, false);
        }
        if (this.getFarmSystem() != null) {
            this.getFarmSystem().saveOfflineFarmSummonData();
        }
        this.setOfflineMode(true);
        this.setVar("offlineFarm", String.valueOf(System.currentTimeMillis() + 10800000L), -1L);
        this.setInOfflineHunting(true);
        this.broadcastCharInfo();
    }

    public void addSpecialRequest(AbstractRequest request) {
        this._specialRequests.put(request.getClass(), request);
    }

    public void removeSpecialRequest(Class<? extends AbstractRequest> clazz) {
        this._specialRequests.remove(clazz);
    }

    public void removeSpecialRequest(AbstractRequest request) {
        if (request != null) {
            this.removeSpecialRequest(request.getClass());
        }
    }

    public boolean canChangeSubclass() {
        return System.currentTimeMillis() > this.lastSubclassChange;
    }

    public <T extends AbstractRequest> T getSpecialRequest(Class<T> requestClass) {
        return (T)((AbstractRequest)requestClass.cast(this._specialRequests.get(requestClass)));
    }

    public Integer getAttributeStone() {
        return this.enchantAttribute;
    }

    public void setAttributeStone(Integer id) {
        this.enchantAttribute = id;
    }

    public void updateChangeClassDelay() {
        long currentTime = System.currentTimeMillis();
        long delay = TimeUnit.SECONDS.toMillis(GiranForgeConfig.CHANGE_INTERVAL);
        this.lastSubclassChange = currentTime + delay;
    }

    public void setAttendanceTask(ScheduledFuture<?> task) {
        this._attendanceTask = task;
    }

    public void cancelAttendanceTask() {
        if (this._attendanceTask != null) {
            this._attendanceTask.cancel(false);
            this._attendanceTask = null;
        }
    }

    public boolean isAuctionBlock() {
        return this.getVarB("player_auction_block", false);
    }

    public void setAuctionBlock(boolean disable) {
        if (disable) {
            this.setVar("player_auction_block", "true", -1L);
        } else {
            this.unsetVar("player_auction_block");
        }
    }

    private class UpdateEffectIcons
    extends RunnableImpl {
        private UpdateEffectIcons() {
        }

        public void runImpl() {
            Player.this.func162();
            Player.this.var_3283 = null;
        }
    }

    private static class MoveToLocationActionForOffload
    extends Creature.MoveToLocationAction {
        public MoveToLocationActionForOffload(Creature var1, Location var2, Location var3, boolean var4, int var5, boolean var6) {
            super(var1, var2, var3, var4, var5, var6);
        }

        private void func329() {
            MoveToLocationOffloadData var3;
            Player var1 = (Player)((Object)this.getActor());
            if (var1 != null && (var3 = var1.var_3284.get()) != null && var1.var_3284.compareAndSet(var3, null)) {
                var1.moveToLocation(var3.getDest(), var3.getIndent(), var3.isPathfind());
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        protected boolean onTick(double var1) {
            boolean var3;
            try {
                var3 = super.onTick(var1);
            }
            finally {
                this.func329();
            }
            return var3;
        }

        @Override
        protected void onFinish(boolean var1, boolean var2) {
            try {
                super.onFinish(var1, var2);
            }
            finally {
                this.func329();
            }
        }
    }

    private static class MoveToLocationOffloadData {
        private final Location var_3831;
        private final int var_3832;
        private final boolean var_3833;

        public MoveToLocationOffloadData(Location var1, int var2, boolean var3) {
            this.var_3831 = var1;
            this.var_3832 = var2;
            this.var_3833 = var3;
        }

        public Location getDest() {
            return this.var_3831;
        }

        public int getIndent() {
            return this.var_3832;
        }

        public boolean isPathfind() {
            return this.var_3833;
        }
    }

    public class BroadcastCharInfoTask
    extends RunnableImpl {
        public void runImpl() throws Exception {
            Player.this.func164();
            Player.this.var_3285 = null;
        }
    }

    private class UserInfoTask
    extends RunnableImpl {
        private UserInfoTask() {
        }

        public void runImpl() {
            Player.this.func165();
            Player.this.var_3287 = null;
        }
    }

    public static enum EPledgeRank {
        VAGABOND(0),
        VASSAL(1),
        HEIR(2),
        KNIGHT(3),
        WISEMAN(4),
        BARON(5),
        VISCOUNT(6),
        COUNT(7),
        MARQUIS(8);

        public static EPledgeRank[] VALUES;
        private final int var_4492;

        private EPledgeRank(int var3) {
            this.var_4492 = var3;
        }

        public static EPledgeRank getPledgeRank(int var0) {
            for (EPledgeRank var4 : VALUES) {
                if (var4.getRankId() != var0) continue;
                return var4;
            }
            return null;
        }

        public int getRankId() {
            return this.var_4492;
        }

        static {
            VALUES = EPledgeRank.values();
        }
    }

    private class ForceCleanupTask
    implements Runnable {
        private ForceCleanupTask() {
        }

        @Override
        public void run() {
            long var1 = 600000L - (System.currentTimeMillis() - Player.this.var_3303);
            if (var1 > 1000L) {
                Player.this.var_3304 = ThreadPoolManager.getInstance().schedule((Runnable)new ForceCleanupTask(), var1);
            } else {
                Player.this.var_3302 = 0;
                Player.this.sendEtcStatusUpdate();
                Player.this.var_3304 = null;
            }
        }
    }
}

