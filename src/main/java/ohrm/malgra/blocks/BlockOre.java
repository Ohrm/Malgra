package ohrm.malgra.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOre extends Block{

	public BlockOre(int toolLevel, int hardness, int resistance) {
		
		super(Material.rock);
		setHarvestLevel("pickaxe", toolLevel);
		setHardness(hardness);
		setResistance(resistance);
		
	}
	
}