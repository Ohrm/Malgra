package ohrm.malgra.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.Reference;

public class Items {

	public static Item magicDust, manaInjector, manaInjectorEmpty, extractor, tinyContainer, basicExtractorTip;

	public static final Item.ToolMaterial BASICEXTRACTOR = EnumHelper.addToolMaterial("BASICEXTRACTOR", 0, 40, 1.0F, -2.0F, 0);
	
	public static void InitItems(){
		
		magicDust = new Item().setUnlocalizedName("magicDust").setCreativeTab(MalgraMain.magicTab);
		manaInjector = new ManaInjector().setUnlocalizedName("manaInjector").setCreativeTab(MalgraMain.magicTab);
		manaInjectorEmpty = new Item().setUnlocalizedName("manaInjectorEmpty").setCreativeTab(MalgraMain.magicTab);
		tinyContainer = new BasicContainer(100, "tiny").setUnlocalizedName("tinyContainer");
		basicExtractorTip = new BasicExtractorTip(BASICEXTRACTOR, "basic").setUnlocalizedName("basicExtractorTip");
		extractor = new Extractor((ExtractorContainer) tinyContainer, (ExtractorTip) basicExtractorTip).setUnlocalizedName("manaExtractor");
		RegisterItems();
				
	}
	
	public static void RegisterItems(){

		GameRegistry.register(magicDust, new ResourceLocation(Reference.MODID, "magicDust"));
		GameRegistry.register(manaInjector, new ResourceLocation(Reference.MODID, "manaInjector"));
		GameRegistry.register(manaInjectorEmpty, new ResourceLocation(Reference.MODID, "manaInjectorEmpty"));
		GameRegistry.register(extractor, new ResourceLocation(Reference.MODID, "manaExtractor"));
		GameRegistry.register(tinyContainer, new ResourceLocation(Reference.MODID, "tinyContainer"));
		GameRegistry.register(basicExtractorTip, new ResourceLocation(Reference.MODID, "basicExtractorTip"));
	}
	
	public static void RegisterRenders(){
		
		RegisterRender(magicDust);
		RegisterRender(manaInjector);
		RegisterRender(manaInjectorEmpty);
		RegisterRender(extractor);
		RegisterRender(tinyContainer);
		RegisterRender(basicExtractorTip);
	}
	
	private static void RegisterRender(Item item){
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
				
	}
	
}
