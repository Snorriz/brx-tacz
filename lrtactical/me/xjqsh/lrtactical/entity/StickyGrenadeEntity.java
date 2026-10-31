package me.xjqsh.lrtactical.entity;

import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import me.xjqsh.lrtactical.entity.ThrowableItemEntity.BounceResult;
import me.xjqsh.lrtactical.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Rotations;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraftforge.network.PlayMessages.SpawnEntity;
import org.jetbrains.annotations.Nullable;

public class StickyGrenadeEntity extends GrenadeEntity {
   public static EntityType<StickyGrenadeEntity> TYPE = Builder.m_20704_(StickyGrenadeEntity::new, MobCategory.MISC)
      .setShouldReceiveVelocityUpdates(true)
      .setTrackingRange(64)
      .setUpdateInterval(1)
      .setCustomClientFactory(StickyGrenadeEntity::new)
      .m_20699_(0.3F, 0.3F)
      .m_20716_()
      .m_20698_()
      .m_20719_()
      .m_20712_("sticky_grenade_entity");
   private static final EntityDataAccessor<Boolean> STICKED = SynchedEntityData.m_135353_(StickyGrenadeEntity.class, EntityDataSerializers.f_135035_);
   private static final EntityDataAccessor<Integer> STUCK_ENTITY_ID = SynchedEntityData.m_135353_(StickyGrenadeEntity.class, EntityDataSerializers.f_135028_);
   private static final EntityDataAccessor<Rotations> STUCK_OFFSET = SynchedEntityData.m_135353_(StickyGrenadeEntity.class, EntityDataSerializers.f_135037_);
   private static final EntityDataAccessor<Rotations> STUCK_ROTATION = SynchedEntityData.m_135353_(StickyGrenadeEntity.class, EntityDataSerializers.f_135037_);
   private static final EntityDataAccessor<Byte> STUCK_FACE = SynchedEntityData.m_135353_(StickyGrenadeEntity.class, EntityDataSerializers.f_135027_);
   @Nullable
   private BlockPos stuckBlockPos;
   @Nullable
   private UUID stuckEntityUUID;

   public StickyGrenadeEntity(LivingEntity entity, Level level, int lifeTime) {
      super(TYPE, entity, level, lifeTime);
   }

   public StickyGrenadeEntity(SpawnEntity spawnEntity, Level level) {
      super(TYPE, level);
   }

   public StickyGrenadeEntity(EntityType<StickyGrenadeEntity> type, Level level) {
      super(type, level);
   }

   protected void m_8097_() {
      super.m_8097_();
      this.f_19804_.m_135372_(STICKED, false);
      this.f_19804_.m_135372_(STUCK_ENTITY_ID, -1);
      this.f_19804_.m_135372_(STUCK_OFFSET, new Rotations(0.0F, 0.0F, 0.0F));
      this.f_19804_.m_135372_(STUCK_ROTATION, new Rotations(0.0F, 0.0F, 0.0F));
      this.f_19804_.m_135372_(STUCK_FACE, (byte)-1);
   }

   public boolean shouldBounce() {
      return false;
   }

   public void m_8119_() {
      super.m_8119_();
      if ((Boolean)this.f_19804_.m_135370_(STICKED)) {
         this.m_20256_(Vec3.f_82478_);
         this.m_20242_(true);
         if (!this.m_9236_().f_46443_ && this.stuckBlockPos != null) {
            BlockState state = this.m_9236_().m_8055_(this.stuckBlockPos);
            if (state.m_60795_()) {
               this.detach();
            }
         }

         int entityId = (Integer)this.f_19804_.m_135370_(STUCK_ENTITY_ID);
         if (entityId != -1) {
            Entity entity = this.m_9236_().m_6815_(entityId);
            if (entity == null && !this.m_9236_().f_46443_ && this.stuckEntityUUID != null && this.m_9236_() instanceof ServerLevel serverLevel) {
               entity = serverLevel.m_8791_(this.stuckEntityUUID);
               if (entity != null) {
                  this.f_19804_.m_135381_(STUCK_ENTITY_ID, entity.m_19879_());
               }
            }

            if (entity != null && entity.m_6084_()) {
               Rotations offsetRot = (Rotations)this.f_19804_.m_135370_(STUCK_OFFSET);
               Vec3 offset = new Vec3(offsetRot.m_123156_(), offsetRot.m_123157_(), offsetRot.m_123158_());
               float yRot = this.getEntityRotation(entity);
               Vec3 rotatedOffset = offset.m_82524_(-yRot * (float) (Math.PI / 180.0));
               Vec3 newPos = entity.m_20182_().m_82520_(0.0, entity.m_20206_() * 0.5, 0.0).m_82549_(rotatedOffset);
               this.m_146884_(newPos);
               Rotations relativeRot = (Rotations)this.f_19804_.m_135370_(STUCK_ROTATION);
               this.m_146922_(yRot + relativeRot.m_123157_());
               this.m_146926_(relativeRot.m_123156_());
            } else if (!this.m_9236_().f_46443_) {
               this.detach();
            }
         }
      }
   }

   public Rotations getStuckOffset() {
      return (Rotations)this.f_19804_.m_135370_(STUCK_OFFSET);
   }

   @Nullable
   public Direction getStuckFace() {
      byte value = (Byte)this.f_19804_.m_135370_(STUCK_FACE);
      return value >= 0 && value <= 5 ? Direction.m_122376_(value) : null;
   }

   @Nullable
   public BlockPos getStuckBlockPos() {
      return this.stuckBlockPos;
   }

   protected void m_37283_() {
      if (!(Boolean)this.f_19804_.m_135370_(STICKED)) {
         super.m_37283_();
      }
   }

   private void alignToVec(Vec3 vec) {
      if (!(vec.m_82556_() < 1.0E-7)) {
         Vec3 normalized = vec.m_82541_();
         this.m_146922_((float)(Mth.m_14136_(-normalized.f_82479_, normalized.f_82481_) * 180.0F / (float)Math.PI));
         this.m_146926_(
            (float)(
               -Mth.m_14136_(normalized.f_82480_, Math.sqrt(normalized.f_82479_ * normalized.f_82479_ + normalized.f_82481_ * normalized.f_82481_))
                  * 180.0F / (float)Math.PI
            )
         );
         this.f_19859_ = this.m_146908_();
         this.f_19860_ = this.m_146909_();
      }
   }

   public BounceResult doMultiBounce(Vec3 deltaMovement) {
      if ((Boolean)this.f_19804_.m_135370_(STICKED)) {
         return new BounceResult(this.m_20182_(), Vec3.f_82478_);
      }

      Vec3 start = this.m_20182_();
      Vec3 end = start.m_82549_(deltaMovement);
      HitResult hitResult = this.getHitResult(start, end, deltaMovement, x$0 -> this.m_5603_(x$0), this.m_9236_());
      if (hitResult.m_6662_() != Type.MISS) {
         if (!this.m_9236_().m_5776_()) {
            this.m_6532_(hitResult);
         }

         return new BounceResult(this.m_20182_(), Vec3.f_82478_);
      } else {
         return new BounceResult(end, deltaMovement);
      }
   }

   public void m_6453_(double pX, double pY, double pZ, float pYRot, float pXRot, int pPosRotationIncrements, boolean pTeleport) {
      if (!(Boolean)this.f_19804_.m_135370_(STICKED) || (Integer)this.f_19804_.m_135370_(STUCK_ENTITY_ID) == -1) {
         super.m_6453_(pX, pY, pZ, pYRot, pXRot, pPosRotationIncrements, pTeleport);
      }
   }

   private void detach() {
      this.f_19804_.m_135381_(STICKED, false);
      this.f_19804_.m_135381_(STUCK_ENTITY_ID, -1);
      this.f_19804_.m_135381_(STUCK_FACE, (byte)-1);
      this.m_20242_(false);
      this.stuckBlockPos = null;
      this.stuckEntityUUID = null;
   }

   @ParametersAreNonnullByDefault
   protected void m_6532_(HitResult result) {
      if (!(Boolean)this.f_19804_.m_135370_(STICKED)) {
         if (result.m_6662_() != Type.MISS) {
            if (result.m_6662_() == Type.BLOCK) {
               BlockHitResult blockResult = (BlockHitResult)result;
               BlockPos resultPos = blockResult.m_82425_();
               BlockState state = this.m_9236_().m_8055_(resultPos);
               SoundEvent event = state.m_60734_().getSoundType(state, this.m_9236_(), resultPos, this).m_56776_();
               this.m_9236_()
                  .m_6263_(null, result.m_82450_().f_82479_, result.m_82450_().f_82480_, result.m_82450_().f_82481_, event, SoundSource.AMBIENT, 2.0F, 1.0F);
               this.m_9236_()
                  .m_6263_(
                     null,
                     result.m_82450_().f_82479_,
                     result.m_82450_().f_82480_,
                     result.m_82450_().f_82481_,
                     (SoundEvent)ModSounds.GRENADE_BOUNCE.get(),
                     SoundSource.AMBIENT,
                     2.0F,
                     1.0F
                  );
               this.f_19804_.m_135381_(STICKED, true);
               this.f_19804_.m_135381_(STUCK_FACE, (byte)blockResult.m_82434_().m_122411_());
               this.m_20242_(true);
               this.m_20256_(Vec3.f_82478_);
               this.m_146884_(
                  result.m_82450_()
                     .m_82492_(
                        -blockResult.m_82434_().m_122429_() * 0.15,
                        -blockResult.m_82434_().m_122430_() * 0.15 + 0.15,
                        -blockResult.m_82434_().m_122431_() * 0.15
                     )
               );
               this.stuckBlockPos = resultPos;
               Vec3 normal = Vec3.m_82528_(blockResult.m_82434_().m_122436_());
               this.alignToVec(normal);
            } else if (result.m_6662_() == Type.ENTITY) {
               EntityHitResult entityResult = (EntityHitResult)result;
               Entity entity = entityResult.m_82443_();
               if (entity == this.m_19749_() || entity == this.m_20202_()) {
                  return;
               }

               entity.m_6469_(entity.m_269291_().m_269390_(this, this.m_19749_()), this.getHitDamage());
               this.f_19804_.m_135381_(STICKED, true);
               this.f_19804_.m_135381_(STUCK_FACE, (byte)-1);
               this.m_20242_(true);
               this.m_20256_(result.m_82450_().m_82546_(this.m_20182_()));
               this.stuckEntityUUID = entity.m_20148_();
               this.f_19804_.m_135381_(STUCK_ENTITY_ID, entity.m_19879_());
               Vec3 globalOffset = result.m_82450_().m_82546_(entity.m_20182_().m_82520_(0.0, entity.m_20206_() * 0.5, 0.0));
               this.alignToVec(globalOffset);
               float entityYRot = this.getEntityRotation(entity);
               Vec3 localOffset = globalOffset.m_82524_(entityYRot * (float) (Math.PI / 180.0)).m_82490_(0.75);
               this.f_19804_.m_135381_(STUCK_OFFSET, new Rotations((float)localOffset.f_82479_, (float)localOffset.f_82480_, (float)localOffset.f_82481_));
               float relativeYaw = this.m_146908_() - entityYRot;
               float relativePitch = this.m_146909_();
               this.f_19804_.m_135381_(STUCK_ROTATION, new Rotations(relativePitch, relativeYaw, 0.0F));
            }
         }
      }
   }

   private float getEntityRotation(Entity entity) {
      return entity instanceof LivingEntity livingEntity ? livingEntity.f_20883_ : entity.m_146908_();
   }
}
