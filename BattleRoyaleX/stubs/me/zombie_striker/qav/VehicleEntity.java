package me.zombie_striker.qav;
import java.util.List;
import me.zombie_striker.qav.vehicles.AbstractVehicle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
public class VehicleEntity {
   public VehicleEntity(AbstractVehicle v, Location l, Entity e) {}
   public void remove() {}
   public void deconstruct(Entity killer, Entity damager) {}
   public void spawn() {}
   public void setFuel(int fuel) {}
   public void setHealth(double health) {}
   public void setAllowsPassagers(boolean b) {}
   public List<String> getWhiteList() { return null; }
   public org.bukkit.entity.Entity getLivingEntity() { return null; }
}
