package character;
import java.util.ArrayList;
import monsterous.Monster;

public class Mage extends Player {
	private ArrayList<String> SpellBook = new ArrayList<>();
	private int mana;

	public Mage(String n, int h, double damageModifier) {
		super(n, h, damageModifier);
		mana = 0;
		SpellBook.add("Fire Ball");
	}

	public String toString() {
		if (!super.getDead()) {
			return "The Intelligent Tai Lopez, " + super.toString();
		} else {
			return super.toString();
		}
	}
	
	public void regen(){
		
	}
	
	public int getMana(){
		return mana;
	}

	public void printSpells() {
		System.out.println("Tai Lopez characters regenerate mana used to cast spells each turn.");
		System.out.println(super.getName() + " replenishes " + super.getLevel() + " mana each turn");
		System.out.println(super.getName() + " can cast:");
		System.out.println(SpellBook.toString());
		super.printSpells();

	}
	
	public void spellCost(String spell){
		if (SpellBook.contains(spell)) {
			if (spell.equals("Fire Ball")) {
				System.out.println("Fire Ball costs 6 mana");
			}
			if (spell.equals("Ice Wave")) {
				System.out.println("Ice Wave costs " + super.getLevel()*2 + " mana");
			}
			if (spell.equals("Pyro Blast")) {
				System.out.println("Pyro Blast costs " + super.getLevel()*3 + " mana");
			}
			if (spell.equals("Mana Burn")) {
				System.out.println("Uses all mana in pool");
			}
		}
	}

	public void LevelUp() {
		super.levelUp();
		if (super.getLevel() == 2) {
			SpellBook.add("Ice Wave");
		}
		if (super.getLevel() == 6) {
			SpellBook.add("Pyro Blast");
		}
		if (super.getLevel() == 10) {
			SpellBook.add("Mana Burn");
		}
	}
	
	

	public void useSpell(String spell, Monster m) {
		if (SpellBook.contains(spell)) {
			if (spell.equals("Fire Ball")) {
				fireBall(m);
			}
			if (spell.equals("Ice Wave")) {
				iceWave(m);
			}
			if (spell.equals("Pyro Blast")) {
				pyroBlast(m);
			}
			if (spell.equals("Mana Burn")) {
				manaBurn(m);
			}
		}
	}

	private void fireBall(Monster m) {
		if (mana >= 6) {
			System.out.println(super.getName() + " casts Fire Ball on " + m.getName() + " for "
					+ (super.getLevel() + super.getMaxD()) + " damage");
			m.recieve_dmg(super.getLevel() + super.getMaxD());
		} else {
			System.out.println(super.getName() + " does not have 6 mana to cast Fire Ball");
		}
	}

	private void iceWave(Monster m) {
		if (mana >= 2 * super.getLevel()) {
			System.out.println(super.getName() + " casts Ice Wave on " + m.getName() + " for "
					+ super.getMinD() + " damage");
			m.recieve_dmg(super.getMinD());
			m.recieveDebuff("Frozen");
		} else {
			System.out.println(
					super.getName() + " does not have 2*Level(" + 2 * super.getLevel() + ") mana to cast Ice Wave");
		}
	}

	private void pyroBlast(Monster m) {
		if (mana >= 3 * super.getLevel()) {
			System.out.println(super.getName() + " casts Pyro Blast on " + m.getName() + " for "
					+ (2*(super.getLevel() + super.getMaxD())) + " damage");
			m.recieve_dmg(2*(super.getLevel() + super.getMaxD()));
		} else {
			System.out.println(
					super.getName() + " does not have 3*Level(" + 3 * super.getLevel() + ") mana to cast Pyro Blast");
		}
	}

	private void manaBurn(Monster m) {
		System.out.println(super.getName() + " casts Mana Burn on " + m.getName() + " for "
				+ mana + " damage");
		m.recieve_dmg(mana);
	}

}
