package com.ncpbails.modestmining.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ncpbails.modestmining.ModestMining;
import com.ncpbails.modestmining.entity.custom.ClamEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ClamRenderer extends GeoEntityRenderer<ClamEntity> {
    public ClamRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ClamModel());
        this.shadowRadius = 0.8f;
    }

    @Override
    public ResourceLocation getTextureLocation(ClamEntity instance) {
        return new ResourceLocation(ModestMining.MOD_ID, "textures/entity/clam/clam.png");
    }

    @Override
    public RenderType getRenderType(ClamEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}