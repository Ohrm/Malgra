package ohrm.malgra.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ohrm.malgra.items.Items;

/**
 * Created by Toby on 18/04/2016.
 */
public class CraftingRecipes {

    public static void init() {
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

        GameRegistry.addRecipe(new ShapelessOreRecipe(t_f_extractor, Items.flintExtractorTip, Items.tinyContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(t_i_extractor, Items.ironExtractorTip, Items.tinyContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(t_q_extractor, Items.quartzExtractorTip, Items.tinyContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(t_d_extractor, Items.diamondExtractorTip, Items.tinyContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(t_m_extractor, Items.malgrumExtractorTip, Items.tinyContainer));

        GameRegistry.addRecipe(new ShapelessOreRecipe(s_f_extractor, Items.flintExtractorTip, Items.smallContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(s_i_extractor, Items.ironExtractorTip, Items.smallContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(s_q_extractor, Items.quartzExtractorTip, Items.smallContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(s_d_extractor, Items.diamondExtractorTip, Items.smallContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(s_m_extractor, Items.malgrumExtractorTip, Items.smallContainer));

        GameRegistry.addRecipe(new ShapelessOreRecipe(m_f_extractor, Items.flintExtractorTip, Items.mediumContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(m_i_extractor, Items.ironExtractorTip, Items.mediumContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(m_q_extractor, Items.quartzExtractorTip, Items.mediumContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(m_d_extractor, Items.diamondExtractorTip, Items.mediumContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(m_m_extractor, Items.malgrumExtractorTip, Items.mediumContainer));

        GameRegistry.addRecipe(new ShapelessOreRecipe(l_f_extractor, Items.flintExtractorTip, Items.largeContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(l_i_extractor, Items.ironExtractorTip, Items.largeContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(l_q_extractor, Items.quartzExtractorTip, Items.largeContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(l_d_extractor, Items.diamondExtractorTip, Items.largeContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(l_m_extractor, Items.malgrumExtractorTip, Items.largeContainer));

        GameRegistry.addRecipe(new ShapelessOreRecipe(h_f_extractor, Items.flintExtractorTip, Items.hugeContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(h_i_extractor, Items.ironExtractorTip, Items.hugeContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(h_q_extractor, Items.quartzExtractorTip, Items.hugeContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(h_d_extractor, Items.diamondExtractorTip, Items.hugeContainer));
        GameRegistry.addRecipe(new ShapelessOreRecipe(h_m_extractor, Items.malgrumExtractorTip, Items.hugeContainer));

    }

}
