package net.shendi.horseraddish.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.shendi.horseraddish.HorseRaddish;
import net.shendi.horseraddish.item.HRItems;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class HRItemTagProvider extends ItemTagsProvider {
    public HRItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, HorseRaddish.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {


        tag(ItemTags.HORSE_FOOD)
                .add(HRItems.HORSE_RADISH.get())
                .add(HRItems.RADISH.get())

        ;
        tag(ItemTags.SNIFFER_FOOD)
                .add(HRItems.HORSE_RADISH.get())
                .add(HRItems.RADISH.get())

        ;
        tag(ItemTags.PIG_FOOD)
                .add(HRItems.HORSE_RADISH.get())
                .add(HRItems.RADISH.get())

        ;


    }
}