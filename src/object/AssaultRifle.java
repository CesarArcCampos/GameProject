package object;

import entity.Entity;
import main.Panel;

public class AssaultRifle extends Entity {

	public AssaultRifle(Panel panel) {
		super(panel);
		
		type = type_weapon;
		name = "Assault";	
		down1 = setup("/object/Assault", panel.tileSize, panel.tileSize);
		
		attackValue = 2;
		description = "(" + name + ")\nRifle.";
		
		attackArea.width = 25;
		attackArea.height = 25;
		
		price = 25;
		knockBackPower = 1;
	}

}
