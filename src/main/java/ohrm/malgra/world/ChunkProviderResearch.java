package ohrm.malgra.world;

import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Toby on 29/07/2016.
 */
public class ChunkProviderResearch implements IChunkGenerator {

    /** RNG. */
    private final Random rand;
    /** Reference to the World object. */
    private final World worldObj;
    /** are map structures going to be generated (e.g. strongholds) */
    /** The biomes that are used to generate the chunk */
    private Biome[] biomesForGeneration;
    // temporary variables used during event handling
    private int chunkX = 0;
    private int chunkZ = 0;

    public ChunkProviderResearch(World worldObjIn, boolean mapFeaturesEnabledIn, long seed)
    {
        this.worldObj = worldObjIn;
        this.rand = new Random(seed);
    }

    public Chunk generateChunk(int x, int z)
    {
        this.chunkX = x; this.chunkZ = z;
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    public void populate(int x, int z)
    {
        BlockFalling.fallInstantly = true;
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.worldObj, this.rand, x, z, false);
        BlockFalling.fallInstantly = false;
    }

    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        return false;
    }

    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        return new ArrayList<Biome.SpawnListEntry>();
    }

    public void recreateStructures(Chunk chunkIn, int x, int z)
    {
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }
}
