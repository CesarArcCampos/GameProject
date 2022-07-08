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

		up1 = setup("/npc/npc-up", panel.tileSize, panel.tileSize);
		up2 = setup("/npc/npc-up", panel.tileSize, panel.tileSize);
		down1 = setup("/npc/npc-down", panel.tileSize, panel.tileSize);
		down2 = setup("/npc/npc-down", panel.tileSize, panel.tileSize);
		right1 = setup("/npc/npc-right", panel.tileSize, panel.tileSize);
		right2 = setup("/npc/npc-right", panel.tileSize, panel.tileSize);
		left1 = setup("/npc/npc-left", panel.tileSize, panel.tileSize);
		left2 = setup("/npc/npc-left", panel.tileSize, panel.tileSize);

	}

	public void setDialogue() {
		
		int randomNumber = new Random().nextInt(100) + 1;
		
		if (randomNumber < 50) {
			dialogues[0] = "Hello, friend. I am glad that you \narrived. The Walkers took over this \ncomplex... We need your help to \nclean this place!";
		}
		if (randomNumber >= 50 && randomNumber < 75) {
			dialogues[0] = "Whatever you're going to ask, the \nanswer is No! But the only thing I can\n say is there's a surprise for you\n in one of the teleports.";
		}
		if (randomNumber >= 75) {
			dialogues[0] = "Hey my brother! Do you remember \nwe used to do this in school? We \nwere told there was no future for \nshooters, and now look at us! Haha";
		}
		
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
