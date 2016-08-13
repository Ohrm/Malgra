package ohrm.malgra.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

/**
 * Created by Toby on 13/08/2016.
 */
public class MalgraPickaxe extends MalgraTool {

    public MalgraPickaxe(int maxMalgra) {
        super(maxMalgra);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        ItemStack pickaxe = new ItemStack(Items.malgraPickaxe);
        pickaxe.setTagCompound(new NBTTagCompound());
        pickaxe.getTagCompound().setInteger("malgra", ((MalgraTool)pickaxe.getItem()).getMaxMalgra());
        subItems.add(pickaxe);
    }
}
