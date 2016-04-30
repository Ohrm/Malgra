package ohrm.malgra.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityResearchPoints {

	@CapabilityInject(IResearchPoints.class)
	public static final Capability<IResearchPoints> RESEARCHPOINTS = null;
	
	public static void register(){
		
		CapabilityManager.INSTANCE.register(IResearchPoints.class, new CapabilityResearchPoints.Storage(), CapabilityResearchPoints.Default.class);
		
	}
	
	public interface IResearchPoints{
		
		int getResearchPoints();
		
		void addResearchPoints(int amount);
		void removeResearchPoints(int amount);
		void setResearchPoints(int amount);
		
	}
	
	public static class Storage implements Capability.IStorage<IResearchPoints>{

		@Override
		public NBTBase writeNBT(Capability<IResearchPoints> capability, IResearchPoints instance, EnumFacing side) {
			
			NBTTagCompound data = new NBTTagCompound();
            data.setInteger("researchPoints", instance.getResearchPoints());
            return data;
			
		}

		@Override
		public void readNBT(Capability<IResearchPoints> capability, IResearchPoints instance, EnumFacing side, NBTBase nbt) {
			
			 NBTTagCompound data = (NBTTagCompound) nbt;
	         instance.setResearchPoints(data.getInteger("researchPoints"));
			
		}
		
		
		
	}
	
	public static class Default implements IResearchPoints{

		private int researchPoints;
		
		@Override
		public int getResearchPoints() {
			return researchPoints;
		}

		@Override
		public void addResearchPoints(int amount) {
			
			researchPoints += amount;
			
		}

		@Override
		public void removeResearchPoints(int amount) {

			researchPoints -= amount;
			
		}

		@Override
		public void setResearchPoints(int amount) {

			researchPoints = amount;
			
		}
		
		
		
	}
	
}
