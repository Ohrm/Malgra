package ohrm.malgra.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import ohrm.malgra.ExtendedEntities.ExtendedPlayer;

public class ManaInjector extends Item {

	public ManaInjector() {
		
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote){
			if(ExtendedPlayer.Get(playerIn).GetMaxMana() == 0){

				ExtendedPlayer.Get(playerIn).SetMaxMana(3);
				itemStackIn.stackSize--;
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}else{
				return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
			}
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
}
