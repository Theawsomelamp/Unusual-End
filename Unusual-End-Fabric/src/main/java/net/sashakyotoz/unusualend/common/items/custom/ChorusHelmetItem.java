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

public class ChorusHelmetItem extends ArmorItem {
    public ChorusHelmetItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> list, TooltipContext context) {
        super.appendTooltip(stack, world, list, context);
        list.add(Text.translatable("lore.unusualend.when_hurt").formatted(Formatting.GRAY));
        list.add(Text.translatable("lore.unusualend.chorus_helmet_1").formatted(Formatting.GRAY));
        list.add(Text.translatable("lore.unusualend.chorus_helmet_2").formatted(Formatting.DARK_GRAY));
    }


}
