package main;

import entity.Entity;

public class CollisionChecker {
	
	Panel panel;
	
	public CollisionChecker(Panel panel) {
		this.panel = panel;
	}
	
	public void checkTile (Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/panel.tileSize;
		int entityRightCol = entityRightWorldX/panel.tileSize;
		int entityTopRow = entityTopWorldY/panel.tileSize;
		int entityBottomRow = entityBottomWorldY/panel.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/panel.tileSize;
			tileNum1 = panel.tm.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = panel.tm.mapTileNum[entityRightCol][entityTopRow];
			if (panel.tm.tile[tileNum1].collision == true || panel.tm.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/panel.tileSize;
			tileNum1 = panel.tm.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = panel.tm.mapTileNum[entityRightCol][entityBottomRow];
			if (panel.tm.tile[tileNum1].collision == true || panel.tm.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/panel.tileSize;
			tileNum1 = panel.tm.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = panel.tm.mapTileNum[entityLeftCol][entityBottomRow];
			if (panel.tm.tile[tileNum1].collision == true || panel.tm.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/panel.tileSize;
			tileNum1 = panel.tm.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = panel.tm.mapTileNum[entityRightCol][entityBottomRow];
			if (panel.tm.tile[tileNum1].collision == true || panel.tm.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
			break;
			
				
		}
		
		
	}

}
