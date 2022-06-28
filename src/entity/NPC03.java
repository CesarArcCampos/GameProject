package entity;

import java.awt.image.BufferedImage;

import main.Panel;

public class NPC03 extends Entity {
	
	public BufferedImage picture;
	
	public NPC03(Panel panel) {
		super(panel);

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
		
		dialogues[0] = "Hello, friend! \nDo you need something?";
		dialogues[1] = "No, i'm fine.";
		dialogues[2] = "Yes! Do you know how to open encrypted gates?";
		dialogues[3] = "Yes, I happen to know. We \nfound some passwords that\n will serve to open the gates.\n The password is 1234.";
		dialogues[4] = "Ok! Thanks!";
		dialogues[5] = "1234?? That easy?";
		dialogues[6] = "Well... Not everyone is a genius.\n :)";
		dialogues[7] = "Good luck!";
		dialogues[8] = "Come again!";
		dialogues[9] = "I don't have \nnothing more to say.";
		
	}	
	
	public void speak() {
		
		dialogueIndex = 0;
		panel.gameState = panel.chatState;
		panel.ui.npc = this;
	}

}
