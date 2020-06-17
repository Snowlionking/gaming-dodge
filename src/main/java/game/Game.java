package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import controlls.KeyInput;
import entities.Handler;
import game.hud.Hud;
import services.Spawner;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -4584388369897487885L;

	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 12 * 9;

	public static boolean gameOver = false;

	private boolean running = false;

	public static GameState state;

	private Thread thread;
	private Handler handler = new Handler();
	private Hud hud = new Hud();
	private Spawner spawner = new Spawner();

	public Game() {
		state = GameState.MENU;

		new Window(WIDTH, HEIGHT, "Schwarzhafen", this);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		initializeSpawns();

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (state == GameState.PLAYING) {
			handler.render(g);
		}

		hud.render(g);

		g.dispose();
		bs.show();
	}

	private void initializeSpawns() {
		if (handler.getPlayer() == null) {
			spawner.spawnPlayer(handler);
			spawner.spawnTrackEnemy(handler, handler.getPlayer());
			spawner.spawnPoint(handler);
		}
	}

	private void tick() {
		if (state == GameState.PLAYING) {
			handler.tick();
		}
		hud.tick();

		if(Hud.HEALTH <= 0) {
			Game.state = GameState.GAMEOVER;
		}
	}

	public static void main(String[] args) {
		new Game();
	}

	public void run() {
		this.requestFocus();
		this.addKeyListener(new KeyInput(handler));
//		initGlfw();

		gameLoop();
	}

	private void gameLoop() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
//			glfwPollEvents();
//			FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
//			System.out.println(axes.get());
		}
		stop();
	}

//	private void initGlfw() {
//		if (!glfwInit())
//			throw new IllegalStateException("Unable to initialize GLFW");
//	}

}
