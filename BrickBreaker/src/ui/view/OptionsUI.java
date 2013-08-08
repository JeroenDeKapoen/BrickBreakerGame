package ui.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import db.Config;

public class OptionsUI extends JFrame implements ActionListener{
	
	Config config;
	public OptionsUI(Config c) throws IOException {
		config = c;
		setTitle("OPTIONS");
		//setSize(new Dimension(630,575));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        JComponent newContentPane = createButtonsBallOptions();
        newContentPane.setOpaque(true); //content panes must be opaque
        setContentPane(newContentPane);
        pack();
        setVisible(true); 
	}
	
	public JComponent createButtonsBallOptions() {
		JPanel buttons = new JPanel();
	    JRadioButton NormalBal = new JRadioButton("Normall bal");
	    NormalBal.setActionCommand("0");

	    JRadioButton FastBal = new JRadioButton("Fast bal");
	    FastBal.setActionCommand("1");

	    JRadioButton OtherDirBal = new JRadioButton("Other Direction bal");
	    OtherDirBal.setActionCommand("2");

	    
	    //Selecteer de huidige setting
	    switch(config.getConfig("Stonehit")){
	    case "0": NormalBal.setSelected(true); break;
	    case "1": FastBal.setSelected(true); break;
	    case "2": OtherDirBal.setSelected(true);break;
	    }
	    //Groupje maken
	    ButtonGroup group = new ButtonGroup();
	    group.add(NormalBal);
	    group.add(FastBal);
	    group.add(OtherDirBal);

	    //Luisteraars aanmaken
	    NormalBal.addActionListener(this);
	    FastBal.addActionListener(this);
	    OtherDirBal.addActionListener(this);
	   
	    
	    JPanel radioPanel = new JPanel(new GridLayout(0, 1));
	    radioPanel.add(NormalBal);
	    radioPanel.add(FastBal);
	    radioPanel.add(OtherDirBal);

	    buttons.add(radioPanel);
	    
	    return buttons;
	}
    public void actionPerformed(ActionEvent e) {
    	System.out.println("ER IETS GEBERURT" + e.getActionCommand());
    	config.setConfig("Stonehit", e.getActionCommand());
    	try {
			config.Save();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

}
