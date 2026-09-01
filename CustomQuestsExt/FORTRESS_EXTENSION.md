# Fortress Extension

Server-side support for the 21 fortresses (residence ids 101-121) shipped in the
`l2horizon-server` datapack (branch `claude/lineage2-fortress-xml-files-2gvuuw`:
`data/residences/[101]..[121]`, fortress zones, siege events, flag poles).

The core `server.jar` of this build has no fortress implementation - `ResidenceType.Fortress`
exists, but there is no `Fortress` residence class and the fortress client packets are stubs.
This extension fills that gap. All recreated logic follows the L2Scripts High Five sources
(rev 2268, `Hl4p3x/L2Scripts_H5_2268` mirror), adapted where this core differs.

## New classes

| Class | Purpose |
|---|---|
| `l2.gameserver.model.entity.residence.Fortress` | The residence impl `ResidenceParser` instantiates for `impl="Fortress"` files. H5 logic: exclusive ownership (taking a fortress releases the clan's other fortress/castle), 7-day auto-expiry, reward cycle every 6h, facility/supply/contract state. |
| `l2.gameserver.dao.FortressDAO` | Persistence, H5 schema + `owner_clan_id`. Creates the `fortress` table on first use (see also `sql/install/fortress.sql` in the server repo). |
| `com.l2horizon.CustomQuestsExt.handlers.admin.FortressAdminCommand` | `//fortress`, `//fortress <id>`, `//fortress_set_owner <id> <clanName|npc>`. Registered in `CustomQuestsExt.onLoad()`. Needs `PlayerAccess.CanEditNPC` (same as `//residence`). |

## Core class overrides (classpath shadowing)

Recreated 1:1 from the decompiled originals (kept in `decompiled/*_decompiled.java`),
then extended with the stripped filling logic from the H5 sources:

| Class | Original behavior | Override adds |
|---|---|---|
| `s2c.ExShowFortressInfo` | always sent an empty list | default constructor fills from `ResidenceHolder` (owner name, held time) |
| `c2s.RequestAllFortressInfo` | read nothing, did nothing | responds with `ExShowFortressInfo` |
| `s2c.ExShowFortressMapInfo` | no filling constructor | constructor from `Fortress` (3/5 barracks, all intact) |
| `c2s.RequestFortressMapInfo` | read fort id, did nothing | responds with `ExShowFortressMapInfo` |

The Scripts engine deliberately skips `l2/gameserver/**` entries when scanning ext jars,
so these classes are served purely by the JVM classpath. For the override to win
deterministically, `CustomQuestsExt.ext.jar` must precede `server.jar` on the classpath -
the start scripts in `l2horizon-server` prepend it explicitly
(`-cp config:./CustomQuestsExt.ext.jar:./*`). The `Fortress`/`FortressDAO` classes are new
(no core counterpart), so they resolve regardless of ordering.

## Adaptations vs the H5 reference (documented deviations)

- Ownership is stored in `fortress.owner_clan_id`. This core's `clan_data` has no
  `hasFortress` column, `Clan` has no fortress accessors, and `ClanDataDAO` has no
  fortress owner query, so the H5 split (owner in `clan_data`) is not reproducible
  without overriding `Clan` itself.
- `Fortress.changeOwner()` stamps `own_date`/`last_siege_date` and restarts the hourly
  cycle task; in H5 the `FortressSiegeEvent` handled that.
- `Fortress.init()` tolerates a missing `residence_<id>` zone or siege event, so a jar
  deployed without the fortress datapack (or vice versa) cannot break
  `ResidenceHolder.callInit()` for the residences after it.
- The castle-contract branch of `chanceCycle()` is kept verbatim but is unreachable:
  contract state can only change through envoy NPCs, which this chronicle's datapack
  does not contain.

## What works / what does not

Works: fortresses load as first-class residences (zones bound, functions parsed,
restart/banish points active), DB persistence, admin granting, 7-day expiry,
client fortress status window (`RequestAllFortressInfo`) and fortress map view.

Not implemented (needs the fortress NPCs 35658+ and their AI/spawns, absent from this
chronicle's datapack): fortress sieges (`FortressSiege` events stay unloaded - the
EventParser logs one info line per fortress event file), facility managers, envoys,
support units, logistics rewards.

## Build

Compile `src` against `server.jar`/`scripts.jar` (Java 17), jar `bin` as
`CustomQuestsExt.ext.jar`. `quests/_350_EnhanceYourWeapon` and `AutoLootExtension`
are kept out of the shipped jar, matching the deployed one (quest 350 already lives
in `scripts.jar`; AutoLootExtension force-toggles players' auto-loot).
