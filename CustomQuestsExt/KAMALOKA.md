# Kamaloka: Hall of the Abyss and Labyrinth of the Abyss

High Five Kamaloka on the Classic core: the eleven Halls (levels 23-73, party of 2-6, 30
minutes, one boss) and the eight Labyrinths (levels 29-83, party of 2-9, 45 minutes, two rooms,
a Lost Warden and a Lost Captain). Data and mechanics follow the L2Scripts High Five templates
and NPC classes; the instance layout (entry points, room spawns, level ranges, reuse) follows the
L2J Mobius High Five `Kamaloka` script, which is the same retail data.

## Where it lives

| piece | place |
|---|---|
| instance definitions | `data/instances/[057]..[072] Kamaloka, Hall of the Abyss.xml`, `[073]..[079], [134] Kamaloka, Labyrinth of the Abyss.xml` |
| NPC templates (72) | `data/npc/18500-18599.xml` (Hall bosses and followers), `22400-22599.xml` (Lost Watchers / Beholders), `25600-25799.xml` (Lost Wardens, level 83 set), `29100-29199.xml` (Lost Captains and their guards), `4300-4399.xml` (Teleport Device), `32400-32499.xml` (Escape Device, retyped) |
| captain dialogs | `html-en/guard/<captain>.htm` gets "Ask about Kamaloka." (Chat 9), `html-en/guard/<captain>-9.htm` lists the entries; same in `html-ru` |
| device dialogs | `html-en/instance/kamaloka/32496.htm`, `32496-no.htm`, `4314.htm` (and `html-ru`) |
| strings | `Kama26Boss.helpme`, `kamaloka.disabled`, `kamaloka.wrongCaptain` in `data/string/strings_*.properties` |
| reward item | `data/items/13000-13099.xml`: 13002 Essence of Kamaloka |
| config | `config/custom/kamaloka.properties` |
| geodata | tiles 17_11, 18_11 (Halls), 19_12, 20_12 (Labyrinths), see `tools/geodata/README.md` |
| admin | Teleport Menu -> Kamaloka (`admin/teleports/kamaloka.htm`): every entry, room and boss spot |
| extension | `com.l2horizon.CustomQuestsExt.kamaloka.*`, `npc.model.KamalokaGuardInstance`, `LostCaptainInstance`, `KamalokaBossInstance`, `Kama26BossInstance` |

The Lucera GM event that used instance id 72 ("Kamaloka" in the events panel, Pathfinder Worker
40031) kept its data and moved to id 972 (`[972] Kamaloka Event.xml`, reuse group 9, bypass
`event_instance 972` in `events/instances/40031-1.htm`), so the retail level 73 Hall can have
its own id.

## Captains and instances

| captain | town | Halls | Labyrinth |
|---|---|---|---|
| 30332 Bathis | Gludio | 57 (Lv 23), 58 (Lv 26) | 73 (Lv 29) |
| 30071 Lucas | Dion | 60 (Lv 33), 61 (Lv 36) | 74 (Lv 39) |
| 30916 Gosta | Heine | 63 (Lv 43), 64 (Lv 46) | 75 (Lv 49) |
| 30196 Mouen | Oren | 66 (Lv 53), 67 (Lv 56) | 76 (Lv 59) |
| 31981 Vishotsky | Schuttgart | 69 (Lv 63), 70 (Lv 66) | 77 (Lv 69) |
| 31340 Mathias | Rune | 72 (Lv 73) | 78 (Lv 78), 79 (Lv 81), 134 (Lv 83) |

The captains keep their Classic guard dialogs; the added link opens the Kamaloka page, whose
entries are `bypass -h kamaloka_enter <instance id>`, served by `KamalokaBypassHandler` (a core
`IBypassHandler`, so no NPC class is shadowed). With `KamalokaCaptainCheck` a captain only sends
parties to his own instances.

## Rules (all in the instance xml, enforced by the core)

- Party of 2 to 6 (Halls) or 2 to 9 (Labyrinths), the leader talks, every member within 500 of
  him, every member within 5 levels of the instance level (`<level min max>`).
- One Hall and one Labyrinth per day: `sharedReuseGroup` 1 for the Halls, 2 for the Labyrinths,
  reset `30 6 * * *`. The reuse is set when the boss dies (`setUponEntry="false"`), so a party
  that fails or leaves may try again.
- Buffs are dispelled on entry (`dispelBuffs="true"`), 30 or 45 minutes time limit, the instance
  closes 5 minutes after it is empty or 60 seconds after the party is dismissed.
- Re-entry: a member who left can talk to the captain again while the instance runs and is sent
  back in (`Player.canReenterInstance`).

## Mechanics

**Halls.** The xml spawns the boss; the template AIs of scripts.jar do the rest (OiAriosh and
SeerFlouros call followers as their HP drops, Kama56Boss drives Knight Montagnar's minions). The
bosses are `KamalokaBoss` (`KamalokaBossInstance`): not raid bosses, and every
`KamalokaBossMpRegenSeconds` they restore 6 / 10 / 13 / 16 / 19 / 22 MP (levels 23-26 / 33-36 /
43-46 / 53-56 / 63-66 / 73) to the players around them. Ol Ariosh (`Kama26Boss`) keeps one
Follower of Ariosh as a minion and calls a new one every `KamalokaKama26MinionRespawnSeconds`
after it dies, shouting `Kama26Boss.helpme`.

**Labyrinths.** First room: nine spawn points, one of them (random) holds the shaman, a Lost
Watcher whose `custom.LabyrinthLostWatcher` ai cuts the boss's P.Def to 66 % when it dies; the
other eight hold Lost Watchers that respawn every `KamalokaRoom1RespawnSeconds` until the shaman
is dead (`KamalokaReflection`, add_parameters `room1_shaman`, `room1_minion`, `room1_points`).
Second room: five Lost Beholders, the last one to die cuts the boss's M.Def to 66 %
(`custom.LabyrinthLostBeholder`). The Lost Warden cuts the boss's P.Atk to 66 %
(`custom.LabyrinthLostWarden`). The Lost Captain (`LostCaptain`, `LostCaptainInstance`, a
reflection boss with two Lost Guards and two Lost Assistant Guards) ends the instance. An Escape
Device (32496, `KamalokaGuard`) stands at the entrance: the party leader can close the instance
and send everybody back.

**Boss death** (`LostCaptainInstance`, shared by all bosses): the reuse of the instance is set
for everyone who entered, all NPCs are removed and the instance closes in 5 minutes
(`ReflectionBossInstance`), a Teleport Device (4314, `KamalokaGuard`) appears at
`tele_device_loc` (the entry point) and returns the party to where it came from, and in the
Labyrinths every player present receives Essence of Kamaloka: 5 / 7 / 8 / 12 / 15 / 18 / 18 / 19
for instances 73 / 74 / 75 / 76 / 77 / 78 / 79 / 134 (`KamalokaEssenceCounts`). The Hall bosses
keep their High Five drop lists.

## Config (`config/custom/kamaloka.properties`)

| key | default | meaning |
|---|---|---|
| KamalokaEnabled | True | master switch (entries answer `kamaloka.disabled`) |
| KamalokaCaptainCheck | True | a captain only leads to his own instances |
| KamalokaRoom1RespawnSeconds | 25 | Labyrinth first-room respawn until the shaman dies (0: none) |
| KamalokaEssenceReward | True | Essence of Kamaloka on the Labyrinth boss |
| KamalokaEssenceRewardPremiumOnly | False | only players with a bonus account get it |
| KamalokaEssenceItemId | 13002 | the reward item |
| KamalokaEssenceCounts | 73:5,...,134:19 | count per instance id |
| KamalokaBossMpRegen | True | Hall bosses restore MP to nearby players |
| KamalokaBossMpRegenSeconds | 20 | its interval |
| KamalokaTeleportDevice | True | Teleport Device after the boss |
| KamalokaTeleportDeviceNpcId | 4314 | its template |
| KamalokaKama26MinionRespawnSeconds | 60 | Ol Ariosh's follower respawn (0: none) |

## Client

The Classic client already has every mesh (Npcgrp rows identical to High Five) and every name
except five ids that Classic reused for Procella's raid (29129-29133); those NpcName rows are
replaced with the High Five names (`tools/client/npc_rows`, `merge_npc_rows.py`). Essence of
Kamaloka needs its ItemName / EtcItemgrp rows (`tools/client/item_rows`, `merge_item_rows.py`).
The maps of tiles 17_11, 18_11, 20_12 (Vanguard) and 19_12 (High Five) have to be present in the
client; the server geodata matches them.

## Not ported

Rim Kamaloka (the solo instances 46-56 of the Pathfinder Workers 32484 / 32485 / 32713) and the
Essence of Kamaloka exchange of the Pathfinder Worker; the Classic pack's `default/32485*.htm`
still carry their unimplemented bypasses.
