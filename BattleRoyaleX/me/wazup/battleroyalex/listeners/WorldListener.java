package me.wazup.battleroyalex.listeners;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.utils.ChestType;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.metadata.MetadataValue;

public class WorldListener implements Listener {
   @EventHandler
   public void WorldInitializeEvent(WorldInitEvent var1) {
      if (!Config.getInstance().loadChunksOnWorldLoadImmediately) {
         World var2 = var1.getWorld();

         for (Arena var4 : BattleRoyaleX.getInstance().arenas.values()) {
            if (var4.worldName.equals(var2.getName())) {
               var2.setKeepSpawnInMemory(false);
               var2.setAutoSave(false);
               break;
            }
         }
      }
   }

   @EventHandler
   public void onCreatureSpawn(CreatureSpawnEvent var1) {
      if (var1.getSpawnReason().equals(SpawnReason.NATURAL) && Config.getInstance().disableNaturalMobSpawning) {
         for (Arena var3 : BattleRoyaleX.getInstance().arenas.values()) {
            if (var3.worldName.equals(var1.getLocation().getWorld().getName())) {
               var1.setCancelled(true);
               break;
            }
         }
      }
   }

   @EventHandler
   public void onEntityExplode(EntityExplodeEvent var1) {
      for (Arena var3 : BattleRoyaleX.getInstance().arenas.values()) {
         if (var3.worldName.equalsIgnoreCase(var1.getLocation().getWorld().getName())) {
            var1.blockList().clear();
            break;
         }
      }
   }

   @EventHandler
   public void onEntityChangeBlockEvent(EntityChangeBlockEvent var1) {
      if (var1.getEntity() instanceof FallingBlock) {
         FallingBlock var2 = (FallingBlock)var1.getEntity();
         if (var2.hasMetadata("BRX_PACKAGE") || var2.hasMetadata("BRX_SUPPLY-DROP")) {
            var1.setCancelled(true);
            boolean var3 = var2.hasMetadata("BRX_PACKAGE");
            String var4 = var3
               ? ((MetadataValue)var2.getMetadata("BRX_PACKAGE").get(0)).asString()
               : ((MetadataValue)var2.getMetadata("BRX_SUPPLY-DROP").get(0)).asString();
            Arena var5 = (Arena)BattleRoyaleX.getInstance().arenas.get(var4.toLowerCase());
            Block var6 = var2.getLocation().getBlock();
            if (!var5.state.equals(ArenaState.INGAME)) {
               var6.setType(Material.AIR);
               return;
            }

            if (Config.getInstance().carepackageExplosion) {
               Location var7 = var6.getLocation().add(0.5, 0.0, 0.5);
               var6.getWorld().createExplosion(var7.getX(), var7.getY(), var7.getZ(), 5.0F, false, false);
            }

            if (!var5.modifiedBlocks.containsKey(var6.getLocation())) {
               var5.modifiedBlocks.put(var6.getLocation(), var6.getState());
            }

            var5.openedChests.add(var6.getLocation());
            var2.getWorld().strikeLightningEffect(var6.getLocation());
            var6.setType(Material.CHEST);
            Utils.fillChest(var6, (ChestType)BattleRoyaleX.getInstance().chests.get(var3 ? "Carepackage" : "Supply-Drops"), null);
         }
      }
   }
}
