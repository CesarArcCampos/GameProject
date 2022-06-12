package monster;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.Panel;

public class Mon_Zombie extends Entity {

	public Mon_Zombie(Panel panel) {
		super(panel);
		
		type = 2;
		name = "Zombie";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		
		solidArea = new Rectangle(16,16,16,16);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/zombie_1_up");
		up2 = setup("/monster/zombie_2_up");
		down1 = setup("/monster/zombie_1_down");
		down2 = setup("/monster/zombie_2_down");
		right1 = setup("/monster/zombie_1_right");
		right2 = setup("/monster/zombie_2_right");
		left1 = setup("/monster/zombie_1_left");
		left2 = setup("/monster/zombie_2_left");
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
	
	

}
