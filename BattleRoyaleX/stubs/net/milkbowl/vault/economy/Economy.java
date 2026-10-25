package net.milkbowl.vault.economy;
import org.bukkit.OfflinePlayer;
public interface Economy {
   boolean hasAccount(OfflinePlayer player);
   double getBalance(OfflinePlayer player);
   boolean has(OfflinePlayer player, double amount);
   boolean withdrawPlayer(OfflinePlayer player, double amount);
   boolean depositPlayer(OfflinePlayer player, double amount);
   String getName();
}
