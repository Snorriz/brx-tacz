package me.xjqsh.lrtactical.item.throwable;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import me.xjqsh.lrtactical.util.TooltipLine;
import me.xjqsh.lrtactical.util.TooltipUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class ThrowableData {
   @SerializedName("prepare_time")
   private int prepareTime = 10;
   @SerializedName("cookable")
   private boolean cookable = false;
   @SerializedName("initial_speed")
   private double initialSpeed = 1.5;
   @SerializedName("cooldown")
   private int cooldown = 40;
   @SerializedName("cooldown_category")
   private ResourceLocation cooldownCategory = null;
   @SerializedName("stack_size")
   private int stackSize = 1;
   @SerializedName("entity")
   private EntityData entityData = new EntityData();
   @SerializedName("put_away_time")
   private long putAwayTime = 0L;

   public int getPrepareTime() {
      return this.prepareTime;
   }

   public double getInitialSpeed() {
      return this.initialSpeed;
   }

   public int getCooldown() {
      return this.cooldown;
   }

   @Nullable
   public ResourceLocation getCooldownCategory() {
      return this.cooldownCategory;
   }

   public int getStackSize() {
      return this.stackSize;
   }

   public EntityData getEntityData() {
      return this.entityData;
   }

   public long getPutAwayTime() {
      return this.putAwayTime;
   }

   public boolean isCookable() {
      return this.cookable;
   }

   public List<TooltipLine> getTooltipLines() {
      List<TooltipLine> lines = new ArrayList();
      if (this.getCooldown() > 0) {
         lines.add(TooltipLine.normal(Component.m_237110_("tooltip.lrtactical.throwable.cooldown", new Object[]{TooltipUtil.formatTicks(this.getCooldown())})));
      }

      if (this.isCookable()) {
         lines.add(TooltipLine.normal(Component.m_237115_("tooltip.lrtactical.throwable.cookable")));
      }

      if (this.getClass() == ThrowableData.class && this.getEntityData().getLifeTime() > 0) {
         lines.add(
            TooltipLine.normal(
               Component.m_237110_("tooltip.lrtactical.throwable.life_time", new Object[]{TooltipUtil.formatTicks(this.getEntityData().getLifeTime())})
            )
         );
      }

      return lines;
   }
}
