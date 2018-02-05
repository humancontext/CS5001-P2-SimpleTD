package towerdefence;

/**
 * Abstract enemy class.
 * @author XignzhiYue CS5011 Student (xy31@st-andrews.ac.uk)
 * @version 1.0
 * @since 2017-10-19
 */
public abstract class Enemy {

	/** Getter of the name of this enemy.
	 *@return String enemy type.
	 */
	public abstract String getType();

	/** Getter of the coins of this enemy.
	 *@return int the coins.
	 */
	public abstract int getCoins();

	/** Getter of the health of this enemy.
	 *@return int the health.
	 */
	public abstract int getHealth();

	/** Getter of the position of this enemy.
	 *@return int the position.
	 */
	public abstract int getPosition();

	/** Method that hit this enemy with a tower.
	 *@param t the tower
	 */
	public abstract void hit(Tower t);

	/** Method to move this enemy forward.
	 */
	public abstract void advance();
}
