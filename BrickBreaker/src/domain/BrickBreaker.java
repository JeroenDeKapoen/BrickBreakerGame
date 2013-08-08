package domain;

import java.io.IOException;

import ui.view.BrickBreakerUI;

public class BrickBreaker {
	  public static void main(String args[]) {
		  BrickBreakerUI g = new BrickBreakerUI();
		  try {
			g.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
