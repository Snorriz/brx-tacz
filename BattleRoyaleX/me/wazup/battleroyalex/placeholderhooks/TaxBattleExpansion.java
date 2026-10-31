package me.wazup.battleroyalex.placeholderhooks;

import java.util.Iterator;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Espansione PlaceholderAPI per TaxBattle (fork BattleRoyaleX).
 *
 * NOTE STORICHE: questa classe usava il metodo deprecato
 * {@code onPlaceholderRequest(Player, String)} di PlaceholderAPI 2.x vecchia.
 * Su PlaceholderAPI >= 2.11.2 quel metodo e' stato RIMOSSO dal superclass
 * {@link PlaceholderExpansion}: la vecchia firma non sovrascrive piu' nulla,
 * l'espansione risultava registrata ma restituiva sempre "" / null per ogni
 * token %battleroyalex_*% / %taxbattle_*. Fix: override del moderno
 * onRequest(OfflinePlayer, String) + normalizzazione del parametro.
 */
public class TaxBattleExpansion extends PlaceholderExpansion {

   @Override
   public boolean persist() {
      // Non e' richiesto da PAPI >= 2.11: la persistenza fra reload e' garantita
      // dal manager delle espansioni. Mantenuto come no-op documentativo.
      return true;
   }

   @Override
   public String getIdentifier() {
      return "taxbattle";
   }

   @Override
   public String getAuthor() {
      return String.join(", ", BattleRoyaleX.getInstance().getDescription().getAuthors());
   }

   @Override
   public String getVersion() {
      return BattleRoyaleX.getInstance().getDescription().getVersion();
   }

   /**
    * Risolve un placeholder. Il parametro arriva senza il prefisso
    * "%taxbattle_" e senza il suffisso "_%", in minuscolo.
    */
   @Override
   public String onRequest(OfflinePlayer player, String params) {
      BattleRoyaleX plugin = BattleRoyaleX.getInstance();
      if (plugin == null || params == null) {
         return "";
      }

      // Normalizzazione: some callers/configs pass the raw inner text with case
      // variations or a trailing/leading underscore. Canonicalize once.
      String key = params.trim().toLowerCase();

      // ---- contatori globali ----
      switch (key) {
         case "players_count":
            return String.valueOf(plugin.players.size());
         case "parties_count":
            return String.valueOf(plugin.parties != null ? plugin.parties.size() : 0);
         case "lobby_players_count":
            return String.valueOf(plugin.lobbyPlayers.size());
         case "arenas_count":
            return String.valueOf(plugin.arenas.size());
         case "kits_count":
            return String.valueOf(plugin.kits != null ? plugin.kits.size() : 0);
         default:
            break;
      }

      // ---- placeholders dinamici per arena ----
      // Usiamo startsWith (non contains!) per non catturare token malformati.
      if (key.startsWith("arena_status_")) {
         String arenaName = key.substring("arena_status_".length());
         Arena arena = arenaName.isEmpty() ? null : plugin.arenas.get(arenaName);
         return arena != null ? arena.state.toString() : "NoArena";
      }
      if (key.startsWith("arena_max_players_")) {
         String arenaName = key.substring("arena_max_players_".length());
         Arena arena = arenaName.isEmpty() ? null : plugin.arenas.get(arenaName);
         return arena != null ? String.valueOf(arena.maxPlayers) : "NoArena";
      }
      if (key.startsWith("arena_players_")) {
         String arenaName = key.substring("arena_players_".length());
         Arena arena = arenaName.isEmpty() ? null : plugin.arenas.get(arenaName);
         return arena != null ? String.valueOf(arena.players.size()) : "NoArena";
      }

      if (key.equals("bungee_arena_status")) {
         if (plugin.isOneGamePerServer()) {
            Iterator<Arena> it = plugin.arenas.values().iterator();
            if (it.hasNext()) {
               return it.next().state.toString();
            }
         }
         return "NoArena";
      }

      // ---- placeholders per-giocatore ----
      Player online = player != null ? player.getPlayer() : null;
      if (online == null) {
         return "";
      }
      PlayerData data = plugin.playerData.get(online.getName());
      if (data == null) {
         return "";
      }

      switch (key) {
         case "arena":
            return data.arena != null ? data.arena.name : "";
         case "modifier":
            return String.valueOf(data.modifier);
         case "party_leader":
            return data.party != null && data.party.leaderName != null ? data.party.leaderName : "";
         case "coins":
            return String.valueOf(data.getCoins(online));
         case "kills":
            return String.valueOf(data.kills);
         case "deaths":
            return String.valueOf(data.deaths);
         case "games_played":
            return String.valueOf(data.games_played);
         case "projectiles_hit":
            return String.valueOf(data.projectiles_hit);
         case "normal_wins":
            return String.valueOf(data.normal_wins);
         case "plunder_wins":
            return String.valueOf(data.plunder_wins);
         case "player_exp":
            return String.valueOf(data.player_exp);
         case "player_rank":
            return String.valueOf(data.player_rank);
         case "selected_kit":
            return data.selectedKit != null ? data.selectedKit : "";
         case "selected_celebration":
            return data.selectedCelebration != null ? data.selectedCelebration : "";
         case "blocks_broken":
            return String.valueOf(data.blocks_broken);
         case "blocks_placed":
            return String.valueOf(data.blocks_placed);
         default:
            return "Unknown";
      }
   }
}
