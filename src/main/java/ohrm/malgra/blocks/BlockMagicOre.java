package ohrm.malgra.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import ohrm.malgra.items.Items;

public class BlockMagicOre extends BlockOre {

	public BlockMagicOre(int toolLevel, int hardness, int resistance) {
		
		super(toolLevel, hardness, resistance);
		
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		
		return Items.magicDust;
	
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		// TODO Auto-generated method stub
		return 4 + random.nextInt(2) + (1 * fortune);
	}

}
