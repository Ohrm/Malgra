package ohrm.malgra.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.Reference;

public class Items {

	public static Item magicDust, manaInjector, manaInjectorEmpty;
	
	public static void InitItems(){
		
		magicDust = new Item().setUnlocalizedName("magicDust").setCreativeTab(MalgraMain.magicTab);
		manaInjector = new ManaInjector().setUnlocalizedName("manaInjector").setCreativeTab(MalgraMain.magicTab);
		manaInjectorEmpty = new Item().setUnlocalizedName("manaInjectorEmpty").setCreativeTab(MalgraMain.magicTab);
		RegisterItems();
				
	}
	
	public static void RegisterItems(){
		
		GameRegistry.register(magicDust, new ResourceLocation(Reference.MODID, "magicDust"));
		GameRegistry.register(manaInjector, new ResourceLocation(Reference.MODID, "manaInjector"));
		GameRegistry.register(manaInjectorEmpty, new ResourceLocation(Reference.MODID, "manaInjectorEmpty"));
	}
	
	public static void RegisterRenders(){
		
		RegisterRender(magicDust);
		RegisterRender(manaInjector);
		RegisterRender(manaInjectorEmpty);
		
	}
	
	private static void RegisterRender(Item item){
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
				
	}
	
}
