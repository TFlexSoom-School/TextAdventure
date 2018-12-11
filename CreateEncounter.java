package monsterous;
import java.util.Random;

import character.Player;

public class CreateEncounter {
	private static Player leader;
	private static int MinD_Temp;

	public static Monster CreateEncounterMain(Player Leader) {
		leader = Leader;
		return new Monster(pickHealth(), createMinD(), createMaxD(),
				randName(), randType());
	}

	private static int pickHealth() {
		Random rand = new Random();
		return (40 + ((10 + rand.nextInt(10)) * leader.getLevel()));
	}

	private static int createMinD() {
		Random rand = new Random();
		MinD_Temp = (rand.nextInt(6) + 1) * leader.getLevel();
		return MinD_Temp;
	}

	private static int createMaxD() {
		Random rand = new Random();
		return MinD_Temp + rand.nextInt(10) + 1;
	}

	private static String randName() {
		return MultipleClassArrays.getRandMonsterName();
	}

	private static String randType() {
		return MultipleClassArrays.getRandMonsterType();
	}

}
