package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Panel;
import main.UtilityTool;

public class Entity {
	
	Panel panel;
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public BufferedImage atack_up1, atack_up2, atack_up3, atack_up4,
						 atack_down1, atack_down2, atack_down3, atack_down4,
						 atack_right1, atack_right2, atack_right3, atack_right4,
						 atack_left1, atack_left2, atack_left3, atack_left4;
	public String direction = "down";
	
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionON = false;
	public int actionLockerCounter = 0;
	public boolean invincible = false;
	boolean attacking = false;
	public int invincibleCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	public boolean alive = true;
	public boolean dying = false;
	int dyingCounter = 0;
	public boolean hpBarOn = false;
	int hpBarCounter = 0;
	
	//TYPE
	public int type;
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_zombie = 2;
	public final int type_weapon = 3;
	public final int type_shield = 4;
	public final int type_consumable = 5;
	
	
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
	
	public BufferedImage image, image2, image3;
	public String name;
	public String name1;
	public boolean collision = false;
	
	// Character Status
	public int maxLife;
	public int life;
	
	// Item Attributes
	public int attackValue;
	public int defenseValue;
	public String description = "";
	
	
	public Entity(Panel panel) {
		this.panel = panel;
	}
	
	public void setAction() {}
	
	public void damageReaction() {}
	
	public void speak() {
		
		if (dialogues[dialogueIndex] == null) {
			String finalDialogue = "I don't have nothing more to say.";
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
	
	public void use(Entity entity) {
		
	}

	public void update() {
		
		setAction();
		
		collisionON = false;
		panel.checker.checkTile(this);
		panel.checker.checkObject(this, false);
		boolean contactPlayer = panel.checker.checkPlayer(this);
		panel.checker.checkEntity(this, panel.npc);
		panel.checker.checkEntity(this, panel.monster);
		
		if (this.type == type_zombie && contactPlayer == true) {
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
		
		if (collisionON == false) {
			
			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
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
		
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 40) {
				invincible = false;
				invincibleCounter = 0;
			}
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
			}
			
			g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);
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
			dying = false;
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
			int imageType = image.getType();
			image = uTool.scaleImage(image, width, height);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	

}
