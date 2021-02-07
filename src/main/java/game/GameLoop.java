package game;

import entities.Handler;
import game.music.Music;
import game.window.GameOver;
import game.window.Highscores;
import game.window.Hud;
import game.window.Menu;
import game.window.Settings;
import game.window.Window;
import services.HighscoreService;

public class GameLoop {

    private static final String MUSIC_HIGHSCORES = "Patakas_World.wav";
    private static final String MUSIC_GAMEOVER = "Star_Commander1.wav";
    private static final String MUSIC_PLAYING = "Blazer_Rail.wav";

    private HighscoreService highscoreService = new HighscoreService();

    private GameOver gameOver;
    private Handler handler;
    private Highscores highscores;
    private Hud hud;
    private Menu menu;
    private Music music;
    private Settings settings;

    public GameLoop() {
        this.gameOver = new GameOver();
        this.handler = new Handler();
        this.highscores = new Highscores();
        this.hud = new Hud();
        this.menu = new Menu();
        this.music = new Music();
        this.settings = new Settings();
    }

    public void loop(Window window) {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (GameVariables.isRunning()) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                render(window, handler);
                tick(handler);
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
    }

    private void render(Window window, Handler handler) {

        switch (GameVariables.getState()) {
            case MENU:
                menu.render(window, handler);
                break;
            case PLAYING:
                hud.render(window, handler);
                break;
            case SETTINGS:
                settings.render(window, handler);
                break;
            case HIGHSCORES:
                highscores.render(window, handler);
                break;
            case GAMEOVER:
                gameOver.render(window, handler);
            default:
                break;
        }
    }

    private void tick(Handler handler) {
        switch (GameVariables.getState()) {
            case PLAYING:
                if (!GameVariables.isMusicRunning()) {
                    music.startMusic(MUSIC_PLAYING);
                }
                if (GameVariables.isHighscoreSet()) {
                    GameVariables.setHighscoreSet(false);
                }
                GameVariables.setLevel(GameVariables.getScore() / 100 + 1);
                handler.tick();
                break;
            case MENU:
                if (!GameVariables.isMusicRunning()) {
                    music.startMusic(MUSIC_PLAYING);
                }
                break;
            case GAMEOVER:
                if (!GameVariables.isMusicRunning()) {
                    music.startMusic(MUSIC_GAMEOVER);
                }
                break;
            case HIGHSCORES:
                if (!GameVariables.isMusicRunning()) {
                    music.startMusic(MUSIC_HIGHSCORES);
                }
                break;
            case SETTINGS:
                if (!GameVariables.isMusicRunning()) {
                    music.startMusic(MUSIC_PLAYING);
                }
                break;
            default:
                break;
        }

        hud.tick();

        if (GameVariables.getHealth() <= 0) {
            if (!GameVariables.isHighscoreSet()) {
                highscoreService.safeHighscore(Integer.toString(GameVariables.getScore()));
                handler.resetAllEntities();
            }
            handler.getPlayer().setVelX(0);
            handler.getPlayer().setVelY(0);
            GameVariables.setHealth(100);
            GameVariables.setState(GameState.GAMEOVER);
            GameVariables.setMusicRunning(false);
            GameVariables.setWindowCleared(false);
        }
    }
}
