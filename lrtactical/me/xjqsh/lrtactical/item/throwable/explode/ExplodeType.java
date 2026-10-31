package me.xjqsh.lrtactical.item.throwable.explode;

import me.xjqsh.lrtactical.config.ServerConfig;
import me.xjqsh.lrtactical.entity.GrenadeEntity;
import me.xjqsh.lrtactical.item.throwable.ThrowableType;
import me.xjqsh.lrtactical.item.throwable.ThrowableType.Builder;
import me.xjqsh.lrtactical.resource.CommonAssetsManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ExplodeType {
   public static final ThrowableType<ExplodeThrowableData, GrenadeEntity> EXPLODE = Builder.of()
      .setFactory(ExplodeType::createEntity)
      .setSerializer(jsonElement -> (ExplodeThrowableData)CommonAssetsManager.GSON.fromJson(jsonElement, ExplodeThrowableData.class))
      .build();

   public static GrenadeEntity createEntity(ItemStack stack, LivingEntity thrower, ExplodeThrowableData data) {
      GrenadeEntity entity = new GrenadeEntity(thrower, thrower.m_9236_(), data.getEntityData().getLifeTime());
      float initialSpeed = (float)data.getInitialSpeed();
      if (thrower.m_6047_()) {
         initialSpeed = (float)(initialSpeed * (Double)ServerConfig.CROUCHING_INIT_SPEED_PERCENT.get());
      }

      entity.m_37251_(entity, thrower.m_146909_(), thrower.m_146908_(), 0.0F, initialSpeed, 1.0F);
      entity.setItem(stack);
      entity.setBaseData(data.getEntityData());
      entity.setDamage(data.getExplode().getDamage());
      entity.setRadius(data.getExplode().getRadius());
      entity.setDestroyBlocks(data.getExplode().isDestroyBlocks());
      entity.setExplodeDestroyMultiplier(data.getExplode().getDestroyMultiplier());
      entity.setTriggerOnExplode(data.getExplode().isTriggerOnExplode());
      entity.setScreenShakeTime(data.getExplode().getScreenShakeTime());
      entity.setScreenShakeAmplitude(data.getExplode().getScreenShakeAmplitude());
      return entity;
   }
}
