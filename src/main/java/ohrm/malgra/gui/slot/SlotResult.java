package ohrm.malgra.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotResult extends Slot {

	public SlotResult(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		
		super(inventoryIn, index, xPosition, yPosition);
		
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
	
		return false;
		
	}

}
