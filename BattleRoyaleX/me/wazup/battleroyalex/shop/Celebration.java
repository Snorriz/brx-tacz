package me.wazup.battleroyalex.shop;

import me.wazup.battleroyalex.Arena;
import org.bukkit.entity.Player;

public abstract class Celebration extends Sellable {
   public abstract String getName();

   public abstract void start(Player var1, Arena var2);
}
