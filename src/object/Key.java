package object;

import entity.Entity;
import main.Panel;

public class Key extends Entity {
	
	Panel panel;
	
	public Key(Panel panel) {
		
		super(panel);
		this.panel = panel;
		
		type = type_consumable;
		name = "Key";
		down1 = setup("/object/key", panel.tileSize, panel.tileSize);
		description = "(" + name + ")\nIt opens a door.";
		stackable = true;
	}
	
	public boolean use(Entity entity) {
		
		panel.gameState = panel.warningState;
		
		int objIndex = getDetected(entity, panel.obj, "Gate");
		
		if (objIndex != 999) {
			panel.ui.currentDialogue = "You used a key and opened the door.";
			panel.playSFX(14);
			panel.obj[panel.currentMap][objIndex] = null;
			return true;
		} else {
			panel.ui.currentDialogue = "What are you doing!?";
			return false;
		}
	}
}
