package ohrm.malgra.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.Reference;

public class Blocks {
	
	public static Block manaCraftingTable;
	
	public static void InitBlocks(){
		
		manaCraftingTable = new ManaCraftingTable().setUnlocalizedName("manaCraftingTable").setCreativeTab(MalgraMain.magicTab);
	
		RegisterBlocks();
		
	}
	
	public static void RegisterBlocks(){

		GameRegistry.register(manaCraftingTable, new ResourceLocation(Reference.MODID, "manaCraftingTable"));
		GameRegistry.register(new ItemBlock(manaCraftingTable), new ResourceLocation(Reference.MODID, "manaCraftingTable"));
		
	}
	
	public static void RegisterRenders(){
		
		RegisterRender(manaCraftingTable);
		
	}
	
	private static void RegisterRender(Block block){
		
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		
	}
	
}
