package mainPkg;

import java.util.Scanner;
import character.*;
import monsterous.*;

public class BattleMain {
	private static Scanner consoleInput = new Scanner(System.in);
	private static Player player1;
	private static Monster enemy;

	public static void main(String[] args) {
		System.out.println("Welcome to the Memetastic adventure!!\n\n");
		createChar();
		System.out.println();
		System.out.println("As " + player1.getName() + " takes their first steps away from home"
				+ "\n ---They look forward at the adventure ahead");
		while (!player1.getDead()) {
			createEncounter();
			System.out.println("A " + enemy.getType() + ", named " + enemy.getName() + ", enters the battlefield\n");
			while (!player1.getDead() && !enemy.getDead()) {
				Combat.PlayerPassive(player1);
				Combat.PlayerTurn(player1, consoleInput,enemy);
				Combat.MonsterPassive(enemy);
				Combat.MonsterTurn(enemy,player1);
			}

			if (!player1.getDead()) {
				Combat.Loot(player1);
			}
		}
		Combat.closeScanner();

	}

	// Creates the character through user input
	public static void createChar() {
		player1 = CreateChar.CreateCharMain(consoleInput);

		// A little Coding Easter egg ---- Please Excuse
		MultipleClassArrays.trollName(player1);
	}

	// Creates a Monster to battle
	public static void createEncounter() {
		enemy = CreateEncounter.CreateEncounterMain(player1);

	}

	// To Be Implemented:
	/*
	 * Combat Monster Types allow for special abilities or attributes
	 * ClassCombatBoss (Make Random Boss Monster) ClassDeath End
	 */
	// Optional:
	/*
	 * Special Abilities for Monsters Encounters allow for special effects
	 * Scoring Multiplayer Duels Multiplayer Parties Saving
	 */

}
