package ohrm.malgra.crafting;

import net.minecraft.item.ItemStack;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.items.Items;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManaRecipes {

	public static List<ManaCraftingRecipe> recipesList = new ArrayList<ManaCraftingRecipe>();
	
	static{
		
		AddRecipe(new ItemStack(Items.magicDust), 0, new ItemStack(net.minecraft.init.Blocks.DIRT));
		AddRecipe(new ItemStack(Items.manaInjector), 1, new ItemStack(net.minecraft.init.Blocks.WOODEN_BUTTON));
		AddRecipe(new ItemStack(Items.dimensionTool), 5, new ItemStack(Items.magicDust), new ItemStack(Items.magicDust), new ItemStack(Items.magicDust), new ItemStack(net.minecraft.init.Blocks.COAL_BLOCK));
		AddRecipe(new ItemStack(Items.diamondExtractorTip), 0, "plankWood");

	}
	
	public static void AddRecipe(ItemStack output, int malgra, Object...itemStacks){

		try{

			ManaCraftingRecipe recipe = new ManaCraftingRecipe(malgra, output, itemStacks);

			recipesList.add(recipe);
			
		}catch(IllegalArgumentException e){
			
			MalgraMain.logger.log(Level.WARN, e.toString());
			
		}
	}
	
	public static ManaCraftingRecipe GetResult(int malgra, ItemStack...itemStacks){
		
		for (ManaCraftingRecipe recipe : recipesList) {
			if(recipe.matches(Arrays.asList(itemStacks))){
				
				if(malgra >= recipe.malgra){
					
					return recipe;
					
				}
				
			}
		}
		return null;
	}
}
