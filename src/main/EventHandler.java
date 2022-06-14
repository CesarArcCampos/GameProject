package main;

public class EventHandler {

	Panel panel;
	EventRect eventRect[][];

	int previousEventX, previousEventY;
	boolean canTouchEvent = true;

	public EventHandler(Panel panel) {

		this.panel = panel;

		eventRect = new EventRect[panel.maxWorldCol][panel.maxWorldRow];

		int col = 0;
		int row = 0;

		while (col < panel.maxWorldCol && row < panel.MaxScreenRow ) {

			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23;
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

			col++;
			if (col == panel.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

	public void checkEvent() {

		int xDistance = Math.abs(panel.player.worldX - previousEventX);
		int yDistance = Math.abs(panel.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);

		if (distance > panel.tileSize) {
			canTouchEvent = true;
		}

		if (canTouchEvent == true) {
			if (hit(1,1,"any") == true) {
				damagePit(1, 1, panel.warningState);
			}
			if (hit(2,1,"any") == true) {
				healingPool(2, 1, panel.warningState);
			}
		}
	}

	public boolean hit(int col, int row, String reqDirection) {

		boolean hit = false;

		panel.player.solidArea.x = panel.player.worldX + panel.player.solidArea.x;
		panel.player.solidArea.y = panel.player.worldY + panel.player.solidArea.y;
		eventRect[col][row].x = col * panel.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row * panel.tileSize + eventRect[col][row].y;

		if (panel.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
			if (panel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;

				previousEventX = panel.player.worldX;
				previousEventY = panel.player.worldY;
			}
		}

		panel.player.solidArea.x = panel.player.solidAreaDefaultX;
		panel.player.solidArea.y = panel.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

		return hit;
	}

	public void damagePit(int col, int row, int gameState) {

		panel.gameState = gameState;
		panel.ui.currentDialogue = "You fall into a \nradioactive zone!";
		panel.player.life -= 1;
		canTouchEvent = false;
	}

	public void healingPool(int col, int row, int gameState) {

		if (panel.keyHandler.enterPressed == true) {
			panel.gameState = gameState;
			panel.player.attackCanceled = true;
			panel.ui.currentDialogue = "You used the \nhealing zone! Your health \nis recovered";
			panel.player.life = panel.player.maxLife;
		}
	}

}
