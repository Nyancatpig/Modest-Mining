package com.ncpbails.modestmining.entity.client;

import com.ncpbails.modestmining.ModestMining;
import com.ncpbails.modestmining.entity.custom.ClamEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ClamModel extends AnimatedGeoModel<ClamEntity> {
    @Override
    public ResourceLocation getModelLocation(ClamEntity object) {
        return new ResourceLocation(ModestMining.MOD_ID, "geo/clam.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ClamEntity object) {
        return new ResourceLocation(ModestMining.MOD_ID, "textures/entity/clam/raccoon.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ClamEntity animatable) {
        return new ResourceLocation(ModestMining.MOD_ID, "animations/clam.animation.json");
    }
}
