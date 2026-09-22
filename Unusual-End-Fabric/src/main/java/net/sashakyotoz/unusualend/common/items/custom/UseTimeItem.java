package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UseTimeItem extends Item {
    private final int useTime;

    public UseTimeItem(Settings settings, int useTime) {
        super(settings);
        this.useTime = useTime;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return useTime;
    }
}
