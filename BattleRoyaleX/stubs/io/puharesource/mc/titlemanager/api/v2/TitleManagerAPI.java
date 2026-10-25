package io.puharesource.mc.titlemanager.api.v2;
public interface TitleManagerAPI {
   void sendTitle(org.bukkit.entity.Player player, String title);
   void sendActionbar(org.bukkit.entity.Player player, String message);
}
