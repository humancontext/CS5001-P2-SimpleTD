package towerdefence;

import java.util.ArrayList;
/**
 * Abstract tower class.
 * @author XignzhiYue CS5011 Student (xy31@st-andrews.ac.uk)
 * @version 1.0
 * @since 2017-10-19
 */
public abstract class Tower {
	/** Getter of damage.
	 *@return damage
	 */
	public abstract int getDamage();

	/** Getter of position.
	 *@return position
	 */
	public abstract int getPosition();

	/** Judge if this tower will fire.
	 *@param round is the game round
	 *@return boolean
	 */
	public abstract boolean willFire(int round);

	/** Get the symbol to represent this tower.
	 *@return char
	 */
	public abstract char show();

	/** Find the optimal target of this tower and shoot.
	 *@param enemies is the list of all enemeis.
	 *@return int the coins player get from this attack.
	 */
	public abstract int fire(ArrayList<Enemy> enemies);
}
