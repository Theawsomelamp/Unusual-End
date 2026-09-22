package net.sashakyotoz.unusualend.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.effects.ModEffects;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Unique
    private static final Identifier COVERING = UnusualEnd.makeID(
            "textures/screens/cloth_overlay.png");

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private int scaledHeight;

    @Shadow
    private int scaledWidth;

    @Inject(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;hudHidden:Z", ordinal = 0, opcode = Opcodes.GETFIELD))
    public void renderSpectralClothOverlay(DrawContext context, float tickDelta, CallbackInfo ci){
        if (client.player.hasStatusEffect(ModEffects.SPECTRAL_STRIDE) && client.player.hasStatusEffect(StatusEffects.INVISIBILITY)) {
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            context.drawTexture(COVERING, 0, 0, -90, 0.0F, 0.0F, this.scaledWidth, this.scaledHeight, this.scaledWidth, this.scaledHeight);
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
        }
    }
}
