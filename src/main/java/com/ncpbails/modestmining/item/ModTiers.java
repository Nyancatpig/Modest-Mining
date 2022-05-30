package com.ncpbails.modestmining.item;

import com.ncpbails.modestmining.util.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {

    public static final ForgeTier FLINT = new ForgeTier(0, 45, 1f, 1, 2,
                                            ModTags.Blocks.NEEDS_FLINT_TOOL, () -> Ingredient.of(Items.FLINT));

    public static final ForgeTier COPPER = new ForgeTier(1, 600, 4.0f, 0f, 10,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(Items.COPPER_INGOT));

    public static final ForgeTier STEEL = new ForgeTier(2, 1200, 7.0f, 2.5f, 14,
                                            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.STEEL_INGOT.get()));

    public static final ForgeTier ROSEGOLD = new ForgeTier(0, 1200, 12.0f, 0f, 22,
                                            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ModItems.ROSEGOLD_INGOT.get()));

    public static final ForgeTier PRISMARITE = new ForgeTier(4, 2031, 9.0f, 4f, 13,
                                            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.PRISMARITE_INGOT.get()));

}