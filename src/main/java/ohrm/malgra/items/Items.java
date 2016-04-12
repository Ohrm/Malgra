package ohrm.malgra.items;

import org.omg.PortableServer.ServantActivator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.OhrmsMagicMain;
import ohrm.malgra.Reference;

public class Items {

	public static Item magicDust, manaInjector;
	
	public static void InitItems(){
		
		magicDust = new Item().setUnlocalizedName("magicDust").setCreativeTab(OhrmsMagicMain.magicTab);
		manaInjector = new ManaInjector().setUnlocalizedName("manaInjector").setCreativeTab(OhrmsMagicMain.magicTab);
		RegisterItems();
				
	}
	
	public static void RegisterItems(){
		
		GameRegistry.registerItem(magicDust, "magicDust");	
		GameRegistry.registerItem(manaInjector, "manaInjector");	
		
	}
	
	public static void RegisterRenders(){
		
		RegisterRender(magicDust);
		RegisterRender(manaInjector);
		
	}
	
	private static void RegisterRender(Item item){
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
				
	}
	
}
