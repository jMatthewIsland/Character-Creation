import java.util.*;

public class Creature {
	/**
	 * Constructor
	 * pre: must select god and class first, and updated the godRegister and classRegister with the txt file
	 */
	
	String god;
	String selectedClass;
	String race;
	int intelligence, wisdom, strength, dexterity, constitution, charisma;
	int level;
	int maxHealth, health;
	int maxExp, exp;
	ArrayList<String> godRegister = new ArrayList<String>();
	ArrayList<String> classRegister = new ArrayList<String>();
	ArrayList<String> raceRegister = new ArrayList<String>();
	ArrayList<String> abilitiesRegister = new ArrayList<String>();
	ArrayList<Item> inventory = new ArrayList<Item>();
	ArrayList<Integer> inventoryCount = new ArrayList<Integer>(); //parallel to inventory ArrayList
	int levelUpIn, levelUpWi, levelUpSt, levelUpDe, levelUpCo, levelUpCh;
	String name = "";
	
	public Creature (ArrayList<String> godRegister, ArrayList<String> classRegister, ArrayList<String> raceRegister, ArrayList<String> abilitiesRegister, int level) {
		this.godRegister = godRegister;
		this.classRegister = classRegister;
		this.raceRegister = raceRegister;
		this.abilitiesRegister = abilitiesRegister;
		this.level = level;
	}
	
	public void damage(int damagePoints) {
		int dp;
		dp = damagePoints;
		if (health - dp >= 0) {
			health = health - dp;
		}
		else if (health - dp < 0) {
			health = 0;
		}
	}
	
	public void heal(int healPoints) {
		int hp;
		hp = healPoints;
		if (health + hp >= maxHealth) {
			health = maxHealth;
		}
		else if (health + hp < maxHealth) {
			health = health + hp;
		}
	}
	
	public void levelUp() {
		level ++;
		
		strength += levelUpSt;
		dexterity += levelUpDe;
		intelligence += levelUpIn;
		wisdom += levelUpWi;
		charisma += levelUpCh;
		constitution += levelUpCo;
		
		if (50 + (15 * constitution) >= 20) { 
			maxHealth = 50 + (15 * constitution);
		}
		else {
			maxHealth = 20;
		}
		
		health = maxHealth;
		
		exp = exp - maxExp;
		//more code
	}
	
	public void create(String god, String selectedClass, String race, int intelligence, int wisdom, int strength, int dexterity, int constitution, int charisma) {
		this.god = god;
		this.selectedClass = selectedClass;
		this.race = race;
		this.intelligence = intelligence;
		this.wisdom = wisdom;
		this.strength = strength;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.charisma = charisma;
		
		selectedClass=selectedClass.toLowerCase();
		
		int counter = 0;
		Boolean out = false;
		String line = "";
		int indexOfClass = -1;
		int indexOfGod = -1;
		int indexOfRace = -1;
		String godStats = "", classStats = "", raceStats = "";
		String test;
		int indexOfPipeOne, indexOfPipeTwo, indexOfPipeThree;
		//String creationStats[] = new String[6];
		int indexOfComma, indexOfCommaTwo;
		String stat;
		
		
		while ((counter < classRegister.size()) && (out == false)) {
			line = classRegister.get(counter);
			line = line.toLowerCase();
			line = line.substring(0, (line.indexOf('|')));
			if (line.equals(selectedClass.toLowerCase())) {
				
				out = true;
				indexOfClass = counter;
				test = line;
			}
			
			counter ++;
		}
		
		
		if (indexOfClass == -1) {
			System.out.println("Error, could not identify the class " + line);
		}
		
		
		line = "";
		out = false;
		counter = 0;
		
		while ((counter < godRegister.size()) && (out == false)) {
			line = godRegister.get(counter);
			line = line.toLowerCase();
			line = line.substring(0, (line.indexOf('|')));
			if (line.equals(god.toLowerCase())) {
				
				out = true;
				indexOfGod = counter;
			}
			
			counter ++;
		}
		
		if (indexOfGod == -1) {
			System.out.println("Error, could not identify the god " + line);
		}
		
		line = "";
		out = false;
		counter = 0;
		
		while ((counter < raceRegister.size()) && (out == false)) {
			line = raceRegister.get(counter);
			line = line.toLowerCase();
			line = line.substring(0, line.indexOf('|'));
			if (line.equals(race.toLowerCase())) {
				
				out = true;
				indexOfRace = counter;
				test = line;
			}
			
			counter ++;
		}
		
		
		if (indexOfRace == -1) {
			System.out.println("Error, could not identify the race " + line);
		}
		
		
		test = godRegister.get(indexOfGod);
		indexOfPipeOne = test.indexOf('|');
		indexOfPipeTwo = test.indexOf('|', indexOfPipeOne + 1);
		indexOfPipeThree = test.indexOf('|', indexOfPipeTwo + 1);
		godStats = test.substring(indexOfPipeThree + 1, test.length());
		
		test = "";
		indexOfPipeOne = 0;
		indexOfPipeTwo = 0;
		indexOfPipeThree = 0;
		
		test = classRegister.get(indexOfClass);
		indexOfPipeOne = test.indexOf('|');
		indexOfPipeTwo = test.indexOf('|', indexOfPipeOne + 1);
		indexOfPipeThree = test.indexOf('|', indexOfPipeTwo + 1);
		classStats = test.substring(indexOfPipeTwo + 1, test.length());
		
		test = "";
		indexOfPipeOne = 0;
		indexOfPipeTwo = 0;
		indexOfPipeThree = 0;
		
		test = raceRegister.get(indexOfRace);
		indexOfPipeOne = test.indexOf('|');
		indexOfPipeTwo = test.indexOf('|', indexOfPipeOne + 1);
		indexOfPipeThree = test.indexOf('|', indexOfPipeTwo + 1);
		raceStats = test.substring(indexOfPipeTwo + 1, test.length());
		
		
		//god stat addition
		
		indexOfComma = godStats.indexOf(',');
		indexOfCommaTwo = godStats.indexOf(',', indexOfComma + 1);
		
		stat = godStats.substring(0, indexOfComma);
		
		addStats(stat);
		
		stat = godStats.substring(indexOfCommaTwo - 3, indexOfCommaTwo);
		
		addStats(stat);
		
		stat = godStats.substring(indexOfCommaTwo + 2);
		
		addStats(stat); //System.out.println("here!");
		
		
		//class stat addition
		
		indexOfComma = classStats.indexOf(',');
		
		stat = classStats.substring(0, indexOfComma);
		
		addStats(stat);
		
		stat = classStats.substring(indexOfComma + 2);
		
		addStats(stat);
		
		//race stat addition
		
		indexOfComma = raceStats.indexOf(',');
		
		stat = raceStats.substring(0, indexOfComma);
		
		addStats(stat);
		
		stat = raceStats.substring(indexOfComma + 2);
		
		addStats(stat);
		
		levelUpSt = levelUpSt/2;
		levelUpDe = levelUpDe/2;
		levelUpWi = levelUpWi/2;
		levelUpIn = levelUpIn/2;
		levelUpCh = levelUpCh/2;
		levelUpCo = levelUpCo/2;
		
		
		if (50 + (15 * getConstitution()) >= 20) { 
			maxHealth = 50 + (15 * getConstitution());
		}
		else {
			maxHealth = 20;
		}
		health = maxHealth;
		exp = 0;
		maxExp = 1000 + (4 ^ (level/5));
		
		
	}
	
	public void addStats(String stat) {
		
		int value;
		if ((stat.substring(0,1)).equals("-")) {
			value = Integer.valueOf(stat.substring(1, 2));
			
			//System.out.println(stat.substring(2, 4));
			
			switch(stat.substring(2, 4)) {
			
			case "St":
				strength -= value;
				break;
			case "Ch":
				charisma -= value;
				break;
			case "In":
				intelligence -= value;
				break;
			case "Wi":
				wisdom -= value;
				break;
			case "Co":
				constitution -= value;
				break;
			case "De":
				dexterity -= value;
				break;
			default:
				System.out.println("Error, could not identify the stat indicator: " + stat.substring(1));
				break;
			
			}
			
		}
		
		else {
			value = Integer.valueOf(stat.substring(0, 1));
			
			switch (stat.substring(1, 3)) {
			
			case "St":
				strength += value;
				levelUpSt += value;
				break;
			case "Ch":
				charisma += value;
				levelUpCh += value;
				break;
			case "In":
				intelligence += value;
				levelUpIn += value;
				break;
			case "Wi":
				wisdom += value;
				levelUpWi += value;
				break;
			case "Co":
				constitution += value;
				levelUpCo += value;
				break;
			case "De":
				dexterity += value;
				levelUpDe += value;
				break;
			default:
				System.out.println("Error, could not identify the stat indicator: " + stat.substring(1));
				break;
			}
			
		}
		
	}
	
	public int getIntelligence() {
		return(intelligence);
	}
	public void setIntelligence(int tempIn) {
		intelligence = tempIn;
	}
	
	public int getWisdom() {
		return(wisdom);
	}
	public void setWisdom(int tempWi) {
		wisdom = tempWi;
	}
	
	public int getStrength() {
		return(strength);
	}
	public void setStrength(int tempSt) {
		strength = tempSt;
	}
	
	public int getDexterity() {
		return(dexterity);
	}
	public void setDexterity(int tempDe) {
		dexterity = tempDe;
	}
	
	public int getConstitution() {
		return(constitution);
	}
	public void setConstitution(int tempCo) {
		constitution = tempCo;
	}
	
	public int getCharisma() {
		return(charisma);
	}
	public void setCharisma(int tempCh) {
		charisma = tempCh;
	}
	
	public int getLevel() {
		return(level);
	}
	
	public int getHealth() {
		return(health);
	}
	public void setHealth(int setHP) {
		health = setHP;
	}
	
	public int getMaxHealth() {
		return(maxHealth);
	}
	
	public void getInventory() {
		int index = 0;
		int digits = 0;
		int stringLength = 0;
		if (inventory.size() == 0) {
			System.out.println("This inventory is empty!");
		}
		
		else {
			System.out.println("-----------------------------------"); //35 dashes
			System.out.println("|            Inventory            |");
			System.out.println("|                                 |");
			for (Item item : inventory) {
				digits = 0;
				stringLength = 0;
				System.out.print("|");
				System.out.print((index + 1) + ". ");
				System.out.print(item.getName() + "  x" + inventoryCount.get(index));
				if (inventoryCount.get(index) < 10) {
					digits = 1;
				}
				else if (inventoryCount.get(index) < 100) {
					digits = 2;
				}
				else if (inventoryCount.get(index) < 1000) {
					digits = 3;
				}
				if (index < 10) {
					digits += 1;
				}
				else if (index < 100) {
					digits += 2;
				}
				else if (index < 1000) {
					digits += 3;
				}
				
				stringLength = digits + 1 + 1 + 1 + item.getName().length() + 1 + 1 + 1 + 1;
				for (int i = stringLength; i < 35; i ++) {
					System.out.print(" ");
				}
				System.out.println("|");
				
				index ++;
			}
			System.out.println("|                                 |");
			System.out.println("-----------------------------------");
		}
	}
	
	public void giveName(String name) {
		this.name = name;
	}
	
	public void showHealthRatio() {
		if (!name.equals("")) { 
			System.out.print(name + ": ");
		}
		else {
			System.out.print(race + ": ");
		}
		System.out.println(health + " / " + maxHealth);
	}
	
	public void addInventory(Item item, Integer amount) {
		Item addedItem = item;
		Boolean alreadyInInventory = false;
		Integer increment;
		int index = -1;
		for (Item testItem : inventory) {
			if (testItem.equals(addedItem)) {
				alreadyInInventory = true;
				index = inventory.indexOf(testItem);
			}
		}
		if (alreadyInInventory == true) {
			increment = inventoryCount.get(index);
			increment += amount;
			inventoryCount.set(index, increment);
		}
		else {
			inventory.add(addedItem);
			inventoryCount.add(amount);
		}
		
	}
	
	public void useInventory(int inventoryPosition, Creature target) {
		int index = inventoryPosition - 1;
		Creature targetCreature = target;
		Item useItem = inventory.get(index);
		
		useItem.use(targetCreature);
		
		if(useItem.getItemType().equals("Aid")) {
			removeInventory(index, 1);
		}
		
		
		
	}
	
	public void removeInventory(int inventoryPosition, int amount) {
		int index = inventoryPosition;
		int number = amount;
		int numberOfItems = inventoryCount.get(index);
		if (numberOfItems - number <= 0) {
			inventory.remove(index);
			inventoryCount.remove(index);
		}
		else {
			numberOfItems = numberOfItems - number;
			inventoryCount.set(index, numberOfItems);
		}
	}
	
	public void getStats() {
		System.out.println("Charisma: " + charisma);
		System.out.println("Strength: " + strength);
		System.out.println("Dexterity: " + dexterity);
		System.out.println("Intelligence: " + intelligence);
		System.out.println("Wisdom: " + wisdom);
		System.out.println("Constitution: " + constitution);
	}
	
	public String getRace() {
		return(race);
	}
	
	public String getReligion() {
		return(god);
	}
	
	public String getSelectedClass() {
		return(selectedClass);
	}
	
	public void clearScreen() {
		for (int i = 0; i < 100; i ++) {
			System.out.print("\n");
		}
	}
	
	public String toString() {
		if (name == "") {
			return(race + ": a level " + level + " " + race + " " + selectedClass + " who worships " + god + ". He has " + this.getStrength() + " strength, " + this.getDexterity() + " dexterity, " + this.getIntelligence() + " intelligence, " + this.getWisdom() + " wisdom, " + this.getCharisma() + " charisma, and " + this.getConstitution() + " constitution.");
		}
		else {
			return (name + ": a level " + level + " " + race + " " + selectedClass + " who worships " + god + ". He has " + this.getStrength() + " strength, " + this.getDexterity() + " dexterity, " + this.getIntelligence() + " intelligence, " + this.getWisdom() + " wisdom, " + this.getCharisma() + " charisma, and " + this.getConstitution() + " constitution.");
		}
	}
	
	
	
	
}
