package object;

import entity.Entity;
import main.Panel;

public class Key extends Entity {
	
	public Key(Panel panel) {
		
		super(panel);
		
		type = type_consumable;
		name = "Key";
		down1 = setup("/object/key", panel.tileSize, panel.tileSize);
		description = "(" + name + ")\nIt opens a door.";
	}
}
