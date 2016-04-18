package ohrm.malgra.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.Reference;

/**
 * Created by Toby on 18/04/2016.
 */
public class ExtractorItemOverrides extends ItemOverrideList {

    public static final ExtractorItemOverrides INSTANCE = new ExtractorItemOverrides();

    private ExtractorItemOverrides() {
        super(ImmutableList.<ItemOverride>of());
    }

    @Override
    public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
        if (stack.hasTagCompound()) {
            ((ExtractorModel) originalModel).container = ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID, stack.getTagCompound().getString("container")));
            ((ExtractorModel) originalModel).tip = ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID, stack.getTagCompound().getString("tip")));
        }
        return originalModel;
    }
}
