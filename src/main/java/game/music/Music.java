package game.music;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.GameVariables;

public class Music {

    public void playSound(String musicPath) {
        try {
            Clip sound = AudioSystem.getClip();

            AudioInputStream audioIn =
                AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("music\\" + musicPath));

            sound.open(audioIn);
            FloatControl volume = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(GameVariables.getSoundVolume());
            sound.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void startMusic(String musicPath) {
        try {
            if (GameVariables.getClip().isOpen()) {
                GameVariables.getClip().close();
            }
            AudioInputStream audioIn =
                AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("music\\" + musicPath));

            GameVariables.getClip().open(audioIn);
            FloatControl volume = (FloatControl) GameVariables.getClip().getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(GameVariables.getMusicVolume());
            GameVariables.getClip().loop(Clip.LOOP_CONTINUOUSLY);
            GameVariables.getClip().start();
            GameVariables.setMusicRunning(true);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
