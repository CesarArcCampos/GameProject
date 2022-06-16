package object;

import entity.Entity;
import main.Panel;

public class BulletMagazine extends Entity {
	
	Panel panel;

	public BulletMagazine(Panel panel) {
		super(panel);
		this.panel = panel;
		
		name = "Magazine";
		image = setup("/projectile/bullet_up", panel.tileSize, panel.tileSize);
		
	}

}
