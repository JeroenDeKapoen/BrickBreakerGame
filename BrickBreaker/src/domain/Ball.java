package domain;

import java.awt.Rectangle;

public interface Ball {
	int getRadius();
	int getX();
	int getY();
	int getSpeed();
	BallDirection getDirection();
	void onHit();
	void move();
	Rectangle getRectangle();
	void setDirection(BallDirection i);
}
