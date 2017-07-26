package ohrm.malgra.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import ohrm.malgra.fluid.Fluids;
import ohrm.malgra.items.Items;

public class MalgraTab extends CreativeTabs{

	public MalgraTab(int index, String label) {
		
		super(index, label);
		
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.magicDust);
	}

	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> itemStacks) {
		super.displayAllRelevantItems(itemStacks);
		//Add the universal bucket to the creative tab
		itemStacks.add(FluidUtil.getFilledBucket(new FluidStack(Fluids.liquidMalgra, 1000)));
	}
}
