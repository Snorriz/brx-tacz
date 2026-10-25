package me.wazup.battleroyalex.custommaps.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class MapGeneratingCuboid {
   private int x1;
   private int z1;
   private int x2;
   private int z2;
   public String worldName;

   public MapGeneratingCuboid(Location var1, Location var2) {
      this.worldName = var1.getWorld().getName();
      this.x1 = Math.min(var1.getBlockX(), var2.getBlockX());
      this.z1 = Math.min(var1.getBlockZ(), var2.getBlockZ());
      this.x2 = Math.max(var1.getBlockX(), var2.getBlockX());
      this.z2 = Math.max(var1.getBlockZ(), var2.getBlockZ());
   }

   public MapGeneratingCuboid(String var1) {
      String[] var2 = var1.split(", ");
      this.worldName = var2[0];
      this.x1 = Integer.parseInt(var2[1]);
      this.z1 = Integer.parseInt(var2[3]);
      this.x2 = Integer.parseInt(var2[4]);
      this.z2 = Integer.parseInt(var2[6]);
   }

   public int getXSize() {
      return this.x2 - this.x1 + 1;
   }

   public int getZSize() {
      return this.z2 - this.z1 + 1;
   }

   public int getSize() {
      return this.getXSize() * this.getZSize();
   }

   public Iterator<Block> iterator() {
      return new MapGeneratingCuboid.CuboidIterator(Bukkit.getWorld(this.worldName), this.x1, this.z1, this.x2, this.z2);
   }

   public String toString() {
      return this.worldName + ", " + this.x1 + ", " + this.z1 + ", " + this.x2 + ", " + this.z2;
   }

   public class CuboidIterator implements Iterator<Block> {
      private World w;
      private int baseX;
      private int baseZ;
      private int x;
      private int z;
      private int sizeX;
      private int sizeZ;
      ArrayList<Material> airMaterials = new ArrayList(3);
      private List<Integer> bannedXValues = new ArrayList();
      private List<Integer> bannedZValues = new ArrayList();

      public CuboidIterator(World var2, int var3, int var4, int var5, int var6) {
         this.w = var2;
         this.baseX = var3;
         this.baseZ = var4;
         this.sizeX = var5 - var3 + 1;
         this.sizeZ = var6 - var4 + 1;
         this.x = this.z = 0;
         this.airMaterials.add(Material.AIR);
         if (!this.airMaterials.contains(XMaterial.VOID_AIR.parseMaterial())) {
            this.airMaterials.add(XMaterial.VOID_AIR.parseMaterial());
         }

         if (!this.airMaterials.contains(XMaterial.CAVE_AIR.parseMaterial())) {
            this.airMaterials.add(XMaterial.CAVE_AIR.parseMaterial());
         }
      }

      public boolean hasNext() {
         return this.x < this.sizeX && this.z < this.sizeZ;
      }

      public Block next() {
         Block var1 = this.getHighestBlockAt(new Location(this.w, this.baseX + this.x, 0.0, this.baseZ + this.z));
         this.update();
         return var1;
      }

      private Block getHighestBlockAt(Location var1) {
         for (int var2 = 256; var2 > -1; var2--) {
            var1.setY(var2);
            if (!this.airMaterials.contains(var1.getBlock().getType())) {
               return var1.getBlock();
            }
         }

         return null;
      }

      public void banRandomX() {
         int var1 = Utils.random.nextInt(this.sizeX - 1) + 1;

         while (this.bannedXValues.contains(var1)) {
            var1 = Utils.random.nextInt(this.sizeX - 1) + 1;
         }

         this.bannedXValues.add(var1);
      }

      public void banRandomZ() {
         int var1 = Utils.random.nextInt(this.sizeZ - 1) + 1;

         while (this.bannedZValues.contains(var1)) {
            var1 = Utils.random.nextInt(this.sizeZ - 1) + 1;
         }

         this.bannedZValues.add(var1);
      }

      public void update() {
         this.x++;

         while (this.bannedXValues.contains(this.x)) {
            this.x++;
         }

         if (this.x >= this.sizeX) {
            this.x = 0;
            this.z++;

            while (this.bannedZValues.contains(this.z)) {
               this.z++;
            }
         }
      }

      public void remove() {
      }
   }
}
