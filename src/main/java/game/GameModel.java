package game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import lombok.Data;

@Data
public class GameModel {

    public static final int WIDTH = 1280;

    public static final int HEIGHT = WIDTH / 12 * 9;

    public static long soundVolume = 0;

    public static long musicVolume = 0;

    private boolean highscoreSet;

    private GameState state;

    private int level;

    private boolean running;

    private boolean musicRunning;

    private Clip clip;

    public GameModel() {
        try {
            setClip(AudioSystem.getClip());
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
