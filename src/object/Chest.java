package object;

import entity.Entity;
import main.Panel;

public class Chest extends Entity{

	Panel panel;
	Entity loot;
	boolean opened = false;
	
	public Chest(Panel panel, Entity loot) {
		super(panel);
		this.panel = panel;
		this.loot = loot;
		
		type = type_obstacle;
		name = "Chest";
		image = setup("/object/chest_closed",panel.tileSize,panel.tileSize);
		image2 = setup("/object/chest_opened",panel.tileSize,panel.tileSize);
		down1 = image;
		collision = true;
		
		solidArea.x = panel.tileSize/2 - 20;
		solidArea.y = panel.tileSize/2 - 20;
		solidArea.width = 40;
		solidArea.height = 40;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void interact() {
		
		panel.gameState = panel.warningState;
		
		if (opened == false && loot != null) {
			panel.playSFX(14);
			
			StringBuilder sb = new StringBuilder();
			sb.append("You open the crate and find a " + loot.name + "!");
			
			if (panel.player.canObtainItem(loot) == false) {
				sb.append("\n... Your inventory is full!");
			} else {
				sb.append("\nYou obtained the " + loot.name + "!");
				if (loot.type == type_weapon) {
					panel.player.bullets += 50;
				}
				down1 = image2;
				opened = true;
			}
			panel.ui.currentDialogue = sb.toString();
		} else {
			panel.ui.currentDialogue = "This crate is empty!";
			down1 = image2;
			opened = true;
		}
	}

}
