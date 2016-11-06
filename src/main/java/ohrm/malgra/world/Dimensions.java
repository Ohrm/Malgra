package ohrm.malgra.world;

import net.minecraft.util.IntegerCache;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.server.FMLServerHandler;
import ohrm.malgra.MalgraMain;
import org.apache.logging.log4j.Level;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static scala.swing.event.Key.F;

/**
 * Created by Toby on 28/07/2016.
 */
public class Dimensions {

    public static Map<String, Integer> researchDimIDs = new HashMap<String, Integer>();
    public static Map<Integer, DimensionType> researchDimTypes = new HashMap<Integer, DimensionType>();

    public static void init() {

    }

    public static void addDim(String name, int id, boolean shouldSave){

        researchDimIDs.put(name, id);

        if(shouldSave)
            saveDims();

    }

    public static void loadDims(){

        File file = new File(DimensionManager.getCurrentSaveRootDirectory().toPath() + "/researchDims.mal");
        if(file.exists() && !file.isDirectory()){
            try {

                FileInputStream input = new FileInputStream(DimensionManager.getCurrentSaveRootDirectory().toPath() + "/researchDims.mal");
                ObjectInputStream object = new ObjectInputStream(input);

                researchDimIDs = (HashMap<String, Integer>) object.readObject();

                object.close();
                Iterator it = researchDimIDs.entrySet().iterator();
                while (it.hasNext()) {

                    Map.Entry pair = (Map.Entry)it.next();
                    DimensionType researchDim = DimensionType.register("research" + (String)pair.getKey(), "", (Integer)pair.getValue(), WorldProviderResearch.class, false);

                    DimensionManager.registerDimension((Integer)pair.getValue(), researchDim);

                    //Dimensions.researchDims.put(event.getEntity().getName(), researchDimID);
                    Dimensions.addDim((String)pair.getKey(), (Integer)pair.getValue(), true);
                    Dimensions.researchDimTypes.put((Integer)pair.getValue(), researchDim);
                }

            }catch(Exception e){

                MalgraMain.logger.log(Level.ERROR, "Failed to load dimensions " + e.getMessage());

            }
        }

    }

    public static void saveDims(){

        try {
            FileOutputStream output = new FileOutputStream(DimensionManager.getCurrentSaveRootDirectory().toPath() + "/researchDims.mal");

            ObjectOutputStream object = new ObjectOutputStream(output);

            object.writeObject(researchDimIDs);

            object.close();
        }catch(Exception e){

            MalgraMain.logger.log(Level.ERROR, "Failed to save dimensions " + e.getMessage());

        }

    }

}
