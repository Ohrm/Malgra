package ohrm.malgra.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.MinecraftServerGui;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Set;

/**
 * Created by Toby on 13/08/2016.
 */
public class MalgraTool extends ItemTool {

    public int maxMalgra;

    public MalgraTool(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn, int maxMalgra) {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
        this.maxMalgra = maxMalgra;
        this.setMaxStackSize(1);
    }

    public int getMaxMalgra() {
        return maxMalgra;
    }

    public void setMaxMalgra(int max) {
        maxMalgra = max;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().getInteger("malgra") > 0) {
                if (state.getBlock().getBlockHardness(state, worldIn, pos) != 0)
                stack.getTagCompound().setInteger("malgra", stack.getTagCompound().getInteger("malgra") - 1);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().getInteger("malgra") > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().getInteger("malgra") > 0) {
                stack.getTagCompound().setInteger("malgra", stack.getTagCompound().getInteger("malgra") - 1);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if (stack.hasTagCompound()) {
            return 1-(double) stack.getTagCompound().getInteger("malgra") / (double)getMaxMalgra();
        } else {
            return (double)0 / (double)getMaxMalgra();
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (stack.hasTagCompound()) {
            tooltip.add("Stored Malgra: " + stack.getTagCompound().getInteger("malgra") + "/" + getMaxMalgra());
        }
        super.addInformation(stack, playerIn, tooltip, advanced);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().getInteger("malgra") > 0) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    @SubscribeEvent
    public void breakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getEntityPlayer().getHeldItemMainhand() != null) {
            if (event.getEntityPlayer().getHeldItemMainhand().getItem() == Items.malgraPickaxe){
                if (event.getEntityPlayer().getHeldItemMainhand().hasTagCompound()) {
                    if (event.getEntityPlayer().getHeldItemMainhand().getTagCompound().getInteger("malgra") <= 0) {
                        event.setCanceled(true);
                    } else {
                        event.setCanceled(false);
                    }
                }
            }
        }
    }
}
