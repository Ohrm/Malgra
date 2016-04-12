package ohrm.malgra.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.OhrmsMagicMain;
import ohrm.malgra.Reference;

public class Blocks {

	public static Block magicOre;
	
	public static void InitBlocks(){
		
		magicOre = new BlockMagicOre(1, 3, 15).setUnlocalizedName("magicOre").setCreativeTab(OhrmsMagicMain.magicTab).setLightLevel(0.5f);
		
		RegisterBlocks();
		
	}
	
	public static void RegisterBlocks(){
		
		GameRegistry.register(magicOre, new ResourceLocation(Reference.MODID, "magicOre"));
		GameRegistry.register(new ItemBlock(magicOre), new ResourceLocation(Reference.MODID, "magicOre"));
		
	}
	
	public static void RegisterRenders(){
		
		RegisterRender(magicOre);
		
	}
	
	private static void RegisterRender(Block block){
		
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		
	}
	
}
