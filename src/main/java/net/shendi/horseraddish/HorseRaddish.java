package net.shendi.horseraddish;

import net.shendi.horseraddish.block.HRBlocks;
import net.shendi.horseraddish.effect.HREffects;
import net.shendi.horseraddish.event.HRDispenserEvents;
import net.shendi.horseraddish.event.HorseNetEvents;
import net.shendi.horseraddish.item.HRCreativeModeTabs;
import net.shendi.horseraddish.item.HRItems;
import net.shendi.horseraddish.sound.HRSounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.common.MinecraftForge;

@Mod(HorseRaddish.MODID)
public class HorseRaddish {
    public static final String MODID = "horseraddish";
    public static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("removal")
    public HorseRaddish() {
        this(FMLJavaModLoadingContext.get().getModEventBus(), ModLoadingContext.get());
    }

    public HorseRaddish(IEventBus modEventBus, ModLoadingContext modLoadingContext) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
        RegistryObject<CreativeModeTab> MOD_TAB = CREATIVE_TABS.register("horseraddish", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.horseraddish"))
                .icon(() -> new ItemStack(HRItems.RADISH.get()))
                .displayItems((params, output) -> {
                    output.accept(HRItems.EMPTY_NET.get());
                    output.accept(HRItems.HORSE_IN_A_NET.get());
                    output.accept(HRItems.RADISH.get());
                    output.accept(HRItems.HORSE_RADISH.get());
                })
                .build());

        CREATIVE_TABS.register(modEventBus);

        HRItems.register(modEventBus);
        HRBlocks.register(modEventBus);
        HRSounds.register(modEventBus);
        HREffects.register(modEventBus);
        HRCreativeModeTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(new HorseNetEvents());

        modLoadingContext.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        HRDispenserEvents.register();

        // Debug: log registered item IDs to verify registration at runtime
        LOGGER.info("Registered items: empty_net={}, horse_in_a_net={}, radish={}, horse_radish={}",
                HRItems.EMPTY_NET.getId(), HRItems.HORSE_IN_A_NET.getId(), HRItems.RADISH.getId(), HRItems.HORSE_RADISH.getId());
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(HRItems.EMPTY_NET);
            event.accept(HRItems.HORSE_IN_A_NET);
        }
    }

    @SubscribeEvent
    public void onServerStarting(net.minecraftforge.event.server.ServerStartingEvent event) {

    }
}
