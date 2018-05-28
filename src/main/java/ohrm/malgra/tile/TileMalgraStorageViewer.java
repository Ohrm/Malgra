package ohrm.malgra.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import ohrm.malgra.api.network.IMalgraLinkable;
import ohrm.malgra.api.network.IMalgraNetwork;
import ohrm.malgra.api.network.IMalgraNetworkNode;
import ohrm.malgra.api.network.IMalgraStorageViewer;

import javax.annotation.Nullable;

public class TileMalgraStorageViewer extends TileEntity implements IMalgraLinkable, IMalgraNetworkNode, IMalgraStorageViewer {

    private IMalgraNetwork network;

    public TileMalgraStorageViewer(){
        super();
    }

    @Override
    public void onLinkedSource(IMalgraLinkable linkedTo) {
        if(linkedTo instanceof IMalgraNetwork){
            ((IMalgraNetwork) linkedTo).addNodetoNetwork(this);
        }else if(linkedTo instanceof  IMalgraNetworkNode){
            IMalgraNetworkNode node = (IMalgraNetworkNode)linkedTo;
            if(node.getNetwork() != null){
                node.getNetwork().addNodetoNetwork(this);
            }
        }
    }

    @Override
    public void onLinkedDestination(IMalgraLinkable linkedTo) {
        if(linkedTo instanceof IMalgraNetwork){
            ((IMalgraNetwork) linkedTo).addNodetoNetwork(this);
        }else if(linkedTo instanceof  IMalgraNetworkNode){
            IMalgraNetworkNode node = (IMalgraNetworkNode)linkedTo;
            if(node.getNetwork() != null){
                node.getNetwork().addNodetoNetwork(this);
            }
        }
    }

    @Override
    public void onConnected(IMalgraNetwork network) {
        this.network = network;
    }

    @Override
    public void onDisconnected(IMalgraNetwork network) {

    }

    @Override
    public void notifyRemove() {

    }

    @Nullable
    @Override
    public IMalgraNetwork getNetwork() {
        return network;
    }

    @Override
    public void listItems(EntityPlayer player) {
        if(network == null){
            return;
        }
        network.getAttachedStorage(player);
    }
}
