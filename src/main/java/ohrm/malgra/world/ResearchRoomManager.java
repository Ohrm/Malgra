package ohrm.malgra.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import ohrm.malgra.Reference;
import ohrm.malgra.api.research.Research;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResearchRoomManager extends WorldSavedData {

    private static final String DATA_NAME = Reference.MODID + "MalgraResearchRooms";

    public Map<BlockPos, Research> researches = new LinkedHashMap<BlockPos, Research>();

    public ResearchRoomManager(){super(DATA_NAME);}

    public ResearchRoomManager(String s){super(s);}

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return null;
    }

    public void checkAndUpdateRooms(World world){

    }

    public static ResearchRoomManager get(World world) {
        // The IS_GLOBAL constant is there for clarity, and should be simplified into the right branch.
        MapStorage storage = world.getPerWorldStorage();
        ResearchRoomManager instance = (ResearchRoomManager) storage.getOrLoadData(ResearchRoomManager.class, DATA_NAME);

        if (instance == null) {
            instance = new ResearchRoomManager();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

}
