package game;

import java.awt.Canvas;
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

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -4584388369897487885L;

    public static GameModel gameModel = new GameModel();

    private HighscoreService highscoreService = new HighscoreService();

    private Thread thread;

    private Handler handler = new Handler();

    private Hud hud = new Hud();

    private Music music = new Music();

    private Spawner spawner = new Spawner();

    public Game() {
        gameModel.setHighscoreSet(false);
        gameModel.setLevel(1);
        gameModel.setRunning(false);
        gameModel.setState(GameState.MENU);
        gameModel.setMusicRunning(false);

        new Window(GameModel.WIDTH, GameModel.HEIGHT, "Dodge-City", this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        gameModel.setRunning(true);
    }

    public synchronized void stop() {
        try {
            thread.join();
            gameModel.setRunning(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gameLoop() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        initializeSpawns();
        while (gameModel.isRunning()) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                render();
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, GameModel.WIDTH, GameModel.HEIGHT);

        if (gameModel.getState() == GameState.PLAYING) {
            handler.render(g);
        }

        hud.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        switch (gameModel.getState()) {
            case PLAYING:
                if (!gameModel.isMusicRunning()) {
                    music.startMusic("Blazer_Rail.wav");
                }
                if (gameModel.isHighscoreSet()) {
                    gameModel.setHighscoreSet(false);
                }
                gameModel.setLevel(Hud.SCORE / 100 + 1);
                handler.tick();
                break;
            case MENU:
                if (!gameModel.isMusicRunning()) {
                    music.startMusic("Blazer_Rail.wav");
                }
                break;
            case GAMEOVER:
                if (!gameModel.isMusicRunning()) {
                    music.startMusic("Star_Commander1.wav");
                }
                break;
            case HIGHSCORES:
                if (!gameModel.isMusicRunning()) {
                    music.startMusic("Patakas_World.wav");
                }
                break;
            case SETTINGS:
                if (!gameModel.isMusicRunning()) {
                    music.startMusic("Blazer_Rail.wav");
                }
                break;
            default:
                break;
        }

        hud.tick();

        if (Hud.HEALTH <= 0) {
            if (!gameModel.isHighscoreSet()) {
                highscoreService.safeHighscore(Integer.toString(Hud.SCORE));
                handler.resetAllEntities();
            }
            Hud.HEALTH = 100;
            gameModel.setState(GameState.GAMEOVER);
            gameModel.setMusicRunning(false);
        }
    }

    public static void main(String[] args) {
        new Game();
    }

    public void run() {
        this.requestFocus();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler));

        gameLoop();
    }

    private void initializeSpawns() {
        spawner.spawnPlayer(handler);
        spawner.spawnPoint(handler);
    }
}
