package character;

import java.util.ArrayList;
import monsterous.Monster;
import java.util.Random;

public class Warrior extends Player {
	private ArrayList<String> SpellBook;
	private int armor;

	public Warrior(String n, int h, double damageModifier) {
		super(n, h, damageModifier);
		SpellBook = new ArrayList<>();
	}

	public int getArmor() {
		return armor;
	}

	public String toString() {
		if (!super.getDead()) {
			return "The Menacing John Cena, " + super.toString();
		} else {
			return super.toString();
		}
	}
	
	public void regen(){
		
	}

	public void printSpells() {
		System.out.println(
				"John Cena characters can deplete health to cast spells each turn\n     and they can add temporary armor to their health");
		System.out.println(super.getName()
				+ " has a chance to do extra damage with each attack which increases based on missing health");
		System.out.println(super.getName() + " currently has a "
				+ (int) ((super.getMaxHealth() - super.getHealth()) / 75 + 25) + "/100 chance to do critical damage");
		if (SpellBook.isEmpty()) {
			System.out.println(super.getName() + " can cast spells after level 2");
		} else {
			System.out.println(super.getName() + " can cast:");
			System.out.println(SpellBook.toString());
		}
		super.printSpells();
		if(super.getLevel() >= 6){
			System.out.println(super.getName() + " also has a 1/10 chance each time he is attacked to be enraged" );
		}

	}
	
	public void attacked(int damage, Monster m){
		if(super.getLevel() >= 6 && Math.random() >= .9){
			System.out.println(super.getName() + " just got enraged");
			super.addBuff();
		}
		recieve_dmg(damage);
	}
	
	public void recieve_dmg(int damage) {
		if (armor > 0) {
			armor -= damage;
		}

		if (armor < 0) {
			super.recieve_dmg(Math.abs(armor));
			armor = 0;
		}
	}

	public void Attack(Monster m) {
		if ((int) (Math.random() * 100) > (super.getMaxHealth() - super.getHealth()) / 75 + 25) {
			super.Attack(m);
		} else {
			System.out.println(super.getName() + " just got a critical strike!!!");
			Random rand = new Random();
			int x = (rand.nextInt(super.getMaxD() - super.getMinD() + 1) + super.getMinD());
			if (super.getBuffed() > 0) {
				x = (int) (((double) (x)) * 1.5);
				super.depleteBuff();
			}
			x = (int) (x * 1.3);
			System.out.println(super.getName() + " did " + x + "damage to " + m.getName());
			if(super.getLevel() >= 4 && Math.random() >= .66){
				System.out.println(super.getName() + " obtained another wind and gained " + x/2 + " health!");
			}
			m.recieve_dmg(x);
		}
	}

	public void LevelUp() {
		super.levelUp();
		if (super.getLevel() == 2) {
			SpellBook.add("Armor Up");
		}
		if (super.getLevel() == 10) {
			SpellBook.add("Battle Burst");
		}
	}

	public void useSpell(String spell, Monster m) {
		if (SpellBook.contains(spell)) {
			if (spell.equals("Armor Up")) {
				ArmorUp(m);
			}
			if (spell.equals("Battle Burst")) {
				BattleBurst(m);
			}
		}
	}
	
	public void spellCost(String spell){
		if (SpellBook.contains(spell)) {
			if (spell.equals("Armor Up")) {
				System.out.println("Armor Up is a free spell");
			}
			if (spell.equals("Battle Burst")) {
				System.out.println("Battle Burst costs 20 damage to yourself");
			}
		}
	}

	private void ArmorUp(Monster m) {
		System.out.println(super.getName() + " armored Up for 10 temporary health");
		armor = 10;
	}

	private void BattleBurst(Monster m) {
		recieve_dmg(20);
		System.out.println(super.getName().toUpperCase() + 
				" JUST DEALT 20 DAMAGE TO HIMSELF TO ATTACK 3 TIMES");
		for(int i = 0; i < 3; i ++){
			System.out.println();
			super.Attack(m);
		}
	}
}
