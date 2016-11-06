package ohrm.malgra.crafting;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.items.Items;

public class ManaRecipes {

	public static Map<ManaCraftingRecipe, Item> recipes = new HashMap<ManaCraftingRecipe, Item>();
	
	static{
		
		AddRecipe(Items.magicDust, 0, new ItemStack(net.minecraft.init.Blocks.DIRT));
		AddRecipe(Items.manaInjector, 1, new ItemStack(net.minecraft.init.Blocks.WOODEN_BUTTON));

	}
	
	public static void AddRecipe(Item output, int malgra, ItemStack...itemStacks){

		try{
			
			recipes.put(new ManaCraftingRecipe(malgra, itemStacks), output);
			
		}catch(IllegalArgumentException e){
			
			MalgraMain.logger.log(Level.WARN, e.toString());
			
		}
	}
	
	public static ManaCraftingRecipe GetResult(int malgra, ItemStack...itemStacks){
		
		for (ManaCraftingRecipe recipe : recipes.keySet()) {
			if(recipe.IsSame(itemStacks)){
				
				if(malgra >= recipe.malgra){
					
					return recipe;
					
				}
				
			}
		}
		return null;
	}
}
