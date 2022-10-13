package com.ncpbails.modestmining.effect;

import com.ncpbails.modestmining.ModestMining;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ModestMining.MOD_ID);

    public static final RegistryObject<MobEffect> PRISMARITE_SPEED = MOB_EFFECTS.register("prismarite_speed",
            () -> new PrismariteEffect(MobEffectCategory.BENEFICIAL, 1268330));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}