package ohrm.malgra.world;

import net.minecraft.util.IntegerCache;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toby on 28/07/2016.
 */
public class Dimensions {

    //public static final int researchDimID = DimensionManager.getNextFreeDimId();
    //public static DimensionType researchDim;

    public static Map<String, Integer> researchDimIDs = new HashMap<String, Integer>();
    public static Map<Integer, DimensionType> researchDimTypes = new HashMap<Integer, DimensionType>();

    public static void init() {
//        researchDim = DimensionType.register("researchTest", "", researchDimID, WorldProviderResearch.class, false);
//        DimensionManager.registerDimension(researchDimID, researchDim);
    }

    public static void addDim(String name, int id){

        System.out.println(name + " " + id + " registered");
        researchDimIDs.put(name, id);

    }

}
