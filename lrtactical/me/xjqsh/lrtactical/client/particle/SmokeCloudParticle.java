package me.xjqsh.lrtactical.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SmokeCloudParticle extends TextureSheetParticle {
   private final SpriteSet spriteSet;

   public static SmokeCloudParticle.SmokeCloudParticleProvider provider(SpriteSet spriteSet) {
      return new SmokeCloudParticle.SmokeCloudParticleProvider(spriteSet);
   }

   protected SmokeCloudParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
      super(world, x, y, z);
      this.spriteSet = spriteSet;
      this.f_107663_ *= 5.5F;
      this.f_107225_ = 20;
      this.f_107226_ = 0.0F;
      this.f_107219_ = false;
      this.f_107215_ = vx * 1.0;
      this.f_107216_ = vy * 1.0;
      this.f_107217_ = vz * 1.0;
      this.m_108339_(spriteSet);
   }

   public int m_6355_(float partialTick) {
      BlockPos pos = BlockPos.m_274561_(this.f_107212_, this.f_107213_, this.f_107214_);
      if (!this.f_107208_.m_46805_(pos)) {
         return 0;
      }

      int light = LevelRenderer.m_109541_(this.f_107208_, pos);
      int sky = Math.max(light >> 20 & 15, 2);
      int block = Math.max(light >> 4 & 15, 2);
      if (sky <= 2 && block <= 2) {
         for (Direction direction : Direction.values()) {
            int neighbor = LevelRenderer.m_109541_(this.f_107208_, pos.m_121945_(direction));
            sky = Math.max(sky, neighbor >> 20 & 15);
            block = Math.max(block, neighbor >> 4 & 15);
         }
      }

      return sky << 20 | block << 4;
   }

   public ParticleRenderType m_7556_() {
      return ParticleRenderType.f_107431_;
   }

   public void m_5989_() {
      super.m_5989_();
      this.m_108339_(this.spriteSet);
   }

   public void m_5744_(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
      super.m_5744_(pBuffer, pRenderInfo, pPartialTicks);
   }

   public boolean shouldCull() {
      return true;
   }

   public static class SmokeCloudParticleProvider implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteSet;

      public SmokeCloudParticleProvider(SpriteSet spriteSet) {
         this.spriteSet = spriteSet;
      }

      public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         return new SmokeCloudParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
      }
   }
}
