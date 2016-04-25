package ohrm.malgra.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by Toby on 18/04/2016.
 */
public class LiquidMalgra extends BlockFluidClassic {

    public LiquidMalgra(Fluid fluid, Material material) {
        super(fluid, material);
        setDensity(1000);
        setLightLevel(0.5F);
        setTemperature(295);
    }

}
