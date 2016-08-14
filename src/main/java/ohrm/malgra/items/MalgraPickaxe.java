package ohrm.malgra.items;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;
import java.util.Set;

/**
 * Created by Toby on 13/08/2016.
 */
public class MalgraPickaxe extends MalgraTool {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {net.minecraft.init.Blocks.ACTIVATOR_RAIL, net.minecraft.init.Blocks.COAL_ORE, net.minecraft.init.Blocks.COBBLESTONE, net.minecraft.init.Blocks.DETECTOR_RAIL, net.minecraft.init.Blocks.DIAMOND_BLOCK, net.minecraft.init.Blocks.DIAMOND_ORE, net.minecraft.init.Blocks.DOUBLE_STONE_SLAB, net.minecraft.init.Blocks.GOLDEN_RAIL, net.minecraft.init.Blocks.GOLD_BLOCK, net.minecraft.init.Blocks.GOLD_ORE, net.minecraft.init.Blocks.ICE, net.minecraft.init.Blocks.IRON_BLOCK, net.minecraft.init.Blocks.IRON_ORE, net.minecraft.init.Blocks.LAPIS_BLOCK, net.minecraft.init.Blocks.LAPIS_ORE, net.minecraft.init.Blocks.LIT_REDSTONE_ORE, net.minecraft.init.Blocks.MOSSY_COBBLESTONE, net.minecraft.init.Blocks.NETHERRACK, net.minecraft.init.Blocks.PACKED_ICE, net.minecraft.init.Blocks.RAIL, net.minecraft.init.Blocks.REDSTONE_ORE, net.minecraft.init.Blocks.SANDSTONE, net.minecraft.init.Blocks.RED_SANDSTONE, net.minecraft.init.Blocks.STONE, net.minecraft.init.Blocks.STONE_SLAB, net.minecraft.init.Blocks.STONE_BUTTON, net.minecraft.init.Blocks.STONE_PRESSURE_PLATE});

    public MalgraPickaxe(float attackDamageIn, float attackSpeedIn, ToolMaterial material, int maxMalgra) {
        super(attackDamageIn, attackSpeedIn, material, EFFECTIVE_ON, maxMalgra);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        ItemStack pickaxe = new ItemStack(Items.malgraPickaxe);
        pickaxe.setTagCompound(new NBTTagCompound());
        pickaxe.getTagCompound().setInteger("malgra", ((MalgraTool)pickaxe.getItem()).getMaxMalgra());
        subItems.add(pickaxe);
    }
}
