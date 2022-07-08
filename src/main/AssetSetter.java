package main;

import java.util.Random;

import entity.Entity;
import entity.NPC01;
import entity.NPC02;
import entity.NPC03;
import monster.Mon_Boss;
import monster.Mon_Zombie;
import object.AK;
import object.AssaultRifle;
import object.Chest;
import object.Encrypted_Gate;
import object.Gate;
import object.M16;
import object.MedicKit;
import object.MediumShield;
import object.Shotgun;

public class AssetSetter {

	Panel panel;

	public AssetSetter(Panel panel) {
		this.panel = panel;
	}

	public void setObject() {
//		
		int mapNum = 0;
		int i = 0;

		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 44 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 36 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 40 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 36 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 39 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 39 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 40 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 28 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 40 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 21 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 47 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 16 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 2 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 12 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 18 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 9 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 28 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 6 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 42 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 16 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 6 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 8 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 23 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 32 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 7 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 45 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Gate(panel);
		panel.obj[mapNum][i].worldX = 23 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 15 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = new Encrypted_Gate(panel);
		panel.obj[mapNum][i].worldX = 16 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 23 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = setRandomLoot();
		panel.obj[mapNum][i].worldX = 2 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 17 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = setRandomLoot();
		panel.obj[mapNum][i].worldX = 9 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 21 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = setRandomLoot();
		panel.obj[mapNum][i].worldX = 32 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 25 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = setRandomLoot();
		panel.obj[mapNum][i].worldX = 30 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 2 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = setRandomLoot();
		panel.obj[mapNum][i].worldX = 48 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 7 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = setRandomLoot();
		panel.obj[mapNum][i].worldX = 32 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 35 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = setRandomLoot();
		panel.obj[mapNum][i].worldX = 47 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 12 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = setRandomLoot();
		panel.obj[mapNum][i].worldX = 40 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 33 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = setRandomLoot();
		panel.obj[mapNum][i].worldX = 48 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 42 * panel.tileSize;
		i++;
		panel.obj[mapNum][i] = setRandomLoot();
		panel.obj[mapNum][i].worldX = 2 * panel.tileSize;
		panel.obj[mapNum][i].worldY = 44 * panel.tileSize;
		i++;
		
	}

	public void setNPC() {

		int mapNum = 0;
		int i = 0;
		panel.npc[mapNum][i] = new NPC01(panel);
		panel.npc[mapNum][i].worldX = panel.tileSize*2;
		panel.npc[mapNum][i].worldY = panel.tileSize*2;
		i++;
		panel.npc[mapNum][i] = new NPC01(panel);
		panel.npc[mapNum][i].worldX = panel.tileSize*47;
		panel.npc[mapNum][i].worldY = panel.tileSize*26;
		i++;
		panel.npc[mapNum][i] = new NPC01(panel);
		panel.npc[mapNum][i].worldX = panel.tileSize*7;
		panel.npc[mapNum][i].worldY = panel.tileSize*46;
		i++;
		panel.npc[mapNum][i] = new NPC03(panel);
		panel.npc[mapNum][i].worldX = panel.tileSize*44;
		panel.npc[mapNum][i].worldY = panel.tileSize*33;
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
		
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*47;
		panel.monster[mapNum][i].worldY = panel.tileSize*44;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*39;
		panel.monster[mapNum][i].worldY = panel.tileSize*43;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*44;
		panel.monster[mapNum][i].worldY = panel.tileSize*40;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*34;
		panel.monster[mapNum][i].worldY = panel.tileSize*44;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*28;
		panel.monster[mapNum][i].worldY = panel.tileSize*39;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*33;
		panel.monster[mapNum][i].worldY = panel.tileSize*37;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*31;
		panel.monster[mapNum][i].worldY = panel.tileSize*34;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*45;
		panel.monster[mapNum][i].worldY = panel.tileSize*37;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*47;
		panel.monster[mapNum][i].worldY = panel.tileSize*31;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*39;
		panel.monster[mapNum][i].worldY = panel.tileSize*25;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*39;
		panel.monster[mapNum][i].worldY = panel.tileSize*24;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*39;
		panel.monster[mapNum][i].worldY = panel.tileSize*23;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*40;
		panel.monster[mapNum][i].worldY = panel.tileSize*22;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*41;
		panel.monster[mapNum][i].worldY = panel.tileSize*22;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*43;
		panel.monster[mapNum][i].worldY = panel.tileSize*18;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*46;
		panel.monster[mapNum][i].worldY = panel.tileSize*11;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*40;
		panel.monster[mapNum][i].worldY = panel.tileSize*11;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*33;
		panel.monster[mapNum][i].worldY = panel.tileSize*19;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*27;
		panel.monster[mapNum][i].worldY = panel.tileSize*17;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*18;
		panel.monster[mapNum][i].worldY = panel.tileSize*17;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*6;
		panel.monster[mapNum][i].worldY = panel.tileSize*13;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*15;
		panel.monster[mapNum][i].worldY = panel.tileSize*21;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*22;
		panel.monster[mapNum][i].worldY = panel.tileSize*12;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*28;
		panel.monster[mapNum][i].worldY = panel.tileSize*7;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*32;
		panel.monster[mapNum][i].worldY = panel.tileSize*12;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*38;
		panel.monster[mapNum][i].worldY = panel.tileSize*1;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*46;
		panel.monster[mapNum][i].worldY = panel.tileSize*2;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*27;
		panel.monster[mapNum][i].worldY = panel.tileSize*1;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*9;
		panel.monster[mapNum][i].worldY = panel.tileSize*12;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*17;
		panel.monster[mapNum][i].worldY = panel.tileSize*25;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*11;
		panel.monster[mapNum][i].worldY = panel.tileSize*26;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*1;
		panel.monster[mapNum][i].worldY = panel.tileSize*24;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*5;
		panel.monster[mapNum][i].worldY = panel.tileSize*33;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*10;
		panel.monster[mapNum][i].worldY = panel.tileSize*37;
		i++;
		panel.monster[mapNum][i] = new Mon_Zombie(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*6;
		panel.monster[mapNum][i].worldY = panel.tileSize*43;
		i++;
		
		mapNum = 2;
		panel.monster[mapNum][i] = new Mon_Boss(panel);
		panel.monster[mapNum][i].worldX = panel.tileSize*20;
		panel.monster[mapNum][i].worldY = panel.tileSize*30;
		i++;
	}
	
	public void setInteractiveTile() {
		
//		int mapNum = 0;
//		int i = 0;
//		panel.iTile[mapNum][i] = new InteractiveGate(panel, 8, 43);
//		i++;
	}
	
	public Entity setRandomLoot() {
		
		int randomNumber = new Random().nextInt(100) + 1;
		Entity entity = null;
		
		if (randomNumber < 48) {
			entity = new Chest(panel, null);
		} 
		if (randomNumber >= 48 && randomNumber < 58) {
			entity = new Chest(panel, new AK(panel));
		} 
		if (randomNumber >= 58 && randomNumber < 68) {
			entity = new Chest(panel, new Shotgun(panel));
		}
		if (randomNumber >= 68 && randomNumber < 78) {
			entity = new Chest(panel, new AssaultRifle(panel));
		}
		if (randomNumber >= 78 && randomNumber < 88) {
			entity = new Chest(panel, new M16(panel));
		}
		if (randomNumber >= 88 && randomNumber < 98) {
			entity = new Chest(panel, new MedicKit(panel));
		}
		if (randomNumber >= 98) {
			entity = new Chest(panel, new MediumShield(panel));
		}
		
		return entity;
	}

}
