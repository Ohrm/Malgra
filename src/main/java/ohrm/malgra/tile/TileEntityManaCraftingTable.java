package ohrm.malgra.tile;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class TileEntityManaCraftingTable extends TileEntity implements IInventory{

	final int NUM_SLOT = 11;
	private ItemStack[] itemStacks = new ItemStack[NUM_SLOT];
	
	public TileEntityManaCraftingTable(World world) {
		
		setWorldObj(world);
		
		
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "container.malgra.manaCraftingTable";
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return itemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return itemStacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemStackInSlot = getStackInSlot(index);
		if (itemStackInSlot == null) return null;

		ItemStack itemStackRemoved;
		if (itemStackInSlot.stackSize <= count) {
			itemStackRemoved = itemStackInSlot;
			setInventorySlotContents(index, null);
		} else {
			itemStackRemoved = itemStackInSlot.splitStack(count);
			if (itemStackInSlot.stackSize == 0) setInventorySlotContents(index, null);
		}
		markDirty();
		return itemStackRemoved;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack itemStack = getStackInSlot(index);
		if (itemStack != null) setInventorySlotContents(index, null);
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		itemStacks[index] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) stack.stackSize = getInventoryStackLimit();
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (this.worldObj.getTileEntity(this.pos) != this) return false;
		final double X_CENTRE_OFFSET = 0.5;
		final double Y_CENTRE_OFFSET = 0.5;
		final double Z_CENTRE_OFFSET = 0.5;
		final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
		return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
	}	

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		Arrays.fill(itemStacks, null);
		
	}	
	
}
