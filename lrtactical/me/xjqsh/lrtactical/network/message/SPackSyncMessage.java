package me.xjqsh.lrtactical.network.message;

import java.util.Map;
import java.util.function.Supplier;
import me.xjqsh.lrtactical.network.DataType;
import me.xjqsh.lrtactical.resource.CommonNetworkCache;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent.Context;

public record SPackSyncMessage(Map<DataType, Map<ResourceLocation, String>> cache) {
   public static void encode(SPackSyncMessage message, FriendlyByteBuf buf) {
      buf.m_236831_(message.cache(), FriendlyByteBuf::m_130068_, (buf1, map) -> buf1.m_236831_(map, FriendlyByteBuf::m_130085_, FriendlyByteBuf::m_130070_));
   }

   public static SPackSyncMessage decode(FriendlyByteBuf buf) {
      Map<DataType, Map<ResourceLocation, String>> map = buf.m_236847_(
         buf1 -> (DataType)buf1.m_130066_(DataType.class), buf2 -> buf2.m_236847_(FriendlyByteBuf::m_130281_, FriendlyByteBuf::m_130277_)
      );
      return new SPackSyncMessage(map);
   }

   public static void handle(SPackSyncMessage message, Supplier<Context> contextSupplier) {
      Context context = (Context)contextSupplier.get();
      if (context.getDirection().getReceptionSide().isClient()) {
         context.enqueueWork(() -> doSync(message));
      }

      context.setPacketHandled(true);
   }

   @OnlyIn(Dist.CLIENT)
   private static void doSync(SPackSyncMessage message) {
      CommonNetworkCache.INSTANCE.fromNetwork(message.cache);
   }
}
