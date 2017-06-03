package ohrm.malgra.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;
import ohrm.malgra.blocks.Blocks;
import ohrm.malgra.crafting.ManaCraftingRecipe;
import ohrm.malgra.crafting.ManaRecipes;
import ohrm.malgra.items.Items;

/**
 * Created by xyz56 on 28/05/2017.
 */
@mezz.jei.api.JEIPlugin
public class JEIPlugin extends BlankModPlugin {

    public static IJeiHelpers jeiHelpers;

    @Override
    public void register(IModRegistry registry) {

        jeiHelpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registry.addRecipeCategories(new ManaCraftingCategory(guiHelper));

        registry.addRecipes(ManaRecipes.recipesList, ManaCraftingCategory.UID);

        registry.handleRecipes(ManaCraftingRecipe.class, new IRecipeWrapperFactory<ManaCraftingRecipe>() {
            @Override
            public IRecipeWrapper getRecipeWrapper(ManaCraftingRecipe recipe) {
                return new ManaCraftingWrapper(recipe);
            }
        }, ManaCraftingCategory.UID);

        registry.addRecipeCategoryCraftingItem(new ItemStack(Blocks.manaCraftingTable), ManaCraftingCategory.UID);

        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
        blacklist.addIngredientToBlacklist(new ItemStack(Blocks.researchStoneBrick));
    }
}