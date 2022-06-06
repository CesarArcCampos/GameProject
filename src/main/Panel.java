package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
	KeyHandler keyHandler = new KeyHandler();
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	final int FPS = 60;
	
	public Panel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			update();
			
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime / 1000000);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		
		if (keyHandler.upPressed == true) {
			playerY -= playerSpeed;
		} else if (keyHandler.downPressed == true) {
			playerY += playerSpeed;
		} else if (keyHandler.leftPressed == true) {
			playerX -= playerSpeed;
		} else if (keyHandler.rightPressed == true) {
			playerX += playerSpeed;
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose();
	}
}
