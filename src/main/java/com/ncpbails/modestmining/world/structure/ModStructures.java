package com.ncpbails.modestmining.world.structure;

import com.ncpbails.modestmining.ModestMining;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {

    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE =
            DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, ModestMining.MOD_ID);

    public static final RegistryObject<StructureFeature<?>> ANCIENT_DIGGING_SITE =
            DEFERRED_REGISTRY_STRUCTURE.register("ancient_digging_site", AncientDiggingSiteStructure::new);

    public static void register(IEventBus eventBus) {
        DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
    }
}