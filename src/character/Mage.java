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
		if (!super.isDead()) {
			return "The Intelligent Mage, " + super.toString() + "\n----" + super.getName() + " currently has " + mana + " mana\n";
		} else {
			return super.toString();
		}
	}

	public void regen() {
		mana += super.getLevel();
	}

	public int getMana() {
		return mana;
	}

	public void printSpells() {
		System.out.println("Mage characters regenerate mana used to cast spells each turn.");
		System.out.println(super.getName() + " replenishes " + super.getLevel() + " mana each turn");
		System.out.println(super.getName() + " can cast:");
		System.out.println(SpellBook.toString());
		super.printSpells();

	}

	public boolean hasSpells(){
		return true;
	}

	public void spellCost(String spell) {
		if (SpellBook.contains(spell)) {
			if (spell.equals("Fire Ball")) {
				System.out.println("Fire Ball costs 6 mana");
				System.out.println("Does " + (super.getMaxD() + super.getMinD())/2 + " damage.");
			}
			if (spell.equals("Ice Wave")) {
				System.out.println("Ice Wave costs " + super.getLevel() * 2 + " mana");
				System.out.println("Does " + super.getMinD() + " damage.");
				System.out.println("2/3 chance to freeze enemy blocking atleast one attack");
			}
			if (spell.equals("Pyro Blast")) {
				System.out.println("Pyro Blast costs " + super.getLevel() * 3 + " mana");
				System.out.println("Does " + (2 * super.getMaxD()) + " damage");
			}
			if (spell.equals("Mana Burn")) {
				System.out.println("Uses all mana in pool dealing double mana as damage");
			}
		}else{
			System.out.println("Cannot find that in my spell book!");
		}
	}

	public void levelUp() {
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

	public boolean useSpell(String spell, Monster m) {
		if (SpellBook.contains(spell)) {
			if (spell.equals("Fire Ball")) {
				if (fireBall(m)) {
					return true;
				}
			} else if (spell.equals("Ice Wave")) {
				if (iceWave(m)) {
					return true;
				}
			} else if (spell.equals("Pyro Blast")) {
				if (pyroBlast(m)) {
					return true;
				}
			} else if (spell.equals("Mana Burn")) {
				manaBurn(m);
				return true;
			} else {
				System.out.println("<" + spell + ">");
				System.out.println("= Spell Unknown = --->> Check Spells");
			}
		}

		return false;
	}

	private boolean fireBall(Monster m) {
		if (mana >= 6) {
			System.out.println(super.getName() + " casts Fire Ball on " + m.getName() + " for "
					+ (super.getMaxD() + super.getMinD())/2 + " damage");
			m.recieve_dmg((super.getMaxD() + super.getMinD())/2);
			mana -= 6;
			return true;
		} else {
			System.out.println(super.getName() + " does not have 6 mana to cast Fire Ball");
			return false;
		}
	}

	private boolean iceWave(Monster m) {
		if (mana >= 2 * super.getLevel()) {
			System.out.println(
					super.getName() + " casts Ice Wave on " + m.getName() + " for " + super.getMinD() + " damage");
			m.recieve_dmg(super.getMinD());
			m.recieveDebuff("Frozen");
			mana -= 2* super.getLevel();
			return true;
		} else {
			System.out.println(
					super.getName() + " does not have 2*Level->(" + 2 * super.getLevel() + ") mana to cast Ice Wave");
			return false;
		}
	}

	private boolean pyroBlast(Monster m) {
		if (mana >= 3 * super.getLevel()) {
			System.out.println(super.getName() + " casts Pyro Blast on " + m.getName() + " for "
					+ (2 * super.getMaxD()) + " damage");
			m.recieve_dmg(2 * super.getMaxD());
			mana -= 3*super.getLevel();
			return true;
		} else {
			System.out.println(
					super.getName() + " does not have 3*Level->(" + 3 * super.getLevel() + ") mana to cast Pyro Blast");
			return false;
		}
	}

	private void manaBurn(Monster m) {
		System.out.println(super.getName() + " casts Mana Burn on " + m.getName() + " for " + mana*2 + " damage");
		m.recieve_dmg(mana*2);
		mana = 0;
	}

}
