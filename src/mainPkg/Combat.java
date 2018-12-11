package mainPkg;

import java.util.Scanner;
import monsterous.*;
import character.*;

public class Combat {
	private static Scanner parameter;

	public static void PlayerPassive(Player p) {
		p.regen();
	}

	public static void PlayerTurn(Player p, Scanner consoleInput, Monster m) {
		System.out.print(p.getName() + " prepared himself...\nEnter action--");
		while (true) {
			String userInput = consoleInput.nextLine();
			if (userInput.contains("help")) {
				System.out.println("                                      =Available Actions=\n"
						+ "--> Attack | Item<index | Items | Scan | Define Spell<name | Spell<name | Spells | Point Check <--");
			} else if (userInput.toLowerCase().contains("attack")) {
				p.Attack(m);
				break;
				// -----------------------------------
			} else if (userInput.toLowerCase().contains("item<")) {
				parameter = new Scanner(userInput);
				parameter.skip("Item<");
				if (parameter.hasNextInt()) {
					if (p.use_item(parameter.nextInt())) {
						break;
					}
				}
				System.out.println("= No Item Used = --->> Try again");

				// -----------------------------------
			} else if (userInput.toLowerCase().contains("items")) {
				System.out.println(p.getInventory());

				// ------------------------------------
			} else if (userInput.toLowerCase().contains("scan")) {
				System.out.println(m + "\n");
				break;

				// -----------------------------------
			} else if (userInput.toLowerCase().contains("define spell<")){
				parameter = new Scanner(userInput);
				parameter.skip("Define Spell<");
				if(parameter.hasNext()){
					if (p.getClass() == Mage.class) {
						((Mage)p).spellCost(parameter.nextLine());
					} else if (p.getClass() == Warrior.class) {
						((Warrior)p).spellCost(parameter.next());
					}
				}
				// -----------------------------------
			} else if (userInput.toLowerCase().contains("spell<")) {
				parameter = new Scanner(userInput);
				parameter.skip("Spell<");
				if (parameter.hasNext()) {
					if (p.getClass() == Mage.class) {
						if (((Mage) p).useSpell(parameter.nextLine(), m)) {
							break;
						}
					} else if (p.getClass() == Warrior.class) {
						if (((Warrior) p).useSpell(parameter.nextLine(), m)) {
							break;
						}
					}
				}
				System.out.println("= No Spell Used = --->> Try Again");

				// -----------------------------------

			} else if (userInput.toLowerCase().contains("spells")) {
				p.printSpells();

				// -----------------------------------
			} else if (userInput.toLowerCase().contains("point check")) {
				System.out.println(p);
				// -----------------------------------
			} else {
				System.out.println("Unknown action please type -| help |- for list of actions\n");
			}
		}
	}

	public static void MonsterPassive(Monster m) {
		m.debuffs();
	}

	public static void MonsterTurn(Monster m, Player p) {
		m.attack(p);
	}

	public static void Loot(Player p) {
		System.out.println();
		p.levelUp();
		p.getLoot();
		p.rest();
		BattleMain.scoreAdd();
	}

}
