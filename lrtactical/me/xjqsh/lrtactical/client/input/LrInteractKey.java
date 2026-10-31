package me.xjqsh.lrtactical.client.input;

import com.tacz.guns.client.input.InteractKey;
import com.tacz.guns.config.util.InteractKeyConfigRead;
import com.tacz.guns.util.InputExtraCheck;
import me.xjqsh.lrtactical.api.item.IMeleeWeapon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent.Key;
import net.minecraftforge.client.event.InputEvent.MouseButton.Post;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(Dist.CLIENT)
public class LrInteractKey {
   @SubscribeEvent
   public static void onInteractKeyPress(Key event) {
      if (InputExtraCheck.isInGame() && event.getAction() == 1 && InteractKey.INTERACT_KEY.m_90832_(event.getKey(), event.getScanCode())) {
         doInteractLogic();
      }
   }

   @SubscribeEvent
   public static void onInteractMousePress(Post event) {
      if (InputExtraCheck.isInGame() && event.getAction() == 1 && InteractKey.INTERACT_KEY.m_90830_(event.getButton())) {
         doInteractLogic();
      }
   }

   private static void doInteractLogic() {
      Minecraft mc = Minecraft.m_91087_();
      LocalPlayer player = mc.f_91074_;
      if (player != null && !player.m_5833_()) {
         if (player.m_21205_().m_41720_() instanceof IMeleeWeapon) {
            HitResult hitResult = mc.f_91077_;
            if (hitResult == null) {
               return;
            }

            if (hitResult instanceof BlockHitResult blockHitResult) {
               interactBlock(blockHitResult, player, mc);
               return;
            }

            if (hitResult instanceof EntityHitResult entityHitResult) {
               interactEntity(entityHitResult, mc);
            }
         }
      }
   }

   private static void interactBlock(BlockHitResult blockHitResult, LocalPlayer player, Minecraft mc) {
      BlockPos blockPos = blockHitResult.m_82425_();
      BlockState block = player.m_9236_().m_8055_(blockPos);
      if (InteractKeyConfigRead.canInteractBlock(block)) {
         mc.m_91277_();
      }
   }

   private static void interactEntity(EntityHitResult entityHitResult, Minecraft mc) {
      Entity entity = entityHitResult.m_82443_();
      if (InteractKeyConfigRead.canInteractEntity(entity)) {
         mc.m_91277_();
      }
   }
}
