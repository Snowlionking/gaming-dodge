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
    private static boolean highscoreSet = false;
    @Getter
    @Setter
    private static boolean running = false;
    @Getter
    @Setter
    private static boolean musicRunning = false;

    @Getter
    @Setter
    public static Clip clip;

    static {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
