package object;

import entity.Entity;
import main.Panel;

public class Heart extends Entity{
	
	public Heart(Panel panel) {
		
		super(panel);

		name = "Heart";
		
		image = setup("/object/heart_full");
		image2 = setup("/object/heart_half");
		image3 = setup("/object/heart_blank");
	
	}
}
