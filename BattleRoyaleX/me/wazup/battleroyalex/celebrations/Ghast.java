package me.wazup.battleroyalex.celebrations;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.shop.Celebration;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

public class Ghast extends Celebration implements Listener {
   FixedMetadataValue meta;
   Vector[] spaces = new Vector[]{new Vector(0, 1, 0), new Vector(1, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, 1), new Vector(0, 0, -1)};

   public Ghast() {
      Bukkit.getPluginManager().registerEvents(this, BattleRoyaleX.getInstance());
      this.meta = new FixedMetadataValue(BattleRoyaleX.getInstance(), null);
   }

   public String getName() {
      return "Ghast";
   }

   public void start(final Player var1, final Arena var2) {
      (new BukkitRunnable() {
         public void run() {
            if (var2.state.equals(ArenaState.ENDING) && var2.players.containsKey(var1.getName())) {
               Fireball var1x = (Fireball)var1.launchProjectile(Fireball.class);
               var1x.setIsIncendiary(false);
               var1x.setYield(0.0F);
               var1x.setMetadata("CELEBRATION_GHAST", Ghast.this.meta);
            } else {
               this.cancel();
            }
         }
      }).runTaskTimer(BattleRoyaleX.getInstance(), 0L, 10L);
   }

   @EventHandler
   public void onFireballExplode(ProjectileHitEvent var1) {
      if (var1.getEntity().getType().equals(EntityType.FIREBALL)) {
         Fireball var2 = (Fireball)var1.getEntity();
         if (var2.hasMetadata("CELEBRATION_GHAST")) {
            BlockIterator var3 = new BlockIterator(
               var1.getEntity().getWorld(), var1.getEntity().getLocation().toVector(), var1.getEntity().getVelocity().normalize(), 0.0, 4
            );

            while (var3.hasNext()) {
               Block var4 = var3.next();
               if (!var4.getType().equals(Material.AIR)) {
                  for (Vector var8 : this.spaces) {
                     if (var4.getLocation().add(var8).getBlock().getType().equals(Material.AIR)) {
                        FallingBlock var9 = var4.getLocation().getWorld().spawnFallingBlock(var4.getLocation().add(var8), var4.getType(), var4.getData());
                        var9.setMetadata("CELEBRATION_GHAST", this.meta);
                        var9.setVelocity(Vector.getRandom().setY(1));
                        var9.setDropItem(false);
                        return;
                     }
                  }
                  break;
               }
            }
         }
      }
   }

   @EventHandler
   public void onGhastBlockLand(EntityChangeBlockEvent var1) {
      if (var1.getEntity().getType().equals(EntityType.FALLING_BLOCK)) {
         FallingBlock var2 = (FallingBlock)var1.getEntity();
         if (var2.hasMetadata("CELEBRATION_GHAST")) {
            var1.setCancelled(true);
         }
      }
   }
}
