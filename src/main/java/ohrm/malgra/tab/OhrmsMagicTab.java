package ohrm.malgra.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ohrm.malgra.items.Items;

public class OhrmsMagicTab extends CreativeTabs{

	public OhrmsMagicTab(int index, String label) {
		
		super(index, label);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Items.magicDust;
	}	
	
}
