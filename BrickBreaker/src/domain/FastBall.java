package domain;

public class FastBall extends NormalBall{
	Double faster = 0.25;
	public FastBall(int x, int y,int r) {
		super(x,y,r);
		System.out.println("FASTBALL");
	}
	
	public void onHit(){		
		faster += 0.25;
		System.out.println("SPEED INCREASED " + super.getSpeed() + " - " + faster);
		super.setSpeed((int)(1+faster));
	}
}
