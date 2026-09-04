# Hellbound (Essence version)

The Hellbound island in the client maps in use is the Classic-era build
(package version 133, `T_19_25_Classic` / `T_20_25_Classic` terrain), not the
High Five one, so the zone is ported from the content made for that map: the
L2J Mobius Essence 6.2 Vanguard pack (`spawns/Hellbound/Hellbound.xml`,
`ai/areas/Hellbound/*`, `teleporters/others/Hellbound.xml`,
`TeleportListData.xml`, `zones/teleportzones.xml`, the NPC, item and skill
templates). Geodata for 19_25 / 20_25 comes from the same pack (see
`tools/geodata/README.md` in the server repository).

## Server datapack (l2horizon-server)

- `data/npc/18500-18599.xml`, `18900-18999.xml`, `22300-22399.xml`,
  `25900-25999.xml`, `34100-34199.xml`, `34200-34299.xml`: the 31 NPC
  templates. Level 85 monsters Twilight Witch / Warrior (22313-22314),
  Demonic Wizard / Warrior (22315-22316), Old / Hunting / Bloody / Rueful
  Vampire (22317-22320), Steel Warrior / Worker / Stalker / Swordsman
  (22336-22339), Zamad, Vegskytt, Shisuck, Drayzak, Beleth' Eye (22340-22344),
  Otherworldly Shard (18554), the invisible marker 18919; raid bosses Deiman
  (25933), Satina (25934), Ryuminir (25936), Aizen Kelsour (25937); the Elite
  Wizards of the Ivory Tower Camp (34190, 34191, 34198-34200), Mastie (34192),
  Creanir (34193) and the Warp Gate (34201). Stats, skills, drops and spoils
  are the Vanguard values; Mobius per-item drop chances became one
  RATED_GROUPED group per item. Essence Asofe / Thons (92994 / 92995) are
  the Classic items of the same name (4043 / 4044). The Elite Wizards are
  plain NPCs here (Vanguard marks them attackable Folk, which would give free
  XP), and the raids have no XP reward because the Vanguard templates carry
  none.
- `data/spawn/hellbound.xml`: the 1539 fixed spawns (respawn 60 s). The
  Vanguard file also spawns Deiman once more with a 60 s respawn; that line
  is left out because the raid script owns him.
- `data/items/94700-94799.xml`: Hellbound Ring (94718, M. Def. 44, passive
  50468) and Scroll: Enchant Hellbound Ring (94719); `enchant_items.xml`
  restricts the scroll to the ring, +0 to +10, with the Vanguard
  HELLBOUND_RING rates (100 90 80 60 50 40 30 20 10 5), failure destroys the
  ring. `data/items/95500-95599.xml` + `capsule_items.xml`: Adena Pouch
  (95569, 10k-300k adena).
- `data/stats/skills/50400-50499.xml`: Hellbound Ring passive 50468 with all
  11 Essence levels (paralysis resistance, Max HP, P./M. Atk., DEX/WIT); the
  core cannot raise an item skill per enchant level (only `enchant4_skill`
  exists), so the ring grants level 1 at every enchant and the enchant adds
  the usual M. Def. bonus. `14700-14799.xml` and `48000-48099.xml`: the
  marker passives Blood Siphon Resistance and Fire / Wind / Earth Attribute
  Monster used by the templates.
- Not ported, since the systems do not exist in this core: the craft-point
  materials 92915 / 92916 / 92999 (Bag of Low-grade Stuff, Animal Spirit,
  High-grade Resources), Talisman of Hellbound Fragment (94716) and
  Hellbound Energy (94717). They are dropped from the reward lists.
- Entry: Gatekeeper Tamil in Oren (30576) got `Hellbound Island (Lv. 85+)`
  (7730 250566 -1800, the Ivory Tower Camp, 20000 adena, `min_level` 85;
  strings `Gatekeeper.HellboundIsland` in `data/string`). Essence sends
  players there through its teleport UI (TeleportListData 438-442).
- `html-*/default/34201.htm`: the Warp Gate, `scripts_Util:Gatekeeper` to
  Satina's Laboratory (8884 242700 -2292), Beleth's Magic Circle
  (4361 239303 -3032) and the Otherworldly Tower East / South
  (21839 253159 -1756 / 14960 250112 -1593).
- `data/zone/dummy.xml`: `[hellbound_tp_1]`..`[hellbound_tp_4]`, the four
  portal spots in the Ivory Tower basement (400x400 around the Vanguard
  cylinder centres, z -3600..-3300).

## Extension scripts

- `ai.hellbound.HellboundRaids`: spawns each raid boss at one of its two
  Vanguard spots and, on death, at a random spot again after 120 minutes
  (Ryuminir 60), like the Vanguard Deiman / Satina / Ryuminir / Aizen AIs.
  Spawned with `NpcUtils.spawnSingle`, so a restart respawns them at once.
- `services.HellboundPortal`: `OnZoneEnterLeaveListener` on the four portal
  zones; on Saturdays a level 85+ character entering one is moved to the
  matching camp spot (6736 251024 -1795 etc.), like the Vanguard
  `IvoryTowerTeleportZones` + TeleportZone pair.

## Client

The NPCs, the ring, the scroll, the pouch and skill 50468 need their rows in
the Classic dat files (NpcName / Npcgrp, ItemName / Armorgrp / EtcItemgrp,
SkillName / Skillgrp) and the NPC model packages; all of it comes from the
Vanguard client the maps were taken from. The Essence-flavored dat exports
merged earlier predate Vanguard (22313 is still Garden Stakato there), so
the rows must be exported from the Vanguard client itself.
