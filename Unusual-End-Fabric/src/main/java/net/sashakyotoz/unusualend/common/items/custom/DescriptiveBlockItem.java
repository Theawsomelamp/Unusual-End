package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DescriptiveBlockItem extends BlockItem {
    public DescriptiveBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        String[] txt = Text.translatable(this.getTranslationKey() + ".desc").getString().split("\\n");
        for (String t : txt)
            tooltip.add(Text.translatable(t).formatted(Formatting.GRAY));
    }
}
