package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.KeyHandler;
import main.Panel;

public class Player extends Entity {

	Panel panel;
	KeyHandler keyHandler;

	public Player (Panel panel, KeyHandler keyHandler) {
		this.panel = panel;
		this.keyHandler = keyHandler;
		
		setDefaultValues();
	}

	public void setDefaultValues() {

		x = 100;
		y = 100;
		speed = 4;
		
		
	}

	public void update() {

		if (keyHandler.upPressed == true) {
			y -= speed;
		} else if (keyHandler.downPressed == true) {
			y += speed;
		} else if (keyHandler.leftPressed == true) {
			x -= speed;
		} else if (keyHandler.rightPressed == true) {
			x += speed;
		}

	}
	
	public void draw(Graphics2D g2) {

		g2.setColor(Color.white);
		g2.fillRect(x, y, panel.tileSize, panel.tileSize);

	}



}
