package net.shendi.horseraddish.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
// import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
// import net.minecraftforge.registries.ForgeRegistries;
// import net.minecraftforge.registries.RegistryObject;
import net.shendi.horseraddish.HorseRaddish;
import net.shendi.horseraddish.block.HRBlocks;

import java.util.function.Function;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import java.util.Optional;
import net.minecraft.server.packs.PackType;

public class HRBlockStateProvider extends BlockStateProvider {
    public HRBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, HorseRaddish.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        makeCrop(((CropBlock) HRBlocks.RADDISH_CROP.get()), "raddish_crop_stage", "raddish_crop_stage");
        makeCrop(((CropBlock) HRBlocks.HORSERADDISH_CROP.get()), "horseraddish_crop_stage", "horseraddish_crop_stage");
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        Optional<IntegerProperty> ageProp = block.getStateDefinition().getProperties().stream()
                .filter(p -> p instanceof IntegerProperty)
                .map(p -> (IntegerProperty) p)
                .findFirst();

        if (ageProp.isEmpty()) {
            ModelFile model = models().getExistingFile(modLoc("block/" + textureName));
            return new ConfiguredModel[] { new ConfiguredModel(model) };
        }

        int age = state.getValue(ageProp.get());

        ResourceLocation modTex = ResourceLocation.fromNamespaceAndPath(HorseRaddish.MODID, "block/" + textureName + age);

        ResourceLocation texToUse = modTex;
        if (!models().existingFileHelper.exists(modTex, new net.minecraftforge.common.data.ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures"))) {
            int maxAge = block.getMaxAge();
            int mapped = Math.round(((float) age / Math.max(1, maxAge)) * 7.0f);
            if (mapped < 0) mapped = 0;
            if (mapped > 7) mapped = 7;
            texToUse = mcLoc("block/wheat_stage" + mapped);
        }

        BlockModelBuilder model = models().crop(modelName + age, texToUse);
        model.renderType("cutout");

        return new ConfiguredModel[] { new ConfiguredModel(model) };
    }



    // private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
    //     simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    // }

    // private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
    //     simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("horseraddish:block/" +
    //             ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    // }

    // private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
    //     simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("horseraddish:block/" +
    //             ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    // }

}
