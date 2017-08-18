package ohrm.malgra.registries;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import ohrm.malgra.Reference;
import ohrm.malgra.api.research.Research;

public final class MalgraRegistryManager {

    public static IForgeRegistry<Research> researchRegistry;

    public static void CreateRegistries(){
        researchRegistry = new RegistryBuilder().setType(Research.class).setName(new ResourceLocation(Reference.MODID, "malgraResearchRegistry")).setIDRange(0, 4096).addCallback(new MalgraRegistryCallbacks.ResearchAddCallback()).create();
    }

}
