package ohrm.malgra.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.tile.TileEntityManaCraftingTable;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0){
			TileEntityManaCraftingTable te = (TileEntityManaCraftingTable)world.getTileEntity(new BlockPos(x, y, z));
			te.guiOpen = true;
			return new ContainerManaCraftingTable(player.inventory, te);
		
		}else{
			
			return null;
			
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0){
			TileEntityManaCraftingTable te = (TileEntityManaCraftingTable)world.getTileEntity(new BlockPos(x, y, z));
			return new GuiManaCraftingTable(player.inventory, te);
		
		}else{
			
			return null;
			
		}
	}

}
