package controlls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entities.Handler;
import game.Game;
import game.GameState;

public class KeyInput extends KeyAdapter {

	private static final int KEY_W = 0;
	private static final int KEY_A = 1;
	private static final int KEY_S = 2;
	private static final int KEY_D = 3;

	private Handler handler;

	private boolean pressedKeys[] = { false, false, false, false };

	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}

		if (Game.state == GameState.PLAYING) {

			switch (key) {
			case KeyEvent.VK_W:
				handler.getPlayer().setVelY(-8);
				pressedKeys[KEY_W] = true;
				break;
			case KeyEvent.VK_A:
				handler.getPlayer().setVelX(-8);
				pressedKeys[KEY_A] = true;
				break;
			case KeyEvent.VK_S:
				handler.getPlayer().setVelY(8);
				pressedKeys[KEY_S] = true;
				break;
			case KeyEvent.VK_D:
				handler.getPlayer().setVelX(8);
				pressedKeys[KEY_D] = true;
				break;
			default:
				break;
			}
		} else if(Game.state == GameState.MENU) {
			switch (key) {
			case KeyEvent.VK_P:
				Game.state = GameState.PLAYING;
				break;
			default:
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (Game.state == GameState.PLAYING) {
			switch (key) {
			case KeyEvent.VK_W:
				pressedKeys[KEY_W] = false;
				if (pressedKeys[KEY_S]) {
					handler.getPlayer().setVelY(8);
				} else {
					handler.getPlayer().setVelY(0);
				}
				break;
			case KeyEvent.VK_A:
				pressedKeys[KEY_A] = false;
				if (pressedKeys[KEY_D]) {
					handler.getPlayer().setVelX(8);
				} else {
					handler.getPlayer().setVelX(0);
				}
				break;
			case KeyEvent.VK_S:
				pressedKeys[KEY_S] = false;
				if (pressedKeys[KEY_W]) {
					handler.getPlayer().setVelY(-8);
				} else {
					handler.getPlayer().setVelY(0);
				}
				break;
			case KeyEvent.VK_D:
				pressedKeys[KEY_D] = false;
				if (pressedKeys[KEY_A]) {
					handler.getPlayer().setVelX(-8);
				} else {
					handler.getPlayer().setVelX(0);
				}
				break;
			default:
				break;
			}
		}
	}
}
