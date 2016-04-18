package ohrm.malgra.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;
import ohrm.malgra.Reference;
import ohrm.malgra.items.Extractor;
import ohrm.malgra.items.Items;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND;

/**
 * Created by Toby on 18/04/2016.
 */
public class ExtractorModel implements IPerspectiveAwareModel {

    public static final ModelResourceLocation baseModelResourceLocation = new ModelResourceLocation(Reference.MODID + ":extractor_base", "inventory");
    public static final Map<Item, ModelResourceLocation> containerModelResourceLocations = new HashMap<Item, ModelResourceLocation>() {{
        put(Items.tinyContainer, new ModelResourceLocation(Reference.MODID + ":extractor_container_tiny", "inventory"));
        put(Items.smallContainer, new ModelResourceLocation(Reference.MODID + ":extractor_container_small", "inventory"));
        put(Items.mediumContainer, new ModelResourceLocation(Reference.MODID + ":extractor_container_medium", "inventory"));
        put(Items.largeContainer, new ModelResourceLocation(Reference.MODID + ":extractor_container_large", "inventory"));
        put(Items.hugeContainer, new ModelResourceLocation(Reference.MODID + ":extractor_container_huge", "inventory"));
    }};

    public static final Map<Item, ModelResourceLocation> tipModelResourceLocations = new HashMap<Item, ModelResourceLocation>() {{
        put(Items.flintExtractorTip, new ModelResourceLocation(Reference.MODID + ":extractor_tip_flint", "inventory"));
        put(Items.ironExtractorTip, new ModelResourceLocation(Reference.MODID + ":extractor_tip_iron", "inventory"));
        put(Items.quartzExtractorTip, new ModelResourceLocation(Reference.MODID + ":extractor_tip_quartz", "inventory"));
        put(Items.diamondExtractorTip, new ModelResourceLocation(Reference.MODID + ":extractor_tip_diamond", "inventory"));
        put(Items.malgrumExtractorTip, new ModelResourceLocation(Reference.MODID + ":extractor_tip_malgrum", "inventory"));
    }};

    public Item container, tip;
    public IBakedModel baseModel;
    public Map<Item, IBakedModel> containerModels;
    public Map<Item, IBakedModel> tipModels;


    public ExtractorModel(IBakedModel baseModel, Map<Item, IBakedModel> containerModels, Map<Item, IBakedModel> tipModels) {
        this.baseModel = baseModel;
        this.containerModels = containerModels;
        this.tipModels = tipModels;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ExtractorItemOverrides.INSTANCE;
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        ArrayList<BakedQuad> baseQuads = new ArrayList<BakedQuad>(baseModel.getQuads(state, null, rand));
        if (container != null && tip != null) {
            ArrayList<BakedQuad> containerQuads = new ArrayList<BakedQuad>(containerModels.get(container).getQuads(state, null, rand));
            ArrayList<BakedQuad> tipQuads = new ArrayList<BakedQuad>(tipModels.get(tip).getQuads(state, null, rand));
            ArrayList<BakedQuad> newQuads = new ArrayList<BakedQuad>();
            newQuads.addAll(baseQuads);
            newQuads.addAll(containerQuads);
            newQuads.addAll(tipQuads);
            return newQuads.subList(0, newQuads.size());
        }
        return baseQuads.subList(0, baseQuads.size());
    }

    @Override
    public boolean isAmbientOcclusion() {
        return baseModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return baseModel.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return baseModel.getParticleTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType type) {
        Matrix4f m = new Matrix4f();
        m.setIdentity();
        TRSRTransformation trsrt = new TRSRTransformation(m);


        switch(type){
            case FIRST_PERSON_RIGHT_HAND:
                trsrt = new TRSRTransformation(new Vector3f( 0.1f, 0.2f, 0.05f ), new Quat4f(-0.15304594f, -0.6903456f, 0.15304594f, 0.6903456f), new Vector3f(0.68f, 0.68f, 0.68f),  new Quat4f(0.0f, 0.0f, 0.0f, 1.0f));
                break;
            case FIRST_PERSON_LEFT_HAND:
                trsrt = new TRSRTransformation(new Vector3f( 0.1f, 0.2f, 0.05f ), new Quat4f(0.15304594f, -0.6903456f, 0.15304594f, -0.6903456f), new Vector3f(0.68f, 0.68f, 0.68f),  new Quat4f(0.0f, 0.0f, 0.0f, 1.0f));
                break;
            case THIRD_PERSON_RIGHT_HAND:
                trsrt = new TRSRTransformation(new Vector3f( 0.0f, 0.25f, 0.03f ), new Quat4f(-0.3265056f, -0.6272113f, 0.32650554f, 0.62721133f), new Vector3f(0.85f, 0.85f, 0.85f),  new Quat4f(0.0f, 0.0f, 0.0f, 1.0f));
                break;
            case THIRD_PERSON_LEFT_HAND:
                trsrt = new TRSRTransformation(new Vector3f( 0.0f, 0.25f, 0.03f ), new Quat4f(-0.3265056f, 0.6272113f, -0.32650554f, 0.62721133f), new Vector3f(0.85f, 0.85f, 0.85f),  new Quat4f(0.0f, 0.0f, 0.0f, 1.0f));
                break;
            case GROUND:
                trsrt = new TRSRTransformation(new Vector3f( 0.0f, 0.0f, 0.0f ), new Quat4f(0.0f, 0.0f, 0.0f, 1.0f), new Vector3f(0.5f, 0.5f, 0.5f),  new Quat4f(0.0f, 0.0f, 0.0f, 1.0f));
                break;

            default:
                break;
        }

        return Pair.of(this,trsrt.getMatrix());
    }
}
