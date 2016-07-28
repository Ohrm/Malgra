package ohrm.malgra.world;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by Toby on 28/07/2016.
 */
public class Dimensions {

    public static final int malgraDimID = DimensionManager.getNextFreeDimId();

    public static void init() {
        DimensionType malgraDim = DimensionType.register("malgra", "", 27, WorldProviderEnd.class, false);
        DimensionManager.registerDimension(malgraDimID, malgraDim);
    }

}
