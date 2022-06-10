package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Panel;

public class Heart extends SuperObject{
	
	Panel panel;
	
	public Heart(Panel panel) {
		
		this.panel = panel;
		name = "Heart";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/object/heart_full.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/object/heart_half.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/object/heart_blank.png"));
			image = uTool.scaleImage(image, panel.tileSize/2, panel.tileSize/2);
			image2 = uTool.scaleImage(image2, panel.tileSize/2, panel.tileSize/2);
			image3 = uTool.scaleImage(image3, panel.tileSize/2, panel.tileSize/2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
