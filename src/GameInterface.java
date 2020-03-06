import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;

public class GameInterface {
	
	
	public static void main (String [] args) {	
		
		String location = "Yuletides Pub";
		String time = "Day";
		String spawnPoint = "Yuletides Pub";
		
		ArrayList<String> godRegister = new ArrayList <String>();
		ArrayList<String> classRegister = new ArrayList<String>();
		ArrayList<String> raceRegister = new ArrayList<String>();
		ArrayList<String> abilitiesRegister = new ArrayList<String>();
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		File godList = new File("godList.txt");
		File classList = new File("classList.txt");
		File raceList = new File("raceList.txt");
		File abilitiesList = new File("abilities.txt");
		
		Scanner input = new Scanner(System.in);
		
		godRegister = refreshList(godList, godRegister);
		classRegister = refreshList(classList, classRegister);
		raceRegister = refreshList(raceList, raceRegister);
		abilitiesRegister = refreshList(abilitiesList, abilitiesRegister);
		
		//This will create the robot and stop the screen from switching
		pause(1);
		
		Player P1 = new Player(godRegister, classRegister, raceRegister, abilitiesRegister, 5); 
		P1.create();
		
		//Creature Test = new Creature(godRegister, classRegister, raceRegister, abilitiesRegister, 5);
		//Test.create("Rona", "Barbarian", "Bugbear", -2, 0, 9, 5, 4, -1);
		//System.out.println(Test.toString());
		//System.out.println(Test.getConstitution());
		//Test.showHealthRatio();
		
		pause(2);
		clearScreen();
		
		System.out.println(P1.toString());
		//P1.showHealthRatio();
		
		pause(2);
		
		P1.levelUp();
		
		pause(1);
		
		P1.damage(100);
		
		P1.showHealthRatio();
		
		P1.getInventory();
		
		pause(3);
		
		Item HealthPotion = new Aid("He", 0.5, "Health Potion");
		Item Thing = new Aid("He", 0.7, "Thing");
		Item Thing2 = new Aid("He", 0.2, "Thing");
		Item Thing3 = new Aid("He", 0.5, "Thing 3");
		
		P1.addInventory(HealthPotion, 1);
		P1.addInventory(Thing2, 2);
		P1.addInventory(HealthPotion, 1);
		
		P1.getInventory();
		
		P1.addInventory(Thing3, 1);
		P1.addInventory(Thing, 1);
		P1.getInventory();
		
		P1.useInventory(1, P1);
		
		P1.showHealthRatio();
		
		System.out.println("Wake up.");
		
		pause(2);
		
		
	}
	
	
	
	
	
	public static void createNewGame() {
		Scanner input = new Scanner(System.in);
		int choice;
		String saveNumber = "1";
		String saveAddress = "saves/save" + saveNumber;
		File testFile = new File(saveAddress);
		while(testFile.exists()) {
			saveNumber = String.valueOf(Integer.valueOf(saveNumber) + 1);
			saveAddress = "saves/save" + saveNumber;
			testFile = new File(saveAddress);
		}
	
				File newSave = new File ("saves/save" + saveNumber);
				newSave.mkdir();
				File characterData = new File ("saves/save" + saveNumber + "/characterData.txt");
				File location = new File ("saves/save" + saveNumber + "/location.txt");
				File inventories = new File ("saves/save" + saveNumber + "/inventory.txt");
				File time = new File ("saves/save" + saveNumber + "/time.txt");
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 100; i ++) {
			System.out.print("\n");
		}
	}
	
	
	public static ArrayList<String> refreshList(File file, ArrayList<String> list) {
		
		String line;
		File inputFile;
		ArrayList<String> outputList;
		
		inputFile = file;
		outputList = list;
		
		
		try {
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader reader = new BufferedReader(fileReader);
			
			while ((line = reader.readLine())!=null) {
				
				
				if (line.length() < 4) {
					//skip
				}
				else {
					outputList.add(line);
				}
				
			}
			
			
			reader.close();
			return(outputList);
			
			
			
		} catch(IOException e) {
			System.err.println(e);
			return(outputList);
		}

		
		
		
	} 
	
	public static void pause(int seconds) {
		
		//robot takes milliseconds
		seconds *= 1000;
		
		try {
			Robot r = new Robot();
			
			r.delay(seconds);
		}catch (AWTException e) {
			System.err.println();
		}
	}
	
	
}




