package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Entity;
import object.Heart;

public class UI {

	Panel panel;
	Graphics2D g2;
	Font futuristicFont;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	//public String message = "";
	//public int messageCounter = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int slotCol = 0;
	public int slotRow = 0;


	public UI(Panel panel) {

		this.panel = panel;

		try {
			InputStream is = getClass().getResourceAsStream("/fonts/FuturisticArmour-1p84.ttf");
			futuristicFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		Entity heart = new Heart(panel);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;

	}

	public void addMessage(String text) {

		message.add(text);
		messageCounter.add(0);
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
			drawPlayerLife();
			drawMessage();
		}
		//PauseState
		if (panel.gameState == panel.pauseState) {
			drawPauseScreen();
			drawPlayerLife();
		}
		//DialogueState
		if (panel.gameState == panel.dialogueState) {
			drawDialogueScreen();
			drawPlayerLife();
		}

		//WarningState
		if (panel.gameState == panel.warningState) {
			drawWarningScreen();
		}

		//CharacterState
		if (panel.gameState == panel.characterState) {
			drawCharacterScreen();
			drawInventory();
		}

	}

	public void drawPlayerLife() {

		int x = panel.tileSize/2;
		int y = panel.tileSize/2;
		int i = 0;

		// Draw maximum life
		while (i < panel.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += panel.tileSize;
		}

		// Reset the values
		x = panel.tileSize/2;
		y = panel.tileSize/2;
		i = 0;

		// Draw current life
		while (i < panel.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if (i < panel.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += panel.tileSize;
		}
	}

	public void drawMessage() {

		int messageX = panel.tileSize;
		int messageY = panel.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));

		for (int i = 0; i <message.size(); i++) {

			if (message.get(i) != null) {

				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);

				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 30;

				if (messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}

	public void drawInventory() {

		//frame
		int frameX = panel.tileSize * 9;
		int frameY = panel.tileSize;
		int frameWidth = panel.tileSize * 6;
		int frameHeight = panel.tileSize * 5;
		drawSubWindow(frameX ,frameY, frameWidth, frameHeight);

		//slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = panel.tileSize + 3;

		//draw items
		for (int i = 0; i < panel.player.inventory.size(); i++) {
			
			if (panel.player.inventory.get(i) == panel.player.currentWeapon ||
				panel.player.inventory.get(i) == panel.player.currentShield) {
				
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX,slotY,panel.tileSize,panel.tileSize,10,10);
			}

			g2.drawImage(panel.player.inventory.get(i).down1, 
					slotX, slotY, null);
			slotX += slotSize;

			if (i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}

		//cursor
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = panel.tileSize;
		int cursorHeight = panel.tileSize;
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		//description frame
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = panel.tileSize * 3;
		
		int textX = dFrameX + 20;
		int textY = dFrameY + panel.tileSize;
		g2.setFont(g2.getFont().deriveFont(20F));
		int itemIndex = getItemIndexOnSlot();

		if (itemIndex < panel.player.inventory.size()) {
			
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

			for (String line: panel.player.inventory.get(itemIndex).description.split("\n")) {

				g2.drawString(line, textX, textY);
				textY += 24;
			}
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

		try{
			BufferedImage image = ImageIO.read(getClass().getResource("/pictures/zombie-picture.png"));
			AffineTransform at = new AffineTransform();
			g2.drawImage(image, x + panel.tileSize/2, y - panel.tileSize, panel.tileSize*3, panel.tileSize*3, null);

		}catch(IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		} 

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

	public void drawWarningScreen() {

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
	}

	public void drawSubWindow(int x, int y, int width, int height) {

		Color c = new Color(105,105,105,150);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

	}

	public int getItemIndexOnSlot() {

		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}

	public int getXforCenteredText(String text) {

		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = panel.screenWidth/2 - length/2;
		return x;
	}

	public int getXforAlignRightText(String text, int tailX) {

		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
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

	public void drawCharacterScreen() {

		//create frame
		final int frameX = panel.tileSize;
		final int frameY = panel.tileSize;
		final int frameWidth = panel.tileSize * 5;
		final int frameHeight = panel.tileSize * 10;

		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		//text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(25F));

		int textX = frameX + 20;
		int textY = frameY + panel.tileSize;
		final int lineHeight = 35;

		//parameters name
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("EXP", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 20;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;

		// parameters values
		int tailX = (frameX + frameWidth) - 30;
		textY = frameY + panel.tileSize;
		String value;

		value = String.valueOf(panel.player.level);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(panel.player.life + "/" + panel.player.maxLife);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(panel.player.strength);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(panel.player.dexterity);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(panel.player.attack);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(panel.player.defense);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(panel.player.exp);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(panel.player.nextLevelExp);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(panel.player.coin);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight + 20;

		//OPTION WITHOUT IMAGES
		value = String.valueOf(panel.player.currentWeapon.name);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight + 15;

		value = String.valueOf(panel.player.currentShield.name1);
		textX = getXforAlignRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		//OPTION WITH IMAGES
		/*
		g2.drawImage(panel.player.currentWeapon.down1, tailX - panel.tileSize, textY, null);
		textY += panel.tileSize;
		g2.drawImage(panel.player.currentShield.down1, tailX - panel.tileSize, textY - 5, null);
		 */

	}



}
