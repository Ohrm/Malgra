package ohrm.malgra.tile;

import com.google.common.collect.Sets;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ohrm.malgra.api.network.IMalgraLinkable;
import ohrm.malgra.api.network.IMalgraNetwork;
import ohrm.malgra.api.network.IMalgraNetworkNode;
import ohrm.malgra.api.network.IMalgraStorage;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TileMalgraStorage extends TileEntity implements IMalgraNetworkNode, IMalgraStorage, IMalgraLinkable {

    private int uniqueItemCount;
    private int totalItemCount;
    private int currentItemCount;
    private IMalgraNetwork network;
    private Map<Item, Integer> items = new HashMap<Item, Integer>();

    public TileMalgraStorage(){
        super();
    }

    public TileMalgraStorage(int itemCount, int totalItemCount){
        this.uniqueItemCount = itemCount;
        this.totalItemCount = totalItemCount;
        this.currentItemCount = 0;
        this.network = null;
    }

    @Override
    public void notifyRemove() {

    }

    @Override
    public boolean insertItems(Item item, int count) {
        if(items.size() == uniqueItemCount || currentItemCount + count > totalItemCount)
            return false;

        currentItemCount += count;

        if(items.containsKey(item)){
            items.put(item, items.get(item) + count);
        }else {
            items.put(item, count);
        }

        return true;

    }

    @Nullable
    @Override
    public ItemStack extractItems(Item item, int count) {
        if(items.containsKey(item)){
            int total = items.get(item);
            if(total <= count){
                items.remove(item);
                currentItemCount -= total;
                return new ItemStack(item, total);
            }else{
                currentItemCount -= count;
                items.put(item, total - count);
                return new ItemStack(item, count);
            }
        }
        return null;
    }

    @Override
    public int getTotalItemCount() {
        return totalItemCount;
    }

    @Override
    public int getUniqueItemCount() {
        return uniqueItemCount;
    }

    @Override
    public int getCurrentItemCount() {
        return currentItemCount;
    }

    @Override
    public Map<Item, Integer> getInventory() {
        return items;
    }

    @Nullable
    @Override
    public IMalgraNetwork getNetwork() {
        return network;
    }

    @Override
    public void onConnected(IMalgraNetwork network) {
        this.network = network;
    }

    @Override
    public void onDisconnected(IMalgraNetwork network) {

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
}
