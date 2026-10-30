package me.wazup.battleroyalex.utils;

import java.util.ArrayList;
import java.util.List;
import me.wazup.battleroyalex.Arena;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import me.wazup.battleroyalex.BattleRoyaleX;

/**
 * Animated gradient scoreboard header (e.g. "TaxPhobia").
 * Reads the Scoreboards.Gradient section of customization.yml:
 *   logo:
 *     change-interval: 150
 *     texts: [ "&#..hex..TaxP..", ... ]
 * and cycles the texts, applying them as the sidebar title of every active game scoreboard.
 */
public class GradientTitle {
   private static final List<String> frames = new ArrayList<>();
   private static int intervalTicks = 3; // 150ms ~ 3 ticks
   private static int index = 0;
   private static boolean started = false;

   public static void load(FileConfiguration config) {
      frames.clear();
      if (config.contains("Scoreboards.Gradient.logo.texts")) {
         for (String s : config.getStringList("Scoreboards.Gradient.logo.texts")) {
            frames.add(FlexibleScoreboard.hexToLegacy(s));
         }
      }
      if (config.contains("Scoreboards.Gradient.logo.change-interval")) {
         int ms = config.getInt("Scoreboards.Gradient.logo.change-interval");
         intervalTicks = Math.max(1, Math.round(ms / 50.0f));
      }
   }

   public static String current() {
      return frames.isEmpty() ? null : frames.get(index % frames.size());
   }

   public static synchronized void start() {
      if (started || frames.isEmpty()) {
         return;
      }
      started = true;
      new BukkitRunnable() {
         public void run() {
            index++;
            String frame = current();
            if (frame == null) {
               return;
            }
            for (Arena arena : BattleRoyaleX.getInstance().arenas.values()) {
               if (arena.scoreboard != null && arena.state.equals(me.wazup.battleroyalex.utils.Enums.ArenaState.INGAME)) {
                  arena.scoreboard.setTitle(frame);
               }
            }
         }
      }.runTaskTimer(BattleRoyaleX.getInstance(), 0L, intervalTicks);
   }
}
