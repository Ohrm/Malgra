package ohrm.malgra.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ohrm.malgra.api.network.IMalgraNetworkNode;
import ohrm.malgra.api.network.IMalgraStorageViewer;
import ohrm.malgra.tile.TileMalgraStorageViewer;

import javax.annotation.Nullable;

public class MalgraStorageViewer extends Block implements ITileEntityProvider {

    public MalgraStorageViewer(){
        super(Material.WOOD);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileMalgraStorageViewer();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IMalgraStorageViewer tile = (IMalgraStorageViewer) worldIn.getTileEntity(pos);
        if(((IMalgraNetworkNode)tile).getNetwork() != null) {
            tile.listItems(playerIn);
            return true;
        }else{
            return false;
        }

    }
}
