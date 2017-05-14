package ohrm.malgra.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ohrm.malgra.items.Items;

public class MalgraTab extends CreativeTabs{

	public MalgraTab(int index, String label) {
		
		super(index, label);
		
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.magicDust);
	}
	
}
