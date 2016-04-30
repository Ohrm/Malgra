package ohrm.malgra.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.Reference;
import ohrm.malgra.fluid.Fluids;

public class Blocks {

	public static Block manaCraftingTable, liquidMalgraBlock, specialBlock;

	public static void InitBlocks(){
		
		manaCraftingTable = new ManaCraftingTable().setUnlocalizedName("manaCraftingTable").setCreativeTab(MalgraMain.magicTab);
		liquidMalgraBlock = new LiquidMalgra(Fluids.liquidMalgra, Material.WATER).setUnlocalizedName("liquidMalgraBlock");
		specialBlock = new SpecialBlock().setUnlocalizedName("specialBlock").setCreativeTab(MalgraMain.magicTab);
		RegisterBlocks();
		
	}
	
	public static void RegisterBlocks(){

		GameRegistry.register(manaCraftingTable, new ResourceLocation(Reference.MODID, "manaCraftingTable"));
		GameRegistry.register(new ItemBlock(manaCraftingTable), new ResourceLocation(Reference.MODID, "manaCraftingTable"));
		GameRegistry.register(specialBlock, new ResourceLocation(Reference.MODID, "specialBlock"));
		GameRegistry.register(new ItemBlock(specialBlock), new ResourceLocation(Reference.MODID, "specialBlock"));
		GameRegistry.register(liquidMalgraBlock, new ResourceLocation(Reference.MODID, "liquidMalgraBlock"));
		GameRegistry.register(new ItemBlock(liquidMalgraBlock), new ResourceLocation(Reference.MODID, "liquidMalgraBlock"));
	}
	
	public static void RegisterRenders(){
		RegisterRender(manaCraftingTable);
		RegisterRender(specialBlock);
		//RegisterRender(liquidMalgraBlock);
	}
	
	private static void RegisterRender(Block block){
		
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		
	}
	
}
