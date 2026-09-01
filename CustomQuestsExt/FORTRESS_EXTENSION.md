# Fortress Extension

The complete fortress system for the 21 fortresses (residence ids 101-121): residences,
the full siege event (registration at the Suspicious Merchant, commanders/barracks, power
units, combat flag capture), fortress NPCs and their AI, facilities, envoys/contracts and
Blood Oath logistics. The data side lives in the `l2horizon-server` datapack (branch
`claude/lineage2-fortress-xml-files-2gvuuw`): residence/event/zone XMLs, 519 NPC templates,
4800+ siege spawns, skills 3318/3358, and items: 9819 Combat Flag, 9910 Blood Oath,
10031 Control Room Card (moved from H5's 10014, which this client uses for a D-grade
enchant scroll), 9912 Knight's Epaulette + shirts 9579-9581 (guard drops) and 10212
npc Only (Bow) - H5 items this pack was missing.

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
