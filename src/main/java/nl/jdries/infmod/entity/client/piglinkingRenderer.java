package nl.jdries.infmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import nl.jdries.infmod.InfMod;
import nl.jdries.infmod.entity.custom.piglinkingentity;

public class piglinkingRenderer extends MobRenderer<piglinkingentity, piglinkingModel<piglinkingentity>> {
    public piglinkingRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new piglinkingModel<>(pContext.bakeLayer(piglinkingModel.LAYER_LOCATION)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(piglinkingentity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(InfMod.MOD_ID, "textures/entity/piglinking.png");
    }

    @Override
    public void render(piglinkingentity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
