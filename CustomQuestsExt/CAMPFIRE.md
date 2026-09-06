# Campfire

A usable campfire: the **Campfire** item lights the campfire npc, and
everyone resting by the fire recovers faster.

| Piece | Where |
|---|---|
| Item 40100 Campfire | `gameserver/data/items/40100-40199.xml` - etc item, icon of the Classic Bonfire (71030), weight 0, stackable, not tradeable / sellable / droppable / storable, 3 s reuse |
| Npc 148 Campfire | `gameserver/data/npc/100-199.xml`, `type="Campfire"` = `npc.model.CampfireInstance` |
| Skill 40300 Campfire Relax | `gameserver/data/stats/skills/40300-40399.xml` - HP and MP recovery +50% (`regHp`, `regMp` x1.5) for 120 s, stack type `CampfireRelax`, not saved at logout |
| Config | `gameserver/config/custom/campfire.properties` |
| Extension | `handler.items.Campfire` (item handler, registered by the scripts engine), `com.l2horizon.CustomQuestsExt.campfire.CampfireManager` / `CampfireConfig`, `npc.model.CampfireInstance` |
| Strings | `campfire.*` in `data/string/strings_en.properties` / `strings_ru.properties` |
| Client | ItemName / EtcItemgrp rows of 40100 (`tools/client/item_rows`, `merge_item_rows.py`), Skillgrp / SkillName rows of 40300 (`tools/client/skill_rows`, `merge_skill_rows.py`) |

## How it works

Using the item calls `CampfireManager.light`: it refuses Olympiad
participants, observers, flying and swimming players, peace zones (unless
`CampfireAllowInPeaceZone`) and a second fire while the player's own one
burns (`CampfireOnePerPlayer`). Then one Campfire is consumed and npc 148 is
spawned one step in front of the player, facing them, on the ground (a wall
or drop right ahead puts it at the player's feet), in the player's
reflection, with a lifetime of `CampfireLifetimeMinutes` (15).

`CampfireInstance` gives Campfire Relax to every living, visible player
within `CampfireRadius` (300) two seconds after lighting and then every
`CampfireBuffIntervalSeconds` (60): the buff is applied directly with the
fire as the caster and a `MagicSkillUse` is broadcast so the client plays the
skill's effect (the Regeneration sparkle, `skill_visual_effect` 1044 in the
client row). Leaving the radius lets the buff run out after its 120 s. The
fire cannot be targeted, attacked or damaged and never moves; when its
lifetime ends the core deletes it and the task stops.

Nothing hands the item out yet: put 40100 on a shop, a multisell, an event
reward or Erica as you see fit.
