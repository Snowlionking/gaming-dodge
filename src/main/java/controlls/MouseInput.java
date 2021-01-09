package controlls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import game.GameState;
import game.GameVariables;
import game.hud.Hud;
import game.music.Music;
import services.VolumeConverter;

public class MouseInput extends MouseAdapter {

    private static final String HURT_SOUND = "hurt.wav";

    private static final Logger logger = Logger.getLogger(MouseInput.class.getName());
    private Music music = new Music();

    private VolumeConverter volumeConverter = new VolumeConverter();

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        logger.info("X: " + x + "Y: " + y);
        switch (GameVariables.getState()) {
            case MENU:
                if (x >= 576 && x <= 704 && y >= 698 && y <= 745) {
                    System.exit(1);
                }
                if (x >= 596 && x <= 684 && y >= 364 && y <= 411) {
                    GameVariables.setState(GameState.PLAYING);
                }
                if (x >= 558 && x <= 735 && y >= 521 && y <= 571) {
                    GameVariables.setState(GameState.HIGHSCORES);
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 566 && x <= 729 && y >= 440 && y <= 487) {
                    GameVariables.setState(GameState.SETTINGS);
                }
                break;
            case HIGHSCORES:
                if (x >= 0 && x <= 156 && y >= 0 && y <= 66) {
                    GameVariables.setState(GameState.MENU);
                    GameVariables.setMusicRunning(false);
                    Hud.safeFileRead = false;
                }
                break;
            case GAMEOVER:
                Hud.SCORE = 0;
                Hud.HEALTH = 100;
                GameVariables.setState(GameState.MENU);
                GameVariables.setMusicRunning(false);
                break;
            case SETTINGS:
                if (x >= 0 && x <= 156 && y >= 0 && y <= 66) {
                    GameVariables.setState(GameState.MENU);
                }
                if (x >= 113 && x <= 163 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 - 505;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(-80));
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 213 && x <= 263 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 - 405;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(-72));
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 313 && x <= 363 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 - 305;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(-64));
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 413 && x <= 463 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 - 205;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(-56));
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 513 && x <= 563 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 - 105;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(-48));
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 613 && x <= 663 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 - 5;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(-40));
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 713 && x <= 763 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 + 95;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(-32));
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 813 && x <= 863 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 + 195;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(-24));
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 913 && x <= 963 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 + 295;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(-16));
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 1013 && x <= 1063 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 + 395;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(-8));
                    GameVariables.setMusicRunning(false);
                }
                if (x >= 1113 && x <= 1163 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameVariables.getWIDTH() / 2 + 495;
                    GameVariables.setMusicVolume(volumeConverter.convertFromPercentageToVolume(0));
                    GameVariables.setMusicRunning(false);
                }

                ///////////// SOUND VOLUME /////////////

                if (x >= 113 && x <= 163 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 - 505;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-80));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 213 && x <= 263 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 - 405;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-72));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 313 && x <= 363 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 - 305;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-64));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 413 && x <= 463 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 - 205;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-56));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 513 && x <= 563 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 - 105;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-48));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 613 && x <= 663 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 - 5;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-40));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 713 && x <= 763 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 + 95;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-32));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 813 && x <= 863 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 + 195;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-24));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 913 && x <= 963 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 + 295;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-16));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 1013 && x <= 1063 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 + 395;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-8));
                    music.playSound(HURT_SOUND);
                }
                if (x >= 1113 && x <= 1163 && y >= 537 && y <= 637) {
                    Hud.soundX = GameVariables.getWIDTH() / 2 + 495;
                    GameVariables.setSoundVolume(volumeConverter.convertFromPercentageToVolume(-0));
                    music.playSound(HURT_SOUND);
                }
                break;
            default:
                break;
        }
    }

}
