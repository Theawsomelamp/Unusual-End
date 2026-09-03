package net.sashakyotoz.unusualend.mixin;

import net.minecraft.entity.LivingEntity;
import net.sashakyotoz.unusualend.common.effects.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "getJumpBoostVelocityModifier", at = @At("RETURN"), cancellable = true)
    private void spectralStrideJumpBoost(CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = ((LivingEntity) (Object) this);
        if (entity.hasStatusEffect(ModEffects.SPECTRAL_STRIDE)) {
            cir.setReturnValue(0.1F * ((float)entity.getStatusEffect(ModEffects.SPECTRAL_STRIDE).getAmplifier() + 1.0F));
        }
    }

    @ModifyVariable(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;"), method = "travel", name = "d", ordinal = 0)
    public double modifyFallingVelocity(double in) {
        LivingEntity entity = ((LivingEntity) (Object) this);
        if(entity.getVelocity().y > 0D) {
            return in;
        }
        if(entity.hasStatusEffect(ModEffects.SPECTRAL_STRIDE)) {
            return (in * 0.35);
        }
        return in;
    }
}