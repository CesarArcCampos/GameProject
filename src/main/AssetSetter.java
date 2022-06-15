package main;

import entity.NPC01;
import monster.Mon_Zombie;
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

	}

	public void setNPC() {

		panel.npc[0] = new NPC01(panel);
		panel.npc[0].worldX = panel.tileSize*21;
		panel.npc[0].worldY = panel.tileSize*21;
	}
	
	public void setMonster() {
		
		int i = 0;
		
		panel.monster[i] = new Mon_Zombie(panel);
		panel.monster[i].worldX = panel.tileSize*45;
		panel.monster[i].worldY = panel.tileSize*45;
		i++;
		panel.monster[i] = new Mon_Zombie(panel);
		panel.monster[i].worldX = panel.tileSize*42;
		panel.monster[i].worldY = panel.tileSize*42;
		i++;
		panel.monster[i] = new Mon_Zombie(panel);
		panel.monster[i].worldX = panel.tileSize*40;
		panel.monster[i].worldY = panel.tileSize*40;
	}

}
