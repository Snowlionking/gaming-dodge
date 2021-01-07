package game.music;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.Game;
import game.GameModel;

public class Music {

    public void playSound(String musicPath) {
        try {

            Clip sound = AudioSystem.getClip();

            AudioInputStream audioIn =
                AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("music\\" + musicPath));

            sound.open(audioIn);
            FloatControl volume = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(GameModel.soundVolume);
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

            if (Game.gameModel.getClip().isOpen()) {
                Game.gameModel.getClip().close();
            }

            AudioInputStream audioIn =
                AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("music\\" + musicPath));

            Game.gameModel.getClip().open(audioIn);
            FloatControl volume = (FloatControl) Game.gameModel.getClip().getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(GameModel.musicVolume);
            Game.gameModel.getClip().loop(Clip.LOOP_CONTINUOUSLY);
            Game.gameModel.getClip().start();
            Game.gameModel.setMusicRunning(true);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
