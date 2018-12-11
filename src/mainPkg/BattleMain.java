package mainPkg;

import java.util.Scanner;
import character.*;
import monsterous.*;
import leaderBoard.*;

public class BattleMain {
	private static Scanner consoleInput = new Scanner(System.in);
	private static Player player1;
	private static Monster enemy;
	private static int Score;

	public static void main(String[] args) {
		System.out.println("Welcome to the Memetastic adventure!!\n");
		createChar();
		System.out.println();
		System.out.println("As " + player1.getName() + " takes their first steps away from home"
				+ "\n ---They look forward at the adventure ahead");
		while (!player1.getDead()) {
			createEncounter();
			System.out.println("A " + enemy.getType() + ", named " + enemy.getName() + " with " + enemy.getHealth() + " health, enters the battlefield\n");
			do {
				Combat.PlayerPassive(player1);
				Combat.PlayerTurn(player1, consoleInput, enemy);
				if (!enemy.getDead()) {
					Combat.MonsterPassive(enemy);
					Combat.MonsterTurn(enemy, player1);
				}
			} while (!player1.getDead() && !enemy.getDead());
			if (!player1.getDead()) {
				System.out.println(enemy);
				Combat.Loot(player1);
			}
		}
		System.out.println(player1);
		//HighScore.Score(Score);
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
	
	//To be called in the combat loot method
	public static void scoreAdd(){
		Score ++;
	}

	// To Be Implemented:
	/*
	 * Combat Monster Types allow for special abilities or attributes//Canceled 
	 * ClassCombatBoss (Make Random Boss Monster)
	 */
	// Optional:
	/*
	 * Special Abilities for Monsters Encounters allow for special effects//Canceled
	 * Scoring 
	 * Multiplayer Duels //Canceled
	 * Multiplayer Parties //Canceled
	 * Saving//Scores only
	 */

}
