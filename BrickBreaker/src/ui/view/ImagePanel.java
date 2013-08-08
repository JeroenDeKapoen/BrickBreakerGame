package ui.view;
import java.awt.*;
import javax.swing.*;

import domain.Ball;
import domain.Brick;
import domain.BrickBreakerGame;
import domain.Paddle;

public class ImagePanel extends JPanel{


	private static final long serialVersionUID = 1L;
    private BrickBreakerGame spel;

    public ImagePanel(BrickBreakerGame spel){

        setSpel(spel);

        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
        setBackground(Color.YELLOW);
        this.setFocusable(true);

        repaint();
    }

    public BrickBreakerGame getSpel() {
		return spel;
	}

	private void setSpel(BrickBreakerGame spel) {
		this.spel = spel;
	}

	public void paintComponent(Graphics g){

        super.paintComponent(g);
        paintField(g);
        paintBricksIfNotHit(g);
        paintPaddle(g);
        paintBall(g);
    }

	private void paintField(Graphics g) {
		int hoogte = getSpel().getHeight();
        
        g.setColor(Color.RED);
        g.drawLine(0, hoogte, getSpel().getWidth(), hoogte);
	}

	private void paintBricksIfNotHit(Graphics g) {
		int hoogte = getSpel().getHeight();
		for(int i = 0; i < getSpel().getNumberOfStones(); i++){
        	Brick steen = getSpel().getStone(i);
            if(!steen.isHit()){
                g.setColor(Color.ORANGE);
                g.fillRect(steen.getX(), 1+hoogte - steen.getY() - steen.getHeight(), steen.getWidth(), steen.getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(steen.getX(), 1+hoogte - steen.getY() - steen.getHeight(), steen.getWidth(), steen.getHeight());
            }
        }
	}

	private void paintPaddle(Graphics g) {
		int hoogte = getSpel().getHeight();
		Paddle plankje = getSpel().getPaddle();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(plankje.getX(), hoogte-plankje.getY() - plankje.getHeight(), plankje.getWidth(), plankje.getHeight());
        g.setColor(Color.BLUE);
        g.drawRect(plankje.getX(), hoogte-plankje.getY() - plankje.getHeight(), plankje.getWidth(), plankje.getHeight());
	}

	private void paintBall(Graphics g) {
		int hoogte = getSpel().getHeight();
		Ball bal = getSpel().getBall();
        g.setColor(Color.BLACK);
        g.fillOval(bal.getX() - bal.getRadius(), hoogte - bal.getY() - bal.getRadius(), 2*bal.getRadius(), 2*bal.getRadius());
	}

    public void updateBoard(){
        repaint();
    }

}


