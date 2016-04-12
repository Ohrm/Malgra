package ohrm.malgra.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import ohrm.malgra.capabilities.CapabilityMana;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.packets.client.SyncManaData;

public class ManaInjector extends Item {

	public ManaInjector() {
		
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote){
			PacketDispatcher.sendTo(new SyncManaData(playerIn.getCapability(CapabilityMana.MANA, null)), (EntityPlayerMP) playerIn);
			if(playerIn.getCapability(CapabilityMana.MANA, null).getMaxMana() == 0){

				playerIn.getCapability(CapabilityMana.MANA, null).setMaxMana(3);
				itemStackIn.stackSize--;
				PacketDispatcher.sendTo(new SyncManaData(playerIn.getCapability(CapabilityMana.MANA, null)), (EntityPlayerMP) playerIn);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}else{
				return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
			}
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
}
