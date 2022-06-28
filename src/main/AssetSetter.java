package main;

import entity.NPC01;
import entity.NPC02;
import entity.NPC03;
import object.Encrypted_Gate;

public class AssetSetter {

	Panel panel;

	public AssetSetter(Panel panel) {
		this.panel = panel;
	}

	public void setObject() {
//		
		int mapNum = 0;
		int i = 0;
//		panel.obj[mapNum][i] = new Key(panel);
//		panel.obj[mapNum][i].worldX = 45 * panel.tileSize;
//		panel.obj[mapNum][i].worldY = 2 * panel.tileSize;
//		i++;
//		panel.obj[mapNum][i] = new Shotgun(panel);
//		panel.obj[mapNum][i].worldX = 2 * panel.tileSize;
//		panel.obj[mapNum][i].worldY = 30 * panel.tileSize;
//		i++;
//		panel.obj[mapNum][i] = new AK(panel);
//		panel.obj[mapNum][i].worldX = 19 * panel.tileSize;
//		panel.obj[mapNum][i].worldY = 20 * panel.tileSize;
//		i++;
//		panel.obj[mapNum][i] = new AssaultRifle(panel);
//		panel.obj[mapNum][i].worldX = 19 * panel.tileSize;
//		panel.obj[mapNum][i].worldY = 20 * panel.tileSize;
//		i++;
//		panel.obj[mapNum][i] = new MediumShield(panel);
//		panel.obj[mapNum][i].worldX = 23 * panel.tileSize;
//		panel.obj[mapNum][i].worldY = 30 * panel.tileSize;
//		i++;
//		panel.obj[mapNum][i] = new MedicKit(panel);
//		panel.obj[mapNum][i].worldX = 06 * panel.tileSize;
//		panel.obj[mapNum][i].worldY = 02 * panel.tileSize;
//		i++;
//		panel.obj[mapNum][i] = new Coins(panel);
//		panel.obj[mapNum][i].worldX = 10 * panel.tileSize;
//		panel.obj[mapNum][i].worldY = 01 * panel.tileSize;
//		i++;
//		panel.obj[mapNum][i] = new Coins(panel);
//		panel.obj[mapNum][i].worldX = 45 * panel.tileSize;
//		panel.obj[mapNum][i].worldY = 45 * panel.tileSize;
//		i++;
//		panel.obj[mapNum][i] = new Gate(panel);
//		panel.obj[mapNum][i].worldX = 16 * panel.tileSize;
//		panel.obj[mapNum][i].worldY = 23 * panel.tileSize;
//		i++;
		panel.obj[mapNum][i] = new Encrypted_Gate(panel);
		panel.obj[mapNum][i].worldX = 16 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 23 * panel.tileSize;
		i++;
//		panel.obj[mapNum][i] = new Chest(panel,new Key(panel));
//		panel.obj[mapNum][i].worldX = 8 * panel.tileSize;
//		panel.obj[mapNum][i].worldY = 30 * panel.tileSize;
//		i++;
//		
	}

	public void setNPC() {

		int mapNum = 0;
		int i = 0;
		panel.npc[mapNum][i] = new NPC01(panel);
		panel.npc[mapNum][i].worldX = panel.tileSize*21;
		panel.npc[mapNum][i].worldY = panel.tileSize*21;
		i++;
		panel.npc[mapNum][i] = new NPC03(panel);
		panel.npc[mapNum][i].worldX = panel.tileSize*2;
		panel.npc[mapNum][i].worldY = panel.tileSize*4;
		i++;
		
		mapNum = 1;
		i = 0;
		panel.npc[mapNum][i] = new NPC01(panel);
		panel.npc[mapNum][i].worldX = panel.tileSize*20;
		panel.npc[mapNum][i].worldY = panel.tileSize*20;
		i++;
		panel.npc[mapNum][i] = new NPC02(panel);
		panel.npc[mapNum][i].worldX = panel.tileSize*25;
		panel.npc[mapNum][i].worldY = panel.tileSize*25;
		i++;
	}
	
	public void setMonster() {
		
		int mapNum = 0;
		int i = 0;
		
//		panel.monster[mapNum][i] = new Mon_Zombie(panel);
//		panel.monster[mapNum][i].worldX = panel.tileSize*45;
//		panel.monster[mapNum][i].worldY = panel.tileSize*45;
//		i++;
//		panel.monster[mapNum][i] = new Mon_Zombie(panel);
//		panel.monster[mapNum][i].worldX = panel.tileSize*42;
//		panel.monster[mapNum][i].worldY = panel.tileSize*42;
//		i++;
//		panel.monster[mapNum][i] = new Mon_Zombie(panel);
//		panel.monster[mapNum][i].worldX = panel.tileSize*40;
//		panel.monster[mapNum][i].worldY = panel.tileSize*40;
//		i++;
//		
//		mapNum = 1;
//		panel.monster[mapNum][i] = new Mon_Zombie(panel);
//		panel.monster[mapNum][i].worldX = panel.tileSize*40;
//		panel.monster[mapNum][i].worldY = panel.tileSize*40;
//		i++;
	}
	
	public void setInteractiveTile() {
		
//		int mapNum = 0;
//		int i = 0;
//		panel.iTile[mapNum][i] = new InteractiveGate(panel, 8, 43);
//		i++;
	}

}
