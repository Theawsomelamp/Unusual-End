package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.common.blocks.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlobShieldItem extends ShieldItem {
    public BlobShieldItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(ModBlocks.ENDERBLOB_BLOCK.asItem());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> list, TooltipContext context) {
        super.appendTooltip(stack, world, list, context);
        list.add(Text.translatable("lore.unusualend.when_block").formatted(Formatting.GRAY));
        list.add(Text.translatable("lore.unusualend.blob_shield_1").formatted(Formatting.GRAY));
        list.add(Text.translatable("lore.unusualend.blob_shield_2").formatted(Formatting.GRAY));
    }
}
