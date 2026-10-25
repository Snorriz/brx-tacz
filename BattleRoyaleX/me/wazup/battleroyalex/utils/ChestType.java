package me.wazup.battleroyalex.utils;

import java.util.HashMap;
import java.util.Random;
import org.bukkit.inventory.ItemStack;

public class ChestType {
   public int minItems;
   public int maxItems;
   public ChestType.ChestContent content;

   public ChestType(int var1, int var2, HashMap<ItemStack, Integer> var3) {
      this.minItems = var1;
      this.maxItems = var2;
      this.content = new ChestType.ChestContent(var3);
   }

   public class ChestContent {
      private HashMap<ItemStack, ChestType.ChestContent.Interval> items = new HashMap();
      private int total;

      public ChestContent(HashMap<ItemStack, Integer> var2) {
         int var3 = 0;

         for (ItemStack var5 : var2.keySet()) {
            int var6 = (Integer)var2.get(var5);
            this.items.put(var5, new ChestType.ChestContent.Interval(var3, var3 + var6));
            var3 += var6;
         }

         this.total = var3;
      }

      public ItemStack getRandom(Random var1) {
         int var2 = var1.nextInt(this.total);

         for (ItemStack var4 : this.items.keySet()) {
            ChestType.ChestContent.Interval var5 = (ChestType.ChestContent.Interval)this.items.get(var4);
            if (var2 >= var5.min && var2 < var5.max) {
               return var4;
            }
         }

         return null;
      }

      private class Interval {
         int min;
         int max;

         public Interval(int var2, int var3) {
            this.min = var2;
            this.max = var3;
         }
      }
   }
}
