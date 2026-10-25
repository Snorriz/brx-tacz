package me.wazup.battleroyalex.events;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BRArenaStartEvent extends Event {
   private static final HandlerList handlers = new HandlerList();
   String name;
   String worldName;
   List<Player> players;

   public HandlerList getHandlers() {
      return handlers;
   }

   public static HandlerList getHandlerList() {
      return handlers;
   }

   public BRArenaStartEvent(String var1, String var2, List<Player> var3) {
      this.name = var1;
      this.worldName = var2;
      this.players = var3;
   }

   public String getName() {
      return this.name;
   }

   public World getWorld() {
      return Bukkit.getWorld(this.worldName);
   }

   public List<Player> getPlayers() {
      return this.players;
   }
}
