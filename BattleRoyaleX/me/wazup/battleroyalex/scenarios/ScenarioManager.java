package me.wazup.battleroyalex.scenarios;

import java.util.HashMap;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.scenarios.list.WeakestLinkScenario;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.SmartInventory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ScenarioManager {
   private static ScenarioManager instance;
   HashMap<String, Scenario> registered;
   SmartInventory gui;
   int currentGui;

   public ScenarioManager() {
      instance = this;
      this.registered = new HashMap();
      this.gui = new SmartInventory((String)Customization.getInstance().inventories.get("Arena-Settings"));
      this.currentGui = this.gui.addInventory("List #1");
      this.gui.setItem(this.currentGui, 49, (ItemStack)Customization.getInstance().items.get("Back"));
      this.registerScenario(new WeakestLinkScenario());
   }

   public static ScenarioManager getInstance() {
      return instance;
   }

   public void registerScenario(Scenario var1) {
      this.registered.put(var1.getName().toLowerCase(), var1);
      if (var1.isEnabled()) {
         ItemStackBuilder var2 = new ItemStackBuilder(var1.getRepresentingMaterial()).setName(ChatColor.GREEN + var1.getName());

         for (String var4 : var1.getDescription()) {
            var2.addLore(new String[]{var4});
         }

         ItemStack var5 = var2.build();
         boolean var6 = this.gui.addItem(this.currentGui, var5);
         if (!var6) {
            this.currentGui = this.gui.addInventory("List #" + (this.currentGui + 2));
            this.gui.addItem(this.currentGui, var5);
            this.gui.setItem(this.currentGui, 49, (ItemStack)Customization.getInstance().items.get("Back"));
         }
      }
   }

   public Scenario getScenario(String var1) {
      return !this.registered.containsKey(var1) ? null : (Scenario)this.registered.get(var1);
   }

   public void showGUI(Player var1) {
      this.gui.open(var1);
   }
}
