package controlls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import entities.Handler;
import game.Game;
import game.GameModel;
import game.GameState;
import game.hud.Hud;
import services.VolumeConverter;

public class MouseInput extends MouseAdapter {

    private Handler handler;

    private VolumeConverter volumeConverter = new VolumeConverter();

    public MouseInput(Handler handler) {
        this.handler = handler;
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("X: " + x + "Y: " + y);
        switch (Game.gameModel.getState()) {
            case MENU:
                if (x >= 576 && x <= 704 && y >= 698 && y <= 745) {
                    System.exit(1);
                }
                if (x >= 596 && x <= 684 && y >= 364 && y <= 411) {
                    Game.gameModel.setState(GameState.PLAYING);
                }
                if (x >= 558 && x <= 735 && y >= 521 && y <= 571) {
                    Game.gameModel.setState(GameState.HIGHSCORES);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 566 && x <= 729 && y >= 440 && y <= 487) {
                    Game.gameModel.setState(GameState.SETTINGS);
                }
                break;
            case HIGHSCORES:
                if (x >= 0 && x <= 156 && y >= 0 && y <= 66) {
                    Game.gameModel.setState(GameState.MENU);
                    Game.gameModel.setMusicRunning(false);
                    Hud.safeFileRead = false;
                }
                break;
            case GAMEOVER:
                Hud.SCORE = 0;
                Hud.HEALTH = 100;
                handler.getPlayer().setVelX(0);
                handler.getPlayer().setVelY(0);
                Game.gameModel.setState(GameState.MENU);
                Game.gameModel.setMusicRunning(false);
            case SETTINGS:
                if (x >= 0 && x <= 156 && y >= 0 && y <= 66) {
                    Game.gameModel.setState(GameState.MENU);
                }
                if (x >= 113 && x <= 163 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 - 505;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(-80);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 213 && x <= 263 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 - 405;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(-72);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 313 && x <= 363 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 - 305;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(-64);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 413 && x <= 463 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 - 205;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(-56);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 513 && x <= 563 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 - 105;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(-48);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 613 && x <= 663 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 - 5;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(-40);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 713 && x <= 763 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 + 95;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(-32);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 813 && x <= 863 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 + 195;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(-24);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 913 && x <= 963 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 + 295;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(-16);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 1013 && x <= 1063 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 + 395;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(-8);
                    Game.gameModel.setMusicRunning(false);
                }
                if (x >= 1113 && x <= 1163 && y >= 227 && y <= 327) {
                    Hud.volumeX = GameModel.WIDTH / 2 + 495;
                    GameModel.musicVolume = volumeConverter.convertFromPercentageToVolume(0);
                    Game.gameModel.setMusicRunning(false);
                }

                ///////////// SOUND VOLUME /////////////

                if (x >= 113 && x <= 163 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 - 505;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(-80);
                    Game.playSound("hurt.wav");
                }
                if (x >= 213 && x <= 263 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 - 405;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(-72);
                    Game.playSound("hurt.wav");
                }
                if (x >= 313 && x <= 363 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 - 305;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(-64);
                    Game.playSound("hurt.wav");
                }
                if (x >= 413 && x <= 463 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 - 205;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(-56);
                    Game.playSound("hurt.wav");
                }
                if (x >= 513 && x <= 563 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 - 105;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(-48);
                    Game.playSound("hurt.wav");
                }
                if (x >= 613 && x <= 663 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 - 5;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(-40);
                    Game.playSound("hurt.wav");
                }
                if (x >= 713 && x <= 763 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 + 95;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(-32);
                    Game.playSound("hurt.wav");
                }
                if (x >= 813 && x <= 863 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 + 195;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(-24);
                    Game.playSound("hurt.wav");
                }
                if (x >= 913 && x <= 963 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 + 295;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(-16);
                    Game.playSound("hurt.wav");
                }
                if (x >= 1013 && x <= 1063 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 + 395;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(-8);
                    Game.playSound("hurt.wav");
                }
                if (x >= 1113 && x <= 1163 && y >= 537 && y <= 637) {
                    Hud.soundX = GameModel.WIDTH / 2 + 495;
                    GameModel.soundVolume = volumeConverter.convertFromPercentageToVolume(0);
                    Game.playSound("hurt.wav");
                }
                break;
            default:
                break;
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

}
