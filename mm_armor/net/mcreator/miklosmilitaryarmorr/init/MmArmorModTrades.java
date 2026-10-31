package net.mcreator.miklosmilitaryarmorr.init;

import java.util.List;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.FORGE)
public class MmArmorModTrades {
   @SubscribeEvent
   public static void registerWanderingTrades(WandererTradesEvent event) {
      event.getGenericTrades()
         .add(new BasicItemListing(new ItemStack(Items.f_42616_), new ItemStack((ItemLike)MmArmorModItems.BLACKBOOTS_BOOTS.get()), 10, 3, 0.05F));
      event.getGenericTrades()
         .add(new BasicItemListing(new ItemStack(Items.f_42616_), new ItemStack((ItemLike)MmArmorModItems.DESERTBOOTS_BOOTS.get()), 10, 3, 0.05F));
      event.getGenericTrades()
         .add(new BasicItemListing(new ItemStack(Items.f_42616_), new ItemStack((ItemLike)MmArmorModItems.NONLA_HELMET.get()), 10, 1, 0.05F));
      event.getGenericTrades()
         .add(new BasicItemListing(new ItemStack(Items.f_42616_), new ItemStack((ItemLike)MmArmorModItems.BLACKWINTERCAP_HELMET.get()), 10, 1, 0.05F));
      event.getGenericTrades()
         .add(new BasicItemListing(new ItemStack(Items.f_42616_), new ItemStack((ItemLike)MmArmorModItems.GREENWINTERCAP_HELMET.get()), 10, 1, 0.05F));
   }

   @SubscribeEvent
   public static void registerTrades(VillagerTradesEvent event) {
      if (event.getType() == VillagerProfession.f_35598_) {
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_, 2), new ItemStack((ItemLike)MmArmorModItems.HAMMER.get()), 4, 8, 0.06F));
      }

      if (event.getType() == VillagerProfession.f_35586_) {
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_), new ItemStack((ItemLike)MmArmorModItems.VESTPLATELV_1.get()), 15, 10, 0.06F));
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_, 2), new ItemStack((ItemLike)MmArmorModItems.VESTPLATELV_2.get()), 15, 15, 0.06F));
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_, 3), new ItemStack((ItemLike)MmArmorModItems.VESTPLATELV_3.get()), 15, 20, 0.06F));
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_, 4), new ItemStack((ItemLike)MmArmorModItems.VESTPLATELV_4.get()), 15, 25, 0.06F));
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_, 5), new ItemStack((ItemLike)MmArmorModItems.VESTPLATELV_5.get()), 15, 30, 0.06F));
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_, 6), new ItemStack((ItemLike)MmArmorModItems.VESTPLATELV_6.get()), 15, 35, 0.06F));
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_, 7), new ItemStack((ItemLike)MmArmorModItems.VESTPLATELV_7.get()), 15, 40, 0.06F));
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_), new ItemStack((ItemLike)MmArmorModItems.HELMETBASELV_1.get()), 15, 10, 0.05F));
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_, 2), new ItemStack((ItemLike)MmArmorModItems.HELMETBASELV_2.get()), 15, 15, 0.05F));
         ((List)event.getTrades().get(1))
            .add(new BasicItemListing(new ItemStack(Items.f_42616_, 3), new ItemStack((ItemLike)MmArmorModItems.HELMETBASELV_3.get()), 15, 20, 0.05F));
      }
   }
}
