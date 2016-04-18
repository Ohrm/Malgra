package ohrm.malgra.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ohrm.malgra.Reference;
import ohrm.malgra.blocks.Blocks;
import ohrm.malgra.gui.GuiManaBar;
import ohrm.malgra.items.Items;
import ohrm.malgra.model.ModelBakeHandler;

public class ClientProxy extends CommonProxy {

	private final Minecraft mc = Minecraft.getMinecraft();

	
	@Override
	public void PreInit(FMLPreInitializationEvent e) {
		
		super.PreInit(e);
		
	}
	
	@Override
	public void Init(FMLInitializationEvent e) {
		
		Items.RegisterRenders();

		ModelLoader.setCustomModelResourceLocation(Items.extractor, 0, new ModelResourceLocation(Reference.MODID + ":extractor_base", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Items.extractor, 1, new ModelResourceLocation(Reference.MODID + ":extractor_container_tiny", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Items.extractor, 2, new ModelResourceLocation(Reference.MODID + ":extractor_container_small", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Items.extractor, 3, new ModelResourceLocation(Reference.MODID + ":extractor_container_medium", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Items.extractor, 4, new ModelResourceLocation(Reference.MODID + ":extractor_container_large", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Items.extractor, 5, new ModelResourceLocation(Reference.MODID + ":extractor_container_huge", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Items.extractor, 6, new ModelResourceLocation(Reference.MODID + ":extractor_tip_flint", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Items.extractor, 7, new ModelResourceLocation(Reference.MODID + ":extractor_tip_iron", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Items.extractor, 8, new ModelResourceLocation(Reference.MODID + ":extractor_tip_quartz", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Items.extractor, 9, new ModelResourceLocation(Reference.MODID + ":extractor_tip_diamond", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Items.extractor, 10, new ModelResourceLocation(Reference.MODID + ":extractor_tip_malgrum", "inventory"));

		MinecraftForge.EVENT_BUS.register(ModelBakeHandler.instance);

		Blocks.RegisterRenders();
		super.Init(e);
		
	}
	
	@Override
	public void PostInit(FMLPostInitializationEvent e) {
		
		MinecraftForge.EVENT_BUS.register(new GuiManaBar(Minecraft.getMinecraft()));
		super.PostInit(e);
		
	}
	
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		// Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
		// your packets will not work as expected because you will be getting a
		// client player even when you are on the server!
		// Sounds absurd, but it's true.

		// Solution is to double-check side before returning the player:
		return (ctx.side.isClient() ? mc.thePlayer : super.getPlayerEntity(ctx));
	}

	@Override
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return (ctx.side.isClient() ? mc : super.getThreadFromContext(ctx));
	}
	
}
