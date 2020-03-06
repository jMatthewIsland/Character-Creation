import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Creature {

	Scanner input = new Scanner(System.in);
	int tempIn, tempCh, tempSt, tempDe, tempWi, tempCo = 0;
	String name;
	int health;
	
	public Player(ArrayList<String>godRegister, ArrayList<String>classRegister, ArrayList<String>raceRegister, ArrayList<String>abilitiesRegister, int level) {
		super(godRegister, classRegister, raceRegister, abilitiesRegister, level);
		//this.name = name;
	}
	
	public void create() {
		clearScreen();
		System.out.println("Welcome to the game!");
		System.out.println("Enter your name: ");
		name = input.nextLine();
		clearScreen();
		statChooser(10);
		
		race = chooseRace();
		clearScreen();
		god = chooseReligion();
		clearScreen();
		selectedClass = chooseClass();
		
		
		super.create(god, selectedClass, race, tempIn, tempWi, tempSt, tempDe, tempCo, tempCh);
	}
	
	public void levelUp() {
		super.levelUp();
		System.out.println(name + " leveled up!");
		statChooser(3);
		setStrength(tempSt);
		setDexterity(tempDe);
		setIntelligence(tempIn);
		setWisdom(tempWi);
		setCharisma(tempCh);
		setConstitution(tempCo);
	}
	
	public void statChooser(int points) {
		int total = 0;
		int countTo = points;
		tempSt = strength;
		tempDe = dexterity;
		tempIn = intelligence;
		tempWi = wisdom;
		tempCh = charisma;
		tempCo = constitution;
		//Scanner statInput = new Scanner(System.in);
		int choice;
		while (total < countTo) {
			System.out.println("You have " + (countTo - total) + " points. Select a class you would like to add to: ");
			System.out.println("\n" + "---------------------------------------------------------------------" + "\n" + "\n");
			System.out.println("1. Strength: " + tempSt + "\n");
			System.out.println("2. Dexterity: " + tempDe + "\n");
			System.out.println("3. Intelligence: " + tempIn + "\n");
			System.out.println("4. Wisdom: " + tempWi + "\n");
			System.out.println("5. Charisma: " + tempCh + "\n");
			System.out.println("6. Constitution: " + tempCo + "\n");
			System.out.println("7. Subtract a point" + "\n" + "\n");
			
			choice = input.nextInt();
			
			switch(choice) {
			
			case 1:
				tempSt++;
				total++;
				break;
			case 2:
				tempDe++;
				total++;
				break;
			case 3:
				tempIn++;
				total++;
				break;
			case 4:
				tempWi++;
				total++;
				break;
			case 5:
				tempCh++;
				total++;
				break;
			case 6:
				tempCo++;
				total++;
				break;
			case 7:
				System.out.println("------------------------------------------------");
				System.out.println("\n" + "Remove a point from: " + "\n");
				System.out.println("1. Strength?" + "\n");
				System.out.println("2. Dexterity?" + "\n");
				System.out.println("3. Intelligence?" + "\n");
				System.out.println("4. Wisdom?" + "\n");
				System.out.println("5. Charisma?" + "\n");
				System.out.println("6. Constitution?" + "\n");
				
				choice = input.nextInt();
				
				switch(choice) {
				
				case 1:
					if (tempSt > -5) {
						tempSt--;
						total--;
					}
					else {
						System.out.println("Error, not enough points in strength to subtract.");
					}
					break;
				case 2:
					if (tempDe > -5) {
						tempDe--;
						total--;
					}
					else {
						System.out.println("Error, not enough points in dexterity to subtract.");
					}
					break;
				case 3:
					if (tempIn > -5) {
						tempIn--;
						total--;
					}
					else {
						System.out.println("Error, not enough points in intelligence to subtract.");
					}
					break;
				case 4:
					if (tempWi > -5) {
						tempWi--;
						total--;
					}
					else {
						System.out.println("Error, not enough points in wisdom to subtract.");
					}
					break;
				case 5:
					if (tempCh > -5) {
						tempCh--;
						total--;
					}
					else {
						System.out.println("Error, not enough points in charisma to subtract.");
					}
					break;
				case 6:
					if (tempCo > -5) {
						tempCo--;
						total--;
					}
					else {
						System.out.println("Error, not enough points in constitution to subtract.");
					}
					break;
				
				}
			}
			
			clearScreen();
			
		}
	}
	
	public String chooseClass() {
		String classChosen = "";
		String className, classDescription, classStats;
		String line;
		String statString;
		//Scanner classInput = new Scanner(System.in);
		int pipeIndex, pipeIndex2, pipeIndex3;
		int classNumber;
		int commaIndex;
		
		System.out.println("Your class is what your character has decided to pursue. Classes boost your stats, and also give you special abilites that you can unlock at higher levels. This has the greatest bearing on your character." + "\n");
		System.out.println("--------------------------------------------------------" + "\n" + "\n");
		
		while (classChosen.equalsIgnoreCase("") ) {
			
			for(int i = 1; i < classRegister.size(); i ++) {
				line = classRegister.get(i);
				
				pipeIndex = line.indexOf('|');
				pipeIndex2 = line.indexOf('|', pipeIndex + 1);
				pipeIndex3 = line.indexOf('|', pipeIndex2 + 1);
				className = line.substring(0, pipeIndex);
				classDescription = line.substring(pipeIndex + 1, pipeIndex2);
				classStats = line.substring(pipeIndex2 + 1, pipeIndex3);
				//classAbilities = line.substring(pipeIndex3 + 1, line.length());
				
				System.out.print(i + ": " + className);
				System.out.print("    || ");
				System.out.print(classDescription);
				System.out.print(" This class gives you +" + classStats.substring(0, 1));
				commaIndex = classStats.indexOf(',');
				statString = classStats.substring(1, commaIndex);
				statsSwitch(statString);
				System.out.print(" and +" + classStats.substring(commaIndex + 2, commaIndex + 3));
				statString = classStats.substring(commaIndex + 3);
				statsSwitch(statString);
				
				System.out.print(" in the beginning of the game and every time you level up.");
				System.out.print("\n");
				System.out.print("\n");
				
			}

			System.out.println("Enter your choice: ");
			classNumber= input.nextInt();
			line = classRegister.get(classNumber);
			classChosen = line.substring(0, line.indexOf('|'));
			
		}
		
		return classChosen;
	}
	
	public String chooseReligion() {
		String godChosen = "";
		String godName, godOrientation, godDescription, godStats, godAbilities;
		String line;
		String statString;
		//Scanner godInput = new Scanner(System.in);
		int pipeIndex, pipeIndex2, pipeIndex3, pipeIndex4;
		int godNumber;
		int commaIndex, commaIndex2;
		
		System.out.println("Your religion boosts certain stats, and binds you to two effects, one good, one bad." + "\n");
		System.out.println("--------------------------------------------------------" + "\n" + "\n");
		
		while (godChosen.equalsIgnoreCase("") ) {
			
			for (int i = 1; i < godRegister.size(); i ++) {
				line = godRegister.get(i);
				//System.out.println(line);
				pipeIndex = line.indexOf('|');
				pipeIndex2 = line.indexOf('|', pipeIndex + 1);
				pipeIndex3 = line.indexOf('|', pipeIndex2 + 1);
				pipeIndex4 = line.indexOf('|', pipeIndex3 + 1);
				godName = line.substring(0, pipeIndex);
				godOrientation = line.substring(pipeIndex + 1, pipeIndex2);
				
				godDescription = line.substring(pipeIndex2 + 1, pipeIndex3);
				godStats = line.substring(pipeIndex3 + 1, pipeIndex4);
				godAbilities = line.substring(pipeIndex4 + 1, line.length());
				
				System.out.print(i + ". " + godName);
				System.out.print("    || ");
				System.out.print(godOrientation + "     || ");
				System.out.print(godDescription + "     || ");
				
				System.out.print("This god give you +"  + godStats.substring(0, 1));
				commaIndex = godStats.indexOf(',');
				commaIndex2 = godStats.indexOf(',', commaIndex + 1);
				
				statString = godStats.substring(1, commaIndex);
				
				statsSwitch(statString);
				
				System.out.print(", +" + godStats.substring(commaIndex + 2, commaIndex + 3));
				
				statString = godStats.substring(commaIndex + 3, commaIndex2);
				
				statsSwitch(statString);
				
				
				System.out.print(" and -" + godStats.substring(commaIndex2 + 3, commaIndex2 + 4));
				
				statString = godStats.substring(commaIndex2 + 4, godStats.length());
				
				statsSwitch(statString);
				
				System.out.print("\n");
				System.out.print("\n");
			}
			
			System.out.println("Enter your choice: ");
			godNumber= input.nextInt();
			line = godRegister.get(godNumber);
			godChosen = line.substring(0, line.indexOf('|'));
			
		}
		
		
		
		return godChosen;
	}
	
	public String chooseRace() {
		String raceChosen = "";
		String raceName, raceDescription, raceStats;
		String statString;
		String line;
		//Scanner raceInput = new Scanner(System.in);
		int pipeIndex, pipeIndex2;
		int raceNumber;
		System.out.println("Your race defines who your character is and what special abilities you can use. This may effect socialization with other characters, environmental boosts, and much more. Pick one!" + "\n");
		System.out.println("--------------------------------------------------------" + "\n" + "\n");
		for(int i = 1; i < raceRegister.size(); i ++) {
			line = raceRegister.get(i);
			pipeIndex = line.indexOf('|');
			pipeIndex2 = line.indexOf('|', pipeIndex + 1);
			//pipeIndex3 = line.indexOf('|', pipeIndex2 + 1);
			raceName = line.substring(0,  pipeIndex);
			raceDescription = line.substring(pipeIndex + 1, pipeIndex2);
			raceStats = line.substring(pipeIndex2 + 1, line.length());
			//raceEffects = line.substring(pipeIndex3, line.length());
			
			System.out.print(i + ". ");
			System.out.print(raceName);
			//for (int l = 0; l < (raceName.length() - 20); l++) {
				//System.out.print(" ");
				
			//}
			System.out.print("   ||");
			System.out.print(" " + raceDescription);
			//for (int l = 0; l < (raceDescription.length() - 200); l++) {
				//System.out.print(" ");
			//}
			System.out.print("||");
			System.out.print(" This race gets +" + raceStats.substring(0,1) );
			statString = raceStats.substring(1,raceStats.indexOf(","));
			
			statsSwitch(statString);
			
			System.out.print(" and +" + raceStats.substring(raceStats.indexOf(",") + 2, raceStats.indexOf(",") + 3));
			statString = raceStats.substring(raceStats.indexOf(",") + 3, raceStats.length());
			
			statsSwitch(statString);
			
			//print abilities
			
			
			System.out.print("\n");
			System.out.print("\n");
			
			
		}
		
		System.out.println("Enter your choice: ");
		
		raceNumber = input.nextInt();
		
		line = raceRegister.get(raceNumber);
		raceChosen = line.substring(0, line.indexOf('|'));
		
		return raceChosen;
	}
	
	
	public String getName() {
		return(name);
	}
	
	public void statsSwitch(String statString) {
		String line = statString;
		switch(line) {
		case "St":
			System.out.print(" strength");
			break;
		case "De":
			System.out.print(" dexterity");
			break;
		case "Wi":
			System.out.print(" wisdom");
			break;
		case "In":
			System.out.print(" intelligence");
			break;
		case "Ch":
			System.out.print(" charisma");
			break;
		case "Co":
			System.out.print(" constitution");
			break;
		default:
			System.out.println("Error in reading race for " + name);
		}
	}
	
	public void clearScreen() {
		for (int i = 0; i < 100; i ++) {
			System.out.print("\n");
		}
	}
	
	public String toString() {
		return(name + ": a level " + level + " " + race + " " + selectedClass + " who worships " + god + ". He has " + this.getStrength() + " strength, " + this.getDexterity() + " dexterity, " + this.getIntelligence() + " intelligence, " + this.getWisdom() + " wisdom, " + this.getCharisma() + " charisma, and " + this.getConstitution() + " constitution.");
	}
	
	public void showHealthRatio() {
		System.out.print(name + ": ");
		System.out.println(health + " / " + maxHealth);
	}
	
	
	
}

