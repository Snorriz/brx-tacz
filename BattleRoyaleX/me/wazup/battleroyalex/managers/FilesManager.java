package me.wazup.battleroyalex.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import me.wazup.battleroyalex.BattleRoyaleX;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FilesManager {
   private static FilesManager instance;
   private HashMap<String, FileConfiguration> configurations = new HashMap();

   public FilesManager() {
      instance = this;
      BattleRoyaleX.getInstance().reloadConfig();
      BattleRoyaleX.getInstance().getConfig().options().copyDefaults(true);
      BattleRoyaleX.getInstance().saveConfig();
      this.registerConfig("kits.yml");
      this.registerConfig("celebrations.yml");
      this.registerConfig("shop.yml");
      this.registerConfig("chests.yml");
      this.registerConfig("achievements.yml");
      this.registerConfig("broadcaster.yml");
      this.registerConfig("signs.yml");
      this.registerConfig("customization.yml");
      this.registerConfig("custom-recipes.yml");
      this.registerConfig("map-palette.yml");
      this.registerConfig("vehicles.yml");
      this.registerConfig("shopping-station.yml");

      for (String var2 : this.configurations.keySet()) {
         this.reloadConfig(var2);
         ((FileConfiguration)this.configurations.get(var2)).options().copyDefaults(true);
         this.saveConfig(var2);
      }
   }

   public static FilesManager getInstance() {
      return instance;
   }

   private void registerConfig(String var1) {
      this.configurations.put(var1, YamlConfiguration.loadConfiguration(new File(BattleRoyaleX.getInstance().getDataFolder(), var1)));
   }

   public FileConfiguration getConfig(String var1) {
      return (FileConfiguration)this.configurations.get(var1);
   }

   private void reloadConfig(String var1) {
      InputStream var2 = BattleRoyaleX.getInstance().getResource(var1);
      if (var2 != null) {
         InputStreamReader var3 = new InputStreamReader(var2);
         YamlConfiguration var4 = YamlConfiguration.loadConfiguration(var3);
         ((FileConfiguration)this.configurations.get(var1)).setDefaults(var4);

         try {
            var3.close();
            var2.close();
         } catch (IOException var6) {
            var6.printStackTrace();
         }
      }
   }

   public void saveConfig(String var1) {
      try {
         ((FileConfiguration)this.configurations.get(var1)).save(new File(BattleRoyaleX.getInstance().getDataFolder(), var1));
      } catch (IOException var3) {
         Bukkit.getConsoleSender().sendMessage(Customization.getInstance().prefix + "Couldn't save " + var1 + "!");
      }
   }

   public static void deleteFile(File var0) {
      if (var0.exists() && var0.isDirectory()) {
         for (File var4 : var0.listFiles()) {
            if (var4.isDirectory()) {
               deleteFile(var4);
            } else {
               var4.delete();
            }
         }
      }

      var0.delete();
   }

   public static void copyFile(File var0, File var1) {
      try {
         if (var0.isDirectory()) {
            if (!var1.exists()) {
               var1.mkdirs();
            }

            String[] var2 = var0.list();

            for (String var6 : var2) {
               File var7 = new File(var0, var6);
               File var8 = new File(var1, var6);
               copyFile(var7, var8);
            }
         } else {
            FileInputStream var16 = new FileInputStream(var0);
            FileOutputStream var17 = new FileOutputStream(var1);
            FileChannel var18 = var16.getChannel();
            FileChannel var19 = var17.getChannel();

            try {
               var18.transferTo(0L, var18.size(), var19);
            } catch (IOException var13) {
               var13.printStackTrace();
            } finally {
               if (var18 != null) {
                  var18.close();
               }

               if (var19 != null) {
                  var19.close();
               }

               var16.close();
               var17.close();
            }
         }
      } catch (IOException var15) {
         Bukkit.getConsoleSender().sendMessage(Customization.getInstance().prefix + "Failed to copy files!");
         var15.printStackTrace();
      }
   }

   public static long getSize(File var0) {
      long var1 = 0L;
      if (var0.isDirectory()) {
         for (String var6 : var0.list()) {
            var1 += getSize(new File(var0, var6));
         }
      } else {
         var1 = var0.length();
      }

      return var1;
   }
}
