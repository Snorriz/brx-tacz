package me.wazup.battleroyalex.listeners;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.managers.AchievementsManager;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.utils.Enums.AchievementType;
import me.zombie_striker.qg.api.QAWeaponDamageEntityEvent;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class QAListener implements Listener {
   @EventHandler
   public void onQAWeaponDamageEntityEvent(QAWeaponDamageEntityEvent var1) {
      Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         if (Config.getInstance().qualityArmoryGunsDamageModification) {
            if (var1.isHeadshot(var1.getDamaged())) {
               var1.setCancelled(true);
               if (var1.getDamaged() instanceof Damageable) {
                  ((Damageable)var1.getDamaged()).damage(Config.getInstance().qualityArmoryHeadShotDamage, var2);
               }
            } else if (var1.getDamage() > Config.getInstance().qualityArmoryMaxBodyShotDamage) {
               var1.setDamage(Config.getInstance().qualityArmoryMaxBodyShotDamage);
            }
         }

         PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
         var3.projectiles_hit++;
         AchievementsManager.getInstance().checkPlayer(var2, AchievementType.PROJECTILES_HIT, var3.projectiles_hit);
      }
   }
}
