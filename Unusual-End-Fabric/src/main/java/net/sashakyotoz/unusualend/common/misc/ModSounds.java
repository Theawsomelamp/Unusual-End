package net.sashakyotoz.unusualend.common.misc;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.sashakyotoz.unusualend.UnusualEnd;

public class ModSounds {
    public static final SoundEvent MUSIC_DISC_FLYING_SHIPS = register("music_disc_flying_ships");
    public static final SoundEvent MUSIC_DISC_ENDSTONE_GOLEM_THEME = register("music_disc_endstone_golem_theme");
    public static final SoundEvent MUSIC_DISC_QUEEN = register("music_disc_queen");


    public static SoundEvent register(String name) {
        return Registry.register(Registries.SOUND_EVENT, UnusualEnd.makeID(name), SoundEvent.of(UnusualEnd.makeID(name)));
    }

    public static void register() {

    }
}
