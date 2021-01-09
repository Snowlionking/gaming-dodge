package game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import lombok.Getter;
import lombok.Setter;

public class GameVariables {

    @Getter
    private static final int WIDTH = 1280;
    @Getter
    private static final int HEIGHT = WIDTH / 12 * 9;

    @Getter
    @Setter
    private static long soundVolume = 0;
    @Getter
    @Setter
    private static long musicVolume = 0;

    @Getter
    @Setter
    private static GameState state = GameState.MENU;

    @Getter
    @Setter
    private static int level = 1;
    @Getter
    @Setter
    private static int health = 100;
    @Getter
    @Setter
    private static int score = 0;
    @Getter
    @Setter
    private static int volumeX = GameVariables.getWIDTH() / 2 + 495;
    @Getter
    @Setter
    private static int soundX = GameVariables.getWIDTH() / 2 + 495;

    @Getter
    @Setter
    private static boolean safeFileRead = false;
    @Getter
    @Setter
    private static boolean highscoreSet = false;
    @Getter
    @Setter
    private static boolean running = false;
    @Getter
    @Setter
    private static boolean musicRunning = false;

    @Getter
    @Setter
    private static Clip clip;

    static {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private GameVariables() {

    }

}
