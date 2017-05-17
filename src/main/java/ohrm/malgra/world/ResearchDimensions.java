package ohrm.malgra.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.Constants;
import ohrm.malgra.Reference;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xyz56 on 17/05/2017.
 */
public class ResearchDimensions extends WorldSavedData{

    private static final String DATA_NAME = Reference.MODID + "MalgraResearchDims";

    public Map<String, Integer> researchDimIDs = new LinkedHashMap<String, Integer>();
    public Map<Integer, DimensionType> researchDimTypes = new LinkedHashMap<Integer, DimensionType>();

    public ResearchDimensions(){
        super(DATA_NAME);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList list = nbt.getTagList("researchDimensions", Constants.NBT.TAG_COMPOUND);

        for(int i = 0; i < list.tagCount(); i++){
            NBTTagCompound tag = list.getCompoundTagAt(i);
            String dimName = tag.getString("dimName");
            int dimID = tag.getInteger("dimID");

            researchDimIDs.put(dimName, dimID);

            DimensionType researchDim = DimensionType.register("research" + dimName, "", dimID, WorldProviderResearch.class, false);

            DimensionManager.registerDimension(dimID, researchDim);

            researchDimTypes.put(dimID, researchDim);
        }

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList dimensions = new NBTTagList();

        for(int i = 0; i < researchDimIDs.size(); i++){
            NBTTagCompound tag = new NBTTagCompound();
            String key = (new ArrayList<String>(researchDimIDs.keySet())).get(i);
            int dimID = (new ArrayList<Integer>(researchDimIDs.values())).get(i);
            tag.setString("dimName", key);
            tag.setInteger("dimID", dimID);
            dimensions.appendTag(tag);
        }

        compound.setTag("researchDimensions", dimensions);

        return compound;
    }

    public static ResearchDimensions get(World world){
        MapStorage storage = world.getMapStorage();

        ResearchDimensions instance = (ResearchDimensions) storage.getOrLoadData(ResearchDimensions.class, DATA_NAME);

        if(instance == null){
            instance = new ResearchDimensions();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

}
