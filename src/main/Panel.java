package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Panel extends JPanel {
	
	final int originalTileSize = 16;
	final int scale = 3;
	final int tileSize = originalTileSize * scale;
	final int MaxScreenColumn = 16;
	final int MaxScreenRow = 12;
	
	final int screenWidth = tileSize * MaxScreenColumn; //768 pixels
	final int screenHeight = tileSize * MaxScreenRow; //576 pixels
	
	public Panel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
}
