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

public class MedicKit extends Entity{
	
	Panel panel;
	int value = 5;

	public MedicKit(Panel panel) {
		super(panel);
		
		this.panel = panel;
		
		type = type_consumable;
		name = "Medicine";
		down1 = setupKit("/object/medickit");
		description = "(Medicine)\nHeals your life by" + value + ".";	
		
		price = 20;
	}
	
	public boolean use(Entity entity) {
		
		panel.gameState = panel.warningState;
		panel.ui.currentDialogue = "You took the " + name +
			"!\n" + "Your life as been recovered\n by" + value + ".";
		entity.life += value;
		
		if (panel.player.life > panel.player.maxLife) {
			panel.player.life = panel.player.maxLife;
		}
		
		panel.playSFX(7);
		return true;
		
	}
	
	public BufferedImage setupKit(String imagePath) {

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
