package main;

public class EventHandler {

	Panel panel;
	EventRect eventRect[][][];

	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempCol, tempRow;

	public EventHandler(Panel panel) {

		this.panel = panel;

		eventRect = new EventRect[panel.maxMap][panel.maxWorldCol][panel.maxWorldRow];

		int map = 0;
		int col = 0;
		int row = 0;

		while (map < panel.maxMap && col < panel.maxWorldCol && row < panel.maxWorldRow ) {

			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

			col++;
			if (col == panel.maxWorldCol) {
				col = 0;
				row++;

				if (row == panel.maxWorldRow) {
					row = 0;
					map++;
				}
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
			if (hit(0,2,16,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,3,47,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,6,12,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,9,25,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,10,5,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,14,34,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,15,26,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,17,42,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,23,11,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,23,30,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,23,48,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,25,42,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,29,2,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,29,44,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,35,12,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,35,19,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,35,26,"any") == true) {
				damagePit(panel.warningState);
			}
			else if (hit(0,36,34,"any") == true) {
				damagePit(panel.warningState);
			}

			else if (hit(0,2,1,"any") == true) {
				healingPool(panel.warningState);
			}

			else if (hit(0,48,4,"any") == true) {
				teleport(1,24,21);
			}

			else if (hit(1,24,21,"any") == true) {
				teleport(0,48,4);
			}

			else if (hit(0,47,41,"any") == true) {
				if (panel.player.zombieCounter == 35) {
					teleport(2,9,30);
				} else {

					if (panel.keyHandler.enterPressed == true) {
						panel.player.attackCanceled = true;
						panel.gameState = panel.warningState;
						panel.ui.currentDialogue = "You need to kill all the walkers!\n You missed: " + (35 - panel.player.zombieCounter);
					}
				}

			}
		}
	}

	public boolean hit(int map, int col, int row, String reqDirection) {

		boolean hit = false;

		if (map == panel.currentMap) {

			panel.player.solidArea.x = panel.player.worldX + panel.player.solidArea.x;
			panel.player.solidArea.y = panel.player.worldY + panel.player.solidArea.y;
			eventRect[map][col][row].x = col * panel.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row * panel.tileSize + eventRect[map][col][row].y;

			if (panel.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if (panel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;

					previousEventX = panel.player.worldX;
					previousEventY = panel.player.worldY;
				}
			}

			panel.player.solidArea.x = panel.player.solidAreaDefaultX;
			panel.player.solidArea.y = panel.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}

		return hit;
	}

	public void damagePit(int gameState) {

		panel.gameState = gameState;
		panel.ui.currentDialogue = "You fall into a \nradioactive zone!";
		panel.player.life -= 1;
		canTouchEvent = false;
	}

	public void healingPool(int gameState) {

		if (panel.keyHandler.enterPressed == true) {
			panel.gameState = gameState;
			panel.player.attackCanceled = true;
			panel.ui.currentDialogue = "You used the \nhealing zone! Your health \nis recovered";
			panel.player.life = panel.player.maxLife;
			panel.aSetter.setMonster(); // respawn the monsters
		}
	}

	public void teleport(int map, int col, int row) {

		if (panel.keyHandler.enterPressed == true) {
			panel.player.attackCanceled = true;
			panel.gameState = panel.transitionState;
			tempMap = map;
			tempCol = col;
			tempRow = row;

			canTouchEvent = false;
			panel.playSFX(13);
		}
	}

}
