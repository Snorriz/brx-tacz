package me.wazup.battleroyalex.celebrations;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.shop.Celebration;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Zeus extends Celebration {
   public String getName() {
      return "Zeus";
   }

   public void start(final Player var1, final Arena var2) {
      (new BukkitRunnable() {
         public void run() {
            if (var2.state.equals(ArenaState.ENDING) && var2.players.containsKey(var1.getName())) {
               var1.getWorld().strikeLightningEffect(var1.getLocation());
            } else {
               this.cancel();
            }
         }
      }).runTaskTimer(BattleRoyaleX.getInstance(), 0L, 15L);
   }
}
