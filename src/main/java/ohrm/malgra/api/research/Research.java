package ohrm.malgra.api.research;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;

public abstract class Research {

	protected String name;
    protected List<Research> children = new ArrayList<Research>();
    protected List<Item> requirements = new ArrayList<Item>();
    protected int researchPoints;
	
	public List<Research> getChildren(){
		
		return children;
		
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
	
	public void addChild(Research child){
		
		children.add(child);
		
	}
	
	public abstract void onUnlock();
	
}
