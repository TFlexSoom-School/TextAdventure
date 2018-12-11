package monsterous;

import java.util.Random;

import character.Player;

public class CreateEncounter {
	private static Player leader;
	private static int MinD_Temp;

	public static Monster CreateEncounterMain(Player Leader) {
		leader = Leader;
		return new Monster(pickHealth(), createMinD(), createMaxD(), randName(), randType());
	}

	private static int pickHealth() {
		Random rand = new Random();
		return (int) ((3 * leader.getMaxD() + ((10 + rand.nextInt(10)) * leader.getLevel())) / leader.getModifier());
	}

	private static int createMinD() {
		Random rand = new Random();
		MinD_Temp = (int) ((rand.nextInt(leader.getMinD() / 5) + leader.getMinD() / 5) / leader.getModifier());
		return MinD_Temp;
	}

	private static int createMaxD() {
		Random rand = new Random();
		return MinD_Temp + rand.nextInt(20);
	}

	private static String randName() {
		return MultipleClassArrays.getRandMonsterName();
	}

	private static String randType() {
		return MultipleClassArrays.getRandMonsterType();
	}

}
