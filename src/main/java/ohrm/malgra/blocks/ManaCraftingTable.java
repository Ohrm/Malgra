package ohrm.malgra.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.tile.TileEntityManaCraftingTable;

public class ManaCraftingTable extends Block implements ITileEntityProvider {

	public ManaCraftingTable() {
		super(Material.WOOD);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		
		return new TileEntityManaCraftingTable(world);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote || worldIn.getTileEntity(pos) == null){
			
			if(!playerIn.isSneaking()){
				
				playerIn.openGui(MalgraMain.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
				
			}
			return false;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityManaCraftingTable(worldIn);
	}
}
