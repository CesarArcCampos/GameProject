package object;

import entity.Entity;
import main.Panel;

public class Key extends Entity {
	
	public Key(Panel panel) {
		
		super(panel);
		
		name = "Key";
		down1 = setup("/object/key", panel.tileSize, panel.tileSize);
	}
}
