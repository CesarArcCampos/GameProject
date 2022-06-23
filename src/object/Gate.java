package object;

import entity.Entity;
import main.Panel;

public class Gate extends Entity{
	
	Panel panel;

	public Gate(Panel panel) {
		super(panel);
		
		this.panel = panel;
		
		type = type_obstacle;
		name = "Gate";
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
		
		panel.gameState = panel.warningState;
		panel.ui.currentDialogue = "You need a key to open this gate";
	}

}
