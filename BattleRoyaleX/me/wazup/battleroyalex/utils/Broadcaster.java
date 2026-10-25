package me.wazup.battleroyalex.utils;

import java.util.ArrayList;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.managers.FilesManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Broadcaster {
   BukkitTask task;
   String top_line;
   String bottom_line;
   public ArrayList<String> messages = new ArrayList();
   public int sendInterval;
   public static Broadcaster instance;

   public Broadcaster() {
      instance = this;
   }

   public static Broadcaster getInstance() {
      return instance;
   }

   public void loadMessages() {
      FileConfiguration var1 = FilesManager.getInstance().getConfig("broadcaster.yml");
      this.sendInterval = var1.getInt("send-interval-in-seconds") * 20;
      this.top_line = Utils.colorize(var1.getString("top-line"));

      for (String var3 : var1.getStringList("Messages")) {
         this.messages.add(Utils.colorize(var3));
      }

      this.bottom_line = Utils.colorize(var1.getString("bottom-line"));
      if (this.task != null) {
         this.task.cancel();
      }

      if (!this.messages.isEmpty() && var1.getBoolean("enabled")) {
         this.start();
      }
   }

   private void start() {
      this.task = (new BukkitRunnable() {
         int index = 0;

         public void run() {
            if (this.index >= Broadcaster.this.messages.size()) {
               this.index = 0;
            }

            String var1 = (String)Broadcaster.this.messages.get(this.index);

            for (Player var3 : Utils.getPlayers(BattleRoyaleX.getInstance().players)) {
               var3.sendMessage(Broadcaster.this.top_line);
               var3.sendMessage(var1);
               var3.sendMessage(Broadcaster.this.bottom_line);
            }

            this.index++;
         }
      }).runTaskTimer(BattleRoyaleX.getInstance(), this.sendInterval, this.sendInterval);
   }
}
