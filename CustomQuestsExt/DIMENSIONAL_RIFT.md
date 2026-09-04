# Dimensional Rift: the Legend tier

The Recruit rift (area type 1, the level 30 tier) is replaced by the **Legend**
tier, a level 85 tier above Hero. The other five tiers are untouched.

## What changed

| Part | Where |
|------|-------|
| Entry NPC | 31488 is *Rift Post Legend* (server template and client `NpcName`); it still sends `EnterRift 1` |
| Cost | `RecruitFC = 36` fragments (Hero is 33); `RiftMaxPartiesRecruit` limits the Legend rifts |
| Rooms | `gameserver/data/dimensional_rift.xml`, area type 1: rooms 1-8 spawn the Infernal Invaders in the Hero room layout, room 9 is Anakazel |
| Monsters | `gameserver/data/npc/40100-40199.xml`: 23 *Infernal Invader* templates (40100-40122) and Anakazel 40123 |
| Dialogs | `html-*/default/3148[89]-2.htm`, `3149[0-3]-2.htm` and `html-*/quests/_635_InTheDimensionalRift/5.htm` describe the tiers without the recruits |
| Boss room on the last jump | `src/l2/gameserver/model/entity/DimensionalRift.java` (this extension) |
| Generator | `tools/rift/gen_legend_rift.py` in the server repo (report next to it) |

## The override

The core `DimensionalRift.teleportToNextRoom()` sends only the tiers of type 5
and 6 (Commander, Hero) to room 9 on the last jump; every other tier only
finds the boss room by chance. The extension's copy of the class (the
extension jar precedes `server.jar` on the classpath) keeps the core logic and
adds type 1 to that rule (`endsInBossRoom()`), so a Legend party always ends
at Anakazel after `MaxRiftJumps` jumps. `DelusionChamber` extends this class
unchanged.

## The tier

Every Hero room template (21774-21796, level 78) was rebuilt at level 85 with
a Hellbound model, one step higher NPC skills, and the Hero drop and spoil
lists with every S-grade item replaced by its Dynasty counterpart:

* stats: HP x1.40, P./M. Atk. x1.30, P. Def. x1.15, M. Def. x1.20, exp x1.45,
  SP x1.40, adena x1.45 over the Hero template of the same room slot;
* models: Darion's Enforcer/Executioner, Blind Huntsman/Watchman, Junior
  Watchman/Summoner, Arcane Scout/Guardian/Watchman, Keltas, Quarry
  Foreman/Supervisor/Bowman, Remnant Wraith, Sand Devil, Desiccator, the four
  chimeras (Fire, Earth, Wind, Darkness, plus Celtus' skin), the Wandering
  Caravan as the "Food" chest, and the Dark Choir Archer for the archer (with a
  Dynasty Bow). No new client assets: the Hellbound rows are already there;
* Nightmare pieces feed the Dynasty heavy set, Majestic pieces the parts that
  mapping leaves out (gaiters, shoes, circlet, robe gloves, stockings), Draconic
  and Major Arcana pieces the light and robe sets, Tateossian and Majestic
  jewelry the sealed Dynasty jewelry, and each S-grade weapon its Dynasty
  weapon of the same kind (Saint Spear -> Halberd, Dragon Slayer -> Blade, Sword
  of Miracles -> Phantom, Soul Bow -> Bow, Elysian -> Cudgel, Branch of the
  Mother Tree -> Staff, Doom Crusher -> Crusher, Flaming Dragon Skull -> Mace,
  Angel Slayer -> Knife, Demon Splinter -> Bagh-Nakh...). Level 76 life stones
  become level 80 life stones. Enchant scrolls, arrows, chest keys, recipes and
  the herbs of the Food stay as they are (S80 uses S-grade consumables);
* Leonard, Adamantine and Orichalcum (the Dynasty recipe materials) take 30%
  of the biggest cheap material entry of each template's main group
  (Adamantine on weapon-part droppers, Orichalcum on armor-part droppers,
  Leonard on jewelry droppers); the three templates without a real material
  group got a 30% materials group of their own.

Anakazel 40123: level 85, 520,000 HP, P. Atk. 2,300, M. Atk. 1,400, P. Def.
1,180, M. Def. 590, boss skills one level higher, *Raid Boss - Level 85*, soul
crystal absorb `min_level="10" max_level="14"` (Hero: 10-13), Sealed Dynasty
jewelry and gemstones, Dynasty Halberd and its pieces, Blessed Scroll: Enchant
Armor (S), Blessed Scroll of Resurrection, and a guaranteed stack of Leonard
6-12 / Adamantine 8-16 / Orichalcum 8-16. The client row is the Hero Anakazel
model at `drawscale=1.2` (collision 30/108).
