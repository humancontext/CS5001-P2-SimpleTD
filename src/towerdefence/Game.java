package towerdefence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main game class.
 * @author XignzhiYue CS5011 Student (xy31@st-andrews.ac.uk)
 * @version 1.0
 * @since 2017-10-19
 */
public class Game {
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	private int corridorLength;
	private int money;
	private int elephantTotal;
	private int ratTotal;
	private int elephantCount;
	private int ratCount;
	private int eleStep;
	private int lizardTotal;
	private int lizardCount;
	private int gameRound;
	private int baseMoney = 100;

	/** Getter of the enemy list.
	 *@return ArrayList<Enemy>
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/** Constructor.
	 *@param corridorLength
	 *@param money
	 *@param elephantTotal
	 *@param ratTotal
	 *@param lizaardTotal
	 */
	public Game(int corridorLength, int money, int elephantTotal, int ratTotal, int lizardTotal) {
		this.corridorLength = corridorLength;
		this.money = money;
		this.elephantTotal = elephantTotal;
		this.ratTotal = ratTotal;
		this.lizardTotal = lizardTotal;
	}

	/**
	 * To advance the game, calls methods to.
	 * 			produce new enemies.
	 * 			let towers hit enemies.
	 * 			let the player decide whether to build new towers.
	 * 			move enemies forward.
	 * @return boolean whether the game is over.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public boolean advance() throws InterruptedException, IOException {
		// counting the round of the game
		System.out.print("\033[H\033[2J");
		gameRound++;
		produceEnemy();
		showGame();
		System.out.println("====================================");
		System.out.println("Tower hittings:");
		int temp = hitEnemy();

		//judge if the game is over
		if (temp == -1) {
			return false;
		}
		money += temp;
		System.out.println("====================================");

		// if the money is enough, ask player to build new towers.
		if (money >= baseMoney) {
			System.out.println("You Now have $" + money + "\nDo you want to build a new tower? (y/n)");
			Scanner in = new Scanner(System.in);
		    String s = in.nextLine();
		    while (s.equals("n") && s.equals("y")) {
		    	System.out.println("Invalid input.");
		    	in = new Scanner(System.in);
		    	s = in.nextLine();
		    }
		    if (s.equals("y")) {
		    	buildTower();
				showGame();
				Thread.sleep(1000);
				
		    }
		    else {
		    	baseMoney += 100;
		    }
		}
		// judge if the game is over.
		if (enemies.size() == 0) {
			if (elephantCount == elephantTotal && ratCount == ratTotal && lizardCount == lizardTotal) {
				return false;
			}
		}
		// pause the screen and let the game show.
		Thread.sleep(1000);
		System.out.print("\033[H\033[2J");

		// move the enemy
		moveEnemy();
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getPosition() == corridorLength) {
				return false;
			}
		}
		return true;
	}

	/**
	 * move all enemies forward.
	 */
	public void moveEnemy() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).advance();
		}
	}

	/**
	 * for each tower, judge if it can shoot at this round.
	 * if it can, calling the fire method for each tower.
	 * @return int the coins collected from dead enemies.
	 */
	public int hitEnemy() {
		int coins = 0;
		for (int i = 0; i < towers.size(); i++) {
			Tower t = towers.get(i);
			if (t.willFire(gameRound)) {
				coins += t.fire(enemies);
			}
			if (enemies.size() == 0 && elephantCount == elephantTotal && ratCount == ratTotal && lizardCount == lizardTotal) {
				return -1;
			}
		}
		return coins;
	}

	/**
	 * produce new enemies.
	 */
	public void produceEnemy() {
		if (ratCount < ratTotal) {
			enemies.add(new Rat());
			ratCount++;
		}
		if (elephantCount < elephantTotal) {
			if (eleStep == 1) {
				enemies.add(new Elephant());
				elephantCount++;
				eleStep = 0;
			}
			else {
				eleStep++;
			}
		}
		if (lizardCount < lizardTotal) {
			enemies.add(new Lizard());
			lizardCount++;
		}
	}

	/**
	 * let the player build towers with the initiate money.
	 */
	public void initializeTower() {
		while (money >= 100 && towers.size() <= corridorLength) {
			System.out.print("\033[H\033[2J");
			System.out.println("Catapult Tower costs $" + Catapult.COST);
			System.out.println("Slingshot Tower costs $" + Slingshot.COST);
			System.out.println("Radiation Tower costs $" + Radiation.COST);
			showGame();
			buildTower();
		}
	}

	/**
	 * building towers.
	 */
	public void buildTower() {
		// pick the tower type
		System.out.println("Which tower do you want to build? (C/S/R)");
		Scanner in = new Scanner(System.in);
	    String type = in.nextLine();
	    while (!type.equals("C") && !type.equals("S") && !type.equals("R") || type.equals("C") && money < 200 || type.equals("R") && money < 200) {
	    	System.out.println("Invalid input.");
	    	in = new Scanner(System.in);
		    type = in.nextLine();
	    }

	    // pick the location
	    System.out.println("Where do you want to build this tower? (input an number between 0 and " + (corridorLength - 1) + ")");
	    in = new Scanner(System.in);
	    String s = in.nextLine();
	    int position = Integer.parseInt(s);
	    boolean ocuppied = false;
	    for (int i = 0; i < towers.size(); i++) {
	    	if (towers.get(i).getPosition() == position) {
	    		ocuppied = true;
	    	}
	    }
	    while (position < 0 || position >= corridorLength || ocuppied) {
	    	System.out.println("Invalid position.");
	    	in = new Scanner(System.in);
		    s = in.nextLine();
		    position = Integer.parseInt(s);
		    ocuppied = false;
		    for (int i = 0; i < towers.size(); i++) {
		    	if (towers.get(i).getPosition() == position) {
		    		ocuppied = true;
		    	}
		    }
	    }
	    switch (type) {
	    	case "C":
	    		towers.add(new Catapult(position));
	    		money -= Catapult.COST;
	    		break;
	    	case "S":
	    		towers.add(new Slingshot(position));
	    		money -= Slingshot.COST;
	    		break;
	    	case "R":
	    		towers.add(new Radiation(position));
	    		money -= Radiation.COST;
	    		break;
	    	default:
	    		break;
	    }
	}
	/**
	 * show the gameboard with current money, the game round.
	 * living enemies.
	 */
	public void showGame() {
		System.out.println("Current money: $" + money);
		System.out.println("Round:" + gameRound);
		System.out.println("Number of enemies alive:" + enemies.size());
		System.out.print("Position:	|");
		for (int i = 0; i < corridorLength; i++) {
			System.out.print(" ");
			if (i < 10) {
				System.out.print(0);
			}
			System.out.print(i);
		}
		System.out.println();
		showTower();
		showEnemy();
	}

	/**
	 * show the tower line.
	 */
	public void showTower() {
		char[] towerArray = new char[corridorLength * 3];
		for (int i = 0; i < towerArray.length; i++) {
			towerArray[i] = ' ';
		}
		for (int i = 0; i < towers.size(); i++) {
			Tower t = towers.get(i);
			towerArray[t.getPosition() * 3 + 1] = t.show();
		}
		System.out.print("Tower:		|");
		for (int i = 0; i < towerArray.length; i++) {
			System.out.print(towerArray[i]);
		}
		System.out.println();
	}

	/**
	 * show the enemy lines.
	 * enemies are splited in several lines to avoid overlapping.
	 */
	public void showEnemy() {
		char[] elephantArray = new char[corridorLength * 3];
		char[] ratArray = new char[corridorLength * 3];
		char[] lizardArray = new char[corridorLength * 3];
		for (int i = 0; i < corridorLength * 3; i++) {
			elephantArray[i] = ' ';
			ratArray[i] = ' ';
			lizardArray[i] = ' ';
		}
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			if (e instanceof Rat) {
				ratArray[e.getPosition() * 3 + 1] = 'R';
			}
			if (e instanceof Elephant) {
				elephantArray[e.getPosition() * 3 + 1] = 'E';
			}
			if (e instanceof Lizard) {
				lizardArray[e.getPosition() * 3 + 1] = 'L';
			}
		}
		System.out.print("Elephant:	|");
		for (int i = 0; i < corridorLength * 3; i++) {
			System.out.print(elephantArray[i]);
		}
		System.out.println();

		System.out.print("Rat:		|");
		for (int i = 0; i < corridorLength * 3; i++) {
			System.out.print(ratArray[i]);
		}
		System.out.println();

		System.out.print("Lizard:		|");
		for (int i = 0; i < corridorLength * 3; i++) {
			System.out.print(lizardArray[i]);
		}
		System.out.println();
	}
}