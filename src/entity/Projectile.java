package entity;

import main.Panel;

public class Projectile extends Entity {
	
	Entity user;

	public Projectile(Panel panel) {
		super(panel);
		
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;

	}
	
	public void update() {
		
		collisionON = false;
		panel.checker.checkTile(this);
		
		solidArea(this.direction);
		
		if (this.collisionON == true) {
			alive = false;
			panel.playSFX(10);
		}
		
		if (user == panel.player) {
			int monsterIndex = panel.checker.checkEntity(this, panel.monster);
			
			if (monsterIndex != 999) {
				panel.player.damageMonster(monsterIndex, attack);
				generateParticle(user.projectile, panel.monster[panel.currentMap][monsterIndex]);
				alive = false;
				
				int x = panel.player.worldX;
				int y = panel.player.worldY;
				int xm = panel.monster[panel.currentMap][monsterIndex].worldX;
				int ym = panel.monster[panel.currentMap][monsterIndex].worldY;
				int xm_solid = panel.monster[panel.currentMap][monsterIndex].solidArea.x;
				int ym_solid = panel.monster[panel.currentMap][monsterIndex].solidArea.y;
				int xb = this.worldX;
				int yb = this.worldY;
				int xb_solid = this.solidArea.x;
				int yb_solid = this.solidArea.y;
				int xb_default = this.solidAreaDefaultX;
				
				System.out.println("x: " + x);
				System.out.println("xm: " + xm + " ; xm_solid: " + xm_solid);
				System.out.println("direction: " + direction + "xb: " + xb + " ; xb_solid: " + xb_solid + " ; xb_default: " + xb_default );
			}
			
			int interactiveIndex = panel.checker.checkEntity(this, panel.iTile);
			
			if (interactiveIndex != 999 && panel.iTile[panel.currentMap][interactiveIndex].destructible == true) {
				
				panel.player.damageInteractiveTile(interactiveIndex);
			}
		} 
		
		if (user != panel.player) {
			//IF I WANT MONSTERS TO USE WEAPONS
		}
		
		switch(direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		}
		
		life--;
		
		if (life <= 0) {
			alive = false;
			
		}
	}
	
	public boolean checkBullets(Entity user) {
		
		boolean haveBullets = true;
		return haveBullets;
	}
	
	public void subtractBullets(Entity user) {

	}
	
	public void solidArea(String direction) {
		
		if (direction == "right") {
			solidArea.x = panel.tileSize;
			solidArea.y = panel.tileSize - 5;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
		}
		else if (direction == "left") {
			solidArea.x = panel.tileSize / 2;
			solidArea.y = 15;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
		}
		else if (direction == "down") {
			solidArea.x = 10;
			solidArea.y = panel.tileSize;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
		}
		else if (direction == "up") {
			solidArea.x = panel.tileSize - 10;
			solidArea.y = panel.tileSize/2;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
		}
	}

}
