package ohrm.malgra.client.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.Reference;


public class Sounds {
	public static SoundEvent soundSpecial;

	public static void registerSounds() {
		soundSpecial = registerSound("specialsound");
	}

	private static SoundEvent registerSound(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(Reference.MODID, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}
}