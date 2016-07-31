package ohrm.malgra.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ohrm.malgra.capabilities.CapabilityMana;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.packets.client.SyncManaData;
import ohrm.malgra.world.Dimensions;
import ohrm.malgra.world.TeleporterResearch;
import ohrm.malgra.world.WorldGenResearchRoom;

import java.util.Random;

public class ManaInjector extends Item {

	public ManaInjector() {
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote){
			PacketDispatcher.sendTo(new SyncManaData(playerIn.getCapability(CapabilityMana.MANA, null)), (EntityPlayerMP) playerIn);
			if(playerIn.getCapability(CapabilityMana.MANA, null).getMaxMana() == 0){

				playerIn.getCapability(CapabilityMana.MANA, null).setMaxMana(3);
				itemStackIn.setItem(Items.manaInjectorEmpty);
				PacketDispatcher.sendTo(new SyncManaData(playerIn.getCapability(CapabilityMana.MANA, null)), (EntityPlayerMP) playerIn);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}else{
				return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
			}
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
}
