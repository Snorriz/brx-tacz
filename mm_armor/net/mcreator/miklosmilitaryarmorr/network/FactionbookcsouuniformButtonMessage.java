package net.mcreator.miklosmilitaryarmorr.network;

import java.util.HashMap;
import java.util.function.Supplier;
import net.mcreator.miklosmilitaryarmorr.MmArmorMod;
import net.mcreator.miklosmilitaryarmorr.procedures.CsougetsniperuniformProcedure;
import net.mcreator.miklosmilitaryarmorr.procedures.CsougetuniformProcedure;
import net.mcreator.miklosmilitaryarmorr.world.inventory.FactionbookcsouuniformMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent.Context;

@EventBusSubscriber(bus = Bus.MOD)
public class FactionbookcsouuniformButtonMessage {
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;

   public FactionbookcsouuniformButtonMessage(FriendlyByteBuf buffer) {
      this.buttonID = buffer.readInt();
      this.x = buffer.readInt();
      this.y = buffer.readInt();
      this.z = buffer.readInt();
   }

   public FactionbookcsouuniformButtonMessage(int buttonID, int x, int y, int z) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public static void buffer(FactionbookcsouuniformButtonMessage message, FriendlyByteBuf buffer) {
      buffer.writeInt(message.buttonID);
      buffer.writeInt(message.x);
      buffer.writeInt(message.y);
      buffer.writeInt(message.z);
   }

   public static void handler(FactionbookcsouuniformButtonMessage message, Supplier<Context> contextSupplier) {
      Context context = (Context)contextSupplier.get();
      context.enqueueWork(() -> {
         Player entity = context.getSender();
         int buttonID = message.buttonID;
         int x = message.x;
         int y = message.y;
         int z = message.z;
         handleButtonAction(entity, buttonID, x, y, z);
      });
      context.setPacketHandled(true);
   }

   public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
      Level world = entity.m_9236_();
      HashMap guistate = FactionbookcsouuniformMenu.guistate;
      if (world.m_46805_(new BlockPos(x, y, z))) {
         if (buttonID == 0) {
            CsougetuniformProcedure.execute(world, x, y, z, entity);
         }

         if (buttonID == 1) {
            CsougetsniperuniformProcedure.execute(world, x, y, z, entity);
         }
      }
   }

   @SubscribeEvent
   public static void registerMessage(FMLCommonSetupEvent event) {
      MmArmorMod.addNetworkMessage(
         FactionbookcsouuniformButtonMessage.class,
         FactionbookcsouuniformButtonMessage::buffer,
         FactionbookcsouuniformButtonMessage::new,
         FactionbookcsouuniformButtonMessage::handler
      );
   }
}
