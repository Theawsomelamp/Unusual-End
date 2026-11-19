package net.sashakyotoz.unusualend.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Collection;
import java.util.Iterator;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @ModifyVariable(method = "attack(Lnet/minecraft/entity/Entity;)V", at = @At(value = "LOAD", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getKnockback(Lnet/minecraft/entity/LivingEntity;)I", id = "i"))
    public int applyDefaultKnockbackFromStack(int i) {
        // The attack knockback attribute doesn't exist on players by default (it's something forge adds)
        // So this is a way to handle it on fabric
        ItemStack itemInMainHand = this.getEquippedStack(EquipmentSlot.MAINHAND);
        if (itemInMainHand.isEmpty()) return i;
        Collection<EntityAttributeModifier> knockBackAttributes = itemInMainHand.getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
        if (knockBackAttributes.isEmpty()) return i;

        Iterator<EntityAttributeModifier> iterator = knockBackAttributes.iterator();
        if (iterator.hasNext()) {
            EntityAttributeModifier entityAttributeModifier = iterator.next();
            double knockBackValue = entityAttributeModifier.getValue();

            return i + (int) knockBackValue;
        }
        return i;
    }

    @WrapOperation(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    protected boolean damageModdedShield(ItemStack instance, Item item, Operation<Boolean> original) {
        return (item instanceof ShieldItem);
    }
}
