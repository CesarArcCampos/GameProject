package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class UI {

	Panel panel;
	Graphics2D g2;
	//Font arial20, arial40;
	Font futuristicFont;
	public boolean messageOn = false;
	public String message = "";
	public int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;


	public UI(Panel panel) {

		this.panel = panel;
		//arial20 = new Font("Arial", Font.PLAIN, 20);
		//arial40 = new Font("Arial", Font.BOLD, 40);

		try {
			InputStream is = getClass().getResourceAsStream("/fonts/FuturisticArmour-1p84.ttf");
			futuristicFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;

		g2.setFont(futuristicFont);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);

		//TitleState
		if (panel.gameState == panel.titleState) {
			drawTitleScreen();
		}

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

	public void drawTitleScreen() {

		g2.setColor(Color.black);
		g2.fillRect(0, 0, panel.screenWidth, panel.screenHeight);

		//Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Game Project";
		int x = getXforCenteredText(text);
		int y = panel.tileSize*3;

		//Shadow
		g2.setColor(new Color(0,100,0));
		g2.drawString(text, x + 5, y + 5);

		g2.setColor(Color.green);
		g2.drawString(text, x, y);

		// Zombie Picture
		x = panel.screenWidth/2 - (panel.tileSize)*2;
		y += panel.tileSize*2;

		BufferedImage image = null;
		image = setup("/pictures/zombie-picture");
		g2.drawImage(image, x, y, panel.tileSize*3, panel.tileSize*3, null);

		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		text = "New Game";
		x = getXforCenteredText(text);
		y += panel.tileSize*4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {

			g2.drawString(">", x - panel.tileSize, y);
		}

		text = "Load Game";
		x = getXforCenteredText(text);
		y += panel.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {

			g2.drawString(">", x - panel.tileSize, y);
		}

		text = "Quit";
		x = getXforCenteredText(text);
		y += panel.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 2) {

			g2.drawString(">", x - panel.tileSize, y);
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
		int width = panel.screenWidth - (panel.tileSize*8);
		int height = panel.tileSize*4;

		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,22F));
		g2.setColor(Color.GREEN);
		x += panel.tileSize;
		y += panel.tileSize;

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}

		x = panel.tileSize * 10;
		y = panel.tileSize / 2;
		width = panel.screenWidth - (panel.tileSize*12);
		height = panel.tileSize*4;
		drawSubWindow(x, y, width, height);
		drawPicture(x, y, width, height);

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

	public int getXforCenteredText(String text) {

		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = panel.screenWidth/2 - length/2;
		return x;
	}

	public void drawPicture(int x, int y, int width, int height) {

		BufferedImage image = null;
		image = setup("/npc/npc-picture");

		x += panel.tileSize/4;
		y += panel.tileSize/4;
		width -= panel.tileSize/2;
		height -= panel.tileSize/2;

		g2.drawImage(image, x, y, width, height, null);
	}

	public BufferedImage setup(String imagePath) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, panel.tileSize, panel.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}



}
