package object;

import entity.Entity;
import main.Panel;

public class MediumShield extends Entity{

	public MediumShield(Panel panel) {
		super(panel);
		
		type = type_shield;
		name = "Medium Shield";
		name1 = "Medium";
		down1 = setup("/object/shield",panel.tileSize,panel.tileSize);
		defenseValue = 2;
		description = "(" + name + ")\nHas standart \nprotection.";
		
		price = 25;
	}

}
