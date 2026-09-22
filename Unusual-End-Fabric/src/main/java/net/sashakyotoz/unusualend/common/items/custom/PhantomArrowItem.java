package net.sashakyotoz.unusualend.common.items.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.common.entity.custom.PhantomArrowEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PhantomArrowItem extends ArrowItem {
    public PhantomArrowItem(Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        PhantomArrowEntity arrow = new PhantomArrowEntity(world, shooter);
        arrow.initFromStack();
        return arrow;
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.translatable("item.unusualend.phantom_arrow.desc").formatted(Formatting.GRAY));
    }
}
