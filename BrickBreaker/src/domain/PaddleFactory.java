package domain;

public class PaddleFactory {

	public Paddle CreatePaddle(int x, int y, int j, int l) {
		return new Paddle(x,y,j,l);		
	}

}
