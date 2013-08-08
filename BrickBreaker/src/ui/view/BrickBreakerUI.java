package ui.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import db.Config;
import domain.BallFactory;
import domain.BrickBreakerGame;
import domain.Paddle;
import domain.Ball;
import domain.PaddleFactory;
import ui.controller.ControlPanel;

public class BrickBreakerUI extends JFrame{

	private static final long serialVersionUID = 1L;
    private Config config;
    public void start() throws IOException{
    	config = new Config();
    	setSize(new Dimension(630,575));
    	
    	JLabel titleLabel = createTitleLabel();
    	BrickBreakerGame game = createGame();
    	
    	ImagePanel image = new ImagePanel(game);
    	JPanel titlePanel = createTitlePanel(titleLabel);
        JPanel infoPanel = createInfoPanel(image, game);
        JPanel mainPanel = createMainPanel(titlePanel, infoPanel, image);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setVisible(true);

    }

	private BrickBreakerGame createGame() throws IOException {
		int breedte = 400;
    	int hoogte = 500;
    	
    	BallFactory balfac = new BallFactory(config);
    	Ball bal = balfac.CreateBall(200, 30, 10);	
    	PaddleFactory paddlefac = new PaddleFactory();
    	Paddle plankje = paddlefac.CreatePaddle(150, 0, 20, 100); //TODO settings uitlezen, in paddlefactory
    	int aantalstenen = 16;
    	int aantallevens = 3;
    	
    	return new BrickBreakerGame(breedte, hoogte, bal, plankje, aantalstenen, aantallevens, config);
	}

	private JPanel createMainPanel(JPanel titlePanel, JPanel infoPanel, ImagePanel image) {
		JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.EAST);
        mainPanel.add(image, BorderLayout.CENTER);
		return mainPanel;
	}

	private JPanel createInfoPanel(ImagePanel image, BrickBreakerGame game) {
		TextfieldPanel text = new TextfieldPanel();
		ControlPanel control = new ControlPanel(text, image, game, config);
        
		JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(text, BorderLayout.NORTH);
        infoPanel.add(control, BorderLayout.SOUTH);
        infoPanel.setBackground(Color.BLUE);
		return infoPanel;
	}

	private JPanel createTitlePanel(JLabel titleLabel) {
		JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.BLUE);
		return titlePanel;
	}

	private JLabel createTitleLabel() {
		JLabel titleLabel = new JLabel("BrickBreaker");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		return titleLabel;
	}
    
}



