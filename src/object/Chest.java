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
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 20;
		solidArea.height = 20;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void interact() {
		
		panel.gameState = panel.warningState;
		
		if (opened == false) {
			panel.playSFX(14);
			
			StringBuilder sb = new StringBuilder();
			sb.append("You open the chest and find a " + loot.name + "!");
			
			if (panel.player.canObtainItem(loot) == false) {
				sb.append("\n... Your inventory is full!");
			} else {
				sb.append("\nYou obtained the " + loot.name + "!");
				down1 = image2;
				opened = true;
			}
			panel.ui.currentDialogue = sb.toString();
		} else {
			panel.ui.currentDialogue = "This chest is empty!";
		}
	}

}
