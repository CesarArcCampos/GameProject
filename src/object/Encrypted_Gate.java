package object;

import entity.Entity;
import main.Panel;

public class Encrypted_Gate extends Entity {

	Panel panel;

	public Encrypted_Gate(Panel panel) {
		super(panel);

		this.panel = panel;

		type = type_obstacle;
		name = "Encrypted_Gate";
		down1 = setup("/tiles_interactive/gate_close",panel.tileSize,panel.tileSize);
		collision = true;

		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

	public void interact() {

		int objIndex = getDetected(this, panel.obj, "Encrypted_Gate");

		if (objIndex != 999) {
			if (panel.player.hasPassword == true) {
				panel.playSFX(14);
				panel.obj[panel.currentMap][objIndex] = null;
			} else {
				panel.gameState = panel.warningState;
				panel.ui.currentDialogue = "This door is closed...\n You need a password!";
			}
			
		}
	}
}
