package com.ncpbails.modestmining.world.feature;

import com.ncpbails.modestmining.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_OCEANIC_REMAINS = List.of(
            //OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.OCEANIC_REMAINS.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.OCEANIC_REMAINS.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OCEANIC_REMAINS = FeatureUtils.register("oceanic_remains",
            Feature.ORE, new OreConfiguration(OVERWORLD_OCEANIC_REMAINS, 4));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> SHELL =
            FeatureUtils.register("shell", Feature.FLOWER,
                    new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SHELL.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> ROCKS =
            FeatureUtils.register("rocks", Feature.FLOWER,
                    new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ROCKS.get())))));
}
