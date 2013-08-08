package domain;

import java.awt.Rectangle;

public class Brick {

	private int X;
	private int Y;
	private int Height;
	private int Width;
	private Boolean isHit;
	public Brick(int x,int y,int h, int w) {
		this.X = x;
		this.Y = y;
		this.Height = h;
		this.Width = w;
		this.isHit = false;
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
	public void stoneHit() {
		this.isHit = true;
	}
	public Rectangle getRectangle()
	{
		return new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());	 
	}
	public boolean isHit() {
		return isHit;
	}


}
