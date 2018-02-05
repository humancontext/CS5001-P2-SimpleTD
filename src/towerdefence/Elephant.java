package towerdefence;

/**
 * Lizard enemy class.
 * @author XignzhiYue CS5011 Student (xy31@st-andrews.ac.uk)
 * @version 1.0
 * @since 2017-10-19
 */
public class Elephant extends Enemy {
	private int health = 10;
	private int position = 0;
	private int step;

	/** Getter of the name of this enemy.
	 *@return String enemy type.
	 */
	public String getType() {
		return "Elephant";
	}

	/** Getter of the health of this enemy.
	 *@return int the health.
	 */
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	/** Getter of the position of this enemy.
	 *@return int the position.
	 */
	public int getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	/** Method that hit this enemy with a tower.
	 *@param t the tower
	 */
	public void hit(Tower t) {
		if (t.getPosition() >= position) {
			health -= t.getDamage();
		}
	}

	/** Method to move this enemy forward.
	 * elephant move 1 unit every 2 rounds.
	 */
	public void advance() {
		if (step == 1) {
			step = 0;
			position++;
		}
		else {
			step++;
		}
	}

	/** Getter of the coins of this enemy.
	 *@return int the coins.
	 */
	public int getCoins() {

		return 50;
	}
}
