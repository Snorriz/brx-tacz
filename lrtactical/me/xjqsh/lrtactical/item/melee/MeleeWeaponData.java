package me.xjqsh.lrtactical.item.melee;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import me.xjqsh.lrtactical.api.melee.MeleeAction;
import me.xjqsh.lrtactical.item.melee.CombatData.MeleeAttackInfo;
import me.xjqsh.lrtactical.util.TooltipLine;
import me.xjqsh.lrtactical.util.TooltipUtil;
import net.minecraft.network.chat.Component;

public class MeleeWeaponData {
   @SerializedName("draw_time")
   private int drawTime;
   @SerializedName("put_away_time")
   private int putAwayTime;
   @SerializedName("attack")
   private CombatData attackInfo = new CombatData();
   @SerializedName("attributes")
   private AttributeData attributes = new AttributeData();
   @SerializedName("max_durability")
   private int maxDurability = 0;
   @SerializedName("enchantment_value")
   private int enchantmentValue = 14;

   public int getPutAwayTime() {
      return this.putAwayTime;
   }

   public int getDrawTime() {
      return this.drawTime;
   }

   public CombatData getAttackInfo() {
      return this.attackInfo;
   }

   public AttributeData getRawAttributes() {
      return this.attributes;
   }

   public int getMaxDurability() {
      return this.maxDurability;
   }

   public int getEnchantmentValue() {
      return this.enchantmentValue;
   }

   public List<TooltipLine> getTooltipLines() {
      List<TooltipLine> lines = new ArrayList();

      for (MeleeAction action : MeleeAction.values()) {
         MeleeAttackInfo attackInfo = this.attackInfoOf(action);
         if (attackInfo != null) {
            String actionKey = action == MeleeAction.LEFT ? "tooltip.lrtactical.melee.attack_left" : "tooltip.lrtactical.melee.attack_right";
            lines.add(
               TooltipLine.normal(
                  Component.m_237110_(
                     "tooltip.lrtactical.melee.action_line",
                     new Object[]{
                        Component.m_237115_(actionKey), TooltipUtil.formatFactor(attackInfo.getFactor()), TooltipUtil.formatTicks(attackInfo.getCooldown())
                     }
                  )
               )
            );
         }
      }

      return lines;
   }

   private MeleeAttackInfo attackInfoOf(MeleeAction action) {
      return this.attackInfo == null ? null : this.attackInfo.getAttackInfo(action);
   }
}
