package object;

import entity.Entity;
import main.Panel;

public class Rifle extends Entity{

	public Rifle(Panel panel) {
		super(panel);
		
		name = "AR-15";
		down1 = setup("/object/rifle",panel.tileSize,panel.tileSize);
		attackValue = 4;
	}
	
	

}
