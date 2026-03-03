package net.shendi.horseraddish.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shendi.horseraddish.HorseRaddish;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = HorseRaddish.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HRDataGenerator {
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if (event.includeServer()) {
            generator.addProvider(true, new HRRecipeProvider(generator.getPackOutput(), event.getLookupProvider()));
            generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                    List.of(new LootTableProvider.SubProviderEntry(HRBlockLoottableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
//            generator.addProvider(event.includeServer(), new HRItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        }
        if (event.includeClient()) {
            generator.addProvider(true, new HRItemModelProvider(generator.getPackOutput(), event.getExistingFileHelper()));
            generator.addProvider(true, new HRBlockStateProvider(generator.getPackOutput(), event.getExistingFileHelper()));
        }
    }
}
