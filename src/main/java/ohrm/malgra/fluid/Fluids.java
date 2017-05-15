package ohrm.malgra.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import ohrm.malgra.Reference;

/**
 * Created by Toby on 18/04/2016.
 */
public class Fluids {

    public static Fluid liquidMalgra;

    public static void init() {
        liquidMalgra = new Fluid("liquidmalgra", new ResourceLocation("blocks/water_still"), new ResourceLocation("blocks/water_flow")).setUnlocalizedName("liquidMalgra");
        register(liquidMalgra);
        FluidRegistry.addBucketForFluid(liquidMalgra);
    }

    public static void register(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
    }

}
