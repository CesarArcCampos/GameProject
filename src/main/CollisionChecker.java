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

	public int checkObject(Entity entity, boolean player) {

		int index = 999;

		for (int i = 0; i < panel.obj.length; i++ ) {

			if (panel.obj[i] != null) {

				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				panel.obj[i].solidArea.x = panel.obj[i].worldX + panel.obj[i].solidArea.x;
				panel.obj[i].solidArea.y = panel.obj[i].worldY + panel.obj[i].solidArea.y;

				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(panel.obj[i].solidArea)) {
						if (panel.obj[i].collision == true) {
							entity.collisionON = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(panel.obj[i].solidArea)) {
						if (panel.obj[i].collision == true) {
							entity.collisionON = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(panel.obj[i].solidArea)) {
						if (panel.obj[i].collision == true) {
							entity.collisionON = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(panel.obj[i].solidArea)) {
						if (panel.obj[i].collision == true) {
							entity.collisionON = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				panel.obj[i].solidArea.x = panel.obj[i].solidAreaDefaultX;
				panel.obj[i].solidArea.y = panel.obj[i].solidAreaDefaultY;
			}

		}

		return index;
	}
	
	public int checkEntity(Entity entity, Entity[] target) {
		
		int index = 999;

		for (int i = 0; i < target.length; i++ ) {

			if (target[i] != null) {

				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						
							entity.collisionON = true;
							index = i;	
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						
						entity.collisionON = true;
						index = i;	
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						
						entity.collisionON = true;
						index = i;	
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						
						entity.collisionON = true;
						index = i;	
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}

		return index;
	}
	
	public void checkPlayer(Entity entity) {
		
			entity.solidArea.x = entity.worldX + entity.solidArea.x;
			entity.solidArea.y = entity.worldY + entity.solidArea.y;

			panel.player.solidArea.x = panel.player.worldX + panel.player.solidArea.x;
			panel.player.solidArea.y = panel.player.worldY + panel.player.solidArea.y;

			switch(entity.direction) {
			case "up":
				entity.solidArea.y -= entity.speed;
				if (entity.solidArea.intersects(panel.player.solidArea)) {
					
						entity.collisionON = true;
				}
				break;
			case "down":
				entity.solidArea.y += entity.speed;
				if (entity.solidArea.intersects(panel.player.solidArea)) {
					
					entity.collisionON = true;
				}
				break;
			case "left":
				entity.solidArea.x -= entity.speed;
				if (entity.solidArea.intersects(panel.player.solidArea)) {
					
					entity.collisionON = true;	
				}
				break;
			case "right":
				entity.solidArea.x += entity.speed;
				if (entity.solidArea.intersects(panel.player.solidArea)) {
					
					entity.collisionON = true;
				}
				break;
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			panel.player.solidArea.x = panel.player.solidAreaDefaultX;
			panel.player.solidArea.y = panel.player.solidAreaDefaultY;
		}
		
	
	
	

}
