package me.xjqsh.lrtactical.client.audio;

import me.xjqsh.lrtactical.init.ModEffects;
import me.xjqsh.lrtactical.init.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance.Attenuation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class StunRingingSound extends AbstractTickableSoundInstance {
   public StunRingingSound() {
      super((SoundEvent)ModSounds.DEAFENED_RING.get(), SoundSource.MASTER, SoundInstance.m_235150_());
      this.f_119578_ = true;
      this.f_119580_ = Attenuation.NONE;
      this.m_7788_();
   }

   public void m_7788_() {
      Player player = Minecraft.m_91087_().f_91074_;
      if (player != null && player.m_6084_()) {
         MobEffectInstance effect = player.m_21124_((MobEffect)ModEffects.DEAFENED.get());
         if (effect != null) {
            this.f_119575_ = (float)player.m_20185_();
            this.f_119576_ = (float)player.m_20186_();
            this.f_119577_ = (float)player.m_20189_();
            this.f_119573_ = Math.min(effect.m_19557_() / 100.0F, 1.0F);
            return;
         }
      }

      this.m_119609_();
   }
}
