package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class FireflyEggItem extends Item {
    public FireflyEggItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        Random random = world.getRandom();
        world.playSound(null, blockPos, SoundEvents.ENTITY_TURTLE_EGG_BREAK, SoundCategory.PLAYERS, 0.5f, 1.3f);
        for (int i = 0; i < 25; ++i) {
            world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, this.getDefaultStack()), blockPos.getX() + (random.nextDouble()), blockPos.getY() + 1, blockPos.getZ() + (random.nextDouble()), 0.0F, 0.0F, 0.0F);
        }
        if (random.nextFloat() < 0.4) {
            // TODO: Summon firefly
        }
        return ActionResult.CONSUME;
    }
}
