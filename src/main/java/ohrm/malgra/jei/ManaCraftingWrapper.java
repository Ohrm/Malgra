package ohrm.malgra.jei;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ohrm.malgra.crafting.ManaCraftingRecipe;
import ohrm.malgra.util.Utils;

import java.util.List;

/**
 * Created by xyz56 on 03/06/2017.
 */
public class ManaCraftingWrapper implements IRecipeWrapper{

    private final List<List<ItemStack>> input;
    private final ItemStack output;
    private final int mana;

    public ManaCraftingWrapper(ManaCraftingRecipe recipe){

        ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();

        for(Object obj : recipe.getInputs()){
            if(obj instanceof ItemStack){
                builder.add(ImmutableList.of((ItemStack)obj));
            }
            if(obj instanceof String){
                builder.add(OreDictionary.getOres((String)obj));
            }
        }

        input = builder.build();
        output = recipe.getOutput();
        mana = recipe.malgra;

    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

        minecraft.fontRenderer.drawString(Utils.translateToLocal("malgra.jei.manacraftingtable.required") + " " + mana, 0, 60, 0x808080, false);

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
