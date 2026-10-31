package me.xjqsh.lrtactical.network.message;

import java.util.function.Supplier;
import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.api.item.IConsumable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent.Context;

public class CCancelToggleConsumableUse {
   public CCancelToggleConsumableUse() {
   }

   public CCancelToggleConsumableUse(FriendlyByteBuf buf) {
   }

   public void encode(FriendlyByteBuf buf) {
   }

   public void handle(Supplier<Context> contextSupplier) {
      Context context = (Context)contextSupplier.get();
      context.enqueueWork(
         () -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
               ItemStack useItem = player.m_21211_();
               if (useItem.m_41720_() instanceof IConsumable
                  && (Boolean)LrTacticalAPI.getConsumableIndex(useItem).map(index -> index.getData().isToggleUse()).orElse(false)) {
                  player.m_5810_();
               }
            }
         }
      );
      context.setPacketHandled(true);
   }
}
