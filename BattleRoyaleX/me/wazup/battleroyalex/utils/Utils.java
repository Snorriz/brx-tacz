package me.wazup.battleroyalex.utils;

import com.google.common.collect.Lists;
import dev.lone.itemsadder.api.CustomStack;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.Constructor;
import me.DeeCaaD.CrackShotPlus.CSPapi;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Enums.SPECIAL_CHARACTER;
import me.zombie_striker.qg.api.QualityArmory;
import me.zombie_striker.qg.guns.Gun;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class Utils {
   public static final Random random = new Random();
   private static final ChatColor[] goodColors = new ChatColor[]{
      ChatColor.DARK_AQUA,
      ChatColor.GOLD,
      ChatColor.GRAY,
      ChatColor.BLUE,
      ChatColor.GREEN,
      ChatColor.AQUA,
      ChatColor.RED,
      ChatColor.LIGHT_PURPLE,
      ChatColor.YELLOW
   };
   private static final int[] craftingResultPanes = new int[]{14, 15, 16, 23, 25, 32, 33, 34};
   private static final int[] craftingIngredientsSlots = new int[]{10, 11, 12, 19, 20, 21, 28, 29, 30};
   private static final BlockFace[] radial = new BlockFace[]{
      BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST
   };
   private static final Pattern HEX_PATTERN = Pattern.compile("&#[a-fA-F0-9]{6}");

   public static ChatColor getRandomColor() {
      return goodColors[random.nextInt(goodColors.length)];
   }

   public static void error(String var0) {
      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Battle Royale X] " + var0);
   }

   public static void info(String var0) {
      Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Battle Royale X] " + var0);
   }

   public static List<Player> getPlayers(Collection<String> var0) {
      ArrayList var1 = new ArrayList();

      for (String var3 : var0) {
         if (Bukkit.getPlayer(var3) != null) {
            var1.add(Bukkit.getPlayer(var3));
         }
      }

      return var1;
   }

   public static List<Player> getOnlinePlayers() {
      ArrayList var0 = Lists.newArrayList();

      for (World var2 : Bukkit.getWorlds()) {
         var0.addAll(var2.getPlayers());
      }

      return Collections.unmodifiableList(var0);
   }

   public static String getStringFromLocation(Location var0, boolean var1) {
      return var0.getWorld().getName()
         + ", "
         + (var0.getBlockX() + (var1 ? 0.5 : 0.0))
         + ", "
         + (var0.getBlockY() + (var1 ? 1 : 0))
         + ", "
         + (var0.getBlockZ() + (var1 ? 0.5 : 0.0))
         + ", "
         + var0.getYaw()
         + ", "
         + var0.getPitch();
   }

   public static String getStringFromLocationExact(Location var0) {
      return var0.getWorld().getName() + ", " + var0.getX() + ", " + var0.getY() + ", " + var0.getZ() + ", " + var0.getYaw() + ", " + var0.getPitch();
   }

   public static Location getLocationFromString(String var0) {
      String[] var1 = var0.split(", ");
      World var2 = Bukkit.getWorld(var1[0]);
      if (var2 == null) {
         Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "battleroyale worldmanager import " + var1[0]);
         var2 = Bukkit.getWorld(var1[0]);
         if (var2 == null) {
            return null;
         }
      }

      double var3 = Double.parseDouble(var1[1]);
      double var5 = Double.parseDouble(var1[2]);
      double var7 = Double.parseDouble(var1[3]);
      float var9 = Float.parseFloat(var1[4]);
      float var10 = Float.parseFloat(var1[5]);
      return new Location(var2, var3, var5, var7, var9, var10);
   }

   public static String getReadableLocationString(Location var0, boolean var1) {
      return ""
         + ChatColor.GREEN
         + (var0.getBlockX() + (var1 ? 0.5 : 0.0))
         + ChatColor.GRAY
         + ", "
         + ChatColor.GREEN
         + (var0.getBlockY() + (var1 ? 1 : 0))
         + ChatColor.GRAY
         + ", "
         + ChatColor.GREEN
         + (var0.getBlockZ() + (var1 ? 0.5 : 0.0));
   }

   public static void cageInventory(Inventory var0, boolean var1) {
      ItemStack var2 = (ItemStack)Customization.getInstance().items.get("Pane");
      if (var1) {
         for (int var7 = 0; var7 < var0.getSize(); var7++) {
            var0.setItem(var7, var2);
         }
      } else {
         for (int var3 = 0; var3 < 9; var3++) {
            var0.setItem(var3, var2);
         }

         for (int var5 = var0.getSize() - 9; var5 < var0.getSize(); var5++) {
            var0.setItem(var5, var2);
         }

         int var6 = var0.getSize() / 9 - 2;
         if (var6 >= 1) {
            for (byte var4 = 9; var4 < 9 * var6 + 1; var4 += 9) {
               var0.setItem(var4, var2);
            }

            for (byte var8 = 17; var8 < 9 * (var6 + 1); var8 += 9) {
               var0.setItem(var8, var2);
            }
         }
      }
   }

   public static int getInventorySize(int var0) {
      return var0 < 10 ? 9 : (var0 < 19 ? 18 : (var0 < 28 ? 27 : (var0 < 37 ? 36 : (var0 < 46 ? 45 : 54))));
   }

   public static boolean compareItem(ItemStack var0, ItemStack var1) {
      if (var0 != null && var1 != null) {
         if (!var0.getType().equals(var1.getType())) {
            return false;
         } else {
            return (!var0.getItemMeta().hasDisplayName() || var1.getItemMeta().hasDisplayName())
                  && (var0.getItemMeta().hasDisplayName() || !var1.getItemMeta().hasDisplayName())
                  && (
                     !var0.getItemMeta().hasDisplayName()
                        || !var1.getItemMeta().hasDisplayName()
                        || var0.getItemMeta().getDisplayName().equals(var1.getItemMeta().getDisplayName())
                  )
               ? (!var0.getItemMeta().hasLore() || var1.getItemMeta().hasLore())
                  && (var0.getItemMeta().hasLore() || !var1.getItemMeta().hasLore())
                  && (!var0.getItemMeta().hasLore() || !var1.getItemMeta().hasLore() || var0.getItemMeta().getLore().equals(var1.getItemMeta().getLore()))
               : false;
         }
      } else {
         return false;
      }
   }

   public static void fireWorkEffect(Location var0, boolean var1) {
      if (Config.getInstance().fireworksEnabled) {
         final Firework var2 = (Firework)var0.getWorld().spawn(var0.clone().add(0.0, 1.0, 0.0), Firework.class);
         FireworkMeta var3 = var2.getFireworkMeta();
         FireworkEffect var4 = FireworkEffect.builder()
            .flicker(random.nextBoolean())
            .withColor(Color.fromBGR(random.nextInt(256), random.nextInt(256), random.nextInt(256)))
            .withFade(Color.fromBGR(random.nextInt(256), random.nextInt(256), random.nextInt(256)))
            .with(Type.values()[random.nextInt(Type.values().length)])
            .trail(random.nextBoolean())
            .build();
         var3.addEffect(var4);
         var2.setFireworkMeta(var3);
         if (var1) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(BattleRoyaleX.getInstance(), new Runnable() {
               public void run() {
                  var2.detonate();
               }
            }, 2L);
         }
      }
   }

   public static ItemStack getItemStack(String var0, boolean var1, boolean var2) {
      String[] var3 = var0.split(" : ");
      String var4 = var3[0].toUpperCase();
      ItemStackBuilder var5 = null;
      if (var4.contains(":")) {
         if (var4.split(":")[0].equals("QUALITYARMORY")) {
            ItemStack var6 = null;
            if (BattleRoyaleX.getInstance().QualityArmoryEnabled) {
               String var7 = var3[0].split(":")[1];
               if (QualityArmory.getGunByName(var7) != null) {
                  Gun var8 = QualityArmory.getGunByName(var7);
                  var6 = var8.getItemStack();
                  Gun.updateAmmo(var8, var6, var8.getMaxBullets());
               } else if (QualityArmory.getAmmoByName(var7) != null) {
                  var6 = QualityArmory.getAmmoByName(var7).getItemStack();
               } else if (QualityArmory.getArmorByName(var7) != null) {
                  var6 = QualityArmory.getArmorByName(var7).getItemStack();
               } else if (QualityArmory.getCustomItemAsItemStack(var7) != null) {
                  var6 = QualityArmory.getCustomItemAsItemStack(var7);
               }
            }

            if (var6 == null) {
               return null;
            }

            var5 = new ItemStackBuilder(var6);
         } else if (var4.split(":")[0].equals("CRACKSHOT")) {
            ItemStack var12 = null;
            if (BattleRoyaleX.getInstance().crackShotAPI != null) {
               String var21 = var3[0].split(":")[1];
               var12 = BattleRoyaleX.getInstance().crackShotAPI.generateWeapon(var21);
            }

            if (var12 == null) {
               return null;
            }

            var5 = new ItemStackBuilder(var12);
         } else if (var4.split(":")[0].equals("CRACKSHOTPLUS")) {
            ItemStack var13 = null;
            if (BattleRoyaleX.getInstance().CrackShotPlusEnabled) {
               String var22 = var3[0].split(":")[1];
               var13 = CSPapi.updateItemStackFeaturesNonPlayer(var22, new ItemStack(Material.STICK));
               if (var13 == null) {
                  var13 = CSPapi.getAttachmentItemStack(var22);
               }
            }

            if (var13 == null) {
               return null;
            }

            var5 = new ItemStackBuilder(var13);
         } else if (var4.split(":")[0].equals("TACZ") || var4.split(":")[0].equals("TIMELESS") || var4.split(":")[0].equals("TAKECHAIMSGUNS") || var4.split(":")[0].equals("TACZAMMO") || var4.split(":")[0].equals("AMMOTACZ")) {
            // Timeless and Classy Guns (TaCZ, Forge mod) support: handled through reflection
            // because it's a Forge mod running on ArcLight (Forge+Bukkit hybrid server),
            // so the plugin cannot be compiled against its classes directly.
            // Syntax examples:
            //   TACZ:tacz:ak47 : 1
            //   TACZ:bbm:kar98k : 1
            //   TIMELESS:ak47 : 1          (defaults to the "tacz" namespace)
            // An optional third colon-separated field sets the starting ammo count:
            //   TACZ:tacz:ak47:30 : 1
            ItemStack var32 = getTaczItemStack(var3[0]);
            if (var32 == null) {
               return null;
            }

            var5 = new ItemStackBuilder(var32);
         } else if (var4.split(":")[0].equals("ITEMEDIT")) {
            ItemStack var14 = null;
            if (BattleRoyaleX.getInstance().itemEdit != null) {
               String var23 = var3[0].split(":")[1];
               var14 = BattleRoyaleX.getInstance().itemEdit.getItem(var23);
            }

            if (var14 == null) {
               return null;
            }

            var5 = new ItemStackBuilder(var14);
         } else if (var4.split(":")[0].equals("WEAPONMECHANICS")) {
            ItemStack var15 = null;
            if (BattleRoyaleX.getInstance().weaponMechanicsAPI != null) {
               String var24 = var3[0].split(":")[1];

               try {
                  var15 = (ItemStack)BattleRoyaleX.getInstance()
                     .weaponMechanicsAPI
                     .getMethod("generateWeapon", new Class[]{String.class})
                     .invoke(null, new Object[]{var24});
               } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var11) {
                  var11.printStackTrace();
               }
            }

            if (var15 == null) {
               return null;
            }

            var5 = new ItemStackBuilder(var15);
         } else if (var4.split(":")[0].equals("ITEMSADDER")) {
            if (BattleRoyaleX.getInstance().ItemsAdderEnabled) {
               String var16 = var3[0].split(":")[1];
               CustomStack var25 = CustomStack.getInstance(var16);
               if (var25 == null) {
                  return null;
               }

               var5 = new ItemStackBuilder(var25.getItemStack());
            }
         } else if (var4.split(":")[0].equals("MMOITEMS")) {
            if (BattleRoyaleX.getInstance().mmoItems != null) {
               String var17 = var3[0].split(":")[1];
               String var26 = var3[0].split(":")[2];
               ItemStack var30 = null;

               try {
                  var30 = (ItemStack)BattleRoyaleX.getInstance()
                     .mmoItems
                     .getMethod("getItem", new Class[]{String.class, String.class})
                     .invoke(BattleRoyaleX.getInstance().mmoItemsInstance, new Object[]{var17, var26});
               } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var10) {
                  var10.printStackTrace();
               }

               if (var30 == null) {
                  return null;
               }

               var5 = new ItemStackBuilder(var30);
            }
         } else if (var4.split(":")[0].equals("BATTLEROYALEX")) {
            ItemStack var18 = null;
            String var27 = var3[0].split(":")[1];
            if (var27.equalsIgnoreCase("Carepackage")) {
               var18 = new ItemStackBuilder(Material.ENDER_CHEST).setName(ChatColor.YELLOW + "Carepackage").build();
            } else if (var27.equalsIgnoreCase("Cash")) {
               var18 = new ItemStackBuilder(Material.GOLD_INGOT)
                  .setName(ChatColor.GRAY + "Cash: $" + ChatColor.GREEN + var3[0].split(":")[2])
                  .addLore(new String[]{ChatColor.GRAY + "Right click to claim!"})
                  .build();
            }

            if (var18 == null) {
               return null;
            }

            var5 = new ItemStackBuilder(var18);
         }
      }

      if (var5 == null) {
         Optional var19 = XMaterial.matchXMaterial(var4);
         if (!var19.isPresent() && var4.contains(":")) {
            var19 = XMaterial.matchXMaterial(var4.split(":")[0]);
         }

         var5 = new ItemStackBuilder(((XMaterial)var19.get()).parseItem());
         if (var3[0].contains(":")) {
            String var28 = var4.split(":")[0];
            if (var28.contains("POTION") && var4.split(":").length == 4) {
               var5.setPotionEffect(PotionType.valueOf(var4.split(":")[1]), Boolean.valueOf(var3[0].split(":")[2]), Boolean.valueOf(var3[0].split(":")[3]));
            } else {
               var5.setDurability(Integer.parseInt(var4.split(":")[1]));
            }
         }
      }

      if (var1) {
         var5.setAmount(Integer.parseInt(var3[1]));
      }

      if (var2) {
         for (int var20 = var1 ? 2 : 1; var20 < var3.length; var20++) {
            String var29 = var3[var20].split(":")[0].toLowerCase();
            if (var29.equals("name")) {
               var5.setName(colorize(var3[var20].split(":")[1]));
            } else if (var29.equals("lore")) {
               var5.addLore(new String[]{colorize(var3[var20].split(":")[1])});
            } else if (var29.equals("enchant")) {
               String var31 = var3[var20].split(":")[1].toUpperCase();
               var5.addEnchantment(Enchantment.getByName(var31), Integer.parseInt(var3[var20].split(":")[2]));
            }
         }
      }

      return var5.build();
   }

   public static ItemStack getTaczItemStack(String rawToken) {
      if (BattleRoyaleX.getInstance().taczApi == null) {
         return null;
      } else {
         try {
            // NOTE: getItemStack() uppercases the first segment of the token for routing
            // (var4), but passes us var3[0] which keeps the original config casing.
            // To be safe against both behaviours we take the namespace/gun/ammo segments
            // from the ORIGINAL string and only uppercase the routing prefix ourselves.
            // Expected token formats:
            //   TACZ:<namespace>:<gunId>[:<ammoCount>]
            //   TIMELESS:<gunId>[:<ammoCount>]        (namespace defaults to "tacz")
            //   AMMO:<namespace>:<caliber>  /  TACZAMMO:<ns>:<caliber>  -> ammo stack
            String[] parts = rawToken.split(":");
            String prefix = parts[0].toUpperCase();
            String namespace = "tacz";
            String gunId = null;
            int ammoCount = -1;
            if (prefix.equals("TAKECHAIMSGUNS")) {
               // TAKECHAIMSGUNS is the legacy full name of the mod; it always means the default pack
               if (parts.length >= 3) {
                  // TIMELESS-style explicit namespace still supported: TAKECHAIMSGUNS:<ns>:<gun>[:ammo]
                  namespace = parts[1];
                  gunId = parts[2];
                  if (parts.length >= 4) {
                     try {
                        ammoCount = Integer.parseInt(parts[3]);
                     } catch (NumberFormatException ignored) {
                     }
                  }
               } else if (parts.length == 2) {
                  gunId = parts[1];
               }
            } else if (parts.length >= 3) {
               namespace = parts[1];
               gunId = parts[2];
               if (parts.length >= 4) {
                  try {
                     ammoCount = Integer.parseInt(parts[3]);
                  } catch (NumberFormatException ignored) {
                  }
               }
            } else if (parts.length == 2) {
               gunId = parts[1];
            }

            if (prefix.equals("TACZAMMO") || prefix.equals("AMMOTACZ")) {
               // Ammo token: TACZAMMO:<namespace>:<caliber>[:count] -> TaCZ ammo item stack.
               String caliber = parts[2];
               int count = -1;
               if (parts.length >= 4) {
                  try {
                     count = Integer.parseInt(parts[3]);
                  } catch (NumberFormatException ignored) {
                  }
               }
               return getTaczAmmoStack(namespace, caliber, count);
            }

            if (gunId == null || gunId.isEmpty()) {
               return null;
            }

            Object gunRL = BattleRoyaleX.getInstance().resourceLocationCtor.newInstance(namespace, gunId);
            Object builder = taczGunItemBuilderCreate.invoke(null);
            taczGunItemBuilderSetId.invoke(builder, gunRL);

            int effectiveAmmo = ammoCount;
            if (effectiveAmmo < 0) {
               // No explicit ammo requested: fill the magazine with the gun's default ammo amount
               Object gunIndex = timelessApiGetCommonGunIndex.invoke(null, gunRL);
               boolean present = (Boolean)taczOptionalIsPresent.invoke(gunIndex);
               if (present) {
                  Object maxAmmo = null;
                  // Preferred path: CommonGunIndex#getGunData().getAmmoAmount()
                  if (taczCommonGunIndexGetGunData != null && taczGunDataGetAmmoAmount != null) {
                     try {
                        Object gunData = taczCommonGunIndexGetGunData.invoke(gunIndex);
                        if (gunData != null) {
                           maxAmmo = taczGunDataGetAmmoAmount.invoke(gunData);
                        }
                     } catch (Throwable ignored) {
                     }
                  }

                  // Fallback path: CommonGunIndex#getPojo().getData() -> CommonAssetsManager.getGunData(rl).getAmmoAmount()
                  if (!(maxAmmo instanceof Integer)
                        && taczCommonGunIndexGetPojo != null
                        && taczGunIndexPojoGetData != null
                        && taczGunDataGetAmmoAmount != null) {
                     try {
                        Object pojo = taczCommonGunIndexGetPojo.invoke(gunIndex);
                        Object dataRL = taczGunIndexPojoGetData.invoke(pojo);
                        Object gunData = commonAssetsManagerGetInstance.invoke(null);
                        if (gunData != null && dataRL != null) {
                           Object resolvedData = commonAssetsGetGunData.invoke(gunData, dataRL);
                           if (resolvedData != null) {
                              maxAmmo = taczGunDataGetAmmoAmount.invoke(resolvedData);
                           }
                        }
                     } catch (Throwable ignored) {
                     }
                  }

                  if (maxAmmo instanceof Integer) {
                     effectiveAmmo = (Integer)maxAmmo;
                  }
               }
            }

            if (effectiveAmmo >= 0) {
               taczGunItemBuilderSetAmmoCount.invoke(builder, effectiveAmmo);
            }

            // GunItemBuilder leaves the fire mode as UNKNOWN and the barrel empty by default.
            // Bolt-action / single-shot guns refuse to cycle their bolt unless a round is
            // chambered, so when available we set the builder's own fire-mode / ammo-in-barrel
            // options BEFORE build(); otherwise we fall back to post-build IGun NBT editing.
            Object semiMode = null;
            if (taczFireModeSemi != null) {
               semiMode = taczFireModeSemi.get(null);
            } else if (taczFireModeValues != null) {
               Object allModes = taczFireModeValues.invoke(null);
               if (allModes instanceof Object[]) {
                  for (Object m : (Object[])allModes) {
                     if ("SEMI".equals(m.toString())) {
                        semiMode = m;
                        break;
                     }
                  }
               }
            }

            boolean configuredBeforeBuild = false;
            try {
               if (semiMode != null && taczGunItemBuilderSetFireMode != null) {
                  taczGunItemBuilderSetFireMode.invoke(builder, semiMode);
                  if (effectiveAmmo > 0 && taczGunItemBuilderSetAmmoInBarrel != null) {
                     taczGunItemBuilderSetAmmoInBarrel.invoke(builder, Boolean.TRUE);
                  }
                  configuredBeforeBuild = true;
               }
            } catch (IllegalAccessException | InvocationTargetException ignored) {
               // Older/newer TaCZ builds without these builder options: use the IGun fallback below
            }

            // On ArcLight, TaCZ returns a NET.MINECRAFT ItemStack which CANNOT be cast to
            // org.bukkit.inventory.ItemStack (different modules). We bridge it via NMS CraftItemStack.
            Object rawBuilt = taczGunItemBuilderBuild.invoke(builder);
            ItemStack result = asBukkitItemStack(rawBuilt);
            if (result != null && !result.getType().equals(Material.AIR)) {
               if (!configuredBeforeBuild) {
                  try {
                     Object iGun = taczIGunGetIGunOrNull.invoke(null, rawBuilt);
                     if (iGun != null) {
                        if (semiMode != null && taczIGunSetFireMode != null) {
                           taczIGunSetFireMode.invoke(iGun, rawBuilt, semiMode);
                        }

                        if (taczIGunGetCurrentAmmoCount != null && taczIGunSetCurrentAmmoCount != null) {
                           int magazine = effectiveAmmo >= 0 ? effectiveAmmo : 30;
                           if (magazine > 0) {
                              int current = (Integer)taczIGunGetCurrentAmmoCount.invoke(iGun, rawBuilt);
                              if (current > 0) {
                                 taczIGunSetCurrentAmmoCount.invoke(iGun, rawBuilt, current - 1);
                                 if (taczIGunSetBulletInBarrel != null) {
                                    taczIGunSetBulletInBarrel.invoke(iGun, rawBuilt, Boolean.TRUE);
                                 }
                              }
                           }
                        }
                     }
                  } catch (Throwable ignored) {
                     // Post-processing is best-effort: a failure here must not lose the gun item
                  }
               }

               return result;
            } else {
               return null;
            }
         } catch (IllegalAccessException | InvocationTargetException | InstantiationException var7) {
            error("Could not generate TaCZ item for \"" + rawToken + "\"");
            var7.printStackTrace();
            return null;
         }
      }
   }

   /**
    * Converts an arbitrary object returned by reflection into a Bukkit ItemStack.
    * On ArcLight, mods like TaCZ hand back net.minecraft.world.item.ItemStack objects,
    * which live in a different module than org.bukkit.inventory.ItemStack and therefore
    * cannot be cast directly. We bridge through CraftItemStack.asBukkitCopy (reflection,
    * so this class still compiles against plain Paper API without NMS on the classpath).
    */
   public static ItemStack asBukkitItemStack(Object obj) {
      if (obj == null) {
         return null;
      }
      if (obj instanceof ItemStack) {
         return (ItemStack)obj;
      }
      try {
         String pkg = org.bukkit.Bukkit.getServer().getClass().getPackage().getName();
         String version = pkg.substring(pkg.lastIndexOf('.') + 1);
         Class<?> craftItemStackClass = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack");
         Method asBukkitCopy = craftItemStackClass.getMethod("asBukkitCopy", obj.getClass());
         Object converted = asBukkitCopy.invoke(null, obj);
         if (converted instanceof ItemStack) {
            return (ItemStack)converted;
         }
      } catch (Throwable ignored) {
      }
      // Fallback: some loaders give us a wrapper whose toString/type still works via clone trick
      try {
         java.lang.reflect.Method getBukkitStack = obj.getClass().getMethod("getBukkitStack");
         Object converted = getBukkitStack.invoke(obj);
         if (converted instanceof ItemStack) {
            return (ItemStack)converted;
         }
      } catch (Throwable ignored) {
      }
      return null;
   }

   // ---- TaCZ reflection method handles (populated by initTaczHook) ----
   static Method timelessApiGetCommonGunIndex;
   static Method timelessApiGetCommonAmmoIndex;
   static Method taczAmmoItemBuilderCreate;
   static Method taczAmmoItemBuilderSetId;
   static Method taczAmmoItemBuilderSetCount;
   static Method taczAmmoItemBuilderBuild;
   static Method taczCommonAmmoIndexGetStack;

   /**
    * Builds a TaCZ ammo item stack via AmmoItemBuilder (fallback: CommonAmmoIndex#getStack()).
    * count <= 0 means "one full logical stack" as defined by the ammo's stack_size.
    */
   public static ItemStack getTaczAmmoStack(String namespace, String caliber, int count) {
      if (BattleRoyaleX.getInstance().taczApi == null || taczAmmoItemBuilderCreate == null) {
         return null;
      }
      try {
         Object ammoRL = BattleRoyaleX.getInstance().resourceLocationCtor.newInstance(namespace, caliber);
         Object builder = taczAmmoItemBuilderCreate.invoke(null);
         taczAmmoItemBuilderSetId.invoke(builder, ammoRL);

         int effectiveCount = count;
         if (effectiveCount <= 0 && timelessApiGetCommonAmmoIndex != null) {
            // Fill to the ammo's configured stack size (e.g. 42 for 9mm, 60 for 7.62x39)
            try {
               Object opt = timelessApiGetCommonAmmoIndex.invoke(null, ammoRL);
               Boolean present = (Boolean)taczOptionalIsPresent.invoke(opt);
               if (present && taczCommonAmmoIndexGetStack != null) {
                  Object ammoIndex = java.util.Optional.class.getMethod("get").invoke(opt);
                  Object stackSize = taczCommonAmmoIndexGetStack.invoke(ammoIndex);
                  if (stackSize instanceof Integer) {
                     effectiveCount = (Integer)stackSize;
                  }
               }
            } catch (Throwable ignored) {
            }
         }
         if (effectiveCount > 0 && taczAmmoItemBuilderSetCount != null) {
            taczAmmoItemBuilderSetCount.invoke(builder, effectiveCount);
         }
         Object rawAmmoBuilt = taczAmmoItemBuilderBuild.invoke(builder);
         ItemStack result = asBukkitItemStack(rawAmmoBuilt);
         if (result != null && !result.getType().equals(Material.AIR)) {
            return result;
         }
         return null;
      } catch (Throwable var6) {
         error("Could not generate TaCZ ammo item for \"" + namespace + ":" + caliber + "\"");
         return null;
      }
   }

   static Method taczGunItemBuilderCreate;
   static Method taczGunItemBuilderSetId;
   static Method taczGunItemBuilderSetAmmoCount;
   static Method taczGunItemBuilderSetFireMode;
   static Method taczGunItemBuilderSetAmmoInBarrel;
   static Method taczGunItemBuilderBuild;
   static Method taczOptionalIsPresent;
   static Method taczCommonGunIndexGetGunData;
   static Method taczCommonGunIndexGetPojo;
   static Method taczGunIndexPojoGetData;
   static Method taczGunDataGetAmmoAmount;
   static Method commonAssetsManagerGetInstance;
   static Method commonAssetsGetGunData;
   static Method taczIGunGetIGunOrNull;
   static Method taczIGunSetFireMode;
   static Method taczIGunSetBulletInBarrel;
   static Method taczIGunGetCurrentAmmoCount;
   static Method taczIGunSetCurrentAmmoCount;
   static java.lang.reflect.Field taczFireModeSemi;
   static Method taczFireModeValues;
   static Method taczFireModeValueOf;

   private static Method taczGetMethod(ClassLoader classLoader, String className, String methodName, Class<?>... params) {
      try {
         Class<?> clazz = loadTaCZClass(classLoader, className);
         return clazz.getMethod(methodName, params);
      } catch (Throwable var5) {
         return null;
      }
   }

   public static void initTaczHook(BattleRoyaleX plugin) {
      plugin.taczApi = null;
      plugin.resourceLocationCtor = null;
      try {
         // On ArcLight (Forge+Bukkit hybrid) the mod classes are loaded by the
         // plugin's own classloader, but we also try Forge's mod-aware classloader
         // as a fallback so the hook works on other loaders too.
         ClassLoader classLoader = plugin.getClass().getClassLoader();
         Class<?> timelessApiClass = loadTaCZClass(classLoader, "com.tacz.guns.api.TimelessAPI");
         Class<?> gunItemBuilderClass = loadTaCZClass(classLoader, "com.tacz.guns.api.item.builder.GunItemBuilder");
         Class<?> commonGunIndexClass = loadTaCZClass(classLoader, "com.tacz.guns.resource.index.CommonGunIndex");
         Class<?> gunIndexPojoClass = loadTaCZClass(classLoader, "com.tacz.guns.resource.pojo.GunIndexPOJO");
         Class<?> gunDataClass = loadTaCZClass(classLoader, "com.tacz.guns.resource.pojo.data.gun.GunData");
         Class<?> assetsManagerClass = loadTaCZClass(classLoader, "com.tacz.guns.resource.CommonAssetsManager");
         Class<?> resourceLocationClass = loadTaCZClass(classLoader, "net.minecraft.resources.ResourceLocation");
         Class<?> itemStackClass = loadTaCZClass(classLoader, "net.minecraft.world.item.ItemStack");
         Class<?> iGunClass = loadTaCZClass(classLoader, "com.tacz.guns.api.item.IGun");
         Class<?> fireModeClass = loadTaCZClass(classLoader, "com.tacz.guns.api.item.gun.FireMode");

         plugin.resourceLocationCtor = resourceLocationClass.getConstructor(String.class, String.class);

         timelessApiGetCommonGunIndex = timelessApiClass.getMethod("getCommonGunIndex", resourceLocationClass);
         // Ammo-side handles (best-effort: TACZAMMO tokens degrade gracefully if missing)
         timelessApiGetCommonAmmoIndex = taczGetMethod(classLoader, "com.tacz.guns.api.TimelessAPI", "getCommonAmmoIndex", resourceLocationClass);
         Class<?> ammoItemBuilderClass = null;
         try {
            ammoItemBuilderClass = loadTaCZClass(classLoader, "com.tacz.guns.api.item.builder.AmmoItemBuilder");
         } catch (Throwable ignored) {
         }
         if (ammoItemBuilderClass != null) {
            taczAmmoItemBuilderCreate = ammoItemBuilderClass.getMethod("create");
            taczAmmoItemBuilderSetId = ammoItemBuilderClass.getMethod("setId", resourceLocationClass);
            taczAmmoItemBuilderBuild = ammoItemBuilderClass.getMethod("build");
            try {
               taczAmmoItemBuilderSetCount = ammoItemBuilderClass.getMethod("setCount", int.class);
            } catch (NoSuchMethodException var7x) {
               taczAmmoItemBuilderSetCount = null;
            }
         }
         Class<?> commonAmmoIndexClass = null;
         try {
            commonAmmoIndexClass = loadTaCZClass(classLoader, "com.tacz.guns.resource.index.CommonAmmoIndex");
         } catch (Throwable ignored) {
         }
         if (commonAmmoIndexClass != null) {
            try {
               taczCommonAmmoIndexGetStack = commonAmmoIndexClass.getMethod("getStack");
            } catch (NoSuchMethodException var7y) {
               taczCommonAmmoIndexGetStack = null;
            }
         }
         taczGunItemBuilderCreate = gunItemBuilderClass.getMethod("create");
         taczGunItemBuilderSetId = gunItemBuilderClass.getMethod("setId", resourceLocationClass);
         taczGunItemBuilderSetAmmoCount = gunItemBuilderClass.getMethod("setAmmoCount", int.class);
         taczGunItemBuilderSetFireMode = gunItemBuilderClass.getMethod("setFireMode", fireModeClass);
         taczGunItemBuilderSetAmmoInBarrel = gunItemBuilderClass.getMethod("setAmmoInBarrel", boolean.class);
         taczGunItemBuilderBuild = gunItemBuilderClass.getMethod("build");
         taczOptionalIsPresent = java.util.Optional.class.getMethod("isPresent");
         taczCommonGunIndexGetGunData = commonGunIndexClass.getMethod("getGunData");
         taczCommonGunIndexGetPojo = commonGunIndexClass.getMethod("getPojo");
         taczGunIndexPojoGetData = gunIndexPojoClass.getMethod("getData");
         taczGunDataGetAmmoAmount = gunDataClass.getMethod("getAmmoAmount");
         commonAssetsManagerGetInstance = assetsManagerClass.getMethod("getInstance");
         commonAssetsGetGunData = taczGetMethod(timelessApiClass.getClassLoader(), "com.tacz.guns.resource.CommonAssetsManager", "getGunData", resourceLocationClass);
         if (commonAssetsGetGunData == null) {
            // The instance method may live on the ICommonResourceProvider interface instead
            commonAssetsGetGunData = taczGetMethod(timelessApiClass.getClassLoader(), "com.tacz.guns.resource.ICommonResourceProvider", "getGunData", resourceLocationClass);
         }

         taczIGunGetIGunOrNull = iGunClass.getMethod("getIGunOrNull", itemStackClass);
         taczIGunSetFireMode = taczGetMethod(timelessApiClass.getClassLoader(), "com.tacz.guns.api.item.IGun", "setFireMode", itemStackClass, fireModeClass);
         taczIGunSetBulletInBarrel = taczGetMethod(timelessApiClass.getClassLoader(), "com.tacz.guns.api.item.IGun", "setBulletInBarrel", itemStackClass, boolean.class);
         taczIGunGetCurrentAmmoCount = taczGetMethod(timelessApiClass.getClassLoader(), "com.tacz.guns.api.item.IGun", "getCurrentAmmoCount", itemStackClass);
         taczIGunSetCurrentAmmoCount = taczGetMethod(timelessApiClass.getClassLoader(), "com.tacz.guns.api.item.IGun", "setCurrentAmmoCount", itemStackClass, int.class);

         try {
            taczFireModeSemi = fireModeClass.getField("SEMI");
         } catch (NoSuchFieldException var8) {
            taczFireModeSemi = null;
            taczFireModeValues = taczGetMethod(timelessApiClass.getClassLoader(), "com.tacz.guns.api.item.gun.FireMode", "values");
            taczFireModeValueOf = taczGetMethod(timelessApiClass.getClassLoader(), "com.tacz.guns.api.item.gun.FireMode", "valueOf", String.class);
         }

         plugin.taczApi = timelessApiClass;
         info("Successfully hooked into Timeless and Classy Guns (TaCZ) API.");
      } catch (ClassNotFoundException | NoSuchMethodException | NoSuchMethodError | NoClassDefFoundError var9) {
         plugin.taczApi = null;
         error("Could not hook to TaCZ (Timeless and Classy Guns). TaCZ guns will not be available.");
      } catch (Throwable var10) {
         plugin.taczApi = null;
         error("Unexpected error while hooking to TaCZ: " + var10.getMessage());
      }
   }

   private static Class<?> loadTaCZClass(ClassLoader primary, String name) throws ClassNotFoundException {
      ClassLoader[] candidates = new ClassLoader[]{
            primary,
            Thread.currentThread().getContextClassLoader(),
            ClassLoader.getSystemClassLoader(),
            Utils.class.getClassLoader()
      };

      ClassNotFoundException last = null;
      for (ClassLoader cl : candidates) {
         if (cl == null) {
            continue;
         }

         try {
            return Class.forName(name, true, cl);
         } catch (ClassNotFoundException | NoClassDefFoundError var6) {
            last = var6 instanceof ClassNotFoundException ? (ClassNotFoundException)var6 : new ClassNotFoundException(name, var6);
         }
      }

      // Last resort: ArcLight loads Forge mods inside plugin/launcher classloaders that we
      // cannot guess statically, so scan every Bukkit plugin classloader and the server's
      // own classloader for the TaCZ main class, then reuse the loader that owns it.
      if (name.startsWith("com.tacz.") || name.startsWith("net.minecraft.")) {
         java.util.List<ClassLoader> loaders = new java.util.ArrayList();
         loaders.add(Bukkit.class.getClassLoader());

         for (org.bukkit.plugin.Plugin p : Bukkit.getPluginManager().getPlugins()) {
            try {
               loaders.add(p.getClass().getClassLoader());
            } catch (Throwable ignored) {
            }
         }

         for (ClassLoader cl : loaders) {
            if (cl == null) {
               continue;
            }

            try {
               return Class.forName(name, true, cl);
            } catch (ClassNotFoundException | NoClassDefFoundError ignored) {
            }
         }
      }

      throw last != null ? last : new ClassNotFoundException(name);
   }

   public static ItemStack getSkull(String var0, String var1) {
      ItemStack var2 = XMaterial.PLAYER_HEAD.parseItem();
      SkullMeta var3 = (SkullMeta)var2.getItemMeta();
      if (Config.getInstance().loadSkinsOnSkulls) {
         var3.setOwner(var0);
      }

      var3.setDisplayName(var1);
      var2.setItemMeta(var3);
      return var2;
   }

   public static Inventory getCraftingInventory(ItemStack var0, Collection<ItemStack> var1) {
      Inventory var2 = Bukkit.createInventory(null, 45, (String)Customization.getInstance().inventories.get("Recipe"));
      cageInventory(var2, false);
      ItemStack var3 = (ItemStack)Customization.getInstance().items.get("Pane");
      var2.setItem(13, var3);
      var2.setItem(22, var3);
      var2.setItem(31, var3);
      ItemStack var4 = (ItemStack)Customization.getInstance().items.get("Green Pane");

      for (int var8 : craftingResultPanes) {
         var2.setItem(var8, var4);
      }

      var2.setItem(24, var0);
      var2.setItem(40, (ItemStack)Customization.getInstance().items.get("Back"));
      int var9 = 0;

      for (ItemStack var11 : var1) {
         var2.setItem(craftingIngredientsSlots[var9++], var11);
      }

      return var2;
   }

   public static boolean checkNumbers(String... var0) {
      try {
         for (String var4 : var0) {
            Integer.parseInt(var4);
         }

         return true;
      } catch (NumberFormatException var5) {
         return false;
      }
   }

   public static void fillChest(Block var0, ChestType var1, Location var2) {
      int var3 = random.nextInt(var1.maxItems - var1.minItems + 1) + var1.minItems;
      if (Config.getInstance().realisticChests) {
         RealisticItems.getInstance().playChestAnimation(var0, true);
         ArrayList var4 = new ArrayList();

         for (int var5 = 0; var5 < var3; var5++) {
            Object var6 = null;
            int var7 = 0;

            while (var7 < 3 && var4.contains(var6 = var1.content.getRandom(random))) {
               var7++;
            }

            var4.add(var6);
         }

         RealisticItems.getInstance().giveItemsFromBlock(var0, var4, var2);
      } else {
         Chest var9 = (Chest)var0.getState();
         var9.getInventory().clear();

         for (int var10 = 0; var10 < var3; var10++) {
            int var11 = 0;
            int var12 = 0;

            while (var12 < 3 && var9.getInventory().getItem(var11 = random.nextInt(var9.getInventory().getSize())) != null) {
               var12++;
            }

            ItemStack var13 = null;
            int var8 = 0;

            while (var8 < 3 && var9.getInventory().contains((var13 = var1.content.getRandom(random)).getType())) {
               var8++;
            }

            var9.getInventory().setItem(var11, var13);
         }
      }
   }

   public static String randomizeList(HashMap<String, Integer> var0) {
      int var1 = 0;

      for (int var3 : var0.values()) {
         var1 += var3;
      }

      int var6 = random.nextInt(var1);
      int var7 = 0;

      for (String var5 : var0.keySet()) {
         var7 += var0.get(var5);
         if (var6 >= var1 - var7) {
            return var5;
         }
      }

      return null;
   }

   public static String getArmorString(double var0, double var2) {
      String var4 = "";
      int var5 = (int)(var0 / var2 * 12.0);

      for (int var6 = 0; var6 < var5; var6++) {
         if (var6 % 4 == 0) {
            var4 = var4 + " ";
         }

         var4 = var4 + SPECIAL_CHARACTER.BOX.toString();
      }

      for (int var7 = var5; var7 < 12; var7++) {
         if (var7 % 4 == 0) {
            var4 = var4 + " ";
         }

         var4 = var4 + SPECIAL_CHARACTER.EMPTY_BOX.toString();
      }

      return var4;
   }

   public static String getProgressBar(int var0, int var1) {
      String var2 = "";

      for (int var3 = 0; var3 < var0; var3++) {
         var2 = var2 + ChatColor.GREEN + SPECIAL_CHARACTER.RECTANGLE.toString();
      }

      for (int var4 = 0; var4 < var1 - var0; var4++) {
         var2 = var2 + ChatColor.GRAY + SPECIAL_CHARACTER.RECTANGLE.toString();
      }

      return (String)var2;
   }

   public static void removeAI(LivingEntity var0) throws java.lang.reflect.InvocationTargetException, java.lang.IllegalAccessException, java.lang.NoSuchMethodException {
      if (!BattleRoyaleX.getInstance().below1_10) {
         var0.setAI(false);
      } else {
         Object var1 = var0.getClass().getDeclaredMethod("getHandle", null).invoke(var0, new Object[0]);
         Object var2 = var1.getClass().getMethod("getDataWatcher", null).invoke(var1, new Object[0]);

         for (Method var6 : var2.getClass().getDeclaredMethods()) {
            if (var6.getName().equals("watch")) {
               var6.invoke(var2, new Object[]{15, (byte)1});
               break;
            }
         }
      }
   }

   public static void updateSkull(Sign var0, String var1) {
      if (var1.contains("NO_PLAYER")) {
         var1 = "MHF_Question";
      }

      Location[] var2 = new Location[]{
         var0.getLocation().add(0.0, 1.0, 0.0),
         var0.getLocation().clone().add(1.0, 1.0, 0.0),
         var0.getLocation().clone().add(-1.0, 1.0, 0.0),
         var0.getLocation().clone().add(0.0, 1.0, 1.0),
         var0.getLocation().clone().add(0.0, 1.0, -1.0)
      };

      for (Location var6 : var2) {
         if (var6.getBlock().getState() instanceof Skull) {
            Skull var7 = (Skull)var6.getBlock().getState();
            var7.setOwner(var1);
            var7.update();
            break;
         }
      }
   }

   public static void makeSpectator(Player var0) {
      var0.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 3, true));
      var0.setAllowFlight(true);
      var0.setFlying(true);
      var0.getInventory().setContents((ItemStack[])Config.getInstance().hotbarItems.get("Spectator"));
   }

   public static BlockFace yawToFace(float var0) {
      return radial[Math.round(var0 / 45.0F) & 7].getOppositeFace();
   }

   public static byte byteFromFace(BlockFace var0) {
      switch (var0) {
         case SOUTH:
            return 0;
         case SOUTH_WEST:
            return 2;
         case WEST:
            return 4;
         case NORTH_WEST:
            return 6;
         case NORTH_EAST:
            return 10;
         case EAST:
            return 12;
         case SOUTH_EAST:
            return 14;
         default:
            return 8;
      }
   }

   public static String colorize(String var0) {
      for (Matcher var1 = HEX_PATTERN.matcher(var0); var1.find(); var1 = HEX_PATTERN.matcher(var0)) {
         String var2 = var0.substring(var1.start(), var1.end());
         String var3 = var2.replace("&#", "x");
         char[] var4 = var3.toCharArray();
         StringBuilder var5 = new StringBuilder();

         for (char var9 : var4) {
            var5.append("&").append(var9);
         }

         var0 = var0.replace(var2, var5.toString());
      }

      return ChatColor.translateAlternateColorCodes('&', var0);
   }
}
