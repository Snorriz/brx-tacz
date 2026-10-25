package me.wazup.battleroyalex.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

public class ItemStackBuilder {
   private ItemStack item;
   private ItemMeta meta;
   private List<String> lore;
   public static Method setDamageMethod;
   public static Method getDamageMethod;

   public ItemStackBuilder(ItemStack var1) {
      this.item = var1;
      this.meta = var1.getItemMeta();
      this.lore = (List<String>)(this.meta != null && this.meta.hasLore() ? this.meta.getLore() : new ArrayList());
   }

   public ItemStackBuilder(Material var1) {
      this(new ItemStack(var1));
   }

   public ItemStackBuilder setType(Material var1) {
      this.item.setType(var1);
      return this;
   }

   public ItemStackBuilder setName(String var1) {
      this.meta.setDisplayName(var1);
      return this;
   }

   public ItemStackBuilder clearLore() {
      this.lore.clear();
      return this;
   }

   public ItemStackBuilder addLore(String... var1) {
      for (String var5 : var1) {
         this.lore.add(var5);
      }

      return this;
   }

   public ItemStackBuilder addEnchantment(Enchantment var1, int var2) {
      if (!this.item.getType().equals(Material.BOOK) && !this.item.getType().equals(Material.ENCHANTED_BOOK)) {
         this.meta.addEnchant(var1, var2, true);
      } else {
         ((EnchantmentStorageMeta)this.meta).addStoredEnchant(var1, var2, true);
      }

      return this;
   }

   public ItemStackBuilder setAmount(int var1) {
      this.item.setAmount(var1);
      return this;
   }

   public Material getType() {
      return this.item.getType();
   }

   public ItemStackBuilder setDurability(int var1) {
      if (setDamageMethod != null) {
         try {
            setDamageMethod.invoke(this.meta, new Object[]{(short)var1});
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      } else {
         this.item.setDurability((short)var1);
      }

      return this;
   }

   public void setPotionEffect(PotionType var1, boolean var2, boolean var3) {
      PotionMeta var4 = (PotionMeta)this.item.getItemMeta();

      try {
         PotionMeta.class
            .getMethod("setBasePotionData", new Class[]{Class.forName("org.bukkit.potion.PotionData")})
            .invoke(
               var4,
               new Object[]{
                  Class.forName("org.bukkit.potion.PotionData")
                     .getConstructor(new Class[]{PotionType.class, boolean.class, boolean.class})
                     .newInstance(new Object[]{var1, var2, var3})
               }
            );
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException var6) {
         Bukkit.getConsoleSender()
            .sendMessage(
               "[Battle Royale X] The used format for potions does not work on this minecraft version, please try other format which is POTION:DURABILITY"
            );
      }

      this.meta = var4;
   }

   public ItemStackBuilder replaceLore(String var1, String var2) {
      for (int var3 = 0; var3 < this.lore.size(); var3++) {
         if (((String)this.lore.get(var3)).contains(var1)) {
            this.lore.remove(var3);
            this.lore.add(var3, var2);
            break;
         }
      }

      return this;
   }

   public ItemStack build() {
      if (this.meta != null) {
         this.meta.setLore(this.lore);
      }

      this.lore.clear();
      this.item.setItemMeta(this.meta);
      return this.item;
   }

   public static void loadMethods() {
      try {
         for (Method var3 : Class.forName("org.bukkit.inventory.meta.Damageable").getMethods()) {
            if (var3.getName().equals("setDamage")) {
               setDamageMethod = var3;
            } else if (var3.getName().equals("getDamage")) {
               getDamageMethod = var3;
            }
         }
      } catch (Exception var4) {
      }
   }
}
