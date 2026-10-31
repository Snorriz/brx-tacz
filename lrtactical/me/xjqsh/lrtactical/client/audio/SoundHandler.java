package me.xjqsh.lrtactical.client.audio;

import com.tacz.guns.client.sound.GunSoundInstance;
import java.lang.reflect.Field;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import me.xjqsh.lrtactical.init.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance.Attenuation;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.client.sounds.ChannelAccess.ChannelHandle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class SoundHandler {
   private static SoundHandler instance;
   private final Map<SoundInstance, Float> soundVolumes = new ConcurrentHashMap();
   private boolean isDeafened;
   private Field playingSounds;
   private SoundEngine soundEngine;
   private StunRingingSound ringing;

   public static SoundHandler get() {
      if (instance == null) {
         instance = new SoundHandler();
      }

      return instance;
   }

   private SoundHandler() {
      this.initReflection();
   }

   private void initReflection() {
      this.playingSounds = ObfuscationReflectionHelper.findField(SoundEngine.class, "f_120226_");
   }

   @SubscribeEvent
   public void deafenPlayer(ClientTickEvent event) {
      if (event.phase != Phase.START && Minecraft.m_91087_().f_91074_ != null && this.soundEngine != null) {
         MobEffectInstance effect = Minecraft.m_91087_().f_91074_.m_21124_((MobEffect)ModEffects.DEAFENED.get());
         if (effect != null || this.isDeafened) {
            if (this.ringing != null && Minecraft.m_91087_().m_91106_().m_120403_(this.ringing)) {
               Map<SoundInstance, ChannelHandle> playingSounds;
               try {
                  playingSounds = (Map<SoundInstance, ChannelHandle>)this.playingSounds.get(this.soundEngine);
               } catch (IllegalArgumentException | IllegalAccessException e) {
                  return;
               }

               if (effect != null) {
                  try {
                     playingSounds.forEach(
                        (sound, entryx) -> {
                           if (sound != null && !(sound instanceof TickableSoundInstance)) {
                              ResourceLocation loc = sound.m_7904_();
                              if (!loc.equals(new ResourceLocation("minecraft", "entity.generic.explode"))) {
                                 if (!loc.equals(new ResourceLocation("lrtactical", "entity.grenade.flash.explode"))) {
                                    float volume = sound instanceof SoundHandler.SoundMuted
                                       ? ((SoundHandler.SoundMuted)sound).getVolumeInitial()
                                       : sound.m_7769_();
                                    this.soundVolumes.put(sound, volume);
                                    entryx.m_120154_(soundSource -> soundSource.m_83666_(this.getMutedVolume(effect.m_19557_(), volume)));
                                 }
                              }
                           }
                        }
                     );
                  } catch (ConcurrentModificationException var7) {
                  }

                  this.isDeafened = true;
               } else if (this.isDeafened) {
                  this.isDeafened = false;

                  for (Entry<SoundInstance, Float> entry : this.soundVolumes.entrySet()) {
                     ChannelHandle entry1 = (ChannelHandle)playingSounds.get(entry.getKey());
                     if (entry1 != null) {
                        entry1.m_120154_(soundSource -> soundSource.m_83666_((Float)entry.getValue()));
                     }
                  }

                  this.soundVolumes.clear();
               }
            } else {
               this.ringing = new StunRingingSound();
               Minecraft.m_91087_().m_91106_().m_120367_(this.ringing);
            }
         }
      }
   }

   @SubscribeEvent
   public void lowerInitialVolume(PlaySoundEvent event) {
      if (this.soundEngine == null) {
         this.soundEngine = event.getEngine();
      }

      if (this.isDeafened
         && Minecraft.m_91087_().f_91074_ != null
         && (!(event.getSound() instanceof TickableSoundInstance) || event.getSound() instanceof GunSoundInstance)) {
         ResourceLocation loc = event.getSound().m_7904_();
         if (!loc.equals(new ResourceLocation("minecraft", "entity.generic.explode"))) {
            MobEffectInstance effect = Minecraft.m_91087_().f_91074_.m_21124_((MobEffect)ModEffects.DEAFENED.get());
            int duration = effect != null ? effect.m_19557_() : 0;
            if (duration != 0) {
               event.getSound().m_6775_(Minecraft.m_91087_().m_91106_());
               event.setSound(new SoundHandler.SoundMuted(event.getSound(), duration, false));
            }
         }
      }
   }

   private float getMutedVolume(float duration, float volumeBase) {
      float volumeMin = 0.01F * volumeBase;
      float percent = Math.min(duration / 100.0F, 1.0F);
      return volumeMin + (1.0F - percent) * (volumeBase - volumeMin);
   }

   public static class SoundMuted implements SoundInstance {
      private SoundInstance parent;
      private float volume;
      private float volumeInitial;

      public SoundMuted(SoundInstance parent, int duration, boolean isStunGrenade) {
         this.parent = parent;
         this.volumeInitial = Mth.m_14036_(parent.m_7769_(), 0.0F, 1.0F);
         this.volume = SoundHandler.get().getMutedVolume(duration, this.volumeInitial);
         if (isStunGrenade) {
            this.volumeInitial = this.volume;
         }
      }

      public float m_7769_() {
         return this.volume;
      }

      public float getVolumeInitial() {
         return this.volumeInitial;
      }

      public ResourceLocation m_7904_() {
         return this.parent.m_7904_();
      }

      @Nullable
      public WeighedSoundEvents m_6775_(SoundManager handler) {
         return this.parent.m_6775_(handler);
      }

      public Sound m_5891_() {
         return this.parent.m_5891_();
      }

      public SoundSource m_8070_() {
         return this.parent.m_8070_();
      }

      public boolean m_7775_() {
         return this.parent.m_7775_();
      }

      public boolean m_7796_() {
         return false;
      }

      public int m_7766_() {
         return this.parent.m_7766_();
      }

      public float m_7783_() {
         return this.parent.m_7783_();
      }

      public double m_7772_() {
         return this.parent.m_7772_();
      }

      public double m_7780_() {
         return this.parent.m_7780_();
      }

      public double m_7778_() {
         return this.parent.m_7778_();
      }

      public Attenuation m_7438_() {
         return this.parent.m_7438_();
      }
   }
}
