package me.xjqsh.lrtactical.api.collision;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.List;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ITargetFilter {
   @NotNull
   List<Entity> filterTargets(LivingEntity var1, Vec3 var2, Vec3 var3);

   default double getMaxRange() {
      return 2.5;
   }

   static boolean hasLineOfSight(LivingEntity attacker, Entity pEntity) {
      if (pEntity.m_9236_() == attacker.m_9236_()) {
         Vec3 vec3 = new Vec3(attacker.m_20185_(), attacker.m_20188_(), attacker.m_20189_());
         if (clip(attacker, new Vec3(pEntity.m_20185_(), pEntity.m_20188_(), pEntity.m_20189_()), vec3)) {
            return true;
         }

         AABB boundingBox = pEntity.m_20191_();

         for (double x : new double[]{boundingBox.f_82288_, boundingBox.f_82291_}) {
            for (double y : new double[]{boundingBox.f_82289_, boundingBox.f_82292_}) {
               for (double z : new double[]{boundingBox.f_82290_, boundingBox.f_82293_}) {
                  if (clip(attacker, new Vec3(x, y, z), vec3)) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   static boolean clip(LivingEntity attacker, Vec3 vec31, Vec3 vec3) {
      return vec31.m_82554_(vec3) > 128.0
         ? false
         : attacker.m_9236_().m_45547_(new ClipContext(vec3, vec31, Block.COLLIDER, Fluid.NONE, attacker)).m_6662_() == Type.MISS;
   }

   class Deserializer implements JsonDeserializer<ITargetFilter> {
      public ITargetFilter deserialize(JsonElement element, java.lang.reflect.Type type, JsonDeserializationContext ctx) throws JsonParseException {
         if (!element.isJsonObject()) {
            throw new JsonParseException("Expected a JsonObject, get " + element);
         }

         JsonObject jsonObject = (JsonObject)element;
         String typeName = GsonHelper.m_13906_(jsonObject, "type");

         return switch (typeName) {
            case "cone" -> (ITargetFilter)ctx.deserialize(jsonObject, ConeFilter.class);
            case "ray" -> (ITargetFilter)ctx.deserialize(jsonObject, RayFilter.class);
            case "obb" -> (ITargetFilter)ctx.deserialize(jsonObject, OBBFilter.class);
            default -> throw new JsonParseException("Unknown filter type: " + typeName);
         };
      }
   }
}
