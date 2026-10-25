package me.wazup.battleroyalex.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import me.wazup.battleroyalex.BattleRoyaleX;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Lidded;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class RealisticItems {
   public static RealisticItems instance;
   private boolean chestAnimationEnabled;
   private Method craftWorldGetHandle;
   private Constructor blockPositionConstructor;
   private Method worldPlayBlockAction;
   private Object chestBlock;

   public RealisticItems() {
      instance = this;
      if (XMaterial.supports(16)) {
         this.chestAnimationEnabled = true;
      } else {
         String var1 = Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf(46) + 1);

         try {
            this.craftWorldGetHandle = Class.forName("org.bukkit.craftbukkit." + var1 + ".CraftWorld").getMethod("getHandle", new Class[0]);

            try {
               Class var9 = Class.forName("net.minecraft.server." + var1 + ".BlockPosition");
               this.blockPositionConstructor = var9.getConstructor(new Class[]{double.class, double.class, double.class});
               Class var5 = Class.forName("net.minecraft.server." + var1 + ".Blocks");
               this.chestBlock = var5.getField("CHEST").get(null);
               Class var10 = Class.forName("net.minecraft.server." + var1 + ".WorldServer");
               Class var11 = Class.forName("net.minecraft.server." + var1 + ".Block");
               this.worldPlayBlockAction = var10.getMethod("playBlockAction", new Class[]{var9, var11, int.class, int.class});
            } catch (ClassNotFoundException var7) {
               Class var2 = Class.forName("net.minecraft.core.BlockPosition");
               if (XMaterial.supports(20)) {
                  this.blockPositionConstructor = var2.getConstructor(new Class[]{int.class, int.class, int.class});
               } else {
                  this.blockPositionConstructor = var2.getConstructor(new Class[]{double.class, double.class, double.class});
               }

               Class var6 = Class.forName("net.minecraft.world.level.block.Blocks");
               this.chestBlock = var6.getField("cl").get(null);
               Class var3 = Class.forName("net.minecraft.server.level.WorldServer");
               Class var4 = Class.forName("net.minecraft.world.level.block.Block");
               this.worldPlayBlockAction = var3.getMethod("a", new Class[]{var2, var4, int.class, int.class});
            }

            this.chestAnimationEnabled = true;
         } catch (NoSuchMethodException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException var8) {
            Bukkit.getConsoleSender()
               .sendMessage(ChatColor.RED + "[Battle Royale X] The error below simply means that chest opening animations will not work on this server!");
            var8.printStackTrace();
            this.chestAnimationEnabled = false;
         }
      }
   }

   public static RealisticItems getInstance() {
      return instance;
   }

   public void playChestAnimation(Block var1, boolean var2) {
      if (this.chestAnimationEnabled) {
         if (this.worldPlayBlockAction == null) {
            Lidded var3 = (Lidded)var1.getState();
            if (var2) {
               var3.open();
            } else {
               var3.close();
            }
         } else {
            try {
               Location var7 = var1.getLocation();
               Object var4 = this.craftWorldGetHandle.invoke(var7.getWorld(), new Object[0]);
               Object var5;
               if (XMaterial.supports(20)) {
                  var5 = this.blockPositionConstructor.newInstance(new Object[]{var7.getBlockX(), var7.getBlockY(), var7.getBlockZ()});
               } else {
                  var5 = this.blockPositionConstructor.newInstance(new Object[]{var7.getX(), var7.getY(), var7.getZ()});
               }

               this.worldPlayBlockAction.invoke(var4, new Object[]{var5, this.chestBlock, 1, var2 ? 1 : 0});
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException var6) {
               var6.printStackTrace();
               this.chestAnimationEnabled = false;
            }
         }
      }
   }

   public void giveItemsFromBlock(Block var1, ArrayList<ItemStack> var2, final Location var3) {
      final ArrayList<Item> var4 = new ArrayList<>();

      for (ItemStack var6 : var2) {
         Item var7 = var1.getWorld().dropItem(var1.getLocation().add(0.5, 1.0, 0.5), var6);
         var7.setVelocity(new Vector(0, 0, 0));
         var7.setPickupDelay(10000);
         var4.add(var7);
      }

      (new BukkitRunnable() {
            boolean firstRun = true;
            int randomFactor = 3;

            public void run() {
               if (this.firstRun) {
                  this.firstRun = false;

                  for (Item var2x : var4) {
                     var2x.setVelocity(
                        (var3 != null ? var3.toVector().subtract(var2x.getLocation().toVector()) : new Vector().zero())
                           .add(
                              new Vector(
                                 (Utils.random.nextBoolean() ? 1 : -1) * Utils.random.nextInt(this.randomFactor),
                                 0,
                                 (Utils.random.nextBoolean() ? 1 : -1) * Utils.random.nextInt(this.randomFactor)
                              )
                           )
                           .normalize()
                           .multiply((Utils.random.nextInt(4) + 1) * 0.1)
                           .setY(0.25)
                     );
                  }
               } else {
                  int var4x = 0;

                  for (Item var3x : var4) {
                     if (var3x.isValid()) {
                        RealisticItems.getInstance().makeDropPhyiscal(var3x);
                     } else {
                        var4x++;
                     }
                  }

                  if (var4x == var4.size()) {
                     this.cancel();
                  }
               }
            }
         })
         .runTaskTimer(BattleRoyaleX.getInstance(), 5L, 10L);
   }

   public boolean makeDropPhyiscal(Item var1) {
      if (!var1.isOnGround()) {
         return false;
      }

      ArmorStand var2 = (ArmorStand)var1.getLocation().getWorld().spawnEntity(var1.getLocation().add(0.0, -1.1, 0.0), EntityType.ARMOR_STAND);
      var2.setGravity(false);
      var2.setVisible(false);
      byte var3 = 0;
      byte var4 = 0;
      byte var5 = -90;
      var2.setRightArmPose(new EulerAngle(var3 * 3.14F / 180.0F, var4 * 3.14F / 180.0F, var5 * 3.14F / 180.0F));
      ItemStack var6 = var1.getItemStack();
      var2.setItemInHand(var6);
      var2.setCustomName(var6.hasItemMeta() && var6.getItemMeta().hasDisplayName() ? var6.getItemMeta().getDisplayName() : var6.getType().name());
      var2.setCustomNameVisible(true);
      var2.setMetadata("BRX_ITEM-DROP", new FixedMetadataValue(BattleRoyaleX.getInstance(), null));
      var1.remove();
      return true;
   }
}
