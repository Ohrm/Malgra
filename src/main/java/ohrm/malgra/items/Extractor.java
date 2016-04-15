package ohrm.malgra.items;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.api.MalgraAPI;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Toby on 14/04/2016.
 */
public class Extractor extends ItemPickaxe {

    ExtractorContainer container;
    ExtractorTip tip;

    public Extractor(ExtractorContainer container, ExtractorTip tip) {
        super(tip.getMaterial());
        this.tip = tip;
        this.container = container;
        this.setCreativeTab(MalgraMain.magicTab);
    }

    public Map<Block, Integer> getMalgraProviders() {
        return MalgraAPI.malgraProviders;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (getMalgraProviders().containsKey(state.getBlock())) {
            if (stack.hasTagCompound()) {
                if (stack.getTagCompound().getInteger("malgra") + getMalgraProviders().get(state.getBlock()) > this.container.getStorage()) {
                    stack.getTagCompound().setInteger("buffer", (stack.getTagCompound().getInteger("malgra") + getMalgraProviders().get(state.getBlock())) - this.container.getStorage());
                    stack.getTagCompound().setInteger("malgra", this.container.getStorage());
                } else {
                    stack.getTagCompound().setInteger("malgra", stack.getTagCompound().getInteger("malgra") + getMalgraProviders().get(state.getBlock()));
                }
            } else {
                stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setInteger("malgra", getMalgraProviders().get(state.getBlock()));
            }
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    public ExtractorContainer getContainer() {
        return container;
    }

    public void setContainer(ExtractorContainer container) {
        this.container = container;
    }

    public ExtractorTip getTip() {
        return tip;
    }

    public void setTip(ExtractorTip tip) {
        this.tip = tip;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String containerName = "item." + this.getContainer().getPrefix() + ".name";
        String tipName = "item." + this.getTip().getPrefix() + ".name";
        String extractorName = this.getUnlocalizedName() + ".name";
        return I18n.translateToLocal(containerName) + " " + I18n.translateToLocal(tipName) + " " + I18n.translateToLocal(extractorName);
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
        tooltip.add(I18n.translateToLocal("item.manaExtractor.malgraStored") + ": " + stack.getTagCompound().getInteger("malgra") + "/" + this.container.getStorage());
        super.addInformation(stack, playerIn, tooltip, advanced);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand) {
        int firstEmptySlot = -1;
        if (player.isSneaking()) {
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                if (player.inventory.getStackInSlot(i) == null) {
                    firstEmptySlot = i;
                    break;
                }
            }
            if (firstEmptySlot != -1) {
                //Give container
                //Give tip
            }
        }
        return super.onItemRightClick(itemStack, world, player, hand);
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
            if (event.getHarvester().getHeldItemMainhand().getItem().equals(Items.extractor)) {
                event.getDrops().clear();
            }
        }
    }
}
