package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.UnusualEnd;

public class FloatingPouchItem extends DescriptiveItem {
    public FloatingPouchItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.swingHand(hand);
        user.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.5F, 1);
        stack.damage(1, user, player -> player.sendEquipmentBreakStatus(hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND));
        user.fallDistance = 0;
        if (user.getWorld() instanceof ServerWorld world1)
            world1.spawnParticles(ParticleTypes.END_ROD, user.getX(), user.getY(), user.getZ(), 5, 0.3, 0.3, 0.3, 0.1);
        if (user.isFallFlying())
            user.setVelocity(new Vec3d((user.getVelocity().getX() * 1.3), 0.8, (user.getVelocity().getZ() * 1.3)));
        else {
            user.setVelocity(new Vec3d((user.getVelocity().getX()), 1, (user.getVelocity().getZ())));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 60, 0));
        }
        user.getItemCooldownManager().set(user.getStackInHand(hand).getItem(), UnusualEnd.CONFIG.floatingPouchCooldown);
        return super.use(world, user, hand);
    }
}