package ohrm.malgra.research;

import net.minecraft.init.Items;
import ohrm.malgra.api.research.Research;

/**
 * Created by Chris on 13/08/2016.
 */
public class ResearchFireStarter extends Research {

    public ResearchFireStarter(){

        name = "malgraFireStarter";
        researchPoints = 1;
        requirements.add(Items.STICK);

    }

    @Override
    public void onUnlock() {

    }

}
