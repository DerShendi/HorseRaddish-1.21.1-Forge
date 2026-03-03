package net.shendi.horseraddish.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HRBreedingFoodItem extends Item {
    public HRBreedingFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        Level level = player.level();
        if (level.isClientSide) return InteractionResult.SUCCESS;

        boolean fed = false;

        if (target instanceof AbstractHorse || target instanceof Pig) {
            try {
                if (target instanceof Animal) {
                    ((Animal) target).setInLove(player);
                    fed = true;
                }
            } catch (Exception ignored) {}
        }

        if (fed) {
            if (!player.isCreative()) stack.shrink(1);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
