
public class Aid implements Item{
	
	String indicator, name, description;
	double percent;
	int commonality;
	
	
	public Aid(String indicator, double percent, String name) {
		this.indicator = indicator;
		this.percent = percent;
		this.name = name;
		
	}
	
	public void use(Creature target) {
		Creature useOn = target;
		int healthMod;
		if (indicator == "He") {
			healthMod = (int) (useOn.getMaxHealth() * percent);
			useOn.heal(healthMod);
		}
		//add to list of potential aids
	}
	
	public void equip(Creature equipTo) {
		
	}
	
	public void unequip(Creature unequipFrom) {
		
	}
	
	public String toString() {
		String itemString = "";
		
		return (itemString);
	}
	
	public String getName() {
		return(name);
	}
	
	public String getDescription() {
		return(description);
	}
	
	public int getCommonality() {
		return(commonality);
	}
	
	public String getItemType() {
		return("Aid");
	}
	
}
