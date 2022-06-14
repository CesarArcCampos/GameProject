package entity;

import java.awt.AlphaComposite;
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
	public int type;
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	
	// Character Status
	public int maxLife;
	public int life;
	
	
	public Entity(Panel panel) {
		this.panel = panel;
	}
	
	public void setAction() {}
	
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
	
	public void update() {
		
		setAction();
		
		collisionON = false;
		panel.checker.checkTile(this);
		panel.checker.checkObject(this, false);
		boolean contactPlayer = panel.checker.checkPlayer(this);
		panel.checker.checkEntity(this, panel.npc);
		panel.checker.checkEntity(this, panel.monster);
		
		if (this.type == 2 && contactPlayer == true) {
			if (panel.player.invincible == false) {
				panel.player.life -= 1;
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
			
			if (invincible == true) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			}
			
			g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
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
	

}
