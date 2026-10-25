package me.wazup.battleroyalex.celebrations;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.managers.SoundsManager;
import me.wazup.battleroyalex.shop.Celebration;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class FallingAnvils extends Celebration implements Listener {
   public FallingAnvils() {
      Bukkit.getPluginManager().registerEvents(this, BattleRoyaleX.getInstance());
   }

   public String getName() {
      return "Falling Anvils";
   }

   public void start(final Player var1, final Arena var2) {
      (new BukkitRunnable() {
         public void run() {
            if (var2.state.equals(ArenaState.ENDING) && var2.players.containsKey(var1.getName())) {
               Location var1x = var1.getLocation().add(Utils.random.nextInt(5), 10.0, Utils.random.nextInt(5));
               FallingBlock var2x = var1x.getWorld().spawnFallingBlock(var1x, Material.ANVIL, (byte)0);
               var2x.setMetadata("CELEBRATION_ANVIL", new FixedMetadataValue(BattleRoyaleX.getInstance(), null));
               var2x.setDropItem(false);
            } else {
               this.cancel();
            }
         }
      }).runTaskTimer(BattleRoyaleX.getInstance(), 0L, 8L);
   }

   @EventHandler
   public void onAnvilDropEvent(EntityChangeBlockEvent var1) {
      if (var1.getEntity().getType().equals(EntityType.FALLING_BLOCK)) {
         FallingBlock var2 = (FallingBlock)var1.getEntity();
         if (var2.hasMetadata("CELEBRATION_ANVIL")) {
            var1.setCancelled(true);
            var2.getWorld().playSound(var2.getLocation(), SoundsManager.ANVIL_LAND, 1.0F, 1.0F);
         }
      }
   }
}
