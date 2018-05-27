package ohrm.malgra.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ohrm.malgra.tile.TileMalgraStorage;

import javax.annotation.Nullable;

public class MalgraStorage extends Block implements ITileEntityProvider {

    public MalgraStorage(){
        super(Material.WOOD);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileMalgraStorage(64, 1024);
    }
}
