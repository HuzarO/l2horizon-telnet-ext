# Server stages

The server opens in stages. Each stage sets the highest equipment grade that
can be worn and the EXP/SP multipliers per level band, and multisell lists can
have a version per stage. Everything lives in the extension; no core class is
shadowed for it.

## Layout

| Part | Where |
|------|-------|
| Config | `gameserver/config/custom/stages.properties` (read at start) |
| Manager | `src/com/l2horizon/CustomQuestsExt/stages/StageManager.java`, `StageConfig.java` |
| Equipment gate | `stages/StageEquipCondition.java` (an item condition attached at world start) |
| Player hooks | `stages/StageListener.java` (login, level up, admin set level, subclass switch) |
| Admin | `handlers/admin/StageAdminCommand.java`, page `html-en/admin/stages.htm` (button *Stages* on the main admin page) |
| Players | `handlers/voice/StageVoiceCommand.java`: `.stage` opens `html-*/mods/stages/stage.htm` |
| Band passives | `gameserver/data/stats/skills/40200-40299.xml`: 40200 *Server Stage - EXP*, 40201 *Server Stage - SP*, 40 levels in steps of 0.05 (level 20 = x1.00) |
| Strings | `stages.*` and `common.MultisellForbidden` in `data/string/strings_en.properties`, `strings_ru.properties` |
| Generators | `tools/stages/gen_stage_skill_rows.py` (client rows) in the server repo |

## Choosing the stage

The stage is chosen once, at world start:

1. the stage stored by an admin (`ServerVariables` key `ServerStage`, set with
   `//stageset <n>`, cleared with `//stageauto`), or
2. when nothing is stored, the last stage whose `StageN.Date` has passed
   (stage 1 when none has).

Nothing switches on a running world. An admin choice or a passed date takes
effect at the next restart, and `.stage`, the login line and the admin page say
which stage the next restart activates. The boot log prints the chosen stage,
the number of gated items and the resolved multisell lists.

## Equipment gate

At world start every equipable item above No-grade (weapons, armor, jewelry,
shields, sigils, graded talismans and bracelets; pet gear excluded) receives a
`StageEquipCondition` for its grade. The core evaluates item conditions before
the equip handler runs (`UseItem`) and in `PcInventory.validateItems()`, which
the extension calls at login, so gear above the limit cannot be equipped and
is taken off at login after a stage went down. Unequipping is always allowed.
The refusal message is `stages.equip.<grade>`. `StageGateGm = False` exempts
GM characters.

## EXP/SP bands

`StageN.ExpBands = 1-40:1.0, 41-52:0.5, ...` (and optional `SpBands`) give the
multiplier per level band. The extension gives the player skill 40200/40201 at
the level of the factor in steps of 0.05 (x0.50 = level 10, x1.50 = level 30;
factors are rounded to the nearest 0.05) and removes it when the factor is
1.0. The client only knows skill levels up to 85 and treats levels above 100
as enchant routes, hence the coarse grid. The skills multiply
`ExpMultiplier`/`SpMultiplier` (and the raid variants), so they stack with
premium and party rates. They are refreshed at login, on level up, on an admin
level change and on a subclass switch; `//stagebands` refreshes everybody.

## Multisell versions

For a list `X` (file `X.xml`, numeric or named), `X-3.xml` is served from stage
3 on, `X-4.xml` from stage 4 on, and so on; the highest version not above the
active stage is registered under the name or id `X`, so html links and
purchase validation stay the same. A list that exists only as versions gets an
empty placeholder below its first stage and is refused with
`common.MultisellForbidden` ("not available at the current server stage").
`//reload multisell` drops the swap; `//stagemultisell` re-applies it.

Shipped versions:

* Blacksmith of Mammon: `ssq_*_for_a` -> `-3`, `ssq_*_for_s` -> `-4`,
  `ssq_*_for_s80`, `ssq_foundation_for_s80`, `ssq_rare_exchange_for_s80` -> `-5`
  (unseal, exchange, dual-sword, SA and Foundation services open with the grade).
* Any other list works the same way: add `X-N.xml` next to `X.xml`, or
  without an `X.xml` when the list should not exist below stage N. (A catch-up
  gear list for Erica was tried and removed; the core flags prices below the
  item reference price as a crystallization exploit.)
