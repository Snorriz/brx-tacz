package me.xjqsh.lrtactical.network.message;

import java.util.function.Supplier;
import javax.annotation.Nullable;
import me.xjqsh.lrtactical.api.event.MeleePreAttackEvent;
import me.xjqsh.lrtactical.api.melee.MeleeAction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkEvent.Context;

public class SMeleeAnimationSync {
   private final int playerId;
   private final MeleeAction state;
   private final int actionCount;
   private final ResourceLocation animationId;

   public SMeleeAnimationSync(int playerId, MeleeAction state, int actionCount, @Nullable ResourceLocation animationId) {
      this.playerId = playerId;
      this.state = state;
      this.actionCount = actionCount;
      this.animationId = animationId;
   }

   public static void encode(SMeleeAnimationSync msg, FriendlyByteBuf buf) {
      buf.writeInt(msg.playerId);
      buf.m_130068_(msg.state);
      buf.m_130130_(msg.actionCount);
      buf.writeBoolean(msg.animationId != null);
      if (msg.animationId != null) {
         buf.m_130085_(msg.animationId);
      }
   }

   public static SMeleeAnimationSync decode(FriendlyByteBuf buf) {
      int playerId = buf.readInt();
      MeleeAction state = (MeleeAction)buf.m_130066_(MeleeAction.class);
      int actionCount = buf.m_130242_();
      ResourceLocation animationId = buf.readBoolean() ? buf.m_130281_() : null;
      return new SMeleeAnimationSync(playerId, state, actionCount, animationId);
   }

   public static void handle(SMeleeAnimationSync msg, Supplier<Context> ctx) {
      Context context = (Context)ctx.get();
      if (context.getDirection().getReceptionSide().isClient()) {
         context.enqueueWork(() -> onClientReceive(msg));
      }

      context.setPacketHandled(true);
   }

   @OnlyIn(Dist.CLIENT)
   public static void onClientReceive(SMeleeAnimationSync msg) {
      MinecraftForge.EVENT_BUS.post(new MeleePreAttackEvent(msg.playerId, msg.state, msg.actionCount, msg.animationId));
   }
}
