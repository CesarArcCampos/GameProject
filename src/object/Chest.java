package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Panel;

public class Chest extends SuperObject {
	
	Panel panel;
	
	public Chest(Panel panel) {
		
		this.panel = panel;
		name = "Chest";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/object/chest.png"));
			uTool.scaleImage(image, panel.tileSize, panel.tileSize);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
