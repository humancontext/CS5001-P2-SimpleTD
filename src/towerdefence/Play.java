package towerdefence;

import java.io.IOException;
import java.util.Scanner;

/**
 * The class containing main method.
 * @author XignzhiYue CS5011 Student (xy31@st-andrews.ac.uk)
 * @version 1.0
 * @since 2017-10-19
 */
public class Play {
	/**
	 * the main method instantiate a game class and give initial state to the game.
	 * @param args not used.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.print("\033[H\033[2J");
		System.out.println("Length of the corridor:");
		Scanner in = new Scanner(System.in);
	    String s = in.nextLine();
	    int corridorLength = Integer.parseInt(s);

	    System.out.println("Initial money: (>100)");
		in = new Scanner(System.in);
	    s = in.nextLine();
	    int money = Integer.parseInt(s);
	    while (money <= 100) {
	    	System.out.println("Invalid.");
			in = new Scanner(System.in);
		    s = in.nextLine();
		    money = Integer.parseInt(s);
	    }

	    System.out.println("Number of elephants: (>=0)");
		in = new Scanner(System.in);
	    s = in.nextLine();
	    int elephantNum = Integer.parseInt(s);
	    while (elephantNum < 0) {
	    	System.out.println("Invalid.");
			in = new Scanner(System.in);
		    s = in.nextLine();
		    elephantNum = Integer.parseInt(s);
	    }

	    System.out.println("Number of rats: (>=0)");
		in = new Scanner(System.in);
	    s = in.nextLine();
	    int ratNum = Integer.parseInt(s);
	    while (ratNum < 0) {
	    	System.out.println("Invalid.");
			in = new Scanner(System.in);
		    s = in.nextLine();
		    ratNum = Integer.parseInt(s);
	    }

	    System.out.println("Number of lizards: (>=0)");
		in = new Scanner(System.in);
	    s = in.nextLine();
	    int lizardNum = Integer.parseInt(s);
	    while (lizardNum < 0) {
	    	System.out.println("Invalid.");
			in = new Scanner(System.in);
		    s = in.nextLine();
		    lizardNum = Integer.parseInt(s);
	    }

		Game g = new Game(corridorLength, money, elephantNum, ratNum, lizardNum);
		g.initializeTower();
		while (g.advance()) { }
		if (g.getEnemies().size() == 0) {
			System.out.println("Good Job!");
		}
		else {
			System.out.println("Sorry, you lost.");
		}
	}
}
