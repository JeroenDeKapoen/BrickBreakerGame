package domain;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class NormalBall implements Ball {

	private Point2D.Double coord;
	private int speed;
	private BallDirection dir = BallDirection.NOORDOOST;
	private int r;
	public NormalBall(int x, int y,int r) {
		this.coord = new Point2D.Double(x,y);
		this.r = r;
		this.speed = 1;
	}

	@Override
	public int getRadius() {
		return this.r;
	}

	@Override
	public int getX() {
		return (int)this.coord.getX();
	}

	@Override
	public int getY() {
		return (int)this.coord.getY();
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public BallDirection getDirection() {
		return this.dir;
	}
	public void setDirection(BallDirection d){
		this.dir = d;
		
	}
	public void onHit() {
		//Empty in normal
	}
	public void setSpeed(int s){
		this.speed = s;
	}
	
	public void move(){	
		switch(getDirection()){
			case NOORDOOST:
				this.coord.setLocation(this.getX() + this.getSpeed(), this.getY() + this.getSpeed());
				break;
			case NOORDWEST:
				this.coord.setLocation(this.getX() - this.getSpeed(), this.getY() + this.getSpeed());
				break;
			case ZUIDOOST:
				this.coord.setLocation(this.getX() + this.getSpeed(), this.getY() - this.getSpeed());
				break;
			case ZUIDWEST:
				this.coord.setLocation(this.getX() - this.getSpeed(), this.getY() - this.getSpeed());
				break;
			default: 
				this.setDirection(BallDirection.NOORDOOST);
				break;
				
		}

		//System.out.println("BALL MOVEMENT!" + this.getDirection() + this.getX() + this.getY());
	}
	
	public Rectangle getRectangle(){
		//Rectangle om intersection mogelijk te maken.
		return new Rectangle(this.getX(),this.getY(),this.r*2,this.r*2);		
	}

}
