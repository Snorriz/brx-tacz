package com.gmail.filoghost.holographicdisplays.api;
import org.bukkit.Location;
import org.bukkit.entity.Player;
public class Hologram {
   public void appendTextLine(String line) {}
   public void delete() {}
   public void teleport(Location location) {}
   public Location getLocation() { return null; }
   public VisibilityManager getVisibilityManager() { return null; }
   public static class VisibilityManager {
      public void showTo(Player player) {}
      public void setVisibleByDefault(boolean b) {}
   }
}
