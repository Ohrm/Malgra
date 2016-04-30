package ohrm.malgra.capabilities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import scala.language;

public class CapabilityResearchActivites {
	
	@CapabilityInject(IResearchActivities.class)
	public static final Capability<IResearchActivities> RESEARCHACTIVITIES = null;
		
	public static void register(){
		
		CapabilityManager.INSTANCE.register(IResearchActivities.class, new CapabilityResearchActivites.Storage(), CapabilityResearchActivites.Default.class);
		
	}
	
	public interface IResearchActivities{
	
		void addMinedBlock(String string);
		
		boolean hasMinedBlock(String string);
		
		List<String> getMinedBlocks();
		void setMinedBlocks(List<String> strings);
		
	}
	
	public static class Storage implements Capability.IStorage<IResearchActivities>{

		@Override
		public NBTBase writeNBT(Capability<IResearchActivities> capability, IResearchActivities instance, EnumFacing side) {
			
			List<String> temp = instance.getMinedBlocks();
			NBTTagList tagList = new NBTTagList();
			
			for(int i = 0; i < temp.size(); i++){
				
				String string = temp.get(i);
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("MinedBlocks" + i, string);
				tagList.appendTag(tag);
				
			}
			NBTTagCompound tagCompound = new NBTTagCompound();
			tagCompound.setTag("MinedBlocks", tagList);
			return tagCompound;
			
		}

		@Override
		public void readNBT(Capability<IResearchActivities> capability, IResearchActivities instance, EnumFacing side, NBTBase nbt) {
			
			NBTTagCompound compound = (NBTTagCompound)nbt;
			NBTTagList tagList = (NBTTagList) compound.getTag("MinedBlocks");
			
			for(int i = 0; i < tagList.tagCount(); i++){
				
				NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
				String string = tagCompound.getString("MinedBlocks" + i);
				instance.addMinedBlock(string);
			}
			
		}
		
	}
	
	public static class Default implements IResearchActivities{

		List<String> minedBlocks = new ArrayList<String>();
		
		@Override
		public void addMinedBlock(String string) {

			minedBlocks.add(string);
			
		}

		@Override
		public boolean hasMinedBlock(String string) {
			
			for(int i = 0; i < minedBlocks.size(); i++){
				
				if(minedBlocks.get(i).equals(string))
					return true;
				
			}
			return false;
		}

		@Override
		public List<String> getMinedBlocks() {
			
			return minedBlocks;
			
		}

		@Override
		public void setMinedBlocks(List<String> strings) {
			
			minedBlocks = strings;
			
		}
		
	}
	
}
