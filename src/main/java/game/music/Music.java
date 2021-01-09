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
            if (GameVariables.clip.isOpen()) {
                GameVariables.clip.close();
            }
            AudioInputStream audioIn =
                AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("music\\" + musicPath));

            GameVariables.clip.open(audioIn);
            FloatControl volume = (FloatControl) GameVariables.clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(GameVariables.getMusicVolume());
            GameVariables.clip.loop(Clip.LOOP_CONTINUOUSLY);
            GameVariables.clip.start();
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
