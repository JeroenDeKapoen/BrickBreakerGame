package domain;

import java.awt.Rectangle;

public class Paddle {

	private int X;
	private int Y;
	private int Height;
	private int Width;
	public Paddle(int i, int j, int k, int l) {
		this.X = i;
		this.Y = j;
		this.Height = k;
		this.Width = l;
		
	}

	public int getX() {
		return this.X;
	}

	public int getY() {
		return this.Y;
	}

	public int getHeight() {
		return this.Height;
	}

	public int getWidth() {
		return this.Width;
	}
	public void moveLeft(int x){
		this.X -= x;
	}
	public void moveRight(int y){
		this.X += y;
	}
	public Rectangle getRectangle(){
		return new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());	 
	}
}
