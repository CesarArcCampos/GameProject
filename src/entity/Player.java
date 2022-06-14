package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.KeyHandler;
import main.Panel;

public class Player extends Entity {

	KeyHandler keyHandler;

	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	public int hasChest = 0;
	int standCounter = 0;

	public Player (Panel panel, KeyHandler keyHandler) {

		super(panel);

		this.keyHandler = keyHandler;

		screenX = panel.screenWidth/2 - (panel.tileSize/2);
		screenY = panel.screenHeight/2 - (panel.tileSize/2);

		solidArea = new Rectangle(16,16,16,16);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		attackArea.width = 25;
		attackArea.height = 25;

		setDefaultValues();
		getPlayerImage();
		getAttackImage();
	}

	public void setDefaultValues() {

		worldX = panel.tileSize * 23;
		worldY = panel.tileSize * 21;
		speed = 4;
		direction = "down";

		maxLife = 6;
		life = maxLife;
	}

	public void getPlayerImage() {

		up1 = setup("/player/survivor-up", panel.tileSize, panel.tileSize);
		up2 = setup("/player/survivor-up1", panel.tileSize, panel.tileSize);
		up3 = setup("/player/survivor-up2", panel.tileSize, panel.tileSize);
		down1 = setup("/player/survivor-down", panel.tileSize, panel.tileSize);
		down2 = setup("/player/survivor-down1", panel.tileSize, panel.tileSize);
		down3 = setup("/player/survivor-down2", panel.tileSize, panel.tileSize);
		right1 = setup("/player/survivor-right", panel.tileSize, panel.tileSize);
		right2 = setup("/player/survivor-right1", panel.tileSize, panel.tileSize);
		right3 = setup("/player/survivor-right2", panel.tileSize, panel.tileSize);
		left1 = setup("/player/survivor-left", panel.tileSize, panel.tileSize);
		left2 = setup("/player/survivor-left1", panel.tileSize, panel.tileSize);
		left3 = setup("/player/survivor-left2", panel.tileSize, panel.tileSize);
	}

	public void getAttackImage() {

		atack_up1 = setup("/player/atack_up1", panel.tileSize*2, panel.tileSize);
		atack_up2 = setup("/player/atack_up2", panel.tileSize*2, panel.tileSize);
		atack_up3 = setup("/player/atack_up3", panel.tileSize*2, panel.tileSize);
		atack_up4 = setup("/player/atack_up4", panel.tileSize*2, panel.tileSize);
		atack_down1 = setup("/player/atack_down1", panel.tileSize*2, panel.tileSize);
		atack_down2 = setup("/player/atack_down2", panel.tileSize*2, panel.tileSize);
		atack_down3 = setup("/player/atack_down3", panel.tileSize*2, panel.tileSize);
		atack_down4 = setup("/player/atack_down4", panel.tileSize*2, panel.tileSize);
		atack_right1 = setup("/player/atack_right1", panel.tileSize, panel.tileSize*2);
		atack_right2 = setup("/player/atack_right2", panel.tileSize, panel.tileSize*2);
		atack_right3 = setup("/player/atack_right3", panel.tileSize, panel.tileSize*2);
		atack_right4 = setup("/player/atack_right4", panel.tileSize, panel.tileSize*2);
		atack_left1 = setup("/player/atack_left1", panel.tileSize, panel.tileSize*2);
		atack_left2 = setup("/player/atack_left2", panel.tileSize, panel.tileSize*2);
		atack_left3 = setup("/player/atack_left3", panel.tileSize, panel.tileSize*2);
		atack_left4 = setup("/player/atack_left4", panel.tileSize, panel.tileSize*2);

	}

	public void update() {
		
		if (attacking == true) {
			attacking();
		} 
		else if(keyHandler.upPressed == true || keyHandler.downPressed == true ||
				keyHandler.leftPressed == true || keyHandler.rightPressed == true ||
				keyHandler.enterPressed == true) {

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

			// CHECK NPC COLLISION
			int npcIndex = panel.checker.checkEntity(this, panel.npc);
			interactNPC(npcIndex);

			// CHECK ZOMBIE COLLISION
			int monsterIndex = panel.checker.checkEntity(this, panel.monster);
			contactMonster(monsterIndex);

			// CHECK EVENT
			panel.eHandler.checkEvent();

			// IF THERE IS NO COLLISION, PLAYER CAN MOVE
			if (collisionON == false && keyHandler.enterPressed == false) {

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

			panel.keyHandler.enterPressed = false;

			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNumber == 1) {
					spriteNumber = 2;
				} else if (spriteNumber == 2) {
					spriteNumber = 3;
				} else if (spriteNumber == 3) {
					spriteNumber = 1;
				}
				spriteCounter = 0;
			}
		}

		//Way to fix problem when player touch zombie, all the life is drain, because the 
		//update method is called 60 times per second, so there is 60 contacts per second.
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	
	public void attacking() {
		
		spriteCounter++;
		
		if (spriteCounter <= 5) {
			spriteNumber = 1;
		}
		if (spriteCounter > 5 && spriteCounter <= 10) {
			spriteNumber = 2;
		}
		if (spriteCounter > 10 && spriteCounter <= 15) {
			spriteNumber = 3;
		}
		if (spriteCounter > 15 && spriteCounter <= 20) {
			spriteNumber = 4;
			
			//save the current position
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//adjust the player worldX and worldY for the attack area
			switch (direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "right": worldX += attackArea.width; break;
			case "left": worldX -= attackArea.width; break;
			}
			
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			//check monster collision with new player area
			int monsterIndex = panel.checker.checkEntity(this, panel.monster);
			damageMonster(monsterIndex);
			
			//reset position and solid area
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			
		}
		
		if (spriteCounter > 25) {
			spriteNumber = 3;
			spriteCounter = 0;
			attacking = false;
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
				panel.ui.showMessage("You got a key!");
				break;
			case "Gate":
				if (hasKey > 0) {
					panel.playSFX(1);
					panel.obj[i] = null;
					hasKey--;
					panel.ui.showMessage("You opened the gate!");
				} else {
					panel.ui.showMessage("You need a Key!");
				}
				break;
			case "Chest":
				panel.playSFX(2);
				hasChest++;
				panel.obj[i] = null;
				panel.ui.gameFinished = true;
				panel.stopMusic();
				panel.playSFX(2);
				break;
			}
		}
	}

	public void interactNPC(int i) {

		if (panel.keyHandler.enterPressed == true) {

			if (i != 999) {
				panel.gameState = panel.dialogueState;
				panel.npc[i].speak();
			}
			else {
				panel.playSFX(6);
				attacking = true;	
			}
		}
	}

	public void contactMonster(int i) {

		if (i != 999) {
			if (invincible == false) {
				panel.playSFX(5);
				life -= 1;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i) {
		
		if (i != 999) {
			
			if(panel.monster[i].invincible == false) {
				
				panel.playSFX(4);
				panel.monster[i].life -= 1;
				panel.monster[i].invincible = true;
				
				if (panel.monster[i].life <= 0) {
					panel.monster[i].dying = true;
				}
			}
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNumber == 1) {image = up1;}
				if(spriteNumber == 2) {image = up2;}
				if(spriteNumber == 3) {image = up3;}
			}
			if(attacking == true) {
				tempScreenX = screenX - panel.tileSize/2;
				if(spriteNumber == 1) {image = atack_up1;}
				if(spriteNumber == 2) {image = atack_up2;}
				if(spriteNumber == 3) {image = atack_up3;}
				if(spriteNumber == 4) {image = atack_up4;}
			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNumber == 1) {image = down1;}
				if(spriteNumber == 2) {image = down2;}	
				if(spriteNumber == 3) {image = down3;}
			}
			if(attacking == true) {
				tempScreenX = screenX - panel.tileSize/2;
				if(spriteNumber == 1) {image = atack_down1;}
				if(spriteNumber == 2) {image = atack_down2;}
				if(spriteNumber == 3) {image = atack_down3;}
				if(spriteNumber == 4) {image = atack_down4;}
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNumber == 1) {image = left1;}
				if(spriteNumber == 2) {image = left2;}
				if(spriteNumber == 3) {image = left3;}
			}
			if(attacking == true) {
				tempScreenY = screenY - panel.tileSize/2;
				if(spriteNumber == 1) {image = atack_left1;}
				if(spriteNumber == 2) {image = atack_left2;}
				if(spriteNumber == 3) {image = atack_left3;}
				if(spriteNumber == 4) {image = atack_left4;}
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNumber == 1) {image = right1;}
				if(spriteNumber == 2) {image = right2;}	
				if(spriteNumber == 3) {image = right3;}
			}
			if(attacking == true) {
				tempScreenY = screenY - panel.tileSize/2;
				if(spriteNumber == 1) {image = atack_right1;}
				if(spriteNumber == 2) {image = atack_right2;}
				if(spriteNumber == 3) {image = atack_right3;}
				if(spriteNumber == 4) {image = atack_right4;}
			}
			break;
		}

		//set transparency damage
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		}

		g2.drawImage(image, tempScreenX, tempScreenY, null);

		//reset transparency damage
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
}
