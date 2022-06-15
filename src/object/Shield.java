package object;

import entity.Entity;
import main.Panel;

public class Shield extends Entity {

	public Shield(Panel panel) {
		super(panel);

		name = "Basic";
		down1 = setup("/object/Shield",panel.tileSize,panel.tileSize);
		defenseValue = 1;
	}

}
