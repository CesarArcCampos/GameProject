package main;

import entity.NPC01;
import monster.Mon_Zombie;
import object.AK;
import object.AssaultRifle;
import object.Coins;
import object.Key;
import object.MedicKit;
import object.MediumShield;
import object.Shotgun;

public class AssetSetter {

	Panel panel;

	public AssetSetter(Panel panel) {
		this.panel = panel;
	}

	public void setObject() {
		
		int i = 0;
		panel.obj[i] = new Key(panel);
		panel.obj[i].worldX = 45 * panel.tileSize;
		panel.obj[i].worldY = 2 * panel.tileSize;
		i++;
		panel.obj[i] = new Shotgun(panel);
		panel.obj[i].worldX = 2 * panel.tileSize;
		panel.obj[i].worldY = 30 * panel.tileSize;
		i++;
		panel.obj[i] = new AK(panel);
		panel.obj[i].worldX = 19 * panel.tileSize;
		panel.obj[i].worldY = 20 * panel.tileSize;
		i++;
		panel.obj[i] = new AssaultRifle(panel);
		panel.obj[i].worldX = 19 * panel.tileSize;
		panel.obj[i].worldY = 20 * panel.tileSize;
		i++;
		panel.obj[i] = new MediumShield(panel);
		panel.obj[i].worldX = 23 * panel.tileSize;
		panel.obj[i].worldY = 30 * panel.tileSize;
		i++;
		panel.obj[i] = new MedicKit(panel);
		panel.obj[i].worldX = 06 * panel.tileSize;
		panel.obj[i].worldY = 02 * panel.tileSize;
		i++;
		panel.obj[i] = new Coins(panel);
		panel.obj[i].worldX = 10 * panel.tileSize;
		panel.obj[i].worldY = 01 * panel.tileSize;
		i++;
		panel.obj[i] = new Coins(panel);
		panel.obj[i].worldX = 45 * panel.tileSize;
		panel.obj[i].worldY = 45 * panel.tileSize;
		i++;
		
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
