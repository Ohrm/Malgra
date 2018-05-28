package ohrm.malgra.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ohrm.malgra.api.network.IMalgraLinkable;

import javax.annotation.Nullable;

public class NetworkLinker extends Item {

    public NetworkLinker(){
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //Link
        TileEntity block = worldIn.getTileEntity(pos);
        if(block instanceof IMalgraLinkable){
            BlockPos linkPos = getLinkedPos(player.getHeldItem(hand));
            if(linkPos != null && worldIn.getTileEntity(linkPos) != null){
                IMalgraLinkable linkFrom = ((IMalgraLinkable)worldIn.getTileEntity(linkPos));
                linkFrom.onLinkedSource((IMalgraLinkable)block);
                ((IMalgraLinkable) block).onLinkedDestination(linkFrom);
                removeLinkedPos(player.getHeldItem(hand));
                return EnumActionResult.SUCCESS;
            }else{
                setLinkedPos(player.getHeldItem(hand), pos);
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }

    @Nullable
    public BlockPos getLinkedPos(ItemStack stack){
        Integer x = getNbtInt(stack, "linkedX");
        Integer y = getNbtInt(stack, "linkedY");
        Integer z = getNbtInt(stack, "linkedZ");
        if(x != null && y != null && z != null)
            return new BlockPos(x, y, z);

        return null;
    }

    public void setLinkedPos(ItemStack stack, BlockPos pos){
        setNbtInt(stack, "linkedX", pos.getX());
        setNbtInt(stack, "linkedY", pos.getY());
        setNbtInt(stack, "linkedZ", pos.getZ());
    }

    private NBTTagCompound getNbtTagCompound(ItemStack stack){
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        return stack.getTagCompound();
    }

    @Nullable
    private Integer getNbtInt(ItemStack stack, String tag){
        if(stack.isEmpty() || !getNbtTagCompound(stack).hasKey(tag))
            return null;

        return getNbtTagCompound(stack).getInteger(tag);
    }

    private void setNbtInt(ItemStack stack, String tag, Integer value){
        getNbtTagCompound(stack).setInteger(tag, value);
    }

    private void removeLinkedPos(ItemStack stack){
        getNbtTagCompound(stack).removeTag("linkedX");
        getNbtTagCompound(stack).removeTag("linkedY");
        getNbtTagCompound(stack).removeTag("linkedZ");
    }

}
