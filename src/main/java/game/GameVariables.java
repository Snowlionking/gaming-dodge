package game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import lombok.Data;

@Data
public class GameVariables {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = WIDTH / 12 * 9;

    public static long soundVolume = 0;
    public static long musicVolume = 0;

    public static GameState state = GameState.MENU;

    public static int level = 1;

    public static boolean highscoreSet = false;
    public static boolean running = false;
    public static boolean musicRunning = false;

    public static Clip clip;

    static {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
