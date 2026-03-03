package net.shendi.horseraddish.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.shendi.horseraddish.HorseRaddish;
import net.shendi.horseraddish.item.HRItems;

public class HRItemModelProvider extends ItemModelProvider {
    public HRItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HorseRaddish.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(HRItems.EMPTY_NET.get());
        basicItem(HRItems.HORSE_IN_A_NET.get());
        basicItem(HRItems.RADISH.get());
        basicItem(HRItems.HORSE_RADISH.get());
    }

}
