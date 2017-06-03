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
    //private final IDrawable overlay;

    public ManaCraftingCategory(IGuiHelper guiHelper){
        //background = guiHelper.createBlankDrawable(256, 256);
        localizedName = I18n.format("malgra.jei.ManaCraftingTable");
        background = guiHelper.createDrawable(new ResourceLocation("malgra", "textures/gui/manacraftingtable.png"), 5, 11 , 141, 64);
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
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        //overlay.draw(minecraft, 48, 0);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(0, true, 0, 0);
        guiItemStacks.init(1, true, 16, 0);
        guiItemStacks.init(2, true, 32, 0);
        guiItemStacks.init(3, true, 0, 16);
        guiItemStacks.init(4, true, 16, 16);
        guiItemStacks.init(5, true, 32, 16);
        guiItemStacks.init(6, true, 0, 0);
        guiItemStacks.init(7, true, 16, 32);
        guiItemStacks.init(8, true, 32, 32);
        guiItemStacks.init(9, false, 100, 100);

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
