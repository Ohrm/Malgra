package ohrm.malgra.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import ohrm.malgra.crafting.ManaCraftingRecipe;
import ohrm.malgra.crafting.ManaRecipes;
import ohrm.malgra.util.Utils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz56 on 31/05/2017.
 */
public class ManaCraftingCategory implements IRecipeCategory {

    public static final String UID = "ManaCraftingTable";
    private final IDrawable background;
    private final String localizedName;

    public ManaCraftingCategory(IGuiHelper guiHelper){
        localizedName = I18n.format("malgra.jei.ManaCraftingTable");
        background = guiHelper.createDrawable(new ResourceLocation("malgra", "textures/gui/manacraftingtable.png"), 6, 11 , 140, 64);
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(0, true, 23, 5);
        guiItemStacks.init(1, true, 41, 5);
        guiItemStacks.init(2, true, 59, 5);
        guiItemStacks.init(3, true, 23, 23);
        guiItemStacks.init(4, true, 41, 23);
        guiItemStacks.init(5, true, 59, 23);
        guiItemStacks.init(6, true, 23, 41);
        guiItemStacks.init(7, true, 41, 41);
        guiItemStacks.init(8, true, 59, 41);
        guiItemStacks.init(9, false, 117, 23);

        List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);

        for(int i = 0; i < inputs.size(); i++){
            List<ItemStack> input = inputs.get(i);
            if(input != null){
                guiItemStacks.set(i, input.get(0));
            }
        }

        List<List<ItemStack>> output = ingredients.getOutputs(ItemStack.class);

        guiItemStacks.set(9, output.get(0));

    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return new ArrayList();
    }
}
