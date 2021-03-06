package ohrm.malgra.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import ohrm.malgra.crafting.ManaCraftingRecipe;
import ohrm.malgra.crafting.ManaRecipes;

import java.util.Arrays;

public class TileEntityManaCraftingTable extends TileEntity implements IInventory, ITickable{

	final int NUM_SLOT = 11;
	public ItemStack[] itemStacks = new ItemStack[NUM_SLOT];
	public boolean guiOpen = false;
	public ManaCraftingRecipe recipe;
	
	public TileEntityManaCraftingTable() {
		
	}
	
	public TileEntityManaCraftingTable(World world) {
		
		setWorld(world);

		for(int i = 0; i < NUM_SLOT; i++)
			itemStacks[i] = ItemStack.EMPTY;

	}

	@Override
	public boolean isEmpty() {
		//TODO: this as well
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		
		super.readFromNBT(tag);
		
		if(tag.hasKey("Items")) {
			NBTTagList tagList = tag.getTagList("Items", 10);
			for(int i = 0; i < tagList.tagCount(); i++) {
				NBTTagCompound itemTag = (NBTTagCompound)tagList.getCompoundTagAt(i);
				int slot = itemTag.getByte("Slot") & 0xff;
				if(slot >= 0 && slot <= itemStacks.length) {
					ItemStack itemStack = new ItemStack(itemTag);
					itemStacks[slot] = itemStack;
				}
			}
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		
		super.writeToNBT(tag);
		// Inventories
		NBTTagList tagList = new NBTTagList();		
		for(int i = 0; i < itemStacks.length; i++) {

				NBTTagCompound itemTag = new NBTTagCompound();
				itemTag.setByte("Slot", (byte)i);
				itemStacks[i].writeToNBT(itemTag);
				tagList.appendTag(itemTag);

		}
				
		if(tagList.tagCount() > 0) {
			tag.setTag("Items", tagList);
		}
		return tag;
	}
	
	@Override
	public String getName() {
	
		return "container.malgra.manaCraftingTable";
	}

	@Override
	public boolean hasCustomName() {
		
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
	
		return null;
	}

	@Override
	public int getSizeInventory() {
		
		return itemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		
		return itemStacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemStackInSlot = getStackInSlot(index);
		if (itemStackInSlot.isEmpty()) return ItemStack.EMPTY;

		ItemStack itemStackRemoved;
		if (itemStackInSlot.getCount() <= count) {
			itemStackRemoved = itemStackInSlot;
			setInventorySlotContents(index, ItemStack.EMPTY);
		} else {
			itemStackRemoved = itemStackInSlot.splitStack(count);
			if (itemStackInSlot.getCount() == 0) setInventorySlotContents(index, ItemStack.EMPTY);
		}
		markDirty();
		return itemStackRemoved;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack itemStack = getStackInSlot(index);
		if (!itemStack.isEmpty()) setInventorySlotContents(index, ItemStack.EMPTY);
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		itemStacks[index] = stack;
		if ((!stack.isEmpty()) && stack.getCount() > getInventoryStackLimit()) stack.setCount(getInventoryStackLimit());
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
	
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (this.world.getTileEntity(this.pos) != this) return false;
		final double X_CENTRE_OFFSET = 0.5;
		final double Y_CENTRE_OFFSET = 0.5;
		final double Z_CENTRE_OFFSET = 0.5;
		final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
		return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
	}	

	@Override
	public void openInventory(EntityPlayer player) {
		
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		
		return true;
	}

	@Override
	public int getField(int id) {
		
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
		
	}

	@Override
	public int getFieldCount() {
		
		return 0;
	}

	@Override
	public void clear() {
		Arrays.fill(itemStacks, ItemStack.EMPTY);
		
	}	
	
	@Override
	public void update() {
		
		if(guiOpen && !world.isRemote){
		
			checkForResult();
			
		}
		
	}
	
	public int GetRecipeMalgraCost(){
		
		ItemStack[] temp = new ItemStack[9];
		
		for(int i = 0; i < temp.length; i++){
			
			temp[i] = itemStacks[i+2];
			
		}
				
		recipe = ManaRecipes.GetResult(itemStacks[1].getTagCompound().getInteger("malgra"), temp);
		return recipe.malgra;
		
		
	}
	
	public void checkForResult(){
		
		ItemStack[] temp = new ItemStack[9];

		for(int i = 0; i < temp.length; i++){
			temp[i] = ItemStack.EMPTY;
			if(itemStacks[i+2] != ItemStack.EMPTY)
				temp[i] = itemStacks[i+2];
			
		}
		
		if(temp.length == 0){
			itemStacks[0] = ItemStack.EMPTY;
			return;
		}
		
		if((!itemStacks[1].isEmpty()) && itemStacks[1].hasTagCompound()){
			
			recipe = ManaRecipes.GetResult(itemStacks[1].getTagCompound().getInteger("malgra"), temp);
			if(recipe != null) {
				ItemStack stack = recipe.getOutput();
				if (stack.isEmpty()) {

					this.itemStacks[0] = ItemStack.EMPTY;

				} else {

					this.itemStacks[0] = stack;
					//markDirty();
				}
				return;
			}
		}
		
		recipe = ManaRecipes.GetResult(0, temp);
		if(recipe != null) {
			ItemStack stack = recipe.getOutput();

			if (stack.isEmpty()) {

				this.itemStacks[0] = ItemStack.EMPTY;

			} else {
				this.itemStacks[0] = stack;
				//markDirty();
			}
			return;
		}

		this.itemStacks[0] = ItemStack.EMPTY;

	}

}
