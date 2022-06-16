package object;

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

		up1 = setupBullet("/projectile/bullet_up");
		up2 = setupBullet("/projectile/bullet_up");
		down1 = setupBullet("/projectile/bullet_down");
		down2 = setupBullet("/projectile/bullet_down");
		right1 = setupBullet("/projectile/bullet_right");
		right2 = setupBullet("/projectile/bullet_right");
		left1 = setupBullet("/projectile/bullet_left");
		left2 = setupBullet("/projectile/bullet_left");

	}

	public BufferedImage setupBullet(String imagePath) {

		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));

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
	
}
