package me.wazup.battleroyalex.managers;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.shop.Kit;
import me.wazup.battleroyalex.utils.SmartInventory;
import me.wazup.battleroyalex.utils.Enums.AchievementType;
import me.wazup.battleroyalex.utils.Enums.Rarity;
import me.zombie_striker.customitemmanager.CustomBaseObject;
import me.zombie_striker.qg.ammo.Ammo;
import me.zombie_striker.qg.api.QualityArmory;
import me.zombie_striker.qg.armor.ArmorObject;
import me.zombie_striker.qg.guns.Gun;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DefaultsManager {
   public static void createDefaultKits(FileConfiguration var0) {
      // TaxBattle: kit unico di default con le basi TaCZ (pistola + munizioni + mischia).
      HashMap<String, String> var1 = new HashMap<>();
      HashMap<String, List<String>> var2 = new HashMap<>();
      HashMap<String, List<String>> var3 = new HashMap<>();
      var1.put("TaxBattle", "TACZ:tacz:glock_17 : 1, false, COMMON, 0, MM_ARMOR:mm_armor:helmet_6b_47emrribbon_helmet, MM_ARMOR:mm_armor:vest_6b_45_chestplate, MM_ARMOR:mm_armor:emruniformribbon_leggins, MM_ARMOR:mm_armor:desertboots_boots");
      var2.put("TaxBattle", Arrays.asList(new String[]{
         "TACZ:tacz:glock_17 : 1 :: name:&eGlock 17",
         "TACZAMMO:tacz:9mm : 1 :: name:&eMunizioni 9mm",
         "LRTACTICAL:lrtactical:melee : 1 :: name:&eColtello Tattico",
         "GOLDEN_APPLE : 1 :: name:&cMela d'Oro"
      }));
      var3.put("TaxBattle", Arrays.asList(new String[]{"&7Il tuo equipaggiamento base:", "&7Pistola, munizioni e armatura Miklos."}));

      StringBuilder varSb = new StringBuilder();
      for (String key : var1.keySet()) {
         varSb.append("Kits.").append(key).append(".item: ").append(var1.get(key)).append("\n");
         varSb.append("Kits.").append(key).append(".permission: ''\n");
         varSb.append("Kits.").append(key).append(".rarity: COMMON\n");
         varSb.append("Kits.").append(key).append(".value: 0\n");
         String[] armor = var1.get(key).split(", ");
         varSb.append("Kits.").append(key).append(".boots: ").append(armor[armor.length - 1]).append("\n");
         varSb.append("Kits.").append(key).append(".leggings: ").append(armor[armor.length - 2]).append("\n");
         varSb.append("Kits.").append(key).append(".chestplate: ").append(armor[armor.length - 3]).append("\n");
         varSb.append("Kits.").append(key).append(".helmet: ").append(armor[armor.length - 4]).append("\n");
         varSb.append("Kits.").append(key).append(".items:\n");
         for (String it : var2.get(key)) {
            varSb.append("- '").append(it).append("'\n");
         }
         varSb.append("Kits.").append(key).append(".description:\n");
         for (String de : var3.get(key)) {
            varSb.append("- '").append(de).append("'\n");
         }
      }
      try {
         java.io.FileWriter fw = new java.io.FileWriter(new java.io.File(BattleRoyaleX.getInstance().getDataFolder(), "kits.yml"));
         fw.write(varSb.toString());
         fw.close();
      } catch (Exception ignored) {
      }

      // Ricarica il FileConfiguration in memoria cosi' il chiamante vede subito le chiavi.
      try {
         var0.load(new java.io.File(BattleRoyaleX.getInstance().getDataFolder(), "kits.yml"));
      } catch (Exception ignored) {
      }
   }

   public static void createDefaultKitsUNUSED(FileConfiguration var0) {
      HashMap<String, String> var1 = new HashMap<>();
      HashMap<String, List<String>> var2 = new HashMap<>();
      HashMap<String, List<String>> var3 = new HashMap<>();
      var1.put("Tactician", "IRON_SWORD, false, COMMON, 1000, AIR, LEATHER_CHESTPLATE, AIR, AIR");
      var2.put("Tactician", Arrays.asList(new String[]{"IRON_SWORD : 1", "POTION:NIGHT_VISION:true:false : 1"}));
      var3.put("Tactician", Arrays.asList(new String[]{"&7Start with a set of items", "&7that will help you eliminate the others"}));
      var1.put("Griefer", "MONSTER_EGG, false, COMMON, 1000, LEATHER_HELMET, LEATHER_CHESTPLATE, AIR, AIR");
      var2.put("Griefer", Arrays.asList(new String[]{"WOOD_SWORD : 1", "MONSTER_EGG:50 : 3 : name:&cCreeper"}));
      var3.put("Griefer", Arrays.asList(new String[]{"&7Spawn friendly creepers"}));
      var1.put("Miner", "STONE_PICKAXE, false, COMMON, 1000, IRON_HELMET : enchant:PROTECTION_EXPLOSIONS:2, AIR, AIR, AIR");
      var2.put("Miner", Arrays.asList(new String[]{"STONE_PICKAXE : 1 : enchant:DIG_SPEED:1 : enchant:DURABILITY:3", "TNT : 1"}));
      var3.put("Miner", Arrays.asList(new String[]{"&7Your enchanted pickaxe will help", "&7you mine faster than any one"}));
      var1.put("Hunter", "BOW, false, COMMON, 1000, LEATHER_HELMET, AIR, LEATHER_LEGGINGS, AIR");
      var2.put("Hunter", Arrays.asList(new String[]{"BOW : 1", "ARROW : 8", "GOLDEN_APPLE : 1"}));
      var3.put("Hunter", Arrays.asList(new String[]{"&7Receive a normal bow and have the", "&7ability of shooting other players"}));
      var1.put("Armorer", "LEATHER_CHESTPLATE, false, COMMON, 1000, AIR, LEATHER_CHESTPLATE, IRON_LEGGINGS, AIR");
      var2.put("Armorer", Collections.singletonList("GOLDEN_APPLE : 1"));
      var3.put("Armorer", Collections.singletonList("&7Receive a leather chest and iron leggings"));
      var1.put("Trainer", "BONE, false, COMMON, 1000, AIR, LEATHER_CHESTPLATE, AIR, AIR");
      var2.put("Trainer", Arrays.asList(new String[]{"MONSTER_EGG:95 : 2 : name:&cWolf", "GOLDEN_APPLE : 1"}));
      var3.put("Trainer", Collections.singletonList("&7Tame some wolfs that will protect you"));
      var1.put("Enchanter", "ENCHANTMENT_TABLE, false, RARE, 2000, IRON_HELMET, AIR, AIR, AIR");
      var2.put("Enchanter", Arrays.asList(new String[]{"ENCHANTMENT_TABLE : 1", "EXP_BOTTLE : 10", "GOLDEN_APPLE : 1"}));
      var3.put("Enchanter", Arrays.asList(new String[]{"&7Receive an enchanting table and", "&7start enchanting to protect your self"}));
      var1.put("Hunter+", "BOW, false, RARE, 2000, LEATHER_HELMET, AIR, LEATHER_LEGGINGS, AIR");
      var2.put("Hunter+", Arrays.asList(new String[]{"BOW : 1 : enchant:ARROW_DAMAGE:1", "ARROW : 16", "GOLDEN_APPLE : 1"}));
      var3.put("Hunter+", Arrays.asList(new String[]{"&7Receive a strong bow and have the instant", "&7ability of shooting other players"}));
      var1.put("Armorer+", "IRON_CHESTPLATE, false, RARE, 2000, IRON_HELMET, LEATHER_CHESTPLATE, IRON_LEGGINGS, LEATHER_BOOTS");
      var2.put("Armorer+", Arrays.asList(new String[]{"STONE_AXE : 1", "GOLDEN_APPLE : 1"}));
      var3.put("Armorer+", Arrays.asList(new String[]{"&7Receive a full armor set", "&7that will help you stay alive"}));
      var1.put("Lumberjack", "IRON_AXE, false, RARE, 2000, IRON_HELMET, AIR, AIR, AIR");
      var2.put("Lumberjack", Arrays.asList(new String[]{"IRON_AXE : 1 : enchant:DIG_SPEED:3", "LOG : 16"}));
      var3.put("Lumberjack", Arrays.asList(new String[]{"&7Start with an enchanted axe and", "&7lots of wood that will help you"}));
      var1.put("Necromancer", "MONSTER_EGG:51, false, RARE, 2000, LEATHER_HELMET, LEATHER_CHESTPLATE, AIR, AIR");
      var2.put("Necromancer", Arrays.asList(new String[]{"MONSTER_EGG:51 : 2 : name:&cSkeleton", "GOLDEN_APPLE : 1"}));
      var3.put("Necromancer", Arrays.asList(new String[]{"&7Spawn skeletons that will", "&7protect you from others"}));
      var1.put("Knight", "IRON_SWORD, false, LEGENDARY, 3000, AIR, AIR, AIR, AIR");
      var2.put("Knight", Arrays.asList(new String[]{"IRON_SWORD : 1 : enchant:DAMAGE_ALL:1", "GOLDEN_APPLE : 2"}));
      var3.put(
         "Knight", Arrays.asList(new String[]{"&7Start with a very strong", "&7enchanted iron sword, but unfortunately", "&7you will have no protection"})
      );
      var1.put("Blaze", "MONSTER_EGG:61, false, LEGENDARY, 3000, AIR, CHAINMAIL_CHESTPLATE, AIR, AIR");
      var2.put("Blaze", Arrays.asList(new String[]{"WOOD_SWORD : 1", "MONSTER_EGG:61 : 2 : name:&cBlaze", "GOLDEN_APPLE : 2"}));
      var3.put("Blaze", Arrays.asList(new String[]{"&7Spawn blazes that will protect", "&7you and set everyone on fire!"}));

      for (String var5 : var1.keySet()) {
         String var6 = (String)var1.get(var5);
         List<String> var7 = (List<String>)var2.get(var5);
         List<String> var8 = (List<String>)var3.get(var5);
         String var9 = "Kits." + var5 + ".";
         int var10 = 0;
         var0.set(var9 + "item", var6.split(", ")[var10++]);
         var0.set(var9 + "permission", "none");
         var10++;
         var0.set(var9 + "rarity", var6.split(", ")[var10++]);
         var0.set(var9 + "value", Integer.valueOf(var6.split(", ")[var10++]));
         var0.set(var9 + "helmet", var6.split(", ")[var10++]);
         var0.set(var9 + "chestplate", var6.split(", ")[var10++]);
         var0.set(var9 + "leggings", var6.split(", ")[var10++]);
         var0.set(var9 + "boots", var6.split(", ")[var10++]);
         var0.set(var9 + "items", var7);
         var0.set(var9 + "description", var8);
         var0.set(var9 + "potion-effects", Arrays.asList(new String[0]));
         var0.set(var9 + "executed-commands", Arrays.asList(new String[0]));
      }

      FilesManager.getInstance().saveConfig("kits.yml");
   }

   public static void createDefaultKitsShop(FileConfiguration var0) {
      ArrayList var1 = new ArrayList();

      for (Kit var3 : BattleRoyaleX.getInstance().kits.values()) {
         if (var3.rarity == Rarity.COMMON) {
            var1.add(var3);
         }
      }

      for (Kit var12 : BattleRoyaleX.getInstance().kits.values()) {
         if (var12.rarity == Rarity.RARE) {
            var1.add(var12);
         }
      }

      for (Kit var13 : BattleRoyaleX.getInstance().kits.values()) {
         if (var13.rarity == Rarity.LEGENDARY) {
            var1.add(var13);
         }
      }

      int var11 = 0;

      for (int var14 = 1; var14 < Math.ceil((double)var1.size() / SmartInventory.slots.length) + 1.0; var14++) {
         String var4 = "Normal-Mode-Kits.&cKits #" + var14 + ".";

         for (int var8 : SmartInventory.slots) {
            if (var11 >= var1.size()) {
               break;
            }

            var0.set(var4 + var8, ((Kit)var1.get(var11)).name);
            var11++;
         }
      }

      FilesManager.getInstance().saveConfig("shop.yml");
   }

   public static void createDefaultLoadoutsShop(FileConfiguration var0) {
      if (BattleRoyaleX.getInstance().QualityArmoryEnabled) {
         try {
            ArrayList var1 = new ArrayList();
            if (var0.getStringList("Loadout-Items.Main Weapon").isEmpty()) {
               Iterable<Gun> var2 = QualityArmory.getGuns();

               while (var2.iterator().hasNext()) {
                  Gun var3 = var2.iterator().next();
                  if (!var3.getAmmoType().getName().equals("9mm")) {
                     var1.add("QualityArmory:" + var3.getName() + " : 1 :: " + Math.min((int)var3.getDamage(), 4));
                  }
               }

               var1.add(0, var1.get(1));
               var1.remove(2);
               var0.set("Loadout-Items.Main Weapon", var1);
            }

            if (var0.getStringList("Loadout-Items.Secondary Weapon").isEmpty()) {
               var1 = new ArrayList();
               Iterable<Gun> var7 = QualityArmory.getGuns();

               while (var7.iterator().hasNext()) {
                  Gun var9 = var7.iterator().next();
                  if (var9.getAmmoType().getName().equals("9mm")) {
                     var1.add("QualityArmory:" + var9.getName() + " : 1 :: 1");
                  }
               }

               var0.set("Loadout-Items.Secondary Weapon", var1);
            }

            if (var0.getStringList("Loadout-Items.Items").isEmpty()) {
               var1 = new ArrayList();
               Iterable<CustomBaseObject> var8 = QualityArmory.getMisc();

               while (var8.iterator().hasNext()) {
                  CustomBaseObject var10 = var8.iterator().next();
                  var1.add("QualityArmory:" + var10.getName() + " : 1 :: 1");
               }

               var0.set("Loadout-Items.Items", var1);
            }
         } catch (Exception var4) {
            Bukkit.getConsoleSender().sendMessage(Customization.getInstance().prefix + ChatColor.RED + "Could not manually add QualityArmory guns to shop.yml");
            var4.printStackTrace();
         }
      }

      FilesManager.getInstance().saveConfig("shop.yml");
   }

   public static void createDefaultAchievements(FileConfiguration var0) {
      for (AchievementType var4 : AchievementType.values()) {
         if (!var0.contains("Achievements." + var4.name().toLowerCase())) {
            for (int var8 : var4.levels) {
               String var9 = "Achievements." + var4.name().toLowerCase() + "." + var8 + ".";
               var0.set(var9 + "description", var4.defaultDescription.replace("%x%", String.valueOf(var8)));
               var0.set(var9 + "prize-description", "Earn " + var4.prizeMultiplier * var8 + " coins!");
               var0.set(var9 + "executed-command", "brx coins add %player% " + var4.prizeMultiplier * var8);
            }
         }
      }

      FilesManager.getInstance().saveConfig("achievements.yml");
   }

   public static void createDefaultChests(FileConfiguration var0) {
      var0.addDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(BattleRoyaleX.getInstance().getResource("chests-default.yml"))));
      var0.options().copyDefaults(true);
      if (BattleRoyaleX.getInstance().QualityArmoryEnabled) {
         List var1 = var0.getStringList("Chests.Normal.items");
         Iterable<Gun> var2 = QualityArmory.getGuns();

         while (var2.iterator().hasNext()) {
            Gun var3 = var2.iterator().next();
            var1.add("QualityArmory:" + var3.getName() + " : 1 :: 15%");
         }

         Iterable<Ammo> var11 = QualityArmory.getAmmo();

         while (var11.iterator().hasNext()) {
            Ammo var4 = var11.iterator().next();
            var1.add("QualityArmory:" + var4.getName() + " : " + var4.getMaxItemStack() + " :: 50%");
         }

         Iterable<ArmorObject> var12 = QualityArmory.getArmor();

         while (var12.iterator().hasNext()) {
            ArmorObject var5 = var12.iterator().next();
            var1.add("QualityArmory:" + var5.getName() + " : 1 :: 20%");
         }

         Iterable<CustomBaseObject> var13 = QualityArmory.getMisc();

         while (var13.iterator().hasNext()) {
            CustomBaseObject var6 = (CustomBaseObject)var13.iterator().next();
            var1.add("QualityArmory:" + var6.getName() + " : 1 :: 20%");
         }

         var0.set("Chests.Normal.items", var1);
         var1 = var0.getStringList("Chests.Carepackage.items");
         var2 = QualityArmory.getGuns();

         while (var2.iterator().hasNext()) {
            Gun var14 = var2.iterator().next();
            if (!(var14.getDamage() < 8.0)) {
               var1.add("QualityArmory:" + var14.getName() + " : 1 :: 5%");
            }
         }

         var0.set("Chests.Carepackage.items", var1);
         var1 = var0.getStringList("Chests.Supply-Drops.items");
         var2 = QualityArmory.getGuns();

         while (var2.iterator().hasNext()) {
            Gun var15 = var2.iterator().next();
            if (!(var15.getDamage() < 8.0)) {
               var1.add("QualityArmory:" + var15.getName() + " : 1 :: 10%");
            }
         }

         var0.set("Chests.Supply-Drops.items", var1);
      }

      if (BattleRoyaleX.getInstance().crackShotAPI != null) {
      }

      FilesManager.getInstance().saveConfig("chests.yml");
   }
}
