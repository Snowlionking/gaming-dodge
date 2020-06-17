package game.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;
import services.InboundService;

public class Hud {
	public static int HEALTH = 100;
	public static int SCORE = 0;

	private InboundService inboundService = new InboundService();

	public void tick() {
		HEALTH = inboundService.clamp(HEALTH, 0, 100);
	}

	public void render(Graphics g) {
		switch (Game.state) {
		case PLAYING:
			g.setColor(Color.gray);
			g.fillRect(1, 1, 100, 24);
			g.setColor(Color.green);
			g.fillRect(1, 1, HEALTH, 24);
			g.setColor(Color.white);
			g.drawRect(1, 1, 100, 24);
			g.setColor(Color.white);
			g.drawString("Health: " + Integer.toString(HEALTH), 2, 48);
			g.drawString("Score: " + Integer.toString(SCORE), 2, 64);
			break;
		case MENU:
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			g.drawString("PRESS \"P\" TO PLAY", Game.WIDTH / 2 - 115, Game.HEIGHT / 2);
			break;
		case GAMEOVER:
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			g.drawString("YOU DIED!", Game.WIDTH / 2 - 115, Game.HEIGHT / 2);
			g.drawString("SCORE: " + Integer.toString(SCORE), Game.WIDTH / 2 - 115, Game.HEIGHT / 2 + 128);
			break;
		default:
			break;
		}
	}
}
