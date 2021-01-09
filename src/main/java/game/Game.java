package game;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -4584388369897487885L;

    private GameLoop gameLoop = new GameLoop();

    private transient Thread thread;

    public Game() {
        new Window(GameVariables.getWIDTH(), GameVariables.getHEIGHT(), "Dodge-City", this);
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
