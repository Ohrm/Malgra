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
import ohrm.malgra.capabilities.CapabilityMana;
import ohrm.malgra.items.Items;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.proxies.CommonProxy;
import ohrm.malgra.tab.MalgraTab;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class MalgraMain {

	@Instance
	public static MalgraMain instance = new MalgraMain();
	
	@SidedProxy(clientSide = "ohrm.malgra.proxies.ClientProxy", serverSide = "ohrm.malgra.proxies.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs magicTab;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent e){
		
		magicTab = new MalgraTab(CreativeTabs.getNextID(), "malgraTab");
		Items.InitItems();
		Blocks.InitBlocks();
		CapabilityMana.register();
		proxy.PreInit(e);
		PacketDispatcher.registerPackets();
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent e){
		MinecraftForge.EVENT_BUS.register(new MalgraEventHandler());

		proxy.Init(e);
		
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent e){

		proxy.PostInit(e);
		
	}
	
}
