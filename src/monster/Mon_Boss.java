package monster;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.Panel;

public class Mon_Boss extends Entity {

	Panel panel;

	public Mon_Boss(Panel panel) {
		super(panel);

		this.panel = panel;

		type = type_zombie;
		name = "Boss";
		defaultSpeed = 1;
		speed = 0;
		maxLife = 5;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 5;

		solidArea = new Rectangle(panel.tileSize/4, panel.tileSize/4,panel.tileSize/2,panel.tileSize/2);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();
		setDialogue();
	}

	public void getImage() {
		
		
		up1 = setup("/monster/zombie_1_up", panel.tileSize, panel.tileSize);
		up2 = setup("/monster/zombie_2_up", panel.tileSize, panel.tileSize);
		down1 = setup("/monster/zombie_1_down", panel.tileSize, panel.tileSize);
		down2 = setup("/monster/zombie_2_down", panel.tileSize, panel.tileSize);
		right1 = setup("/monster/zombie_1_right", panel.tileSize, panel.tileSize);
		right2 = setup("/monster/zombie_2_right", panel.tileSize, panel.tileSize);
		left1 = setup("/monster/zombie_1_left", panel.tileSize, panel.tileSize);
		left2 = setup("/monster/zombie_2_left", panel.tileSize, panel.tileSize);
	}
	
	public void update() {
		
		super.update();
		
		int xDistance = Math.abs(worldX - panel.player.worldX);
		int yDistance = Math.abs(worldY - panel.player.worldY);
		int tileDistance = (xDistance + yDistance)/panel.tileSize;
		
		if (hadDialogue == false) {
			this.invincible = true;
		}
		
		if (onPath == false && tileDistance < 3) {
				
				panel.stopMusic();
				panel.player.interactBoss(this);
				hadDialogue = true;
				onPath = true;
				speed = 3;
		}
		
		if (life < 0) {
			life = 0;
		}

		if (life <= 0) {
			panel.stopMusic();
			panel.gameState = panel.endState;
			panel.playSFX(12);
		}
		
	}
	
	public void setDialogue() {

		dialogues[0] = "Hello, dear friend.\nFinally you arrived.\nI was waiting for you...\nIt is time to die! ";

	}

	public void setAction() {

		if (onPath == true) {

			int goalCol = (panel.player.worldX + panel.player.solidArea.x)/panel.tileSize;
			int goalRow = (panel.player.worldY + panel.player.solidArea.y)/panel.tileSize;

			searchPath(goalCol, goalRow);

		} else {

			actionLockerCounter++;

			if (actionLockerCounter == 120) {

				Random random = new Random();
				int i = random.nextInt(100)+1;

				if (i <= 25) {
					direction = "up";
				}
				if (i > 25 && i <= 50) {
					direction = "down";
				}
				if (i > 50 && i <= 75) {
					direction = "left";
				}
				if (i > 75) {
					direction = "right";
				}

				actionLockerCounter = 0;
			}
		}
	}
	
	public void speak() {

		super.speak();
	}

}
