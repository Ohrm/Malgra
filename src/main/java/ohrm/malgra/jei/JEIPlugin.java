package ohrm.malgra.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.ItemStack;
import ohrm.malgra.blocks.Blocks;

/**
 * Created by xyz56 on 28/05/2017.
 */
@mezz.jei.api.JEIPlugin
public class JEIPlugin extends BlankModPlugin {

    @Override
    public void register(IModRegistry registry) {
        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
        blacklist.addIngredientToBlacklist(new ItemStack(Blocks.researchStoneBrick));
    }
}
