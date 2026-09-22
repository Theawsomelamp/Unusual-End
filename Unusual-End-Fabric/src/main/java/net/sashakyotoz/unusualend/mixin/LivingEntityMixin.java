package net.sashakyotoz.unusualend.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.effects.ModEffects;
import net.sashakyotoz.unusualend.common.items.ModItems;
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

    @Inject(method = "damage", at = @At(value = "TAIL"))
    public void chorusHelmetRandomTeleports(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = ((LivingEntity) (Object) this);
        if (entity.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.CHORUS_HELMET) && Math.random() < UnusualEnd.CONFIG.chorusHelmetTeleportChance && !entity.isSneaking()) {
            World world = entity.getWorld();
            if (!world.isClient) {
                for(int i = 0; i < 16; ++i) {
                    double g = entity.getX() + (entity.getRandom().nextDouble() - (double)0.5F) * (double)16.0F;
                    double h = MathHelper.clamp(entity.getY() + (double)(entity.getRandom().nextInt(16) - 8), world.getBottomY(), world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1);
                    double j = entity.getZ() + (entity.getRandom().nextDouble() - (double)0.5F) * (double)16.0F;
                    if (entity.hasVehicle()) {
                        entity.stopRiding();
                    }

                    Vec3d vec3d = entity.getPos();
                    if (entity.teleport(g, h, j, true)) {
                        if (entity instanceof ServerPlayerEntity serverPlayer) {
                            Advancement advancement = serverPlayer.server.getAdvancementLoader().get(UnusualEnd.makeID("use_chorus_helmet"));
                            AdvancementProgress progress = serverPlayer.getAdvancementTracker().getProgress(advancement);
                            if (!progress.isDone()) {
                                for (String criteria : progress.getUnobtainedCriteria())
                                    serverPlayer.getAdvancementTracker().grantCriterion(advancement, criteria);
                            }
                        }
                        world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
                        entity.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                        if (amount <= UnusualEnd.CONFIG.maxDamageDodgedWithChorusHelmetTp) {
                            entity.heal(amount);
                        }
                        break;
                    }
                }
            }

        }
    }
}