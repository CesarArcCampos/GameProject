package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed;
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
	}

}