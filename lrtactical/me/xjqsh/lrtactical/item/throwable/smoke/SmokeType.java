package me.xjqsh.lrtactical.item.throwable.smoke;

import me.xjqsh.lrtactical.config.ServerConfig;
import me.xjqsh.lrtactical.entity.SmokeGrenadeEntity;
import me.xjqsh.lrtactical.item.throwable.ThrowableData;
import me.xjqsh.lrtactical.item.throwable.ThrowableType;
import me.xjqsh.lrtactical.item.throwable.ThrowableType.Builder;
import me.xjqsh.lrtactical.resource.CommonAssetsManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class SmokeType {
   public static final ThrowableType<ThrowableData, SmokeGrenadeEntity> SMOKE = Builder.of()
      .setFactory(SmokeType::createEntity)
      .setSerializer(jsonElement -> (ThrowableData)CommonAssetsManager.GSON.fromJson(jsonElement, ThrowableData.class))
      .build();

   public static SmokeGrenadeEntity createEntity(ItemStack stack, LivingEntity thrower, ThrowableData data) {
      SmokeGrenadeEntity entity = new SmokeGrenadeEntity(thrower, thrower.m_9236_(), data.getEntityData().getLifeTime());
      float initialSpeed = (float)data.getInitialSpeed();
      if (thrower.m_6047_()) {
         initialSpeed = (float)(initialSpeed * (Double)ServerConfig.CROUCHING_INIT_SPEED_PERCENT.get());
      }

      entity.m_37251_(entity, thrower.m_146909_(), thrower.m_146908_(), 0.0F, initialSpeed, 1.0F);
      entity.setItem(stack);
      entity.setBaseData(data.getEntityData());
      return entity;
   }
}
