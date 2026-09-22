package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.UnusualEnd;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrismaticMirrorItem extends UseTimeItem {
    public PrismaticMirrorItem(Settings settings, int useTime) {
        super(settings, useTime);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> list, TooltipContext context) {
        super.appendTooltip(stack, world, list, context);
        list.add(Text.translatable("lore.unusualend.when_rightclick").formatted(Formatting.GRAY));
        list.add(Text.translatable("lore.unusualend.warp_spawn").formatted(Formatting.GRAY));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity player) {
            BlockPos spawnPoint = player.getSpawnPointPosition();
            String spawnWorld = player.getSpawnPointDimension().toString();

            if (world.getRegistryKey().toString().equals(spawnWorld) && spawnPoint != null) {
                player.teleport(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ());

                player.getItemCooldownManager().set(this, UnusualEnd.CONFIG.prismaticMirrorCooldown);
                stack.damage(1, player, playerEntity -> playerEntity.sendEquipmentBreakStatus(
                        player.getActiveHand() == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND));

                player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1, 1);
            }
        }
        return super.finishUsing(stack, world, user);
    }
}
