package ohrm.malgra.items.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IItemSpecialRightClick {

    boolean onRightClick(World world, BlockPos pos, EntityPlayer player, EnumFacing facing, EnumHand hand, ItemStack stack);

}
