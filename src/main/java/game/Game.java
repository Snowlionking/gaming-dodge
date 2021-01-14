package game;

import java.awt.Canvas;

import game.window.Window;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -4584388369897487885L;

    private transient GameLoop gameLoop = new GameLoop();

    private transient Thread thread;

    private transient Window window;

    public Game() {
        this.window = new Window(GameVariables.getWIDTH(), GameVariables.getHEIGHT(), "Dodge-City", this);
        start();
    }

    public static void main(String[] args) {
        new Game();
    }

    public void run() {
        gameLoop.loop(window);
        stop();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        GameVariables.setRunning(true);
    }

    public synchronized void stop() {
        try {
            thread.join();
            GameVariables.setRunning(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
