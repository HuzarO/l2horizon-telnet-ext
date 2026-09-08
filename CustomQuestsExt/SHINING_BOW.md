# Shining Bow

The Shining Bow (6368, S grade; 6593 / 6594 / 6595 with a special ability) shipped in
the game files without a source: its recipes (6889 60%, 6890 100%) and its part, the
Shining Bow Shaft (6692), were dropped by nothing and sold by nobody, on retail High
Five as well as here. It is now obtainable exactly where the Draconic Bow (7575,
shaft 7579, recipe 7580) is, with the same chances.

## Datapack (`HuzarO/l2horizon-server`)

* Spoil (`data/npc`): Derek 18465, Varka's Head Magus 21371, Triol's High Priest
  22171 and Desert Scorpion 22335 spoil the Shining Bow Shaft with the Draconic Bow
  Shaft's chance (an extra line in the sweep list, independent rolls).
* Drops (`data/npc`): every grouped drop that holds the Draconic Bow or its shaft got
  a second group holding the Shining copies. The new group's chance is the old
  group's chance times the share the Draconic entries had in it, and the entries are
  renormalised inside, so P(Shining Bow) = P(Draconic Bow) and P(Shining Bow Shaft)
  = P(Draconic Bow Shaft) exactly, and the Draconic odds are untouched: Storm
  Winged Naga 25229, Last Lesser Giant Glaki 25245, Lilith 25283, Shadow of Halisha
  25342, Typhoon 25539, Antharas (29019, 29066-29068), Baium 29020, Valakas 29028,
  Scarlet van Halisha 29047, Andreas Van Halter 29062, Lost Captain 29144.
* Gather the Flames (`multisell/quests/_617_GatherTheFlames/32049.xml`): Recipe:
  Shining Bow (60%) for 1200 Vacualite Floating Stones, next to the Draconic one.
* Blacksmith of Mammon (`multisell/3112601.xml`) and the other special ability
  removal list (`multisell/4002.xml`): the three Shining Bow special-ability versions
  turn back into the plain bow, like the Draconic ones. The variation lists and the
  Mammon "bestow" list already knew the bow.
* `capsule_items.xml`: the box 7629 that can hold a Draconic Bow Shaft (9%) can hold
  a Shining Bow Shaft at the same chance.
* Four Goblets dialog (`html-en|ru/quests/_620_FourGoblets/wigoth_ghost_b_q0620_16.htm`):
  the Ghost of Wigoth's recipe list has "Recipe: Shining Bow (60%)"
  (`reply_shining_bow`).

## Extension (`src/quests`)

Three scripts.jar quests are shadowed (the decompiled originals with the changes
marked `l2horizon`):

* `_617_GatherTheFlames`, `_619_RelicsOfTheOldEmpire`: the random 60% recipe reward
  (1000 stones / relic parts) can be Recipe: Shining Bow; the pick stays uniform, so
  the Shining and the Draconic recipe have the same chance (1 in 11 now instead of
  1 in 10 for every recipe).
* `_620_FourGoblets`: the sealed box tables that hold the Draconic Bow Shaft (101 of
  a 1000-wide roll) hold the Shining Bow Shaft with the same weight; the roll is
  1101 wide now, so each other part is a little less likely. The Ghost of Wigoth
  exchanges 1000 Broken Relic Parts for the Shining Bow recipe (`reply_shining_bow`).

Not touched: the GM shop and the Test Server Helper lists already had the bow; the
orphan buy lists 5110 / 5206 (no NPC) are left alone. The client already has every
Shining Bow item.
