package ohrm.malgra;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.api.MalgraAPI;
import ohrm.malgra.capabilities.CapabilityMana;
import ohrm.malgra.capabilities.CapabilityResearchActivites;
import ohrm.malgra.capabilities.CapabilityResearchPoints;
import ohrm.malgra.client.sounds.Sounds;
import ohrm.malgra.fluid.Fluids;
import ohrm.malgra.gui.GuiHandler;
import ohrm.malgra.items.Items;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.proxies.IMalgraProxy;
import ohrm.malgra.registries.MalgraRegistryManager;
import ohrm.malgra.tab.MalgraTab;
import ohrm.malgra.tile.TileEntityManaCraftingTable;
import ohrm.malgra.tile.TileMalgraCore;
import ohrm.malgra.tile.TileMalgraStorage;
import ohrm.malgra.tile.TileMalgraStorageViewer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class MalgraMain {

	MalgraAPI apiInstance = new MalgraAPI();
	public static Logger logger = LogManager.getFormatterLogger(Reference.MODID);

	@Instance
	public static MalgraMain instance = new MalgraMain();
	
	@SidedProxy(clientSide = "ohrm.malgra.proxies.ClientProxy", serverSide = "ohrm.malgra.proxies.ServerProxy")
	public static IMalgraProxy proxy;
	
	public static CreativeTabs magicTab;

    public static MalgraEventHandler eventHandler;

    static{
		FluidRegistry.enableUniversalBucket();
	}

	@EventHandler
	public void PreInit(FMLPreInitializationEvent e){

		MalgraRegistryManager.CreateRegistries();

		magicTab = new MalgraTab(CreativeTabs.getNextID(), "malgraTab");
		Fluids.init();

		CapabilityMana.register();
		CapabilityResearchPoints.register();
		CapabilityResearchActivites.register();

        eventHandler = new MalgraEventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);

		proxy.PreInit(e);
		PacketDispatcher.registerPackets();
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent e){

		MinecraftForge.EVENT_BUS.register(Items.extractor);
		MinecraftForge.EVENT_BUS.register(Items.malgraPickaxe);
		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new GuiHandler());
		GameRegistry.registerTileEntity(TileEntityManaCraftingTable.class, new ResourceLocation(Reference.MODID, "Mana Crafting Table"));
		GameRegistry.registerTileEntity(TileMalgraCore.class, new ResourceLocation(Reference.MODID, "Malgra Core"));
		GameRegistry.registerTileEntity(TileMalgraStorage.class, new ResourceLocation(Reference.MODID, "Malgra Storage"));
		GameRegistry.registerTileEntity(TileMalgraStorageViewer.class, new ResourceLocation(Reference.MODID, "Malgra Storage Viewer"));
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
