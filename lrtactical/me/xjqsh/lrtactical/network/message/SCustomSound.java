package me.xjqsh.lrtactical.network.message;

import java.util.function.Supplier;
import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.client.audio.ICustomSoundSupplier;
import me.xjqsh.lrtactical.client.resource.display.MeleeDisplayInstance;
import me.xjqsh.lrtactical.client.resource.display.ThrowableDisplayInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent.Context;

public record SCustomSound(SCustomSound.SoundType type, ResourceLocation id, String key, Vec3 pos, float volume, float pitch) {
   public static void encode(SCustomSound message, FriendlyByteBuf buf) {
      buf.m_130068_(message.type());
      buf.m_130085_(message.id());
      buf.m_130070_(message.key());
      buf.writeDouble(message.pos().f_82479_);
      buf.writeDouble(message.pos().f_82480_);
      buf.writeDouble(message.pos().f_82481_);
      buf.writeFloat(message.volume());
      buf.writeFloat(message.pitch());
   }

   public static SCustomSound decode(FriendlyByteBuf buf) {
      return new SCustomSound(
         (SCustomSound.SoundType)buf.m_130066_(SCustomSound.SoundType.class),
         buf.m_130281_(),
         buf.m_130277_(),
         new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble()),
         buf.readFloat(),
         buf.readFloat()
      );
   }

   public static void handle(SCustomSound message, Supplier<Context> contextSupplier) {
      Context context = (Context)contextSupplier.get();
      if (context.getDirection().getReceptionSide().isClient()) {
         context.enqueueWork(() -> handle(message));
      }

      context.setPacketHandled(true);
   }

   @OnlyIn(Dist.CLIENT)
   private static void handle(SCustomSound message) {
      Player player = Minecraft.m_91087_().f_91074_;
      ClientLevel level = Minecraft.m_91087_().f_91073_;
      if (player != null && level != null) {
         ICustomSoundSupplier supplier = (ICustomSoundSupplier)(switch (message.type()) {
            case MELEE -> (MeleeDisplayInstance)LrTacticalAPI.getMeleeDisplay(message.id()).orElse(null);
            case THROWABLE -> (ThrowableDisplayInstance)LrTacticalAPI.getThrowableDisplay(message.id()).orElse(null);
            default -> null;
         });
         if (supplier != null) {
            ResourceLocation soundLocation = supplier.getSound(message.key());
            if (soundLocation != null) {
               level.m_7785_(
                  message.pos.f_82479_,
                  message.pos.f_82480_,
                  message.pos.f_82481_,
                  SoundEvent.m_262824_(soundLocation),
                  SoundSource.PLAYERS,
                  message.volume,
                  message.pitch,
                  false
               );
            }
         }
      }
   }

   public enum SoundType {
      MELEE,
      THROWABLE;
   }
}
