package net.shendi.horseraddish.block;

import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shendi.horseraddish.HorseRaddish;
import net.shendi.horseraddish.block.custom.HorseRaddishCropBlock;
import net.shendi.horseraddish.block.custom.RaddishCropBlock;
import net.shendi.horseraddish.item.HRItems;

public class HRBlocks {
    public static final DeferredRegister<Block> BLOCKS = 
    DeferredRegister.create(ForgeRegistries.BLOCKS, HorseRaddish.MODID);


        public static final RegistryObject<Block> HORSERADDISH_CROP = BLOCKS.register("horseraddish_crop",
            () -> new HorseRaddishCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));

        public static final RegistryObject<Block> RADDISH_CROP = BLOCKS.register("raddish_crop",
            () -> new RaddishCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        HRItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}