package tile_interactive;

import java.awt.Graphics2D;

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

	public void draw(Graphics2D g2) {

		int screenX = worldX - panel.player.worldX + panel.player.screenX;
		int screenY = worldY - panel.player.worldY + panel.player.screenY;

		if (worldX + panel.tileSize > panel.player.worldX - panel.player.screenX &&
				worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
				worldY + panel.tileSize > panel.player.worldY - panel.player.screenY &&
				worldY - panel.tileSize < panel.player.worldY + panel.player.screenY) {

			g2.drawImage(down1, screenX, screenY, null);
		}
	}

}
