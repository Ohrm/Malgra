package ohrm.malgra.world;

import net.minecraft.util.IntegerCache;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
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
        saveDims();

    }

    public static void loadDims(){

        File file = new File("researchDims.mal");

        if(file.exists() && !file.isDirectory()){

            try {

                FileInputStream input = new FileInputStream("researchDims.mal");
                ObjectInputStream object = new ObjectInputStream(input);

                researchDimIDs = (HashMap<String, Integer>) object.readObject();

                object.close();

                Iterator it = researchDimIDs.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    addDim((String)pair.getKey(), (Integer)pair.getValue());
                }

            }catch(Exception e){



            }
        }

    }

    public static void saveDims(){

        try {
            FileOutputStream output = new FileOutputStream("researchDims.mal");

            ObjectOutputStream object = new ObjectOutputStream(output);

            object.writeObject(researchDimIDs);

            object.close();
        }catch(Exception e){



        }

    }

}
