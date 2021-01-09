package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import controlls.KeyInput;
import controlls.MouseInput;
import entities.Handler;
import game.hud.Hud;
import game.music.Music;
import services.HighscoreService;
import services.Spawner;

public class GameLoop {

    private static final String MUSIC_HIGHSCORES = "Patakas_World.wav";
    private static final String MUSIC_GAMEOVER = "Star_Commander1.wav";
    private static final String MUSIC_PLAYING = "Blazer_Rail.wav";

    private HighscoreService highscoreService = new HighscoreService();

    private Handler handler;
    private Hud hud;
    private Music music;
    private Spawner spawner;

    public GameLoop() {
        this.handler = new Handler();
        this.hud = new Hud();
        this.music = new Music();
        this.spawner = new Spawner();
    }

    public void loop(Game game) {

        game.requestFocus();
        game.addKeyListener(new KeyInput(handler));
        game.addMouseListener(new MouseInput());

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        initializeSpawns(handler);

        while (GameVariables.running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick(handler);
                render(game, handler);
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        game.stop();
    }

    private void render(Game game, Handler handler) {
        BufferStrategy bs = game.getBufferStrategy();
        if (bs == null) {
            game.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, GameVariables.WIDTH, GameVariables.HEIGHT);

        if (GameVariables.state == GameState.PLAYING) {
            handler.render(g);
        }

        hud.render(g);

        g.dispose();
        bs.show();
    }

    private void tick(Handler handler) {
        switch (GameVariables.state) {
            case PLAYING:
                if (!GameVariables.musicRunning) {
                    music.startMusic(MUSIC_PLAYING);
                }
                if (GameVariables.highscoreSet) {
                    GameVariables.highscoreSet = false;
                }
                GameVariables.level = Hud.SCORE / 100 + 1;
                handler.tick();
                break;
            case MENU:
                if (!GameVariables.musicRunning) {
                    music.startMusic(MUSIC_PLAYING);
                }
                break;
            case GAMEOVER:
                if (!GameVariables.musicRunning) {
                    music.startMusic(MUSIC_GAMEOVER);
                }
                break;
            case HIGHSCORES:
                if (!GameVariables.musicRunning) {
                    music.startMusic(MUSIC_HIGHSCORES);
                }
                break;
            case SETTINGS:
                if (!GameVariables.musicRunning) {
                    music.startMusic(MUSIC_PLAYING);
                }
                break;
            default:
                break;
        }

        hud.tick();

        if (Hud.HEALTH <= 0) {
            if (!GameVariables.highscoreSet) {
                highscoreService.safeHighscore(Integer.toString(Hud.SCORE));
                handler.resetAllEntities();
            }
            handler.getPlayer().setVelX(0);
            handler.getPlayer().setVelY(0);
            Hud.HEALTH = 100;
            GameVariables.state = GameState.GAMEOVER;
            GameVariables.musicRunning = false;
        }
    }

    private void initializeSpawns(Handler handler) {
        spawner.spawnPlayer(handler);
        spawner.spawnPoint(handler);
    }
}
