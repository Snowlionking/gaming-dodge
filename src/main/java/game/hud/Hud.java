package game.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import game.Game;
import game.GameModel;
import services.HighscoreService;
import services.InboundService;

public class Hud {
	public static int HEALTH = 100;
	public static int SCORE = 0;
	
	public static boolean safeFileRead = false;
	private List<Integer> highscores = new ArrayList<>();

	private InboundService inboundService = new InboundService();
	private HighscoreService highscoreService = new HighscoreService();

	public void tick() {
		HEALTH = inboundService.clamp(HEALTH, 0, 100);
	}

	public void render(Graphics g) {
		switch (Game.gameModel.getState()) {
		case PLAYING:
			g.setColor(Color.gray);
			g.fillRect(1, 1, 100, 24);
			g.setColor(Color.green);
			g.fillRect(1, 1, HEALTH, 24);
			g.setColor(Color.white);
			g.drawRect(1, 1, 100, 24);
			g.setColor(Color.white);
			g.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			g.drawString("Health: " + Integer.toString(HEALTH), 2, 44);
			g.drawString("Level: " + Integer.toString(Game.gameModel.getLevel()), 2, 84);
			g.drawString("Score: " + Integer.toString(SCORE), 2, 64);
			break;
		case MENU:
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.BOLD, 48));
			g.drawString("DODGE-CITY", GameModel.WIDTH / 2 - 156, 96);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			g.drawRect(GameModel.WIDTH / 2 - 45, GameModel.HEIGHT / 2 - 115, 90, 48);
			g.drawString("PLAY", GameModel.WIDTH / 2 - 30, GameModel.HEIGHT / 2 - 80);
			g.drawRect(GameModel.WIDTH / 2 - 84, GameModel.HEIGHT / 2 + 44, 180, 48);
			g.drawString("HIGHSCORES", GameModel.WIDTH / 2 - 75, GameModel.HEIGHT / 2 + 75);
			g.drawRect(GameModel.WIDTH / 2 - 64, GameModel.HEIGHT - 256, 128, 48);
			g.drawString("EXIT", GameModel.WIDTH / 2 - 24, GameModel.HEIGHT - 222);
			break;
		case GAMEOVER:
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			g.drawString("YOU DIED!", GameModel.WIDTH / 2 - 115, GameModel.HEIGHT / 2);
			g.drawString("SCORE: " + Integer.toString(SCORE), GameModel.WIDTH / 2 - 115, GameModel.HEIGHT / 2 + 128);
			g.drawString("PRESS ANYWHERE TO GO BACK TO MENU", GameModel.WIDTH / 2 - 256, GameModel.HEIGHT / 2 + 256);
			break;
		case HIGHSCORES:
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
			g.drawString("BACK", 10, 50);
			g.drawRect(1, 1, 154, 64);
			g.drawString("HIGHSCORES", 488, 84);
			if (!safeFileRead) {
				highscores = highscoreService.loadHighscores();
				safeFileRead= true;
			}
			g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			highscores = highscores.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
			for (int i = 0; i < highscores.size(); i++) {
				if (i >= 20) {
					break;
				}
				g.drawString((i + 1) + ". " + highscores.get(i), 550, 140 + (i * 35));
			}
		default:
			break;
		}
	}
}
