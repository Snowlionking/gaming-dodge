package game.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import game.GameVariables;
import services.HighscoreService;
import services.InboundService;

public class Hud {

    private static final String TIMES_ROMAN = "TimesRoman";

    private List<Integer> highscores = new ArrayList<>();

    private InboundService inboundService = new InboundService();
    private HighscoreService highscoreService = new HighscoreService();

    public void tick() {
        GameVariables.setHealth(inboundService.clamp(GameVariables.getHealth(), 0, 100));
    }

    public void render(Graphics g) {
        switch (GameVariables.getState()) {
            case PLAYING:
                g.setColor(Color.gray);
                g.fillRect(1, 1, 100, 24);
                g.setColor(Color.green);
                g.fillRect(1, 1, GameVariables.getHealth(), 24);
                g.setColor(Color.white);
                g.drawRect(1, 1, 100, 24);
                g.setColor(Color.white);
                g.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
                g.drawString("Health: " + Integer.toString(GameVariables.getHealth()), 2, 44);
                g.drawString("Level: " + Integer.toString(GameVariables.getLevel()), 2, 84);
                g.drawString("Score: " + Integer.toString(GameVariables.getScore()), 2, 64);
                break;
            case MENU:
                g.setColor(Color.white);
                g.setFont(new Font(TIMES_ROMAN, Font.BOLD, 48));
                g.drawString("DODGE-CITY", GameVariables.getWIDTH() / 2 - 156, 96);
                g.setFont(new Font(TIMES_ROMAN, Font.PLAIN, 24));
                g.drawRect(GameVariables.getWIDTH() / 2 - 45, GameVariables.getHEIGHT() / 2 - 115, 90, 48);
                g.drawString("PLAY", GameVariables.getWIDTH() / 2 - 30, GameVariables.getHEIGHT() / 2 - 80);
                g.drawRect(GameVariables.getWIDTH() / 2 - 75, GameVariables.getHEIGHT() / 2 - 36, 165, 48);
                g.drawString("SETTINGS", GameVariables.getWIDTH() / 2 - 55, GameVariables.getHEIGHT() / 2 - 5);
                g.drawRect(GameVariables.getWIDTH() / 2 - 84, GameVariables.getHEIGHT() / 2 + 44, 180, 48);
                g.drawString("HIGHSCORES", GameVariables.getWIDTH() / 2 - 75, GameVariables.getHEIGHT() / 2 + 75);
                g.drawRect(GameVariables.getWIDTH() / 2 - 64, GameVariables.getHEIGHT() - 256, 128, 48);
                g.drawString("EXIT", GameVariables.getWIDTH() / 2 - 24, GameVariables.getHEIGHT() - 222);
                break;
            case GAMEOVER:
                g.setColor(Color.white);
                g.setFont(new Font(TIMES_ROMAN, Font.PLAIN, 24));
                g.drawString("YOU DIED!", GameVariables.getWIDTH() / 2 - 115, GameVariables.getHEIGHT() / 2);
                g.drawString("SCORE: " + Integer.toString(GameVariables.getScore()), GameVariables.getWIDTH() / 2 - 115,
                    GameVariables.getHEIGHT() / 2 + 128);
                g.drawString("PRESS ANYWHERE TO GO BACK TO MENU", GameVariables.getWIDTH() / 2 - 256,
                    GameVariables.getHEIGHT() / 2 + 256);
                break;
            case HIGHSCORES:
                g.setColor(Color.white);
                g.setFont(new Font(TIMES_ROMAN, Font.PLAIN, 48));
                g.drawString("BACK", 10, 50);
                g.drawRect(1, 1, 154, 64);
                g.drawString("HIGHSCORES", 488, 84);
                if (!GameVariables.isSafeFileRead()) {
                    highscores = highscoreService.loadHighscores();
                    GameVariables.setSafeFileRead(true);
                }
                g.setFont(new Font(TIMES_ROMAN, Font.PLAIN, 24));
                highscores = highscores.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                for (int i = 0; i < highscores.size(); i++) {
                    if (i >= 20) {
                        break;
                    }
                    g.drawString((i + 1) + ". " + highscores.get(i), 550, 140 + (i * 35));
                }
                break;
            case SETTINGS:
                g.setColor(Color.white);
                g.setFont(new Font(TIMES_ROMAN, Font.PLAIN, 48));
                g.drawString("BACK", 10, 50);
                g.drawRect(1, 1, 154, 64);
                g.setFont(new Font(TIMES_ROMAN, Font.PLAIN, 32));
                g.drawString("ADJUST MUSIC VOLUME", GameVariables.getWIDTH() / 2 - 200,
                    GameVariables.getHEIGHT() / 2 - 300);
                g.drawLine(GameVariables.getWIDTH() / 2 - 500, GameVariables.getHEIGHT() / 2 - 200,
                    GameVariables.getWIDTH() / 2 + 500, GameVariables.getHEIGHT() / 2 - 200);
                g.drawLine(GameVariables.getWIDTH() / 2 + 500, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2 + 500, GameVariables.getHEIGHT() / 2 - 175);
                g.drawLine(GameVariables.getWIDTH() / 2 + 400, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2 + 400, GameVariables.getHEIGHT() / 2 - 175);
                g.drawLine(GameVariables.getWIDTH() / 2 + 300, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2 + 300, GameVariables.getHEIGHT() / 2 - 175);
                g.drawLine(GameVariables.getWIDTH() / 2 + 200, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2 + 200, GameVariables.getHEIGHT() / 2 - 175);
                g.drawLine(GameVariables.getWIDTH() / 2 + 100, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2 + 100, GameVariables.getHEIGHT() / 2 - 175);
                g.drawLine(GameVariables.getWIDTH() / 2, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2, GameVariables.getHEIGHT() / 2 - 175);
                g.drawLine(GameVariables.getWIDTH() / 2 - 100, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2 - 100, GameVariables.getHEIGHT() / 2 - 175);
                g.drawLine(GameVariables.getWIDTH() / 2 - 200, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2 - 200, GameVariables.getHEIGHT() / 2 - 175);
                g.drawLine(GameVariables.getWIDTH() / 2 - 300, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2 - 300, GameVariables.getHEIGHT() / 2 - 175);
                g.drawLine(GameVariables.getWIDTH() / 2 - 400, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2 - 400, GameVariables.getHEIGHT() / 2 - 175);
                g.drawLine(GameVariables.getWIDTH() / 2 - 500, GameVariables.getHEIGHT() / 2 - 225,
                    GameVariables.getWIDTH() / 2 - 500, GameVariables.getHEIGHT() / 2 - 175);
                g.drawString("0", GameVariables.getWIDTH() / 2 - 507, GameVariables.getHEIGHT() / 2 - 140);
                g.drawString("10", GameVariables.getWIDTH() / 2 - 417, GameVariables.getHEIGHT() / 2 - 140);
                g.drawString("20", GameVariables.getWIDTH() / 2 - 317, GameVariables.getHEIGHT() / 2 - 140);
                g.drawString("30", GameVariables.getWIDTH() / 2 - 217, GameVariables.getHEIGHT() / 2 - 140);
                g.drawString("40", GameVariables.getWIDTH() / 2 - 117, GameVariables.getHEIGHT() / 2 - 140);
                g.drawString("50", GameVariables.getWIDTH() / 2 - 17, GameVariables.getHEIGHT() / 2 - 140);
                g.drawString("60", GameVariables.getWIDTH() / 2 + 83, GameVariables.getHEIGHT() / 2 - 140);
                g.drawString("70", GameVariables.getWIDTH() / 2 + 183, GameVariables.getHEIGHT() / 2 - 140);
                g.drawString("80", GameVariables.getWIDTH() / 2 + 283, GameVariables.getHEIGHT() / 2 - 140);
                g.drawString("90", GameVariables.getWIDTH() / 2 + 383, GameVariables.getHEIGHT() / 2 - 140);
                g.drawString("100", GameVariables.getWIDTH() / 2 + 475, GameVariables.getHEIGHT() / 2 - 140);
                g.fillRect(GameVariables.getVolumeX(), GameVariables.getHEIGHT() / 2 - 250, 10, 80);

                g.drawString("ADJUST SOUND VOLUME", GameVariables.getWIDTH() / 2 - 200, GameVariables.getHEIGHT() / 2);
                g.drawLine(GameVariables.getWIDTH() / 2 - 500, GameVariables.getHEIGHT() / 2 + 100,
                    GameVariables.getWIDTH() / 2 + 500, GameVariables.getHEIGHT() / 2 + 100);
                g.drawLine(GameVariables.getWIDTH() / 2 + 500, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2 + 500, GameVariables.getHEIGHT() / 2 + 125);
                g.drawLine(GameVariables.getWIDTH() / 2 + 400, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2 + 400, GameVariables.getHEIGHT() / 2 + 125);
                g.drawLine(GameVariables.getWIDTH() / 2 + 300, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2 + 300, GameVariables.getHEIGHT() / 2 + 125);
                g.drawLine(GameVariables.getWIDTH() / 2 + 200, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2 + 200, GameVariables.getHEIGHT() / 2 + 125);
                g.drawLine(GameVariables.getWIDTH() / 2 + 100, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2 + 100, GameVariables.getHEIGHT() / 2 + 125);
                g.drawLine(GameVariables.getWIDTH() / 2, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2, GameVariables.getHEIGHT() / 2 + 125);
                g.drawLine(GameVariables.getWIDTH() / 2 - 100, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2 - 100, GameVariables.getHEIGHT() / 2 + 125);
                g.drawLine(GameVariables.getWIDTH() / 2 - 200, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2 - 200, GameVariables.getHEIGHT() / 2 + 125);
                g.drawLine(GameVariables.getWIDTH() / 2 - 300, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2 - 300, GameVariables.getHEIGHT() / 2 + 125);
                g.drawLine(GameVariables.getWIDTH() / 2 - 400, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2 - 400, GameVariables.getHEIGHT() / 2 + 125);
                g.drawLine(GameVariables.getWIDTH() / 2 - 500, GameVariables.getHEIGHT() / 2 + 75,
                    GameVariables.getWIDTH() / 2 - 500, GameVariables.getHEIGHT() / 2 + 125);
                g.drawString("0", GameVariables.getWIDTH() / 2 - 507, GameVariables.getHEIGHT() / 2 + 160);
                g.drawString("10", GameVariables.getWIDTH() / 2 - 417, GameVariables.getHEIGHT() / 2 + 160);
                g.drawString("20", GameVariables.getWIDTH() / 2 - 317, GameVariables.getHEIGHT() / 2 + 160);
                g.drawString("30", GameVariables.getWIDTH() / 2 - 217, GameVariables.getHEIGHT() / 2 + 160);
                g.drawString("40", GameVariables.getWIDTH() / 2 - 117, GameVariables.getHEIGHT() / 2 + 160);
                g.drawString("50", GameVariables.getWIDTH() / 2 - 17, GameVariables.getHEIGHT() / 2 + 160);
                g.drawString("60", GameVariables.getWIDTH() / 2 + 83, GameVariables.getHEIGHT() / 2 + 160);
                g.drawString("70", GameVariables.getWIDTH() / 2 + 183, GameVariables.getHEIGHT() / 2 + 160);
                g.drawString("80", GameVariables.getWIDTH() / 2 + 283, GameVariables.getHEIGHT() / 2 + 160);
                g.drawString("90", GameVariables.getWIDTH() / 2 + 383, GameVariables.getHEIGHT() / 2 + 160);
                g.drawString("100", GameVariables.getWIDTH() / 2 + 475, GameVariables.getHEIGHT() / 2 + 160);
                g.fillRect(GameVariables.getSoundX(), GameVariables.getHEIGHT() / 2 + 50, 10, 80);
                break;
            default:
                break;
        }
    }
}
