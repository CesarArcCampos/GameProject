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
		
		if (panel.player != null) {
			knockBackPower = panel.player.currentWeapon.knockBackPower;
		}
		
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
				panel.player.damageMonster(monsterIndex, attack, panel.player.currentWeapon.knockBackPower);
				generateParticle(user.projectile, panel.monster[panel.currentMap][monsterIndex]);
				alive = false;
			}
			
			int interactiveIndex = panel.checker.checkEntity(this, panel.iTile);
			
			if (interactiveIndex != 999 && panel.iTile[panel.currentMap][interactiveIndex].destructible == true) {
				
				panel.player.damageInteractiveTile(interactiveIndex);
			}
			
			int gateIndex = panel.checker.checkEntity(this, panel.obj); 
			
			if (gateIndex != 999) {
				if (panel.obj[panel.currentMap][gateIndex].name == "Gate") {
					alive = false;
					panel.playSFX(10);
				}
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
