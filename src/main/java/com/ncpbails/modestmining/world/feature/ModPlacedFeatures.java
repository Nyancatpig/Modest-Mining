package com.ncpbails.modestmining.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> OCEANIC_REMAINS_PLACED = PlacementUtils.register("oceanic_remains_placed",
            ModConfiguredFeatures.OCEANIC_REMAINS, ModOrePlacement.commonOrePlacement(2, // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> SHELL_PLACED = PlacementUtils.register("shell_placed",
            ModConfiguredFeatures.SHELL, RarityFilter.onAverageOnceEvery(25),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> ROCKS_PLACED = PlacementUtils.register("rocks_placed",
            ModConfiguredFeatures.ROCKS, RarityFilter.onAverageOnceEvery(25),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
}
