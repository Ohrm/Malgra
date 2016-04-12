package ohrm.malgra;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohrm.malgra.ExtendedEntities.ExtendedPlayer;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.packets.client.SyncPlayerPropsMessage;

public class OhrmsMagicEventHandler {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event){
		
		if(event.entity instanceof EntityPlayer && ExtendedPlayer.Get((EntityPlayer)event.entity) == null){
			
			ExtendedPlayer.Register((EntityPlayer)event.entity);
			
		}
		
	}
	
	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		// If you have any non-DataWatcher fields in your extended properties that
		// need to be synced to the client, you must send a packet each time the
		// player joins the world; this takes care of dying, changing dimensions, etc.
		if (event.entity instanceof EntityPlayerMP) {
			PacketDispatcher.sendTo(new SyncPlayerPropsMessage((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
		}
	}
	
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		ExtendedPlayer.Get(event.entityPlayer).copy(ExtendedPlayer.Get(event.original));
	}
	
}
