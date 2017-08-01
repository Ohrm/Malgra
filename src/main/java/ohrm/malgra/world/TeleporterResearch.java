package ohrm.malgra.world;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
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
        EntityPlayerMP playerMP = (EntityPlayerMP) entity;

        int height = 3;
        int width = 32;
        int depth = 32;

        double dx = 0;
        double dy = 64;
        double dz = 0;

        entity.setPosition(width/2, dy+1, depth/2);

        entity.motionX = entity.motionY = entity.motionZ = 0.0D;
        entity.setPosition(width/2, dy+1, depth/2);

        playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, ResearchDimensions.get(world).researchDimIDs.get(playerMP.getUniqueID().toString()), this);

        entity.setPosition(width/2, dy+1, depth/2);
        
        if(playerMP.world.getBlockState(new BlockPos(dx, dy, dz)).getBlock() != ohrm.malgra.blocks.Blocks.researchStoneBrick.getDefaultState().getBlock()){
        
        	for(int x = 0; x < width; x++){
        	
	        	for(int z = 0; z < depth; z++){

	        		playerMP.world.setBlockState(new BlockPos(dx + x, dy, dz + z), ohrm.malgra.blocks.Blocks.researchStoneBrick.getDefaultState());
	        		if (x == 0 || x == width-1 || ((x!=0 && x!= width-1) && (z == 0 || z == depth-1))){
                        for (int y = 0; y < height; y++) {
                            playerMP.world.setBlockState(new BlockPos(dx + x, dy + y, dz + z), Blocks.BARRIER.getDefaultState());
                        }
                    }
                    if ((x == 1 && z == 1) || (x == 1 && z == depth-2) || (x == width-2 && z == depth-2) || (x == width-2 && z == 1) || (x == (width/2) && z == (depth/2)))
                        playerMP.world.setBlockState(new BlockPos(dx + x, dy + 1, dz + z), Blocks.TORCH.getDefaultState());
                }
	        	
	        }
        }
        
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        
    	if (worldServerInstance.provider.getDimension() == ResearchDimensions.get(entityIn.world).researchDimIDs.get(entityIn.getUniqueID().toString())) {
            
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
