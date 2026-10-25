package me.wazup.battleroyalex.managers;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TitleManager {
   boolean titles;
   boolean actionBar;
   TitleManagerAPI api;
   private static TitleManager instance;

   public TitleManager(boolean var1, boolean var2) {
      instance = this;
      if (var1 || var2) {
         this.titles = var1;
         if (Bukkit.getPluginManager().isPluginEnabled("TitleManager")) {
            this.api = (TitleManagerAPI)Bukkit.getServer().getPluginManager().getPlugin("TitleManager");
            this.actionBar = var2;
            Bukkit.getConsoleSender().sendMessage(Customization.getInstance().prefix + "Hooked into TitleManager!");
         } else {
            try {
               Class.forName("org.bukkit.entity.Player$Spigot").getMethod("sendMessage", new Class[]{ChatMessageType.class, BaseComponent.class});
               this.actionBar = var2;
            } catch (NoSuchMethodException | ClassNotFoundException var4) {
               Bukkit.getConsoleSender().sendMessage(Customization.getInstance().prefix + "Action bar is not supported on this server!");
            }
         }
      }
   }

   public static TitleManager getInstance() {
      return instance;
   }

   public void sendTitle(Player var1, String var2) {
      this.sendTitle(var1, var2, "");
   }

   public void sendTitle(Player var1, String var2, String var3) {
      if (this.titles) {
         if (this.api != null) {
            this.api.sendTitle(var1, var2);
         } else {
            var1.sendTitle(var2, var3);
         }
      }
   }

   public void sendForcedActionBar(Player var1, String var2, PlayerData var3) {
      if (this.actionBar) {
         if (this.api != null) {
            this.api.sendActionbar(var1, var2);
         } else {
            var1.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(var2));
         }

         var3.lastActionBarSent = System.currentTimeMillis();
      }
   }

   public void sendSoftActionBar(Player var1, String var2) {
      PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName());
      if (System.currentTimeMillis() - var3.lastActionBarSent > 1000L) {
         this.sendForcedActionBar(var1, var2, var3);
      }
   }

   public boolean isActionBarEnabled() {
      return this.actionBar;
   }
}
