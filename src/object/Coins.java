package object;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.Panel;

public class Coins extends Entity {

	Panel panel;
	
	public Coins(Panel panel) {
		super(panel);
		this.panel = panel;
		
		type = type_pickUpOnly;
		name = "Coins";
		down1 = setupCoin("/object/coin");
		value = 1;
	}
	
	public void use(Entity entity) {
		
		panel.playSFX(2);
		panel.ui.addMessage("Coin +" + value);
		panel.player.coin += value;
		
	}
	
	public BufferedImage setupCoin(String imagePath) {

		BufferedImage image = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
	    Color transparent = new Color(0x00FFFFFF, true);
	    Graphics2D g = (Graphics2D) image.getGraphics();
	    g.setComposite(AlphaComposite.Src);
	    g.setColor(transparent);
	    g.setBackground(transparent);
	    g.fillRect(0, 0, image.getWidth(), image.getHeight());
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    BufferedImage bulletImage;
	    
		try {
			bulletImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			g.drawImage(bulletImage, panel.tileSize/2, panel.tileSize/2, 20, 20, null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    
		return image;
	}

}
