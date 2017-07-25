package ohrm.malgra.items;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import ohrm.malgra.MalgraMain;

import java.util.List;
import java.util.Set;

/**
 * Created by Toby on 17/08/2016.
 */
public class MalgraSword extends MalgraTool {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.WEB});

    public MalgraSword(float attackDamageIn, float attackSpeedIn, ToolMaterial material, int maxMalgra) {
        super(attackDamageIn, attackSpeedIn, material, EFFECTIVE_ON, maxMalgra);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(tab != MalgraMain.magicTab)
            return;

        ItemStack sword = new ItemStack(Items.malgraSword);
        sword.setTagCompound(new NBTTagCompound());
        sword.getTagCompound().setInteger("malgra", ((MalgraTool)sword.getItem()).getMaxMalgra());
        subItems.add(sword);
    }

}
