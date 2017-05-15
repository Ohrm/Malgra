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
			diamondExtractorTip, malgrumExtractorTip, liquidMalgraBucket, dimensionTool, malgraPickaxe, malgraSword,
			malgraAxe, malgraShovel, malgraHoe
			;

	public static final Item.ToolMaterial TIP_FLINT = EnumHelper.addToolMaterial("TIP_FLINT", 0, 40, 1.0F, -2.0F, 0);
	public static final Item.ToolMaterial TIP_IRON = EnumHelper.addToolMaterial("TIP_IRON", 0, 500, 3.0F, 0.0F, 0);
	public static final Item.ToolMaterial TIP_QUARTZ = EnumHelper.addToolMaterial("TIP_QUARTZ", 0, 200, 7.0F, 1.0F, 0);
	public static final Item.ToolMaterial TIP_DIAMOND = EnumHelper.addToolMaterial("TIP_DIAMOND", 0, 2000, 5.5F, 1.0F, 0);
	public static final Item.ToolMaterial TIP_MALGRUM = EnumHelper.addToolMaterial("TIP_MALGRUM", 0, -1, 8.0F, 2.0F, 0);
	public static final Item.ToolMaterial MALGRA = EnumHelper.addToolMaterial("MALGRA", 3, -1, 12, 3, 30);

	public static void InitItems(){
		
		magicDust = new MalgraDust().setRegistryName(Reference.MODID, "magicdust").setUnlocalizedName("magicdust").setCreativeTab(MalgraMain.magicTab);
		manaInjector = new ManaInjector().setRegistryName(Reference.MODID, "manainjector").setUnlocalizedName("manainjector").setCreativeTab(MalgraMain.magicTab);
		manaInjectorEmpty = new Item().setRegistryName(Reference.MODID, "manainjectorempty").setUnlocalizedName("manainjectorempty").setCreativeTab(MalgraMain.magicTab);
		tinyContainer = new ExtractorContainer(100, "tiny").setRegistryName(Reference.MODID, "tinyContainer").setUnlocalizedName("tinyContainer");
		smallContainer = new ExtractorContainer(500, "small").setRegistryName(Reference.MODID, "smallContainer").setUnlocalizedName("smallContainer");
		mediumContainer = new ExtractorContainer(2000, "medium").setRegistryName(Reference.MODID, "mediumContainer").setUnlocalizedName("mediumContainer");
		largeContainer = new ExtractorContainer(10000, "large").setRegistryName(Reference.MODID, "largeContainer").setUnlocalizedName("largeContainer");
		hugeContainer = new ExtractorContainer(50000, "huge").setRegistryName(Reference.MODID, "hugeContainer").setUnlocalizedName("hugeContainer");
		flintExtractorTip = new ExtractorTip(TIP_FLINT, "flint").setRegistryName(Reference.MODID, "flintExtractorTip").setUnlocalizedName("flintExtractorTip");
		ironExtractorTip = new ExtractorTip(TIP_IRON, "iron").setRegistryName(Reference.MODID, "ironExtractorTip").setUnlocalizedName("ironExtractorTip");
		quartzExtractorTip = new ExtractorTip(TIP_QUARTZ, "quartz").setRegistryName(Reference.MODID, "quartzExtractorTip").setUnlocalizedName("quartzExtractorTip");
		diamondExtractorTip = new ExtractorTip(TIP_DIAMOND, "diamond").setRegistryName(Reference.MODID, "diamondExtractorTip").setUnlocalizedName("diamondExtractorTip");
		malgrumExtractorTip = new ExtractorTip(TIP_MALGRUM, "malgrum").setRegistryName(Reference.MODID, "malgrumExtractorTip").setUnlocalizedName("malgrumExtractorTip");
		extractor = new Extractor().setRegistryName(Reference.MODID, "manaExtractor").setUnlocalizedName("manaExtractor");
		liquidMalgraBucket = new ItemBucket(Blocks.liquidMalgraBlock).setRegistryName(Reference.MODID, "liquidMalgraBucket").setUnlocalizedName("liquidMalgraBucket");
		dimensionTool = new DimensionTool().setRegistryName(Reference.MODID, "dimensionTool").setUnlocalizedName("dimensionTool").setCreativeTab(MalgraMain.magicTab);
		malgraPickaxe = new MalgraPickaxe(1, 1.2F, MALGRA, 500).setRegistryName(Reference.MODID, "malgrapickaxe").setUnlocalizedName("malgrapickaxe").setCreativeTab(MalgraMain.magicTab);
		malgraSword = new MalgraSword(5, 1.6F, MALGRA, 500).setRegistryName(Reference.MODID, "malgrasword").setUnlocalizedName("malgrasword").setCreativeTab(MalgraMain.magicTab);
		RegisterItems();
	}
	
	public static void RegisterItems(){

		GameRegistry.register(magicDust);
		GameRegistry.register(manaInjector);
		GameRegistry.register(manaInjectorEmpty);
		GameRegistry.register(extractor);
		GameRegistry.register(tinyContainer);
		GameRegistry.register(smallContainer);
		GameRegistry.register(mediumContainer);
		GameRegistry.register(largeContainer);
		GameRegistry.register(hugeContainer);
		GameRegistry.register(flintExtractorTip);
		GameRegistry.register(ironExtractorTip);
		GameRegistry.register(quartzExtractorTip);
		GameRegistry.register(diamondExtractorTip);
		GameRegistry.register(malgrumExtractorTip);
		GameRegistry.register(liquidMalgraBucket);
		GameRegistry.register(dimensionTool);
		GameRegistry.register(malgraPickaxe);
		GameRegistry.register(malgraSword);
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
		RegisterRender(malgraSword);
	}
	
	private static void RegisterRender(Item item){
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
				
	}

	private static void RegisterRender(Item item, int meta, String file){

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(Reference.MODID + ":" + file, "inventory"));

	}
	
}
