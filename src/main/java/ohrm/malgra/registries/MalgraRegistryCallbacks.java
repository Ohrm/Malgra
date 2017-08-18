package ohrm.malgra.registries;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryInternal;
import net.minecraftforge.registries.RegistryManager;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.api.research.Research;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

public class MalgraRegistryCallbacks {

    public static class ResearchAddCallback implements IForgeRegistry.AddCallback<Research>{

        @Override
        public void onAdd(IForgeRegistryInternal<Research> owner, RegistryManager stage, int id, Research obj, @Nullable Research oldObj) {
            MalgraMain.logger.log(Level.INFO, obj.getName());
        }
    }

}
