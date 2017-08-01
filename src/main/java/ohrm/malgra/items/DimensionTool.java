package ohrm.malgra.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ohrm.malgra.world.ResearchDimensions;
import ohrm.malgra.world.TeleporterResearch;
import ohrm.malgra.world.WorldGenResearchRoom;

import java.util.Random;

public class DimensionTool extends Item {

	public DimensionTool() {
		setMaxStackSize(1);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (!worldIn.isRemote)
	        if (entityLiving.dimension != ResearchDimensions.get(worldIn).researchDimIDs.get(entityLiving.getUniqueID().toString()))
				new TeleporterResearch(worldIn.getMinecraftServer().getServer().getWorld(ResearchDimensions.get(worldIn).researchDimIDs.get(entityLiving.getUniqueID().toString()))).teleport(entityLiving, worldIn);
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		if (player.dimension == ResearchDimensions.get(player.world).researchDimIDs.get(player.getUniqueID().toString()))
			new WorldGenResearchRoom().generate(player.world, new Random(), player.getPosition().down().offset(player.getHorizontalFacing(), 30));
		return super.onDroppedByPlayer(item, player);
	}
}
