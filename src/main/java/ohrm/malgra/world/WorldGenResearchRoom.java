package ohrm.malgra.world;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by Toby on 31/07/2016.
 */
public class WorldGenResearchRoom extends WorldGenerator {

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        double dx = position.getX();
        double dy = position.getY();
        double dz = position.getZ();

        if(worldIn.getBlockState(position).getBlock() != ohrm.malgra.blocks.Blocks.researchStoneBrick.getDefaultState().getBlock()){

            for(int x = 0; x < 8; x++){

                for(int z = 0; z < 8; z++){

                    worldIn.setBlockState(new BlockPos(dx + x, dy, dz + z), ohrm.malgra.blocks.Blocks.researchStoneBrick.getDefaultState());
                    worldIn.setBlockState(new BlockPos(dx + x, dy + 1, dz + z), Blocks.TORCH.getDefaultState());
                }

            }
        }

        return false;
    }
}
