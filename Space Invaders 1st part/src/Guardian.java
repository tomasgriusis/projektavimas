import java.util.Scanner;

public class Guardian extends Thread {
	static boolean stopThread = false;

	public Guardian() {
		
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while (!stopThread) {
			String position = scanner.next();
			switch (position) {
			case "a":
				Guard.changePosition(-1);
				break;
			case "d":
				Guard.changePosition(1);
				break;
			case "s":
				if (Game.bulletExists==false) {
					Game.bulletExists=true;
					Game.bulletColumn = Guard.getPosition();
				}
				break;
			}
		}
		
	}

}
