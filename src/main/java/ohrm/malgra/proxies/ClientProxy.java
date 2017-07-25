package ohrm.malgra.proxies;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import ohrm.malgra.Reference;
import ohrm.malgra.blocks.Blocks;
import ohrm.malgra.gui.GuiManaBar;
import ohrm.malgra.items.Items;
import ohrm.malgra.model.ModelBakeHandler;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MODID)
public class ClientProxy extends CommonProxy {

	private final Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		registerRender(Items.magicDust);
		registerRender(Items.manaInjector);
		registerRender(Items.manaInjectorEmpty);
		registerRender(Items.extractor, 0, "extractor_base");
		registerRender(Items.extractor, 1, "extractor_container_tiny");
		registerRender(Items.extractor, 2, "extractor_container_small");
		registerRender(Items.extractor, 3, "extractor_container_medium");
		registerRender(Items.extractor, 4, "extractor_container_large");
		registerRender(Items.extractor, 5, "extractor_container_huge");
		registerRender(Items.extractor, 6, "extractor_tip_flint");
		registerRender(Items.extractor, 7, "extractor_tip_iron");
		registerRender(Items.extractor, 8, "extractor_tip_quartz");
		registerRender(Items.extractor, 9, "extractor_tip_diamond");
		registerRender(Items.extractor, 10, "extractor_tip_malgrum");
		registerRender(Items.tinyContainer, 0, "extractor_container_tiny");
		registerRender(Items.smallContainer, 0, "extractor_container_small");
		registerRender(Items.mediumContainer, 0, "extractor_container_medium");
		registerRender(Items.largeContainer, 0, "extractor_container_large");
		registerRender(Items.hugeContainer, 0, "extractor_container_huge");
		registerRender(Items.flintExtractorTip, 0, "extractor_tip_flint");
		registerRender(Items.ironExtractorTip, 0, "extractor_tip_iron");
		registerRender(Items.quartzExtractorTip, 0, "extractor_tip_quartz");
		registerRender(Items.diamondExtractorTip, 0, "extractor_tip_diamond");
		registerRender(Items.malgrumExtractorTip, 0, "extractor_tip_malgrum");
		registerRender(Items.dimensionTool);
		registerRender(Items.malgraPickaxe);
		registerRender(Items.malgraSword);

		//Blocks
		registerRender(Item.getItemFromBlock(Blocks.manaCraftingTable));
		registerRender(Item.getItemFromBlock(Blocks.specialBlock));
		//RegisterRender(liquidMalgraBlock);
		registerRender(Item.getItemFromBlock(Blocks.researchStoneBrick));
	}

	public static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void registerRender(Item item, int meta, String name) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MODID + ":" + name, "inventory"));
	}
	
	@Override
	public void PreInit(FMLPreInitializationEvent e) {

		MinecraftForge.EVENT_BUS.register(ModelBakeHandler.instance);
		
	}
	
	@Override
	public void Init(FMLInitializationEvent e) {

		registerFluidModel(Blocks.liquidMalgraBlock);

		
	}

	public void registerFluidModel(Block  block) {
		Item item = Item.getItemFromBlock(block);

		ModelBakery.registerItemVariants(item);

		final ModelResourceLocation loc = new ModelResourceLocation(Reference.MODID + ":fluid", "liquidmalgra");

		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return loc;
			}
		});

		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return loc;
			}
		});
	}
	
	@Override
	public void PostInit(FMLPostInitializationEvent e) {
		
		MinecraftForge.EVENT_BUS.register(new GuiManaBar(Minecraft.getMinecraft()));
		
	}
	
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		// Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
		// your packets will not work as expected because you will be getting a
		// client player even when you are on the server!
		// Sounds absurd, but it's true.

		// Solution is to double-check side before returning the player:
		return (ctx.side.isClient() ? mc.player : super.getPlayerEntity(ctx));
	}

	@Override
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return (ctx.side.isClient() ? mc : super.getThreadFromContext(ctx));
	}

}
