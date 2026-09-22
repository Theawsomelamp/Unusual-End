package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.items.ModItems;

public class VoidTotemItem extends Item {
    // Note: item is missing linking functionality
    public VoidTotemItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity instanceof LivingEntity livingEntity && entity.getY() <= 0 && world.getRegistryKey().getValue().toString().equals("minecraft:the_end")) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 600, 0, true, false));
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 0, true, false));
            this.finishUsing(stack, world, livingEntity);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient()) {
            user.requestTeleport(user.getX(), 260, user.getZ());
            user.playSound(SoundEvents.ENTITY_PHANTOM_AMBIENT, 2, 1);
        }

        if (user instanceof PlayerEntity player) {
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            if (player instanceof ServerPlayerEntity serverPlayer) {
                Advancement advancement = serverPlayer.server.getAdvancementLoader().get(UnusualEnd.makeID("worse_than_death"));
                AdvancementProgress progress = serverPlayer.getAdvancementTracker().getProgress(advancement);
                if (!progress.isDone()) {
                    for (String criteria : progress.getUnobtainedCriteria())
                        serverPlayer.getAdvancementTracker().grantCriterion(advancement, criteria);
                }
            }

            // Doesn't work (technically a faithful port since it also doesn't work on forge)
            if (world.isClient()) {
                MinecraftClient.getInstance().gameRenderer.showFloatingItem(ModItems.VOID_TOTEM.getDefaultStack());
            }

            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        } else {
            stack.decrement(1);
        }

        return super.finishUsing(stack, world, user);
    }
}
