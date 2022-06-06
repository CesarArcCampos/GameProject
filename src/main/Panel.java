package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable {
	
	final int originalTileSize = 16;
	final int scale = 3;
	final int tileSize = originalTileSize * scale;
	final int MaxScreenColumn = 16;
	final int MaxScreenRow = 12;
	
	final int screenWidth = tileSize * MaxScreenColumn; //768 pixels
	final int screenHeight = tileSize * MaxScreenRow; //576 pixels
	
	Thread gameThread;
	
	public Panel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}


	@Override
	public void run() {
		
		while(gameThread != null) {
			System.out.println("The loop is running");
		}
	}
}
