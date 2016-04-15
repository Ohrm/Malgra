package ohrm.malgra.items;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import ohrm.malgra.api.MalgraAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Toby on 13/04/2016.
 */
public class ManaExtractor extends ItemPickaxe {

    int maxMalgraStorage;

    public Map<Block, Integer> getMalgraProviders() {
        return MalgraAPI.malgraProviders;
    }

    public ManaExtractor(ToolMaterial material, int maxMalgraStorage) {
        super(material);
        this.maxMalgraStorage = maxMalgraStorage;
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return super.canHarvestBlock(blockIn);
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "shovel", "axe");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        int malgra = 0;
        if (stack.hasTagCompound()) {
            malgra = stack.getTagCompound().getInteger("malgra");
        } else {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setInteger("malgra", malgra);
        }
        tooltip.add(I18n.translateToLocal("item.manaExtractor.malgraStored") + ": " + stack.getTagCompound().getInteger("malgra") + "/" + this.maxMalgraStorage);
        super.addInformation(stack, playerIn, tooltip, advanced);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (getMalgraProviders().containsKey(state.getBlock())) {
            if (stack.hasTagCompound()) {
                stack.getTagCompound().setInteger("malgra", stack.getTagCompound().getInteger("malgra") + getMalgraProviders().get(state.getBlock()));
            } else {
                stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setInteger("malgra", getMalgraProviders().get(state.getBlock()));
        }
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    @SubscribeEvent
    public void onItemBroken(PlayerDestroyItemEvent event) {
        if (event.getOriginal().equals(new ItemStack(this))) {
            //TODO Broken state
        }
    }

    @SubscribeEvent
    public void onBlockHarvested(BlockEvent.HarvestDropsEvent event) {
        if (event.getHarvester() != null && event.getHarvester().getHeldItemMainhand() != null) {
            if (event.getHarvester().getHeldItemMainhand().getItem().equals(Items.manaExtractor)) {
                event.getDrops().clear();
            }
         }
    }
}
