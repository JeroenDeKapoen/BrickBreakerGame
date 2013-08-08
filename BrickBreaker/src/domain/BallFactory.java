package domain;

import java.io.IOException;

import db.Config;

public class BallFactory {
	private Config config;
	public BallFactory(Config c)
	{
		this.config = c;
	}
	public Ball CreateBall(int x, int y, int r) {
		Ball bal = null;
		String type = "";
		type = config.getConfig("Stonehit");		
		switch (type) {
		case "0":
			bal = new NormalBall(x,y,r);
			break;
		case "1":
			bal = new FastBall(x,y,r);
			break;
		case "2":
			bal = new otherDirectionBall(x,y,r);
			break;
		default:
			bal = new NormalBall(x,y,r);
			break;
		
		}		
		return bal;		
	}
}
