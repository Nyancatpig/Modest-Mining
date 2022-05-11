package com.ncpbails.modestmining.event;

import com.ncpbails.modestmining.ModestMining;
import com.ncpbails.modestmining.entity.ModEntityTypes;
import com.ncpbails.modestmining.entity.custom.ClamEntity;
import com.ncpbails.modestmining.recipe.ForgeRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModestMining.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, ForgeRecipe.Type.ID, ForgeRecipe.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.CLAM.get(), ClamEntity.setAttributes());
    }
}
