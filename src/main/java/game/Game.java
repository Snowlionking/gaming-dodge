package game;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -4584388369897487885L;

    private GameLoop gameLoop = new GameLoop();

    private transient Thread thread;

    public Game() {
        new Window(GameVariables.WIDTH, GameVariables.HEIGHT, "Dodge-City", this);
    }

    public static void main(String[] args) {
        new Game();
    }

    public void run() {
        gameLoop.loop(this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        GameVariables.running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            GameVariables.running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
