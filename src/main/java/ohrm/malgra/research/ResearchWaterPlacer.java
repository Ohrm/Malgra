package ohrm.malgra.research;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import ohrm.malgra.api.research.Research;

/**
 * Created by Chris on 13/08/2016.
 */
public class ResearchWaterPlacer extends Research {

    public ResearchWaterPlacer(){
        super(2, new Item[]{Items.IRON_INGOT}, null);

    }

    @Override
    public void onUnlock() {

    }
    
}
