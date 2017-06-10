package ohrm.malgra.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.items.Items;
import ohrm.malgra.blocks.Blocks;

public class ManaRecipes {

	public static List<ManaCraftingRecipe> recipesList = new ArrayList<ManaCraftingRecipe>();
	
	static{
		
		AddRecipe(new ItemStack(Items.magicDust), 0, new ItemStack(net.minecraft.init.Blocks.DIRT));
		AddRecipe(new ItemStack(Items.manaInjector), 1, new ItemStack(net.minecraft.init.Blocks.WOODEN_BUTTON));
		AddRecipe(new ItemStack(Items.dimensionTool), 5, new ItemStack(Items.magicDust), new ItemStack(Items.magicDust), new ItemStack(Items.magicDust), new ItemStack(net.minecraft.init.Blocks.COAL_BLOCK));

	}
	
	public static void AddRecipe(ItemStack output, int malgra, ItemStack...itemStacks){

		try{

			ManaCraftingRecipe recipe = new ManaCraftingRecipe(malgra, output, itemStacks);

			recipesList.add(recipe);
			
		}catch(IllegalArgumentException e){
			
			MalgraMain.logger.log(Level.WARN, e.toString());
			
		}
	}
	
	public static ManaCraftingRecipe GetResult(int malgra, ItemStack...itemStacks){
		
		for (ManaCraftingRecipe recipe : recipesList) {
			if(recipe.IsSame(itemStacks)){
				
				if(malgra >= recipe.malgra){
					
					return recipe;
					
				}
				
			}
		}
		return null;
	}
}
