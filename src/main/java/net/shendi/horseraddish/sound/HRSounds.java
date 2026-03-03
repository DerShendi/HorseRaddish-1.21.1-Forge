package net.shendi.horseraddish.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shendi.horseraddish.HorseRaddish;

public class HRSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HorseRaddish.MODID);

    public static final RegistryObject<SoundEvent> HORSE_LANDING_1 = registerSoundEvent("horse_landing_1");
    public static final RegistryObject<SoundEvent> HORSE_LANDING_2 = registerSoundEvent("horse_landing_2");
    public static final RegistryObject<SoundEvent> HORSE_LANDING_3 = registerSoundEvent("horse_landing_3");
    public static final RegistryObject<SoundEvent> HORSE_LANDING_4 = registerSoundEvent("horse_landing_4");
    public static final RegistryObject<SoundEvent> HORSE_LANDING_5 = registerSoundEvent("horse_landing_5");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(HorseRaddish.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
