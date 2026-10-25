package me.wazup.battleroyalex.scenarios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class Scenario {
   private final String name;
   private final List<String> description;
   private final Material representingMaterial;
   private boolean enabled;

   public String getName() {
      return this.name;
   }

   public List<String> getDescription() {
      return this.description;
   }

   public Material getRepresentingMaterial() {
      return this.representingMaterial;
   }

   public Scenario(String var1, Material var2, String... var3) {
      this.name = var1;
      this.representingMaterial = var2;
      FileConfiguration var4 = this.getConfiguration();
      boolean var5 = false;
      if (!var4.contains("enabled")) {
         var4.set("enabled", true);
         var5 = true;
      }

      if (!var4.contains("description")) {
         var4.set("description", var3);
         var5 = true;
      }

      if (var5) {
         this.saveConfiguration(var4);
      }

      this.enabled = var4.getBoolean("enabled");
      this.description = new ArrayList();

      for (String var7 : var4.getStringList("description")) {
         this.description.add(Utils.colorize(var7));
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public FileConfiguration getConfiguration() {
      File var1 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/scenarios", this.name + ".yml");
      return YamlConfiguration.loadConfiguration(var1);
   }

   public void saveConfiguration(FileConfiguration var1) {
      File var2 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/scenarios", this.name + ".yml");

      try {
         var1.save(var2);
      } catch (IOException var4) {
         var4.printStackTrace();
      }
   }

   public abstract void registerArena(Arena var1);

   public abstract void unregisterArena(Arena var1);
}
