package ohrm.malgra.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ohrm.malgra.ExtendedEntities.ExtendedPlayer;

public class ManaInjector extends Item {

	public ManaInjector() {
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		
		if(!worldIn.isRemote){
			if(ExtendedPlayer.Get(playerIn).GetMaxMana() == 0){
				
				ExtendedPlayer.Get(playerIn).SetMaxMana(3);
				return itemStackIn.splitStack(1);
				
			}else{
				return itemStackIn;
				
			}
		}
		return itemStackIn;
	}
	
}
