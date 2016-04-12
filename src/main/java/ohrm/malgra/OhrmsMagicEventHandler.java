package ohrm.malgra;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohrm.malgra.capabilities.CapabilityMana;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.packets.client.SyncManaData;

public class OhrmsMagicEventHandler {

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
	}
	
	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		// If you have any non-DataWatcher fields in your extended properties that
		// need to be synced to the client, you must send a packet each time the
		// player joins the world; this takes care of dying, changing dimensions, etc.
		if (event.getEntity() instanceof EntityPlayerMP) {
			PacketDispatcher.sendTo(new SyncManaData(event.getEntity().getCapability(CapabilityMana.MANA, null)), (EntityPlayerMP) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			final CapabilityMana.IMana manaOriginal = event.getOriginal().getCapability(CapabilityMana.MANA, null);
			final CapabilityMana.IMana manaNew = event.getEntityPlayer().getCapability(CapabilityMana.MANA, null);
			manaNew.setMana(manaOriginal.getMana());
			manaNew.setMaxMana(manaOriginal.getMaxMana());
		}
	}
	
}
