package net.mcreator.miklosmilitaryarmorr.init;

import net.mcreator.miklosmilitaryarmorr.block.entity.BarbedwireBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MmArmorModBlockEntities {
   public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "mm_armor");
   public static final RegistryObject<BlockEntityType<?>> BARBEDWIRE = register("barbedwire", MmArmorModBlocks.BARBEDWIRE, BarbedwireBlockEntity::new);

   private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntitySupplier<?> supplier) {
      return REGISTRY.register(registryname, () -> Builder.m_155273_(supplier, new Block[]{(Block)block.get()}).m_58966_(null));
   }
}
