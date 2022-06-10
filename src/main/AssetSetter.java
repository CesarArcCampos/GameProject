package main;

import entity.NPC01;
import object.Chest;
import object.Gate;
import object.Key;

public class AssetSetter {
	
	Panel panel;
	
	public AssetSetter(Panel panel) {
		this.panel = panel;
	}
	
	public void setObject() {
		
		panel.obj[0] = new Key(panel);
		panel.obj[0].worldX = 45 * panel.tileSize;
		panel.obj[0].worldY = 2 * panel.tileSize;
		
		panel.obj[1] = new Gate(panel);
		panel.obj[1].worldX = 46 * panel.tileSize;
		panel.obj[1].worldY = 36 * panel.tileSize;
		
		panel.obj[2] = new Chest(panel);
		panel.obj[2].worldX = 2 * panel.tileSize;
		panel.obj[2].worldY = 48 * panel.tileSize;
	}
	
	public void setNPC() {
		
		panel.npc[0] = new NPC01(panel);
		panel.npc[0].worldX = panel.tileSize*21;
		panel.npc[0].worldY = panel.tileSize*21;
	}

}
