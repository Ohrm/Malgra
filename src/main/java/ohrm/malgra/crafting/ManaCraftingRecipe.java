package ohrm.malgra.crafting;

import net.minecraft.item.ItemStack;
import ohrm.malgra.items.Items;

public class ManaCraftingRecipe {

	public int malgra;
	private ItemStack[] stacks;
	private ItemStack output;

	public ManaCraftingRecipe(int malgra, ItemStack output, ItemStack...itemStacks) {

		this.malgra = malgra;

		int count = 0;
		for (ItemStack itemStack : itemStacks) {
			if(!itemStack.isEmpty()){

				count++;

			}
		}

		if(count == 0){

			throw new IllegalArgumentException("Item stacks are all null. Recipe will not be added.");

		}

		stacks = new ItemStack[count];

		int index = 0;
		for (ItemStack itemStack : itemStacks) {
			if(!itemStack.isEmpty()){

				stacks[index++] = itemStack;

			}
		}

		this.output = output;

	}

	public ItemStack[] getInput(){
		return stacks;
	}

	public ItemStack getOutput(){
		return output;
	}

	public boolean IsSame(ItemStack...itemStacks){

		int count = 0;
		for (ItemStack itemStack : itemStacks) {
			if(!itemStack.isEmpty()){

				count++;

			}
		}

		ItemStack[] temp = new ItemStack[count];

		int index = 0;
		for (ItemStack itemStack : itemStacks) {
			if(!itemStack.isEmpty()){

				temp[index++] = itemStack;

			}
		}

		if(temp.length != stacks.length){

			return false;

		}

		int same = 0;
		for(int i = 0; i < this.stacks.length; i++){

			if(stacks[i].getItem() == temp[i].getItem()){

				same++;

			}

		}

		if(same == stacks.length && same == temp.length){

			return true;

		}else{

			return false;

		}
	}

}
