package ohrm.malgra.api.network;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

public interface IMalgraStorage {

    boolean insertItems(Item item, int count);

    ItemStack extractItems(Item item, int count);

    int getTotalItemCount();

    int getUniqueItemCount();

    int getCurrentItemCount();

    Map<Item, Integer> getInventory();

}
