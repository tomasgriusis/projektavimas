public class AlienOne extends Alien {
	private int points = 10;
	private String icon = " Θ ";
	
	public AlienOne() {
		
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
