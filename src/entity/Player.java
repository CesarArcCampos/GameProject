package entity;

import java.awt.Graphics2D;
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

	public Player (Panel panel, KeyHandler keyHandler) {
		this.panel = panel;
		this.keyHandler = keyHandler;
		
		screenX = panel.screenWidth/2 - (panel.tileSize/2);
		screenY = panel.screenHeight/2 - (panel.tileSize/2);

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
				worldY -= speed;
			} else if (keyHandler.downPressed == true) {
				direction = "down";
				worldY += speed;
			} else if (keyHandler.leftPressed == true) {
				direction = "left";
				worldX -= speed;
			} else if (keyHandler.rightPressed == true) {
				direction = "right";
				worldX += speed;
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
