package monsterous;
import java.util.Random;

import character.Player;

public class Monster {

	// Fields
	private int health;
	private final int min_dmg;
	private final int max_dmg;
	private String name;
	private String type;
	private int poisoned;
	private int frozen;
	private boolean dead;

	// Constructor
	public Monster(int h, int miD, int maD, String n, String t) {
		health = h;
		min_dmg = miD;
		max_dmg = maD;
		name = n;
		type = t;
		poisoned = 0;
		frozen = 0;
		dead = false;
	}

	// Accessors:
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}

	public int getMaxD() {
		return max_dmg;
	}

	public int getMinD() {
		return min_dmg;
	}

	public int getHealth() {
		return health;
	}

	public boolean getDead() {
		return dead;
	}

	// Monster Action Methods:
	public String toString() {
		if (dead == false) {
			return name + " is a " + type + ", has " + health
					+ " health and\ncan do " + min_dmg + " to " + max_dmg
					+ " damage";
		} else {
			return name + " has been slain by the hero";
		}
	}

	public void attack(Player p) {
		if(frozen > 0){
			System.out.println(name + " could not attack because it was frozen\n");
			frozen --;
			if(frozen == 0){
				System.out.println(name + " is no longer frozen");
			}
			else{
				System.out.println(name + " is still frozen for " + frozen + " turns.");
			}
			
		}else {
			Random rand = new Random();
			int x = (rand.nextInt(max_dmg - min_dmg + 1) + min_dmg);
			System.out.println(name + " the " + type + " did " + x
					+ " damage to " + p.getName() + "\n");
			p.attacked(x, this);
		}
	}

	public void debuffs() {
		if (poisoned > 0) {
			System.out.println(name + " has taken damage from poison");
			recieve_dmg((int) (health * .05));
			poisoned--;
			if (poisoned == 0) {
				System.out.println(name + " is no longer poisoned");
			}
			else{
				System.out.println(name + " is still poisoned");
			}
		}
	}

	public void recieve_dmg(int damage) {
		if (health - damage <= 0) {
			health = 0;
			dead = true;
		} else {
			health -= damage;
		}
		System.out.println(name + " the " + type + " has " + health
				+ " health left");
	}

	public void recieveDebuff(String debuff) {
		Random rand = new Random();
		if (debuff.equals("Poisoned")) {
			poisoned = rand.nextInt(3) + 1;
		}
		if (debuff.equals("Frozen")) {
			frozen = rand.nextInt(3);
			if (frozen > 0) {
				System.out.println("--" + getName() + " is frozen for " + frozen
						+ " turns--\n");
			} else {
				System.out.println(getName() + " did not freeze");
			}
		}
	}
}
