package ohrm.malgra.fluid;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toby on 14/08/2016.
 */
public class BucketHandler {

    public static BucketHandler INSTANCE = new BucketHandler();
    public Map<Block, Item> buckets = new HashMap<Block, Item>();

    public BucketHandler() {}

    @SubscribeEvent
    public void onBucket(FillBucketEvent event) {
        ItemStack result = fillBucket(event.getWorld(), event.getTarget());

        if (result == null) {
            return;
        }
        event.setFilledBucket(result);
        event.setResult(Event.Result.ALLOW);
    }

    private ItemStack fillBucket(World world, RayTraceResult pos) {
        if (world.getBlockState(pos.getBlockPos()) != null) {
            Block block = world.getBlockState(pos.getBlockPos()).getBlock();

            Item bucket = buckets.get(block);

            if (bucket != null && world.getBlockState(pos.getBlockPos()).getValue(BlockFluidBase.LEVEL) == 0) {
                world.setBlockState(pos.getBlockPos(), Blocks.AIR.getDefaultState());
                return new ItemStack(bucket);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
