package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public class MaskItem extends ArmorItem {
    private final String maskType;

    public MaskItem(ArmorMaterial material, Type type, Settings settings, String maskType) {
        super(material, type, settings);
        this.maskType = maskType;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> list, TooltipContext context) {
        super.appendTooltip(stack, world, list, context);
        list.add(Text.literal(maskType).formatted(Formatting.GRAY));
        list.add(Text.literal(""));
        list.add(Text.translatable("lore.unusualend.mask_1").formatted(Formatting.GRAY));
        list.add(Text.translatable("lore.unusualend.mask_2").formatted(Formatting.GRAY));
    }

    public String getMaskType() {
        return this.maskType.toLowerCase(Locale.ROOT);
    }
}
