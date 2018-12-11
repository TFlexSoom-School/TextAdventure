package mainPkg;
import java.util.Scanner;
import monsterous.*;
import character.*;

public class Combat {
	private static Scanner parameter;

	public static void PlayerPassive(Player p) {
		p.regen();
	}

	public static void PlayerTurn(Player p,Scanner consoleInput,Monster m) {
		System.out.print(p.getName() + " prepared himself...\nEnter action--");
		while(true){
			String userInput = consoleInput.nextLine
					().toLowerCase();
			if(userInput.contains("help")){
				System.out.println("                              =Available Actions=\n" 
						+ "--> Attack | Item<index> | Items | Scan | Spell<name> | Spells | Point Check <--");
			}else if(userInput.contains("Attack")){
				p.Attack(m);
				break;
			}else if(userInput.contains("Item<")){
				parameter = new Scanner(userInput);
				parameter.skip("<");
				if(p.use_item(parameter.nextInt())){
					break;
				}
				System.out.println("=Item Not Used= --->> Try again");
				
				
			}else if(userInput.contains("Scan")){
				
			}else if(userInput.contains("Spell<")){
				
			}else if(userInput.contains("Spell Book")){
				
			}else if(userInput.contains("Point Check")){
				
			}else{
				System.out.println("Unknown action please type help for list of actions\n");
			}
		}
	}

	public static void MonsterPassive(Monster m) {
		
	}

	public static void MonsterTurn(Monster m, Player p) {
		
	}

	public static void Loot(Player p) {
		
	}
	
	public static void closeScanner(){
		parameter.close();
	}

}
