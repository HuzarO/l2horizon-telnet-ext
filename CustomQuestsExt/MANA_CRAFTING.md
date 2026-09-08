# Mana potion crafting

Mana Drug (726) and Mana Potion (728) are not sold anywhere on l2horizon: Dwarves
craft them from materials that come out of fish, with recipes that raid bosses
drop. Everything is datapack (l2horizon-server), no extension code.

## The potions

| Item | Effect | Re-use | Usable from |
|---|---|---|---|
| 726 Mana Drug | skill 90002: 20 MP every second for 5 s (100 MP) | 30 s | level 20 |
| 728 Mana Potion | skill 90001: 100 MP at once | 5 s (was 1 s) | level 60 |

Both stay blocked in the Olympiad (the stock condition). The level gates are
`<cond customMessage=...><player minLevel=.../></cond>` blocks in
`gameserver/data/items/700-799.xml`; the messages are the keys
`items.manadrug.lowlevel` and `items.manapotion.lowlevel` in
`gameserver/data/string/strings_en.properties` and `strings_ru.properties`.

## The recipes (`gameserver/data/recipe.xml`)

| Recipe | Item | Create Item level | Materials | Makes |
|---|---|---|---|---|
| 872 Recipe: Mana Drug (60%) | 91695 | 2 (Artisan, level 20) | 2 Aqua Herb, 2 Spirit Powder, 1 Pure Water Bottle | 15 Mana Drugs |
| 873 Recipe: Mana Drug (100%) | 91696 | 2 | 5 / 5 / 3 | 15 Mana Drugs |
| 874 Recipe: Mana Potion (60%) | 91697 | 8 (Warsmith, level 62; was 6) | 10 Aqua Herb, 10 Spirit Powder, 6 Pure Water Bottle, 2 Crystal of Mind | 15 Mana Potions |
| 875 Recipe: Mana Potion (100%) | 91698 | 8 (was 6) | 20 / 20 / 12 / 4 | 15 Mana Potions |

Only the 60% recipes are obtainable; the 100% ones have no source (GM hand-out).
The Mana Potion recipes were raised to Create Item level 8 so that the level 60
potion belongs to a Warsmith of about the same level, not to a 49 Artisan.

## Where the recipes drop (`gameserver/data/npc/*.xml`)

Every npc of type RaidBoss with a `RATED_GROUPED` reward list got one more
group, marked `<!-- l2horizon: mana potion crafting, see MANA_CRAFTING.md -->`:

| Bosses | Group | Drop |
|---|---|---|
| level 20-59 (129 bosses) | chance 30% | 1 Recipe: Mana Drug (60%) |
| level 60 and above (80 bosses) | chance 20% | 1 Recipe: Mana Potion (60%) |

The group chance is multiplied by `RateRaidBoss` (1.0 here), so the numbers
above are the in-game chances. Instance and special raid bosses of the right
level are included (the rift Anakazels, Zaken, Gordon, Anais, Van Halter,
Hellinark, Hestia, ...); 29060 Captain of the Ice Queen's Royal Guard (a quest
boss without a drop table) is the only level 20+ raid boss without the group.

## Where the materials come from (`gameserver/data/capsule_items.xml`)

Every fish that opens (the 271 fish and treasure boxes of `fishdata.xml` that
have a capsule entry) rolls, independently of its own rewards:

| Material | Chance | Count |
|---|---|---|
| 91699 Aqua Herb | 25% | 1-2 |
| 91700 Spirit Powder | 25% | 1-2 |
| 91701 Pure Water Bottle | 15% | 1 |
| 91702 Crystal of Mind | 5% | 1 |

The 30 fish ids that have no capsule entry (6519-6528, 7610-7613, 7807-7809,
8484-8486, 8505-8513, 8548) cannot be opened and were left alone.

Expected yield per 100 fish: 37.5 Aqua Herb, 37.5 Spirit Powder, 15 Pure Water
Bottles, 5 Crystals of Mind. That is enough for 15 Mana Drug crafts (225
potions, about 135 after the 60% roll) or 2.5 Mana Potion crafts (37 potions,
about 22 after the roll). At 50-60 fish an hour a fisher gathers the materials
for roughly 70-80 Mana Drugs or 11-13 Mana Potions per hour, before the dwarf's
craft fee. The Pure Water Bottle limits the Mana Drug, the Crystal of Mind the
Mana Potion.

## Client

Only descriptions change: `tools/client/item_rows/ItemName_Classic.rows.txt`
carries replacement rows for 726, 728, 91695, 91697, 91698 and 91699-91702
(level gates, the 5 s re-use, Create Item level 8, the raid boss and fish
sources); `merge_item_rows.py` puts them in. The items, icons and the recipe
rows themselves were already in the Classic tables.

## Tuning

* recipe chance on the bosses: the `<group chance=...>` lines after the
  l2horizon comment in the npc files (grep `mana potion crafting`);
* material chances: the four `l2horizon` lines in every fish capsule;
* the level gates and the re-use: items 726 / 728 in `items/700-799.xml`;
* the craft level: `level=` of recipes 874 / 875 in `recipe.xml`.
