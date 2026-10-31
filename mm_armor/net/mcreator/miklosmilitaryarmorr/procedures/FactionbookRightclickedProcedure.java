package net.mcreator.miklosmilitaryarmorr.procedures;

import io.netty.buffer.Unpooled;
import net.mcreator.miklosmilitaryarmorr.world.inventory.Factionbook1Menu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.network.NetworkHooks;

public class FactionbookRightclickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity instanceof ServerPlayer _ent) {
            final BlockPos _bpos = BlockPos.m_274561_(x, y, z);
            NetworkHooks.openScreen(_ent, new MenuProvider() {
               public Component m_5446_() {
                  return Component.m_237113_("Factionbook1");
               }

               public AbstractContainerMenu m_7208_(int id, Inventory inventory, Player player) {
                  return new Factionbook1Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).m_130064_(_bpos));
               }
            }, _bpos);
         }
      }
   }
}
