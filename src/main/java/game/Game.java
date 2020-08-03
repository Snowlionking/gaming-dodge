package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import controlls.KeyInput;
import controlls.MouseInput;
import entities.Handler;
import game.hud.Hud;
import services.HighscoreService;
import services.Spawner;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -4584388369897487885L;

	public static GameModel gameModel = new GameModel();

	private HighscoreService highscoreService = new HighscoreService();

	private Thread thread;
	private Handler handler = new Handler();
	private Hud hud = new Hud();
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

	private void startMusic(String musicPath) {
		try {

			if (gameModel.getClip().isOpen()) {
				gameModel.getClip().close();
			}

			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("music\\" + musicPath));

			gameModel.getClip().open(audioIn);
			FloatControl volume = (FloatControl) gameModel.getClip().getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(-10);
			gameModel.getClip().loop(Clip.LOOP_CONTINUOUSLY);
			gameModel.getClip().start();
			gameModel.setMusicRunning(true);

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
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
				startMusic("Blazer_Rail.wav");
			}
			if (gameModel.isHighscoreSet()) {
				gameModel.setHighscoreSet(false);
			}
			gameModel.setLevel(Hud.SCORE / 100 + 1);
			handler.tick();
			break;
		case MENU:
			if (!gameModel.isMusicRunning()) {
				startMusic("Blazer_Rail.wav");
			}
			break;
		case GAMEOVER:
			if (!gameModel.isMusicRunning()) {
				startMusic("Star_Commander1.wav");
			}
			break;
		case HIGHSCORES:
			if (!gameModel.isMusicRunning()) {
				startMusic("Patakas_World.wav");
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
