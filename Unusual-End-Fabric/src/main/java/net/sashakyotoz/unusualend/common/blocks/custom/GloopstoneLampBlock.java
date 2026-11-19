package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.common.misc.ModParticleTypes;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GloopstoneLampBlock extends RedstoneLampBlock {
    public GloopstoneLampBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> list, TooltipContext context) {
        super.appendTooltip(stack, world, list, context);
        list.add(Text.translatable("lore.unusualend.when_redstone").formatted(Formatting.GRAY));
        list.add(Text.translatable("lore.unusualend.produce_particles").formatted(Formatting.BLUE));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            world.addParticle(ModParticleTypes.PINK_FLAME, pos.getX() + random.nextBetween(-1, 2), pos.getY() + random.nextBetween(-1, 2), pos.getZ() + random.nextBetween(-1, 2), 0.0, 0.0, 0.0);
        }
    }
}
