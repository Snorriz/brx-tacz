package me.wazup.battleroyalex.placeholderhooks;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import java.util.Iterator;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;

public class MVdWPlacholderHook {
   String pluginName = "battleroyalex";

   /**
    * Estrae il parametro dinamico da un placeholder MVdW del tipo
    * "battleroyalex_<marker><parametro>" (es. "..._arena_status_winter").
    * Prende l'occorrenza PIU' INTERNA del marker per evitare ambiguita' fra
    * "_arena_players_" e "_arena_max_players_". Ritorna null se assente.
    */
   private static String extractParam(String placeholder, String marker) {
      if (placeholder == null) {
         return null;
      }
      int idx = placeholder.lastIndexOf(marker);
      if (idx < 0) {
         return null;
      }
      return placeholder.substring(idx + marker.length());
   }

   public MVdWPlacholderHook() {
      final BattleRoyaleX var1 = BattleRoyaleX.getInstance();
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_players_count", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return String.valueOf(var1.players.size());
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_parties_count", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return String.valueOf(var1.parties.size());
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_lobby_players_count", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return String.valueOf(var1.lobbyPlayers.size());
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_arenas_count", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return String.valueOf(var1.arenas.size());
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_kits_count", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return String.valueOf(var1.kits.size());
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_bungee_arena_status", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            if (var1.isOneGamePerServer()) {
               Iterator var2 = var1.arenas.values().iterator();
               if (var2.hasNext()) {
                  Arena var3 = (Arena)var2.next();
                  return var3.state.toString();
               }
            }

            return "NoArena";
         }
      });
      // FIX: i placeholder dinamici per-arena (arena_status_<nome>, arena_players_<nome>,
      // arena_max_players_<nome>) erano presenti solo nell'hook PlaceholderAPI e mancavano
      // qui, rendendoli non risolvibili con MVdWPlaceholderAPI. Aggiunti con lookup sicuro.
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_arena_status_", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            // MVdW: per i placeholder con prefisso dinamico l'evento espone il valore
            // gia' risolto; se nullo (o non disponibile) estraiamo il parametro dal nome.
            String key = extractParam(var1x.getPlaceholder(), "_arena_status_");
            Arena arena = key != null && !key.isEmpty() ? var1.arenas.get(key.toLowerCase()) : null;
            return arena != null ? arena.state.toString() : "NoArena";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_arena_players_", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            String key = extractParam(var1x.getPlaceholder(), "_arena_players_");
            Arena arena = key != null && !key.isEmpty() ? var1.arenas.get(key.toLowerCase()) : null;
            return arena != null ? String.valueOf(arena.players.size()) : "NoArena";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_arena_max_players_", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            String key = extractParam(var1x.getPlaceholder(), "_arena_max_players_");
            Arena arena = key != null && !key.isEmpty() ? var1.arenas.get(key.toLowerCase()) : null;
            return arena != null ? String.valueOf(arena.maxPlayers) : "NoArena";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_modifier", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).modifier) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(
         var1,
         this.pluginName + "_arena",
         new PlaceholderReplacer() {
            public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
               return var1x.isOnline()
                  ? String.valueOf(
                     ((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).arena != null
                        ? ((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).arena.name
                        : ""
                  )
                  : "";
            }
         }
      );
      PlaceholderAPI.registerPlaceholder(
         var1,
         this.pluginName + "_party_leader",
         new PlaceholderReplacer() {
            public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
               return var1x.isOnline()
                  ? String.valueOf(
                     ((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).party != null
                        ? ((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).party.leaderName
                        : ""
                  )
                  : "";
            }
         }
      );
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_coins", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).getCoins(var1x.getPlayer())) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_kills", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).kills) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_deaths", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).deaths) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_projectiles_hit", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).projectiles_hit) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_games_played", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).games_played) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_normal_wins", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).normal_wins) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_plunder_wins", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).plunder_wins) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_blocks_broken", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).blocks_broken) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_blocks_placed", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).blocks_placed) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_player_exp", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).player_exp) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_player_rank", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).player_rank) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_selected_kit", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).selectedKit) : "";
         }
      });
      PlaceholderAPI.registerPlaceholder(var1, this.pluginName + "_selected_celebration", new PlaceholderReplacer() {
         public String onPlaceholderReplace(PlaceholderReplaceEvent var1x) {
            return var1x.isOnline() ? String.valueOf(((PlayerData)var1.playerData.get(var1x.getPlayer().getName())).selectedCelebration) : "";
         }
      });
   }
}
