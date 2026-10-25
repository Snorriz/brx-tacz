package me.wazup.battleroyalex.holograms;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class Hologram {
   abstract void delete();

   abstract void teleport(Location var1);

   abstract void showToOnePlayer(Player var1);

   abstract void updateLines(List<String> var1);
}
