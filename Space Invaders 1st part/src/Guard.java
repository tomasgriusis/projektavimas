public class Guard {
	private static String icon = "□⌂□";
	private static String bullet = " ' ";
	private static int column = 10;
	
	public static String getIcon() {
		return icon;
	}
	
	public static String getBullet() {
		return bullet;
	}
	
	public static int getPosition() {
		return column;
	}
	
	public static void changePosition(int number) {
		if (column + number>0 && column + number<=48) {
			column += number;
		}
	}
	
	public static void print() {
		System.out.print(icon);
	}
}
