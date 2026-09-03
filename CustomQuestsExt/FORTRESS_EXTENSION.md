# Fortress Extension

The complete fortress system for the 21 fortresses (residence ids 101-121): residences,
the full siege event (registration at the Suspicious Merchant, commanders/barracks, power
units, combat flag capture), fortress NPCs and their AI, facilities, envoys/contracts and
Blood Oath logistics. The data side lives in the `l2horizon-server` datapack (branch
`claude/lineage2-fortress-xml-files-2gvuuw`): residence/event/zone XMLs, 519 NPC templates,
4800+ siege spawns, skills 3318/3358, and items: 9819 Combat Flag, 9910 Blood Oath,
10031 Control Room Card (moved from H5's 10014, which this client uses for a D-grade
enchant scroll), 9912 Knight's Epaulette + shirts 9579-9581 (guard drops), 10212
npc Only (Bow), the 64 H5 talismans this pack lacked (9914-9965 less 9923, 10518/10519,
10533-10543 - completing the 73-talisman Knight's Epaulette pool) with their 22 missing
equip skills, and 10166 Hot Springs CP Potion + skill 2403 (conjured by the Orange
Talisman) - H5 items this pack was missing.

The core `server.jar` of this build has no fortress implementation - `ResidenceType.Fortress`
exists, but there is no `Fortress` residence class and the fortress client packets are stubs.
This extension fills that gap. All recreated logic follows the L2Scripts High Five sources
(rev 2268, `Hl4p3x/L2Scripts_H5_2268` mirror), adapted where this core differs.

## New classes

| Class | Purpose |
|---|---|
| `l2.gameserver.model.entity.residence.Fortress` | The residence impl `ResidenceParser` instantiates for `impl="Fortress"` files. H5 logic: exclusive ownership (taking a fortress releases the clan's other fortress/castle), 7-day auto-expiry, reward cycle every 6h, facility/supply/contract state, related-castle mapping. |
| `l2.gameserver.dao.FortressDAO` | Persistence, H5 schema + `owner_clan_id`. Creates the `fortress` table on first use (see also `sql/install/fortress.sql` in the server repo). |
| `l2.gameserver.model.entity.events.impl.FortressSiegeEvent` | The fortress siege: barracks tracking, commander respawn, flag spawning, envoy scheduling, 1h/5h siege timing after registration, resurrect/attack rules. Loaded by the event files' `impl="FortressSiege"`. |
| `l2.gameserver.model.entity.events.objects.FortressCombatFlagObject` | The Combat Flag (item 9819) dropped at the flag pole once all barracks fall; pick-up/equip/drop handling via this core's FlagItemAttachment. |
| `npc.model.residences.fortress.*` (+`peace`/`siege`) | 16 NPC instance classes: steward (functions via the core ResidenceManager), doorman, Suspicious Merchant registration, peace captains, facility vendors (guard captain, logistics officer with Blood Oath rewards and supply boxes), envoy, main machine + power units mini-games, ballista, mercenary captain. |
| `ai.residences.fortress.siege.*`, `ai.suspiciousmerchant.*` | 8 commander/machine AI classes and the 21 walking Suspicious Merchant route AIs. |
| `com.l2horizon.CustomQuestsExt.handlers.admin.FortressAdminCommand` | `//fortress`, `//fortress <id>`, `//fortress_set_owner <id> <clanName|npc>`. Registered in `CustomQuestsExt.onLoad()`. Needs `PlayerAccess.CanEditNPC` (same as `//residence`). |
| `com.l2horizon.CustomQuestsExt.handlers.admin.MultisellAdminCommand` | `//multisell <listId>` opens a multisell for the GM without an npc (GMs are exempt from the merchant-range check when buying). Used by the GM Shop's Talismans page (`admin/gmshop/talismans.htm`: buy list 9284 and multisell 999955 with all 73 talismans). Needs `PlayerAccess.UseGMShop`, like `//gmshop`. |

## Core class overrides (classpath shadowing)

Recreated 1:1 from the decompiled originals (kept in `decompiled/*_decompiled.java`),
then extended with the stripped filling logic from the H5 sources:

| Class | Original behavior | Override adds |
|---|---|---|
| `s2c.ExShowFortressInfo` | always sent an empty list | default constructor fills from `ResidenceHolder` (owner name, held time, siege state) |
| `c2s.RequestAllFortressInfo` | read nothing, did nothing | responds with `ExShowFortressInfo` |
| `s2c.ExShowFortressMapInfo` | no filling constructor | constructor from `Fortress` (3/5 barracks, live siege state) |
| `c2s.RequestFortressMapInfo` | read fort id, did nothing | responds with `ExShowFortressMapInfo` |
| `data.xml.parser.EventParser` | ignored `combat_flag` event objects | parses them into `FortressCombatFlagObject` (H5 behavior); everything else recreated 1:1 |
| `skills.skillclasses.TakeCastle` | Seal of Ruler castle capture only | fortress branch (ported H5 TakeFortress) taken only when the target is a fortress flag pole; skill 3318 is declared with `skillType="TAKECASTLE"` because this core has no TAKEFORTRESS type |

The Scripts engine deliberately skips `l2/gameserver/**` entries when scanning ext jars,
so these classes are served purely by the JVM classpath. For the override to win
deterministically, `CustomQuestsExt.ext.jar` must precede `server.jar` on the classpath -
the start scripts in `l2horizon-server` prepend it explicitly
(`-cp config:./CustomQuestsExt.ext.jar:./*`). The `Fortress`/`FortressDAO` classes are new
(no core counterpart), so they resolve regardless of ordering.

## Adaptations vs the H5 reference (documented deviations)

- Ownership is stored in `fortress.owner_clan_id`. This core's `clan_data` has no
  `hasFortress` column, `Clan` has no fortress accessors, and `ClanDataDAO` has no
  fortress owner query, so the H5 split (owner in `clan_data`) is not reproducible
  without overriding `Clan` itself.
- `Fortress.changeOwner()` stamps `own_date`/`last_siege_date` and restarts the hourly
  cycle task; in H5 the `FortressSiegeEvent` handled that.
- `Fortress.init()` tolerates a missing `residence_<id>` zone or siege event, so a jar
  deployed without the fortress datapack (or vice versa) cannot break
  `ResidenceHolder.callInit()` for the residences after it.
- The castle-contract branch of `chanceCycle()` is kept verbatim but is unreachable:
  contract state can only change through envoy NPCs, which this chronicle's datapack
  does not contain.
- The H5 event data pays virtual currencies through `give_item` with negative ids
  (`ItemTemplate`: -100 PC points, -200 clan reputation, -300 fame, -500 raid points),
  but this core implements only clan reputation and its stock `giveItem()` NPEs on
  negative ids. `FortressSiegeEvent.giveItem()` pays out clan reputation and swallows
  the rest; the fortress XMLs keep the retail -300 fame ticks (31 fame every 5
  minutes of siege) in case a fame system is added later.
- Only the 5-barrack fortresses define the `siege_minister` object, so its spawn
  actions are guarded by presence - 3-barrack sieges no longer log
  "Undefined objects: siege_minister".
- The retail flag pole is a type-3 static object whose visual is a prop in the
  client map files - and this build's Classic maps carry no such prop, leaving
  the pole invisible and unclickable. The retail Flagpole npcs (35726-family,
  imported with the staff and typed `npc.model.residences.fortress.FlagPoleInstance`)
  stand at the pole spots as a visible, geodata-snapped, targetable pole; the
  TakeCastle override accepts them alongside the static object.
  `FortressUtils.getFortress()` does its own null-zone-tolerant nearest-fortress
  search because `ResidenceHolder.findNearestResidence` NPEs when residence zones
  are not yet bound (zones bind in `Residence.init()`, which can run after
  SpawnManager).
- The Classic geodata tile `21_25.l2g` had no Fortress Dungeon (Awl Under Foot,
  instance zone 22, entry 53321/246314/-6452): the whole dungeon footprint was a
  flat plane at -4672, so any teleport there snapped 1800 units up. The tile was
  merged from the L2J Mobius Vanguard `21_25.l2j` (the client map now in use):
  the 64 dungeon blocks (bx 156-164, by 126-133; byte-identical to High Five
  geodata, `Zyno/L2J_Geodata_Hi5`) plus 64 blocks of a second underground
  structure at the same depth (x 55808-56960, y 238080-239104) that only the
  Vanguard map has, with every other block of the tile left byte-identical
  (see `tools/geodata/` in the server repo to redo the merge from another source).

## Owner phase (staff, services, clan window)

The first conversion missed the per-fortress staff blocks entirely; they are now
imported from H5: 302 templates - Foreman (steward, the `Manager` residence
functions npc), 3 Doormen per fortress (gate control via their `doors`
ai_params + inner teleport), Logistics Officer, Detention Camp Warden,
Engineering Manager, Wyvern Manager, Fortress Trap, generals, supply boxes,
mercenary privates, the remaining machine/power units, and the retail Flagpole
npcs (typed to `FlagPoleInstance`; the custom 90850 stand-in was retired).
They spawn through `spawn/fortress_staff.xml` (always up, as in H5). The 18
Territory-War npcs (dominion managers/catapult) are excluded - no dominion
support on this build. The wardens' Rim Pailaka instance is not ported, so
they are typed plain `Npc`.

Support Unit Captain services: multisells 45300001/356482/356483/356484 are
installed filtered to items this pack carries; `services.ObtainTalisman` (10
Knight's Epaulettes for a random talisman) is ported with the pool filtered
against ItemHolder at server start (all 73 H5 talismans exist since the
datapack import; anything added later joins automatically); the retail
squad-skill option answers with an "unavailable" dialog because this core
has no SUB_UNIT skill acquire path.

Talisman skills: the Classic pack and the client already carried skill ids
3271-3294 with Essence-era content (3271 "Blue Talisman - P. Crit.", 3285 "Grey
Talisman - Gravity", ...) that no item referenced, while the H5 talisman
templates bind those ids to different skills - equipping a talisman applied
the wrong effect and showed the wrong name. The server now carries the H5
definitions for 3271-3294 (`stats/skills/3200-3299.xml`), and the client
SkillName/Skillgrp rows for those ids were rewritten to match (named after the
talisman that grants them); the client's own item descriptions already
described the H5 effects. The talisman Armorgrp rows also moved from
`body_part=underwear` (an Essence leftover) to `deco1`, the talisman slot the
server equips them into. Skills whose meaning already matched (3295-3299,
3410, 3428-3438, 3487-3497) keep the pack's own tuning.

`PledgeShowInfoUpdate` and `PledgeShowMemberListAll` are recreated with the
fortress slot (after the hideout field) filled from fortress ownership, so the
clan window shows the fortress as the clan's base.

Note: the envoy refuses a castle contract while the related castle is
unowned - that is retail behavior, not a bug; independence always works.

Combat Flag handling on this core: `pickUp` broadcasts the char info after the
programmatic equip (this core's `Inventory.equipItem` is silent, so without it
the client showed empty hands until a manual re-equip), and
`FortressDAO.deleteStrayCombatFlags()` wipes item 9819 from persisted
inventories at every boot - sieges never survive a restart, so a flag that
rode a restart inside someone's bag would otherwise become an ordinary
tradeable weapon. `FortressWorldInfoListener` pushes `ExShowFortressInfo` at
enter-world so the world map colors fortresses like castles.

## Awl Under Foot (the fortress prison)

The Detention Camp Warden's "I want to enter the prison" option is quest 511,
ported from H5 as `quests._511_AwlUnderFoot` (registered from the ext jar like
any scripts quest): a party of the owning clan - fortress independent, all
members of that clan, level 60-85, 2-9 people - enters instance zone 22
(`data/instances/[022] Fortress Dungeon.xml`, entry 53321/246314/-6452, 60
minutes, one opening per fortress every four hours). Raid bosses spawn one after
another at the prison spot: one of Hager the Outlaw / All-Seeing Rango / Jakard
after a minute, then one of Helsing / Gillien / Medici / Immortal Muus three
minutes after the first dies, then one of Brand the Exile / Commander Koenig /
Gerg the Hunter. The last one pays 1000 Dungeon Leader Marks (9797) split across
the party, the instance closes five minutes later, and the warden exchanges the
marks for Knight's Epaulettes. The datapack carries the 10 bosses and their 14
minions (25572-25595, converted from the H5 templates with the same rules as the
staff import), items 9797 Dungeon Leader Mark, 9445 Dynasty Bow and 9587 Striped
Scale Shirt (boss drops this pack lacked), and the 10 quest dialogs (en/ru).

The bosses' Giant's Codex - Oblivion / Discipline / Mastery drops (9625-9627)
have no use on this build - the core has no skill untrain, route change or
blessed enchant, and its enchant data consumes only 6622 - so the warden also
offers multisell 45700001, exchanging each of them for a Giant's Codex 1:1;
the three templates carry the Giant's Codex reference price so they can be
sold as well.

Adaptations: the H5 quest script became this core's `Quest`/`ScriptFile`
shape; entry goes through `ReflectionUtils.enterReflection`, whose
`Reflection.init` also arms the time limit; the party is enrolled in the quest
before the entry check because the instance's `<quest id="511"/>` makes the
core require the quest to be running on every member (H5 enrolled them right
after entering). The wardens are typed
`npc.model.residences.fortress.DetentionCampWardenInstance` so their retail
"rumor about Rim Pailaka" option answers with a dialog: Rim Pailaka (instance
80, entry 48200/-12232/-9128) and the castle dungeon of quest 512 (instance 13,
entry 12154/-49190/-2657) are not ported - this build's geodata has no map for
either spot (both are flat planes there), so they would need the same geodata
and client-map work the fortress prison got (`tools/geodata/` in the server
repo) before their scripts are worth porting.

## Siege flow (retail H5 rules)

1. A clan (level 4+, no castle relation conflicts) registers at the fortress's walking
   Suspicious Merchant; the first registration costs 250k adena and schedules the siege
   1h out (or 5h after the previous siege if it ended less than 4h ago).
2. At siege start the guards, commanders and (for an owned fortress) the rebel mercenary
   army spawn; barracks fall when their commanders die (main machine via the power unit
   password mini-games, using a Control Room Card on the control unit). Dead commanders
   respawn after 10 minutes unless all fall in time.
3. When every barrack is down, the command doors open and Combat Flags spawn; an attacker
   carries a flag to the flag pole and casts Flag Display (3318) to capture.
4. The new owner meets the castle envoy (1h) to choose independence or a castle contract;
   a contract enables facilities (reinforced guards, door upgrade, scouts, dwarven gunners,
   guard buffs) and Blood Oath rewards at the Logistics Officer. Fortresses auto-release
   after 7 days.

Verified by booting the full game server against a fresh database with this jar and the
datapack branch: 21 fortress residences and 21 FortressSiegeEvents load with zero errors,
all 519 fortress NPC templates resolve their instance/AI classes, all 283 siege spawn
groups bind, and the fortress table auto-creates with 21 rows.

Not covered: the Territory War layer H5 builds on top of fortresses (dominions do not
exist on this build), and the client must be one that knows the fortress NPCs/items
(modern Classic clients do).

## Build

Compile `src` against `server.jar`/`scripts.jar` (Java 17), jar `bin` as
`CustomQuestsExt.ext.jar`. `quests/_350_EnhanceYourWeapon` and `AutoLootExtension`
are kept out of the shipped jar, matching the deployed one (quest 350 already lives
in `scripts.jar`; AutoLootExtension force-toggles players' auto-loot).
