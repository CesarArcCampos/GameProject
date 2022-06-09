package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class Panel extends JPanel implements Runnable {
	
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int MaxScreenColumn = 16;
	public final int MaxScreenRow = 12;
	
	public final int screenWidth = tileSize * MaxScreenColumn; //768 pixels
	public final int screenHeight = tileSize * MaxScreenRow; //576 pixels
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	KeyHandler keyHandler = new KeyHandler(this);
	TileManager tm = new TileManager(this);
	Sound music = new Sound();
	Sound sfx = new Sound();

	Thread gameThread;
	
	public Player player = new Player(this, keyHandler);
	public CollisionChecker checker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public SuperObject obj[] = new SuperObject[10];
	public UI ui = new UI(this);
	
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	
	final int FPS = 60;
	
	public Panel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void setUpGame() {
		
		aSetter.setObject();
		playMusic(0);
		//stopMusic();
		gameState = playState;
		
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
		
		if (gameState == playState) {
			player.update();
		}
		if (gameState == pauseState) {
			//NOTHING
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// TILES
		tm.draw(g2);
		
		// OBJECTS
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// PLAYER
		player.draw(g2);
		
		// UI
		ui.draw(g2);
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		
		music.stop();
	}
	
	public void playSFX(int i) {
		
		sfx.setFile(i);
		sfx.play();
	}
}
