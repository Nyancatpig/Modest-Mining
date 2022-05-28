package com.ncpbails.modestmining.item.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.ncpbails.modestmining.block.ModBlocks;
import com.ncpbails.modestmining.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Map;

public class BrushItem extends DiggerItem {
    public BrushItem(float v, float v1, Tier tier, Properties properties) {
        super(v, v1, tier, BlockTags.MINEABLE_WITH_SHOVEL, properties);
    }
}
