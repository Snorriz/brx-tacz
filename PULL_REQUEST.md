# BattleRoyaleX 4.5 + supporto armi TaCZ (Timeless and Classy Guns)

## Riepilogo
Aggiunge al plugin decompilato BattleRoyaleX la compatibilità con le armi della mod Forge
**TaCZ**, oltre a quelle CrackShot esistenti, per l'esecuzione su **ArcLight 1.20.1 (Forge 47.x)**.

## Modifiche principali
- `Utils.java`: hook TaCZ in **pura reflection** (nessuna dipendenza esterna): parsing token
  `TACZ:<ns>:<gun>[:ammo]`, `TIMELESS:...`, `TAKECHAIMSGUNS:...`; calcolo munizioni dal GunData;
  post-processing FireMode.SEMI + colpo in canna; init in `onEnable()` (`initTaczHook`).
- Fix **ClassCastException NMS→Bukkit** sotto ArcLight: conversione degli ItemStack restituiti
  dall'API TaCZ via `CraftItemStack.asBukkitCopy` risolto con il classloader del server.
- Fix crash `NoSuchElementException` su token sconosciuti nel parser chest.
- Fix ~100 errori di decompilazione (tipi raw, eccezioni controllate, stub soft-dependency).
- Jar finale completo di tutte le risorse (`custom-recipes-defaults.yml`, `chests-default.yml`,
  `Plane.schem`, yml config, plugin.yml) → risolta NPE in `RecipeManager.<init>`.
- `stubs/`: classi di compilazione delle soft-dependency (escluse dal jar runtime).
- `README-BUILD-TACZ.md`: istruzioni build & deploy.

## Artifact
- `BattleRoyaleX/BattleRoyaleX-4.5-TACZ.jar` — pronto per `plugins/` su ArcLight 1.20.1.

## Setup richiesto
- ArcLight build `arclight-1.20.1-1.0.6-SNAPSHOT` (layer Bukkit su Forge classico, NON NeoForge), Java 17/21.
- Mod TaCZ per MC 1.20.1 in `mods/`. Nessuna "Plugin-Bridge" necessaria.

## Config (esempi)
```yaml
- 'TACZ:tacz:ak47 : 1 :: 15%'
- 'TACZ:tacz:glock_17 : 1 :: 12%'
- 'TACZ:tacz:9mm : 1 :: 20%'          # munizioni (accettato anche TACZAMMO:)
```

## Verifica
Avvio senza stacktrace e log: `Successfully hooked into Timeless and Classy Guns (TaCZ) API.`
