package net.sashakyotoz.unusualend.common.config;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;

public class UnusualEndConfig extends WrappedConfig {
    @Comment("Probability in % for the Chorus Helmet ability to trigger.")
    public float chorusHelmetTeleportChance = 0.2f;
    @Comment("Maximal amount of damages the Chorus Helmet will negate when dodging an attack. Attacks superior to this value won't be affected or reduced.")
    public int maxDamageDodgedWithChorusHelmetTp = 8;
    @Comment("Whether the Crystal Flower should inflict Nausea to entities interacting with it")
    public boolean crystalFlowerNausea = true;
    @Comment("Whether players will be affected by the Endblob Shield knockback (Multiplayer PvP)")
    public boolean blobShieldPvp = true;
    @Comment("The amount of time (in ticks) the effects from using a spectral cloth should apply")
    @Comment("20 ticks 1 second, and thus 1200 ticks is 1 minute")
    public int spectralClothEffectTime = 1200;
    @Comment("")
    @Comment("")
    @Comment("Item cooldowns (in ticks)")
    public int prismaticMirrorCooldown = 6000;
    public int floatingPouchCooldown = 100;
    public int shinyChargeCooldown = 100;
}
