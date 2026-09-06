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
| geodata | tiles 17_11, 18_11 (Halls), 19_12, 20_12 (Labyrinths), 20_11, 21_11 (Rim), see `tools/geodata/README.md` |
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

## Rim Kamaloka (solo instances 46-56)

The eleven solo Rim Kamalokas (levels 25, 30, ... 75, entry within 5 levels, one a day for all of
them, reuse group 3 reset at 6:30). Data follows the Mobius High Five `RimKamaloka` script, the
NPC classes the L2Scripts `PathfinderInstance` / `KamalokaNightmare`.

| piece | place |
|---|---|
| instances | `data/instances/[046]..[056] Rim Kamaloka.xml`: solo (`party min=1 max=1`), `timelimit` 30 = 20 minutes of fighting + 10 to collect the reward, Kanabion spawns with `respawn="30"`, add_parameters `kanabion` / `doppler` / `voider`, `rewarder_loc`, `reward_lvl_1..5` |
| Kanabions | 33 templates 22452-22484 in `data/npc/22400-22499.xml` (three per level: Kanabion, Doppler, Void), ai `Kanabion` (`ai.Kanabion` of the extension) |
| Pathfinder Workers | 32484 (towns, `data/spawn/rim_kamaloka.xml`: Gludio, Dion, Heine, Oren, Rune, Schuttgart at the High Five spots) and 32485 (appears in the instance when the time is up), both typed `Pathfinder` (`npc.model.PathfinderInstance`), dialogs under `html-en/instance/soloKamaloka/` (`32484.htm`, `32484-<town>.htm`, `32485-F..S.htm`, ...) and `html-ru` |
| rewards | 40 Pathfinder Supplies boxes 10836-10864 and 12824-12834 (`data/items/10800-10899.xml`, `12800-12899.xml`) with their retail contents in `data/capsule_items.xml` (every item of a box is given), plus Essence of Kamaloka |
| geodata | tiles 20_11 (levels 25-65) and 21_11 (70, 75) from the Vanguard geodata |
| admin | Teleport Menu -> Kamaloka, section "Rim Kamaloka" |

**Entry.** The town Pathfinder Worker offers the instances of his castle domain (Gludio 46;
Dion 46-48; Heine 48-50; Oren 49-52; Schuttgart 51-54; Rune 53-56, the Mobius menus): "Challenge
Rim Kamaloka" (`ListPossible`, the domain comes from `MapRegionManager`, the nearest of the six
posts is the fallback) then `solo_kamaloka <id>`. The core checks solo, level and the daily reuse
(`Player.canEnterInstance`); the reflection is `RimKamalokaReflection`.

**Fight.** Kanabions respawn every 30 seconds at their four (six for levels 70 and 75) points.
Stronger Kanabions come out of them (`RimKamalokaReflection`, fed by the `Kanabion` ai): a hit that
takes more than 40 % of the HP of an unhurt Kanabion has 5 % to bring out a Doppler (a Doppler:
5 % Doppler, 5 % Void; a Void: 5 % Void); a kill brings one out with 15 % (Kanabion -> Doppler),
10 % + 10 % (Doppler -> Doppler / Void) or 20 % (Void -> Void), doubled or more on an overhit kill
(30 % + 10 %, 30 % + 30 %, 50 %). The newcomer appears on the corpse and attacks at once; one
that nobody attacked for `RimKamalokaMutantDespawnSeconds` fades away. After
`RimKamalokaLockMinutes` the daily reuse is set (a player who already left keeps the day free and
the empty instance closes).

**Result.** After `RimKamalokaDurationMinutes` every monster is removed, the grade is computed (F
below 10 Kanabions, else (Dopplers + 2 x Voids) / Kanabions + 1, capped at S) and the Pathfinder
Worker 32485 appears at `rewarder_loc`: `ShowResults` shows the grade page, `SoloKamaReward` gives
`reward_lvl_<grade>` once (Essence of Kamaloka 2-17 and one Pathfinder Supplies box, the retail
table of the Mobius script), `ExitSoloKama` sends the player back and closes the instance.

**Essence exchange.** The town Pathfinder Worker's "Exchange Essence of Kamaloka" opens
multisell 8100 (the High Five list without its three Kamaloka Circlet boxes, which the client does
not have): 10 / 20 / 50 / 100 / 200 essences for a Pathfinder's Reward D / C / B / A / S-Grade
box (13003-13007, `data/items/13000-13099.xml`). A box is one roll (`data/capsule_items.xml`), the
High Five chances without the attribute stones and the Icarus weapons, and Dynasty weapon pieces
where High Five gave full weapons: D 3.2 % Scroll: Enchant Weapon (B); C 1.6 % Scroll: Enchant
Weapon (A); B 1.1 % Scroll: Enchant Weapon (S); A 2 % Scroll: Enchant Weapon (S), 1 % each Sealed
Dynasty Helmet / Leather Helmet / Circlet Piece, 0.1 % each of the eleven Dynasty weapon pieces;
S 3.8 % Scroll: Enchant Weapon (S) and 0.3 % each of the eleven Dynasty weapon pieces. The rest of
the roll gives nothing.

| key | default | meaning |
|---|---|---|
| RimKamalokaEnabled | True | master switch |
| RimKamalokaDurationMinutes | 20 | fighting time |
| RimKamalokaExitMinutes | 10 | time to collect the reward after that |
| RimKamalokaLockMinutes | 10 | when the daily reuse is set (0: never) |
| RimKamalokaMutantDespawnSeconds | 10 | idle Dopplers / Voids vanish |
| RimKamalokaRewards | True | give the grade reward |

The client needs the 40 supplies rows (`tools/client/item_rows`); the Kanabions and Pathfinder
Workers already have their Classic names and meshes.

## Not ported

The Kanabion report and the leader board of the town Pathfinder Worker, and the three
300-essence Kamaloka Circlet boxes of the exchange (no client rows, no known contents); the Classic pack's `default/32485*.htm` are unused now (the Pathfinder Workers
read `instance/soloKamaloka/`).
