# Hellbound Island (High Five port)

The island surface and the Steel Citadel exterior of High Five Hellbound,
ported from L2Scripts 2268 onto this Classic core. The Steel Citadel interior
(Tully's Workshop, Tower of Infinitum, Naia, Beleth) and the Urban Area
instance are **not** part of it: the trust stage is capped at 9.

## Layout

| Part | Where |
|------|-------|
| Trust manager, config | `src/com/l2horizon/CustomQuestsExt/hellbound/HellboundManager.java`, `HellboundConfig.java` |
| NPC instances | `src/npc/model/{CaravanTrader,Warpgate,QuarrySlave,HellboundRemnant,NativeCorpse,Sandstorm,ImmuneMonster}Instance.java` |
| AI | `src/ai/hellbound/{Chimera,Leodas,OutpostCaptain,OutpostGuards,Pylon,Sandstorm,Typhoon}.java` |
| Entry quests | `src/quests/_130_PathToHellbound.java`, `_131_BirdInACage.java`, `_133_ThatsBloodyHot.java` |
| Holy Water | `src/handler/items/HolyWater.java` |
| Commands | `handlers/admin/HellboundAdminCommand.java` (`//hbadd`, `//hbsub`, `//hbset`, `//hbinfo`), `handlers/voice/HellboundVoiceCommand.java` (`.hellbound`) |
| Datapack | `gameserver/data/hellbound_spawnlist.xml`, `npc/18400-18499.xml`, `22300-22399.xml`, `22400-22499.xml`, `32300-32399.xml` (+ entries in `13000-13099`, `25500-25599`, `32200-32299`), `spawn/hellbound_static.xml`, `doors/19_25.xml`, `20_25.xml`, `zone/dummy.xml` (`[Hellbound_territory]`), `html-*/hellbound/`, `html-*/quests/_13x*`, `multisell/250980013-14, 323472-4`, `config/custom/hellbound.properties` |
| Generators | `tools/hellbound/gen_hellbound_h5.py`, `tools/hellbound/gen_dynasty_h5.py` (server repo) |

The manager loads as a script (`ScriptFile.onLoad`) once the world is up, reads
`data/hellbound_spawnlist.xml`, spawns the entries of the current stage,
attaches its death listener to every spawned NPC and re-checks the stage every
`HellboundStageCheckMinutes`.

## Trust stages

Trust points live in `ServerVariables` (`HellboundConfidence`, `HB_judesBoxes`,
`HB_bernardBoxes`, `HB_derekKilled`, `HB_captainKilled`), thresholds are retail:

| Stage | Trust | Content |
|------:|------:|---------|
| 0 | 0 | closed; the warpgate opens the island (stage 1) for the first player who finished *Path to Hellbound* |
| 1 | 1 – 300k | harbor, quarry, Typhoon, Junior/Blind/Arcane demons (+1/+3 per kill); natives and Quarry Slaves killed cost -10 |
| 2 | 300k – 600k | Remnant Diabolist/Diviner appear (+5 with Holy Water) |
| 3 | 600k – 1M | Darion's Enforcers/Executioners (+3), Keltas (+100) |
| 4 | 1M – 1.2M | needs Jude's 40 Native Treasures and Bernarde's box; Derek (+10000) |
| 5 | 1M – 1.2M, Derek dead | Native village opens (doors 19250001/2), Leodas, Quarry Slaves can be rescued (+10), Traitor opens the cells for 10 Marks of Betrayal |
| 6 | 1.2M – 1.5M | Hellinark (+500) with Naia Failan pylons and Failan's Guards |
| 7 | 1.5M – 1.8M | citadel exterior gate (20250002), chimeras and Celtus (Magic Bottle at <10% HP drops life force) |
| 8 | 1.8M – 2.1M | Outpost Captain and guards (+10000) |
| 9 | 1.8M – 2.1M, captain dead | Hell gate (20250001) opens; Native Slave accepts badges. Highest stage on this server. |

Kief exchanges life forces for trust (+100/+100/+50), Falk exchanges Darion's
Badges (10 per badge) and sells the First Mark, Hude trades the marks and the
S80 materials (Hidden First/Second Page, Demon Contract Fragment) for sealed
Dynasty gemstones and recipes (multisell 250980013), Shadai turns Ancient Tomes
of the Demon into Dynastic Essence (323472) and switches Dynasty class armor
variants (323473/323474). Bernarde talks only to players wearing the Native
transformation (set 9669/9670/9671, skill 3359).

## Adaptations to this core

* `Config.HELLBOUND_LEVEL` / `RATE_HELLBOUND_CONFIDENCE` became
  `config/custom/hellbound.properties` (`HellboundMinLevel`,
  `RateHellboundConfidence`, plus `HellboundMaxLevel` = 9 and the check interval).
* H5 template keys converted by the generator: `NpcAI` → `CharacterAI`,
  `basePCritRate` → `baseCritRate`, `basePHitModify`/`basePAvoidModify` →
  `physicalHitModify`/`physicalAvoidModify`, `baseMAtkSpd` added, integer
  combat stats, `male;female` collision values reduced to one, `faction names`
  → `name`, `use_type` dropped from skills. H5 attribute stones (9546-9551) and
  item 13099 are not on this server and were removed from the drop lists; the
  lvl 80 life stones became 90012-90015.
* Quest completion checks use the quest class names
  (`player.isQuestCompleted("_130_PathToHellbound")`), quest dialogs are
  dispatched as `bypass -h Quest <name> <file>`.
* `ItemFunctions.addItem/removeItem/getItemCount` replace the H5 inventory
  helpers; `NpcHtmlMessage` replaces `NpcHtmlMessagePacket`.
* Citadel bypasses (`tully_entrance`, `enter_urban`) answer with the closed
  dialogs; Deltuva and Kanaf stay on the island as in H5.
* The stage-10/11 spawn entries (22396-22399, 22403) were removed from the
  spawn list; Jerian (32302) and Dorian (32373) are not spawned.

## Dynasty

`gen_dynasty_h5.py` adds every High Five Dynasty item (weapons with SA and PvP
variants, armor with class variants, jewelry, sealed pieces, recipes, parts,
gemstones, Foundation items, sigils; the event "of Friendship" sets and the
unused jewelry are left out), the 31 retail recipes (ids 872-875 collide with
the pack's custom recipes and were moved to 950-953), the armor sets (ids
65-72 moved to 200-207, the Native set is 208) and the item/set skills the pack
lacked. Ten standard SA skills (3009, 3027, 3057, 3066, 3552, 3564, 3565, 3566,
3573, 3600) were replaced by their H5 definitions because the Dynasty weapons
use higher levels of them.
