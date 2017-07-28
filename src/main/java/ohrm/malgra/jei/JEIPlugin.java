package ohrm.malgra.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;
import ohrm.malgra.blocks.Blocks;
import ohrm.malgra.crafting.ManaCraftingRecipe;
import ohrm.malgra.crafting.ManaRecipes;
import ohrm.malgra.gui.GuiManaCraftingTable;

/**
 * Created by xyz56 on 28/05/2017.
 */
@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {

    public static IJeiHelpers jeiHelpers;

    @Override
    public void register(IModRegistry registry) {

        jeiHelpers = registry.getJeiHelpers();

        registry.addRecipes(ManaRecipes.recipesList, ManaCraftingCategory.UID);

        registry.handleRecipes(ManaCraftingRecipe.class, new IRecipeWrapperFactory<ManaCraftingRecipe>() {
            @Override
            public IRecipeWrapper getRecipeWrapper(ManaCraftingRecipe recipe) {
                return new ManaCraftingWrapper(recipe);
            }
        }, ManaCraftingCategory.UID);

        registry.addRecipeCatalyst(new ItemStack(Blocks.manaCraftingTable), ManaCraftingCategory.UID);

        registry.addRecipeClickArea(GuiManaCraftingTable.class, 90, 35, 21, 14, ManaCraftingCategory.UID);

        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
        blacklist.addIngredientToBlacklist(new ItemStack(Blocks.researchStoneBrick));
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new ManaCraftingCategory(guiHelper));
    }
}
