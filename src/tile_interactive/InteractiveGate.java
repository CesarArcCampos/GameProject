package tile_interactive;

import main.Panel;

public class InteractiveGate extends InteractiveTile {

	Panel panel;
	
	public InteractiveGate(Panel panel, int col, int row) {
		super(panel, col, row);
		this.panel = panel;
		
		this.worldX = panel.tileSize * col;
		this.worldY = panel.tileSize * row;
		
		down1 = setup("/tiles_interactive/gate_close", panel.tileSize, panel.tileSize);
		destructible = true;
		life = 5;
	}
	
	public void playSFX() {
		
		panel.playSFX(11);
	}
	
	public InteractiveTile getOpenedGate() {
		
		InteractiveTile tile = new InteractiveOpenGate(panel, worldX/panel.tileSize, worldY/panel.tileSize);
		return tile;
	}

}
