package net.sashakyotoz.unusualend.client.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.entity.custom.PhantomArrowEntity;

public class PhantomArrowRenderer extends ProjectileEntityRenderer<PhantomArrowEntity> {
    public PhantomArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PhantomArrowEntity entity) {
        return UnusualEnd.makeID("textures/entities/phantom_arrow.png");
    }
}
