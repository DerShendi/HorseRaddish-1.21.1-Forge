package net.shendi.horseraddish;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HorseRaddish.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HorseRaddishClient {
    public HorseRaddishClient() {
        // Client-only initialisation can go here if needed.
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        HorseRaddish.LOGGER.info("HELLO FROM CLIENT SETUP");
        HorseRaddish.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
