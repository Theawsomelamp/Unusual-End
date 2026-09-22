package net.sashakyotoz.unusualend.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.sashakyotoz.unusualend.common.items.custom.MaskItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {

    @Inject(method = "isPlayerStaring", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"), cancellable = true)
    public void isPlayerStaring(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (player.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof MaskItem) {
            cir.setReturnValue(false);
        }
    }
}
