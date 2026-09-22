package net.sashakyotoz.unusualend.common.effects.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;


public class EnderInfectionEffect extends StatusEffect {
    public EnderInfectionEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof EndermiteEntity)
            entity.removeStatusEffect(this);

        World world = entity.getEntityWorld();
        Random random = world.getRandom();

        if (random.nextDouble() < 0.005) {
            entity.damage(new DamageSource(RegistryEntry.of(new DamageType("infection_damage", 0.0f))), 1);
            world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_ENDERMITE_HURT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            for (int i = 0; i < 20; ++i) {
                world.addParticle(ParticleTypes.REVERSE_PORTAL, entity.getParticleX(entity.getWidth()), entity.getRandomBodyY(), entity.getParticleZ(entity.getWidth()), 0.0f, 0.0f, 0.0f);
            }
            if (!world.isClient) {
                for(int i = 0; i < 16; ++i) {
                    double g = entity.getX() + (entity.getRandom().nextDouble() - (double)0.5F) * (double)3.0F;
                    double h = MathHelper.clamp(entity.getY() + (double)(entity.getRandom().nextInt(2) - 1), world.getBottomY(), world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1);
                    double j = entity.getZ() + (entity.getRandom().nextDouble() - (double)0.5F) * (double)3.0F;
                    if (entity.hasVehicle()) {
                        entity.stopRiding();
                    }
                    entity.teleport(g, h, j, true);
                }
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
