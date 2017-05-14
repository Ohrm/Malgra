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

	public static Block manaCraftingTable, liquidMalgraBlock, specialBlock, researchStoneBrick;

	public static void InitBlocks(){
		
		manaCraftingTable = new ManaCraftingTable().setRegistryName(Reference.MODID, "manacraftingtable").setUnlocalizedName("manacraftingtable").setCreativeTab(MalgraMain.magicTab);
		liquidMalgraBlock = new LiquidMalgra(Fluids.liquidMalgra, Material.WATER).setRegistryName(Reference.MODID, "liquidMalgraBlock").setUnlocalizedName("liquidMalgraBlock");
		specialBlock = new SpecialBlock().setRegistryName(Reference.MODID, "specialblock").setUnlocalizedName("specialblock").setCreativeTab(MalgraMain.magicTab);
		researchStoneBrick = new ResearchStoneBrick().setRegistryName(Reference.MODID, "researchstonebrick").setUnlocalizedName("researchstonebrick");
		RegisterBlocks();
		
	}
	
	public static void RegisterBlocks(){

		GameRegistry.register(manaCraftingTable);
		GameRegistry.register(new ItemBlock(manaCraftingTable), new ResourceLocation(Reference.MODID, "manacraftingtable"));
		GameRegistry.register(specialBlock);
		GameRegistry.register(new ItemBlock(specialBlock), new ResourceLocation(Reference.MODID, "specialblock"));
		GameRegistry.register(liquidMalgraBlock);
		GameRegistry.register(new ItemBlock(liquidMalgraBlock), new ResourceLocation(Reference.MODID, "liquidMalgraBlock"));
		GameRegistry.register(researchStoneBrick);
		GameRegistry.register(new ItemBlock(researchStoneBrick), new ResourceLocation(Reference.MODID, "researchstonebrick"));
	
	}
	
	public static void RegisterRenders(){
		RegisterRender(manaCraftingTable);
		RegisterRender(specialBlock);
		//RegisterRender(liquidMalgraBlock);
		RegisterRender(researchStoneBrick);
	}
	
	private static void RegisterRender(Block block){
		
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		
	}
	
}
