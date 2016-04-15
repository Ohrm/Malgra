package ohrm.malgra.crafting;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ohrm.malgra.MalgraMain;

public class ManaRecipes {

	public static Map<ManaCraftingRecipe, Item> recipes = new HashMap<ManaCraftingRecipe, Item>();
	
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
