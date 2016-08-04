package ohrm.malgra.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class DimensionTool extends Item {

	public DimensionTool() {
		setMaxStackSize(1);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (entityLiving.dimension != Dimensions.researchDimID)
			if (!worldIn.isRemote)
				new TeleporterResearch(worldIn.getMinecraftServer().getServer().worldServerForDimension(Dimensions.researchDimID)).teleport(entityLiving, worldIn);
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		if (player.dimension == Dimensions.researchDimID)
			new WorldGenResearchRoom().generate(player.worldObj, new Random(), player.getPosition().down().offset(player.getHorizontalFacing(), 30));
		return super.onDroppedByPlayer(item, player);
	}
}
