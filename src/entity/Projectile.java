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
		
		if (this.collisionON == true) {
			alive = false;
			panel.playSFX(10);
		}
		
		if (user == panel.player) {
			int monsterIndex = panel.checker.checkEntity(this, panel.monster);
			
			if (monsterIndex != 999) {
				
				panel.player.damageMonster(monsterIndex, attack);
				alive = false;
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

		
		/*
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteCounter == 1) {
				spriteNumber = 2;
			}
			else if (spriteCounter == 2) {
				spriteNumber = 1;
			}
			spriteNumber = 0;
		}
		*/
	}
	
	public boolean checkBullets(Entity user) {
		
		boolean haveBullets = true;
		return haveBullets;
	}
	
	public void subtractBullets(Entity user) {

	}

}
