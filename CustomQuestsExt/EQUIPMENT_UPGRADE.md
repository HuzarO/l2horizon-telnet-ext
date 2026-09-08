# Equipment upgrade (no-grade and D-grade)

Every equipment merchant of l2horizon offers "Upgrade equipment": hand in a
weapon, armor piece, shield or jewel, pay the difference of the shop prices and
take the next item of the same kind. It replaces the Classic novice exchange
(the `Exchange Equipment` link that opened multisell 6001 for characters below
level 25) and follows the Lu4 lists, masterwork.wiki post 364 "Ng/D Equipment
upgrade": the same chains, the same rules, prices from this server's own item
data.

## Rules

* No-grade: the fee is exactly `price(new) - price(old)`, and no castle tax is
  charged. Buying a Short Sword and upgrading it step by step to a Falchion
  costs the same as buying the Falchion.
* D-grade: the fee is `(price(new) - price(old)) * 1.06`, rounded up to 10
  adena, and the castle tax applies. Step-by-step is slightly dearer than
  buying the final item, as on Lu4 (their D rows carry a 6% markup).
* Armor and jewelry stay within their grade. A top no-grade weapon becomes a
  low D-grade weapon only through the Weapon Upgrade Coupon (below).
* Only the upgrades for the items in the inventory are listed
  (`showall="false"`); the enchantment of the old item is not kept
  (`keepenchanted="false"`).
* Every (old, new) pair is its own entry priced on its own prices. Lu4 charges
  one price per row (that of the cheapest "give" item); pricing each pair on
  its own is never cheaper than the difference, so the adena checks below hold
  for every entry.

## Why it cannot print adena

Merchants buy items back at `referencePrice / ShopRefundSellDivisor` (2). With
a fee of at least `price(new) - price(old)`:

* shop -> upgrade -> shop: `price(old) + fee >= price(new) > price(new) / 2`;
* drop -> upgrade -> shop: `price(new) / 2 - fee <= price(old) - price(new) / 2
  < price(old) / 2`, so selling the drop directly is always better than
  upgrading it first;
* crystallizing the new item is worth even less than selling it (D crystals
  are 650 adena reference, 325 when sold).

The only "free" step is the coupon exchange, one per character.

## The lists

| Multisell | Content | Entries | Tax |
|---|---|---|---|
| 6002 | no-grade weapons (physical and magic) | 64 | no |
| 6003 | no-grade armor, helmets, gloves, boots, shields | 54 | no |
| 6004 | no-grade necklaces, earrings, rings | 17 | no |
| 6005 | D-grade weapons | 138 | yes |
| 6006 | D-grade armor, helmets, gloves, boots, shields | 99 | yes |
| 6007 | D-grade jewelry | 12 | yes |
| 6008 | top no-grade weapon + Weapon Upgrade Coupon -> low D-grade weapon | 121 | - |

The chains are `tools/upgrade/upgrades.json` in the server repo (42 sections,
182 rows, 309 items; a row's "give" items are alternatives, so are its "get"
items) and `tools/upgrade/gen_upgrade_multisells.py` writes the seven files
from them and the item prices:

    python3 tools/upgrade/gen_upgrade_multisells.py

Re-run it after changing an item price or a chain. The lists carry a comment
per entry with both prices and the fee. Odd corner kept from Lu4: Scalpel
(845,000) is listed as a way to a Tomahawk (644,000); on this server that entry
costs nothing, it is a downgrade.

## Where

* `gameserver/data/html-en|ru/merchant/upgrade_equipment.htm` is the menu (the
  rules in one paragraph and the six lists); all 68 equipment merchants (weapon,
  armor and accessory merchants of every town and village, the Ivory Tower and
  Hunters Village traders, Cema and Galman) link it as "Upgrade equipment",
  in place of the old `Exchange Equipment` link where there was one, and the
  `-1` purchase sub-pages too. The core's `_Exchange` bypass and multisell 6001
  still exist but nothing links them.
* The seven Newbie Guides (30598-30602, 31076, 31077) got the coupon exchange
  link on their first page.

## Weapon Upgrade Coupon (item 40110)

`com.l2horizon.CustomQuestsExt.upgrade.WeaponUpgradeCoupon` (a ScriptFile
registering itself as a global player listener, no core class shadowed) gives
one coupon when the base class takes its 1st class transfer (OnSetClass), on
any path: village master, community board class master. Characters that had
their 1st class before the coupon existed and have not taken the 2nd class get
it on their next login. The character variable `l2h_weapon_upgrade_coupon`
makes it one per character; the message is the string
`l2horizon.upgrade.coupon` (en, ru).

The coupon cannot be traded, dropped or sold, can be stored. With any of the
11 top no-grade weapons (Falchion, Zweihander, Composite Bow, Flanged Mace,
Iron Hammer, Viper Fang, Sword Breaker, Great Spear, Mage Staff, Crucifix of
Blessing, Voodoo Doll) a Newbie Guide gives any of the 11 low D-grade weapons
(Saber, Heavy Sword, Strengthened Bow, Work Hammer, Hand Axe, Assassin Knife,
Bagh-Nakh, Trident, Bone Staff, Branch of Life, Priest Sword), all 409,000
adena items, for no adena.

Client: the coupon's ItemName and EtcItemgrp rows are in
`tools/client/item_rows/` (EtcItemgrp copied from the Classic Weapon Exchange
Coupon 49859); nothing else on the client changes, all 309 list items are in
the Classic tables.
