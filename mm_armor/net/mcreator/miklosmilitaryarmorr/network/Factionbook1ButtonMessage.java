package net.mcreator.miklosmilitaryarmorr.network;

import java.util.HashMap;
import java.util.function.Supplier;
import net.mcreator.miklosmilitaryarmorr.MmArmorMod;
import net.mcreator.miklosmilitaryarmorr.procedures.FactionbookopencsouProcedure;
import net.mcreator.miklosmilitaryarmorr.procedures.FactionbookopenuconProcedure;
import net.mcreator.miklosmilitaryarmorr.world.inventory.Factionbook1Menu;
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
public class Factionbook1ButtonMessage {
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;

   public Factionbook1ButtonMessage(FriendlyByteBuf buffer) {
      this.buttonID = buffer.readInt();
      this.x = buffer.readInt();
      this.y = buffer.readInt();
      this.z = buffer.readInt();
   }

   public Factionbook1ButtonMessage(int buttonID, int x, int y, int z) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public static void buffer(Factionbook1ButtonMessage message, FriendlyByteBuf buffer) {
      buffer.writeInt(message.buttonID);
      buffer.writeInt(message.x);
      buffer.writeInt(message.y);
      buffer.writeInt(message.z);
   }

   public static void handler(Factionbook1ButtonMessage message, Supplier<Context> contextSupplier) {
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
      HashMap guistate = Factionbook1Menu.guistate;
      if (world.m_46805_(new BlockPos(x, y, z))) {
         if (buttonID == 0) {
            FactionbookopencsouProcedure.execute(world, x, y, z, entity);
         }

         if (buttonID == 1) {
            FactionbookopenuconProcedure.execute(world, x, y, z, entity);
         }
      }
   }

   @SubscribeEvent
   public static void registerMessage(FMLCommonSetupEvent event) {
      MmArmorMod.addNetworkMessage(
         Factionbook1ButtonMessage.class, Factionbook1ButtonMessage::buffer, Factionbook1ButtonMessage::new, Factionbook1ButtonMessage::handler
      );
   }
}
