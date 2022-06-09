package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.Key;

public class UI {

	Panel panel;
	Font arial20, arial40;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	public int messageCounter = 0;
	public boolean gameFinished = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("0.00");

	public UI(Panel panel) {

		this.panel = panel;
		arial20 = new Font("Arial", Font.PLAIN, 20);
		arial40 = new Font("Arial", Font.BOLD, 40);
		Key key = new Key(panel);
		keyImage = key.image;
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		if (gameFinished == true) {
			
			g2.setFont(arial20);
			g2.setColor(Color.yellow);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found the Chest";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = panel.screenWidth/2 - textLength/2;
			y = panel.screenHeight/2 - (panel.tileSize*2);
			g2.drawString(text, x, y);
			
			text = "Your time is: " + dFormat.format(playTime) + " seconds!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = panel.screenWidth/2 - textLength/2;
			y = panel.screenHeight/2 + (panel.tileSize*4);
			g2.drawString(text, x, y);
			
			
			g2.setFont(arial40);
			g2.setColor(Color.yellow);
			text = "Congratulations!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = panel.screenWidth/2 - textLength/2;
			y = panel.screenHeight/2 + (panel.tileSize*2);
			g2.drawString(text, x, y);
			
			panel.gameThread = null;
			
			
		} else {

			g2.setFont(arial20);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, panel.tileSize/4, panel.tileSize/4, panel.tileSize/2, panel.tileSize/2, null);
			g2.drawString("x " + panel.player.hasKey,40,30);
			
			playTime += (double) 1/60;
			g2.drawString("Time:"+ dFormat.format(playTime), panel.tileSize*13,30);

			if (messageOn == true) {

				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, panel.tileSize/2, panel.tileSize*5);

				messageCounter++;

				if (messageCounter > 120) {

					messageCounter = 0;
					messageOn = false;
				}
			}
		}
	}

}
