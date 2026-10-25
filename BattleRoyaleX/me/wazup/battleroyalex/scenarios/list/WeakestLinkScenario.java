package me.wazup.battleroyalex.scenarios.list;

import java.util.ArrayList;
import java.util.HashMap;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.scenarios.Scenario;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class WeakestLinkScenario extends Scenario {
   HashMap<Arena, BukkitTask> registeredArenas = new HashMap();
   int eliminationInterval;
   boolean eliminateRandomlyIfEqualHealth;

   public WeakestLinkScenario() {
      super(
         "Weakest Link",
         XMaterial.WOODEN_SWORD.parseMaterial(),
         new String[]{"&7The player with the least amount of hearts", "&7is eliminated every 60 seconds!"}
      );
      FileConfiguration var1 = this.getConfiguration();
      boolean var2 = false;
      if (!var1.contains("Elimination-Interval")) {
         var1.set("Elimination-Interval", 60);
         var2 = true;
      }

      if (!var1.contains("Eliminate-Randomly-If-Equal-Health")) {
         var1.set("Eliminate-Randomly-If-Equal-Health", false);
         var2 = true;
      }

      if (var2) {
         this.saveConfiguration(var1);
      }

      this.eliminationInterval = var1.getInt("Elimination-Interval") * 20;
      this.eliminateRandomlyIfEqualHealth = var1.getBoolean("Eliminate-Randomly-If-Equal-Health");
   }

   public void registerArena(final Arena var1) {
      BukkitTask var2 = (new BukkitRunnable() {
         public void run() {
            HashMap var1x = new HashMap();

            for (Player var3 : var1.getPlayers()) {
               var1x.put(var3.getName(), (int)var3.getHealth());
            }

            if (var1x.size() >= 2) {
               int var6 = Integer.MAX_VALUE;

               for (int var4 : var1x.values()) {
                  if (var6 > var4) {
                     var6 = var4;
                  }
               }

               ArrayList var8 = new ArrayList();

               for (String var5 : var1x.keySet()) {
                  if ((Integer)var1x.get(var5) == var6) {
                     var8.add(var5);
                  }
               }

               if (var8.size() == 1) {
                  WeakestLinkScenario.this.eliminatePlayer(var1, Bukkit.getPlayer((String)var8.get(0)));
               } else if (WeakestLinkScenario.this.eliminateRandomlyIfEqualHealth) {
                  WeakestLinkScenario.this.eliminatePlayer(var1, Bukkit.getPlayer((String)var8.get(Utils.random.nextInt(var8.size()))));
               }
            }
         }
      }).runTaskTimer(BattleRoyaleX.getInstance(), this.eliminationInterval, this.eliminationInterval);
      this.registeredArenas.put(var1, var2);
   }

   public void unregisterArena(Arena var1) {
      ((BukkitTask)this.registeredArenas.get(var1)).cancel();
      this.registeredArenas.remove(var1);
   }

   public void eliminatePlayer(Arena var1, Player var2) {
      var2.damage(10000.0);
      String var3 = var2.getName() + " has been eliminated!;";

      for (Player var5 : var1.getAllPlayers()) {
         var5.sendMessage(var3);
      }
   }
}
