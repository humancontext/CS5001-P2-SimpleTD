package towerdefence;

import java.util.ArrayList;

/**
 * Catapult tower class.
 * @author XignzhiYue CS5011 Student (xy31@st-andrews.ac.uk)
 * @version 1.0
 * @since 2017-10-19
 */
public class Catapult extends Tower {
	private int position;
	private String type = "Catapult tower";

	/**
	 * the cost of this tower.
	 */
	public static final int COST = 200;

	/** Constructor.
	 *@param position of this tower
	 */
	public Catapult(int position) {
		this.position = position;
	}

	/** Getter of type.
	 *@return type
	 */
	public String getType() {
		return type;
	}

	/** Getter of damage.
	 *@return damage
	 */
	public int getDamage() {
		return 5;
	}

	/** Getter of position.
	 *@return position
	 */
	public int getPosition() {
		return position;
	}

	/** Judge if this tower will fire.
	 * Catapult tower fire every 3 rounds.
	 *@param turn the count of game round.
	 *@return boolean
	 */
	public boolean willFire(int turn) {
		if (turn % 3 == 0) {
			return true;
		}
		return false;
	}

	/** Get the symbol to represent this tower.
	 *@return char
	 */
	public char show() {
		return 'C';
	}

	/** Find the optimal target of this tower and shoot.
	 * For catapult tower, the priority is Elephant > Lizard > Rat.
	 *@param enemies is the list of all enemeis.
	 *@return int the coins player get from this attack.
	 */
	public int fire(ArrayList<Enemy> enemies) {
		int coins = 0;
		int index = 0;
		boolean findIt = true;
		//looking for the first elephant
		while (!(enemies.get(index) instanceof Elephant) || enemies.get(index).getPosition() > position) {
			index++;
			if (index >= enemies.size()) {
				findIt = false;
				break;
			}
		}
		if (findIt) {
			Enemy e = enemies.get(index);
			e.hit(new Catapult(position));
			System.out.println(type + position + " hit " + e.getType() + e.getPosition() + ".\nEnemy's health is " + e.getHealth());
			if (e.getHealth() <= 0) {
				enemies.remove(e);
				coins += e.getCoins();
			}
		}
		//looking for the first lizard
		else {
			index = 0;
			findIt = true;
			while (!(enemies.get(index) instanceof Lizard) || enemies.get(index).getPosition() > position) {
				index++;
				if (index >= enemies.size()) {
					findIt = false;
					break;
				}
			}
			if (findIt) {
				Enemy e = enemies.get(index);
				e.hit(new Catapult(position));
				System.out.println(type + position + " hit " + e.getType() + e.getPosition() + ".\nEnemy's health is " + e.getHealth());
				if (e.getHealth() <= 0) {
					enemies.remove(e);
					coins += e.getCoins();
				}
			}
			//looking for the first rat
			else {
				index = 0;
				findIt = true;
				while (!(enemies.get(index) instanceof Elephant) || enemies.get(index).getPosition() > position) {
					index++;
					if (index >= enemies.size()) {
						findIt = false;
						break;
					}
				}
				if (findIt) {
					Enemy e = enemies.get(index);
					e.hit(new Catapult(position));
					System.out.println("Catapult tower" + position + " hit " + e.getType() + e.getPosition() + ".\nEnemy's health is " + e.getHealth());
					if (e.getHealth() <= 0) {
						enemies.remove(e);
						coins += e.getCoins();
					}
				}
			}
		}
		return coins;
	}


}
