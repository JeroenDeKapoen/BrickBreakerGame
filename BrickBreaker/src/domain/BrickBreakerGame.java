package domain;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import db.Config;

public class BrickBreakerGame {

	private int breedte;
	private int hoogte;
	private Ball bal;
	private Paddle plank;
	private int stenen;
	private int levens;
	private int score;
	private Config config;
	private ArrayList<Brick> steentjes = new ArrayList<Brick>();
	public BrickBreakerGame(int breedte, int hoogte, Ball bal, Paddle plankje,int aantalstenen, int aantallevens, Config c) {
		this.config = c;
		this.breedte = breedte;
		this.hoogte = hoogte;
		this.bal = bal;
		this.plank = plankje;
		this.stenen = aantalstenen;
		this.levens = aantallevens;
		this.createStones();		
	}
	public void setPaddle(Paddle p)
	{
		this.plank = p;
	}
	public void setBall(Ball b)
	{
		this.bal = b;
	}
	public int getHeight() {
		return this.hoogte;
	}

	public int getWidth() {
		return this.breedte;
	}

	public Paddle getPaddle() {
		return this.plank;
	}

	public Ball getBall() {
		return this.bal;
	}

	public int getNumberOfStones() {
		return this.stenen;
	}
	public void createStones() {
		int y = this.getHeight() - 20;
		int x = 5;
		for(int i =0; i < this.getNumberOfStones(); i++)
		{
			steentjes.add(new Brick(x,y,15,50));
			if(x + 70 < this.getWidth())
			{
				x += 50;
			}
			else
			{
				x = 0;
				y -= 20;
			}
			//System.out.println("STONE POSITION: " + x + " : " + y);			
			x += 5;
		}
	}
	public Brick getStone(int i) {
		return steentjes.get(i);
	}

	public void reset() {
		this.setBall(null);	//Destroy oude bal
    	BallFactory balfac = new BallFactory(this.config);
    	this.setBall(balfac.CreateBall(200, 30, 10));	//TODO settings uitlezen, zou in BallFactory moeten
		this.setPaddle(null); //Destroy oud plankje
    	PaddleFactory paddlefac = new PaddleFactory();
    	this.setPaddle(paddlefac.CreatePaddle(150, 0, 20, 100));    
    	steentjes.clear();
    	this.createStones();    	
    	setScore(0);
    	this.levens = 3;
		
	}
	public void resetPaddleAndBall() {
		this.setBall(null);	//Destroy oude bal
    	BallFactory balfac = new BallFactory(this.config);
    	this.setBall(balfac.CreateBall(200, 30, 10));	//TODO settings uitlezen, zou in BallFactory moeten
		this.setPaddle(null); //Destroy oud plankje
    	PaddleFactory paddlefac = new PaddleFactory();
    	this.setPaddle(paddlefac.CreatePaddle(150, 0, 20, 100));   		
	}
	public String getNumberOfLives() {
		return Integer.toString(this.levens);
	}
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int s){
		this.score = s;
	}
	
	public boolean ballTouchesLeftWall() {
		if(this.getBall().getRectangle().intersectsLine(new Line2D.Double(5,0,5,this.hoogte)))
		{
			return true;
		}		
		return false;
	}
	public boolean ballTouchesRightWall() {
		if(this.getBall().getRectangle().intersectsLine(new Line2D.Double(this.breedte,0,this.breedte,this.hoogte)))
		{
			return true;
		}		
		return false;
	}
	public boolean ballTouchesRoof() {
		if(this.getBall().getRectangle().intersectsLine(new Line2D.Double(0,this.hoogte,this.breedte,this.hoogte))){
			return true;
		}
		return false;
	}
	public void reduceLives() {
		this.levens--;
	}
	public boolean ballTouchesGround() {
		if(this.getBall().getRectangle().intersectsLine(new Line2D.Double(0,0,this.breedte,0))){
	
			return true;
		}
		return false;
	}
	public boolean ballTouchesPaddle() {
		if(this.getBall().getRectangle().intersects(this.getPaddle().getRectangle()))
		{
			return true;
		}
		return false;
	}
	public boolean paddleWillNotCrossLeftBoundary() {
		if(this.getPaddle().getX() > 0)
		{
			return true;
		}
		return false;

	}
	public boolean ballHitABrick() {
		for(Brick b : steentjes)
		{
			if(this.getBall().getRectangle().intersects(b.getRectangle()) && !b.isHit())
			{
				b.stoneHit();
				getBall().onHit();
				return true;
			}
		}
		return false;
		
	}
	public void ballTouchedSomething() {
		if(this.ballTouchesLeftWall()){
			if(this.getBall().getDirection() == BallDirection.NOORDWEST){
				this.getBall().setDirection(BallDirection.NOORDOOST);
			}
			else
			{
				this.getBall().setDirection(BallDirection.ZUIDOOST);
			}
		}
		else if(this.ballTouchesRightWall())
		{
			System.out.println("BALL TOUCHES RIGHT WALL");
			if(this.getBall().getDirection() == BallDirection.NOORDOOST){
				this.getBall().setDirection(BallDirection.NOORDWEST);
			}
			else
			{
				this.getBall().setDirection(BallDirection.ZUIDWEST);
			}
		}

		else if(this.ballTouchesRoof())
		{
			System.out.println("BALL TOUCHED ROOF");
			if(this.getBall().getDirection() == BallDirection.NOORDOOST){
				this.getBall().setDirection(BallDirection.ZUIDOOST);
			}
			else
			{
				this.getBall().setDirection(BallDirection.ZUIDWEST);
			}
		}
		else if (this.ballTouchesPaddle())
		{
			if(this.getBall().getDirection() == BallDirection.ZUIDOOST){
				this.getBall().setDirection(BallDirection.NOORDOOST);
			}
			else
			{
				this.getBall().setDirection(BallDirection.NOORDWEST);
			}
		}
		else if(ballHitABrick())
		{
			this.setScore(getScore() + 50);
			if(this.getBall().getDirection() == BallDirection.NOORDOOST){
				this.getBall().setDirection(BallDirection.ZUIDOOST);
			}
			else
			{
				this.getBall().setDirection(BallDirection.ZUIDWEST);
			}
		}
	}
	public boolean paddleWillNotCrossRightBoundary() {
		if(this.getPaddle().getX() <= this.getWidth() - this.getPaddle().getWidth())
		{
			return true;
		}
		return false;
	}

	public void movePaddleLeft(int i) {
		this.getPaddle().moveLeft(i);
		
	}

	public void movePaddleRight(int i) {
		this.getPaddle().moveRight(i);		
	}

	public boolean zeroLivesLeft() {
		if(this.levens < 1){
			return true;
		}
		return false;
	}
	public boolean isGameOver() {
		return (this.ballTouchesGround() && this.zeroLivesLeft());
	}


}
