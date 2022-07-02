package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
	
	int minutes = 0;
	
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}
	
	public String getFormattedTime(double playTime) {
		StringBuilder stringBuilder = new StringBuilder();
		int seconds = (int) playTime;
		
		if (seconds > 59) {
			playTime = 0;
			minutes++;
		}
		
		if (minutes < 10) {
			stringBuilder.append(0);
		}
		stringBuilder.append(minutes);
		stringBuilder.append(":");
		
		if (seconds < 10) {
			stringBuilder.append(0);
		}
		
		stringBuilder.append(seconds);
		
		return stringBuilder.toString();
		
	}

}
