package ohrm.malgra.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.api.network.IMalgraNetwork;
import org.apache.logging.log4j.Level;

public class TileMalgraCore extends TileEntity implements ITickable, IMalgraNetwork {

    private int maxTransferRate;
    private boolean acceptBind;


    public TileMalgraCore(World world, int maxTransferRate){
        acceptBind = true;
        this.maxTransferRate = maxTransferRate;
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void update() {

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
}
