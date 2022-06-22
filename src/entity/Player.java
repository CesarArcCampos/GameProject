package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.KeyHandler;
import main.Panel;
import object.BasicShield;
import object.Bullet;
import object.M16;

public class Player extends Entity {

	KeyHandler keyHandler;

	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	public int hasChest = 0;
	int standCounter = 0;
	public boolean attackCanceled = false;

	public Player (Panel panel, KeyHandler keyHandler) {

		super(panel);

		this.keyHandler = keyHandler;

		screenX = panel.screenWidth/2 - (panel.tileSize/2);
		screenY = panel.screenHeight/2 - (panel.tileSize/2);

		solidArea = new Rectangle(16,16,16,16);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValues();
		getPlayerImage();
		getAttackImage();
		setItems();
	}

	public void setDefaultValues() {

		worldX = panel.tileSize * 23;
		worldY = panel.tileSize * 21;
		speed = 4;
		direction = "down";
		panel.currentMap = 0;

		level = 1;
		strength = 1;
		dexterity = 1;
		exp = 0;
		nextLevelExp = 5;
		coin = 20;
		currentWeapon = new M16(panel);
		currentShield = new BasicShield(panel);
		projectile = new Bullet(panel);
		attack = getAttack();
		defense = getDefense();

		maxLife = 6;
		life = maxLife;
		maxBullets = 50;
		bullets = maxBullets;
	}

	public void setDefaultPositions() {

		worldX = panel.tileSize * 23;
		worldY = panel.tileSize * 23;
		direction = "down";
	}

	public void restoreLifeAndBullets() {

		life = maxLife;
		bullets = maxBullets;
		invincible = false;
	}

	public void setItems() {
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
	}

	public int getDefense() {

		return defense = dexterity * currentShield.defenseValue;
	}

	public int getAttack() {

		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
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
		
		if(maxLife > 10) {
			maxLife = 10;
		}
		
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

			// CHECK INTERACTIVE TILE COLLISION
			int iTileIndex = panel.checker.checkEntity(this, panel.iTile);
			contactMonster(monsterIndex);

			// CHECK EVENT
			panel.eHandler.checkEvent();

			// IF THERE IS NO COLLISION, PLAYER CAN MOVE
			if (collisionON == false && keyHandler.enterPressed == false) {

				switch (direction) {
				case "up": worldY -= speed;break;
				case "down": worldY += speed;break;
				case "left": worldX -= speed;break;
				case "right": worldX += speed;break;
				}	
			}

			if (keyHandler.enterPressed == true 
					&& attackCanceled == false) {
				panel.playSFX(6);
				attacking = true;
				spriteCounter = 0;
			}

			attackCanceled = false;

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

		if(panel.keyHandler.shotKeyPressed == true 
				&& shotAvailableCounter == 30
				&& projectile.checkBullets(this) == true) {

			projectile.set(worldX, worldY, direction, true, this);

			projectile.subtractBullets(this);

			panel.projectileList.add(projectile);

			panel.playSFX(9);

			shotAvailableCounter = 0;
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

		if (shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
		if (life > maxLife) {
			life = maxLife;
		}
		if (life < 0) {
			life = 0;
		}

		if (life <= 0) {
			panel.stopMusic();
			panel.ui.commandNum = -1;
			panel.gameState = panel.gameOverState;
			panel.playSFX(12);
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
			damageMonster(monsterIndex, attack);

			int iTileIndex = panel.checker.checkEntity(this, panel.iTile);
			damageInteractiveTile(iTileIndex);

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

			//PICK UP ONLY ITEM
			if (panel.obj[panel.currentMap][i].type == type_pickUpOnly) {

				panel.obj[panel.currentMap][i].use(this);
				panel.obj[panel.currentMap][i] = null;
			}
			//PICK UP TO INVENTORY
			else {

				String text;

				if (inventory.size() != maxInventorySize) {

					inventory.add(panel.obj[panel.currentMap][i]);
					panel.playSFX(2);
					text = "Got a " + panel.obj[panel.currentMap][i].name + "!";
				} else {

					text = "Your inventory is full";
				}

				panel.ui.addMessage(text);
				panel.obj[panel.currentMap][i] = null;
			}
		}
	}

	public void interactNPC(int i) {

		if (panel.keyHandler.enterPressed == true) {

			if (i != 999) {
				attackCanceled = true;
				panel.gameState = panel.dialogueState;
				panel.npc[panel.currentMap][i].speak();
			}
		}
	}

	public void contactMonster(int i) {

		if (i != 999) {
			if (invincible == false && panel.monster[panel.currentMap][i].dying == false) {
				panel.playSFX(5);

				int damage = panel.monster[panel.currentMap][i].attack - defense;
				if (damage < 0) {
					damage = 0;
				}

				life -= damage;
				invincible = true;
			}
		}
	}

	public void damageMonster(int i, int attack) {

		if (i != 999) {

			if(panel.monster[panel.currentMap][i].invincible == false) {

				panel.playSFX(4);

				int damage = attack - panel.monster[panel.currentMap][i].defense;
				if (damage < 0) {
					damage = 0;
				}
				panel.monster[panel.currentMap][i].life -= damage;
				panel.ui.addMessage("> " + damage + " damage");

				panel.monster[panel.currentMap][i].invincible = true;
				panel.monster[panel.currentMap][i].damageReaction();

				if (panel.monster[panel.currentMap][i].life <= 0) {
					panel.monster[panel.currentMap][i].dying = true;
					panel.ui.addMessage("> killed the " + panel.monster[panel.currentMap][i].name);
					panel.ui.addMessage("> Exp " + panel.monster[panel.currentMap][i].exp);
					exp += panel.monster[panel.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	}

	public void damageInteractiveTile(int i) {

		if (i != 999 && panel.iTile[panel.currentMap][i].destructible == true
				&& panel.iTile[panel.currentMap][i].invincible == false) {

			panel.iTile[panel.currentMap][i].playSFX();
			panel.iTile[panel.currentMap][i].life--;
			panel.iTile[panel.currentMap][i].invincible = true;

			if (panel.iTile[panel.currentMap][i].life == 0) {
				panel.iTile[panel.currentMap][i] = panel.iTile[panel.currentMap][i].getOpenedGate();
			}
		}	
	}

	public void checkLevelUp() {

		if (exp >= nextLevelExp) {
			panel.playSFX(7);
			level ++;
			nextLevelExp = nextLevelExp * 2;
			maxLife += 2;
			life = maxLife;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();

			panel.gameState = panel.warningState;
			panel.ui.currentDialogue = "You are level " + level + " now\n"
					+ "You feel stronger";
		}
	}

	public void selectItem() {

		int itemIndex = panel.ui.getItemIndexOnSlot(panel.ui.playerSlotCol, panel.ui.playerSlotRow);

		if (itemIndex < inventory.size()) {

			Entity selectedItem = inventory.get(itemIndex);

			if (selectedItem.type == type_weapon) {

				currentWeapon = selectedItem;
				attack = getAttack();
			}

			if(selectedItem.type == type_shield) {

				currentShield = selectedItem;
				defense = getDefense();
			}

			if(selectedItem.type == type_consumable) {

				selectedItem.use(this);
				inventory.remove(itemIndex);
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
