package ohrm.malgra.packets.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import ohrm.malgra.packets.AbstractMessage.AbstractClientMessage;
import ohrm.malgra.world.ResearchDimensions;
import ohrm.malgra.world.WorldProviderResearch;

import java.io.IOException;

/**
 * Created by Chris on 09/08/2016.
 */
public class SyncResearchDimensions extends AbstractClientMessage<SyncResearchDimensions>{

    private String username;
    private int dimID;

    public SyncResearchDimensions(){}

    public SyncResearchDimensions(String username, int dimID){

        this.username = username;
        this.dimID = dimID;

    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        this.username = ByteBufUtils.readUTF8String(buffer);
        this.dimID = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        ByteBufUtils.writeUTF8String(buffer, this.username);
        buffer.writeInt(this.dimID);
    }

    @Override
    public void process(EntityPlayer player, Side side)
    {
        if(ResearchDimensions.get(player.world).researchDimIDs.get(this.username) == null) {
            DimensionType researchDim = DimensionType.register("research" + this.username, "", this.dimID, WorldProviderResearch.class, false);

            DimensionManager.registerDimension(this.dimID, researchDim);

            ResearchDimensions.get(player.world).researchDimIDs.put(this.username, this.dimID);
            ResearchDimensions.get(player.world).researchDimTypes.put(this.dimID, researchDim);
        }else{

            ResearchDimensions.get(player.world).researchDimIDs.put(this.username, this.dimID);
            ResearchDimensions.get(player.world).researchDimTypes.put(this.dimID, DimensionType.getById(this.dimID));

        }

    }
}
