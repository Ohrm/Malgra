package ohrm.malgra;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohrm.malgra.capabilities.CapabilityMana;
import ohrm.malgra.capabilities.CapabilityResearchActivites;
import ohrm.malgra.capabilities.CapabilityResearchPoints;
import ohrm.malgra.entities.EntityItemMalgraTool;
import ohrm.malgra.items.Items;
import ohrm.malgra.items.MalgraTool;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.packets.client.SyncManaData;
import ohrm.malgra.packets.client.SyncResearchDimensions;
import ohrm.malgra.packets.client.SyncResearchActivites;
import ohrm.malgra.packets.client.SyncResearchPoints;
import ohrm.malgra.world.Dimensions;
import ohrm.malgra.world.WorldProviderResearch;

import java.util.Iterator;
import java.util.Map;

import static ohrm.malgra.world.Dimensions.addDim;
import static ohrm.malgra.world.Dimensions.researchDimIDs;

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
	public void onToss(ItemTossEvent event) {

	}
	
	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityItem && !(event.getEntity() instanceof EntityItemMalgraTool)) {
			EntityItem entityItem = (EntityItem) event.getEntity();
			if (entityItem.getEntityItem().getItem() == net.minecraft.init.Items.DIAMOND_PICKAXE) {
				event.setCanceled(true);
				ItemStack malgraPickaxe = new ItemStack(Items.malgraPickaxe);
				malgraPickaxe.setTagCompound(new NBTTagCompound());
				malgraPickaxe.getTagCompound().setInteger("malgra", ((MalgraTool)malgraPickaxe.getItem()).getMaxMalgra());

				EntityItemMalgraTool newEntity = new EntityItemMalgraTool(event.getWorld(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, ((EntityItem) event.getEntity()).getEntityItem(), malgraPickaxe);
				event.getWorld().spawnEntityInWorld(newEntity);
				NBTTagCompound nbt = new NBTTagCompound();
				event.getEntity().writeToNBTOptional(nbt);
				newEntity.readFromNBT(nbt);
			}
		}
		if (event.getEntity() instanceof EntityPlayerMP) {
			PacketDispatcher.sendTo(new SyncManaData(event.getEntity().getCapability(CapabilityMana.MANA, null)), (EntityPlayerMP) event.getEntity());
			PacketDispatcher.sendTo(new SyncResearchPoints(event.getEntity().getCapability(CapabilityResearchPoints.RESEARCHPOINTS, null)), (EntityPlayerMP) event.getEntity());
			PacketDispatcher.sendTo(new SyncResearchActivites(event.getEntity().getCapability(CapabilityResearchActivites.RESEARCHACTIVITIES,  null)), (EntityPlayerMP) event.getEntity());

            if(researchDimIDs.get(event.getEntity().getName()) == null) {
                int researchDimID = DimensionManager.getNextFreeDimId();

                DimensionType researchDim = DimensionType.register("research" + event.getEntity().getName(), "", researchDimID, WorldProviderResearch.class, false);
                DimensionManager.registerDimension(researchDimID, researchDim);

                //Dimensions.researchDims.put(event.getEntity().getName(), researchDimID);
                addDim(event.getEntity().getName(), researchDimID);
                Dimensions.researchDimTypes.put(researchDimID, researchDim);

                Iterator it = Dimensions.researchDimIDs.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    PacketDispatcher.sendTo(new SyncResearchDimensions((String)pair.getKey(), (Integer)pair.getValue()), (EntityPlayerMP) event.getEntity());
                }

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
