package character;

import java.util.ArrayList;
import java.util.Random;
import monsterous.Monster;

public abstract class Player {
	// Fields:
	private String name;
	private int health;
	private int max_health;
	private int level;
	private int min_dmg;
	private int max_dmg;
	private boolean dead;
	private int attackBuffed;
	private double modifier;
	private ArrayList<String> inventory = new ArrayList<String>();

	// Constructor:
	public Player(String n, int h, double damageModifier) {
		name = n;
		health = h;
		level = 1;
		modifier = damageModifier;
		min_dmg = (int) (10 * damageModifier);
		max_dmg = (int) (20 * damageModifier);
		dead = false;
		attackBuffed = 0;
		inventory.add("empty");
		inventory.add("empty");
		inventory.add("empty");
	}

	// Accessors:
	public boolean getDead() {
		return dead;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return max_health;
	}

	public int getLevel() {
		return level;
	}

	public int getMinD() {
		return min_dmg;
	}

	public int getMaxD() {
		return max_dmg;
	}
	
	public int getBuffed(){
		return attackBuffed;
	}

	public String getInventory() {
		return inventory.toString();
	}

	// Methods:
	public String toString() {
		if (!dead) {
			return name + " has " + health + " health and is level " + level + ".\n" + name + " can do " + min_dmg
					+ " to " + max_dmg + " damage";
		} else {
			return name + " is dead ... GAME OVER";
		}
	}
	
	public abstract void regen();
	
	public void printSpells(){
		if(level >= 4){
			System.out.println(name + " has a 1/3 chance to heal himself with each attack");
		}
	}

	public void Attack(Monster m) {
		Random rand = new Random();
		int x = (rand.nextInt(max_dmg - min_dmg + 1) + min_dmg);
		if (attackBuffed > 0) {
			x = (int) (((double) (x)) * 1.5);
			attackBuffed--;
			if (attackBuffed == 0) {
				System.out.println(name + " is now calm once again");
			} else {
				System.out.println(name + "'s rage lives on");
			}
		}

		System.out.println(name + " did " + x + "damage to " + m.getName());
		
		if(level >= 4 && Math.random() >= .66){
			System.out.println(name + " obtained another wind and gained " + x/2 + " health!");
		}
		m.recieve_dmg(x);
	}

	//A method for the purposes of the dodge & enrage mechanic(See Rogue.java and Warrior.java)
	public void attacked(int damage, Monster m) {
		recieve_dmg(damage);
	}

	public void recieve_dmg(int damage) {
		if (health - damage <= 0) {
			health = 0;
			dead = true;
		} else {
			health -= damage;
		}
		System.out.println(name + " has " + health + " health left");
	}

	public void healHero(int healthGained) {
		if (healthGained > 0) {
			if (healthGained + health > max_health) {
				health = max_health;
			} else {
				health += healthGained;
			}
		}
	}

	public boolean use_item(int choice) {
		//returns true if item is used
		if (inventory.size() < choice) {
			String item = inventory.get(choice);
			if (item.equals("Health Potion")) {
				healHero(20);
				System.out.println(name + " just got healed 20 health");
				return true;
			} else if (item.equals("Damage Potion")) {
				attackBuffed = 3;
				System.out.println(name + " is ENRAGED!!!!");
				return true;
			} else if (item.equals("empty")) {
				System.out.println("That slot is empty");
			}
			return false;
		} else {
			System.out.println("index not in inventory");
			return false;
		}
	}

	public void levelUp() {
		level++;
		health += (int) (5 + (5 * modifier));
		max_health += (int) (5 + (5 * modifier));
		min_dmg += (int) (Math.random() * 10.5) + 5;
		max_dmg += (int) (Math.random() * 10.5) + 10;
	}
	
	public void depleteBuff(){	
		attackBuffed--;
		if (attackBuffed == 0) {
			System.out.println(name + " is now calm once again");
		} else {
			System.out.println(name + "'s rage lives on");
		}
		
	}
	
	public void addBuff(){
		attackBuffed = 2;
		System.out.println(name + " is ENRAGED!!!!");
	}
}
