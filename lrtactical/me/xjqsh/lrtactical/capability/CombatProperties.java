package me.xjqsh.lrtactical.capability;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import me.xjqsh.lrtactical.EquipmentMod;
import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.api.item.ICustomItem;
import me.xjqsh.lrtactical.api.item.IMeleeWeapon;
import me.xjqsh.lrtactical.api.melee.MeleeAction;
import me.xjqsh.lrtactical.item.melee.CombatData.MeleeMovement;
import me.xjqsh.lrtactical.network.NetworkHandler;
import me.xjqsh.lrtactical.network.message.CMeleeAttackRequest;
import me.xjqsh.lrtactical.network.message.CPrepareMeleeAttack;
import me.xjqsh.lrtactical.network.message.SResetMeleeSyncMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class CombatProperties {
   public static final ResourceLocation ID = new ResourceLocation("lrtactical", "combat_data");
   private final List<CombatProperties.DelayTask> delayedActions = new ArrayList();
   private ItemStack lastItem = ItemStack.f_41583_;
   private final Player entity;
   private int coolDownTick = 0;
   private int lastMaxTick = 0;
   private int lastSelected = 0;
   private int drawingTick = 0;
   private boolean preparingAttack = false;
   private int preparingAttackCnt = 0;
   private final Map<MeleeAction, Integer> actionCounts = new EnumMap(MeleeAction.class);

   public CombatProperties(Player entity) {
      this.entity = entity;
   }

   public int getCoolDownTick() {
      return this.coolDownTick;
   }

   public int getLastMaxTick() {
      return this.lastMaxTick;
   }

   public void setCoolDownTick(int coolDownTick) {
      this.coolDownTick = coolDownTick;
   }

   public void tick() {
      if (this.entity.m_21205_().m_41720_() instanceof ICustomItem customItem) {
         if (this.lastSelected != this.entity.m_150109_().f_35977_) {
            this.lastSelected = this.entity.m_150109_().f_35977_;
            this.reset(customItem, this.lastItem);
         } else if (!customItem.isSame(this.lastItem, this.entity.m_21205_())) {
            this.reset(customItem, this.lastItem);
         }
      } else if (!ItemStack.m_41728_(this.lastItem, this.entity.m_21205_())) {
         this.lastItem = this.entity.m_21205_().m_41777_();
      }

      if (this.coolDownTick > 0) {
         this.coolDownTick--;
         if (this.entity.m_21205_().m_41720_() instanceof IMeleeWeapon weapon && !weapon.canSprintingAttack()) {
            this.entity.m_6858_(false);
         }

         if (this.coolDownTick <= 0) {
            if (!this.entity.m_9236_().m_5776_() && this.preparingAttack) {
               this.forceResetMeleeSync("melee attack request timed out");
            }

            this.preparingAttack = false;
         }
      }

      if (this.entity.m_9236_().m_5776_()) {
         for (CombatProperties.DelayTask task : this.delayedActions) {
            if (task.tick()) {
               task.perform(this.entity);
            }
         }

         this.delayedActions.removeIf(CombatProperties.DelayTask::expired);
      }

      if (this.drawingTick > 0) {
         this.drawingTick--;
      }
   }

   public boolean isDrawing() {
      return this.drawingTick > 0;
   }

   public void reset(ICustomItem customItem, ItemStack last) {
      this.lastItem = this.entity.m_21205_().m_41777_();
      int newCoolDown = customItem.getDrawTime(this.entity.m_21205_());
      if (last.m_41720_() instanceof ICustomItem customItem1) {
         newCoolDown += customItem1.getPutAwayTime(last);
      }

      this.coolDownTick = newCoolDown;
      this.lastMaxTick = newCoolDown;
      this.drawingTick = newCoolDown;
      this.preparingAttack = false;
      this.delayedActions.clear();
      this.actionCounts.clear();
      this.preparingAttackCnt = 0;
   }

   public int getActionCount(MeleeAction meleeAction) {
      return (Integer)this.actionCounts.getOrDefault(meleeAction, 0);
   }

   public void resetMeleeSync() {
      this.coolDownTick = 0;
      this.lastMaxTick = 0;
      this.preparingAttack = false;
      this.actionCounts.clear();
      this.delayedActions.clear();
      this.preparingAttackCnt = 0;
   }

   public boolean preAttack(MeleeAction action, Vec3 origin, Vec3 direction) {
      ItemStack stack = this.entity.m_21205_();
      if (!(stack.m_41720_() instanceof IMeleeWeapon weapon)) {
         return false;
      } else {
         if (!this.entity.m_9236_().m_5776_()) {
            if (this.preparingAttack || this.coolDownTick > 0) {
               return false;
            }
         } else if (this.coolDownTick > 0) {
            return false;
         }

         if (!weapon.canAttack(this.entity, stack, action)) {
            return false;
         }

         int cnt = (Integer)this.actionCounts.getOrDefault(action, 0);
         int nextCount = cnt + 1;
         this.actionCounts.put(action, nextCount);
         this.coolDownTick = weapon.getAttackCoolDown(stack, action, cnt);
         this.lastMaxTick = this.coolDownTick;
         if (!this.entity.m_9236_().m_5776_()) {
            this.preparingAttack = true;
            this.preparingAttackCnt = cnt;
            this.coolDownTick = Math.max(0, this.coolDownTick - 1);
         } else {
            NetworkHandler.CHANNEL.sendToServer(new CPrepareMeleeAttack(action, origin, direction));
            int delay = weapon.getAttackDelay(this.entity, stack, action, cnt);
            CombatProperties.DelayAttack attack = new CombatProperties.DelayAttack(delay, stack, action, cnt);
            if (attack.getDelay() == 0) {
               attack.perform(this.entity);
            } else {
               this.delayedActions.add(attack);
            }

            MeleeMovement moveInfo = weapon.getAttackMovement(this.entity, stack, action);
            if (moveInfo != null) {
               CombatProperties.DelayMove move = new CombatProperties.DelayMove(moveInfo.getDelay(), moveInfo.getSpeed(), stack);
               if (move.getDelay() == 0) {
                  move.perform(this.entity);
               } else {
                  this.delayedActions.add(move);
               }
            }

            LrTacticalAPI.getMeleeDisplay(stack)
               .ifPresent(
                  display -> {
                     if (display.getSounds().containsKey(action.getId())) {
                        this.entity
                           .m_9236_()
                           .m_7785_(
                              origin.f_82479_,
                              origin.f_82480_,
                              origin.f_82481_,
                              SoundEvent.m_262824_((ResourceLocation)display.getSounds().get(action.getId())),
                              SoundSource.PLAYERS,
                              1.0F,
                              1.0F,
                              false
                           );
                     }
                  }
               );
         }

         return true;
      }
   }

   public void postAttack(MeleeAction action, int actionCount, List<Entity> entities) {
      if (this.preparingAttack) {
         if (this.preparingAttackCnt != actionCount) {
            this.forceResetMeleeSync("received a melee attack request with mismatched action count");
         } else {
            ItemStack stack = this.entity.m_21205_();
            if (stack.m_41720_() instanceof IMeleeWeapon weapon) {
               weapon.attack(this.entity, stack, action, entities, this.preparingAttackCnt);
            }

            this.preparingAttack = false;
         }
      }
   }

   private void forceResetMeleeSync(String reason) {
      this.resetMeleeSync();
      if (!this.entity.m_9236_().m_5776_()) {
         EquipmentMod.LOGGER.warn("Force resetting melee sync for player {}: {}", this.entity.m_6302_(), reason);
         NetworkHandler.sendToClientPlayer(new SResetMeleeSyncMessage(), this.entity);
      }
   }

   public static class DelayAttack extends CombatProperties.DelayTask {
      private final ItemStack stack;
      private final MeleeAction action;
      private final int actionCount;

      DelayAttack(int delay, ItemStack stack, MeleeAction action, int actionCount) {
         super(delay);
         this.action = action;
         this.actionCount = actionCount;
         this.stack = stack;
      }

      @Override
      public void perform(Player player) {
         if (this.stack.m_41720_() instanceof IMeleeWeapon weapon && weapon.isSame(this.stack, player.m_21205_())) {
            List<Entity> entities = weapon.collectTargets(player, this.stack, this.action, player.m_146892_(), player.m_20154_());
            NetworkHandler.CHANNEL.sendToServer(new CMeleeAttackRequest(this.action, this.actionCount, entities));
         }
      }
   }

   public static class DelayMove extends CombatProperties.DelayTask {
      private final ItemStack stack;
      private final double speed;

      DelayMove(int delay, double speed, ItemStack stack) {
         super(delay);
         this.stack = stack;
         this.speed = speed;
      }

      @Override
      public void perform(Player player) {
         if (this.stack.m_41720_() instanceof IMeleeWeapon weapon && weapon.isSame(this.stack, player.m_21205_())) {
            double factor = player.m_20096_() ? 1.0 : 0.5;
            Vec3 motion = player.m_20154_().m_82542_(1.0, 0.0, 1.0).m_82541_().m_82490_(factor * this.speed);
            player.m_246865_(motion);
         }
      }
   }

   public abstract static class DelayTask {
      protected int delay;

      protected DelayTask(int delay) {
         this.delay = delay;
      }

      abstract void perform(Player var1);

      public boolean tick() {
         this.delay--;
         return this.delay <= 0;
      }

      public boolean expired() {
         return this.delay <= 0;
      }

      public int getDelay() {
         return this.delay;
      }
   }
}
