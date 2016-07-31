package ohrm.malgra.api.research;

import java.util.List;

import net.minecraft.item.Item;

public abstract class Research {

	private String name;
	private List<Research> children;
	private List<Item> requirements;
	private int researchPoints;
	
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
	public abstract void onEnterResearchRoom();
	
}
