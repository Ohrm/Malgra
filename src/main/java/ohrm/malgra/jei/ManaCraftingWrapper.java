package ohrm.malgra.jei;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import ohrm.malgra.crafting.ManaCraftingRecipe;
import scala.actors.threadpool.Arrays;

import java.util.List;

/**
 * Created by xyz56 on 03/06/2017.
 */
public class ManaCraftingWrapper implements IRecipeWrapper{

    private final List<ItemStack> input;
    private final ItemStack output;
    private final int mana;

    public ManaCraftingWrapper(ManaCraftingRecipe recipe){

        input = Arrays.asList(recipe.getInput());
        output = recipe.getOutput();
        mana = recipe.malgra;

    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return ImmutableList.of();
    }

    @Override
    public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return false;
    }
}