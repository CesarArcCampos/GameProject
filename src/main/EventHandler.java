package main;

import java.awt.Rectangle;

public class EventHandler {
	
	Panel panel;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	public EventHandler(Panel panel) {
		
		this.panel = panel;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width = 2;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
			
	}
	
	public void checkEvent() {
		
		if (hit(3,2,"up") == true) {
			damagePit(panel.warningState);
		}
		if (hit(2,2,"up") == true) {
			healingPool(panel.warningState);
		}
	}
	
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		
		boolean hit = false;
		
		panel.player.solidArea.x = panel.player.worldX + panel.player.solidArea.x;
		panel.player.solidArea.y = panel.player.worldY + panel.player.solidArea.y;
		eventRect.x = eventCol * panel.tileSize + eventRect.x;
		eventRect.y = eventRow * panel.tileSize + eventRect.y;
		
		if (panel.player.solidArea.intersects(eventRect)) {
			if (panel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		
		panel.player.solidArea.x = panel.player.solidAreaDefaultX;
		panel.player.solidArea.y = panel.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
	
	public void damagePit(int gameState) {
		
		panel.gameState = gameState;
		panel.ui.currentDialogue = "You fall into a \nradioactive zone!";
		panel.player.life -= 1;
	}
	
	public void healingPool(int gameState) {
		
		if (panel.keyHandler.enterPressed == true) {
			panel.gameState = gameState;
			panel.ui.currentDialogue = "You used the \nhealing zone! Your health \nis recovered";
			panel.player.life = panel.player.maxLife;
		}
	}

}
