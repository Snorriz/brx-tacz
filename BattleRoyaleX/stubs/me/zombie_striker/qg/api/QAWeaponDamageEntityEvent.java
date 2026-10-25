package me.zombie_striker.qg.api;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
public class QAWeaponDamageEntityEvent extends Event {
   private static final HandlerList HANDLERS = new HandlerList();
   public HandlerList getHandlers() { return HANDLERS; }
   public static HandlerList getHandlerList() { return HANDLERS; }
   public Player getPlayer() { return null; }
   public Player getShooter() { return null; }
   public Entity getDamaged() { return null; }
   public Entity getDamagedEntity() { return null; }
   public boolean isHeadshot() { return false; }
   public float getDamage() { return 0F; }
   public void setDamage(float f) {}
   public void setCancelled(boolean b) {}
   public boolean isHeadshot(Entity e) { return false; }
   public org.bukkit.inventory.ItemStack getWeapon() { return null; }
   public org.bukkit.inventory.ItemStack weapon;
   public org.bukkit.inventory.ItemStack getWeaponOrNull() { return weapon; }
}
