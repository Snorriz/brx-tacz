package me.wazup.battleroyalex.managers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.SmartInventory;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.AchievementType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AchievementsManager {
   private static AchievementsManager instance;
   private HashMap<AchievementType, ArrayList<AchievementsManager.Achievement>> achievements = new HashMap();

   public AchievementsManager() {
      instance = this;
      FileConfiguration var1 = FilesManager.getInstance().getConfig("achievements.yml");

      for (AchievementType var5 : AchievementType.values()) {
         String var6 = "Achievements." + var5.name().toLowerCase();
         ArrayList var7 = new ArrayList();
         if (!var1.getConfigurationSection(var6).getKeys(false).isEmpty()) {
            for (String var9 : var1.getConfigurationSection(var6).getKeys(false)) {
               var6 = "Achievements." + var5.name().toLowerCase() + "." + var9 + ".";
               var7.add(
                  new AchievementsManager.Achievement(
                     Integer.valueOf(var9),
                     var1.getString(var6 + "description"),
                     var1.getString(var6 + "prize-description"),
                     var1.getString(var6 + "executed-command")
                  )
               );
            }
         }

         this.achievements.put(var5, var7);
      }

      List var10 = this.getAchievements();
      int var11 = 0;

      for (int var12 = 0; var12 < Math.ceil(Double.valueOf((double)var10.size()) / SmartInventory.slots.length); var12++) {
         for (int var17 : SmartInventory.slots) {
            if (var11 >= var10.size()) {
               break;
            }

            ((AchievementsManager.Achievement)var10.get(var11)).id = var12;
            ((AchievementsManager.Achievement)var10.get(var11)).slot = var17;
            var11++;
         }
      }
   }

   public static AchievementsManager getInstance() {
      return instance;
   }

   public SmartInventory getAchievements(PlayerData var1) {
      SmartInventory var2 = new SmartInventory((String)Customization.getInstance().inventories.get("Achievements-Inventory"));

      for (int var3 = 0; var3 < Math.ceil(Double.valueOf((double)this.getSize()) / SmartInventory.slots.length); var3++) {
         var2.addInventory(ChatColor.BLUE + "List #" + (var3 + 1));
         var2.setItem(var3, 49, (ItemStack)Customization.getInstance().items.get("Back"));
      }

      int[] var12 = new int[]{
         var1.games_played, var1.normal_wins, var1.plunder_wins, var1.kills, var1.projectiles_hit, var1.player_rank, var1.blocks_placed, var1.blocks_broken
      };
      if (var12.length != AchievementType.values().length) {
         return null;
      }

      for (AchievementType var7 : AchievementType.values()) {
         int var8 = var12[var7.ordinal()];

         for (AchievementsManager.Achievement var10 : (ArrayList)this.achievements.get(var7)) {
            ItemStack var11 = new ItemStackBuilder(var8 >= var10.score ? Config.getInstance().achievementUnlocked : Config.getInstance().achievementLocked)
               .setName(Config.getInstance().achievementDescription.replace("%description%", var10.description))
               .addLore(
                  new String[]{
                     var8 >= var10.score ? Config.getInstance().achievementUnlockedLore : Config.getInstance().achievementLockedLore,
                     " ",
                     Config.getInstance().achievementPrize.replace("%prizeDescription%", var10.prizeDescription)
                  }
               )
               .build();
            var2.setItem(var10.id, var10.slot, var11);
         }
      }

      return var2;
   }

   public void checkPlayer(Player var1, AchievementType var2, int var3) {
      for (AchievementsManager.Achievement var5 : (ArrayList)this.achievements.get(var2)) {
         if (var3 == var5.score) {
            var5.send(var1);
            break;
         }
      }
   }

   public List<AchievementsManager.Achievement> getAchievements() {
      ArrayList var1 = new ArrayList();

      for (AchievementType var5 : AchievementType.values()) {
         var1.addAll((Collection)this.achievements.get(var5));
      }

      return var1;
   }

   public int getSize() {
      return this.getAchievements().size();
   }

   public class Achievement {
      int score;
      String description;
      String prizeDescription;
      String executedCommand;
      int id;
      int slot;

      public Achievement(int var2, String var3, String var4, String var5) {
         this.score = var2;
         this.description = var3;
         this.prizeDescription = var4;
         this.executedCommand = var5;
      }

      public void send(Player var1) {
         ChatColor var2 = Utils.getRandomColor();
         var1.sendMessage(" ");
         var1.sendMessage(
            var2
               + ""
               + ChatColor.STRIKETHROUGH
               + "-----"
               + ChatColor.DARK_RED
               + ""
               + ChatColor.MAGIC
               + " AA"
               + ChatColor.YELLOW
               + " Achievement unlocked "
               + ChatColor.DARK_RED
               + ""
               + ChatColor.MAGIC
               + "AA "
               + var2
               + ""
               + ChatColor.STRIKETHROUGH
               + "-----"
         );
         var1.sendMessage(var2 + " Achievement -> " + ChatColor.GOLD + this.description);
         var1.sendMessage(var2 + " Prize -> " + ChatColor.YELLOW + this.prizeDescription);
         var1.sendMessage(" ");
         Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.executedCommand.replace("%player%", var1.getName()));
         Utils.fireWorkEffect(var1.getLocation(), true);
         var1.playSound(var1.getLocation(), SoundsManager.NOTE_PLING, 1.0F, 1.0F);
         new ItemStackBuilder(((PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName())).achievements.getItem(this.id, this.slot))
            .setType(Material.DIAMOND_BLOCK)
            .replaceLore(ChatColor.RED + "Locked", ChatColor.GREEN + "Unlocked")
            .build();
      }
   }
}
