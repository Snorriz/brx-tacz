package me.wazup.battleroyalex.placeholderhooks;

import java.util.Iterator;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import org.bukkit.entity.Player;

public class PlaceholderAPIHook extends PlaceholderExpansion {
   public boolean persist() {
      return true;
   }

   public boolean canRegister() {
      return true;
   }

   public String onPlaceholderRequest(Player var1, String var2) {
      BattleRoyaleX var3 = BattleRoyaleX.getInstance();
      if (var2.equals("players_count")) {
         return String.valueOf(var3.players.size());
      }

      if (var2.equals("parties_count")) {
         return String.valueOf(var3.parties.size());
      }

      if (var2.equals("lobby_players_count")) {
         return String.valueOf(var3.lobbyPlayers.size());
      }

      if (var2.equals("arenas_count")) {
         return String.valueOf(var3.arenas.size());
      }

      if (var2.equals("kits_count")) {
         return String.valueOf(var3.kits.size());
      }

      if (var2.contains("arena_status_")) {
         String var10 = var2.replace("arena_status_", "");
         return var3.arenas.containsKey(var10.toLowerCase()) ? ((Arena)var3.arenas.get(var10.toLowerCase())).state.toString() : "NoArena";
      }

      if (var2.contains("arena_players_")) {
         String var9 = var2.replace("arena_players_", "").toLowerCase();
         Arena var12 = (Arena)BattleRoyaleX.getInstance().arenas.get(var9);
         if (var12 != null) {
            int var13 = var12.players.size();
            return String.valueOf(var13);
         } else {
            return "NoArena";
         }
      } else if (var2.contains("arena_max_players_")) {
         String var8 = var2.replace("arena_max_players_", "").toLowerCase();
         Arena var11 = (Arena)BattleRoyaleX.getInstance().arenas.get(var8);
         if (var11 != null) {
            int var6 = var11.maxPlayers;
            return String.valueOf(var6);
         } else {
            return "NoArena";
         }
      } else if (var2.equals("bungee_arena_status")) {
         if (var3.isOneGamePerServer()) {
            Iterator var7 = var3.arenas.values().iterator();
            if (var7.hasNext()) {
               Arena var5 = (Arena)var7.next();
               return var5.state.toString();
            }
         }

         return "NoArena";
      } else if (var1 == null) {
         return "";
      } else {
         PlayerData var4 = (PlayerData)var3.playerData.get(var1.getName());
         if (var2.equals("arena")) {
            return String.valueOf(var4.arena != null ? var4.arena.name : "");
         } else if (var2.equals("modifier")) {
            return String.valueOf(var4.modifier);
         } else if (var2.equals("party_leader")) {
            return String.valueOf(var4.party != null ? var4.party.leaderName : "");
         } else if (var2.equals("coins")) {
            return String.valueOf(var4.getCoins(var1));
         } else if (var2.equals("kills")) {
            return String.valueOf(var4.kills);
         } else if (var2.equals("deaths")) {
            return String.valueOf(var4.deaths);
         } else if (var2.equals("games_played")) {
            return String.valueOf(var4.games_played);
         } else if (var2.equals("projectiles_hit")) {
            return String.valueOf(var4.projectiles_hit);
         } else if (var2.equals("normal_wins")) {
            return String.valueOf(var4.normal_wins);
         } else if (var2.equals("plunder_wins")) {
            return String.valueOf(var4.plunder_wins);
         } else if (var2.equals("player_exp")) {
            return String.valueOf(var4.player_exp);
         } else if (var2.equals("player_rank")) {
            return String.valueOf(var4.player_rank);
         } else if (var2.equals("selected_kit")) {
            return String.valueOf(var4.selectedKit);
         } else if (var2.equals("blocks_broken")) {
            return String.valueOf(var4.blocks_broken);
         } else {
            return var2.equals("blocks_placed") ? String.valueOf(var4.blocks_placed) : "Unknown";
         }
      }
   }

   public String getAuthor() {
      return BattleRoyaleX.getInstance().getDescription().getAuthors().toString();
   }

   public String getIdentifier() {
      return "battleroyalex";
   }

   public String getVersion() {
      return BattleRoyaleX.getInstance().getDescription().getVersion();
   }
}
