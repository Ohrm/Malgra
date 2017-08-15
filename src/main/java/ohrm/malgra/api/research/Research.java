package ohrm.malgra.api.research;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jline.internal.Nullable;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import ohrm.malgra.MalgraMain;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;


public class Research extends IForgeRegistryEntry.Impl<Research>{

    private Research parent;
    private List<Item> requirements = new ArrayList<Item>();
    private int researchPoints;

    public Research(int researchPoints, Item[] requirements, @Nullable ResourceLocation parent){
    	this.researchPoints = researchPoints;
    	this.requirements = Arrays.asList(requirements);
    	if(parent != null){
			if(GameRegistry.findRegistry(Research.class).containsKey(parent)){
				this.parent = GameRegistry.findRegistry(Research.class).getValue(parent);
			}else{
				Logger logger = MalgraMain.logger;
				logger.log(Level.ERROR, "Parent research %s does not exist. Did you register it before this one?", parent.toString());
			}
		}
	}

	public Research(int researchPoints, Item[] requirements){
    	this(researchPoints, requirements, null);
	}

	public final Research getParent(){
		
		return parent;
		
	}
	
	public final List<Item> getRequirements(){
		
		return requirements;
		
	}
	
	public final int getResearchPoints(){
		
		return researchPoints;
		
	}
	
	public final String getName(){
		
		return getRegistryName().getResourcePath();
		
	}
	
	public void onUnlock(){

	}
	
}
