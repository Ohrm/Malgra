package ohrm.malgra.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import ohrm.malgra.blocks.Blocks;

/**
 * Created by Toby on 14/08/2016.
 */
public class MalgraDust extends Item {

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        if (isEntityInBlock(entityItem, Blocks.liquidMalgraBlock)) {
            entityItem.world.setBlockState(entityItem.getPosition().down(), Blocks.liquidMalgraBlock.getDefaultState());
            entityItem.setDead();
        }
        return super.onEntityItemUpdate(entityItem);
    }

    public boolean isEntityInBlock(Entity entity, Block block) {
        AxisAlignedBB bb = entity.getEntityBoundingBox().expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D);
        int i = MathHelper.floor(bb.minX);
        int j = MathHelper.ceil(bb.maxX);
        int k = MathHelper.floor(bb.minY);
        int l = MathHelper.ceil(bb.maxY);
        int i1 = MathHelper.floor(bb.minZ);
        int j1 = MathHelper.ceil(bb.maxZ);
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    if (entity.world.getBlockState(blockpos$pooledmutableblockpos.setPos(k1, l1, i2)).getBlock() == block) {
                        blockpos$pooledmutableblockpos.release();
                        return true;
                    }
                }
            }
        }

        blockpos$pooledmutableblockpos.release();
        return false;
    }
}
