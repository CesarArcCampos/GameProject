package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Panel;

public class Player extends Entity {

	Panel panel;
	KeyHandler keyHandler;
	
	public final int screenX;
	public final int screenY;
	int hasKey = 0;
	int hasChest = 0;

	public Player (Panel panel, KeyHandler keyHandler) {
		this.panel = panel;
		this.keyHandler = keyHandler;
		
		screenX = panel.screenWidth/2 - (panel.tileSize/2);
		screenY = panel.screenHeight/2 - (panel.tileSize/2);
		
		solidArea = new Rectangle(16,16,16,16);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {

		worldX = panel.tileSize * 23;
		worldY = panel.tileSize * 21;
		speed = 4;
		direction = "down";
	}

	public void getPlayerImage() {

		try {

			up1 = ImageIO.read(getClass().getResourceAsStream("/player/survivor-up.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/survivor-up.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/survivor-down.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/survivor-down.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/survivor-right.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/survivor-right.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/survivor-left.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/survivor-left.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

		if(keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {

			if (keyHandler.upPressed == true) {
				direction = "up";
			} else if (keyHandler.downPressed == true) {
				direction = "down";	
			} else if (keyHandler.leftPressed == true) {
				direction = "left";	
			} else if (keyHandler.rightPressed == true) {
				direction = "right";
			}
			
			// CHECK TILE COLLISION
			collisionON = false;
			panel.checker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objIndex = panel.checker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// IF THERE IS NO COLLISION, PLAYER CAN MOVE
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
		}
	}
	
	public void pickUpObject(int i) {
		
		if (i != 999) {
			
			String objectName = panel.obj[i].name;
			
			switch (objectName) {
			case "Key":
				panel.playSFX(2);
				hasKey++;
				panel.obj[i] = null;
				break;
			case "Gate":
				if (hasKey > 0) {
					panel.playSFX(1);
					panel.obj[i] = null;
					hasKey--;
				}
				break;
			case "Chest":
				panel.playSFX(2);
				hasChest++;
				panel.obj[i] = null;
				break;
			}
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch(direction) {
		case "up":
			if(spriteNumber == 1) {
				image = up1;
			}
			if(spriteNumber == 2) {
				image = up2;
			}	
			break;
		case "down":
			if(spriteNumber == 1) {
				image = down1;
			}
			if(spriteNumber == 2) {
				image = down2;
			}	
			break;
		case "left":
			if(spriteNumber == 1) {
				image = left1;
			}
			if(spriteNumber == 2) {
				image = left2;
			}	
			break;
		case "right":
			if(spriteNumber == 1) {
				image = right1;
			}
			if(spriteNumber == 2) {
				image = right2;
			}	
			break;
		}

		g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);

	}



}
