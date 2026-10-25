package org.spigotmc.event.entity;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.HandlerList;
public class EntityDismountEvent extends EntityEvent {
   private static final HandlerList HANDLERS = new HandlerList();
   public EntityDismountEvent(Entity what, Entity dismounted) { super(what); }
   public HandlerList getHandlers() { return HANDLERS; }
   public static HandlerList getHandlerList() { return HANDLERS; }
   public Entity getDismounted() { return null; }
}
