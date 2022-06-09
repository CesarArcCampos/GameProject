package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Panel;

public class Gate extends SuperObject {
	
	Panel panel;
	
	public Gate(Panel panel) {
		
		this.panel = panel;
		name = "Gate";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/object/gate.png"));
			uTool.scaleImage(image, panel.tileSize, panel.tileSize);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}
}
