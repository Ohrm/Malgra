package ohrm.malgra;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import ohrm.malgra.api.MalgraAPI;
import ohrm.malgra.blocks.Blocks;
import ohrm.malgra.capabilities.CapabilityMana;
import ohrm.malgra.gui.GuiHandler;
import ohrm.malgra.items.Items;
import ohrm.malgra.items.ManaExtractor;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.proxies.CommonProxy;
import ohrm.malgra.tab.MalgraTab;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Ref;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class MalgraMain {

	MalgraAPI apiInstance = new MalgraAPI();
	public static Logger logger = LogManager.getFormatterLogger(Reference.MODID);

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
		MinecraftForge.EVENT_BUS.register(Items.manaExtractor);
		proxy.PreInit(e);
		PacketDispatcher.registerPackets();
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent e){
		MinecraftForge.EVENT_BUS.register(new MalgraEventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new GuiHandler());
		proxy.Init(e);
		
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent e){
		logger.log(Level.INFO, "Registered %d malgra providers", MalgraAPI.malgraProviders.size());
		proxy.PostInit(e);
		
	}
	
}
