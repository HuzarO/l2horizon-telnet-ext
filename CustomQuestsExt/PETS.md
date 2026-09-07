# Pets: Great Wolf, Fenrir and the Improved baby pets

Wolf -> Great Wolf -> Fenrir and Baby Buffalo / Cougar / Kookaburra -> Improved
baby pets, evolved at the pet managers like on High Five. The evolution scripts
live in this extension, everything else is datapack data
(`HuzarO/l2horizon-server`).

## What the players see

* Every pet manager (Martin 30731, Lundy 30827, Waters 30828, Cooper 30829, Joey 30830, Nelson 30831, Lemper 30869, Rood 31067, Annette 31265, Woods 31309, Saroyan 31954) has a new line **Evolve a pet** (`petmanager/pet_evolution.htm`,
  en + ru) with five choices:
  * Wolf (level 55+) -> Great Wolf: Wolf Collar 2375 becomes Great Wolf Necklace 9882
  * Great Wolf (level 70+) -> Fenrir: Great Wolf Necklace 9882 becomes Fenrir Necklace 10426 (rideable)
  * Baby Buffalo (55+) -> Improved Baby Buffalo: Panpipe 6648 -> Improved Buffalo Panpipe 10311 (warrior buffs)
  * Baby Kookaburra (55+) -> Improved Baby Kookaburra: Ocarina 6650 -> Improved Kookaburra Ocarina 10313 (mage buffs, recharge)
  * Baby Cougar (55+) -> Improved Baby Cougar: Chime 6649 -> Improved Cougar Chime 10312 (mixed buffs)
* The pet must be summoned, alive, next to the manager and at the required
  level; the pet keeps its name and its experience, the player summons it again
  with the new necklace (the enchant level of the necklace is the pet level).
* Baby pets: the **Pet Exchange Tickets** are quest rewards, as on retail:
  Help the Uncle! (quest 42, Pet Manager Waters 30828, level 25) gives the
  Buffalo ticket 7583, Help the Sister! (43, Pet Manager Cooper 30829, level
  26) the Cougar ticket 7584 and Help the Son! (44, Pet Manager Lundy 30827,
  level 24) the Kookaburra ticket 7585; some clan halls produce them as well.
  The ticket is exchanged for the baby pet on the manager's **Exchange the pet
  trade ticket with a pet** page, whose text and the "no ticket" reply point
  at the quests. The tickets are not sold anywhere. The pet manager shops
  sell Great Wolf Food 9668 and Improved Baby Pet Food 10425.
* The core's baby pet AI (`PetBabyInstance`) checks the owner every second.
  Baby pets cast Heal Trick 4717 when the owner is below 90% HP and Greater
  Heal Trick 4718 below 33%, with a chance that grows as the HP drops (the
  heal level follows the pet level). Improved Baby Buffalo and Cougar use Pet
  Greater Heal 5195 and Pet Battle Heal 5590 the same way; the Improved Baby
  Kookaburra uses Pet Battle Heal below 33% HP and Pet Recharge 5200 when the
  owner is below 66% MP. That is the retail split: only the improved
  Kookaburra restores MP, the plain baby pets restore HP. From level 55 the
  improved pets also buff their owner, a stronger set every 5 levels:

  | Pet | 55 | 60 | 65 | 70 |
  |---|---|---|---|---|
  | Buffalo | Might 3, Blessed Body 6 | Shield 3, Guidance 3 | Vampiric Rage 4, Haste 2 | Focus 3, Death Whisper 3 |
  | Kookaburra | Empower 3, Blessed Soul 6 | Blessed Body 6, Shield 3 | Acumen 3, Concentration 6 | (same) |
  | Cougar | Empower 3, Might 3 | Shield 3, Blessed Body 6 | Acumen 3, Haste 2 | Vampiric Rage 4, Focus 3 |

  The pet recasts a buff when it is about to run out (as long as it has MP),
  so the buffs are permanent while the pet is out. On High Five the pet
  skills carried the Prophet's top-level values; on this server they are a
  weaker fallback for players without a buffer (skill data only, the core's
  choice of skill levels is untouched):

  | Pet skill (level the pet casts) | High Five | l2horizon |
  |---|---|---|
  | Pet Blessed Body 6 / Pet Blessed Soul 6 | +35% HP / MP, 20 min | +15%, 2 min |
  | Pet Empower 3 | +75% M. Atk. | +30% |
  | Pet Might 3 / Pet Shield 3 | +15% P. Atk. / P. Def. | +8% |
  | Pet Acumen 3 / Pet Haste 2 | +30% / +33% | +15% / +15% |
  | Pet Vampiric Rage 4 | 9% absorbed | 5% |
  | Pet Focus 3 / Pet Death Whisper 3 | +30 crit. rate / +35% crit. dmg | +15 / +15% |
  | Pet Guidance 3 | +4 accuracy | +2 |
  | Pet Concentration 6 | -53% cancel chance | -26% |

  Every pet buff has stack order 0 while the player buffs start at 1, so a
  Prophet's (or any player's) buff of the same kind always replaces the pet's
  and the pet never overwrites it; the pet's buff lands again once the
  player's runs out. The client SkillName rows of these skills spell out the
  exact value per level.

  The level of each buff grows with the pet's level (this is the one core
  class the feature shadows, `l2.gameserver.model.instances.PetBabyInstance`,
  see below): level 1 at pet level 55 and the top level at pet level 80,
  linearly in between. A 6-level buff (Blessed Body, Blessed Soul,
  Concentration) gains a level every 5 pet levels; a 3-level buff (Empower,
  Might, Shield, Acumen, Guidance, Focus, Death Whisper) reaches level 2 at 62
  and level 3 at 74; Haste (2 levels) reaches level 2 at 68; Vampiric Rage
  (4 levels) reaches 2 / 3 / 4 at 59 / 67 / 76. So a fresh level-55 Improved
  Kookaburra gives Empower 1 (+20% M. Atk.) and Blessed Soul 1 (+5% MP), a
  level-80 one Empower 3 (+30%) and Blessed Soul 6 (+15%).

## Extension (`src/services/petevolve`, `src/l2/gameserver/model/instances/PetBabyInstance.java`)

`PetBabyInstance` is the core's baby pet class (heals, recharge, buff task)
copied with one change: `getBuffs()` picks each buff's level from the pet's
level (`getBuffSkillLevel`) instead of always using the top level. The class
is final and created directly by `PetDAO`, so it cannot be extended; the
extension jar precedes server.jar on the class path, so this copy is the one
the server loads. Everything else in it (which buffs unlock at 55 / 60 / 65 /
70, heal and recharge levels, timing) is the core's logic unchanged.


`PetEvolution` (abstract, extends `Functions`) does the work; `wolfevolve`,
`fenrir`, `ibbuffalo`, `ibcougar` and `ibkookaburra` only supply the ids and
pages. They carry the retail script names on purpose: the dialogs use the
retail bypasses (`[scripts_services.petevolve.wolfevolve:evolve|...]`) and the
core's extension loader (`Scripts`) registers every class of an `.ext.jar` as a
script class, so nothing of the core is shadowed and nothing has to be
registered in `CustomQuestsExt.onLoad`.

The evolution itself is the High Five one: `pets` rows are keyed by the control
item's object id, so unsummoning the pet (which stores name, level and exp),
switching the control item's item id (`ItemInstance.setItemId`, the core marks
it changed and saves it) and setting its enchant level to the new pet's
`min_level` is all that is needed. `PetDAO.restore` rebuilds the pet from the
new `pet_data` table on the next summon: the level comes from the necklace, the
experience from the row, and the core moves the level to where the experience
lands in the new table. The tables are built so that the level carries over
exactly (see below).

Checks, with the page shown on failure (`data/html-en|ru/scripts/services/petevolve/`):
manager farther than 300 (`no_dist`), no source collar in the inventory
(`no_item`), no live pet summoned (`evolve_no`), wrong pet (`no_wolf`,
`no_great_wolf`, `no_pet`), level too low (`no_level`, `no_level_gw`), pet too
far (`no_dist`). Success shows `yes_wolf` / `yes_pet`. The Russian pages were
added for all of them.

## Datapack

* `data/stats/pets/pet_data.xml`: new pets 16025 Great Wolf (control 9882, food
  9668, `is_great_wolf`, min level 55, Bite Attack 5442 like the Fenrir, not
  rideable) and 16034 / 16035 / 16036 Improved Baby Buffalo / Kookaburra /
  Cougar (controls 10311 / 10313 / 10312, food 10425, `is_improved_baby_pet` +
  the per-type flag the core's `PetBabyInstance` needs, min level 55, 5% exp
  share like the baby pets).
  * Great Wolf table: the datapack's Great Snow Wolf 16037 table (`is_great_wolf`,
    the same pet family) re-aligned by six levels, which makes it continuous
    with the retail tables the pack already has: its level-55 exp equals the
    Wolf's level-55 exp (26,502,939) and its level-70 exp equals the Fenrir's
    level-70 exp (266,966,617), so a Wolf keeps its level when it becomes a
    Great Wolf and a Great Wolf keeps its level when it becomes a Fenrir. HP
    3489 at 55, 5098 at 70, 6083 at 81 (Fenrir: 5198 at 70, 6156 at 81).
  * Improved baby pets: the baby pets' exp curve (125,000 per level, so the
    level carries over and the 5% share keeps the pet close to its owner) and
    their regeneration, with the level-55 pet body of the Great Wolf table for
    HP, MP, attack, defence, accuracy, evasion and food (the retail High Five
    pet tables were not available offline; the Great Wolf and the improved
    pets were introduced together as level-55 pets and share their npc
    templates' base stats).
* `data/stats/skills`: the pet buff and heal skills raised to their High Five
  levels (5186 Pet Haste 2, 5187 Pet Vampiric Rage 4, 5188 Pet Regeneration 3,
  5189 Pet Blessed Body 6, 5190 Pet Blessed Soul 6, 5191 Pet Guidance 3, 5192
  Pet Wind Walk 2, 5193 Pet Acumen 3, 5194 Pet Empower 3, 5195 Pet Greater
  Heal 12, 5200 Pet Recharge 8, 5201 Pet Concentration 6) with the Classic
  stack types kept. `PetBabyInstance` looks these levels up when it is first
  used, so the improved pets could not work without them.
* Items: 9668 Great Wolf Food (Great Wolf, Great Snow Wolf, Fenrir, White
  Fenrir, also when ridden) and 10425 Improved Baby Pet Food (the three
  improved pets) are pet food now (skill 2048 levels 3 and 4, `is_pet_food`,
  `has_pet` conditions), like Food for Wolves and Baby Spice.
* `data/merchant_buylists.xml`: the eleven pet manager shops sell 9668 and
  10425.
* HTML: `petmanager/pet_evolution.htm` (en new, ru rewritten), the evolve link
  on the eleven manager pages (en + ru), the ticket page tells where tickets
  come from, the petevolve message pages (en additions, ru set).

## Client

ItemName / EtcItemgrp rows for 9668, 9882, 10311, 10312, 10313, 10425, the
ticket rows 7583 / 7584 / 7585 with a description and `related_quest_id`
(42 / 43 / 44, the client shows the quest in the tooltip), and
Skillgrp / SkillName rows for the 41 new skill levels, built from the High Five
tables (`tools/client/README.md`, "Pet rows"). The pet NPCs were already in the
Classic tables (NpcName calls 16025 "Great Black Wolf").

## Notes

* The datapack's own Great Snow Wolf 16037 table starts six levels early (its
  level-49 row holds the retail level-55 values); it was left as it was.
* `ImprovedPetsLimitedUse` (altsettings.properties, False) is honored like on
  High Five: when it is True a mage cannot evolve a Baby Buffalo and a fighter
  cannot evolve a Baby Kookaburra (`no_class_w.htm` / `no_class_m.htm`).
* `PetsHealOnlyInBattle` (altsettings.properties) was True, which let the baby
  pets and the improved baby pets heal and recharge only while the owner is in
  combat (about 15 s after the last hit); it is False now, like retail: the
  pets act whenever the owner's HP or MP is low.
* The tickets stay in the clan hall item lists as before; they were briefly
  in the pet manager shops and are not any more.
