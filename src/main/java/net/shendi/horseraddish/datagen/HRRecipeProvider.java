package net.shendi.horseraddish.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.data.recipes.RecipeCategory;
import net.shendi.horseraddish.HorseRaddish;
import net.shendi.horseraddish.item.HRItems;

import java.util.concurrent.CompletableFuture;

public class HRRecipeProvider extends RecipeProvider {
    public HRRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {

        
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HRItems.EMPTY_NET.get())
                .define('T', Items.TRIPWIRE_HOOK)
                .define('H', Items.RABBIT_HIDE)
                .define('C', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("THT")
                .pattern(" C ")
                .pattern(" S ")
                .unlockedBy("has_copper", has(Items.COPPER_INGOT))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(HorseRaddish.MODID, "empty_net"));




    }
}
