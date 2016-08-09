package ohrm.malgra.packets.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import ohrm.malgra.packets.AbstractMessage.AbstractClientMessage;
import ohrm.malgra.world.Dimensions;

import java.io.IOException;

/**
 * Created by Chris on 09/08/2016.
 */
public class SyncReasearchDimensions extends AbstractClientMessage<SyncReasearchDimensions>{

    private String username;
    private int dimID;

    public SyncReasearchDimensions(){}

    public SyncReasearchDimensions(String username, int dimID){

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
    public void process(EntityPlayer player, Side side) {

        Dimensions.addDim(this.username, this.dimID);

    }
}
