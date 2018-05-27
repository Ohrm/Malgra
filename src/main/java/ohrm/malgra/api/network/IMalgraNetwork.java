package ohrm.malgra.api.network;

import net.minecraft.util.math.BlockPos;

public interface IMalgraNetwork {

    int getMalgrumUsage();

    BlockPos getNetworkPosition();

    boolean canAcceptBind();
    void setAcceptBind(boolean accept);

}
