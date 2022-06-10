package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

	Panel panel;
	Graphics2D g2;
	Font arial20, arial40;
	public boolean messageOn = false;
	public String message = "";
	public int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";


	public UI(Panel panel) {

		this.panel = panel;
		arial20 = new Font("Arial", Font.PLAIN, 20);
		arial40 = new Font("Arial", Font.BOLD, 40);

	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial20);
		g2.setColor(Color.white);
		
		//PlayState
		if (panel.gameState == panel.playState) {
			//something
		}
		//PauseState
		if (panel.gameState == panel.pauseState) {
			drawPauseScreen();
		}
		//DialogueState
		if (panel.gameState == panel.dialogueState) {
			drawDialogueScreen();
		}
		
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = panel.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		
		//Window
		int x = panel.tileSize*2;
		int y = panel.tileSize/2;
		int width = panel.screenWidth - (panel.tileSize*4);
		int height = panel.tileSize*4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,22F));
		x += panel.tileSize;
		y += panel.tileSize;
		g2.drawString(currentDialogue, x, y);
		
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0,150);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	
	public int getXforCenteredText (String text) {
		
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = panel.screenWidth/2 - length/2;
		return x;
	}

}
