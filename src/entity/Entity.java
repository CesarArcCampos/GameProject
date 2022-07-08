package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.Panel;
import main.UtilityTool;

public class Entity {

	Panel panel;

	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public BufferedImage atack_up1, atack_up2, atack_up3, atack_up4,
	atack_down1, atack_down2, atack_down3, atack_down4,
	atack_right1, atack_right2, atack_right3, atack_right4,
	atack_left1, atack_left2, atack_left3, atack_left4;
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public Rectangle solidArea = new Rectangle(8,16,30,30);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public String dialogues[] = new String[20];

	//STATE
	public int worldX, worldY;
	public String direction = "down";
	public int dialogueIndex = 0;
	public boolean collisionON = false;
	public boolean invincible = false;
	boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;
	public boolean hadDialogue = false;

	//TYPE
	public int type;
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_zombie = 2;
	public final int type_weapon = 3;
	public final int type_shield = 4;
	public final int type_consumable = 5;
	public final int type_pickUpOnly = 6;
	public final int type_obstacle = 7;

	//CHARACTER ATTRIBUTES
	public String name;
	public int defaultSpeed;
	public String name1;
	public int speed;
	public int maxLife;
	public int life;
	public int maxBullets;
	public int bullets;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public Entity currentWeapon;
	public Entity currentShield;
	public Projectile projectile;

	public BufferedImage image, image2, image3;

	public boolean collision = false;

	// Item Attributes
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	public int value;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int price;
	public int knockBackPower = 0;
	public boolean stackable = false;
	public int amount = 1;
	public boolean finalDialogue = false;
	public boolean automatic;

	//Counter
	int dyingCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter;
	public int weaponShotAvailableCounter;
	public int actionLockerCounter = 0;
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	int hpBarCounter = 0;
	int knockBackCounter = 0;

	public Entity(Panel panel) {
		this.panel = panel;
	}

	public int getLeftX() {
		return worldX + solidArea.x;
	}
	
	public int getRightX() {
		return worldX + solidArea.x + solidArea.width;
	}
	
	public int getTopY() {
		return worldY + solidArea.y;
	}
	
	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}
	
	public int getCol() {
		return (worldX + solidArea.x)/panel.tileSize;
	}
	
	public int getRow() {
		return (worldY + solidArea.y)/panel.tileSize;
	}
	
	public void setAction() {}

	public void damageReaction() {}

	public void speak() {

		if (dialogues[dialogueIndex] == null) {
			String finalDialogue = "I don't have \nnothing more to say.";
			panel.ui.currentDialogue = finalDialogue;
		} else {
			panel.ui.currentDialogue = dialogues[dialogueIndex];
			dialogueIndex++;
		}

		switch(panel.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}

	public void interact() {
		
	}
	
	public boolean use(Entity entity) {
		return false;
	}

	public void checkDrop() {

	}

	public void dropItem(Entity entity) {

		for (int i = 0; i < panel.obj[1].length; i++) {
			if (panel.obj[panel.currentMap][i] == null) {
				panel.obj[panel.currentMap][i] = entity;
				panel.obj[panel.currentMap][i].worldX = worldX;
				panel.obj[panel.currentMap][i].worldY = worldY;
				break;
			}
		}
	}

	public Color getParticleColor() {
		Color color = null;
		return color;
	}

	public int getParticleSize() {
		int size = 0;
		return size;
	}

	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}

	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}

	public void generateParticle (Entity generator, Entity target) {

		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();

		Particle p1 = new Particle(panel, target, color, size, speed, maxLife, -2, -1);
		Particle p2 = new Particle(panel, target, color, size, speed, maxLife, 2, -1);
		Particle p3 = new Particle(panel, target, color, size, speed, maxLife, -2, 1);
		Particle p4 = new Particle(panel, target, color, size, speed, maxLife, 2, 1);
		panel.particleList.add(p1);
		panel.particleList.add(p2);
		panel.particleList.add(p3);
		panel.particleList.add(p4);
	}

	public void checkCollision() {

		collisionON = false;
		panel.checker.checkTile(this);
		panel.checker.checkObject(this, false);
		panel.checker.checkEntity(this, panel.npc);
		panel.checker.checkEntity(this, panel.monster);
		panel.checker.checkEntity(this, panel.iTile);
		boolean contactPlayer = panel.checker.checkPlayer(this);

		if (this.type == type_zombie && contactPlayer == true) {
			damagePlayer(attack);
		}
	}

	public void update() {

		if (knockBack == true) {
			
			checkCollision();
			
			if (collisionON == true) {
				
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			} else if (collisionON == false) {
				
				switch(panel.player.direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			knockBackCounter++;
			
			if (knockBackCounter == 10) {
				knockBackCounter = 0;
				knockBack = false;
			
			}
			
		} else {

			setAction();
			checkCollision();

			if (collisionON == false) {

				switch (direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}	
			}
		}

		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNumber == 1) {
				spriteNumber = 2;
			} else if (spriteNumber == 2) {
				spriteNumber = 1;
			}
			spriteCounter = 0;
		}

		if (invincible == true && name != "Boss" && hadDialogue == false) {
			invincibleCounter++;
			if (invincibleCounter > 40) {
				this.invincible = false;
				invincibleCounter = 0;
			}
		}
		
		if (invincible == true && name == "Boss" && hadDialogue == true) {
			invincibleCounter++;
			if (invincibleCounter > 40) {
				this.invincible = false;
				invincibleCounter = 0;
			}
		}
	}

	public void damagePlayer(int attack) {

		if (panel.player.invincible == false) {
			panel.playSFX(5);

			int damage = attack - panel.player.defense;
			if (damage < 0) {
				damage = 0;
			}

			panel.player.life -= damage;
			panel.player.invincible = true;
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		int screenX = worldX - panel.player.worldX + panel.player.screenX;
		int screenY = worldY - panel.player.worldY + panel.player.screenY;

		if (worldX + panel.tileSize > panel.player.worldX - panel.player.screenX &&
				worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
				worldY + panel.tileSize > panel.player.worldY - panel.player.screenY &&
				worldY - panel.tileSize < panel.player.worldY + panel.player.screenY) {

			switch(direction) {
			case "up":
				if(spriteNumber == 1) {image = up1;}
				if(spriteNumber == 2) {image = up2;}	
				break;
			case "down":
				if(spriteNumber == 1) {image = down1;}
				if(spriteNumber == 2) {image = down2;}	
				break;
			case "left":
				if(spriteNumber == 1) {image = left1;}
				if(spriteNumber == 2) {image = left2;}	
				break;
			case "right":
				if(spriteNumber == 1) {image = right1;}
				if(spriteNumber == 2) {image = right2;}	
				break;
			}

			//Zombie health bar
			if (type == 2 && hpBarOn == true) {

				double oneScale = (double) panel.tileSize/maxLife;
				double hpBarValue = oneScale*life;

				g2.setColor(new Color(139,0,0));
				g2.fillRect(screenX - 1, screenY - 16, panel.tileSize + 2, 12);
				g2.setColor(new Color(50,205,50));
				g2.fillRect(screenX , screenY - 15, (int) hpBarValue, 10);

				hpBarCounter++;

				if (hpBarCounter > 300) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}

			if (invincible == true) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2,0.5f);
			}
			if (dying == true) {
				dyingAnimation(g2);
				if (name == "Boss") {
					panel.stopMusic();
					panel.gameState = panel.endState;
				}
			}

			g2.drawImage(image, screenX, screenY, null);
			changeAlpha(g2,1f);
		}
	}

	public void dyingAnimation(Graphics2D g2) {

		dyingCounter++;
		int i = 5;

		if (dyingCounter <= i) {changeAlpha(g2,0f);}
		if (dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2,1f);}
		if (dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2,0f);}
		if (dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2,1f);}
		if (dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2,0f);}
		if (dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2,1f);}
		if (dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2,0f);}
		if (dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2,1f);}
		if (dyingCounter > i*8) {
			alive = false;
		}
	}

	public void changeAlpha (Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}

	public BufferedImage setup(String imagePath, int width, int height) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	public void searchPath(int goalCol, int goalRow) {

		int startCol = (worldX + solidArea.x)/panel.tileSize;
		int startRow = (worldY + solidArea.y)/panel.tileSize;

		panel.pFinder.setNode(startCol, startRow, goalCol, goalRow);

		if (panel.pFinder.search() == true) {

			//next worldX and worldY

			int nextX = panel.pFinder.pathList.get(0).col * panel.tileSize;
			int nextY = panel.pFinder.pathList.get(0).row * panel.tileSize;

			//Entity solidArea Position
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			//int enRightX = worldX + solidArea.x;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			//int enBottomY = worldY + solidArea.y;

			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + panel.tileSize) {
				direction = "up";
			}
			else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + panel.tileSize) {
				direction = "down";
			}
			else if (enTopY >= nextY && enBottomY < nextY + panel.tileSize) {

				if (enLeftX > nextX) {
					direction = "left";
				}
				if (enLeftX < nextX) {
					direction = "right";
				}	
			}
			else if (enTopY > nextY && enLeftX > nextX) {
				direction = "up";
				checkCollision();

				if (collisionON == true) {
					direction = "left";
				}
			}
			else if (enTopY > nextY && enLeftX < nextX) {
				direction = "up";
				checkCollision();

				if (collisionON == true) {
					direction = "right";
				}
			}
			else if (enTopY < nextY && enLeftX > nextX) {
				direction = "down";
				checkCollision();

				if (collisionON == true) {
					direction = "left";
				}
			}
			else if (enTopY < nextY && enLeftX < nextX) {
				direction = "down";
				checkCollision();

				if (collisionON == true) {
					direction = "right";
				}
			}
		}
	}

	public int getDetected(Entity user, Entity target[][], String targeName) {
		
		int index = 999;
		
		//check the surrounding objects
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		
		switch(user.direction) {
		case "up": nextWorldY = user.getTopY() - 1; break;
		case "down": nextWorldY = user.getBottomY() + 1; break;
		case "left": nextWorldX = user.getLeftX() - 1; break;
		case "right": nextWorldX = user.getRightX() + 1; break;	
		}
		
		int col = nextWorldX/panel.tileSize;
		int row = nextWorldY/panel.tileSize;
		
		for (int i = 0; i < target[1].length; i++) {
			if (target[panel.currentMap][i] != null) {
				if (target[panel.currentMap][i].getCol() == col &&
						target[panel.currentMap][i].getRow() == row &&
						target[panel.currentMap][i].name.equals(targeName)) {
					
					index = i;
					break;
				}
			}
		}
		
		return index;
	}
}
