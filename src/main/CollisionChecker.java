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
			tileNum1 = panel.tm.mapTileNum[panel.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = panel.tm.mapTileNum[panel.currentMap][entityRightCol][entityTopRow];
			if (panel.tm.tile[tileNum1].collision == true || panel.tm.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/panel.tileSize;
			tileNum1 = panel.tm.mapTileNum[panel.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = panel.tm.mapTileNum[panel.currentMap][entityRightCol][entityBottomRow];
			if (panel.tm.tile[tileNum1].collision == true || panel.tm.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/panel.tileSize;
			tileNum1 = panel.tm.mapTileNum[panel.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = panel.tm.mapTileNum[panel.currentMap][entityLeftCol][entityBottomRow];
			if (panel.tm.tile[tileNum1].collision == true || panel.tm.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/panel.tileSize;
			tileNum1 = panel.tm.mapTileNum[panel.currentMap][entityRightCol][entityTopRow];
			tileNum2 = panel.tm.mapTileNum[panel.currentMap][entityRightCol][entityBottomRow];
			if (panel.tm.tile[tileNum1].collision == true || panel.tm.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
			break;	
		}

	}

	public int checkObject(Entity entity, boolean player) {

		int index = 999;

		for (int i = 0; i < panel.obj[1].length; i++ ) {

			if (panel.obj[panel.currentMap][i] != null) {

				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				panel.obj[panel.currentMap][i].solidArea.x = panel.obj[panel.currentMap][i].worldX + panel.obj[panel.currentMap][i].solidArea.x;
				panel.obj[panel.currentMap][i].solidArea.y = panel.obj[panel.currentMap][i].worldY + panel.obj[panel.currentMap][i].solidArea.y;

				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				
				if (entity.solidArea.intersects(panel.obj[panel.currentMap][i].solidArea)) {
					if (panel.obj[panel.currentMap][i].collision == true) {
						entity.collisionON = true;
					}
					if (player == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				panel.obj[panel.currentMap][i].solidArea.x = panel.obj[panel.currentMap][i].solidAreaDefaultX;
				panel.obj[panel.currentMap][i].solidArea.y = panel.obj[panel.currentMap][i].solidAreaDefaultY;
			}

		}

		return index;
	}

	public int checkEntity(Entity entity, Entity[][] target) {

		int index = 999;

		for (int i = 0; i < target[1].length; i++ ) {

			if (target[panel.currentMap][i] != null) {

				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				target[panel.currentMap][i].solidArea.x = target[panel.currentMap][i].worldX + target[panel.currentMap][i].solidArea.x;
				target[panel.currentMap][i].solidArea.y = target[panel.currentMap][i].worldY + target[panel.currentMap][i].solidArea.y;

				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}

				if (entity.solidArea.intersects(target[panel.currentMap][i].solidArea)) {
					if(target[panel.currentMap][i] != entity) {
						entity.collisionON = true;
						index = i;	
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[panel.currentMap][i].solidArea.x = target[panel.currentMap][i].solidAreaDefaultX;
				target[panel.currentMap][i].solidArea.y = target[panel.currentMap][i].solidAreaDefaultY;
			}
		}

		return index;
	}

	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;

		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;

		panel.player.solidArea.x = panel.player.worldX + panel.player.solidArea.x;
		panel.player.solidArea.y = panel.player.worldY + panel.player.solidArea.y;

		switch(entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			break;
		case "left":
			entity.solidArea.x -= entity.speed;
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			break;
		}
		
		if (entity.solidArea.intersects(panel.player.solidArea)) {
			entity.collisionON = true;
			contactPlayer = true;
		}
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		panel.player.solidArea.x = panel.player.solidAreaDefaultX;
		panel.player.solidArea.y = panel.player.solidAreaDefaultY;
		
		return contactPlayer;
	}





}
