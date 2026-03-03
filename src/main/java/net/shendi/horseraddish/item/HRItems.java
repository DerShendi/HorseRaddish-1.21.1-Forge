package net.shendi.horseraddish.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.shendi.horseraddish.block.HRBlocks;
import java.util.function.Function;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shendi.horseraddish.HorseRaddish;

public class HRItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HorseRaddish.MODID);

    public static final RegistryObject<Item> EMPTY_NET = registerItem("empty_net",
            (properties) -> new NetItem(properties.stacksTo(16), false));

    public static final RegistryObject<Item> HORSE_IN_A_NET = registerItem("horse_in_a_net",
            (properties) -> new NetItem(properties.stacksTo(1), true));

    public static final RegistryObject<Item> RADISH = registerItem("radish",
            (properties) -> new HRBreedingFoodItem(properties.food(HRFoods.RADISH)));

    public static final RegistryObject<Item> HORSE_RADISH = registerItem("horse_radish",
            (properties) -> new HRFeedableFoodItem(properties.food(HRFoods.HORSE_RADISH)));

    public static final RegistryObject<Item> RADISH_SEEDS = registerItem("radish_seeds",
            (properties) -> new BlockItem(HRBlocks.RADDISH_CROP.get(), properties));

    public static final RegistryObject<Item> HORSE_RADISH_SEEDS = registerItem("horse_radish_seeds",
            (properties) -> new BlockItem(HRBlocks.HORSERADDISH_CROP.get(), properties));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

        public static RegistryObject<Item> registerItem(String name, Function<Item.Properties, Item> function) {
                return ITEMS.register(name, () -> function.apply(new Item.Properties()));
        }
}
