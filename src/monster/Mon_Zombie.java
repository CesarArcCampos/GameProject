package monster;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.Panel;

public class Mon_Zombie extends Entity {
	
	Panel panel;

	public Mon_Zombie(Panel panel) {
		super(panel);
		
		this.panel = panel;
		
		type = 2;
		name = "Zombie";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		
		solidArea = new Rectangle(16,16,16,16);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
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
	
	public void setAction() {
		
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
	
	public void damageReaction() {
		
		actionLockerCounter = 0;
		direction = panel.player.direction;
	}
	
	

}
