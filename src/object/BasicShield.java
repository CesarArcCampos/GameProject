package object;

import entity.Entity;
import main.Panel;

public class BasicShield extends Entity {

	public BasicShield(Panel panel) {
		super(panel);

		type = type_shield;
		name = "Basic Shield";
		name1 = "Basic";
		down1 = setup("/object/shield",panel.tileSize,panel.tileSize);
		defenseValue = 1;
		description = "(" + name + ")\nHas poor \nprotection.";
	}

}
