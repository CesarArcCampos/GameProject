package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

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

	public EventHandler eHandler = new EventHandler(this);
	public KeyHandler keyHandler = new KeyHandler(this);
	TileManager tm = new TileManager(this);
	Sound music = new Sound();
	Sound sfx = new Sound();

	Thread gameThread;

	public Player player = new Player(this, keyHandler);
	public CollisionChecker checker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Entity obj[] = new Entity[20];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	public InteractiveTile iTile[] = new InteractiveTile[50];
	public UI ui = new UI(this);
	ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();

	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int warningState = 4;
	public final int characterState = 5;

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
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
		//playMusic(0);
		//stopMusic();
		gameState = titleState;

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

			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}

			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					if (monster[i].alive == true && monster[i].dying == false) {
						monster[i].update();
					}
					if (monster[i].alive != true) {
						monster[i].checkDrop();
						monster[i] = null;
					}
				}
			}
			
			for (int i = 0; i < projectileList.size(); i++) {
				if (projectileList.get(i) != null) {
					if (projectileList.get(i).alive == true) {
						projectileList.get(i).update();
					}
					if (projectileList.get(i).alive != true) {
						projectileList.remove(i);
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
			
			for (int i = 0; i < iTile.length; i++) {
				
				if (iTile[i] != null) {
					iTile[i].update();
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
			for (int i = 0; i < iTile.length; i++) {
				
				if (iTile[i] != null) {
					
					iTile[i].draw(g2);
				}
			}

			// ADD ENTITIES TO LIST
			entityList.add(player);

			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					entityList.add(npc[i]);
				}
			}

			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					entityList.add(obj[i]);
				}
			}

			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			
			for (int i = 0; i < projectileList.size(); i++) {
				if (projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
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
}
