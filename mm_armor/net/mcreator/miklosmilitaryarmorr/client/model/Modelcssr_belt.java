package net.mcreator.miklosmilitaryarmorr.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class Modelcssr_belt<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelcssr_belt"), "main");
   public final ModelPart Body;
   public final ModelPart bone;

   public Modelcssr_belt(ModelPart root) {
      this.Body = root.m_171324_("Body");
      this.bone = root.m_171324_("bone");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Body = partdefinition.m_171599_(
         "Body",
         CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(20, 14)
            .m_171488_(0.0F, 1.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(8, 20)
            .m_171488_(0.0F, -2.0F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 10.0F, 1.5F, -3.0523F, -1.5309F, -3.1361F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 20)
            .m_171488_(0.0F, -2.0F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 20)
            .m_171488_(0.0F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 11.0F, 2.0F, -0.7859F, -1.5093F, 0.7906F)
      );
      PartDefinition belt_r1 = Body.m_171599_(
         "belt_r1",
         CubeListBuilder.m_171558_().m_171514_(10, 14).m_171488_(-0.7F, -4.3F, -1.3F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.25F, 12.0F, 1.0F, 0.0619F, 1.5343F, -0.0137F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(16, 20).m_171488_(0.7F, -4.6F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.75F, 13.0F, -2.0F, 0.0436F, 0.0873F, 0.0F)
      );
      PartDefinition belt_r2 = Body.m_171599_(
         "belt_r2",
         CubeListBuilder.m_171558_().m_171514_(0, 14).m_171488_(-0.8F, -4.3F, -2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.25F, 12.25F, -2.0F, 0.0436F, 0.0873F, 0.0F)
      );
      PartDefinition bone = partdefinition.m_171599_("bone", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      return LayerDefinition.m_171565_(meshdefinition, 32, 32);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.bone.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
