package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Gate extends SuperObject {
	
	public Gate() {
		name = "Gate";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/object/gate.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}
}
