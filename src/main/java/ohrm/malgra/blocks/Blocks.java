package ohrm.malgra.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.Reference;
import ohrm.malgra.fluid.Fluids;

public class Blocks {

	@ObjectHolder("malgra:manacraftingtable")
	public static Block manaCraftingTable;
	@ObjectHolder("malgra:liquidMalgraBlock")
	public static Block liquidMalgraBlock;
	@ObjectHolder("malgra:specialblock")
	public static Block specialBlock;
	@ObjectHolder("malgra:researchstonebrick")
	public static Block researchStoneBrick;
	@ObjectHolder("malgra:malgracorebasic")
    public static Block malgraCoreBasic;
	@ObjectHolder("malgra:malgrastorage")
	public static Block malgraStorage;
    @ObjectHolder("malgra:malgrastorageviewer")
    public static Block malgraStorageViewer;

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class BlockEvents{
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event){
			event.getRegistry().register(new ManaCraftingTable().setRegistryName(Reference.MODID, "manacraftingtable").setUnlocalizedName("manacraftingtable").setCreativeTab(MalgraMain.magicTab));
			event.getRegistry().register(new LiquidMalgra(Fluids.liquidMalgra, Material.WATER).setRegistryName(Reference.MODID, "liquidmalgrablock").setUnlocalizedName("liquidmalgrablock"));
            event.getRegistry().register(new SpecialBlock().setRegistryName(Reference.MODID, "specialblock").setUnlocalizedName("specialblock").setCreativeTab(MalgraMain.magicTab));
			event.getRegistry().register(new ResearchStoneBrick().setRegistryName(Reference.MODID, "researchstonebrick").setUnlocalizedName("researchstonebrick"));

            event.getRegistry().register(new MalgraCoreBasic().setRegistryName(Reference.MODID, "malgracorebasic").setUnlocalizedName("malgracorebasic").setCreativeTab(MalgraMain.magicTab));
            event.getRegistry().register(new MalgraStorage().setRegistryName(Reference.MODID, "malgrastorage").setUnlocalizedName("malgrastorage").setCreativeTab(MalgraMain.magicTab));
			event.getRegistry().register(new MalgraStorageViewer().setRegistryName(Reference.MODID, "malgrastorageviewer").setUnlocalizedName("malgrastorageviewer").setCreativeTab(MalgraMain.magicTab));
		}
	}

}
