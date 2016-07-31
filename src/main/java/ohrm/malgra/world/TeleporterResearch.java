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
        double dy = 64;
        double dz = 0;
        
        entity.setPosition(16, 65, 16);

        entity.motionX = entity.motionY = entity.motionZ = 0.0D;
        entity.setPosition(16, 65, 16);

        playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, Dimensions.researchDimID, this);

        entity.setPosition(16, 65, 16);
        
        if(playerMP.worldObj.getBlockState(new BlockPos(dx, dy, dz)).getBlock() != ohrm.malgra.blocks.Blocks.researchStoneBrick.getDefaultState().getBlock()){
        
        	for(int x = 0; x < 32; x++){
        	
	        	for(int z = 0; z < 32; z++){
	        		
	        		playerMP.worldObj.setBlockState(new BlockPos(dx + x, dy, dz + z), ohrm.malgra.blocks.Blocks.researchStoneBrick.getDefaultState());
	        		playerMP.worldObj.setBlockState(new BlockPos(dx + x, dy + 1, dz + z), Blocks.TORCH.getDefaultState());
	        	}
	        	
	        }
        }
        
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        
    	if (worldServerInstance.provider.getDimension() == Dimensions.researchDimID) {
            
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
