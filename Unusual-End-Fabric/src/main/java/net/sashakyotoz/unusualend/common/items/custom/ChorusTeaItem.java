package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChorusTeaItem extends MilkBucketItem {
    public ChorusTeaItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> list, TooltipContext context) {
        super.appendTooltip(stack, world, list, context);
        list.add(Text.translatable("lore.unusualend.clear_effects").formatted(Formatting.BLUE));
    }

    public int getMaxUseTime(ItemStack stack) {
        return 16;
    }
}
