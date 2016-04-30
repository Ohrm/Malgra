package ohrm.malgra.packets.client;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import ohrm.malgra.capabilities.CapabilityResearchPoints;
import ohrm.malgra.packets.AbstractMessage.AbstractClientMessage;

public class SyncResearchPoints extends AbstractClientMessage<SyncResearchPoints>{

	private int researchPoints;
	
	public SyncResearchPoints(){}
	
	public SyncResearchPoints(CapabilityResearchPoints.IResearchPoints researchPoints){
		
		this.researchPoints = researchPoints.getResearchPoints();
		
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		
		this.researchPoints = buffer.readInt();
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		
		buffer.writeInt(this.researchPoints);
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		
		final CapabilityResearchPoints.IResearchPoints research = player.getCapability(CapabilityResearchPoints.RESEARCHPOINTS, null);
		research.setResearchPoints(this.researchPoints);
		
	}

	
	
}
