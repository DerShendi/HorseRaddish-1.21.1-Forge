package net.shendi.horseraddish.item;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.shendi.horseraddish.HorseRaddish;

public class HRCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HorseRaddish.MODID);

    public static final RegistryObject<CreativeModeTab> HR_ITEMS_TAB = CREATIVE_MODE_TABS.register("horseraddish_item_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(HRItems.EMPTY_NET.get()))
                    .title(Component.translatable("creativetab.horseraddish.horseraddish_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(HRItems.HORSE_IN_A_NET.get());
                        output.accept(HRItems.EMPTY_NET.get());
                        output.accept(HRItems.RADISH.get());
                        output.accept(HRItems.HORSE_RADISH.get());

                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}