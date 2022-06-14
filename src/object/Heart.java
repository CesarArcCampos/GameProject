package object;

import entity.Entity;
import main.Panel;

public class Heart extends Entity{
	
	public Heart(Panel panel) {
		
		super(panel);

		name = "Heart";
		
		image = setup("/object/heart_full", panel.tileSize, panel.tileSize);
		image2 = setup("/object/heart_half", panel.tileSize, panel.tileSize);
		image3 = setup("/object/heart_blank", panel.tileSize, panel.tileSize);
	
	}
}
