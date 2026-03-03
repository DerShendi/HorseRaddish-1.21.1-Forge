package net.shendi.horseraddish.datagen;

import java.util.Set;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.shendi.horseraddish.block.HRBlocks;
import net.shendi.horseraddish.block.custom.RaddishCropBlock;
import net.shendi.horseraddish.item.HRItems;

public class HRBlockLoottableProvider extends BlockLootSubProvider {
    public HRBlockLoottableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {




        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(HRBlocks.RADDISH_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RaddishCropBlock.AGE, RaddishCropBlock.MAX_AGE));


        this.add(HRBlocks.RADDISH_CROP.get(), this.createCropDrops(HRBlocks.RADDISH_CROP.get(),
                HRItems.RADISH.get(), HRItems.HORSE_RADISH.get(), lootItemConditionBuilder));
        
        LootItemCondition.Builder horseLootCond = LootItemBlockStatePropertyCondition.hasBlockStateProperties(HRBlocks.HORSERADDISH_CROP.get())
            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RaddishCropBlock.AGE, RaddishCropBlock.MAX_AGE));

        this.add(HRBlocks.HORSERADDISH_CROP.get(), this.createCropDrops(HRBlocks.HORSERADDISH_CROP.get(),
            HRItems.HORSE_RADISH.get(), HRItems.RADISH.get(), horseLootCond));
        }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return HRBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
    
}
