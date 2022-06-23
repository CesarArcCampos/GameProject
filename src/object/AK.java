package object;

import entity.Entity;
import main.Panel;

public class AK extends Entity{

	public AK(Panel panel) {
		super(panel);
		
		type = type_weapon;
		name = "AK";	
		down1 = setup("/object/AK", panel.tileSize, panel.tileSize);
		
		attackValue = 1;
		description = "(" + name + ")\nRifle.";
		
		attackArea.width = 25;
		attackArea.height = 25;
		
		price = 20;
		knockBackPower = 1;
	}

}
