package me.xjqsh.lrtactical.api.animation;

import me.xjqsh.lrtactical.api.melee.MeleeAction;
import me.xjqsh.lrtactical.capability.CombatPropertiesProvider;

public class MeleeAnimationStateContext extends BaseAnimationStateContext {
   public int getActionCount(String action) {
      MeleeAction action1 = switch (action) {
         case "attack_left" -> MeleeAction.LEFT;
         case "attack_right" -> MeleeAction.RIGHT;
         default -> null;
      };
      return action1 == null
         ? 0
         : (Integer)this.processCameraEntity(
               entity -> (Integer)entity.getCapability(CombatPropertiesProvider.CAPABILITY).map(cap -> cap.getActionCount(action1)).orElse(0)
            )
            .orElse(0);
   }
}
