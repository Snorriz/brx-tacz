package me.wazup.battleroyalex.holograms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.Stat;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GeneralizedHologramsManager {
   public Location statsHologramLocation;
   public Location leaderboardHologramLocation;
   HashMap<UUID, Hologram> statHolograms = new HashMap();
   private Hologram leaderboardHologram;
   private GeneralizedHologramsManager.HologramProvider provider;

   public GeneralizedHologramsManager(GeneralizedHologramsManager.HologramProvider var1) {
      this.provider = var1;
   }

   public void setStatsLocation(Location var1) {
      this.statsHologramLocation = var1;
      if (var1 != null) {
         for (Player var7 : Utils.getPlayers(BattleRoyaleX.getInstance().players)) {
            Hologram var4 = (Hologram)this.statHolograms.get(var7.getUniqueId());
            if (var4 != null) {
               var4.teleport(var1);
            } else {
               PlayerData var5 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var7.getName());
               this.createStatsHologram(var7, var5);
            }
         }
      } else {
         for (Hologram var3 : this.statHolograms.values()) {
            var3.delete();
         }

         this.statHolograms.clear();
      }
   }

   public void createStatsHologram(Player var1, PlayerData var2) {
      if (this.statsHologramLocation != null) {
         Hologram var3 = (Hologram)this.statHolograms.get(var1.getUniqueId());
         List var4 = this.getStatLines(var1, var2);
         if (var3 == null) {
            var3 = this.createHologram(var1.getName() + "-PGStats", this.statsHologramLocation, var4);
            var3.showToOnePlayer(var1);
            this.statHolograms.put(var1.getUniqueId(), var3);
         } else {
            var3.updateLines(var4);
         }
      }
   }

   private Hologram createHologram(String var1, Location var2, List<String> var3) {
      Object var4 = null;
      if (this.provider == GeneralizedHologramsManager.HologramProvider.DecentHolograms) {
         var4 = new DecentHologramsHologram(var1, var2, var3);
      } else if (this.provider == GeneralizedHologramsManager.HologramProvider.HolographicDisplays) {
         var4 = new HolographicDisplaysHologram(var2, var3);
      }

      return (Hologram)var4;
   }

   public void deleteStatsHologram(Player var1) {
      Hologram var2 = (Hologram)this.statHolograms.get(var1.getUniqueId());
      if (var2 != null) {
         var2.delete();
         this.statHolograms.remove(var1.getUniqueId());
      }
   }

   public void setLeaderboardLocation(Location var1, boolean var2) {
      this.leaderboardHologramLocation = var1;
      if (var1 == null) {
         this.deleteLeaderboardHologram();
      } else if (this.leaderboardHologram != null) {
         this.leaderboardHologram.teleport(var1);
      } else {
         this.leaderboardHologram = this.createHologram(
            "PGLeaderboard", this.leaderboardHologramLocation, Collections.singletonList(ChatColor.AQUA + "Loading...")
         );
         if (var2) {
            BattleRoyaleX.getInstance().startLeaderboardUpdater();
         }
      }
   }

   public void updateLeaderboardHologram(HashMap<Stat, Entry<String, Integer>> var1) {
      List var2 = this.getLeaderboardLines(var1);
      if (this.leaderboardHologram != null) {
         this.leaderboardHologram.updateLines(var2);
      } else {
         this.leaderboardHologram = this.createHologram("PGLeaderboard", this.leaderboardHologramLocation, var2);
      }
   }

   public void deleteLeaderboardHologram() {
      if (this.leaderboardHologram != null) {
         this.leaderboardHologram.delete();
         this.leaderboardHologram = null;
      }
   }

   protected List<String> getLeaderboardLines(HashMap<Stat, Entry<String, Integer>> var1) {
      ArrayList var2 = new ArrayList();
      if (!Customization.getInstance().holographicLeaderboardHeader.isEmpty()) {
         var2.add(Customization.getInstance().holographicLeaderboardHeader);
      }

      for (Stat var4 : var1.keySet()) {
         Entry var5 = (Entry)var1.get(var4);
         var2.add(
            Customization.getInstance()
               .holographicLeaderboardBody
               .replace("%stat%", (CharSequence)Customization.getInstance().stats.get(var4.name()))
               .replace("%player%", (CharSequence)var5.getKey())
               .replace("%score%", String.valueOf(var5.getValue()))
         );
      }

      if (!Customization.getInstance().holographicLeaderboardFooter.isEmpty()) {
         var2.add(Customization.getInstance().holographicLeaderboardFooter);
      }

      return var2;
   }

   protected List<String> getStatLines(Player var1, PlayerData var2) {
      ArrayList var3 = new ArrayList();

      for (String var5 : Customization.getInstance().holographicStats) {
         var3.add(
            var5.contains("%")
               ? var5.replace("%player_name%", var1.getName())
                  .replace("%kills%", String.valueOf(var2.kills))
                  .replace("%coins%", String.valueOf(var2.getCoins(var1)))
                  .replace("%games_played%", String.valueOf(var2.games_played))
                  .replace("%deaths%", String.valueOf(var2.deaths))
                  .replace("%normal_wins%", String.valueOf(var2.normal_wins))
                  .replace("%plunder_wins%", String.valueOf(var2.plunder_wins))
                  .replace("%projectiles_hit%", String.valueOf(var2.projectiles_hit))
                  .replace("%player_exp%", String.valueOf(var2.player_exp))
                  .replace("%player_rank%", String.valueOf(var2.player_rank))
                  .replace("%blocks_placed%", String.valueOf(var2.blocks_placed))
                  .replace("%blocks_broken%", String.valueOf(var2.blocks_broken))
               : var5
         );
      }

      return var3;
   }

   public enum HologramProvider {
      HolographicDisplays,
      DecentHolograms;
   }
}
