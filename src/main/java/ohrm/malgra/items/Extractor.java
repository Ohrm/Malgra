package ohrm.malgra.items;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.Reference;
import ohrm.malgra.api.MalgraAPI;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Toby on 14/04/2016.
 */
public class Extractor extends Item {

    public Extractor() {
        super();
        this.setCreativeTab(MalgraMain.magicTab);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        if (stack.hasTagCompound()) {
            ExtractorTip tip = (ExtractorTip) ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID, stack.getTagCompound().getString("tip")));
            if (tip != null)
                return tip.getMaterial().getMaxUses();
        }
        return super.getMaxDamage(stack);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (stack.hasTagCompound()) {
            ExtractorTip tip = (ExtractorTip) ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID, stack.getTagCompound().getString("tip")));
            if (tip != null)
                return tip.getMaterial().getEfficiencyOnProperMaterial();
        }
        return super.getStrVsBlock(stack, state);
    }

    public Map<Block, Integer> getMalgraProviders() {
        return MalgraAPI.malgraProviders;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap multimap = super.getAttributeModifiers(slot, stack);
        if (stack.hasTagCompound()) {
            ExtractorTip tip = (ExtractorTip) ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID, stack.getTagCompound().getString("tip")));
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)tip.getMaterial().getDamageVsEntity(), 0));
        }
        return multimap;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (getMalgraProviders().containsKey(state.getBlock())) {
            if (stack.hasTagCompound()) {
                ExtractorContainer container = (ExtractorContainer) (ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID, stack.getTagCompound().getString("container"))));
                if (stack.getTagCompound().getInteger("malgra") + getMalgraProviders().get(state.getBlock()) > container.getStorage()) {
                    stack.getTagCompound().setInteger("buffer", (stack.getTagCompound().getInteger("malgra") + getMalgraProviders().get(state.getBlock())) - container.getStorage());
                    stack.getTagCompound().setInteger("malgra", container.getStorage());
                } else {
                    stack.getTagCompound().setInteger("malgra", stack.getTagCompound().getInteger("malgra") + getMalgraProviders().get(state.getBlock()));
                }
            } else {
                stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setInteger("malgra", getMalgraProviders().get(state.getBlock()));
                stack.getTagCompound().setString("container", Items.tinyContainer.getUnlocalizedName().substring(5));
            }
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }



    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        ItemStack
                t_f_extractor, t_i_extractor, t_q_extractor, t_d_extractor, t_m_extractor, s_f_extractor,
                s_i_extractor, s_q_extractor, s_d_extractor, s_m_extractor, m_f_extractor, m_i_extractor,
                m_q_extractor, m_d_extractor, m_m_extractor, l_f_extractor, l_i_extractor, l_q_extractor,
                l_d_extractor, l_m_extractor, h_f_extractor, h_i_extractor, h_q_extractor, h_d_extractor,
                h_m_extractor
        ;

        t_f_extractor = new ItemStack(Items.extractor);
        t_i_extractor = new ItemStack(Items.extractor);
        t_q_extractor = new ItemStack(Items.extractor);
        t_d_extractor = new ItemStack(Items.extractor);
        t_m_extractor = new ItemStack(Items.extractor);
        s_f_extractor = new ItemStack(Items.extractor);
        s_i_extractor = new ItemStack(Items.extractor);
        s_q_extractor = new ItemStack(Items.extractor);
        s_d_extractor = new ItemStack(Items.extractor);
        s_m_extractor = new ItemStack(Items.extractor);
        m_f_extractor = new ItemStack(Items.extractor);
        m_i_extractor = new ItemStack(Items.extractor);
        m_q_extractor = new ItemStack(Items.extractor);
        m_d_extractor = new ItemStack(Items.extractor);
        m_m_extractor = new ItemStack(Items.extractor);
        l_f_extractor = new ItemStack(Items.extractor);
        l_i_extractor = new ItemStack(Items.extractor);
        l_q_extractor = new ItemStack(Items.extractor);
        l_d_extractor = new ItemStack(Items.extractor);
        l_m_extractor = new ItemStack(Items.extractor);
        h_f_extractor = new ItemStack(Items.extractor);
        h_i_extractor = new ItemStack(Items.extractor);
        h_q_extractor = new ItemStack(Items.extractor);
        h_d_extractor = new ItemStack(Items.extractor);
        h_m_extractor = new ItemStack(Items.extractor);

        t_f_extractor.setTagCompound(new NBTTagCompound());
        t_f_extractor.getTagCompound().setString("container", Items.tinyContainer.getUnlocalizedName().substring(5));
        t_f_extractor.getTagCompound().setString("tip", Items.flintExtractorTip.getUnlocalizedName().substring(5));
        t_i_extractor.setTagCompound(new NBTTagCompound());
        t_i_extractor.getTagCompound().setString("container", Items.tinyContainer.getUnlocalizedName().substring(5));
        t_i_extractor.getTagCompound().setString("tip", Items.ironExtractorTip.getUnlocalizedName().substring(5));
        t_q_extractor.setTagCompound(new NBTTagCompound());
        t_q_extractor.getTagCompound().setString("container", Items.tinyContainer.getUnlocalizedName().substring(5));
        t_q_extractor.getTagCompound().setString("tip", Items.quartzExtractorTip.getUnlocalizedName().substring(5));
        t_d_extractor.setTagCompound(new NBTTagCompound());
        t_d_extractor.getTagCompound().setString("container", Items.tinyContainer.getUnlocalizedName().substring(5));
        t_d_extractor.getTagCompound().setString("tip", Items.diamondExtractorTip.getUnlocalizedName().substring(5));
        t_m_extractor.setTagCompound(new NBTTagCompound());
        t_m_extractor.getTagCompound().setString("container", Items.tinyContainer.getUnlocalizedName().substring(5));
        t_m_extractor.getTagCompound().setString("tip", Items.malgrumExtractorTip.getUnlocalizedName().substring(5));

        s_f_extractor.setTagCompound(new NBTTagCompound());
        s_f_extractor.getTagCompound().setString("container", Items.smallContainer.getUnlocalizedName().substring(5));
        s_f_extractor.getTagCompound().setString("tip", Items.flintExtractorTip.getUnlocalizedName().substring(5));
        s_i_extractor.setTagCompound(new NBTTagCompound());
        s_i_extractor.getTagCompound().setString("container", Items.smallContainer.getUnlocalizedName().substring(5));
        s_i_extractor.getTagCompound().setString("tip", Items.ironExtractorTip.getUnlocalizedName().substring(5));
        s_q_extractor.setTagCompound(new NBTTagCompound());
        s_q_extractor.getTagCompound().setString("container", Items.smallContainer.getUnlocalizedName().substring(5));
        s_q_extractor.getTagCompound().setString("tip", Items.quartzExtractorTip.getUnlocalizedName().substring(5));
        s_d_extractor.setTagCompound(new NBTTagCompound());
        s_d_extractor.getTagCompound().setString("container", Items.smallContainer.getUnlocalizedName().substring(5));
        s_d_extractor.getTagCompound().setString("tip", Items.diamondExtractorTip.getUnlocalizedName().substring(5));
        s_m_extractor.setTagCompound(new NBTTagCompound());
        s_m_extractor.getTagCompound().setString("container", Items.smallContainer.getUnlocalizedName().substring(5));
        s_m_extractor.getTagCompound().setString("tip", Items.malgrumExtractorTip.getUnlocalizedName().substring(5));

        m_f_extractor.setTagCompound(new NBTTagCompound());
        m_f_extractor.getTagCompound().setString("container", Items.mediumContainer.getUnlocalizedName().substring(5));
        m_f_extractor.getTagCompound().setString("tip", Items.flintExtractorTip.getUnlocalizedName().substring(5));
        m_i_extractor.setTagCompound(new NBTTagCompound());
        m_i_extractor.getTagCompound().setString("container", Items.mediumContainer.getUnlocalizedName().substring(5));
        m_i_extractor.getTagCompound().setString("tip", Items.ironExtractorTip.getUnlocalizedName().substring(5));
        m_q_extractor.setTagCompound(new NBTTagCompound());
        m_q_extractor.getTagCompound().setString("container", Items.mediumContainer.getUnlocalizedName().substring(5));
        m_q_extractor.getTagCompound().setString("tip", Items.quartzExtractorTip.getUnlocalizedName().substring(5));
        m_d_extractor.setTagCompound(new NBTTagCompound());
        m_d_extractor.getTagCompound().setString("container", Items.mediumContainer.getUnlocalizedName().substring(5));
        m_d_extractor.getTagCompound().setString("tip", Items.diamondExtractorTip.getUnlocalizedName().substring(5));
        m_m_extractor.setTagCompound(new NBTTagCompound());
        m_m_extractor.getTagCompound().setString("container", Items.mediumContainer.getUnlocalizedName().substring(5));
        m_m_extractor.getTagCompound().setString("tip", Items.malgrumExtractorTip.getUnlocalizedName().substring(5));

        l_f_extractor.setTagCompound(new NBTTagCompound());
        l_f_extractor.getTagCompound().setString("container", Items.largeContainer.getUnlocalizedName().substring(5));
        l_f_extractor.getTagCompound().setString("tip", Items.flintExtractorTip.getUnlocalizedName().substring(5));
        l_i_extractor.setTagCompound(new NBTTagCompound());
        l_i_extractor.getTagCompound().setString("container", Items.largeContainer.getUnlocalizedName().substring(5));
        l_i_extractor.getTagCompound().setString("tip", Items.ironExtractorTip.getUnlocalizedName().substring(5));
        l_q_extractor.setTagCompound(new NBTTagCompound());
        l_q_extractor.getTagCompound().setString("container", Items.largeContainer.getUnlocalizedName().substring(5));
        l_q_extractor.getTagCompound().setString("tip", Items.quartzExtractorTip.getUnlocalizedName().substring(5));
        l_d_extractor.setTagCompound(new NBTTagCompound());
        l_d_extractor.getTagCompound().setString("container", Items.largeContainer.getUnlocalizedName().substring(5));
        l_d_extractor.getTagCompound().setString("tip", Items.diamondExtractorTip.getUnlocalizedName().substring(5));
        l_m_extractor.setTagCompound(new NBTTagCompound());
        l_m_extractor.getTagCompound().setString("container", Items.largeContainer.getUnlocalizedName().substring(5));
        l_m_extractor.getTagCompound().setString("tip", Items.malgrumExtractorTip.getUnlocalizedName().substring(5));

        h_f_extractor.setTagCompound(new NBTTagCompound());
        h_f_extractor.getTagCompound().setString("container", Items.hugeContainer.getUnlocalizedName().substring(5));
        h_f_extractor.getTagCompound().setString("tip", Items.flintExtractorTip.getUnlocalizedName().substring(5));
        h_i_extractor.setTagCompound(new NBTTagCompound());
        h_i_extractor.getTagCompound().setString("container", Items.hugeContainer.getUnlocalizedName().substring(5));
        h_i_extractor.getTagCompound().setString("tip", Items.ironExtractorTip.getUnlocalizedName().substring(5));
        h_q_extractor.setTagCompound(new NBTTagCompound());
        h_q_extractor.getTagCompound().setString("container", Items.hugeContainer.getUnlocalizedName().substring(5));
        h_q_extractor.getTagCompound().setString("tip", Items.quartzExtractorTip.getUnlocalizedName().substring(5));
        h_d_extractor.setTagCompound(new NBTTagCompound());
        h_d_extractor.getTagCompound().setString("container", Items.hugeContainer.getUnlocalizedName().substring(5));
        h_d_extractor.getTagCompound().setString("tip", Items.diamondExtractorTip.getUnlocalizedName().substring(5));
        h_m_extractor.setTagCompound(new NBTTagCompound());
        h_m_extractor.getTagCompound().setString("container", Items.hugeContainer.getUnlocalizedName().substring(5));
        h_m_extractor.getTagCompound().setString("tip", Items.malgrumExtractorTip.getUnlocalizedName().substring(5));

        subItems.add(t_f_extractor);
        subItems.add(t_i_extractor);
        subItems.add(t_q_extractor);
        subItems.add(t_d_extractor);
        subItems.add(t_m_extractor);

        subItems.add(s_f_extractor);
        subItems.add(s_i_extractor);
        subItems.add(s_q_extractor);
        subItems.add(s_d_extractor);
        subItems.add(s_m_extractor);

        subItems.add(m_f_extractor);
        subItems.add(m_i_extractor);
        subItems.add(m_q_extractor);
        subItems.add(m_d_extractor);
        subItems.add(m_m_extractor);

        subItems.add(l_f_extractor);
        subItems.add(l_i_extractor);
        subItems.add(l_q_extractor);
        subItems.add(l_d_extractor);
        subItems.add(l_m_extractor);

        subItems.add(h_f_extractor);
        subItems.add(h_i_extractor);
        subItems.add(h_q_extractor);
        subItems.add(h_d_extractor);
        subItems.add(h_m_extractor);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (stack.hasTagCompound()) {
            ExtractorContainer container = (ExtractorContainer) (ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID, stack.getTagCompound().getString("container"))));
            ExtractorTip tip = (ExtractorTip) ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID, stack.getTagCompound().getString("tip")));
            if (container != null && tip != null) {
                String containerName = "extractor.prefix." + container.getPrefix();
                String tipName = "extractor.prefix." + tip.getPrefix();
                String extractorName = this.getUnlocalizedName() + ".name";
                return I18n.translateToLocal(containerName) + " " + I18n.translateToLocal(tipName) + " " + I18n.translateToLocal(extractorName);
            }
        }
        return super.getItemStackDisplayName(stack);
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
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) { /* Replaced by ItemTooltipEvent */ }

    @SubscribeEvent
    public void onTooltipEvent(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() == Items.extractor) {
            event.getToolTip().subList(1, event.getToolTip().size()).clear();
            int malgra = 0;
            int maxMalgra = 0;
            if (stack.hasTagCompound()) {
                malgra = stack.getTagCompound().getInteger("malgra");
                ExtractorContainer container = (ExtractorContainer) (ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID, stack.getTagCompound().getString("container"))));
                maxMalgra = container.getStorage();
            } else {
                stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setInteger("malgra", malgra);
                stack.getTagCompound().setString("container", Items.tinyContainer.getUnlocalizedName().substring(5));
            }
            event.getToolTip().add(I18n.translateToLocal("item.manaExtractor.malgraStored") + ": " + malgra + "/" + maxMalgra);
            if (stack.hasTagCompound()) {
                ExtractorTip tip = (ExtractorTip) (ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID, stack.getTagCompound().getString("tip"))));
                if (tip != null)
                    event.getToolTip().add((tip.getMaterial().getDamageVsEntity() + 4) + " " + I18n.translateToLocal("attribute.name.generic.attackDamage"));
            }
        }
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
