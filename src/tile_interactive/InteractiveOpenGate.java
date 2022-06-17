package tile_interactive;

import main.Panel;

public class InteractiveOpenGate extends InteractiveTile{

	Panel panel;
	
	public InteractiveOpenGate(Panel panel, int col, int row) {
		super(panel, col, row);
		this.panel = panel;
		
		this.worldX = panel.tileSize * col;
		this.worldY = panel.tileSize * row;
		
		down1 = setup("/tiles_interactive/gate_open", panel.tileSize, panel.tileSize);
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}
