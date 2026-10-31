// Compilazione contro l'API REALE di PlaceholderAPI 2.11.x (non uno stub fittizio).
// Metodi DEPRECATED rimossi deliberatamente: onPlaceholderRequest(Player,String), persist(),
// canRegister(), getPlugin(). Il plugin deve sovrascrivere onRequest(OfflinePlayer,String).
package me.clip.placeholderapi.expansion;
import org.bukkit.OfflinePlayer;
public abstract class PlaceholderExpansion {
   public abstract String getIdentifier();
   public abstract String getVersion();
   public abstract String getAuthor();
   public abstract String onRequest(OfflinePlayer player, String params);
   public boolean register() { return true; }
   public boolean unregister() { return true; }
}
