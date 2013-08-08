package domain;

public class otherDirectionBall extends NormalBall{

	public otherDirectionBall(int x, int y,int r) {
		super(x,y,r);
	}
	
	public void onHit() {
		if(super.getDirection() == BallDirection.NOORDOOST) {
			super.setDirection(BallDirection.ZUIDWEST);
		}else{
			//super.setDirection(super.getDirection() - 1);
		}
	}
}
