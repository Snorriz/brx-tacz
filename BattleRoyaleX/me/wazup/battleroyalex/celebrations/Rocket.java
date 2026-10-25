package me.wazup.battleroyalex.celebrations;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.managers.SoundsManager;
import me.wazup.battleroyalex.shop.Celebration;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Rocket extends Celebration {
   private final int chargingDuration = 4;
   private final PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, 80, 9999999);

   public String getName() {
      return "Rocket";
   }

   public void start(final Player var1, final Arena var2) {
      var1.addPotionEffect(this.slowness);
      (new BukkitRunnable() {
         int counter = 0;
         final int target = 8;
         boolean launched = false;

         public void run() {
            if (var2.state.equals(ArenaState.ENDING) && var2.players.containsKey(var1.getName())) {
               if (!this.launched) {
                  var1.getWorld().playEffect(var1.getLocation(), Effect.STEP_SOUND, 1);
                  var1.getWorld().playSound(var1.getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);
                  this.counter++;
                  if (this.counter >= 8) {
                     this.launched = true;
                     this.counter = 0;

                     for (int var1x = 0; var1x < 5; var1x++) {
                        var1.getWorld().createExplosion(var1.getLocation().add(Vector.getRandom()), 0.0F, false);
                     }

                     var1.setVelocity(var1.getLocation().getDirection().setY(0.1).multiply(20));
                  }
               } else {
                  var1.getWorld().createExplosion(var1.getLocation(), 0.0F, false);
                  this.counter++;
                  if (this.counter >= 4) {
                     this.cancel();
                  }
               }
            } else {
               this.cancel();
            }
         }
      }).runTaskTimer(BattleRoyaleX.getInstance(), 10L, 10L);
   }
}
