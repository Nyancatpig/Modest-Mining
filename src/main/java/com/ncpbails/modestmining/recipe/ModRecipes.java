package com.ncpbails.modestmining.recipe;

import com.ncpbails.modestmining.ModestMining;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ModestMining.MOD_ID);


    public static final RegistryObject<RecipeSerializer<ForgeRecipe>> FORGING_SERIALIZER =
            SERIALIZERS.register("forging", () -> ForgeRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
