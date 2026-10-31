package me.xjqsh.lrtactical.client.input;

import com.mojang.blaze3d.platform.InputConstants.Type;
import com.tacz.guns.client.renderer.item.AnimateGeoItemRenderer;
import me.xjqsh.lrtactical.api.melee.MeleeAction;
import me.xjqsh.lrtactical.capability.CombatPropertiesProvider;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent.MouseButton.Post;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(Dist.CLIENT)
public class AttackKeys {
   public static final KeyMapping NORMAL_ATTACK = new KeyMapping(
      "key.lrtactical.normal_attack.desc", KeyConflictContext.IN_GAME, KeyModifier.NONE, Type.MOUSE, 0, "key.category.lrtactical"
   );
   public static final KeyMapping SPECIAL_ATTACK = new KeyMapping(
      "key.lrtactical.sp_attack.desc", KeyConflictContext.IN_GAME, KeyModifier.NONE, Type.MOUSE, 1, "key.category.lrtactical"
   );

   @SubscribeEvent
   public static void tick(ClientTickEvent event) {
      if (event.phase == Phase.START) {
         Minecraft mc = Minecraft.m_91087_();
         LocalPlayer player = mc.f_91074_;
         if (player != null && !player.m_5833_() && mc.f_91072_ != null) {
            ItemStack stack = player.m_21205_();
            if (NORMAL_ATTACK.m_90857_()) {
               player.getCapability(CombatPropertiesProvider.CAPABILITY).ifPresent(combatProperties -> {
                  if (combatProperties.getCoolDownTick() <= 0) {
                     if (combatProperties.preAttack(MeleeAction.LEFT, player.m_146892_(), player.m_20154_())) {
                        mc.f_91072_.m_105297_();
                        if (IClientItemExtensions.of(stack).getCustomRenderer() instanceof AnimateGeoItemRenderer<?, ?> renderer) {
                           renderer.triggerAnimation(stack, "attack_left");
                           player.m_6674_(InteractionHand.MAIN_HAND);
                        }
                     }
                  }
               });
            }
         }
      }
   }

   @SubscribeEvent
   public static void onNormalAttack(Post event) {
      Minecraft mc = Minecraft.m_91087_();
      LocalPlayer player = mc.f_91074_;
      if (player != null && !player.m_5833_() && mc.f_91072_ != null) {
         ItemStack stack = player.m_21205_();

         while (NORMAL_ATTACK.m_90859_()) {
            player.getCapability(CombatPropertiesProvider.CAPABILITY).ifPresent(combatProperties -> {
               if (combatProperties.preAttack(MeleeAction.LEFT, player.m_146892_(), player.m_20154_())) {
                  mc.f_91072_.m_105297_();
                  if (IClientItemExtensions.of(stack).getCustomRenderer() instanceof AnimateGeoItemRenderer<?, ?> renderer) {
                     renderer.triggerAnimation(stack, "attack_left");
                     player.m_6674_(InteractionHand.MAIN_HAND);
                  }
               }
            });
         }
      }
   }

   @SubscribeEvent
   public static void onSpAttack(Post event) {
      Minecraft mc = Minecraft.m_91087_();
      LocalPlayer player = mc.f_91074_;
      if (player != null && !player.m_5833_() && mc.f_91072_ != null) {
         ItemStack stack = player.m_21205_();

         while (SPECIAL_ATTACK.m_90859_()) {
            player.getCapability(CombatPropertiesProvider.CAPABILITY).ifPresent(combatProperties -> {
               if (combatProperties.preAttack(MeleeAction.RIGHT, player.m_146892_(), player.m_20154_())) {
                  mc.f_91072_.m_105297_();
                  if (IClientItemExtensions.of(stack).getCustomRenderer() instanceof AnimateGeoItemRenderer<?, ?> renderer) {
                     renderer.triggerAnimation(stack, "attack_right");
                     player.m_6674_(InteractionHand.MAIN_HAND);
                  }
               }
            });
         }
      }
   }
}
