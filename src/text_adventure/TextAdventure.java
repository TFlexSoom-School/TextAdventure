package text_adventure;

import character.*;
import monsterous.*;
import leaderBoard.*;
import user_input.UserCommand;

public class TextAdventure {
    public static int score;

    public static void main(String[] args) {
        Player player = introduction();
		Monster enemy = null;

        while (!player.isDead()) {
			enemy = createEncounter(player);
			System.out.printf(
                "A %s, named %s with %d health, enters the battlefield\n\n", 
                enemy.getType(), 
                enemy.getName(), 
                enemy.getHealth()
                );

			while(!player.isDead() && !enemy.isDead()) {
				playerPassive(player);
			    playerTurn(player, enemy);
				if (!enemy.isDead()) {
				    monsterPassive(enemy);
					monsterTurn(enemy, player);
				}else{
                    System.out.println(enemy);
				    loot(player);
                }
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
        System.out.printf(
            "As %s takes their first steps away from home\n", 
            player.getName()
            );
		
        System.out.printf("---They look forward at the adventure ahead\n");
        return player;
    }

    public static void playerPassive(Player p) {
		p.regen();
	}

	public static void playerTurn(Player p,  Monster m) {
		System.out.printf("%s prepared themself...\n", p.getName());
        Command cmd = null;
        boolean isTurnConsumed = false;
        while(!isTurnConsumed){
            cmd = UserCommand.getValidCommand();
            switch(cmd.action){
                case ATTACK:
                    p.attack(m);
                    isTurnConsumed = true;
                    break;
                case LIST:
                    listPlayer(cmd, p);
                    break;
                case DETAIL:
                    detailEntity(cmd, p, m);
                    break;
                case SPECIAL:
                    isTurnConsumed = consumeAbility(cmd, p, m);
                    break;
                default:
                    break;
            }
        }
    }
    
    public static void listPlayer(Command cmd, Player p){
        if(cmd.type == Command.TYPE.ITEM){
            System.out.println(p.getInventory());
        }else if(cmd.type == Command.TYPE.SPELL){
            p.printSpells();
        }else{
            System.err.println("Unknown List Type");
        }
    }

    public static void detailEntity(Command cmd, Player p, Monster m){
        switch(cmd.type){
            case SPELL:
                if(p.hasSpells()){
                    p.spellCost(cmd.name);
                }
                break;
            
            case PLAYER:
                System.out.println(p);
                break;
            
            case MONSTER:
                System.out.println(m + "\n");
                break;

            default:
                System.err.println("Game does not support \"detail\" for that!");
        }
    }

    public static boolean consumeAbility(Command cmd, Player p, Monster m){
        switch(cmd.type){
            case SPELL:
                System.out.println(cmd.name);
                if(p.useSpell(cmd.name, m)){
                    return true;
                }
                System.out.println("= No Spell Used = --->> Try Again");
                return false;
            
            case ITEM:
                if (p.use_item(cmd.index)) {
                    return true;
                }else{
                    System.out.println("= No Item Used = --->> Try again");
                    return false;
                }
            default:
                System.err.println("Game does not support \"casting\" for that!");
                return false;
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
