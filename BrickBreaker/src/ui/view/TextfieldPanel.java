package ui.view;
import java.awt.*;
import javax.swing.*;


public class TextfieldPanel extends JPanel{

	private static final long serialVersionUID = 1L;

    private JTextField time, score, lives;
    private JLabel timeLabel, scoreLabel, livesLabel;

    public TextfieldPanel(){

        createFieldToDisplayTime();
        createFieldToDisplayScore();
        createFieldToDisplayNumberOfLives();

        JPanel timePanel = createPanelForTime();
        JPanel scorePanel = createPanelForScore();
        JPanel livesPanel = createPanelForNumberOfLives();

        this.setLayout(new GridLayout(3,1));
        this.add(timePanel);
        this.add(scorePanel);
        this.add(livesPanel);

    }

	private JPanel createPanelForNumberOfLives() {
		JPanel livesPanel = new JPanel();
        livesLabel = new JLabel();
        livesLabel.setForeground(Color.WHITE);
        livesLabel.setText("Lives: ");
        livesPanel.add(livesLabel);
        livesPanel.add(lives);
        livesPanel.setBackground(Color.BLUE);
		return livesPanel;
	}

	private JPanel createPanelForScore() {
		JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setText("Score: ");
        scorePanel.add(scoreLabel);
        scorePanel.add(score);
        scorePanel.setBackground(Color.BLUE);
		return scorePanel;
	}

	private JPanel createPanelForTime() {
		JPanel timePanel = new JPanel();
        timeLabel = new JLabel();
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setText("Time: ");
        timePanel.add(timeLabel);
        timePanel.add(time);
        timePanel.setBackground(Color.BLUE);
		return timePanel;
	}

	private void createFieldToDisplayNumberOfLives() {
		lives = new JTextField(8);
        lives.setEditable(false);
        lives.setFont(new Font("Arial", Font.BOLD, 20));
        lives.setText("3");
        lives.setBackground(Color.YELLOW);
        lives.setHorizontalAlignment(JTextField.CENTER);
	}

	private void createFieldToDisplayScore() {
		score = new JTextField(8);
        score.setEditable(false);
        score.setFont(new Font("Arial", Font.BOLD, 20));
        score.setText("0");
        score.setBackground(Color.YELLOW);
        score.setHorizontalAlignment(JTextField.CENTER);
	}

	private void createFieldToDisplayTime() {
		time = new JTextField(8);
        time.setEditable(false);
        time.setFont(new Font("Arial", Font.BOLD, 20));
        time.setText("00:00");
        time.setBackground(Color.YELLOW);
        time.setHorizontalAlignment(JTextField.CENTER);
	}

    public JTextField getLives() {
        return lives;
    }

    public void setLives(String lives) {
        this.lives.setText(lives);
    }

    public JTextField getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score.setText(score);
    }

    public JTextField getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time.setText(time);
    }

}
