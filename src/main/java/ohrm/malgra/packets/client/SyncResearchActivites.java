package ohrm.malgra.packets.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import ohrm.malgra.capabilities.CapabilityResearchActivites;
import ohrm.malgra.packets.AbstractMessage.AbstractClientMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SyncResearchActivites extends AbstractClientMessage<SyncResearchActivites> {

	private List<String> minedBlocks = new ArrayList<String>();
	
	public SyncResearchActivites() {}
	
	public SyncResearchActivites(CapabilityResearchActivites.IResearchActivities iResearchActivities){
		
		this.minedBlocks = iResearchActivities.getMinedBlocks();
		
	}
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {

		int size = buffer.readInt();
		for(int i = 0; i < size; i++){
			
			this.minedBlocks.add(ByteBufUtils.readUTF8String(buffer));

		}
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		
		buffer.writeInt(minedBlocks.size());
		for(int i = 0; i < minedBlocks.size(); i++){
			ByteBufUtils.writeUTF8String(buffer, minedBlocks.get(i));
		}
		
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {

		final CapabilityResearchActivites.IResearchActivities iResearchActivities = player.getCapability(CapabilityResearchActivites.RESEARCHACTIVITIES, null);
		iResearchActivities.setMinedBlocks(this.minedBlocks);
	}

}
