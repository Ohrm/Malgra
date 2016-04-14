package ohrm.malgra.gui;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ohrm.malgra.tile.TileEntityManaCraftingTable;
import ohrm.malgra.blocks.Blocks;
import ohrm.malgra.gui.slot.SlotExtractor;
import ohrm.malgra.gui.slot.SlotResult;

public class ContainerManaCraftingTable extends Container{

	private TileEntityManaCraftingTable tileEntity;
	private World world;
	private BlockPos pos;
	
	private final int HOTBAR_SLOT_COUNT = 9;
	private final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

	private final int VANILLA_FIRST_SLOT_INDEX = 0;
	private final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
	private final int TE_INVENTORY_SLOT_COUNT = 11;
	
	public ContainerManaCraftingTable(InventoryPlayer player, TileEntityManaCraftingTable tileEntity) {
		this.tileEntity = tileEntity;
		this.world = tileEntity.getWorld();
		this.pos = tileEntity.getPos();
		
		this.addSlotToContainer(new SlotResult(tileEntity, 0, 124, 35));
		this.addSlotToContainer(new SlotExtractor(tileEntity, 1, 8, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(tileEntity, (j + i * 3) + 2, 30 + j * 18, 17 + i * 18));
            }
        }

        //Player inventory
        for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(player, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        //Player hotbar
        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(player, l, 8 + l * 18, 142));
        }

	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		
		tileEntity.guiOpen = false;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		
		return this.world.getBlockState(this.pos).getBlock() != Blocks.manaCraftingTable ? false : playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
		
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index >= 10 && index < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (index >= 37 && index < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return slotIn.inventory != tileEntity && super.canMergeSlot(stack, slotIn);
    }

}
