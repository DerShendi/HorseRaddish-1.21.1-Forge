package net.shendi.horseraddish.effect;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;

import java.util.Map;
import java.util.UUID;

public class SpicyEffect extends MobEffect {
    @SuppressWarnings("unused")
    public static final UUID SPEED_MODIFIER_UUID = UUID.fromString("1a2b3c4d-5e67-479a-9e0a-9b0f4a1d7abc");
    @SuppressWarnings("unused")
    public static final UUID STEP_HEIGHT_MODIFIER_UUID = UUID.fromString("7e2b7e1b-fc2e-4f1c-a36b-9b0f4a1d7abc");

    public static final ResourceLocation SPEED_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath("horseraddish", "spicy.speed.boost");
    public static final ResourceLocation STEP_HEIGHT_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath("horseraddish", "spicy.step.height");

    public SpicyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    // Allow specifying a custom particle type (useful to replace default effect particles)
    public SpicyEffect(MobEffectCategory category, int color, ParticleOptions particle) {
        super(category, color, particle);
    }

    @SuppressWarnings("unused")
    public void onEffectStarted(LivingEntity entity, int amplifier, @SuppressWarnings("unused") Map<Holder<Attribute>, AttributeModifier> attributeModifiers) {
        super.onEffectStarted(entity, amplifier);

        float damage = (amplifier + 1);
        entity.hurt(entity.damageSources().magic(), damage);

        AttributeInstance movementSpeed = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movementSpeed != null) {
            double speedBonus = 0.2 * (amplifier + 2);
            AttributeModifier speedModifier = new AttributeModifier(ResourceLocation.fromNamespaceAndPath("horseraddish", "spicy.speed.boost"), speedBonus, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            movementSpeed.addTransientModifier(speedModifier);
        }

        AttributeInstance stepHeight = entity.getAttribute(Attributes.STEP_HEIGHT);
        if (stepHeight != null) {
            if (stepHeight.getModifier(STEP_HEIGHT_MODIFIER_ID) != null) {
                stepHeight.removeModifier(STEP_HEIGHT_MODIFIER_ID);
            }
            // Set step height to 1.0 (one block) by adding the difference between target and current base
            double base = stepHeight.getBaseValue();
            double needed = 1.0 - base;
            AttributeModifier stepModifier = new AttributeModifier(STEP_HEIGHT_MODIFIER_ID, needed, AttributeModifier.Operation.ADD_VALUE);
            stepHeight.addTransientModifier(stepModifier);
        }
    }


    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        
        if (!entity.isSprinting()) entity.setSprinting(true);

        double attrSpeed = entity.getAttributeValue(Attributes.MOVEMENT_SPEED);
        double baseMotion = attrSpeed * 43.17 / 20.0;
        double sprintMultiplier = 1.3;
        double forwardSpeed = baseMotion * sprintMultiplier + 0.04 * amplifier;
        double yawRad = Math.toRadians(entity.getYRot());
        double fx = -Math.sin(yawRad);
        double fz = Math.cos(yawRad);
        double tx = fx * forwardSpeed;
        double tz = fz * forwardSpeed;

        Vec3 prev = entity.getDeltaMovement();
        double dot = prev.x * fx + prev.z * fz;

        if (dot < 0) {
            entity.setDeltaMovement(tx, prev.y, tz);
        } else {
            entity.setDeltaMovement(tx, prev.y, tz);
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return true;
    }

    @SuppressWarnings("unused")
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, @SuppressWarnings("unused") int amplifier) {

        AttributeInstance movementSpeed = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movementSpeed != null) {
            movementSpeed.removeModifier(SPEED_MODIFIER_ID);
        }

        AttributeInstance stepHeight = entity.getAttribute(Attributes.STEP_HEIGHT);
        if (stepHeight != null) {
            stepHeight.removeModifier(STEP_HEIGHT_MODIFIER_ID);
        }

        super.removeAttributeModifiers(attributeMap);
    }
}
