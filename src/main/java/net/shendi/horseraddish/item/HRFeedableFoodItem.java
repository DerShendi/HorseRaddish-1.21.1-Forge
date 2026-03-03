package net.shendi.horseraddish.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.shendi.horseraddish.effect.HREffects;

public class HRFeedableFoodItem extends Item {
    public HRFeedableFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        Level level = player.level();
        if (level.isClientSide) return InteractionResult.SUCCESS;

        boolean fed = false;

            if (target instanceof AbstractHorse || target instanceof Pig) {
                try {
                    target.addEffect(new MobEffectInstance(HREffects.SPICYEFFECT.getHolder().orElseThrow(), 400));
                    if (target instanceof Animal) {
                        ((Animal) target).setInLove(player);
                    }
                    fed = true;
                } catch (Exception ignored) {}
        }

        if (fed) {
            if (!player.isCreative()) stack.shrink(1);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
