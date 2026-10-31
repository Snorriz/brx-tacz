package me.xjqsh.lrtactical.item.throwable.area;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import me.xjqsh.lrtactical.item.throwable.ThrowableData;
import me.xjqsh.lrtactical.util.PotionTooltipUtil;
import me.xjqsh.lrtactical.util.TooltipLine;
import me.xjqsh.lrtactical.util.TooltipUtil;
import me.xjqsh.lrtactical.util.PotionTooltipUtil.EffectWithChance;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class EffectCloudThrowableData extends ThrowableData {
   @SerializedName("cloud")
   private EffectCloudThrowableData.CloudData explode = new EffectCloudThrowableData.CloudData();

   @NotNull
   public EffectCloudThrowableData.CloudData getCloudData() {
      return this.explode;
   }

   public List<TooltipLine> getTooltipLines() {
      List<TooltipLine> lines = super.getTooltipLines();
      EffectCloudThrowableData.CloudData cloudData = this.getCloudData();
      lines.add(
         TooltipLine.normal(
            Component.m_237110_(
               "tooltip.lrtactical.throwable.cloud.line",
               new Object[]{TooltipUtil.format(cloudData.getRadius()), TooltipUtil.formatTicks(cloudData.getDuration())}
            )
         )
      );
      if (cloudData.isIgnite()) {
         lines.add(
            TooltipLine.normal(
               Component.m_237110_("tooltip.lrtactical.throwable.cloud.ignite", new Object[]{TooltipUtil.formatTicks(cloudData.getIgniteTime())})
            )
         );
      }

      List<EffectWithChance> effects = new ArrayList();

      for (MobEffectInstance effect : cloudData.getEffectInstances()) {
         if (effect != null) {
            effects.add(new EffectWithChance(effect, 1.0F));
         }
      }

      List<Component> effectLines = new ArrayList();
      PotionTooltipUtil.addPotionTooltip(effects, effectLines, 1.0F);

      for (Component line : effectLines) {
         lines.add(TooltipLine.collapsible(line));
      }

      return lines;
   }

   public static class CloudData {
      @SerializedName("area_cloud")
      private boolean areaCloud = true;
      @SerializedName("radius")
      private float radius = 5.5F;
      @SerializedName("radius_per_tick")
      private float radiusPerTick = 0.01F;
      @SerializedName("wait_time")
      private int waitTime = 20;
      @SerializedName("duration")
      private int duration = 200;
      @SerializedName("particles")
      private ParticleOptions particles = ParticleTypes.f_123806_;
      @SerializedName("ignite")
      private boolean ignite = false;
      @SerializedName("ignite_time")
      private int igniteTime = 2;
      @SerializedName("extinguish_by_smoke")
      private boolean extinguishBySmoke = false;
      @SerializedName("effects")
      private List<EffectCloudThrowableData.EffectData> effects = Lists.newArrayList();

      public CloudData() {
      }

      public CloudData(
         boolean areaCloud,
         float radius,
         float radiusPerTick,
         int waitTime,
         int duration,
         ParticleOptions particles,
         boolean ignite,
         int igniteTime,
         boolean extinguishBySmoke,
         List<EffectCloudThrowableData.EffectData> effects
      ) {
         this.areaCloud = areaCloud;
         this.radius = radius;
         this.radiusPerTick = radiusPerTick;
         this.waitTime = waitTime;
         this.duration = duration;
         this.particles = particles;
         this.ignite = ignite;
         this.igniteTime = igniteTime;
         this.extinguishBySmoke = extinguishBySmoke;
         this.effects = effects;
      }

      public boolean isAreaCloud() {
         return this.areaCloud;
      }

      public float getRadius() {
         return this.radius;
      }

      public float getRadiusPerTick() {
         return this.radiusPerTick;
      }

      public int getWaitTime() {
         return this.waitTime;
      }

      public int getDuration() {
         return this.duration;
      }

      public ParticleOptions getParticles() {
         return this.particles;
      }

      public boolean isIgnite() {
         return this.ignite;
      }

      public int getIgniteTime() {
         return this.igniteTime;
      }

      public boolean isExtinguishBySmoke() {
         return this.extinguishBySmoke;
      }

      public List<EffectCloudThrowableData.EffectData> getEffects() {
         return this.effects;
      }

      public List<MobEffectInstance> getEffectInstances() {
         List<MobEffectInstance> instances = Lists.newArrayList();

         for (EffectCloudThrowableData.EffectData effect : this.effects) {
            instances.add(effect.toInstance());
         }

         return instances;
      }
   }

   public record EffectData(MobEffect type, int duration, int amplifier, boolean visible, boolean showIcon) {
      public MobEffectInstance toInstance() {
         return new MobEffectInstance(this.type, this.duration, this.amplifier, false, this.visible, this.showIcon);
      }
   }

   public static class EffectDataDeSerializer implements JsonDeserializer<EffectCloudThrowableData.EffectData> {
      public EffectCloudThrowableData.EffectData deserialize(JsonElement ele, Type type, JsonDeserializationContext ctx) throws JsonParseException {
         if (ele.isJsonObject()) {
            JsonObject obj = ele.getAsJsonObject();
            ResourceLocation id = (ResourceLocation)ctx.deserialize(obj.get("type"), ResourceLocation.class);
            MobEffect type1 = (MobEffect)ForgeRegistries.MOB_EFFECTS.getValue(id);
            if (type1 == null) {
               throw new JsonParseException("Unknown effect type: " + id);
            }

            int duration = GsonHelper.m_13824_(obj, "duration", 200);
            if (duration < 0) {
               throw new JsonParseException("Duration must be non-negative: " + duration);
            }

            int amplifier = GsonHelper.m_13824_(obj, "amplifier", 0);
            if (amplifier < 0) {
               throw new JsonParseException("Amplifier must be non-negative: " + amplifier);
            }

            boolean visible = GsonHelper.m_13855_(obj, "visible", true);
            boolean showIcon = GsonHelper.m_13855_(obj, "show_icon", true);
            return new EffectCloudThrowableData.EffectData(type1, duration, amplifier, visible, showIcon);
         } else {
            throw new JsonParseException("Invalid EffectData JSON: " + ele);
         }
      }
   }
}
