package ohrm.malgra.research;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import ohrm.malgra.api.research.Research;

/**
 * Created by Chris on 13/08/2016.
 */
public class ResearchFireStarter extends Research {

    public ResearchFireStarter(){
        super(1, new Item[]{Items.STICK}, null);
    }

    @Override
    public void onUnlock() {

    }

}
