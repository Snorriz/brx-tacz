package me.wazup.battleroyalex.commands.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.custommaps.CustomMapsManager;
import me.wazup.battleroyalex.custommaps.utils.MapGeneratingCuboid;
import me.wazup.battleroyalex.custommaps.utils.Pixel;
import me.wazup.battleroyalex.custommaps.utils.MapGeneratingCuboid.CuboidIterator;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class GenerateMapCommand extends SubCommand {
   public GenerateMapCommand() {
      super("battleroyalex.admin", true, "<Arena>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var4.length < 2) {
         return false;
      }

      String var5 = var4[1].toLowerCase();
      if (!var1.arenas.containsKey(var5)) {
         var3.sendMessage(var2.prefix + "Couldn't find an arena with that name!");
         return true;
      }

      final Arena var6 = (Arena)var1.arenas.get(var5);
      Location var7 = new Location(Bukkit.getWorld(var6.worldName), var6.mapCenterX, 0.0, var6.mapCenterZ);
      float var8 = var6.worldBorderInitialSize / 2.0F;
      Location var9 = var7.clone().add(-var8, 0.0, -var8);
      Location var10 = var7.clone().add(var8, 0.0, var8);
      ArrayList var11 = new ArrayList();
      MapGeneratingCuboid var12 = new MapGeneratingCuboid(var9, var10);
      CuboidIterator var13 = (CuboidIterator)var12.iterator();
      int var14 = var12.getXSize();
      int var15 = var14 - 128;
      int var16;
      if (var15 > 0) {
         var16 = 1;
         var3.sendMessage(
            var2.prefix
               + ChatColor.YELLOW
               + "Region is "
               + var14
               + "x"
               + var14
               + " while maps can only display 128x128 blocks! Erasing some layers randomly to fit maps"
         );

         for (int var17 = 0; var17 < var15; var17++) {
            var13.banRandomX();
            var13.banRandomZ();
         }

         while (var13.hasNext()) {
            Block var30 = var13.next();
            if (var30 != null) {
               var11.add(var30.getType());
            }
         }
      } else {
         int[] var31 = new int[]{1, 2, 4, 8, 16, 32, 64, 128};
         int var18 = 0;

         for (int var22 : var31) {
            if (var22 >= var14) {
               var18 = var22;
               break;
            }
         }

         int var34 = var18 - var14;
         var16 = 128 / var18;
         var3.sendMessage(
            var2.prefix
               + ChatColor.YELLOW
               + "Region is "
               + var14
               + "x"
               + var14
               + " while maps can only display 128x128, 64x64, 32x32, 16x16, 8x8, 4x4, 2x2, 1x1 blocks! Duplicating some layers randomly to fit maps"
         );

         while (var13.hasNext()) {
            Block var36 = var13.next();
            var11.add(var36.getType());
         }

         for (int var37 = 0; var37 < var34; var37++) {
            int var40 = Utils.random.nextInt(var14 + var37) * var14;

            for (int var43 = 0; var43 < var14; var43++) {
               var11.add(var40, var11.get(var40 + var14 - 1));
            }
         }

         for (int var38 = 0; var38 < var34; var38++) {
            int var41 = Utils.random.nextInt(var14 + var38);

            for (int var44 = 0; var44 < var18; var44++) {
               var11.add(var41, var11.get(var41));
               var41 += var14 + 1 + var38;
            }
         }
      }

      var6.mapPixels = new ArrayList(16384);
      ArrayList var32 = new ArrayList();
      HashMap var33 = CustomMapsManager.getInstance().colorPalette;
      int var35 = 0;
      int var39 = 0;
      int var42 = 128 / var16;

      for (Material var23 : var11) {
         byte var24 = (Byte)var33.getOrDefault(var23.name(), (byte)0);
         if (!var33.containsKey(var23.name()) && !var32.contains(var23.name())) {
            var32.add(var23.name());
         }

         for (int var25 = 0; var25 < var16; var25++) {
            int var26 = var35 * var16 + var25;

            for (int var27 = 0; var27 < var16; var27++) {
               int var28 = var39 * var16 + var27;
               var6.mapPixels.add(new Pixel(var26, var28, var24));
            }
         }

         if (++var35 >= var42) {
            var35 = 0;
            var39++;
         }
      }

      var3.sendMessage(
         var2.prefix
            + ChatColor.YELLOW
            + "Map has been generated! "
            + (
               var32.isEmpty()
                  ? ""
                  : "There are some blocks detected on the map that do not have a color in the map palette! "
                     + var32.toString()
                     + ", you can add these colors in the map palette file"
            )
      );
      if (var3 instanceof Player) {
         MapView var46 = Bukkit.createMap(var9.getWorld());

         for (MapRenderer var51 : var46.getRenderers()) {
            var46.getRenderers().remove(var51);
         }

         var46.addRenderer(new MapRenderer() {
            public void render(MapView var1, MapCanvas var2x, Player var3x) {
               for (Pixel var5x : var6.mapPixels) {
                  var2x.setPixel(var5x.x, var5x.y, var5x.color);
               }
            }
         });
         ItemStack var49 = CustomMapsManager.getInstance().generateItemStack(var46);
         ((Player)var3).getInventory().addItem(new ItemStack[]{var49});
      }

      var3.sendMessage(var2.prefix + ChatColor.YELLOW + "Saving map to file...");
      File var47 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/arenas/" + var6.name, "maps.dat");
      YamlConfiguration var50 = YamlConfiguration.loadConfiguration(var47);
      ArrayList var52 = new ArrayList();

      for (Pixel var54 : var6.mapPixels) {
         var52.add(var54.x + ":" + var54.y + ":" + var54.color);
      }

      var50.set("Maps.1", var52);

      try {
         var50.save(var47);
      } catch (IOException var29) {
         var29.printStackTrace();
      }

      var3.sendMessage(var2.prefix + ChatColor.YELLOW + "Save complete...!");
      return true;
   }
}
