package controlls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import entities.Handler;
import game.Game;
import game.GameState;
import game.hud.Hud;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;

	public MouseInput(Handler handler) {
		this.handler = handler;
	}


    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
    	int y = e.getY();
    	System.out.println("X: " + x + "Y: " + y);
    	switch (Game.gameModel.getState()) {
		case MENU:
			if (x >= 576 && x <= 704 && y >= 698 && y <= 745) {
				System.exit(1);
			}
			if (x >= 596 && x <= 684 && y >= 364 && y <= 411) {
				Game.gameModel.setState(GameState.PLAYING);
			}
			if (x >= 558 && x <= 735 && y >= 521 && y <= 571) {
				Game.gameModel.setState(GameState.HIGHSCORES);
				Game.gameModel.setMusicRunning(false);
			}
			break;
		case HIGHSCORES:
			if (x >= 0 && x <= 156 && y >= 0 && y <= 66) {
				Game.gameModel.setState(GameState.MENU);
				Game.gameModel.setMusicRunning(false);
				Hud.safeFileRead = false;
			}
			break;
		case GAMEOVER:
			Hud.SCORE = 0;
			Hud.HEALTH = 100;
			handler.getPlayer().setVelX(0);
			handler.getPlayer().setVelY(0);
			Game.gameModel.setState(GameState.MENU);
			Game.gameModel.setMusicRunning(false);
		default:
			break;
		}
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseWheelMoved(MouseWheelEvent e){}

    public void mouseDragged(MouseEvent e){}

    public void mouseMoved(MouseEvent e){}
	
}
