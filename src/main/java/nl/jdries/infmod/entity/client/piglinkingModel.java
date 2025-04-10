package nl.jdries.infmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import nl.jdries.infmod.InfMod;
import nl.jdries.infmod.entity.custom.piglinkingentity;

public class piglinkingModel<T extends piglinkingentity> extends HierarchicalModel<T> {

        // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
        public static final ModelLayerLocation LAYER_LOCATION =
                new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(InfMod.MOD_ID, "piglinking"), "main");

        private final ModelPart bb_main;

        public  piglinkingModel(ModelPart root) {

            this.bb_main = root.getChild("bb_main");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 26).addBox(-14.0F, -8.0F, 2.0F, 10.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 24.0F, 0.0F));

            PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -15.0F, -10.0F, 41.0F, 15.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

            return LayerDefinition.create(meshdefinition, 128, 128);
        }

        @Override
        public void setupAnim(piglinkingentity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            this.root().getAllParts().forEach(ModelPart::resetPose);
            this.animateWalk(piglinkingAnimations.ANIMATION1, limbSwing,limbSwingAmount, 2.0f,2.0f);
        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {

            bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        }
        @Override
        public ModelPart root() {
            return this.bb_main;
        }

}
