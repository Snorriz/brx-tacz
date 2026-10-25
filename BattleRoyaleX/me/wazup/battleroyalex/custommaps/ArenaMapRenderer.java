package me.wazup.battleroyalex.custommaps;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.custommaps.utils.Pixel;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursor;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class ArenaMapRenderer extends MapRenderer {
   Arena arena;
   Location mapCenter;
   int dimension;
   MapCursorCollection cursors;
   boolean updateCursors;
   boolean drawEnvironment = true;
   MapCursor playerCursor;
   int ticks = 0;
   MapCursorCollection borderCursors;
   MapCursorCollection supplyDropCursors;

   public ArenaMapRenderer(Arena var1) {
      this.arena = var1;
      this.mapCenter = new Location(Bukkit.getWorld(var1.worldName), var1.mapCenterX, 0.0, var1.mapCenterZ);
      this.dimension = var1.worldBorderInitialSize + 1;
      this.cursors = new MapCursorCollection();
   }

   public void render(MapView var1, MapCanvas var2, Player var3) {
      if (this.updateCursors) {
         if (this.drawEnvironment) {
            for (Pixel var5 : this.arena.mapPixels) {
               var2.setPixel(var5.x, var5.y, var5.color);
            }

            this.drawEnvironment = false;
         }

         var2.setCursors(this.cursors);
         this.updateCursors = false;
      }

      this.ticks++;
      if (this.ticks == 20) {
         if (this.playerCursor != null) {
            this.cursors.removeCursor(this.playerCursor);
         }

         int var6 = (int)((var3.getLocation().getX() - this.mapCenter.getX()) / this.dimension * 256.0);
         int var7 = (int)((var3.getLocation().getZ() - this.mapCenter.getZ()) / this.dimension * 256.0);
         this.playerCursor = this.cursors.addCursor(var6, var7, Utils.byteFromFace(Utils.yawToFace(var3.getLocation().getYaw())));
         this.ticks = 0;
      }
   }

   public void addPermanentCursor(MapCursor var1) {
      this.cursors.addCursor(var1);
   }

   public void updateCursors() {
      this.updateCursors = true;
   }

   public void updateBorderCursors(MapCursorCollection var1) {
      if (this.borderCursors != null) {
         for (int var2 = 0; var2 < this.borderCursors.size(); var2++) {
            this.cursors.removeCursor(this.borderCursors.getCursor(var2));
         }
      }

      this.borderCursors = var1;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         this.cursors.addCursor(var1.getCursor(var3));
      }
   }

   public void updateSupplyDropCursors(MapCursorCollection var1) {
      if (this.supplyDropCursors != null) {
         for (int var2 = 0; var2 < this.supplyDropCursors.size(); var2++) {
            this.cursors.removeCursor(this.supplyDropCursors.getCursor(var2));
         }
      }

      this.supplyDropCursors = var1;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         this.cursors.addCursor(var1.getCursor(var3));
      }
   }
}
