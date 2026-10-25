package me.wazup.battleroyalex.shop;

import me.wazup.battleroyalex.utils.Enums.Rarity;
import org.bukkit.inventory.ItemStack;

public abstract class Sellable {
   public String name;
   public ItemStack item;
   public int value;
   public String permission;
   public Rarity rarity;
}
