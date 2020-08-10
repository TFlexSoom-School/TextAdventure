package character;
import monsterous.Monster;

public class Rogue extends Player {

	public Rogue(String n, int h, double damageModifier) {
		super(n, h, damageModifier);
	}
	
	public String toString(){
		if(!super.isDead()){
			return "The Sly Rogue, " + super.toString() + "\n";
		} else{
			return super.toString();
		}
	}
	
	public void regen(){
		
	}

	public void printSpells() {
		System.out.println("Rogue characters do not use spells and only have passive traits");
		System.out.println(super.getName() + " has a 1/4 chance to deal a second attack");
		if(super.getLevel() >= 2 && super.getLevel() < 10){
			System.out.println(super.getName() + " has a 1/3 chance to dodge each attack");
			super.printSpells();
			if(super.getLevel() >= 6 ){
				System.out.println(super.getName() + " has a 1/5 chance to apply a poison debuff");
			}
		}
		else if(super.getLevel() >= 10){
			System.out.println(super.getName() + "has a 1/3 chance to dodge the attack and 1/3 chance deal its damage to the monster");
			super.printSpells();
			System.out.println(super.getName() + "has a 1/5 chance to apply a poison debuff");
		}
		
	}

	public void spellCost(String spell){
		// Rogue does not cast Spells!
	}

	public boolean useSpell(String spell, Monster m){
		System.out.println("Rogues don't use spells... pfft!");
		return false;
	}

	public void attack(Monster m) {
		super.attack(m);
		if(super.getLevel() >= 6 && Math.random() > .8){
			m.recieveDebuff("Poisoned");
		}
		if (Math.random() > .74) {
			System.out.println(">>>" + super.getName() + " just got a second attack!!!");
			super.attack(m);
			if(super.getLevel() >= 6 && Math.random() > .8){
				m.recieveDebuff("Poisoned");
			}
		}
	}
	
	public void attacked(int damage, Monster m){
		if(super.getLevel() >= 2 && Math.random() >= .66){
			System.out.println(super.getName() + " just dodge the monster's attack");
			if(super.getLevel() >= 10 && Math.random() >= .66){
				System.out.println(super.getName() + " dealt a counter dealing " + damage + " damage to the monster");
				m.recieve_dmg(damage);
			}
		}
		else{
			super.recieve_dmg(damage);
		}
	}

}
