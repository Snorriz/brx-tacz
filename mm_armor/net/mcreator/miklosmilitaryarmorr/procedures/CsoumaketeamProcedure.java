package net.mcreator.miklosmilitaryarmorr.procedures;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Team.Visibility;

public class CsoumaketeamProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (world instanceof Level _level) {
         _level.m_6188_().m_83492_("CSOU");
      }

      if (world instanceof Level _level) {
         PlayerTeam _pt = _level.m_6188_().m_83489_("CSOU");
         if (_pt != null) {
            _pt.m_83346_(Visibility.HIDE_FOR_OTHER_TEAMS);
         }
      }

      if (world instanceof ServerLevel _level) {
         _level.m_7654_()
            .m_129892_()
            .m_230957_(
               new CommandSourceStack(CommandSource.f_80164_, new Vec3(x, y, z), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), null)
                  .m_81324_(),
               "team modify csou color red"
            );
      }
   }
}
