package main;

import object.Chest;
import object.Gate;
import object.Key;

public class AssetSetter {
	
	Panel panel;
	
	public AssetSetter(Panel panel) {
		this.panel = panel;
	}
	
	public void setObject() {
		
		panel.obj[0] = new Key();
		panel.obj[0].worldX = 45 * panel.tileSize;
		panel.obj[0].worldY = 2 * panel.tileSize;
		
		panel.obj[1] = new Gate();
		panel.obj[1].worldX = 46 * panel.tileSize;
		panel.obj[1].worldY = 36 * panel.tileSize;
		
		panel.obj[2] = new Chest();
		panel.obj[2].worldX = 2 * panel.tileSize;
		panel.obj[2].worldY = 48 * panel.tileSize;
	}

}
