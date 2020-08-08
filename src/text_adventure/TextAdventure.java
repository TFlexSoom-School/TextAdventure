package text_adventure;

import java.util.Scanner;

import character.*;
import monsterous.*;
import leaderBoard.*;
import user_input.UserCommand;

public class TextAdventure {
    public static int score;

    public static void main(String[] args) {
        Player player = introduction();
		Monster enemy = null;

        System.out.printf(
            "As %s takes their first steps away from home\n", 
            player.getName()
            );
		
        System.out.printf("---They look forward at the adventure ahead\n");

        while (!player.isDead()) {
			enemy = createEncounter(player);
			System.out.printf(
                "A %s, named %s with %d health, enters the battlefield\n\n", enemy.getType(), 
                enemy.getName(), 
                enemy.getHealth()
                );

			while(!player.isDead() && !enemy.isDead()) {
				playerPassive(player);
			    playerTurn(player, enemy);
				if (!enemy.isDead()) {
				    monsterPassive(enemy);
					monsterTurn(enemy, player);
				}
            }

			if (!player.isDead()) {
				System.out.println(enemy);
				loot(player);
			}
		}
		System.out.println(player);
		//HighScore.Score(Score);
	}

    public static Player introduction(){
        System.out.println("Welcome to the Memetastic adventure!!\n");
		Player player = CreateChar.CreateCharMain();
		// A little Coding Easter egg ---- Please Excuse
		MultipleClassArrays.trollName(player);
        System.out.println();

        return player;
    }

    public static void playerPassive(Player p) {
		p.regen();
	}

	public static void playerTurn(Player p,  Monster m) {
		System.out.printf("%s prepared themself...\n", p.getName());
        Command cmd = UserCommand.getValidCommand();
        switch(cmd.action){
            case ATTACK:
                p.attack(m);
                break;
            case LIST:
                // System.out.println(p.getInventory());
                // p.printSpells();
                break;
            case DETAIL:
                // System.out.println(m + "\n");
                // System.out.println(p);
                /*
                 parameter = new Scanner(userInput);
                parameter.skip("Define Spell<");
                if(parameter.hasNext()){
                    if (p.getClass() == Mage.class) {
                        ((Mage)p).spellCost(parameter.nextLine());
                    } else if (p.getClass() == Warrior.class) {
                        ((Warrior)p).spellCost(parameter.next());
                    }
                }
                */
                break;
            case SPECIAL:
            /*
            parameter = new Scanner(userInput);
            parameter.skip("Item<");
            if (parameter.hasNextInt()) {
                if (p.use_item(parameter.nextInt())) {
                    break;
                }
            }
            System.out.println("= No Item Used = --->> Try again");

            ////////
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

            */
                break;
            default:
                break;
        } 
	}

	public static void monsterPassive(Monster m) {
		//m.debuffs();
	}

	public static void monsterTurn(Monster m, Player p) {
		m.attack(p);
	}

	public static void loot(Player p) {
		System.out.println();
		p.levelUp();
		p.getLoot();
		p.rest();
		scoreAdd();
	}

	// Creates a Monster to battle
	public static Monster createEncounter(Player player) {
		return CreateEncounter.CreateEncounterMain(player);

	}
	
	//To be called in the combat loot method
	public static void scoreAdd(){
		score ++;
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
