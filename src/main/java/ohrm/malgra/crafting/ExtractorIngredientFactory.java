package ohrm.malgra.crafting;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

import javax.annotation.Nonnull;

public class ExtractorIngredientFactory implements IIngredientFactory {

    @Nonnull
    @Override
    public Ingredient parse(JsonContext context, JsonObject json) {
        final ItemStack stack = CraftingHelper.getItemStack(json, context);

        Item tip = JsonUtils.getItem(json, "tip");
        Item container = JsonUtils.getItem(json, "container");

        stack.setTagCompound(new NBTTagCompound());
        if (stack.getTagCompound() != null) {
            stack.getTagCompound().setString("tip", tip.getRegistryName().getResourcePath());
            stack.getTagCompound().setString("uses", container.getRegistryName().getResourcePath());
        }

        return new IngredientNBT(stack);
    }

}
