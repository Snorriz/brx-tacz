# TaxBattle 4.5 — BattleRoyaleX + TaCZ / Miklos's Military Armor / lrTactical

## Riepilogo
Ribrand completo del plugin BattleRoyaleX in **TaxBattle**, con supporto alle armi/mod Forge tramite reflection pura (zero dipendenze a compile-time), ottimizzato per **ArcLight 1.20.1 (Forge 47.x)**.

## Funzionalità aggiunte
- **Hook TaCZ** (Timeless and Classy Guns) via reflection: token `TACZ:<ns>:<gun>[:ammo]`, `TIMELESS:`, `TACZAMMO:`; munizioni calcolate dai gun-data, colpo in canna + FireMode.SEMI post-processing.
- **Parser generico item Forge**: `MM_ARMOR:` (Miklos's Military Armor), `LRTACTICAL:` (lrTactical melee), jolly `MOD:<namespace>:<id>`; risoluzione via registry con fallback null-safe (niente più crash su item non presenti).
- **Bridge NMS→Bukkit per ArcLight**: conversione `CraftItemStack.asBukkitCopy` risolta col classloader del server (fix ClassCastException ItemStack).
- **Hot-Zones**: pool loot "caldo" (no pistole/meele → fucili/cecchini/LMG) dentro raggi configurabili in `config.yml`; supply drop sempre hot; anti-duplicato armi/munizioni per chunk nelle casse vicine.
- **Kit unico "TaxBattle"**: Glock17 + 9mm + coltello tattico + mela d'oro + set armatura Miklos auto-indossata (`setArmorContents`).
- **Scoreboard "TaxPhobia"**: titolo gradient animato (16 frame esatti, intervallo 150 ms = 3 tick) da `config.yml` riga ~201 con supporto hex maiuscolo (`hexToLegacy`); righe timer tempesta stile Fortnite (⏳ Inizio/Fine prossima tempesta), 👥 vivi, ⚔ kills per giocatore.
- **Messaggi di morte custom IT**: `%killer% ha ucciso %victim% con un %weapon%` (categoria arma tradotta: fucile di precisione, mitragliatrice, ecc.), tutto rosso.
- **Traduzione italiana completa** dei messaggi console/GUI e rimozione funzioni party/amicizia.

## Fix
- NPE `RecipeManager` (risorse mancanti nel jar), NPE `Kit.java:66` (item null-safe), `NoSuchMethodException asItemStack()` (rimossa chiamata invalida), stub soft-dependency esclusi dal runtime.

## Artifact
- `TaxBattle.jar` (root) e `BattleRoyaleX/TaxBattle.jar` — md5 `0c14ad9e051b24a8565bbc4e77aa1b1d`, 214 classi reali, nessuna classe stub, tutte le yml incluse.
- Build: `javac --release 8` contro Paper API 1.20.1 + adventure/guava, exit 0.

## Setup richiesto
1. ArcLight **1.20.1-1.0.6-SNAPSHOT** (Forge 47.x, NON NeoForge) + Java 17/21.
2. Mod TaCZ 1.20.1, Miklos's Military Armor, lrTactical in `mods/`.
3. `TaxBattle.jar` in `plugins/`; verificare log: `Successfully hooked into Timeless and Classy Guns (TaCZ) API.`
4. Se esiste una vecchia `plugins/TaxBattle/config.yml`, copiare la nuova sezione `Scoreboard-Title-Animation` (il plugin non sovrascrive le config esistenti).
5. Comandi: `/taxbattle` (alias `/tb`, `/br`).

## Verifica
- Forzieri contengono armi TaCZ con munizioni corrette, senza stacktrace.
- Titolo scoreboard anima il gradiente TaxPhobia ogni 150 ms.
- Kill chat mostra la categoria arma in italiano.
