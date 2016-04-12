package ohrm.malgra;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ohrm.malgra.blocks.Blocks;
import ohrm.malgra.items.Items;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.proxies.CommonProxy;
import ohrm.malgra.tab.OhrmsMagicTab;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class OhrmsMagicMain {

	@Instance
	public static OhrmsMagicMain instance = new OhrmsMagicMain();
	
	@SidedProxy(clientSide = "ohrm.magic.proxies.ClientProxy", serverSide = "ohrm.magic.proxies.ServerProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs magicTab;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent e){
		
		magicTab = new OhrmsMagicTab(CreativeTabs.getNextID(), "ohrmsMagicTab");
		Items.InitItems();
		Blocks.InitBlocks();
		proxy.PreInit(e);
		PacketDispatcher.registerPackets();
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent e){
		MinecraftForge.EVENT_BUS.register(new OhrmsMagicEventHandler());

		proxy.Init(e);
		
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent e){

		proxy.PostInit(e);
		
	}
	
}
