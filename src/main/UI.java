package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {

	Panel panel;
	Graphics2D g2;
	Font arial20, arial40;
	//BufferedImage keyImage;
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
		//Key key = new Key(panel);
		//keyImage = key.image;
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial20);
		g2.setColor(Color.white);
		
		if (panel.gameState == panel.playState) {
			
		}
		if (panel.gameState == panel.pauseState) {
			drawPauseScreen();
		}
		
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = panel.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public int getXforCenteredText (String text) {
		
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = panel.screenWidth/2 - length/2;
		return x;
	}

}
