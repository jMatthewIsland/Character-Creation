
public interface Item {
	
	public void use(Creature target);
	
	public void equip(Creature equipTo);
	
	public void unequip(Creature unequipFrom);
	
	public String toString();
	
	public String getName();
	
	public String getDescription();
	
	public int getCommonality();
	
	public String getItemType();
}
