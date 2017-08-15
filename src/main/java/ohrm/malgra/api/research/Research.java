package ohrm.malgra.api.research;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Research extends IForgeRegistryEntry.Impl<Research>{

	private String name;
    private Research parent;
    private List<Item> requirements = new ArrayList<Item>();
    private int researchPoints;

    public Research(String name, int researchPoints, List<Item> requirements, ResourceLocation parent){
    	this.name = name;
    	this.researchPoints = researchPoints;
    	this.requirements = requirements;
    	if(parent != null){
			if(GameRegistry.findRegistry(Research.class).containsKey(parent)){
				this.parent = GameRegistry.findRegistry(Research.class).getValue(parent);
			}else{
				Logger logger = LogManager.getFormatterLogger("malgraAPI");
				logger.log(Level.ERROR, "Parent research %s does not exist. Did you register it before this one?", parent.toString());
			}
		}
	}

	public Research(String name, int researchPoints, List<Item> requirements){
    	this(name, researchPoints, requirements, null);
	}

	public Research getParent(){
		
		return parent;
		
	}
	
	public List<Item> getRequirements(){
		
		return requirements;
		
	}
	
	public int getResearchPoints(){
		
		return researchPoints;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public void onUnlock(){

	}
	
}
