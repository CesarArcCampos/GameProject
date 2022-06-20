package main;

import javax.swing.JFrame;

public class Main {
	
	public static JFrame window;

	public static void main(String[] args) {
		
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Project Game");
		
		Panel panel = new Panel();
		window.add(panel);
		
		panel.config.loadConfig();
		
		window.pack();
		
		//The location of the window is not specified. So it will be displayed at the center of the screen.
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		panel.setUpGame();
		
		panel.startGameThread();

	}

}
