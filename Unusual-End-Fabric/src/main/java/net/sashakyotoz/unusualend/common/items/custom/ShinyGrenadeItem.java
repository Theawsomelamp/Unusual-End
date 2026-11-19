package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.entity.custom.ShinyGrenadeEntity;

public class ShinyGrenadeItem extends DescriptiveItem{
    public ShinyGrenadeItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            ShinyGrenadeEntity shinyGrenadeEntity = new ShinyGrenadeEntity(user, world);
            shinyGrenadeEntity.setItem(itemStack);
            shinyGrenadeEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(shinyGrenadeEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        user.getItemCooldownManager().set(user.getStackInHand(hand).getItem(), UnusualEnd.CONFIG.shinyChargeCooldown);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
