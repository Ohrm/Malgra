package ohrm.malgra.items;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.Reference;
import ohrm.malgra.blocks.Blocks;

import java.util.Set;

public class Items {

	public static Item
			magicDust, manaInjector, manaInjectorEmpty, extractor, tinyContainer, smallContainer,
			mediumContainer, largeContainer, hugeContainer, flintExtractorTip, ironExtractorTip, quartzExtractorTip,
			diamondExtractorTip, malgrumExtractorTip, liquidMalgraBucket, dimensionTool, malgraPickaxe
			;

	public static final Item.ToolMaterial TIP_FLINT = EnumHelper.addToolMaterial("TIP_FLINT", 0, 40, 1.0F, -2.0F, 0);
	public static final Item.ToolMaterial TIP_IRON = EnumHelper.addToolMaterial("TIP_IRON", 0, 500, 3.0F, 0.0F, 0);
	public static final Item.ToolMaterial TIP_QUARTZ = EnumHelper.addToolMaterial("TIP_QUARTZ", 0, 200, 7.0F, 1.0F, 0);
	public static final Item.ToolMaterial TIP_DIAMOND = EnumHelper.addToolMaterial("TIP_DIAMOND", 0, 2000, 5.5F, 1.0F, 0);
	public static final Item.ToolMaterial TIP_MALGRUM = EnumHelper.addToolMaterial("TIP_MALGRUM", 0, -1, 8.0F, 2.0F, 0);
	public static final Item.ToolMaterial MALGRA = EnumHelper.addToolMaterial("MALGRA", 3, -1, 12, 3, 30);

	public static void InitItems(){
		
		magicDust = new MalgraDust().setUnlocalizedName("magicDust").setCreativeTab(MalgraMain.magicTab);
		manaInjector = new ManaInjector().setUnlocalizedName("manaInjector").setCreativeTab(MalgraMain.magicTab);
		manaInjectorEmpty = new Item().setUnlocalizedName("manaInjectorEmpty").setCreativeTab(MalgraMain.magicTab);
		tinyContainer = new ExtractorContainer(100, "tiny").setUnlocalizedName("tinyContainer");
		smallContainer = new ExtractorContainer(500, "small").setUnlocalizedName("smallContainer");
		mediumContainer = new ExtractorContainer(2000, "medium").setUnlocalizedName("mediumContainer");
		largeContainer = new ExtractorContainer(10000, "large").setUnlocalizedName("largeContainer");
		hugeContainer = new ExtractorContainer(50000, "huge").setUnlocalizedName("hugeContainer");
		flintExtractorTip = new ExtractorTip(TIP_FLINT, "flint").setUnlocalizedName("flintExtractorTip");
		ironExtractorTip = new ExtractorTip(TIP_IRON, "iron").setUnlocalizedName("ironExtractorTip");
		quartzExtractorTip = new ExtractorTip(TIP_QUARTZ, "quartz").setUnlocalizedName("quartzExtractorTip");
		diamondExtractorTip = new ExtractorTip(TIP_DIAMOND, "diamond").setUnlocalizedName("diamondExtractorTip");
		malgrumExtractorTip = new ExtractorTip(TIP_MALGRUM, "malgrum").setUnlocalizedName("malgrumExtractorTip");
		extractor = new Extractor().setUnlocalizedName("manaExtractor");
		liquidMalgraBucket = new ItemBucket(Blocks.liquidMalgraBlock).setUnlocalizedName("liquidMalgraBucket");
		dimensionTool = new DimensionTool().setUnlocalizedName("dimensionTool").setCreativeTab(MalgraMain.magicTab);
		malgraPickaxe = new MalgraPickaxe(1, 1.2F, MALGRA, 500).setUnlocalizedName("malgraPickaxe").setCreativeTab(MalgraMain.magicTab);
		RegisterItems();
	}
	
	public static void RegisterItems(){

		GameRegistry.register(magicDust, new ResourceLocation(Reference.MODID, "magicDust"));
		GameRegistry.register(manaInjector, new ResourceLocation(Reference.MODID, "manaInjector"));
		GameRegistry.register(manaInjectorEmpty, new ResourceLocation(Reference.MODID, "manaInjectorEmpty"));
		GameRegistry.register(extractor, new ResourceLocation(Reference.MODID, "manaExtractor"));
		GameRegistry.register(tinyContainer, new ResourceLocation(Reference.MODID, "tinyContainer"));
		GameRegistry.register(smallContainer, new ResourceLocation(Reference.MODID, "smallContainer"));
		GameRegistry.register(mediumContainer, new ResourceLocation(Reference.MODID, "mediumContainer"));
		GameRegistry.register(largeContainer, new ResourceLocation(Reference.MODID, "largeContainer"));
		GameRegistry.register(hugeContainer, new ResourceLocation(Reference.MODID, "hugeContainer"));
		GameRegistry.register(flintExtractorTip, new ResourceLocation(Reference.MODID, "flintExtractorTip"));
		GameRegistry.register(ironExtractorTip, new ResourceLocation(Reference.MODID, "ironExtractorTip"));
		GameRegistry.register(quartzExtractorTip, new ResourceLocation(Reference.MODID, "quartzExtractorTip"));
		GameRegistry.register(diamondExtractorTip, new ResourceLocation(Reference.MODID, "diamondExtractorTip"));
		GameRegistry.register(malgrumExtractorTip, new ResourceLocation(Reference.MODID, "malgrumExtractorTip"));
		GameRegistry.register(liquidMalgraBucket, new ResourceLocation(Reference.MODID, "liquidMalgraBucket"));
		GameRegistry.register(dimensionTool, new ResourceLocation(Reference.MODID, "dimensionTool"));
		GameRegistry.register(malgraPickaxe, new ResourceLocation(Reference.MODID, "malgraPickaxe"));
	}
	
	public static void RegisterRenders(){
		
		RegisterRender(magicDust);
		RegisterRender(manaInjector);
		RegisterRender(manaInjectorEmpty);
		RegisterRender(extractor, 0, "extractor_base");
		RegisterRender(extractor, 1, "extractor_container_tiny");
		RegisterRender(extractor, 2, "extractor_container_small");
		RegisterRender(extractor, 3, "extractor_container_medium");
		RegisterRender(extractor, 4, "extractor_container_large");
		RegisterRender(extractor, 5, "extractor_container_huge");
		RegisterRender(extractor, 6, "extractor_tip_flint");
		RegisterRender(extractor, 7, "extractor_tip_iron");
		RegisterRender(extractor, 8, "extractor_tip_quartz");
		RegisterRender(extractor, 9, "extractor_tip_diamond");
		RegisterRender(extractor, 10, "extractor_tip_malgrum");
		RegisterRender(tinyContainer, 0, "extractor_container_tiny");
		RegisterRender(smallContainer, 0, "extractor_container_small");
		RegisterRender(mediumContainer, 0, "extractor_container_medium");
		RegisterRender(largeContainer, 0, "extractor_container_large");
		RegisterRender(hugeContainer, 0, "extractor_container_huge");
		RegisterRender(flintExtractorTip, 0, "extractor_tip_flint");
		RegisterRender(ironExtractorTip, 0, "extractor_tip_iron");
		RegisterRender(quartzExtractorTip, 0, "extractor_tip_quartz");
		RegisterRender(diamondExtractorTip, 0, "extractor_tip_diamond");
		RegisterRender(malgrumExtractorTip, 0, "extractor_tip_malgrum");
		RegisterRender(liquidMalgraBucket);
		RegisterRender(dimensionTool);
		RegisterRender(malgraPickaxe);
	}
	
	private static void RegisterRender(Item item){
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
				
	}

	private static void RegisterRender(Item item, int meta, String file){

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(Reference.MODID + ":" + file, "inventory"));

	}
	
}
