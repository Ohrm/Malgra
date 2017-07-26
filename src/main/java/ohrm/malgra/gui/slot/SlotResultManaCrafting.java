package ohrm.malgra.gui.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ohrm.malgra.tile.TileEntityManaCraftingTable;

public class SlotResultManaCrafting extends Slot {
	
	private TileEntityManaCraftingTable tileEntityManaCraftingTable;
	
	public SlotResultManaCrafting(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		
		super(inventoryIn, index, xPosition, yPosition);
		
		tileEntityManaCraftingTable = (TileEntityManaCraftingTable)inventoryIn;
		
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
	
		return false;
		
	}

	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {

		if ((!tileEntityManaCraftingTable.itemStacks[1].isEmpty()) && tileEntityManaCraftingTable.itemStacks[1].hasTagCompound()) {

			tileEntityManaCraftingTable.itemStacks[1].getTagCompound().setInteger("malgra", tileEntityManaCraftingTable.itemStacks[1].getTagCompound().getInteger("malgra") - tileEntityManaCraftingTable.GetRecipeMalgraCost());
		}

		for (int i = 2; i < tileEntityManaCraftingTable.itemStacks.length; i++) {

			tileEntityManaCraftingTable.decrStackSize(i, 1);

		}

		return super.onTake(thePlayer, stack);
	}
	
}
