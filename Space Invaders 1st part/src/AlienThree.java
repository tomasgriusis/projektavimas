public class AlienThree  extends Alien {
	private int points = 30;
	private String icon = " Ω ";
	
	public AlienThree() {
		
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
