package me.wazup.battleroyalex.celebrations;

import java.util.ArrayList;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.managers.SoundsManager;
import me.wazup.battleroyalex.shop.Celebration;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PartySpitter extends Celebration implements Listener {
   ArrayList<Material> wools = new ArrayList();
   ArrayList<Vector> vectors;

   public PartySpitter() {
      if (XMaterial.supports(13)) {
         for (int var1 = 0; var1 < 16; var1++) {
            this.wools.add(((XMaterial)XMaterial.matchXMaterial("WOOL:" + var1).get()).parseMaterial());
         }
      } else {
         this.wools.add(XMaterial.WHITE_WOOL.parseMaterial());
      }

      this.vectors = new ArrayList();
      double var6 = 0.5;
      double var3 = 0.3;

      for (int var5 = 0; var5 < 4; var5++) {
         this.vectors.add(new Vector(Utils.random.nextDouble() * var6, Utils.random.nextDouble() + var3, Utils.random.nextDouble() * var6));
      }

      for (int var7 = 0; var7 < 4; var7++) {
         this.vectors.add(new Vector(-Utils.random.nextDouble() * var6, Utils.random.nextDouble() + var3, Utils.random.nextDouble() * var6));
      }

      for (int var8 = 0; var8 < 4; var8++) {
         this.vectors.add(new Vector(-Utils.random.nextDouble() * var6, Utils.random.nextDouble() + var3, -Utils.random.nextDouble() * var6));
      }

      for (int var9 = 0; var9 < 4; var9++) {
         this.vectors.add(new Vector(Utils.random.nextDouble() * var6, Utils.random.nextDouble() + var3, -Utils.random.nextDouble() * var6));
      }

      Bukkit.getPluginManager().registerEvents(this, BattleRoyaleX.getInstance());
   }

   public String getName() {
      return "Party Spitter";
   }

   public void start(final Player var1, final Arena var2) {
      (new BukkitRunnable() {
            public void run() {
               if (var2.state.equals(ArenaState.ENDING) && var2.players.containsKey(var1.getName())) {
                  FallingBlock var1x = var1.getLocation()
                     .getWorld()
                     .spawnFallingBlock(
                        var1.getLocation(),
                        (Material)PartySpitter.this.wools.get(Utils.random.nextInt(PartySpitter.this.wools.size())),
                        (byte)Utils.random.nextInt(15)
                     );
                  var1x.setMetadata("CELEBRATION_PARTY", new FixedMetadataValue(BattleRoyaleX.getInstance(), null));
                  var1x.setVelocity((Vector)PartySpitter.this.vectors.get(Utils.random.nextInt(PartySpitter.this.vectors.size())));
                  var1x.setDropItem(false);
                  var1.getWorld().playSound(var1.getLocation(), SoundsManager.ITEM_PICKUP, 1.0F, 1.0F);
               } else {
                  this.cancel();
               }
            }
         })
         .runTaskTimer(BattleRoyaleX.getInstance(), 0L, 4L);
   }

   @EventHandler
   public void onAnvilDropEvent(EntityChangeBlockEvent var1) {
      if (var1.getEntity().getType().equals(EntityType.FALLING_BLOCK)) {
         FallingBlock var2 = (FallingBlock)var1.getEntity();
         if (var2.hasMetadata("CELEBRATION_PARTY")) {
            var1.setCancelled(true);
         }
      }
   }
}
