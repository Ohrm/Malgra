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
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote){
			PacketDispatcher.sendTo(new SyncManaData(playerIn.getCapability(CapabilityMana.MANA, null)), (EntityPlayerMP) playerIn);
			if(playerIn.getCapability(CapabilityMana.MANA, null).getMaxMana() == 0){

				playerIn.getCapability(CapabilityMana.MANA, null).setMaxMana(3);
				playerIn.setHeldItem(handIn, new ItemStack(Items.manaInjectorEmpty));
				PacketDispatcher.sendTo(new SyncManaData(playerIn.getCapability(CapabilityMana.MANA, null)), (EntityPlayerMP) playerIn);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
			}else{
				return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
			}
		}
		return super.onItemRightClick( worldIn, playerIn, handIn);
	}

}
