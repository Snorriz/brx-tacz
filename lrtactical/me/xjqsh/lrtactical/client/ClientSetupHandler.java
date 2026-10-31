package me.xjqsh.lrtactical.client;

import me.xjqsh.lrtactical.client.audio.SoundHandler;
import me.xjqsh.lrtactical.client.gui.overlay.InteractKeyTextOverlay;
import me.xjqsh.lrtactical.client.input.AttackKeys;
import me.xjqsh.lrtactical.client.overlay.UsingProgressOverlay;
import me.xjqsh.lrtactical.client.particle.SmokeCloudParticle;
import me.xjqsh.lrtactical.client.renderer.CoolDownDecorations;
import me.xjqsh.lrtactical.client.renderer.entity.ThrowableEntityRenderer;
import me.xjqsh.lrtactical.client.tooltip.ClientConsumableTooltip;
import me.xjqsh.lrtactical.client.tooltip.ClientMeleeTooltip;
import me.xjqsh.lrtactical.client.tooltip.ClientThrowableTooltip;
import me.xjqsh.lrtactical.entity.EffectCloudGrenadeEntity;
import me.xjqsh.lrtactical.entity.GrenadeEntity;
import me.xjqsh.lrtactical.entity.SmokeGrenadeEntity;
import me.xjqsh.lrtactical.entity.StickyGrenadeEntity;
import me.xjqsh.lrtactical.entity.StunGrenadeEntity;
import me.xjqsh.lrtactical.entity.sp.SpEffectCloudEntity;
import me.xjqsh.lrtactical.init.ModItems;
import me.xjqsh.lrtactical.init.ModParticleTypes;
import me.xjqsh.lrtactical.inventory.tooltip.ConsumableTooltip;
import me.xjqsh.lrtactical.inventory.tooltip.MeleeTooltip;
import me.xjqsh.lrtactical.inventory.tooltip.ThrowableTooltip;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.MOD, modid = "lrtactical")
public class ClientSetupHandler {
   @SubscribeEvent
   public static void onEntityRenderers(RegisterRenderers event) {
      event.registerEntityRenderer(GrenadeEntity.TYPE, ThrowableEntityRenderer::new);
      event.registerEntityRenderer(StickyGrenadeEntity.TYPE, ThrowableEntityRenderer::new);
      event.registerEntityRenderer(SmokeGrenadeEntity.TYPE, ThrowableEntityRenderer::new);
      event.registerEntityRenderer(StunGrenadeEntity.TYPE, ThrowableEntityRenderer::new);
      event.registerEntityRenderer(EffectCloudGrenadeEntity.TYPE, ThrowableEntityRenderer::new);
      event.registerEntityRenderer(SpEffectCloudEntity.TYPE, NoopRenderer::new);
   }

   @SubscribeEvent
   public static void registerParticles(RegisterParticleProvidersEvent event) {
      event.registerSpriteSet((ParticleType)ModParticleTypes.SMOKE_CLOUD.get(), SmokeCloudParticle::provider);
   }

   @SubscribeEvent
   public static void registerItemDecorations(RegisterItemDecorationsEvent event) {
      event.register((ItemLike)ModItems.CONSUMABLE.get(), new CoolDownDecorations());
      event.register((ItemLike)ModItems.THROWABLE.get(), new CoolDownDecorations());
   }

   @SubscribeEvent
   public static void onRegisterTooltipComponentFactories(RegisterClientTooltipComponentFactoriesEvent event) {
      event.register(ConsumableTooltip.class, ClientConsumableTooltip::new);
      event.register(ThrowableTooltip.class, ClientThrowableTooltip::new);
      event.register(MeleeTooltip.class, ClientMeleeTooltip::new);
   }

   @SubscribeEvent
   public static void registerOverlay(RegisterGuiOverlaysEvent event) {
      event.registerAboveAll("lr_using_progress", new UsingProgressOverlay());
   }

   @SubscribeEvent
   public static void onClientSetup(FMLClientSetupEvent event) {
      MinecraftForge.EVENT_BUS.register(SoundHandler.get());
   }

   @SubscribeEvent
   public static void onClientSetup(RegisterKeyMappingsEvent event) {
      event.register(AttackKeys.NORMAL_ATTACK);
      event.register(AttackKeys.SPECIAL_ATTACK);
   }

   @SubscribeEvent
   public static void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
      event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "lrt_interact_key_overlay", new InteractKeyTextOverlay());
   }
}
