package net.mcreator.miklosmilitaryarmorr.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.PlayerTeam;

public class UconjointeamProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         Entity _entityTeam = entity;
         PlayerTeam _pt = _entityTeam.m_9236_().m_6188_().m_83489_("UCON");
         if (_pt != null) {
            if (_entityTeam instanceof Player _player) {
               _entityTeam.m_9236_().m_6188_().m_6546_(_player.m_36316_().getName(), _pt);
            } else {
               _entityTeam.m_9236_().m_6188_().m_6546_(_entityTeam.m_20149_(), _pt);
            }
         }
      }
   }
}
