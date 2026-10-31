package me.xjqsh.lrtactical.compat.player_animator;

import dev.kosmx.playerAnim.api.layered.modifier.AdjustmentModifier;
import dev.kosmx.playerAnim.api.layered.modifier.AdjustmentModifier.PartModifier;
import dev.kosmx.playerAnim.core.util.Vec3f;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

public class AdjustmentYRotModifier implements Function<String, Optional<PartModifier>> {
   private final Player player;

   private AdjustmentYRotModifier(Player player) {
      this.player = player;
   }

   public Optional<PartModifier> apply(String partName) {
      Minecraft mc = Minecraft.m_91087_();
      if (this.player.equals(mc.f_91074_) && mc.f_91080_ != null) {
         return Optional.empty();
      }

      if (this.player.m_20202_() != null && "body".equals(partName)) {
         return Optional.empty();
      }

      float partialTick = mc.getPartialTick();
      float yBodyRot = Mth.m_14189_(partialTick, this.player.f_20884_, this.player.f_20883_);
      float yHeadRot = Mth.m_14189_(partialTick, this.player.f_20886_, this.player.f_20885_);
      float xRot = Mth.m_14179_(partialTick, this.player.f_19860_, this.player.m_146909_());
      float yaw = Mth.m_14036_(Mth.m_14177_(yHeadRot - yBodyRot), -85.0F, 85.0F);
      float pitch = Mth.m_14177_(xRot);

      return switch (partName) {
         case "body" -> !this.player.m_6069_() && this.player.m_20089_() == Pose.SWIMMING
            ? Optional.of(new PartModifier(new Vec3f(0.0F, 0.0F, -yaw * (float) (Math.PI / 180.0)), Vec3f.ZERO))
            : Optional.of(new PartModifier(new Vec3f(0.0F, -yaw * (float) (Math.PI / 180.0), 0.0F), Vec3f.ZERO));
         case "head" -> Optional.of(new PartModifier(new Vec3f(pitch * (float) (Math.PI / 180.0), 0.0F, 0.0F), Vec3f.ZERO));
         case "leftArm", "rightArm" -> Optional.of(
            new PartModifier(new Vec3f(Mth.m_14036_(pitch, -15.0F, 15.0F) * (float) (Math.PI / 180.0), 0.0F, 0.0F), Vec3f.ZERO)
         );
         default -> Optional.empty();
      };
   }

   public static AdjustmentModifier getModifier(Player player) {
      return new AdjustmentModifier(new AdjustmentYRotModifier(player));
   }
}
