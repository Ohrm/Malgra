package ohrm.malgra.api;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toby on 13/04/2016.
 */
public class MalgraAPI {

    public static Map<Block, Integer> malgraProviders = new HashMap<Block, Integer>();


    static {
        addMalgraProvider(Blocks.GRASS, 1);
        addMalgraProvider(Blocks.DIRT, 1);
        addMalgraProvider(Blocks.STONE, 1);
        addMalgraProvider(Blocks.COBBLESTONE, 1);
        addMalgraProvider(Blocks.GRAVEL, 2);
        addMalgraProvider(Blocks.SAND, 1);
        addMalgraProvider(Blocks.SANDSTONE, 1);
        addMalgraProvider(Blocks.TALLGRASS, 1);
        addMalgraProvider(Blocks.GRASS_PATH, 1);
        addMalgraProvider(Blocks.NETHERRACK, 2);
        addMalgraProvider(Blocks.NETHER_BRICK, 5);
        addMalgraProvider(Blocks.COAL_ORE, 10);
        addMalgraProvider(Blocks.COAL_BLOCK, 90);
        addMalgraProvider(Blocks.IRON_ORE, 25);
        addMalgraProvider(Blocks.IRON_BLOCK, 225);
        addMalgraProvider(Blocks.DIAMOND_ORE, 300);
        addMalgraProvider(Blocks.DIAMOND_BLOCK, 2700);
        addMalgraProvider(Blocks.EMERALD_ORE, 500);
        addMalgraProvider(Blocks.EMERALD_BLOCK, 4500);
        addMalgraProvider(Blocks.LAPIS_ORE, 30);
        addMalgraProvider(Blocks.LAPIS_BLOCK, 270);
        addMalgraProvider(Blocks.GOLD_ORE, 50);
        addMalgraProvider(Blocks.GOLD_BLOCK, 450);
        addMalgraProvider(Blocks.REDSTONE_ORE, 20);
        addMalgraProvider(Blocks.REDSTONE_BLOCK, 180);
        addMalgraProvider(Blocks.QUARTZ_ORE, 75);
        addMalgraProvider(Blocks.QUARTZ_BLOCK, 300);
        addMalgraProvider(Blocks.OBSIDIAN, 150);
        addMalgraProvider(Blocks.CHORUS_FLOWER, 10);
        addMalgraProvider(Blocks.RED_FLOWER, 2);
        addMalgraProvider(Blocks.YELLOW_FLOWER, 2);
        addMalgraProvider(Blocks.END_STONE, 5);
    }

    public static boolean addMalgraProvider(Block block, int amount){
        if (!malgraProviders.containsKey(block)) {
            malgraProviders.put(block, amount);
            return true;
        } else {
            FMLLog.log(Level.WARN, "Block %s, already registered as malgra provider", new ItemStack(block).getDisplayName());
            return false;
        }
    }

}
