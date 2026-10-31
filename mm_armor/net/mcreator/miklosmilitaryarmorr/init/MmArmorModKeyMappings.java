package net.mcreator.miklosmilitaryarmorr.init;

import net.mcreator.miklosmilitaryarmorr.MmArmorMod;
import net.mcreator.miklosmilitaryarmorr.network.HelmetabilitiesMessage;
import net.mcreator.miklosmilitaryarmorr.network.ParachuteonMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT)
public class MmArmorModKeyMappings {
   public static final KeyMapping HELMETABILITIES = new KeyMapping("key.mm_armor.helmetabilities", 78, "key.categories.misc") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            MmArmorMod.PACKET_HANDLER.sendToServer(new HelmetabilitiesMessage(0, 0));
            HelmetabilitiesMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping PARACHUTEON = new KeyMapping("key.mm_armor.parachuteon", 71, "key.categories.misc") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            MmArmorMod.PACKET_HANDLER.sendToServer(new ParachuteonMessage(0, 0));
            ParachuteonMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
         }

         this.isDownOld = isDown;
      }
   };

   @SubscribeEvent
   public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
      event.register(HELMETABILITIES);
      event.register(PARACHUTEON);
   }

   @EventBusSubscriber(Dist.CLIENT)
   public static class KeyEventListener {
      @SubscribeEvent
      public static void onClientTick(ClientTickEvent event) {
         if (Minecraft.m_91087_().f_91080_ == null) {
            MmArmorModKeyMappings.HELMETABILITIES.m_90859_();
            MmArmorModKeyMappings.PARACHUTEON.m_90859_();
         }
      }
   }
}
