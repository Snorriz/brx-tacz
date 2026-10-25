package me.wazup.battleroyalex.events;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BRPlayerLeaveArenaEvent extends Event {
   private static final HandlerList handlers = new HandlerList();
   Player p;
   String arenaName;
   String arenaWorldName;
   List<Player> arenaPlayers;

   public HandlerList getHandlers() {
      return handlers;
   }

   public static HandlerList getHandlerList() {
      return handlers;
   }

   public BRPlayerLeaveArenaEvent(Player var1, String var2, String var3, List<Player> var4) {
      this.p = var1;
      this.arenaName = var2;
      this.arenaWorldName = var3;
      this.arenaPlayers = var4;
   }

   public Player getPlayer() {
      return this.p;
   }

   public String getArenaName() {
      return this.arenaName;
   }

   public World getArenaWorld() {
      return Bukkit.getWorld(this.arenaWorldName);
   }

   public List<Player> getArenaPlayers() {
      return this.arenaPlayers;
   }
}
