package ohrm.malgra.api.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public interface IMalgraNetwork {

    int getMalgrumUsage();

    BlockPos getNetworkPosition();

    boolean canAcceptBind();

    void setAcceptBind(boolean accept);

    IMalgraNetworkNodeGraph getNetworkNodeGraph();

    void addNodetoNetwork(IMalgraNetworkNode node);

    void removeNodeFromNetwork(IMalgraNetworkNode node);

    void removeAllNodes();

    void getAttachedStorage(EntityPlayer player);

}
