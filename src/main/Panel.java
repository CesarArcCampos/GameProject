package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class Panel extends JPanel implements Runnable {

	//Screen settings
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int MaxScreenColumn = 20;
	public final int MaxScreenRow = 12;
	public final int screenWidth = tileSize * MaxScreenColumn; //960 pixels
	public final int screenHeight = tileSize * MaxScreenRow; //576 pixels

	//World settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxMap = 10;
	public int currentMap = 0;

	//FPS
	final int FPS = 60;

	//System
	public TileManager tm = new TileManager(this);
	public KeyHandler keyHandler = new KeyHandler(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	Sound music = new Sound();
	Sound sfx = new Sound();
	public CollisionChecker checker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Config config = new Config(this);
	public PathFinder pFinder = new PathFinder(this);
	EnvironmentManager eManager = new EnvironmentManager(this);
	
	//Entity and Object
	public Player player = new Player(this, keyHandler);
	public Entity obj[][] = new Entity[maxMap][200];
	public Entity npc[][] = new Entity[maxMap][10];
	public Entity monster[][] = new Entity[maxMap][20];
	public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
	ArrayList<Entity> entityList = new ArrayList<>();
	public Entity projectile[][] = new Entity[maxMap][200];
	public ArrayList<Entity> particleList = new ArrayList<>();
	
	
	// Game State
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int warningState = 4;
	public final int characterState = 5;
	public final int optionState = 6;
	public final int gameOverState = 7;
	public final int transitionState = 8;
	public final int tradeState = 9;
	public final int chatState = 10;


	public Panel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void setUpGame() {

		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
		gameState = titleState;
		eManager.setup();

	}

	public void retry() {
		
		stopSFX();
		playMusic(0);
		player.setDefaultPositions();
		player.restoreLifeAndBullets();
		aSetter.setNPC();
		aSetter.setMonster();
	}

	public void restart() {
		
		if (sfx.clip != null) {
			stopSFX();
		}
		player.setDefaultValues();
		player.restoreLifeAndBullets();
		player.setItems();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
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

			for (int i = 0; i < npc[1].length; i++) {
				if (npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}

			for (int i = 0; i < monster[1].length; i++) {
				if (monster[currentMap][i] != null) {
					if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
						monster[currentMap][i].update();
					}
					if (monster[currentMap][i].alive != true) {
						monster[currentMap][i].checkDrop();
						monster[currentMap][i] = null;
					}
				}
			}

			for (int i = 0; i < projectile[1].length; i++) {
				if (projectile[currentMap][i] != null) {
					if (projectile[currentMap][i].alive == true) {
						projectile[currentMap][i].update();
					}
					if (projectile[currentMap][i].alive != true) {
						projectile[currentMap][i] = null;
					}
				}
			}

			for (int i = 0; i < particleList.size(); i++) {
				if (particleList.get(i) != null) {
					if (particleList.get(i).alive == true) {
						particleList.get(i).update();
					}
					if (particleList.get(i).alive != true) {
						particleList.remove(i);
					}
				}
			}

			for (int i = 0; i < iTile[1].length; i++) {

				if (iTile[currentMap][i] != null) {
					iTile[currentMap][i].update();
				}
			}		
		}

		if (gameState == pauseState) {
			//NOTHING
		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// TITLE SCREEN		
		if ( gameState == titleState) {
			ui.draw(g2);

		} else {

			// DRAW TILES
			tm.draw(g2);

			// DRAW INTERACTIVE TILES
			for (int i = 0; i < iTile[1].length; i++) {

				if (iTile[currentMap][i] != null) {

					iTile[currentMap][i].draw(g2);
				}
			}

			// ADD ENTITIES TO LIST
			entityList.add(player);

			for (int i = 0; i < npc[1].length; i++) {
				if (npc[currentMap][i] != null) {
					entityList.add(npc[currentMap][i]);
				}
			}

			for (int i = 0; i < obj[1].length; i++) {
				if (obj[currentMap][i] != null) {
					entityList.add(obj[currentMap][i]);
				}
			}

			for (int i = 0; i < monster[1].length; i++) {
				if (monster[currentMap][i] != null) {
					entityList.add(monster[currentMap][i]);
				}
			}

			for (int i = 0; i < projectile[1].length; i++) {
				if (projectile[currentMap][i] != null) {
					entityList.add(projectile[currentMap][i]);
				}
			}

			for (int i = 0; i < particleList.size(); i++) {
				if (particleList.get(i) != null) {
					entityList.add(particleList.get(i));
				}
			}

			//SORT THE ENTITIES LIST
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {

					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});
			
			//DRAW ENTITIES

			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}

			//ENVIRONMENT
			eManager.draw(g2);
			
			// DRAW UI
			ui.draw(g2);

			//CLEAR ENTITIES LIST
			entityList.clear();

		}

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

	public void stopSFX() {	
		
		sfx.stop();
	}


}


