# Buff Store - Private Store (Buff), store type 10

Players sell their own buffs the way they sell items: `/buff` opens the stock
private-store setup window filled with the buffs the player has learned, each buff
gets a price, Start sits the player down with a light-blue `<Private Store - Buff>`
bubble, and buyers double-click the seller, pick buffs in the stock buy window and
pay adena. The seller casts every bought buff on the buyer at the seller's learned
level (skill enchants included). Supply is unlimited and nothing touches the
inventory: the buffs travel through the stock private-store packets as **dummy buff
items** `81000-81499`, which exist only as item templates and client rows.

The client side (store type 10 in `UserInfo`/`CharInfo`, the bubble, the window
titles, `/buff` = user command 100, the dummy id range) is a client patch owned by
the server admin; this document is the server side that fulfils that contract.

## Files

Server datapack (`l2horizon-server`):

| File | Content |
|---|---|
| `gameserver/data/buff_store.xml` | the buff table: dummy item -> buff skill, client name and icon, optional `level`, `minLevel`, `priceMin`, `priceMax` |
| `gameserver/data/items/81000-81099.xml`, `81100-81199.xml` | the dummy EtcItem templates (weight 0, price 0, not tradeable/droppable/sellable/destroyable/stackable), generated |
| `gameserver/config/custom/buffstore.properties` | switches and limits (below) |
| `gameserver/data/string/strings_en.properties`, `strings_ru.properties` | `buffstore.*` messages |
| `tools/client/gen_buff_store_rows.py` | regenerates the item templates and the client rows from `buff_store.xml` |
| `tools/client/item_rows/ItemName_Classic.rows.txt`, `EtcItemgrp_Classic.rows.txt` | the client rows of the dummy items (name = buff name, icon = skill icon, `action_none`), merged with `tools/client/merge_item_rows.py` |

Extension (`CustomQuestsExt`, package `com.l2horizon.CustomQuestsExt.buffstore`):

| Class | Role |
|---|---|
| `BuffStoreConfig` | `config/custom/buffstore.properties` |
| `BuffStoreTable` | `data/buff_store.xml`, validated against the item and skill tables at load |
| `BuffStore` | per-player state: entries, bubble title, setup-pending flag; persisted in the player variables `buffstore` / `buffstoretitle` |
| `BuffStoreManager` | the whole flow: setup window, Start, Stop, Message, buyer window, purchase, store-type listener, login restore, bubble replay |
| `BuffStoreUserCommand` | `/buff` = user command 100 |
| `BuffStoreOfflineCommand` | wraps the core `.offline` command so `BuffStoreAllowOffline` can forbid offline buff stores |
| `BuffStoreManageListSell`, `BuffStoreListSell`, `BuffStoreMsgSell` | the stock `PrivateStoreManageListSell` (0xA0), `PrivateStoreListSell` (0xA1) and `PrivateStoreMsgSell` (0xA2) layouts fed with synthetic entries (objectId = itemId = dummy id, count 1, store price 0) |

Shadowed core packets (`l2.gameserver.network.l2.c2s`, stock logic recreated from the
decompiled originals plus the buff path): `SetPrivateStoreSellList`,
`RequestPrivateStoreQuitSell`, `SetPrivateStoreMsgSell`, `SetPrivateStoreWholeMsg`,
`RequestPrivateStoreBuy`, `Action`. The core has no hook for these opcodes, so the
classes are replaced on the classpath; the item-store behaviour of each is unchanged.

## Flow

| Step | Client packet | Server |
|---|---|---|
| `/buff` | `RequestUserCommand(100)` | `openSetup`: closes any open store (like the stock "Private Store - Sell" action), runs the stock `TradeHelper.checksIfCanOpenStore` checks, sends the manage list: section A = learned buffs of the table, section B = the saved entries with prices |
| Start | `SetPrivateStoreListSell` with dummy ids | `start`: every id must be a table entry the player has learned, count 1, price within the limits, no duplicates, at most `privateStoreSellLimit` entries; then the entries are persisted, the store type becomes 10 (the core sits the player, stores `storemode=10`, broadcasts CharInfo) and `ExPrivateStoreSetWholeMsg` carries the bubble text to everyone including the seller |
| Stop / ESC / X | `RequestPrivateStoreQuitSell` | `quit`: closes a buff store (the core stands the player up); silent when nothing is open |
| context menu close | `RequestActionUse` (Private Store - Sell) | the core resets the type and sends its item manage list; the store-type listener sees the reset came from `RequestActionUse` and re-sends the buff setup window with the previous entries once the stand-up finished |
| Message | `SetPrivateStoreMsgSell` (29 chars) | `setTitle`: stored, echoed back as `PrivateStoreMsgSell` for the prefill, broadcast as `ExPrivateStoreSetWholeMsg` while the store is open |
| buyer double-click | `Action` | targeted seller with type 10: in range -> `PrivateStoreListSell` layout with the entries; out of range -> the core walks the buyer there and the window opens on arrival |
| Buy | `RequestPrivateStoreBuy` | `buy`: seller still type 10 and within 200, both alive, every objectId a listed entry with count 1 and the listed price, sum with overflow check, adena moved (optional tax), `skill.getEffects(seller, buyer)` per buff at the seller's learned level (or the table's fixed `level`), cast animation, messages to both sides, `log/buffstore` line; the store stays open |

Bubble replay: the core re-sends store messages on visibility only for its own store
types, so a task (`BuffStoreBubbleRefreshSeconds`) sends `ExPrivateStoreSetWholeMsg`
to every player who newly sees an open buff store.

Login and offline trade: the core restores `storemode=10` itself (the player logs in
sitting); the entries and title come from the player variables, so the store re-opens
with its list (`BuffStoreRestoreOnLogin`) or is closed if the seller no longer knows
any listed buff. `.offline` works like an offline item store when
`BuffStoreAllowOffline` is on: the offline seller keeps casting and receives the
adena.

Cleanup: death, teleport, logout, Olympiad and GM commands reset the store type
through the core, which stands the player up; the listener drops the pending setup
state. Nothing else is needed because every buff store rule keys on the store type.

## Configuration (`config/custom/buffstore.properties`)

| Key | Default | Meaning |
|---|---|---|
| `BuffStoreEnabled` | True | master switch; off: `/buff` answers with a message, open stores close at login |
| `BuffStoreMinPrice` / `BuffStoreMaxPrice` | 1 / 1000000000 | global price limits per buff |
| `BuffStoreTaxPercent` | 0 | percentage of every sale removed as tax |
| `BuffStoreAllowOffline` | True | `.offline` allowed with a buff store open |
| `BuffStoreRestoreOnLogin` | True | re-open the store at login |
| `BuffStoreBubbleRefreshSeconds` | 2 | bubble replay interval |
| `BuffStoreCastAnimation` | True | `MagicSkillUse` on every sold buff |

The number of buffs a seller can list is the stock private store slot limit the
client reads from `UserInfo` (`MaxPvtStoreSlotsDwarf` / `MaxPvtStoreSlotsOther` in
the server config, 4 / 3 by default); raise those to allow longer buff lists.

## The buff list

103 original buffs (no Mass versions), dummy ids assigned in skill-id order:

* songs 264-270, 304-306, 308, 349, 363, 364 (Swordsinger / Sword Muse);
* dances 271-277, 307, 309-311, 365, 366 (Bladedancer / Spectral Dancer);
* orc chants and Pa'agrio blessings 1002-1010, 1249, 1250, 1251-1253, 1260, 1261,
  1282, 1284, 1308-1310, 1362-1365, 1390, 1391, 1413-1415 (Orc Shaman / Warcryer /
  Overlord / Doomcryer / Dominator);
* support buffs 1032-1048, 1059-1087, 1182-1204, 1232, 1238, 1240-1243, 1257, 1259,
  1268, 1303, 1304, 1307, 1311, 1352-1357, 1388, 1389, 1392, 1393, 1397, 1410 (Cleric,
  Prophet, Oracle, Elder, Shillien lines and their third classes).

Left out on purpose: `Pa'agrio's Fist` 1416 (a CP heal, not a buff), the instant or
10-30 second specials `Cleanse` 1409, `Mystic Immunity` 1411, `Celestial Shield`
1418, `Purification Field` 1425, `Miracle` 1426, `Flames of Invincibility` 1427, and
`Mana Gain` 1460, `Chant of Protection` 1461, `Improved Combat` 1499, which no class
of this server learns.

To add a buff: append a `<buff item="811xx" skill="..." name="..." icon="icon.skillNNNN"/>`
line to `buff_store.xml` (next free id, 81000-81499), run
`python3 tools/client/gen_buff_store_rows.py`, merge the client rows with
`tools/client/merge_item_rows.py` and ship the two client tables.
