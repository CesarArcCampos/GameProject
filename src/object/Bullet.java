package object;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Projectile;
import main.Panel;

public class Bullet extends Projectile {

	Panel panel;

	public Bullet(Panel panel) {
		super(panel);
		this.panel = panel;

		name = "Bullet";
		speed = 10;
		maxLife = 160;
		life = maxLife;
		attack = 2;
		useCost = 1;
		alive = false;
		getImage();
	}

	private void getImage() {

		up1 = setupBullet("/projectile/bullet_up","up");
		up2 = setupBullet("/projectile/bullet_up","up");
		down1 = setupBullet("/projectile/bullet_down","down");
		down2 = setupBullet("/projectile/bullet_down","down");
		right1 = setupBullet("/projectile/bullet_right","right");
		right2 = setupBullet("/projectile/bullet_right","right");
		left1 = setupBullet("/projectile/bullet_left","left");
		left2 = setupBullet("/projectile/bullet_left","left");

	}
	
	public BufferedImage setupBullet(String imagePath, String direction) {

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
			
			if (direction == "right") {
				g.drawImage(bulletImage, panel.tileSize/2 + 20, 3 * panel.tileSize/4 + 4, 10, 10, null);
			} else if (direction == "left") {
				g.drawImage(bulletImage, panel.tileSize/2 - 20, 1 * panel.tileSize/4 - 1, 10, 10, null);
			} else if (direction == "up") {
				g.drawImage(bulletImage, panel.tileSize/2 + 20, panel.tileSize/2 - 20, 10, 10, null);
			} else if (direction == "down") {
				g.drawImage(bulletImage, panel.tileSize/2 - 15, panel.tileSize/2 + 20, 10, 10, null);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    
		return image;
	}
	
	
	public boolean checkBullets(Entity user) {
		
		boolean haveBullets = false;
		
		if (user.bullets >= useCost) {
			haveBullets = true;
		}
		
		return haveBullets;
	}
	
	public void subtractBullets(Entity user) {
		
		user.bullets -= useCost;
	}
	
	public Color getParticleColor() {
		Color color = new Color(240,50,0);
		return color;
	}
	
	public int getParticleSize() {
		int size = 6;
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int maxLife() {
		int maxLife = 20;
		return maxLife;
	}
	
}
