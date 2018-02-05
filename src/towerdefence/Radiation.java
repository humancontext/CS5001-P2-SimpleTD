package towerdefence;

import java.util.ArrayList;
/**
 * Radiation tower class.
 * @author XignzhiYue CS5011 Student (xy31@st-andrews.ac.uk)
 * @version 1.0
 * @since 2017-10-19
 */
public class Radiation extends Tower {

	private int position;
	private String type = "Radiation tower";

	/**
	 * cost of this tower.
	 */
	public static final int COST = 200;

	/** Constructor.
	 *@param position of this tower
	 */
	public Radiation(int position) {
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
		return 2;
	}

	/** Getter of position.
	 *@return position
	 */
	public int getPosition() {
		return position;
	}

	/** Judge if this tower will fire.
	 * Catapult tower fire every 4 rounds.
	 *@param round is the game round
	 *@return boolean
	 */
	public boolean willFire(int round) {
		if (round % 4 == 0) {
			return true;
		}
		return false;
	}

	/** Get the symbol to represent this tower.
	 *@return char
	 */
	public char show() {
		return 'R';
	}

	/** The radiation fires as aoe damage influencing all the enemies before it.
	 *@param enemies is the list of all enemeis.
	 *@return int the coins player get from this attack.
	 */
	public int fire(ArrayList<Enemy> enemies) {
		int coins = 0;
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			if (e.getPosition() <= position) {
				e.hit(new Radiation(position));
				System.out.println(type + position + " hit " + e.getType() + e.getPosition() + ".\nEnemy's health is " + e.getHealth());
				if (e.getHealth() <= 0) {
					enemies.remove(e);
					coins += e.getCoins();
				}
			}
		}
		return coins;
	}

}
