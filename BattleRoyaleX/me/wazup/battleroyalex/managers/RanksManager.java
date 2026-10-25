package me.wazup.battleroyalex.managers;

import java.util.HashMap;
import java.util.List;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.utils.Enums.AchievementType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RanksManager {
   private static RanksManager instance;
   HashMap<Integer, Integer> ranks = new HashMap();

   public RanksManager() {
      instance = this;
      Config var1 = Config.getInstance();
      int var2 = var1.first_rank_exp;
      this.ranks.put(1, var2);

      for (int var3 = 2; var3 < var1.max_rank + 1; var3++) {
         int var4 = (int)(var2 * (var3 < 9 ? var1.rank_modifier + 0.2 : var1.rank_modifier));
         this.ranks.put(var3, var4);
         var2 = var4;
      }
   }

   public static RanksManager getInstance() {
      return instance;
   }

   public int getNextRankExp(PlayerData var1) {
      return var1.player_rank < Config.getInstance().max_rank ? (Integer)this.ranks.get(var1.player_rank + 1) - var1.player_exp : 0;
   }

   public void addExp(Player var1, int var2) {
      PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName());
      var3.player_exp += var2;
      if (this.getNextRankExp(var3) < 1 && var3.player_rank < Config.getInstance().max_rank) {
         var3.player_rank++;
         var1.sendMessage(((String)Customization.getInstance().messages.get("Player-Rank-Up")).replace("%rank%", String.valueOf(var3.player_rank)));
         AchievementsManager.getInstance().checkPlayer(var1, AchievementType.PLAYER_RANK, var3.player_rank);
         if (Config.getInstance().rankCommands.containsKey(var3.player_rank)) {
            for (String var5 : (List)Config.getInstance().rankCommands.get(var3.player_rank)) {
               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var5.replace("%player%", var1.getName()));
            }
         }
      }
   }

   public int getRank(PlayerData var1) {
      int var2 = 0;

      for (int var4 : this.ranks.keySet()) {
         if (var1.player_exp >= (Integer)this.ranks.get(var4)) {
            var2 = var4;
         }
      }

      return var2;
   }
}
