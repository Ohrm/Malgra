package ohrm.malgra;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.api.MalgraAPI;
import ohrm.malgra.blocks.Blocks;
import ohrm.malgra.capabilities.CapabilityMana;
import ohrm.malgra.capabilities.CapabilityResearchActivites;
import ohrm.malgra.capabilities.CapabilityResearchPoints;
import ohrm.malgra.client.sounds.Sounds;
import ohrm.malgra.crafting.CraftingRecipes;
import ohrm.malgra.fluid.Fluids;
import ohrm.malgra.gui.GuiHandler;
import ohrm.malgra.items.Items;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.proxies.CommonProxy;
import ohrm.malgra.tab.MalgraTab;
import ohrm.malgra.tile.TileEntityManaCraftingTable;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class MalgraMain {

	MalgraAPI apiInstance = new MalgraAPI();
	public static Logger logger = LogManager.getFormatterLogger(Reference.MODID);

	@Instance
	public static MalgraMain instance = new MalgraMain();
	
	@SidedProxy(clientSide = "ohrm.malgra.proxies.ClientProxy", serverSide = "ohrm.malgra.proxies.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs magicTab;

    public static MalgraEventHandler eventHandler;

    static{
		FluidRegistry.enableUniversalBucket();
	}

	@EventHandler
	public void PreInit(FMLPreInitializationEvent e){
		
		magicTab = new MalgraTab(CreativeTabs.getNextID(), "malgraTab");
		Fluids.init();
		Blocks.InitBlocks();
		Items.InitItems();

		CapabilityMana.register();
		CapabilityResearchPoints.register();
		CapabilityResearchActivites.register();
		MinecraftForge.EVENT_BUS.register(Items.extractor);

        eventHandler = new MalgraEventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);

		MinecraftForge.EVENT_BUS.register(Items.malgraPickaxe);

		proxy.PreInit(e);
		PacketDispatcher.registerPackets();
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent e){

		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new GuiHandler());
		CraftingRecipes.init();
		GameRegistry.registerTileEntity(TileEntityManaCraftingTable.class, "Mana Crafting Table");
		Sounds.registerSounds();
		proxy.Init(e);
		
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent e){
		logger.log(Level.INFO, "Registered %d malgra providers", MalgraAPI.malgraProviders.size());
		proxy.PostInit(e);
		
	}

    @EventHandler
    public void onServerStarted(FMLServerStartedEvent event){



    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event){
       


    }
	
}
