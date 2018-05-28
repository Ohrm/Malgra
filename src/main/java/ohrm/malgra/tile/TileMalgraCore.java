package ohrm.malgra.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.api.network.*;
import ohrm.malgra.network.MalgraNetworkNodeGraph;
import org.apache.logging.log4j.Level;

import java.util.Map;

public class TileMalgraCore extends TileEntity implements ITickable, IMalgraNetwork, IMalgraLinkable {

    private int maxTransferRate;
    private boolean acceptBind;
    private IMalgraNetworkNodeGraph networkNodeGraph = new MalgraNetworkNodeGraph(this);

    public TileMalgraCore(){
        super();
    }

    public TileMalgraCore(World world, int maxTransferRate){
        acceptBind = true;
        this.maxTransferRate = maxTransferRate;
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void update() {
        if(!world.isRemote)
            MalgraMain.logger.log(Level.INFO, getPos() + " " + networkNodeGraph.getAllNodes().size());
    }

    @Override
    public int getMalgrumUsage() {
        return 0;
    }

    @Override
    public BlockPos getNetworkPosition() {
        return this.getPos();
    }

    @Override
    public boolean canAcceptBind() {
        return acceptBind;
    }

    @Override
    public void setAcceptBind(boolean accept) {
        this.acceptBind = accept;
    }

    @Override
    public IMalgraNetworkNodeGraph getNetworkNodeGraph() {
        return networkNodeGraph;
    }

    @Override
    public void addNodetoNetwork(IMalgraNetworkNode node) {
        if(node.getNetwork() != null && node.getNetwork() != this)
            node.getNetwork().removeNodeFromNetwork(node);
        networkNodeGraph.addNode(node);
        node.onConnected(this);
    }

    @Override
    public void removeNodeFromNetwork(IMalgraNetworkNode node) {
        networkNodeGraph.removeNode(node);
        node.onDisconnected(this);
    }

    @Override
    public void removeAllNodes() {
        networkNodeGraph.removeAllNodes();
    }

    @Override
    public void onLinkedDestination(IMalgraLinkable linkedTo) {
        if(linkedTo instanceof IMalgraNetworkNode){
            addNodetoNetwork((IMalgraNetworkNode)linkedTo);
        }
    }

    @Override
    public void onLinkedSource(IMalgraLinkable linkedTo) {
        if(linkedTo instanceof IMalgraNetworkNode){
            addNodetoNetwork((IMalgraNetworkNode)linkedTo);
        }
    }

    @Override
    public void getAttachedStorage(EntityPlayer player) {
        for(IMalgraNetworkNode node : networkNodeGraph.getAllNodes()){
            //TODO: Cache storages
            if(node instanceof IMalgraStorage){
                IMalgraStorage storage = (IMalgraStorage)node;
                MalgraMain.logger.log(Level.INFO, "Listing items for storage: " + ((IMalgraNetworkNode)storage).getPos());
                for(Map.Entry<Item, Integer> entry : storage.getInventory().entrySet()){
                    MalgraMain.logger.log(Level.INFO, entry.getKey() + ": " + entry.getValue());
                }
            }
        }
    }
}
