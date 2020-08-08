package character;
import java.util.Scanner;


public class CreateChar {
	private static Scanner console_input;
	private static String difficulty;
	private static int health;
	private static double damageMod;
	
	public static Player CreateCharMain(){
		console_input = new Scanner(System.in);
		String name = createCharName();
		difficulty = setDifficulty();
		
		String charClass = chooseClass();

		if(charClass.equals("rogue")){
			return new Rogue(name, health, damageMod);
		}else if(charClass.equals("mage")){
			return new Mage(name, health, damageMod);
		}else if(charClass.equals("warrior")){
			return new Warrior(name, health, damageMod);
		}else{
			return null;
		}
	}
	
	public static String getDifficulty(){
		return difficulty;
	}
	
	private static String createCharName(){
		System.out.print("Name your character!! -- :");
		String name = console_input.nextLine();
		System.out.println();
		return name;
	}
	
	private static String setDifficulty(){
		System.out.print("State your difficulty -|Easy + Medium + Hard + Insane|- :");
		String statedDifficulty = console_input.nextLine().toLowerCase();
		System.out.println();
		String Difficulty = "medium";
		if(statedDifficulty.contains("easy")){
			System.out.println("--Easy Selected--");
			health = 100;
			damageMod = 1;
		}
		else if (statedDifficulty.contains("med")){
			Difficulty = "medium";
			System.out.println("--Medium Selected--");
			health = 80;
			damageMod = 1;
		}
		else if (statedDifficulty.contains("hard")){
			Difficulty = "hard";
			System.out.println("--Hard Selected--");
			health = 80;
			damageMod = .9;
		}
		else if (statedDifficulty.contains("in")){
			Difficulty = "insane";
			System.out.println("--Insane Selected--");
			health = 70;
			damageMod = .75;
		}
		else{
			System.out.println("--Incorrect Input Detected --");
			System.out.println("    ==Defaulted to Medium==");
			health = 80;
			damageMod = 1;
		}
		
		return Difficulty;
	}
	
	private static String chooseClass(){
		System.out.println("Pick Your Class:\n-Mage | Warrior | Rogue");
		while(true){
			String Class = console_input.nextLine();
			Class = Class.toLowerCase();
			if (Class.contains("ma") || Class.contains("war") || Class.contains("rog")) {
				if (Class.contains("ma")) {
					return "mage";
				} else if (Class.contains("war")) {
					return "warrior";
				} else if (Class.contains("rog")) {
					return "rogue";
				}
			}
			System.out.println("Please at least type a name of one of the above characters ^");
		}
		
	}
}
