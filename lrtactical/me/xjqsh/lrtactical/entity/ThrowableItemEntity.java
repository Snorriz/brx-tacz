package me.xjqsh.lrtactical.entity;

import java.util.Optional;
import java.util.function.Predicate;
import me.xjqsh.lrtactical.api.item.IThrowable;
import me.xjqsh.lrtactical.init.ModItems;
import me.xjqsh.lrtactical.init.ModSounds;
import me.xjqsh.lrtactical.item.throwable.EntityData;
import me.xjqsh.lrtactical.network.NetworkHandler;
import me.xjqsh.lrtactical.network.message.SCustomSound;
import me.xjqsh.lrtactical.network.message.SCustomSound.SoundType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ThrowableItemEntity extends Projectile implements IEntityAdditionalSpawnData {
   private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.m_135353_(ThrowableItemEntity.class, EntityDataSerializers.f_135033_);
   private int life = 100;
   private float gravity = 0.07F;
   private double bounceFactor = 0.75;
   private boolean shouldBounce = true;
   private boolean brokeOnGround = false;
   private float hitDamage = 1.0F;
   private ParticleOptions tailParticle = null;

   public ThrowableItemEntity(EntityType<? extends Projectile> type, LivingEntity shooter, Level level, int lifeTime) {
      super(type, level);
      this.m_6034_(shooter.m_20185_(), shooter.m_20188_() - 0.1, shooter.m_20189_());
      this.m_5602_(shooter);
      this.life = lifeTime;
   }

   public ThrowableItemEntity(EntityType<? extends Projectile> type, Level level) {
      super(type, level);
   }

   @NotNull
   public Packet<ClientGamePacketListener> m_5654_() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   protected void m_8097_() {
      this.m_20088_().m_135372_(DATA_ITEM_STACK, ItemStack.f_41583_);
   }

   public void m_7380_(@NotNull CompoundTag pCompound) {
      super.m_7380_(pCompound);
      ItemStack itemstack = this.getItemRaw();
      if (!itemstack.m_41619_()) {
         pCompound.m_128365_("Item", itemstack.m_41739_(new CompoundTag()));
      }
   }

   public void m_7378_(@NotNull CompoundTag pCompound) {
      super.m_7378_(pCompound);
      ItemStack itemstack = ItemStack.m_41712_(pCompound.m_128469_("Item"));
      this.setItem(itemstack);
   }

   protected ItemStack getItemRaw() {
      return (ItemStack)this.m_20088_().m_135370_(DATA_ITEM_STACK);
   }

   public ItemStack getItem() {
      ItemStack itemstack = this.getItemRaw();
      return itemstack.m_41619_() ? new ItemStack(this.getDefaultItem()) : itemstack;
   }

   public void setItem(ItemStack pStack) {
      if (!pStack.m_150930_(this.getDefaultItem()) || pStack.m_41782_()) {
         this.m_20088_().m_135381_(DATA_ITEM_STACK, pStack.m_255036_(1));
      }
   }

   protected Item getDefaultItem() {
      return (Item)ModItems.THROWABLE.get();
   }

   protected void m_6532_(HitResult result) {
      if (result.m_6662_() != Type.MISS) {
         boolean flag = this.brokeOnGround && result instanceof BlockHitResult blockHitResult && blockHitResult.m_82434_() == Direction.UP;
         if (!this.shouldBounce || flag) {
            this.onDeath(result);
            return;
         }
      }

      switch (result.m_6662_()) {
         case BLOCK:
            BlockHitResult blockResult = (BlockHitResult)result;
            BlockPos resultPos = blockResult.m_82425_();
            BlockState state = this.m_9236_().m_8055_(resultPos);
            SoundEvent event = state.m_60734_().getSoundType(state, this.m_9236_(), resultPos, this).m_56776_();
            double speed = this.m_20184_().m_82553_();
            if (speed > 0.1) {
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
            }

            state.m_60669_(this.m_9236_(), state, blockResult, this);
            break;
         case ENTITY:
            EntityHitResult entityResult = (EntityHitResult)result;
            Entity entity = entityResult.m_82443_();
            if (entity == this.m_19749_() || entity == this.m_20202_()) {
               return;
            }

            double speed = this.m_20184_().m_82553_();
            if (speed > 0.1) {
               entity.m_6469_(entity.m_269291_().m_269390_(this, this.m_19749_()), this.getHitDamage());
            }
      }
   }

   public ThrowableItemEntity.BounceResult doMultiBounce(Vec3 deltaMovement) {
      Vec3 start = this.m_20182_();
      Vec3 end = start.m_82549_(deltaMovement);
      Vec3 endVecOffset = new Vec3(deltaMovement.f_82479_, deltaMovement.f_82480_, deltaMovement.f_82481_);

      for (int i = 0; i < 3; i++) {
         HitResult hitResult = this.getHitResult(start, end, endVecOffset, x$0 -> this.m_5603_(x$0), this.m_9236_());
         if (hitResult.m_6662_() == Type.BLOCK) {
            BlockHitResult blockResult = (BlockHitResult)hitResult;
            Vec3 hit = blockResult.m_82450_();
            if (blockResult.m_82434_() == Direction.UP && start.m_7098_() - hit.m_7098_() < 0.01) {
               hit = new Vec3(hit.m_7096_(), start.m_7098_(), hit.m_7094_());
            }

            if (i < 2) {
               start = start.m_165921_(hit, 0.8);
               Vec3 rest = end.m_82546_(start);
               endVecOffset = this.bounce(blockResult.m_82434_(), rest);
               end = start.m_82549_(endVecOffset);
               deltaMovement = this.bounce(blockResult.m_82434_(), deltaMovement);
            } else {
               end = start.m_165921_(hit, 0.8);
               deltaMovement = Vec3.f_82478_;
            }
         } else if (hitResult.m_6662_() == Type.ENTITY) {
            EntityHitResult entityResult = (EntityHitResult)hitResult;
            Entity entity = entityResult.m_82443_();
            if (entity == this.m_19749_() || entity == this.m_20202_()) {
               break;
            }

            Direction direction = Direction.m_122366_(endVecOffset.m_7096_(), endVecOffset.m_7098_(), endVecOffset.m_7094_()).m_122424_();
            Vec3 hit = hitResult.m_82450_();
            start = start.m_165921_(hit, 0.8);
            Vec3 rest = end.m_82546_(start);
            endVecOffset = this.bounce(direction, rest);
            end = start.m_82549_(endVecOffset);
            deltaMovement = this.bounce(direction, deltaMovement);
         } else if (hitResult.m_6662_() == Type.MISS) {
            break;
         }

         this.m_6532_(hitResult);
      }

      return new ThrowableItemEntity.BounceResult(end, deltaMovement);
   }

   public Vec3 bounce(Direction direction, Vec3 deltaMovement) {
      double factor = this.getBounceFactor();

      return switch (direction.m_122434_()) {
         case X -> deltaMovement.m_82542_(-factor / 1.5, factor, factor);
         case Y -> {
            Vec3 newVec = deltaMovement.m_82542_(factor, -factor / 2.5, factor);
            if (newVec.m_7098_() < this.getGravity()) {
               newVec = newVec.m_82542_(1.0, 0.0, 1.0);
            }

            yield newVec;
         }
         case Z -> deltaMovement.m_82542_(factor, factor, -factor / 1.5);
         default -> throw new IncompatibleClassChangeError();
      };
   }

   public HitResult getHitResult(Vec3 start, Vec3 end, Vec3 endVecOffset, Predicate<Entity> pFilter, Level pLevel) {
      HitResult hitresult = pLevel.m_45547_(new ClipContext(start, end, Block.COLLIDER, Fluid.NONE, this));
      if (hitresult.m_6662_() != Type.MISS) {
         end = hitresult.m_82450_();
      }

      HitResult hitresult1 = getEntityHitResult(pLevel, this, start, end, this.m_20191_().m_82369_(endVecOffset).m_82400_(1.0), pFilter);
      if (hitresult1 != null) {
         hitresult = hitresult1;
      }

      return hitresult;
   }

   @Nullable
   public static EntityHitResult getEntityHitResult(
      Level pLevel, Entity pProjectile, Vec3 pStartVec, Vec3 pEndVec, AABB pBoundingBox, Predicate<Entity> pFilter
   ) {
      double d0 = Double.MAX_VALUE;
      Entity entity = null;
      Vec3 hitPos = null;

      for (Entity entity1 : pLevel.m_6249_(pProjectile, pBoundingBox, pFilter)) {
         AABB aabb = entity1.m_20191_().m_82400_(0.3);
         Optional<Vec3> optional = aabb.m_82371_(pStartVec, pEndVec);
         if (optional.isPresent()) {
            double d1 = pStartVec.m_82557_((Vec3)optional.get());
            if (d1 < d0) {
               entity = entity1;
               d0 = d1;
               hitPos = (Vec3)optional.get();
            }
         }
      }

      return entity == null ? null : new EntityHitResult(entity, hitPos);
   }

   public void playBounceSound() {
   }

   public void m_8119_() {
      super.m_8119_();
      ThrowableItemEntity.BounceResult result = this.doMultiBounce(this.m_20184_());
      this.m_20101_();
      Vec3 vec3 = result.deltaMovement();
      double x = result.location().m_7096_();
      double y = result.location().m_7098_();
      double z = result.location().m_7094_();
      this.m_20256_(vec3);
      this.m_37283_();
      float f;
      if (this.m_20069_()) {
         for (int i = 0; i < 4; i++) {
            this.m_9236_()
               .m_7106_(
                  ParticleTypes.f_123795_,
                  x - vec3.f_82479_ * 0.25,
                  y - vec3.f_82480_ * 0.25,
                  z - vec3.f_82481_ * 0.25,
                  vec3.f_82479_,
                  vec3.f_82480_,
                  vec3.f_82481_
               );
         }

         f = 0.8F;
      } else {
         f = 0.99F;
      }

      this.m_20256_(vec3.m_82490_(f));
      if (!this.m_20068_()) {
         Vec3 vec31 = this.m_20184_();
         this.m_20334_(vec31.f_82479_, vec31.f_82480_ - this.getGravity(), vec31.f_82481_);
      }

      this.m_6034_(x, y, z);
      if (this.f_19797_ >= this.life && this.life >= 0 && !this.m_9236_().m_5776_()) {
         this.onDeath(null);
      }

      if (this.m_9236_().m_5776_()) {
         this.renderTailParticle();
      }
   }

   public void renderTailParticle() {
      if (this.getTailParticle() != null) {
         this.m_9236_().m_6493_(this.getTailParticle(), true, this.m_20185_(), this.m_20186_() + 0.35, this.m_20189_(), 0.0, 0.01, 0.0);
      }
   }

   public void onDeath(@Nullable HitResult hitResult) {
      this.m_146870_();
      if (!this.m_9236_().m_5776_()) {
         this.playThrowableSound("death", 16.0F, 1.0F);
      }
   }

   @Deprecated
   public void onDeath() {
      this.onDeath(null);
   }

   public void playThrowableSound(String key, float volume, float pitch) {
      ItemStack stack = this.getItem();
      if (stack.m_41720_() instanceof IThrowable iThrowable) {
         ResourceLocation id = iThrowable.getId(stack);
         SCustomSound packet = new SCustomSound(SoundType.THROWABLE, id, key, this.m_20182_(), volume, pitch);
         NetworkHandler.CHANNEL
            .send(PacketDistributor.NEAR.with(TargetPoint.p(this.m_20185_(), this.m_20186_(), this.m_20189_(), 64.0, this.m_9236_().m_46472_())), packet);
      }
   }

   public boolean m_6783_(double pDistance) {
      double d0 = this.m_20191_().m_82309_() * 4.0;
      if (Double.isNaN(d0)) {
         d0 = 4.0;
      }

      d0 *= 64.0;
      return pDistance < d0 * d0;
   }

   public void setBaseData(EntityData data) {
      this.setGravity(data.getGravity());
      this.setBounceFactor(data.getBounceFactor());
      this.setShouldBounce(data.isShouldBounce());
      this.setHitDamage(data.getHitDamage());
      this.setBrokeOnGround(data.isBrokeOnGround());
      this.setTailParticle(data.getTailParticles());
   }

   public float getGravity() {
      return this.gravity;
   }

   public void setGravity(float gravity) {
      this.gravity = gravity;
   }

   public double getBounceFactor() {
      return this.bounceFactor;
   }

   public void setBounceFactor(double bounceFactor) {
      this.bounceFactor = bounceFactor;
   }

   public boolean shouldBounce() {
      return this.shouldBounce;
   }

   public void setShouldBounce(boolean shouldBounce) {
      this.shouldBounce = shouldBounce;
   }

   public int getLife() {
      return this.life;
   }

   public void setLife(int life) {
      this.life = life;
   }

   public float getHitDamage() {
      return this.hitDamage;
   }

   public void setHitDamage(float hitDamage) {
      this.hitDamage = hitDamage;
   }

   public boolean isBrokeOnGround() {
      return this.brokeOnGround;
   }

   public void setBrokeOnGround(boolean brokeOnGround) {
      this.brokeOnGround = brokeOnGround;
   }

   public ParticleOptions getTailParticle() {
      return this.tailParticle;
   }

   public void setTailParticle(ParticleOptions tailParticle) {
      this.tailParticle = tailParticle;
   }

   public void writeSpawnData(FriendlyByteBuf buffer) {
      buffer.writeInt(this.life);
      buffer.writeFloat(this.gravity);
      buffer.writeDouble(this.bounceFactor);
      buffer.writeBoolean(this.shouldBounce);
      buffer.writeBoolean(this.brokeOnGround);
      if (this.tailParticle != null) {
         buffer.writeBoolean(true);
         EntityDataSerializers.f_135036_.m_6856_(buffer, this.tailParticle);
      } else {
         buffer.writeBoolean(false);
      }
   }

   public void readSpawnData(FriendlyByteBuf additionalData) {
      this.life = additionalData.readInt();
      this.gravity = additionalData.readFloat();
      this.bounceFactor = additionalData.readDouble();
      this.shouldBounce = additionalData.readBoolean();
      this.brokeOnGround = additionalData.readBoolean();
      if (additionalData.readBoolean()) {
         this.tailParticle = (ParticleOptions)EntityDataSerializers.f_135036_.m_6709_(additionalData);
      } else {
         this.tailParticle = null;
      }
   }

   public record BounceResult(Vec3 location, Vec3 deltaMovement) {
   }
}
