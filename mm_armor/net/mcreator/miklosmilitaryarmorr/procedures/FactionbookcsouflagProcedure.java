package net.mcreator.miklosmilitaryarmorr.procedures;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class FactionbookcsouflagProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         Entity _ent = entity;
         if (!_ent.m_9236_().m_5776_() && _ent.m_20194_() != null) {
            _ent.m_20194_()
               .m_129892_()
               .m_230957_(
                  new CommandSourceStack(
                     CommandSource.f_80164_,
                     _ent.m_20182_(),
                     _ent.m_20155_(),
                     _ent.m_9236_() instanceof ServerLevel ? (ServerLevel)_ent.m_9236_() : null,
                     4,
                     _ent.m_7755_().getString(),
                     _ent.m_5446_(),
                     _ent.m_9236_().m_7654_(),
                     _ent
                  ),
                  "/give @s mm_armor:flagcsou"
               );
         }
      }
   }
}
