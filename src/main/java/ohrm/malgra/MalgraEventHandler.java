package ohrm.malgra;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohrm.malgra.api.MalgraAPI;
import ohrm.malgra.capabilities.CapabilityMana;
import ohrm.malgra.capabilities.CapabilityResearchActivites;
import ohrm.malgra.capabilities.CapabilityResearchPoints;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.packets.client.SyncManaData;
import ohrm.malgra.packets.client.SyncResearchActivites;
import ohrm.malgra.packets.client.SyncResearchPoints;
import ohrm.malgra.world.Dimensions;
import ohrm.malgra.world.WorldProviderResearch;

public class MalgraEventHandler {

	@SubscribeEvent
	public void onEntityConstructing(AttachCapabilitiesEvent.Entity event){
		event.addCapability(new ResourceLocation(Reference.MODID, "IMana"), new ICapabilitySerializable<NBTTagCompound>() {
			CapabilityMana.IMana inst = CapabilityMana.MANA.getDefaultInstance();

			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				return capability == CapabilityMana.MANA;
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				return capability == CapabilityMana.MANA ? (T)inst : null;
			}

			@Override
			public NBTTagCompound serializeNBT() {
				return (NBTTagCompound) CapabilityMana.MANA.getStorage().writeNBT(CapabilityMana.MANA, inst, null);
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				CapabilityMana.MANA.getStorage().readNBT(CapabilityMana.MANA, inst, null, nbt);
			}
		});
		event.addCapability(new ResourceLocation(Reference.MODID, "IResearchPoints"), new ICapabilitySerializable<NBTTagCompound>() {
			CapabilityResearchPoints.IResearchPoints inst = CapabilityResearchPoints.RESEARCHPOINTS.getDefaultInstance();

			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				return capability == CapabilityResearchPoints.RESEARCHPOINTS;
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				return capability == CapabilityResearchPoints.RESEARCHPOINTS ? (T)inst : null;
			}

			@Override
			public NBTTagCompound serializeNBT() {
				return (NBTTagCompound) CapabilityResearchPoints.RESEARCHPOINTS.getStorage().writeNBT(CapabilityResearchPoints.RESEARCHPOINTS, inst, null);
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				CapabilityResearchPoints.RESEARCHPOINTS.getStorage().readNBT(CapabilityResearchPoints.RESEARCHPOINTS, inst, null, nbt);
			}
		});
		
		event.addCapability(new ResourceLocation(Reference.MODID, "IResearchActivities"), new ICapabilitySerializable<NBTTagCompound>() {
			CapabilityResearchActivites.IResearchActivities inst = CapabilityResearchActivites.RESEARCHACTIVITIES.getDefaultInstance();

			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				return capability == CapabilityResearchActivites.RESEARCHACTIVITIES;
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				return capability == CapabilityResearchActivites.RESEARCHACTIVITIES ? (T)inst : null;
			}

			@Override
			public NBTTagCompound serializeNBT() {
				return (NBTTagCompound) CapabilityResearchActivites.RESEARCHACTIVITIES.getStorage().writeNBT(CapabilityResearchActivites.RESEARCHACTIVITIES, inst, null);
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				CapabilityResearchActivites.RESEARCHACTIVITIES.getStorage().readNBT(CapabilityResearchActivites.RESEARCHACTIVITIES, inst, null, nbt);
			}
		});
	}
	
	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		// If you have any non-DataWatcher fields in your extended properties that
		// need to be synced to the client, you must send a packet each time the
		// player joins the world; this takes care of dying, changing dimensions, etc.
		if (event.getEntity() instanceof EntityPlayerMP) {
			PacketDispatcher.sendTo(new SyncManaData(event.getEntity().getCapability(CapabilityMana.MANA, null)), (EntityPlayerMP) event.getEntity());
			PacketDispatcher.sendTo(new SyncResearchPoints(event.getEntity().getCapability(CapabilityResearchPoints.RESEARCHPOINTS, null)), (EntityPlayerMP) event.getEntity());
			PacketDispatcher.sendTo(new SyncResearchActivites(event.getEntity().getCapability(CapabilityResearchActivites.RESEARCHACTIVITIES,  null)), (EntityPlayerMP) event.getEntity());

		}

		if(event.getEntity() instanceof EntityPlayer){

			if(Dimensions.researchDimIDs.get(event.getEntity().getName()) == null) {
				int researchDimID = DimensionManager.getNextFreeDimId();

				DimensionType researchDim = DimensionType.register("research" + event.getEntity().getName(), "", researchDimID, WorldProviderResearch.class, false);
				DimensionManager.registerDimension(researchDimID, researchDim);

				//Dimensions.researchDims.put(event.getEntity().getName(), researchDimID);
				Dimensions.addDim(event.getEntity().getName(), researchDimID);
				Dimensions.researchDimTypes.put(researchDimID, researchDim);
			}

		}

	}
	
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			final CapabilityMana.IMana manaOriginal = event.getOriginal().getCapability(CapabilityMana.MANA, null);
			final CapabilityMana.IMana manaNew = event.getEntityPlayer().getCapability(CapabilityMana.MANA, null);
			manaNew.setMana(manaOriginal.getMana());
			manaNew.setMaxMana(manaOriginal.getMaxMana());
			
			final CapabilityResearchPoints.IResearchPoints researchPointsOriginal = event.getOriginal().getCapability(CapabilityResearchPoints.RESEARCHPOINTS, null);
			final CapabilityResearchPoints.IResearchPoints researchPointsNew = event.getEntityPlayer().getCapability(CapabilityResearchPoints.RESEARCHPOINTS, null);
			researchPointsNew.setResearchPoints(researchPointsOriginal.getResearchPoints());
			
			final CapabilityResearchActivites.IResearchActivities researchActivitesOriginal = event.getOriginal().getCapability(CapabilityResearchActivites.RESEARCHACTIVITIES, null);
			final CapabilityResearchActivites.IResearchActivities researchActivitesNew = event.getEntityPlayer().getCapability(CapabilityResearchActivites.RESEARCHACTIVITIES, null);
			researchActivitesNew.setMinedBlocks(researchActivitesOriginal.getMinedBlocks());
		
		}
	}

}
