package object;

import entity.Entity;
import main.Panel;

public class Shotgun extends Entity {

	public Shotgun(Panel panel) {
		super(panel);
		
		type = type_weapon;
		automatic = false;
		weaponShotAvailableCounter = 40;
		name = "Shotgun";	
		down1 = setup("/object/Shootgun", panel.tileSize, panel.tileSize);
		
		attackValue = 3;
		description = "(" + name + ")\nRifle.";
		
		attackArea.width = 25;
		attackArea.height = 25;
		
		price = 40;
		knockBackPower = 3;
	}

}
