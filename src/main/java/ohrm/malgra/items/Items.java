package ohrm.malgra.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.Reference;
import ohrm.malgra.blocks.Blocks;

public class Items {

	@ObjectHolder("malgra:magicdust")
	public static Item magicDust;
	@ObjectHolder("malgra:manainjector")
	public static Item manaInjector;
	@ObjectHolder("malgra:manainjectorempty")
	public static Item manaInjectorEmpty;
	@ObjectHolder("malgra:manaExtractor")
	public static Item extractor;
	@ObjectHolder("malgra:tinyContainer")
	public static Item tinyContainer;
	@ObjectHolder("malgra:smallContainer")
	public static Item smallContainer;
	@ObjectHolder("malgra:mediumContainer")
	public static Item mediumContainer;
	@ObjectHolder("malgra:largeContainer")
	public static Item largeContainer;
	@ObjectHolder("malgra:hugeContainer")
	public static Item hugeContainer;
	@ObjectHolder("malgra:flintExtractorTip")
	public static Item flintExtractorTip;
	@ObjectHolder("malgra:ironExtractorTip")
	public static Item ironExtractorTip;
	@ObjectHolder("malgra:quartzExtractorTip")
	public static Item quartzExtractorTip;
	@ObjectHolder("malgra:diamondExtractorTip")
	public static Item diamondExtractorTip;
	@ObjectHolder("malgra:malgrumExtractorTip")
	public static Item malgrumExtractorTip;
	@ObjectHolder("malgra:dimensionTool")
	public static Item dimensionTool;
	@ObjectHolder("malgra:malgrapickaxe")
	public static Item malgraPickaxe;
	@ObjectHolder("malgra:malgrasword")
	public static Item malgraSword;

	@ObjectHolder("malgra:liquidmalgrablock")
	public static Item liquidMalgraItem;

	public static final Item.ToolMaterial TIP_FLINT = EnumHelper.addToolMaterial("TIP_FLINT", 0, 40, 1.0F, -2.0F, 0);
	public static final Item.ToolMaterial TIP_IRON = EnumHelper.addToolMaterial("TIP_IRON", 0, 500, 3.0F, 0.0F, 0);
	public static final Item.ToolMaterial TIP_QUARTZ = EnumHelper.addToolMaterial("TIP_QUARTZ", 0, 200, 7.0F, 1.0F, 0);
	public static final Item.ToolMaterial TIP_DIAMOND = EnumHelper.addToolMaterial("TIP_DIAMOND", 0, 2000, 5.5F, 1.0F, 0);
	public static final Item.ToolMaterial TIP_MALGRUM = EnumHelper.addToolMaterial("TIP_MALGRUM", 0, -1, 8.0F, 2.0F, 0);
	public static final Item.ToolMaterial MALGRA = EnumHelper.addToolMaterial("MALGRA", 3, -1, 12, 3, 30);

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class ItemEvents{
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event){
			event.getRegistry().register(new MalgraDust().setRegistryName(Reference.MODID, "magicdust").setUnlocalizedName("magicdust").setCreativeTab(MalgraMain.magicTab));
			event.getRegistry().register(new ManaInjector().setRegistryName(Reference.MODID, "manainjector").setUnlocalizedName("manainjector").setCreativeTab(MalgraMain.magicTab));
			event.getRegistry().register(new Item().setRegistryName(Reference.MODID, "manainjectorempty").setUnlocalizedName("manainjectorempty").setCreativeTab(MalgraMain.magicTab));
			event.getRegistry().register(new ExtractorContainer(100, "tiny").setRegistryName(Reference.MODID, "tinyContainer").setUnlocalizedName("tinyContainer"));
			event.getRegistry().register(new ExtractorContainer(500, "small").setRegistryName(Reference.MODID, "smallContainer").setUnlocalizedName("smallContainer"));
			event.getRegistry().register(new ExtractorContainer(2000, "medium").setRegistryName(Reference.MODID, "mediumContainer").setUnlocalizedName("mediumContainer"));
			event.getRegistry().register(new ExtractorContainer(10000, "large").setRegistryName(Reference.MODID, "largeContainer").setUnlocalizedName("largeContainer"));
			event.getRegistry().register(new ExtractorContainer(50000, "huge").setRegistryName(Reference.MODID, "hugeContainer").setUnlocalizedName("hugeContainer"));
			event.getRegistry().register(new ExtractorTip(TIP_FLINT, "flint").setRegistryName(Reference.MODID, "flintExtractorTip").setUnlocalizedName("flintExtractorTip"));
			event.getRegistry().register(new ExtractorTip(TIP_IRON, "iron").setRegistryName(Reference.MODID, "ironExtractorTip").setUnlocalizedName("ironExtractorTip"));
			event.getRegistry().register(new ExtractorTip(TIP_QUARTZ, "quartz").setRegistryName(Reference.MODID, "quartzExtractorTip").setUnlocalizedName("quartzExtractorTip"));
			event.getRegistry().register(new ExtractorTip(TIP_DIAMOND, "diamond").setRegistryName(Reference.MODID, "diamondExtractorTip").setUnlocalizedName("diamondExtractorTip"));
			event.getRegistry().register(new ExtractorTip(TIP_MALGRUM, "malgrum").setRegistryName(Reference.MODID, "malgrumExtractorTip").setUnlocalizedName("malgrumExtractorTip"));
			event.getRegistry().register(new Extractor().setRegistryName(Reference.MODID, "manaExtractor").setUnlocalizedName("manaExtractor"));
			event.getRegistry().register(new DimensionTool().setRegistryName(Reference.MODID, "dimensionTool").setUnlocalizedName("dimensionTool").setCreativeTab(MalgraMain.magicTab));
			event.getRegistry().register(new MalgraPickaxe(1, 1.2F, MALGRA, 500).setRegistryName(Reference.MODID, "malgrapickaxe").setUnlocalizedName("malgrapickaxe").setCreativeTab(MalgraMain.magicTab));
			event.getRegistry().register(new MalgraSword(5, 1.6F, MALGRA, 500).setRegistryName(Reference.MODID, "malgrasword").setUnlocalizedName("malgrasword").setCreativeTab(MalgraMain.magicTab));

			//Blocks
			event.getRegistry().register(new ItemBlock(Blocks.manaCraftingTable).setRegistryName(Blocks.manaCraftingTable.getRegistryName()));
			event.getRegistry().register(new ItemBlock(Blocks.specialBlock).setRegistryName(Blocks.specialBlock.getRegistryName()));
			event.getRegistry().register(new ItemBlock(Blocks.liquidMalgraBlock).setRegistryName(Blocks.liquidMalgraBlock.getRegistryName()));
			event.getRegistry().register(new ItemBlock(Blocks.researchStoneBrick).setRegistryName(Blocks.researchStoneBrick.getRegistryName()));
		}
	}
	
}
