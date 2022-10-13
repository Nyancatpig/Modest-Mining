package com.ncpbails.modestmining.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;

import java.util.Map;

public class PrismariteEffect extends MobEffect {
    public PrismariteEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
        this.addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "7107DE5E-7CE8-4030-940E-514C1F160890", 0.0F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
        return 0.1 * (double) (amplifier + 1);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMapIn, int amplifier) {
        if (!entity.level.isClientSide) {
            int duration = entity.getEffect(ModEffects.PRISMARITE_SPEED.get()).getDuration();
            int totalDuration = entity.getPersistentData().contains("SugarRushDuration") ? entity.getPersistentData().getInt("SugarRushDuration") : 0;
            float amount = duration >= totalDuration / 3 ? (amplifier + 2) * 0.5F : -(amplifier + 2) * 1.25F;
            for (Map.Entry<Attribute, AttributeModifier> entry : this.getAttributeModifiers().entrySet()) {
                AttributeInstance iattributeinstance = attributeMapIn.getInstance(entry.getKey());
                if (iattributeinstance != null) {
                    AttributeModifier attributemodifier = entry.getValue();
                    iattributeinstance.removeModifier(attributemodifier);
                    iattributeinstance.addPermanentModifier(new AttributeModifier(attributemodifier.getId(), this.getDescriptionId() + " " + amplifier, amount * this.getAttributeModifierValue(amplifier, attributemodifier), attributemodifier.getOperation()));
                }
            }
        }
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        this.addAttributeModifiers(pLivingEntity, pLivingEntity.getAttributes(), pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}