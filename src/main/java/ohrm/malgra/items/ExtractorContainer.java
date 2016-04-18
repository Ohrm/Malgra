package ohrm.malgra.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ohrm.malgra.MalgraMain;

/**
 * Created by Toby on 14/04/2016.
 */
public class ExtractorContainer extends Item {

    int storage;
    String prefix;

    public ExtractorContainer(int storage, String prefix) {
        this.storage = storage;
        this.prefix = prefix;
        this.setCreativeTab(MalgraMain.magicTab);
    }

    public int getStorage() {
        return this.storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
