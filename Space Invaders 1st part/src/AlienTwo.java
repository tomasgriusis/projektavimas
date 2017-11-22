public class AlienTwo  extends Alien {
	private int points = 20;
	private String icon = " Φ ";
	
	public AlienTwo() {
		
	}
	
	@Override
	public int getPoints() {
		return points;
	} 
	
	@Override
	public void print() {
		System.out.print(icon);
	}
}
