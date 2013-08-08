package ui.controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import ui.view.TextfieldPanel;
import ui.view.ImagePanel;
import domain.BrickBreakerGame;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton restart, pause, help;

	private TextfieldPanel text;
	private ImagePanel image;

	private BrickBreakerGame game;

	private NumberFormat formatterToDisplayTime;
	private Timer timer;
	private Timer swingTimer;
	private static final int TIMER_DELAY = 5;

	private int timeDisplay;

	public ControlPanel(TextfieldPanel text, ImagePanel image,BrickBreakerGame game) {
		setText(text);
		setImage(image);
		setGame(game);		
		formatterToDisplayTime = new DecimalFormat("00");
		createTimer();
		addKeyListener();
		createButtonsAndAddListeners();
		setUpPanelWitButtons();
	}

	private void setUpPanelWitButtons() {
		this.setLayout(new GridLayout(3, 1));
		this.add(help);
		this.add(restart);
		this.add(pause);
	}

	private void addKeyListener() {
		DirectionListener directionKeyListener = new DirectionListener();
		getImage().addKeyListener(directionKeyListener);
		getImage().requestFocusInWindow();
	}

	private void createTimer() {
		swingTimer = new Timer(TIMER_DELAY, new TimerHandler());	//Timer voor balletje en alles		
		timer = new Timer(1000,new TimerScreenHandler()); //Timer voor tijd
		timeDisplay = 0;
	}

	private void createButtonsAndAddListeners() {
		ButtonHandler action = new ButtonHandler();
		restart = new JButton("RESET");
		restart.addActionListener(action);
		pause = new JButton("START");
		pause.addActionListener(action);
		help = new JButton("HELP");
		help.addActionListener(action);
	}

	private void resetGame() {
		pauseGame();
		getGame().reset();
		timeDisplay = 0;
		updateText();
		getImage().updateBoard();
	}
	private void startTimers() {
		timer.start();
		swingTimer.start();
	}
	private void stopTimers() {
		timer.stop();
		swingTimer.stop();
	}
	private void startGame() {
		startTimers();
		getImage().requestFocusInWindow();
		pause.setText("PAUZE");
	}

	private void pauseGame() {
		stopTimers();
		getImage().requestFocusInWindow();
		pause.setText("START");
	}

	private void updateText() {
		getText().setTime("" + formatterToDisplayTime.format(timeDisplay / 60) + ":"+ formatterToDisplayTime.format(timeDisplay % 60));
		getText().setLives("" + getGame().getNumberOfLives());
		getText().setScore("" + getGame().getScore());
	}

	public BrickBreakerGame getGame() {
		return game;
	}

	private void setGame(BrickBreakerGame spel) {
		this.game = spel;
	}

	private TextfieldPanel getText() {
		return text;
	}

	private void setText(TextfieldPanel text) {
		this.text = text;
	}

	private ImagePanel getImage() {
		return image;
	}

	private void setImage(ImagePanel image) {
		this.image = image;
	}

	class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("RESET")) {
				resetGame();
			} else if (e.getActionCommand().equals("START")) {
				startGame();
			} else if (e.getActionCommand().equals("PAUZE")) {
				pauseGame();
			} else if (e.getActionCommand().equals("HELP")) {
				JOptionPane.showMessageDialog(null,"Press space bar to start the game, hit the ball with your paddle and try to get rid of all the bricks",	"INSTRUCTIONS", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	class DirectionListener implements KeyListener {

		public void keyPressed(KeyEvent event) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				System.out.println("ControlPanel: LEFT DETECTED");
				if(getGame().paddleWillNotCrossLeftBoundary() && swingTimer.isRunning())
				{
					getGame().movePaddleLeft(16);
				}
				break;
			case KeyEvent.VK_RIGHT:
				System.out.println("ControlPanel: RIGHT DETECTED");
				if(getGame().paddleWillNotCrossRightBoundary() && swingTimer.isRunning()) 
				{
					getGame().movePaddleRight(16);
				}
				break;
			case KeyEvent.VK_SPACE:
				System.out.println("ControlPanel: SPACE DETECTED");
				if(!getGame().getNumberOfLives().equals("0"))
				{
					startGame();
				}
				break;
			}
		}


		public void keyTyped(KeyEvent event) {
		}

		public void keyReleased(KeyEvent event) {
		}
	}
	class TimerScreenHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timeDisplay++;	
		}
	}
	class TimerHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {		
			if (getGame().isGameOver()) {
				stopTimers();
			}
			else if(getGame().ballTouchesGround())
			{
				getGame().resetPaddleAndBall();
				getGame().reduceLives();
				stopTimers();
			}
			else
			{
				getGame().ballTouchedSomething();
				getGame().getBall().move();
			}
			updateText();
			getImage().updateBoard();
		}

	}

}