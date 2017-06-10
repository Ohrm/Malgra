package ohrm.malgra.crafting;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class ManaCraftingRecipe {

	public int malgra;
	private ImmutableList<Object> inputs;
	private ItemStack output;

	public ManaCraftingRecipe(int malgra, ItemStack output, Object...inputs) {

		this.malgra = malgra;
		this.output = output;

		ImmutableList.Builder<Object> builder = ImmutableList.builder();
		for (Object input : inputs) {
			if (input instanceof String || input instanceof ItemStack)
				builder.add(input);
			else
				throw new IllegalArgumentException("Invalid input");
		}

		this.inputs = builder.build();

	}

	public List<Object> getInputs(){
		return new ArrayList<Object>(inputs);
	}

	public ItemStack getOutput(){
		return output.copy();
	}

	@Override
	public int hashCode(){
		return 37 * malgra ^ inputs.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof ManaCraftingRecipe && malgra == ((ManaCraftingRecipe) obj).malgra && inputs.equals(((ManaCraftingRecipe)obj).inputs);
	}

	public boolean matches(List<ItemStack> in){

		List<Object> missingInputs = new ArrayList<Object>(inputs);

		for (ItemStack input: in) {
			if(input.isEmpty())
				continue;

			int slotIndex = -1, oredicIndex = -1;

			for(int i = 0; i < missingInputs.size(); i++){
				Object missing = missingInputs.get(i);
				if(missing instanceof String){
					List<ItemStack> validInputs = OreDictionary.getOres((String)missing);
					boolean found = false;
					for(ItemStack valid : validInputs){

						ItemStack val = valid.copy();

						if(val.getItemDamage() == Short.MAX_VALUE)
							val.setItemDamage(input.getItemDamage());

						if(input.isItemEqual(val)){
							oredicIndex = i;
							found = true;
							break;
						}

					}
					if(found)
						break;
				}else if(missing instanceof ItemStack && areStacksEqual((ItemStack)missing, input)){
					slotIndex = i;
				}
			}

			if(slotIndex != -1)
				missingInputs.remove(slotIndex);
			else if(oredicIndex != -1)
				missingInputs.remove(oredicIndex);
		}

		return missingInputs.isEmpty();

	}

	private boolean areStacksEqual(ItemStack stack, ItemStack stack2){
		return stack.getItem() == stack2.getItem() && stack.getItemDamage() == stack2.getItemDamage();
	}

}
