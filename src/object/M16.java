package object;

import entity.Entity;
import main.Panel;

public class M16 extends Entity{

	public M16(Panel panel) {
		super(panel);
		
		type = type_weapon;
		name = "M16";	
		down1 = setup("/object/M16", panel.tileSize, panel.tileSize);
		
		attackValue = 1;
		description = "(" + name + ")\nRifle.";
		
		attackArea.width = 25;
		attackArea.height = 25;
	}
	
	

}
