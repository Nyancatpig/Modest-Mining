package com.ncpbails.modestmining.block;

import com.ncpbails.modestmining.ModestMining;
import com.ncpbails.modestmining.block.custom.BrushingBlock;
import com.ncpbails.modestmining.block.custom.ChiselingBlock;
import com.ncpbails.modestmining.block.custom.ForgeBlock;

import com.ncpbails.modestmining.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModestMining.MOD_ID);

    //BLOCKS
    public static final RegistryObject<Block> COKE_BLOCK = registerBlock("coke_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS, true, 64000);

    public static final RegistryObject<Block> COAL_POWDER_BLOCK = registerBlock("coal_powder_block",
            () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)), CreativeModeTab.TAB_BUILDING_BLOCKS, true, 24000);

    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS, false, 0);

    public static final RegistryObject<Block> ROSEGOLD_BLOCK = registerBlock("rosegold_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS, false, 0);

    public static final RegistryObject<Block> PRISMARITE_BLOCK = registerBlock("prismarite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS, false, 0);


    public static final RegistryObject<Block> FORGE = registerBlock("forge",
            () -> new ForgeBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY)
                    .strength(5.0f, 6.0f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_DECORATIONS, false, 0);

    public static final RegistryObject<Block> COMPACT_AMETHYST_BLOCK = registerBlock("compact_amethyst_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS, false, 0);

    public static final RegistryObject<Block> OCEANIC_REMAINS = registerBlock("oceanic_remains",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)), CreativeModeTab.TAB_BUILDING_BLOCKS, false, 0);


    public static final RegistryObject<Block> ANCIENT_DIRT = registerBlockNoItem("ancient_dirt",
            () -> new BrushingBlock(BlockBehaviour.Properties.copy(Blocks.DIRT).noOcclusion()));

    public static final RegistryObject<Block> ANCIENT_STONE = registerBlockNoItem("ancient_stone",
            () -> new ChiselingBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab, Boolean isFuel, Integer fuelAmount) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, isFuel, fuelAmount);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockNoItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab, Boolean isFuel, Integer fuelAmount) {
        if(isFuel == false) {
            return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                    new Item.Properties().tab(tab)));
        } else {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)){
            @Override public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {return fuelAmount;}});
        }
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}