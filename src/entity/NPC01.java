package entity;

import java.awt.image.BufferedImage;
import java.util.Random;

import main.Panel;

public class NPC01 extends Entity{

	public BufferedImage picture;

	public NPC01(Panel panel) {
		super(panel);

		direction = "down";
		speed = 1;

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/npc-up");
		up2 = setup("/npc/npc-up");
		down1 = setup("/npc/npc-down");
		down2 = setup("/npc/npc-down");
		right1 = setup("/npc/npc-right");
		right2 = setup("/npc/npc-right");
		left1 = setup("/npc/npc-left");
		left2 = setup("/npc/npc-left");
		
	}

	public void setDialogue() {

		dialogues[0] = "Hello, friend.";
		dialogues[1] = "I am glad that you arrived";
		dialogues[2] = "The Walkers took over \nthis complex...";
		dialogues[3] = "We need your help to \nclean this place!";
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

	public void speak() {

		super.speak();
	}




}
