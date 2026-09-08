# Costumes

Costumes are quest-tab items that carry skills; a costume skill is a self buff
whose only effect is one of the Classic client's appearance abnormal effects,
the way the Lucera thread "Change of appearance (Classic)" describes it. The
core's AbnormalEffect list has 39 costume families with three looks each
(`<base>_light`, `<base>_black`, `<base>_red`: plain, black aura, red aura;
Santa Claus and School Uniform use `_light_glow` etc.) and seven older event
suits with one look (`pirate_suit`, `dark_assassin_suit`, `white_assassin_suit`,
`musketeer_suit`, `wizard_suit`, `halloween_suit`, `halloween_suit_2`). All 124
looks are in the pack now. The "legacy" appearances of that thread (bunny,
skeleton, maid, joker, steampunk, ...) need a patched client and are not in
this core's AbnormalEffect list, so they are not here.

Everything is generated: `tools/costumes/costumes.json` (families, outfits,
ids, icons) and `tools/costumes/gen_costumes.py` in the server repo write the
items, the skills, Erica's shop list, her try pages and the client rows.

## Items and skills

| | Items | Skills |
|---|---|---|
| the 20 original costumes (School Uniform ... Red Archer) | 92401-92420 | plain 60101-60120 (unchanged ids), black and red auras 60201-60240 |
| 19 more families (Santa Claus, Blue Pirate, Dark Red Wizard, Mystic, Black Pirate, Red Noblesse, Wild Wolf, Valkyrie, Kelbim, Ninja, High Priest, White Noblesse, Purple Cowboy, Blue Musketeer, Zaken, Dragon Berserker, Anakim, Lilith, Freya) | 92421-92439 | 60241-60297 |
| 7 event outfits (Pirate, Dark Assassin, White Assassin, Musketeer, Wizard, Halloween, Halloween II) | 92440-92446 | 60298-60304 |

An item is `RUNE_QUEST`, not tradeable, droppable or sellable, and gives its
skills while it is in the inventory (three per family, one per outfit). The
skills are the original recipe: self buff, 45 min 30 s, re-use 10 minutes
(permanent), stack type Transformation so the next costume replaces the
current one, not cancelable, blocked in the Olympiad. Two flags were added to
all of them, the 20 original ones included: `isSaveable` (the costume is still
on after a relog; before, the buff was lost while the 10-minute re-use stayed)
and `isPreservedOnDeath`.

The server-side icons of the 20 original items were empty; they now carry the
client icon names.

## Erica

* Shop tab, "Buy Costumes": multisell 999007, all 46 items for 100 Horizon
  Coins (91616) each.
* Shop tab, "Try Costumes and Mounts": `shop/try_costumes_1..4.htm` (the
  families, a Plain / Black / Red button each), `shop/try_outfits.htm`,
  `shop/try_mounts.htm` (the 20 mounts of multisell 999009). A button sends
  `bypass -h Quest _702_ServiceManager try_costume <skill> <page>` or
  `try_mount <skill>`, handled by
  `com.l2horizon.CustomQuestsExt.costumes.AppearancePreview` (no core class
  shadowed):
  * costume: the appearance abnormal of the skill is shown for 60 seconds
    without touching the buffs; the abnormal of the costume the player wears
    is hidden for the minute and shown again afterwards. Refused while dead,
    transformed, mounted or in the Olympiad.
  * mount: the effects of the mount skill (the same transformation the bought
    mount gives) are applied and stopped after 60 seconds; the skill's own
    conditions decide (already transformed, riding, in water, pet out ... with
    the retail message).
  * one preview per player, a new one ends the running one; messages
    `l2horizon.preview.costume|mount|unavailable` (en, ru).

## Client

`tools/client/item_rows/` carries ItemName and EtcItemgrp rows for all 46
items (the 20 original ones regenerated with the same icons and a fuller
description) and `tools/client/skill_rows/` the Skillgrp and SkillName rows
for all 124 skills (`merge_item_rows.py`, `merge_skill_rows.py`). The icons
of the new families come from the retail costume items of the Classic tables;
two are best guesses to check in game: Dark Red Wizard
(`g_bm_costume_wizard_wine`) and Mystic (`g_bm_costume_magician`), and the
seven event outfits reuse related icons (pirate, dark assassin, white knight,
inquisitor, wizard circlet, halloween, pumpkin).

## Changing things

* prices: `price` in costumes.json;
* a family's icon or name: costumes.json, then `python3
  tools/costumes/gen_costumes.py` and the two client merges;
* duration / re-use: `skill_xml()` in the generator;
* preview length: `AppearancePreview.SECONDS`.
