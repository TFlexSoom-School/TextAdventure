package monsterous;
import java.util.Random;

import character.Player;
public class MultipleClassArrays {
	private static String[] MonsterNames = {"Fernando", "Mother", "Some Monster", "Richard", "Chris", "Snorlgarrt", "Troyzurp", "Disguisillip", "Unknown", "Grahm"};
	private static String[] MonsterTypes = {"Golem", "Griffon", "Goblin", "Ogre", "Giant Snail", "Treant", "Angry Man 'O' War", "Hogman", "Harpy"};
	private static String[] BossTypes = {"Automaton", "Chimera", "Orc", "Troll", "Giant Slug", "Angry Druid", "Kracken", "Leading Boarsman", "Killer Siren"};
	
	//A little Easter egg in the code
	public static void trollName(Player leader){
		MonsterNames[4] = leader.getName();
	}
	
	public static String getRandMonsterName(){
		Random rand = new Random();
		return MonsterNames[rand.nextInt(10)];
	}
	
	public static String[] getMonsterNames(){
		return MonsterNames;
	}
	
	public static String getRandMonsterType(){
		Random rand = new Random();
		return MonsterTypes[rand.nextInt(9)];
	}
	
	public static String[] getMonsterTypes(){
		return MonsterTypes;
	}
	
	public static String getRandBossType(){
		Random rand = new Random();
		return BossTypes[rand.nextInt(9)];
	}
	
	public static String[] getBossTypes(){
		return BossTypes;
	}
}
