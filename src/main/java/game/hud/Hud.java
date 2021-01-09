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
    public static int HEALTH = 100;
    public static int SCORE = 0;
    public static int volumeX = GameVariables.WIDTH / 2 + 495;
    public static int soundX = GameVariables.WIDTH / 2 + 495;

    public static boolean safeFileRead = false;

    private List<Integer> highscores = new ArrayList<>();

    private InboundService inboundService = new InboundService();
    private HighscoreService highscoreService = new HighscoreService();

    public void tick() {
        HEALTH = inboundService.clamp(HEALTH, 0, 100);
    }

    public void render(Graphics g) {
        switch (GameVariables.state) {
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
                g.drawString("Level: " + Integer.toString(GameVariables.level), 2, 84);
                g.drawString("Score: " + Integer.toString(SCORE), 2, 64);
                break;
            case MENU:
                g.setColor(Color.white);
                g.setFont(new Font(TIMES_ROMAN, Font.BOLD, 48));
                g.drawString("DODGE-CITY", GameVariables.WIDTH / 2 - 156, 96);
                g.setFont(new Font(TIMES_ROMAN, Font.PLAIN, 24));
                g.drawRect(GameVariables.WIDTH / 2 - 45, GameVariables.HEIGHT / 2 - 115, 90, 48);
                g.drawString("PLAY", GameVariables.WIDTH / 2 - 30, GameVariables.HEIGHT / 2 - 80);
                g.drawRect(GameVariables.WIDTH / 2 - 75, GameVariables.HEIGHT / 2 - 36, 165, 48);
                g.drawString("SETTINGS", GameVariables.WIDTH / 2 - 55, GameVariables.HEIGHT / 2 - 5);
                g.drawRect(GameVariables.WIDTH / 2 - 84, GameVariables.HEIGHT / 2 + 44, 180, 48);
                g.drawString("HIGHSCORES", GameVariables.WIDTH / 2 - 75, GameVariables.HEIGHT / 2 + 75);
                g.drawRect(GameVariables.WIDTH / 2 - 64, GameVariables.HEIGHT - 256, 128, 48);
                g.drawString("EXIT", GameVariables.WIDTH / 2 - 24, GameVariables.HEIGHT - 222);
                break;
            case GAMEOVER:
                g.setColor(Color.white);
                g.setFont(new Font(TIMES_ROMAN, Font.PLAIN, 24));
                g.drawString("YOU DIED!", GameVariables.WIDTH / 2 - 115, GameVariables.HEIGHT / 2);
                g.drawString("SCORE: " + Integer.toString(SCORE), GameVariables.WIDTH / 2 - 115,
                    GameVariables.HEIGHT / 2 + 128);
                g.drawString("PRESS ANYWHERE TO GO BACK TO MENU", GameVariables.WIDTH / 2 - 256,
                    GameVariables.HEIGHT / 2 + 256);
                break;
            case HIGHSCORES:
                g.setColor(Color.white);
                g.setFont(new Font(TIMES_ROMAN, Font.PLAIN, 48));
                g.drawString("BACK", 10, 50);
                g.drawRect(1, 1, 154, 64);
                g.drawString("HIGHSCORES", 488, 84);
                if (!safeFileRead) {
                    highscores = highscoreService.loadHighscores();
                    safeFileRead = true;
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
                g.drawString("ADJUST MUSIC VOLUME", GameVariables.WIDTH / 2 - 200, GameVariables.HEIGHT / 2 - 300);
                g.drawLine(GameVariables.WIDTH / 2 - 500, GameVariables.HEIGHT / 2 - 200, GameVariables.WIDTH / 2 + 500,
                    GameVariables.HEIGHT / 2 - 200);
                g.drawLine(GameVariables.WIDTH / 2 + 500, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2 + 500,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawLine(GameVariables.WIDTH / 2 + 400, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2 + 400,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawLine(GameVariables.WIDTH / 2 + 300, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2 + 300,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawLine(GameVariables.WIDTH / 2 + 200, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2 + 200,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawLine(GameVariables.WIDTH / 2 + 100, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2 + 100,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawLine(GameVariables.WIDTH / 2, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawLine(GameVariables.WIDTH / 2 - 100, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2 - 100,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawLine(GameVariables.WIDTH / 2 - 200, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2 - 200,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawLine(GameVariables.WIDTH / 2 - 300, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2 - 300,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawLine(GameVariables.WIDTH / 2 - 400, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2 - 400,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawLine(GameVariables.WIDTH / 2 - 500, GameVariables.HEIGHT / 2 - 225, GameVariables.WIDTH / 2 - 500,
                    GameVariables.HEIGHT / 2 - 175);
                g.drawString("0", GameVariables.WIDTH / 2 - 507, GameVariables.HEIGHT / 2 - 140);
                g.drawString("10", GameVariables.WIDTH / 2 - 417, GameVariables.HEIGHT / 2 - 140);
                g.drawString("20", GameVariables.WIDTH / 2 - 317, GameVariables.HEIGHT / 2 - 140);
                g.drawString("30", GameVariables.WIDTH / 2 - 217, GameVariables.HEIGHT / 2 - 140);
                g.drawString("40", GameVariables.WIDTH / 2 - 117, GameVariables.HEIGHT / 2 - 140);
                g.drawString("50", GameVariables.WIDTH / 2 - 17, GameVariables.HEIGHT / 2 - 140);
                g.drawString("60", GameVariables.WIDTH / 2 + 83, GameVariables.HEIGHT / 2 - 140);
                g.drawString("70", GameVariables.WIDTH / 2 + 183, GameVariables.HEIGHT / 2 - 140);
                g.drawString("80", GameVariables.WIDTH / 2 + 283, GameVariables.HEIGHT / 2 - 140);
                g.drawString("90", GameVariables.WIDTH / 2 + 383, GameVariables.HEIGHT / 2 - 140);
                g.drawString("100", GameVariables.WIDTH / 2 + 475, GameVariables.HEIGHT / 2 - 140);
                g.fillRect(volumeX, GameVariables.HEIGHT / 2 - 250, 10, 80);

                g.drawString("ADJUST SOUND VOLUME", GameVariables.WIDTH / 2 - 200, GameVariables.HEIGHT / 2);
                g.drawLine(GameVariables.WIDTH / 2 - 500, GameVariables.HEIGHT / 2 + 100, GameVariables.WIDTH / 2 + 500,
                    GameVariables.HEIGHT / 2 + 100);
                g.drawLine(GameVariables.WIDTH / 2 + 500, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2 + 500,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawLine(GameVariables.WIDTH / 2 + 400, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2 + 400,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawLine(GameVariables.WIDTH / 2 + 300, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2 + 300,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawLine(GameVariables.WIDTH / 2 + 200, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2 + 200,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawLine(GameVariables.WIDTH / 2 + 100, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2 + 100,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawLine(GameVariables.WIDTH / 2, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawLine(GameVariables.WIDTH / 2 - 100, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2 - 100,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawLine(GameVariables.WIDTH / 2 - 200, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2 - 200,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawLine(GameVariables.WIDTH / 2 - 300, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2 - 300,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawLine(GameVariables.WIDTH / 2 - 400, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2 - 400,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawLine(GameVariables.WIDTH / 2 - 500, GameVariables.HEIGHT / 2 + 75, GameVariables.WIDTH / 2 - 500,
                    GameVariables.HEIGHT / 2 + 125);
                g.drawString("0", GameVariables.WIDTH / 2 - 507, GameVariables.HEIGHT / 2 + 160);
                g.drawString("10", GameVariables.WIDTH / 2 - 417, GameVariables.HEIGHT / 2 + 160);
                g.drawString("20", GameVariables.WIDTH / 2 - 317, GameVariables.HEIGHT / 2 + 160);
                g.drawString("30", GameVariables.WIDTH / 2 - 217, GameVariables.HEIGHT / 2 + 160);
                g.drawString("40", GameVariables.WIDTH / 2 - 117, GameVariables.HEIGHT / 2 + 160);
                g.drawString("50", GameVariables.WIDTH / 2 - 17, GameVariables.HEIGHT / 2 + 160);
                g.drawString("60", GameVariables.WIDTH / 2 + 83, GameVariables.HEIGHT / 2 + 160);
                g.drawString("70", GameVariables.WIDTH / 2 + 183, GameVariables.HEIGHT / 2 + 160);
                g.drawString("80", GameVariables.WIDTH / 2 + 283, GameVariables.HEIGHT / 2 + 160);
                g.drawString("90", GameVariables.WIDTH / 2 + 383, GameVariables.HEIGHT / 2 + 160);
                g.drawString("100", GameVariables.WIDTH / 2 + 475, GameVariables.HEIGHT / 2 + 160);
                g.fillRect(soundX, GameVariables.HEIGHT / 2 + 50, 10, 80);
                break;
            default:
                break;
        }
    }
}
