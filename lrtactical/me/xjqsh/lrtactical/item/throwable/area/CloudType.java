package me.xjqsh.lrtactical.item.throwable.area;

import me.xjqsh.lrtactical.config.ServerConfig;
import me.xjqsh.lrtactical.entity.EffectCloudGrenadeEntity;
import me.xjqsh.lrtactical.item.throwable.ThrowableType;
import me.xjqsh.lrtactical.item.throwable.ThrowableType.Builder;
import me.xjqsh.lrtactical.resource.CommonAssetsManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class CloudType {
   public static final ThrowableType<EffectCloudThrowableData, EffectCloudGrenadeEntity> CLOUD = Builder.of()
      .setFactory(CloudType::createEntity)
      .setSerializer(jsonElement -> (EffectCloudThrowableData)CommonAssetsManager.GSON.fromJson(jsonElement, EffectCloudThrowableData.class))
      .build();

   public static EffectCloudGrenadeEntity createEntity(ItemStack stack, LivingEntity thrower, EffectCloudThrowableData data) {
      EffectCloudGrenadeEntity entity = new EffectCloudGrenadeEntity(thrower, thrower.m_9236_(), data.getEntityData().getLifeTime());
      float initialSpeed = (float)data.getInitialSpeed();
      if (thrower.m_6047_()) {
         initialSpeed = (float)(initialSpeed * (Double)ServerConfig.CROUCHING_INIT_SPEED_PERCENT.get());
      }

      entity.m_37251_(entity, thrower.m_146909_(), thrower.m_146908_(), 0.0F, initialSpeed, 1.0F);
      entity.setItem(stack);
      entity.setBaseData(data.getEntityData());
      entity.setCloudData(data.getCloudData());
      return entity;
   }
}
