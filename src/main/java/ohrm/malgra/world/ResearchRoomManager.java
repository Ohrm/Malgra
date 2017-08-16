package ohrm.malgra.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;
import ohrm.malgra.Reference;
import ohrm.malgra.blocks.Blocks;
import ohrm.malgra.registries.MalgraRegistryManager;

import java.util.*;


public class ResearchRoomManager extends WorldSavedData {

    private static final String DATA_NAME = Reference.MODID + "MalgraResearchRooms";

    public Map<ResourceLocation, BlockPos> researches = new LinkedHashMap<ResourceLocation, BlockPos>();

    public ResearchRoomManager(){super(DATA_NAME);}

    public ResearchRoomManager(String s){super(s);}

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList tagList = nbt.getTagList("researches", Constants.NBT.TAG_COMPOUND);

        for(int i = 0; i < tagList.tagCount(); i++){
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            ResourceLocation research = new ResourceLocation(tag.getString("research"));
            int x = tag.getInteger("x");
            int y = tag.getInteger("y");
            int z = tag.getInteger("z");
            BlockPos location = new BlockPos(x, y, z);
            researches.put(research, location);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList tagList = new NBTTagList();

        for(int i = 0; i < researches.size(); i++){
            NBTTagCompound tag = new NBTTagCompound();
            ResourceLocation research = (new ArrayList<ResourceLocation>(researches.keySet())).get(i);
            BlockPos location = (new ArrayList<BlockPos>(researches.values())).get(i);
            tag.setString("research", research.toString());
            tag.setInteger("x", location.getX());
            tag.setInteger("y", location.getY());
            tag.setInteger("z", location.getZ());
            tagList.appendTag(tag);
        }

        compound.setTag("researches", tagList);
        return compound;
    }

    public void checkAndUpdateRooms(World world){
        Set<ResourceLocation> allResearches = MalgraRegistryManager.researchRegistry.getKeys();
        if(allResearches.size() != researches.size()){
            if(allResearches.size() > researches.size()){
                //Need to add extra rooms
                Set<ResourceLocation> currentResearches = researches.keySet();
                ArrayList<ResourceLocation> allTemp = new ArrayList<ResourceLocation>(allResearches);
                allTemp.removeAll(currentResearches);
                for(ResourceLocation newResearches : allTemp)
                    createRoom(newResearches, world);
            }
        }
    }

    public void createRoom(ResourceLocation research, World world){
        BlockPos roomLocation = new BlockPos(48, 64, 0);
        Collection<BlockPos> currentLocations = researches.values();
        for(BlockPos location : currentLocations){
            if(roomLocation.equals(location))
                roomLocation = roomLocation.add(32, 0, 0);
        }
        researches.put(research, roomLocation);

        for(int x = 0; x < 16; x++){
            for(int z = 0; z < 16; z++){
                world.setBlockState(roomLocation.add(x, 0, z), Blocks.researchStoneBrick.getDefaultState());
                if(x == 0 || x == 15 || z == 0 || z == 15) {
                    world.setBlockState(roomLocation.add(x, 1, z), net.minecraft.init.Blocks.BARRIER.getDefaultState());
                    world.setBlockState(roomLocation.add(x, 2, z), net.minecraft.init.Blocks.BARRIER.getDefaultState());
                }
                if(x == 1 || x == 14 || z == 1 || z == 14){
                    world.setBlockState(roomLocation.add(x, 1, z), net.minecraft.init.Blocks.TORCH.getDefaultState());
                }
                world.setBlockState(roomLocation.add(x, 3, z), net.minecraft.init.Blocks.BARRIER.getDefaultState());
            }
        }
    }

    public static ResearchRoomManager get(World world) {
        MapStorage storage = world.getPerWorldStorage();
        ResearchRoomManager instance = (ResearchRoomManager) storage.getOrLoadData(ResearchRoomManager.class, DATA_NAME);

        if (instance == null) {
            instance = new ResearchRoomManager();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

}
