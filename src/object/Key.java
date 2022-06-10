package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Panel;

public class Key extends SuperObject {
	
	Panel panel;
	
	public Key(Panel panel) {
		
		this.panel = panel;
		name = "Key";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/object/key.png"));
			uTool.scaleImage(image, panel.tileSize/2, panel.tileSize/2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
