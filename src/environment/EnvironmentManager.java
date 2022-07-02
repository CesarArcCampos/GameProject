package environment;

import java.awt.Graphics2D;

import main.Panel;

public class EnvironmentManager {

	Panel panel;
	Lightning lightning;
	
	public EnvironmentManager(Panel panel) {
		
		this.panel = panel;
	}
	
	public void setup() {
		
		lightning = new Lightning(panel, 500);
	}
	
	public void draw(Graphics2D g2) {
		
		if (lightning != null) {
			lightning.draw(g2);
		}
		
	}
	
}
