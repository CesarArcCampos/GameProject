package object;

import entity.Entity;
import main.Panel;

public class Magazine extends Entity {

	public Magazine(Panel panel) {
		
		super(panel);

		name = "Bullet";
		
		image = setup("/projectile/bullet_up", panel.tileSize, panel.tileSize);
	
	}
}
