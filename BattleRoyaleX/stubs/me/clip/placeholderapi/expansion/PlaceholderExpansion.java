package me.clip.placeholderapi.expansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
public abstract class PlaceholderExpansion {
   public abstract String getIdentifier();
   public abstract String getVersion();
   public abstract String getAuthor();
   public boolean persist() { return true; }
   public boolean register() { return true; }
   public boolean unregister() { return true; }
   public String onRequest(OfflinePlayer player, String params) { return null; }
   public JavaPlugin getPlugin() { return null; }
}
