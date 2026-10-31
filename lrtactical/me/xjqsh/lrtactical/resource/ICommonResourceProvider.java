package me.xjqsh.lrtactical.resource;

import java.util.Collection;
import me.xjqsh.lrtactical.item.index.ConsumableIndex;
import me.xjqsh.lrtactical.item.index.MeleeWeaponIndex;
import me.xjqsh.lrtactical.item.index.ThrowableIndex;
import net.minecraft.resources.ResourceLocation;

public interface ICommonResourceProvider {
   ConsumableIndex getConsumableIndex(ResourceLocation var1);

   Collection<ConsumableIndex> getConsumableIndexes();

   ThrowableIndex<?, ?> getThrowableIndex(ResourceLocation var1);

   Collection<ThrowableIndex<?, ?>> getThrowableIndexes();

   MeleeWeaponIndex<?> getMeleeIndex(ResourceLocation var1);

   Collection<MeleeWeaponIndex<?>> getMeleeIndexes();
}
