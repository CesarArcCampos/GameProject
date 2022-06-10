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

		if (panel.gameState == panel.playState) {

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
			if (code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}

		if (panel.gameState == panel.pauseState) {

			if (code == KeyEvent.VK_P) {
				panel.gameState = panel.playState;	
			}
		}

		if (panel.gameState == panel.dialogueState) {
			if (code == KeyEvent.VK_ENTER) {
				panel.gameState = panel.playState;
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
	}

}