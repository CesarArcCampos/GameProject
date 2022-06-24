package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed, shotKeyPressed;
	Panel panel;

	public KeyHandler(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		//Title State
		if (panel.gameState == panel.titleState) {
			titleState(code);
		}

		//Play State
		else if (panel.gameState == panel.playState) {
			playState(code);
		}

		//Pause State

		else if (panel.gameState == panel.pauseState) {
			pauseState(code);
		}

		//Dialogue State

		else if (panel.gameState == panel.dialogueState) {
			dialogueState(code);
		}

		//Warning State

		else if (panel.gameState == panel.warningState) {
			warningState(code);
		}

		//Character State

		else if (panel.gameState == panel.characterState) {
			characterState(code);
		}

		//Option State

		else if (panel.gameState == panel.optionState) {
			optionState(code);
		}

		//Game Over State

		else if (panel.gameState == panel.gameOverState) {
			gameOverState(code);
		}

		//Trade State

		else if (panel.gameState == panel.tradeState) {
			tradeState(code);
		}
	}

	private void tradeState(int code) {
		
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if (panel.ui.subState == 0) {
			if (code == KeyEvent.VK_W) {
				panel.ui.commandNum--;
				if (panel.ui.commandNum < 0) {
					panel.ui.commandNum = 2;
				}
				panel.playSFX(8);
			}
			if (code == KeyEvent.VK_S) {
				panel.ui.commandNum++;
				if (panel.ui.commandNum > 2) {
					panel.ui.commandNum = 0;
				}
				panel.playSFX(8);
			}	
		}	
		
		if (panel.ui.subState == 1) {
			npcInventory(code);
			if (code == KeyEvent.VK_ESCAPE) {
				panel.ui.subState = 0;
			}
		}
		
		if (panel.ui.subState == 2) {
			playerInventory(code);
			if (code == KeyEvent.VK_ESCAPE) {
				panel.ui.subState = 0;
			}
		}
	}

	public void titleState(int code) {

		if (code == KeyEvent.VK_W) {
			panel.ui.commandNum--;
			if (panel.ui.commandNum < 0) {
				panel.ui.commandNum = 2;
			}
		}
		if (code == KeyEvent.VK_S) {
			panel.ui.commandNum++;
			if (panel.ui.commandNum > 2) {
				panel.ui.commandNum = 0;
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			if (panel.ui.commandNum == 0) {
				
				panel.gameState = panel.playState;
				panel.restart();
				panel.playMusic(0);
			}
			if (panel.ui.commandNum == 1) {
				// later
			}
			if (panel.ui.commandNum == 2) {
				System.exit(0);
			}
		}
	}

	public void playState(int code) {

		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}

		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}

		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}

		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}

		if (code == KeyEvent.VK_P) {
			panel.gameState = panel.pauseState;	
		}

		if (code == KeyEvent.VK_C) {
			panel.gameState = panel.characterState;
		}

		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		if (code == KeyEvent.VK_SHIFT) {
			shotKeyPressed = true;
		}

		if (code == KeyEvent.VK_ESCAPE) {
			panel.gameState = panel.optionState;
		}

	}

	public void pauseState(int code) {

		if (code == KeyEvent.VK_P) {
			panel.gameState = panel.playState;	
		}
	}

	public void dialogueState(int code) {

		if (code == KeyEvent.VK_ENTER) {
			panel.gameState = panel.playState;
		}
	}

	public void warningState(int code) {

		if (code == KeyEvent.VK_ENTER) {
			panel.gameState = panel.playState;
		}
	}

	public void characterState(int code) {

		if (code == KeyEvent.VK_C) {
			panel.gameState = panel.playState;
		}
		
		if (code == KeyEvent.VK_ENTER) {
			panel.player.selectItem();
		}
		
		playerInventory(code);
	}

	public void optionState(int code) {

		if (code == KeyEvent.VK_ESCAPE) {
			panel.gameState = panel.playState;	
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		int maxCommandNum = 0;

		switch(panel.ui.subState)  {
		case 0: maxCommandNum = 4; break;
		case 2: maxCommandNum = 1; break;
		}

		if (code == KeyEvent.VK_W) {
			panel.ui.commandNum--;
			panel.playSFX(8);
			if (panel.ui.commandNum < 0) {
				panel.ui.commandNum = maxCommandNum;
			}
		}
		if (code == KeyEvent.VK_S) {
			panel.ui.commandNum++;
			panel.playSFX(8);
			if (panel.ui.commandNum > maxCommandNum) {
				panel.ui.commandNum = 0;
			}
		}

		if (code == KeyEvent.VK_A) {
			if(panel.ui.subState == 0) {
				if(panel.ui.commandNum == 0 && panel.music.volumeScale > 0) {
					panel.music.volumeScale--;
					panel.music.checkVolume();
					panel.playSFX(8);
				}
				if(panel.ui.commandNum == 1 && panel.sfx.volumeScale > 0) {
					panel.sfx.volumeScale--;
					panel.playSFX(8);
				}
			}
		}

		if (code == KeyEvent.VK_D) {
			if(panel.ui.subState == 0) {
				if(panel.ui.commandNum == 0 && panel.music.volumeScale < 5) {
					panel.music.volumeScale++;
					panel.music.checkVolume();
					panel.playSFX(8);
				}
				if(panel.ui.commandNum == 1 && panel.sfx.volumeScale < 5) {
					panel.sfx.volumeScale++;
					panel.playSFX(8);
				}
			}
		}
	}

	private void gameOverState(int code) {

		if (code == KeyEvent.VK_W) {

			panel.ui.commandNum--;
			if (panel.ui.commandNum < 0) {
				panel.ui.commandNum = 1;
			}

			panel.playSFX(8);
		}

		if (code == KeyEvent.VK_S) {

			panel.ui.commandNum++;
			if (panel.ui.commandNum > 1) {
				panel.ui.commandNum = 0;
			}

			panel.playSFX(8);
		}

		if (code == KeyEvent.VK_ENTER) {
			if (panel.ui.commandNum == 0) {
				panel.gameState = panel.playState;
				panel.retry();
			}
			if (panel.ui.commandNum == 1) {
				panel.gameState = panel.titleState;
				panel.restart();
			}
		}
	}
	
	public void playerInventory(int code) {
		
		if (code == KeyEvent.VK_W) {
			if (panel.ui.playerSlotRow != 0) {
				panel.ui.playerSlotRow--;
				panel.playSFX(8);
			}
		}
		if (code == KeyEvent.VK_S) {
			if (panel.ui.playerSlotRow != 3) {
				panel.ui.playerSlotRow++;
				panel.playSFX(8);
			}
		}
		if (code == KeyEvent.VK_A) {
			if (panel.ui.playerSlotCol != 0) {
				panel.ui.playerSlotCol--;
				panel.playSFX(8);
			}
		}
		if (code == KeyEvent.VK_D) {
			if (panel.ui.playerSlotCol != 4) {
				panel.ui.playerSlotCol++;
				panel.playSFX(8);
			}
		}
	}
	
	public void npcInventory(int code) {
		
		if (code == KeyEvent.VK_W) {
			if (panel.ui.npcSlotRow != 0) {
				panel.ui.npcSlotRow--;
				panel.playSFX(8);
			}
		}
		if (code == KeyEvent.VK_S) {
			if (panel.ui.npcSlotRow != 3) {
				panel.ui.npcSlotRow++;
				panel.playSFX(8);
			}
		}
		if (code == KeyEvent.VK_A) {
			if (panel.ui.npcSlotCol != 0) {
				panel.ui.npcSlotCol--;
				panel.playSFX(8);
			}
		}
		if (code == KeyEvent.VK_D) {
			if (panel.ui.npcSlotCol != 4) {
				panel.ui.npcSlotCol++;
				panel.playSFX(8);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}

		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}

		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}

		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}

		if (code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}

		if (code == KeyEvent.VK_SHIFT) {
			shotKeyPressed = false;
		}
	}

}