package ohrm.malgra.registries;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryInternal;
import net.minecraftforge.registries.RegistryManager;
import ohrm.malgra.MalgraMain;
import ohrm.malgra.api.research.Research;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

public class MalgraResearchRegistryCallbacks implements IForgeRegistry.AddCallback<Research>, IForgeRegistry.ClearCallback<Research>, IForgeRegistry.CreateCallback<Research>{

    @Override
    public void onAdd(IForgeRegistryInternal<Research> owner, RegistryManager stage, int id, Research obj, @Nullable Research oldObj) {
        MalgraMain.logger.log(Level.INFO, obj.getName());
    }

    @Override
    public void onClear(IForgeRegistryInternal<Research> owner, RegistryManager stage) {

    }

    @Override
    public void onCreate(IForgeRegistryInternal<Research> owner, RegistryManager stage) {

    }
}
