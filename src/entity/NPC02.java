package entity;

import java.awt.image.BufferedImage;

import main.Panel;
import object.AK;
import object.AssaultRifle;
import object.M16;
import object.MedicKit;
import object.Shotgun;

public class NPC02 extends Entity {

	public BufferedImage picture;
	
	public NPC02(Panel panel) {
		super(panel);
		
		getImage();
		setDialogue();
		setItems();
		
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
		
		dialogues[0] = "Hello, friend! \nDo you need supplies?";
	}
	
	public void setItems() {
		
		inventory.add(new AK(panel));
		inventory.add(new AssaultRifle(panel));
		inventory.add(new M16(panel));
		inventory.add(new MedicKit(panel));
		inventory.add(new Shotgun(panel));
	}
	
	public void speak() {
		
		dialogueIndex = 0;
		super.speak();
		panel.gameState = panel.tradeState;
		panel.ui.npc = this;
	}
	
	

}
