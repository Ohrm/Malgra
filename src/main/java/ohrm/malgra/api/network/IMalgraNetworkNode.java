package ohrm.malgra.api.network;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface IMalgraNetworkNode {

    void onConnected(IMalgraNetwork network);

    void onDisconnected(IMalgraNetwork network);

    void notifyRemove();

    BlockPos getPos();

    World getWorld();

    @Nullable
    IMalgraNetwork getNetwork();

}
