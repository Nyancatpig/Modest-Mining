package com.ncpbails.modestmining.block.custom;

import com.ncpbails.modestmining.block.ModBlocks;
import com.ncpbails.modestmining.item.ModItems;
import com.ncpbails.modestmining.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.core.jmx.Server;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Random;

public class BrushingBlock extends Block {
    public static IntegerProperty BRUSHING = IntegerProperty.create("brushing", 0, 3);
    private static final VoxelShape[] SHAPE_BY_BRUSH;
    protected final boolean scheduleFluidTicks;
    private int progress = 0;
    private int brushProgress = 0;
    private int maxProgress = 64;
    private boolean canProgress = false;

    public BrushingBlock(Properties properties, boolean bool) {
        super(properties);
        this.registerDefaultState((BlockState)super.defaultBlockState().setValue(BRUSHING, 0));
        this.scheduleFluidTicks = bool;
    }

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_BRUSH[(Integer)state.getValue(this.BRUSHING)];
    }

//    public boolean isRandomlyTicking(BlockState state) {return true;}

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{BRUSHING});
        super.createBlockStateDefinition(builder);
    }

    public int getMaxBrushingStage() {
        return 3;
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
        return this.getMaxBrushingStage() + 1 - (Integer)blockState.getValue(BRUSHING);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        super.animateTick(stateIn, worldIn, pos, rand);
        if (rand.nextInt(10) == 0) {
            worldIn.addParticle(ParticleTypes.MYCELIUM, (double)pos.getX() + (double)rand.nextFloat(), (double)pos.getY() + 1.1D, (double)pos.getZ() + (double)rand.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
    }

    static {
        SHAPE_BY_BRUSH = new VoxelShape[]{
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D)};
    }

    protected void spawnDestroyParticles(Level p_152422_, Player p_152423_, BlockPos p_152424_, BlockState p_152425_) {
        p_152422_.levelEvent(p_152423_, 2001, p_152424_, getId(p_152425_));
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack heldStack = player.getItemInHand(handIn);
        ItemStack offhandStack = player.getOffhandItem();
        //if in main hand
        if (handIn.equals(InteractionHand.MAIN_HAND) && heldStack.is(ModItems.COPPER_BRUSH.get())) {
            canProgress = true;
            if (state.getValue(BRUSHING) == this.getMaxBrushingStage()) {
                worldIn.removeBlock(pos, true);
                dropResources(state, worldIn, pos);
            } else {
                worldIn.setBlock(pos, state.setValue(BRUSHING, state.getValue(BRUSHING) + 1), 2);
            }
            worldIn.playSound((Player)null, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 0.25F, 0.5F);
            spawnDestroyParticles(worldIn, player, pos, state);
            damageItem(heldStack, player);
            return InteractionResult.PASS;
        }
        //if in off hand
        else if (handIn.equals(InteractionHand.OFF_HAND) && offhandStack.is(ModItems.COPPER_BRUSH.get())) {
            canProgress = true;
            if (state.getValue(BRUSHING) == this.getMaxBrushingStage()) {
                worldIn.removeBlock(pos, true);
                dropResources(state, worldIn, pos);
            } else {
                worldIn.setBlock(pos, state.setValue(BRUSHING, state.getValue(BRUSHING) + 1), 2);
            }
            worldIn.playSound((Player)null, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 0.25F, 0.5F);
            spawnDestroyParticles(worldIn, player, pos, state);
            damageItem(offhandStack, player);
            return InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

    protected void damageItem(ItemStack itemStack, Player player) {
        if (!player.isCreative()) {
            itemStack.setDamageValue(itemStack.getDamageValue() + 1);
            if (itemStack.getDamageValue() >= itemStack.getMaxDamage()) {
                itemStack.shrink(1);
            }
        }
    }

}