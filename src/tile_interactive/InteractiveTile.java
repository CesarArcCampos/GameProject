package tile_interactive;

import entity.Entity;
import main.Panel;

public class InteractiveTile extends Entity {

	Panel panel;
	public boolean destructible = false;
	
	public InteractiveTile(Panel panel, int col, int row) {
		super(panel);
		this.panel = panel;
	}
	
	public void update() {
		
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 20) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	
	public void playSFX() {
		
	}
	
	public InteractiveTile getOpenedGate() {
		InteractiveTile tile = null;
		return tile;
	}

}
