package ohrm.malgra.world;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by Toby on 28/07/2016.
 */
public class Dimensions {

    public static final int researchDimID = DimensionManager.getNextFreeDimId();
    public static DimensionType researchDim;

    public static void init() {
        researchDim = DimensionType.register("research", "", 27, WorldProviderResearch.class, false);
        DimensionManager.registerDimension(researchDimID, researchDim);
    }

}
