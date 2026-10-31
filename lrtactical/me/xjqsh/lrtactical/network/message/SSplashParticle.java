package me.xjqsh.lrtactical.network.message;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent.Context;

public record SSplashParticle(BlockPos blockPos, int color) {
   public static void encode(SSplashParticle message, FriendlyByteBuf buf) {
      buf.m_130064_(message.blockPos);
      buf.writeInt(message.color);
   }

   public static SSplashParticle decode(FriendlyByteBuf buf) {
      return new SSplashParticle(buf.m_130135_(), buf.readInt());
   }

   public static void handle(SSplashParticle message, Supplier<Context> contextSupplier) {
      Context context = (Context)contextSupplier.get();
      if (context.getDirection().getReceptionSide().isClient()) {
         context.enqueueWork(() -> handle(message));
      }

      context.setPacketHandled(true);
   }

   @OnlyIn(Dist.CLIENT)
   private static void handle(SSplashParticle message) {
      Player player = Minecraft.m_91087_().f_91074_;
      ClientLevel level = Minecraft.m_91087_().f_91073_;
      ParticleEngine particleEngine = Minecraft.m_91087_().f_91061_;
      if (player != null && level != null) {
         int color = message.color;
         RandomSource randomsource = level.f_46441_;
         float r = (color >> 16 & 0xFF) / 255.0F;
         float g = (color >> 8 & 0xFF) / 255.0F;
         float b = (color & 0xFF) / 255.0F;
         Vec3 vec3 = Vec3.m_82539_(message.blockPos);
         ParticleOptions particleoptions = ParticleTypes.f_123806_;

         for (int k2 = 0; k2 < 100; k2++) {
            double d13 = randomsource.m_188500_() * 4.0;
            double d19 = randomsource.m_188500_() * Math.PI * 2.0;
            double d25 = Math.cos(d19) * d13;
            double d30 = 0.01 + randomsource.m_188500_() * 0.5;
            double d31 = Math.sin(d19) * d13;
            Particle particle1 = particleEngine.m_107370_(
               particleoptions, vec3.f_82479_ + d25 * 0.1, vec3.f_82480_ + 0.3, vec3.f_82481_ + d31 * 0.1, d25, d30, d31
            );
            if (particle1 != null) {
               float f2 = 0.75F + randomsource.m_188501_() * 0.25F;
               particle1.m_107253_(r * f2, g * f2, b * f2);
               particle1.m_107268_((float)d13);
            }
         }
      }
   }
}
