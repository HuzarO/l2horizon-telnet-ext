# GM and player commands of CustomQuestsExt

All admin commands need `PlayerAccess.Menu` (GM panel access); the core's
command dispatcher also requires `CanUseGMCommand`.

## Inventory

| Command | What it does |
|---|---|
| `//clearinv` | Deletes every item of **your own** inventory except the equipped ones: adena, consumables, quest items, everything not worn. It never looks at the target, the pet or the warehouse. Cursed weapons are left alone (their manager owns them). Each deleted stack is written to the item log as a `Delete`, and the command answers with the counts. There is no undo. |

## Server stages (`SERVER_STAGES.md`)

`//stage`, `//stageset <n>`, `//stageauto`, `//stagemultisell`, `//stagebands`,
`//stageinfo`; players: `.stage` / `.stages`.

## Hellbound (`HELLBOUND.md`)

`//hb`, `//hbinfo`, `//hbset <stage>`, `//hbadd <trust>`, `//hbsub <trust>`,
`//hbstage`, `//hbrespawn`, `//hbreset`, `//hbdoors`, `//hbflag`, `//hbtele`;
players: `.hellbound`.

## Fortresses (`FORTRESS_EXTENSION.md`)

`//fortress`, `//fortress_set_owner`.

## Multisell

`//multisell <listId>` opens any multisell list (stage versions apply).
