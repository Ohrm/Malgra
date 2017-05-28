package ohrm.malgra.research;

import net.minecraft.init.Items;
import ohrm.malgra.api.research.Research;

/**
 * Created by Chris on 13/08/2016.
 */
public class ResearchWaterPlacer extends Research {

    public ResearchWaterPlacer(){

        name = "malgraWaterPlacer";
        researchPoints = 2;
        requirements.add(Items.IRON_INGOT);

    }

    @Override
    public void onUnlock() {

    }
    
}
