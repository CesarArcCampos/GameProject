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

	public MedicKit(Panel panel) {
		super(panel);
		
		this.panel = panel;
		
		type = type_consumable;
		name = "Medicine";
		down1 = setupKit("/object/medickit");
		description = "(Medicine)\nHeals your life.";
		stackable = true;
		
		price = 20;
	}
	
	public boolean use(Entity entity) {
		
		panel.gameState = panel.warningState;
		panel.ui.currentDialogue = "You used the medicine kit!\nYour life as been recovered.";
		entity.life += entity.maxLife;
		
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
	    BufferedImage imageKit;
	    
		try {
			imageKit = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			g.drawImage(imageKit, panel.tileSize/2 - 10, panel.tileSize/2 - 10, 20, 20, null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    
		return image;
	}

}
