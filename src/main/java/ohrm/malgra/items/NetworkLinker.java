package ohrm.malgra.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ohrm.malgra.items.base.IItemSpecialRightClick;

public class NetworkLinker extends Item implements IItemSpecialRightClick {

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public boolean onRightClick(World world, BlockPos pos, EntityPlayer player, EnumFacing facing, EnumHand hand, ItemStack stack) {
        if(!world.isRemote){
            //Link
        }else{
            player.swingArm(hand);
        }

        return true;
    }
}
