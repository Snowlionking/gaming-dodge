package controlls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GameState;
import game.GameVariables;
import game.music.Music;
import services.VolumeConverter;

public class MouseInput extends MouseAdapter {

    private static final String HURT_SOUND = "hurt.wav";

    private Logger logger = LoggerFactory.getLogger(MouseInput.class);

    private Music music = new Music();
    private VolumeConverter volumeConverter = new VolumeConverter();

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        logger.info("X: {} - Y: {}", x, y);
        switch (GameVariables.getState()) {
            case HIGHSCORES:
                if (x >= 0 && x <= 156 && y >= 0 && y <= 66) {
                    GameVariables.setState(GameState.MENU);
                    GameVariables.setMusicRunning(false);
                    GameVariables.setSafeFileRead(false);
                }
                break;
            case GAMEOVER:
                GameVariables.setScore(0);
                GameVariables.setHealth(100);
                GameVariables.setState(GameState.MENU);
                GameVariables.setMusicRunning(false);
                break;
            case SETTINGS:

                ///////////// SOUND VOLUME /////////////

                if (x >= 113 && x <= 163 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 - 505);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-80));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 213 && x <= 263 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 - 405);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-72));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 313 && x <= 363 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 - 305);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-64));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 413 && x <= 463 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 - 205);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-56));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 513 && x <= 563 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 - 105);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-48));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 613 && x <= 663 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 - 5);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-40));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 713 && x <= 763 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 + 95);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-32));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 813 && x <= 863 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 + 195);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-24));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 913 && x <= 963 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 + 295);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-16));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 1013 && x <= 1063 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 + 395);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-8));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 1113 && x <= 1163 && y >= 537 && y <= 637) {
                    GameVariables.setSoundX(GameVariables.getWIDTH() / 2 + 495);
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-0));
                    music.playSound(HURT_SOUND);
                }
                break;
            default:
                break;
        }
    }

}
