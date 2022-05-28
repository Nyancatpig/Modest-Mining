package com.ncpbails.modestmining.block.entity;

import com.ncpbails.modestmining.ModestMining;
import com.ncpbails.modestmining.block.ModBlocks;
import com.ncpbails.modestmining.block.entity.custom.BrushingBlockEntity;
import com.ncpbails.modestmining.block.entity.custom.ChiselingBlockEntity;
import com.ncpbails.modestmining.block.entity.custom.ForgeBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ModestMining.MOD_ID);

    public static final RegistryObject<BlockEntityType<BrushingBlockEntity>> BRUSHING_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("brushing_block_entity", () ->
                    BlockEntityType.Builder.of(BrushingBlockEntity::new,
                            ModBlocks.ANCIENT_DIRT.get()).build(null));

    public static final RegistryObject<BlockEntityType<ChiselingBlockEntity>> CHISELING_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("chiseling_block_entity", () ->
                    BlockEntityType.Builder.of(ChiselingBlockEntity::new,
                            ModBlocks.ANCIENT_STONE.get()).build(null));

    public static final RegistryObject<BlockEntityType<ForgeBlockEntity>> FORGE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("forge_block_entity", () ->
                    BlockEntityType.Builder.of(ForgeBlockEntity::new,
                            ModBlocks.FORGE.get()).build(null));




    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}