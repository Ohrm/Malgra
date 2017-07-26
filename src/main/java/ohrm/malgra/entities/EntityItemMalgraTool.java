package ohrm.malgra.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import ohrm.malgra.blocks.Blocks;

/**
 * Created by Toby on 14/08/2016.
 */
public class EntityItemMalgraTool extends EntityItem {

    ItemStack replacement;

    public EntityItemMalgraTool(World worldIn) {
        super(worldIn);
    }

    public EntityItemMalgraTool(World worldIn, double x, double y, double z, ItemStack stack, ItemStack replacement) {
        super(worldIn, x, y, z, stack);
        this.replacement = replacement;
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

    @Override
    public void onUpdate() {
        if (isEntityInBlock(this, Blocks.liquidMalgraBlock)) {
            this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ, replacement));
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, 0, 0, 0);
            this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
            this.world.setBlockToAir(this.getPosition());
            this.setDead();
        }
        super.onUpdate();
    }

}
