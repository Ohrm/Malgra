package ohrm.malgra.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ohrm.malgra.items.Items;

public class SlotExtractor extends Slot {

	public SlotExtractor(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		
		super(inventoryIn, index, xPosition, yPosition);
		
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {

		if(stack.getItem() == Items.extractor){
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}

}
