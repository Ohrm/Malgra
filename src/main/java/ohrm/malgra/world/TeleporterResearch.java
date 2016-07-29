package ohrm.malgra.world;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

/**
 * Created by Toby on 29/07/2016.
 */
public class TeleporterResearch extends Teleporter {

    private final WorldServer worldServerInstance;
    /** A private Random() function in Teleporter */
    private final Random random;
    private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap(4096);

    public TeleporterResearch(WorldServer worldIn) {
        super(worldIn);
        this.worldServerInstance = worldIn;
        this.random = new Random(worldIn.getSeed());
    }

    public void teleport(Entity entity, World world) {
        System.out.print("Teleport!");
        EntityPlayerMP playerMP = (EntityPlayerMP) entity;
        double dx = 0;
        double dy = 0;
        double dz = 0;

        dx = world.getWorldInfo().getSpawnX();
        dy = world.getWorldInfo().getSpawnY();
        dz = world.getWorldInfo().getSpawnZ();

        dx = dx + 0.5d;
        dy = dy + 1.0d;
        dz = dz + 0.5d;
        entity.setPosition(dx, dy, dz);

        entity.motionX = entity.motionY = entity.motionZ = 0.0D;
        entity.setPosition(dx, dy, dz);

        playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, Dimensions.researchDimID, this);

        entity.setPosition(dx, dy, dz);
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        if (worldServerInstance.provider.getDimension() == Dimensions.researchDimID) {
            //int x = MathHelper.floor_double(entityIn.posX);
            //int y = MathHelper.floor_double(entityIn.posY) - 1;
            //int z = MathHelper.floor_double(entityIn.posZ);
            for (int z = -1; z < 1; z++) {
                for (int x = -1; x < 1; x++) {
                    this.worldServerInstance.setBlockState(new BlockPos(x, entityIn.posY - 1, z), Blocks.BEDROCK.getDefaultState());

                }
            }
            entityIn.setLocationAndAngles(entityIn.posX, entityIn.posY, entityIn.posZ, entityIn.rotationYaw, 0.0F);

            entityIn.motionX = 0.0D;
            entityIn.motionY = 0.0D;
            entityIn.motionZ = 0.0D;
        }

    }

    @Override
    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
        return false;
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        return false;
    }

    /**
     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
     * WorldServer.getTotalWorldTime() value.
     */
    @Override
    public void removeStalePortalLocations(long worldTime)
    {
        if (worldTime % 100L == 0L)
        {
            long i = worldTime - 300L;
            ObjectIterator<Teleporter.PortalPosition> objectiterator = this.destinationCoordinateCache.values().iterator();

            while (objectiterator.hasNext())
            {
                Teleporter.PortalPosition teleporter$portalposition = (Teleporter.PortalPosition)objectiterator.next();

                if (teleporter$portalposition == null || teleporter$portalposition.lastUpdateTime < i)
                {
                    objectiterator.remove();
                }
            }
        }
    }

    public class PortalPosition extends BlockPos
    {
        /** The worldtime at which this PortalPosition was last verified */
        public long lastUpdateTime;

        public PortalPosition(BlockPos pos, long lastUpdate)
        {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.lastUpdateTime = lastUpdate;
        }
    }
}
