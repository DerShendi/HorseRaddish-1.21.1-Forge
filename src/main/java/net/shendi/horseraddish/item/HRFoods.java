package net.shendi.horseraddish.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.shendi.horseraddish.effect.HREffects;

public class HRFoods {

    public static final FoodProperties RADISH = new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f)
            .build();

    public static final FoodProperties HORSE_RADISH = new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f)
            .effect(new MobEffectInstance(HREffects.SPICYEFFECT.getHolder().orElseThrow(), 400), 1f).build();


}