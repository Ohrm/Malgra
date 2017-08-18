package ohrm.malgra.api;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import ohrm.malgra.Reference;
import ohrm.malgra.api.research.Research;
import ohrm.malgra.research.ResearchFireStarter;

import ohrm.malgra.research.ResearchWaterPlacer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toby on 13/04/2016.
 */
public class MalgraAPI {

    public static Map<Block, Integer> malgraProviders = new HashMap<Block, Integer>();
    
    public static Map<String, Research> researches = new HashMap<String, Research>();
    
    public static Logger apiLogger = LogManager.getFormatterLogger("malgraAPI");

    static {
        addMalgraProvider(Blocks.GRASS, 1);
        addMalgraProvider(Blocks.DIRT, 1);
        addMalgraProviderFromOreDict("stone", 1);
        addMalgraProviderFromOreDict("cobblestone", 1);
        addMalgraProvider(Blocks.GRAVEL, 2);
        addMalgraProviderFromOreDict("sand", 1);
        addMalgraProviderFromOreDict("sandstone", 1);
        addMalgraProvider(Blocks.TALLGRASS, 1);
        addMalgraProvider(Blocks.GRASS_PATH, 1);
        addMalgraProvider(Blocks.NETHERRACK, 2);
        addMalgraProvider(Blocks.NETHER_BRICK, 5);
        addMalgraProviderFromOreDict("oreCoal", 10);
        addMalgraProviderFromOreDict("blockCoal", 90);
        addMalgraProviderFromOreDict("oreIron", 25);
        addMalgraProviderFromOreDict("blockIron", 225);
        addMalgraProviderFromOreDict("oreDiamond", 300);
        addMalgraProviderFromOreDict("blockDiamond", 2700);
        addMalgraProviderFromOreDict("oreEmerald", 500);
        addMalgraProviderFromOreDict("blockEmerald", 4500);
        addMalgraProviderFromOreDict("oreLapis", 30);
        addMalgraProviderFromOreDict("blockLapis", 270);
        addMalgraProviderFromOreDict("oreGold", 50);
        addMalgraProviderFromOreDict("blockGold", 450);
        addMalgraProviderFromOreDict("oreRedstone", 20);
        addMalgraProviderFromOreDict("blockRedstone", 180);
        addMalgraProviderFromOreDict("oreQuartz", 75);
        addMalgraProviderFromOreDict("blockQuartz", 300);
        addMalgraProvider(Blocks.OBSIDIAN, 150);
        addMalgraProvider(Blocks.CHORUS_FLOWER, 10);
        addMalgraProvider(Blocks.RED_FLOWER, 2);
        addMalgraProvider(Blocks.YELLOW_FLOWER, 2);
        addMalgraProvider(Blocks.END_STONE, 5);
        addMalgraProvider(Blocks.GLOWSTONE, 15);
        addMalgraProviderFromOreDict("plankWood", 3);
        addMalgraProviderFromOreDict("logWood", 12);
        addMalgraProviderFromOreDict("slabWood", 1);
        addMalgraProviderFromOreDict("stairWood", 2);
        addMalgraProviderFromOreDict("blockGlass", 4);
        addMalgraProviderFromOreDict("paneGlass", 4);

    }

    public static boolean addMalgraProvider(Block block, int amount){
        if (!malgraProviders.containsKey(block)) {
            malgraProviders.put(block, amount);
            apiLogger.log(Level.DEBUG, "Registered %s as a malgra provider", new ItemStack(block).getDisplayName());
            return true;
        } else {
            apiLogger.log(Level.WARN, "Block %s, already registered as malgra provider", new ItemStack(block).getDisplayName());
            return false;
        }
    }

    public static boolean addMalgraProviderFromOreDict(String block, int amount) {
        List<ItemStack> oreStacks = OreDictionary.getOres(block);
        if (oreStacks.isEmpty()) {
            apiLogger.log(Level.WARN, "Ore %s, is not registered in the ore dictionary", block);
            return false;
        } else {
            List<Block> blocks = new ArrayList<Block>();
            for (int i = 0; i < oreStacks.size(); i++) {
                blocks.add(Block.getBlockFromItem(oreStacks.get(i).getItem()));
            }
            for (int i = 0; i < blocks.size(); i++) {
                if (!malgraProviders.containsKey(blocks.get(i))) {
                    malgraProviders.put(blocks.get(i), amount);
                    apiLogger.log(Level.DEBUG, "Registered %s as a malgra provider", new ItemStack(blocks.get(i)).getDisplayName());
                } else {
                    apiLogger.log(Level.WARN, "Block %s, already registered as malgra provider", new ItemStack(blocks.get(i)).getDisplayName());
                    return false;
                }
            }
            return true;
        }
    }

    @Mod.EventBusSubscriber(modid = Reference.MODID)
    public static class ResearchEvents{
        @SubscribeEvent
        public static void registerEvents(RegistryEvent.Register<Research> event){
            event.getRegistry().register(new ResearchFireStarter().setRegistryName(Reference.MODID, "researchFireStarter"));
            event.getRegistry().register(new ResearchWaterPlacer().setRegistryName(Reference.MODID, "researchWaterPlacer"));
        }
    }

}
