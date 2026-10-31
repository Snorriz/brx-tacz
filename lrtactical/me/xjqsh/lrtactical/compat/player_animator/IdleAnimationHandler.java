package me.xjqsh.lrtactical.compat.player_animator;

import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.api.item.IMeleeWeapon;
import me.xjqsh.lrtactical.client.resource.display.MeleeDisplayInstance;
import me.xjqsh.lrtactical.compat.player_animator.PlayerAnimatorIntegration.AnimationLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.RenderTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class IdleAnimationHandler {
   @SubscribeEvent
   public static void onClientTick(RenderTickEvent event) {
      if (event.phase == Phase.END) {
         Minecraft mc = Minecraft.m_91087_();
         if (mc.f_91073_ != null) {
            for (AbstractClientPlayer clientPlayer : mc.f_91073_.m_6907_()) {
               if (clientPlayer.m_20089_() != Pose.SLEEPING
                  && !clientPlayer.m_6147_()
                  && !clientPlayer.m_6069_()
                  && clientPlayer.m_20089_() != Pose.FALL_FLYING) {
                  ItemStack stack = clientPlayer.m_21205_();
                  ThirdPersonAnimationConfig config = getConfig(stack);
                  if (config == null) {
                     stopAll(clientPlayer);
                  } else {
                     if (!PlayerAnimatorIntegration.isAttackAnimationActive(clientPlayer, AnimationLayer.UPPER)) {
                        applyIdleAnimationFor(clientPlayer, config);
                     }

                     applyLowerBodyAnimation(clientPlayer, config);
                     enableRotationModifier(clientPlayer, config);
                  }
               } else {
                  stopAll(clientPlayer);
               }
            }
         }
      }
   }

   public static void stopAll(AbstractClientPlayer clientPlayer) {
      PlayerAnimatorIntegration.stopAnimation(clientPlayer, AnimationLayer.UPPER, 8);
      PlayerAnimatorIntegration.stopAnimation(clientPlayer, AnimationLayer.LOWER, 8);
      PlayerAnimatorIntegration.stopAnimation(clientPlayer, AnimationLayer.ROTATION, 4);
   }

   public static void enableRotationModifier(AbstractClientPlayer player, @NotNull ThirdPersonAnimationConfig config) {
      ResourceLocation basePath = config.getAnimationPath();
      if (basePath != null) {
         PlayerAnimatorIntegration.enableRotationModifier(player, basePath, 4);
      }
   }

   public static void applyIdleAnimationFor(AbstractClientPlayer player, @NotNull ThirdPersonAnimationConfig config) {
      String state = getPlayerMovementState(player);
      ResourceLocation basePath = config.getAnimationPath();
      String animName = config.getAnimation(AnimationLayer.UPPER, state, 0);
      if (basePath != null && animName != null) {
         PlayerAnimatorIntegration.playIdleAnimation(player, basePath, animName, AnimationLayer.UPPER, 4);
      }
   }

   private static void applyLowerBodyAnimation(AbstractClientPlayer player, @NotNull ThirdPersonAnimationConfig config) {
      String state = getPlayerMovementState(player);
      ResourceLocation basePath = config.getAnimationPath();
      String animName = config.getAnimation(AnimationLayer.LOWER, state, 0);
      if (basePath != null && animName != null) {
         PlayerAnimatorIntegration.playIdleAnimation(player, basePath, animName, AnimationLayer.LOWER, 8);
      }
   }

   private static String getPlayerMovementState(AbstractClientPlayer player) {
      if (!player.m_20096_()) {
         return player.m_20184_().f_82480_ > 0.0 ? "jump" : "fall";
      } else {
         double speed = player.m_20184_().m_165925_();
         if (player.m_6047_()) {
            return speed > 0.001 ? "sneak_walk" : "sneak";
         } else if (player.m_20142_()) {
            return "sprint";
         } else if (speed > 0.001) {
            return speed > 0.05 ? "run" : "walk";
         } else {
            return "idle";
         }
      }
   }

   private static ThirdPersonAnimationConfig getConfig(ItemStack stack) {
      return stack.m_41720_() instanceof IMeleeWeapon
         ? (ThirdPersonAnimationConfig)LrTacticalAPI.getMeleeDisplay(stack).map(MeleeDisplayInstance::getThirdPersonAnimation).orElse(null)
         : null;
   }
}
