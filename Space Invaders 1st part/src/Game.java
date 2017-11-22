import java.util.concurrent.TimeUnit;

public class Game {

	static int points = 0;
	static boolean bulletExists = false;
	static int bulletColumn = 0;
	
	static boolean invadersOne1Exists = true;
	static boolean invadersOne2Exists = true;
	static boolean invadersTwo1Exists = true;
	static boolean invadersTwo2Exists = true;
	static boolean invadersThreeExists = true;
	static int notEmptyLines = 5;
	
	static int addLine = 1;
	static int addColumn = 0;
	static int bulletLine = 16;
	
	static Alien invaderEmpty = new Alien();
	static Alien invaderOne = new AlienOne();
	static Alien invaderTwo = new AlienTwo();
	static Alien invaderThree = new AlienThree();
	static Alien[] invadersOne1 = {invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne};
	static Alien[] invadersOne2 = {invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne, invaderOne};
	static Alien[] invadersTwo1 = {invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo};
	static Alien[] invadersTwo2 = {invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo, invaderTwo};
	static Alien[] invadersThree = {invaderThree, invaderThree, invaderThree, invaderThree, invaderThree, invaderThree, invaderThree, invaderThree, invaderThree, invaderThree, invaderThree};
		
	
	public static void main(String[] args) {
		
		Guardian guardian = new Guardian();
		guardian.start();
		while(true) {
			
			isGameOver();
			
			forward();
			
			backward();
			
			addColumn++;
		}
	}
	
	public static void sleep() {
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void forward() {
		for (int i=0; i<19; i++) {
			for (int j=0; j<50; j++) {
				if (j==0 || j==49) {
					System.out.print("|");
				}
				else if (i==0 || i==18) {
					System.out.print("---");
				}
				
				else if (j-addColumn>=0 && j-addColumn<11 && i-addLine>=0  && i-addLine<notEmptyLines) {
					if (i-addLine==0 && invadersThreeExists) {
						invadersThree[j-addColumn].print();
					}
					if (i-addLine==1 && invadersTwo2Exists) {
						invadersTwo2[j-addColumn].print();
					}
					if (i-addLine==2 && invadersTwo1Exists) {
						invadersTwo1[j-addColumn].print();
					}
					if (i-addLine==3 && invadersOne2Exists) {
						invadersOne2[j-addColumn].print();
					}
					if (i-addLine==4 && invadersOne1Exists) {
						invadersOne1[j-addColumn].print();
					}
				}
				else {
					
					if (i!=0 && i!=18) {
						if (bulletExists && i==bulletLine && j==bulletColumn) {
							System.out.print(Guard.getBullet());
							bulletLine -= 2;
							if (bulletLine-1 == 5+addLine || bulletLine-2 == 5+addLine || bulletLine == 5+addLine) {
								try {
									if (invadersOne1[bulletColumn-addColumn] != invaderEmpty) {
										invadersOne1[bulletColumn-addColumn]=invaderEmpty;
										points +=invaderOne.getPoints();
										invadersOne1Exists = false;
										for (int k=0; k<12; k++) {
											if (invadersOne1[k] != invaderEmpty) {
												invadersOne1Exists = true;
												break;
											}
											else {
												notEmptyLines = 4;
											}
										}
									}
									else if (invadersOne2[bulletColumn-addColumn] != invaderEmpty) {
										invadersOne2[bulletColumn-addColumn]=invaderEmpty;
										points +=invaderOne.getPoints();
										invadersOne2Exists = false;
										for (int k=0; k<12; k++) {
											if (invadersOne2[k] != invaderEmpty) {
												invadersOne2Exists = true;
												break;
											}
											else {
												notEmptyLines = 3;
											}
										}
									}
									else if (invadersTwo1[bulletColumn-addColumn] != invaderEmpty){
										invadersTwo1[bulletColumn-addColumn]=invaderEmpty;
										points +=invaderTwo.getPoints();
										invadersTwo1Exists = false;
										for (int k=0; k<12; k++) {
											if (invadersTwo1[k] != invaderEmpty) {
												invadersTwo1Exists = true;
												break;
											}
											else {
												notEmptyLines = 2;
											}
										}
									}
									else if (invadersTwo2[bulletColumn-addColumn] != invaderEmpty){
										invadersTwo2[bulletColumn-addColumn]=invaderEmpty;
										points +=invaderTwo.getPoints();
										invadersTwo2Exists = false;
										for (int k=0; k<12; k++) {
											if (invadersTwo2[k] != invaderEmpty) {
												invadersTwo2Exists = true;
												break;
											}
											else {
												notEmptyLines = 1;
											}
										}
									}
									else if (invadersThree[bulletColumn-addColumn] != invaderEmpty){
										invadersThree[bulletColumn-addColumn]=invaderEmpty;
										points +=invaderThree.getPoints();
										invadersThreeExists = false;
										for (int k=0; k<12; k++) {
											if (invadersThree[k] != invaderEmpty) {
												invadersThreeExists = true;
												break;
											}
											else {
												System.out.println("Game completed! Collected points: "+points);
												Guardian.stopThread = true;
												System.exit(0);
											}
										}
									}
									else {
										bulletLine = 16; 
										bulletExists = false;
									}
									bulletLine = 16; 
									bulletExists = false;
								} catch (Exception e) {
									
								}
							}
							if (bulletLine == 1 || bulletLine == 2) {
								bulletLine = 16; 
								bulletExists = false;
							}
						}
						
						else if (j==Guard.getPosition() && i==17) {
							Guard.print();
						}
						else {
							System.out.print("   ");
						}
					}
				}
				if (j==49) {
					System.out.println("\n");
				}
				
			}
		}
	}
	
	
	public static void backward() {
		if (addColumn==38) {
			sleep();
			addLine++;
			while (addColumn!=0) {
				for (int i=0; i<19; i++) {
					for (int j=0; j<50; j++) {
						if (j==0 || j==49) {
							System.out.print("|");
						}
						else if (i==0 || i==18) {
							System.out.print("---");
						}
						else if (j-addColumn>=0 && j-addColumn<11 && i-addLine>=0  && i-addLine<notEmptyLines) {
							if (i-addLine==0 && invadersThreeExists) {
								invadersThree[j-addColumn].print();
							}
							if (i-addLine==1 && invadersTwo2Exists) {
								invadersTwo2[j-addColumn].print();
							}
							if (i-addLine==2 && invadersTwo1Exists) {
								invadersTwo1[j-addColumn].print();
							}
							if (i-addLine==3 && invadersOne2Exists) {
								invadersOne2[j-addColumn].print();
							}
							if (i-addLine==4 && invadersOne1Exists) {
								invadersOne1[j-addColumn].print();
							}
						}
						else {
							if (i!=0 && i!=18) {
								if (bulletExists && i==bulletLine && j==bulletColumn) {
									System.out.print(Guard.getBullet());
									bulletLine -= 2;
									if (bulletLine-1 == 5+addLine || bulletLine-2 == 5+addLine) {
										try {
											if (invadersOne1[bulletColumn-addColumn] != invaderEmpty) {
												invadersOne1[bulletColumn-addColumn]=invaderEmpty;
												points +=invaderOne.getPoints();
												invadersOne1Exists = false;
												for (int k=0; k<12; k++) {
													if (invadersOne1[k] != invaderEmpty) {
														invadersOne1Exists = true;
														break;
													}
													else {
														notEmptyLines = 4;
													}
												}
											}
											else if (invadersOne2[bulletColumn-addColumn] != invaderEmpty) {
												invadersOne2[bulletColumn-addColumn]=invaderEmpty;
												points +=invaderOne.getPoints();
												invadersOne2Exists = false;
												for (int k=0; k<12; k++) {
													if (invadersOne2[k] != invaderEmpty) {
														invadersOne2Exists = true;
														break;
													}
													else {
														notEmptyLines = 3;
													}
												}
											}
											else if (invadersTwo1[bulletColumn-addColumn] != invaderEmpty){
												invadersTwo1[bulletColumn-addColumn]=invaderEmpty;
												points +=invaderTwo.getPoints();
												invadersTwo1Exists = false;
												for (int k=0; k<12; k++) {
													if (invadersTwo1[k] != invaderEmpty) {
														invadersTwo1Exists = true;
														break;
													}
													else {
														notEmptyLines = 2;
													}
												}
											}
											else if (invadersTwo2[bulletColumn-addColumn] != invaderEmpty){
												invadersTwo2[bulletColumn-addColumn]=invaderEmpty;
												points +=invaderTwo.getPoints();
												invadersTwo2Exists = false;
												for (int k=0; k<12; k++) {
													if (invadersTwo2[k] != invaderEmpty) {
														invadersTwo2Exists = true;
														break;
													}
													else {
														notEmptyLines = 1;
													}
												}
											}
											else if (invadersThree[bulletColumn-addColumn] != invaderEmpty){
												invadersThree[bulletColumn-addColumn]=invaderEmpty;
												points +=invaderThree.getPoints();
												invadersThreeExists = false;
												for (int k=0; k<12; k++) {
													if (invadersThree[k] != invaderEmpty) {
														invadersThreeExists = true;
														break;
													}
													else {
														System.out.println("Game completed! Collected points: "+points);
														Guardian.stopThread = true;
														System.exit(0);
													}
												}
											}
											else {
												bulletLine = 16; 
												bulletExists = false;
											}
											bulletLine = 16; 
											bulletExists = false;
										} catch (Exception e) {
											
										}
									}
									if (bulletLine == 1 || bulletLine == 2) {
										bulletLine = 16; 
										bulletExists = false;
									}
								}
								
								else if (j==Guard.getPosition() && i==17) {
									Guard.print();
								}
								else {
									System.out.print("   ");
								}
							}
						}
						if (j==49) {
							System.out.println("\n");
						}
					}
				}
				sleep();
				addColumn--;
			}
			addLine++;
		}
		else {
			sleep();
		}
	}
	
	
	public static void isGameOver() {
		if (invadersOne1Exists==true && addLine==12) {
			System.out.println("Game over! Collected points: "+points);
			Guardian.stopThread = true;
			System.exit(0);
		}
		else if (invadersOne2Exists==true && addLine==13) {
			System.out.println("Game over! Collected points: "+points);
			Guardian.stopThread = true;
			System.exit(0);
		}	
		else if (invadersTwo1Exists==true && addLine==14) {
			System.out.println("Game over! Collected points: "+points);
			Guardian.stopThread = true;
			System.exit(0);
		}
		else if (invadersTwo2Exists==true && addLine==15) {
			System.out.println("Game over! Collected points: "+points);
			Guardian.stopThread = true;
			System.exit(0);
		}
	}
}
