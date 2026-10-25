# BattleRoyaleX 4.5 — TaCZ (Timeless and Classy Guns) Edition

## Cosa è stato aggiunto
- **Hook TaCZ in pura reflection** (`Utils.initTaczHook` / `Utils.getTaczItemStack`): nessuna
  dipendenza esterna a compile-time o runtime (nessuna "Plugin-Bridge", che non esiste).
  Il plugin funziona con le sole API pubbliche di TaCZ caricate via classloader della mod.
- Supporto CrackShot, QualityArmory ecc. **invariato**.

## Sintassi per config (chests.yml, shop.yml, kits.yml ...)
```yaml
TACZ:tacz:ak47 : 1 :: 15%          # TACZ:<namespace>:<gunId>
TIMELESS:m4a1 : 1                  # namespace di default "tacz"
TACZ:bbm:kar98k : 1                # add-on pack (bbm, lraps, ecc.)
TACZ:tacz:hk416:60 : 1             # con munizioni explicitate (senza = caricatore massimo)
```
Il colpo in canna (bullet-in-barrel) e la modalità SEMI per i bolt-action vengono applicati
automaticamente quando l'API lo consente.

## Versioni richieste per il server
| Componente | Versione |
|---|---|
| ArcLight | build **1.20.1** (per Forge classico, NON NeoForge — NeoForge solo da 1.21+) |
| Forge | 47.3.x (inclusa in ArcLight 1.20.1) |
| Java | 17 (o 21) |
| TaCZ | release per MC **1.20.1** (+ sue librerie/dipendenze) |
| Plugin | `BattleRoyaleX-4.5-TACZ.jar` in `plugins/` |

## Ricompilare da sorgenti
```bash
cd BattleRoyaleX
find me stubs -name "*.java" > /tmp/sources.txt
grep '^me/' /tmp/sources.txt > /tmp/real_sources.txt   # stubs = SOLO soft-deps fittizie, mai nel jar

javac -nowarn -proc:none --release 8 -d /tmp/out2 \
  -sourcepath "me:stubs" \
  -cp "paper-1.20.1.jar:guava.jar:jsr305.jar:bungeecord-chat.jar:adventure-api.jar:adventure-key.jar:adventure-text-minimessage.jar:examination-api.jar" \
  @/tmp/real_sources.txt

# Packaging: SOLO me/ + yml + plugin.yml
cd /tmp/out2 && rm -rf be com dev emanondev eu io javax net org shampaggon
cp /workspace/BattleRoyaleX/plugin.yml .
cp /workspace/BattleRoyaleX/*.yml .
jar cf BattleRoyaleX-4.5-TACZ.jar plugin.yml *.yml me
```

## Verifica all'avvio
Nel log del server deve comparire:
```
Successfully hooked into Timeless and Classy Guns (TaCZ) API.
```
Se compare `Could not hook to TaCZ...` la mod non è caricata (controlla cartella `mods/`).

## Test rapido
1. `/br reload` dopo aver modificato chests.yml con token `TACZ:`
2. Entra in arena, cerca un forziere → arma TaCZ con munizioni nel caricatore
3. Spara, ricarica, verifica pickup/death-drop
