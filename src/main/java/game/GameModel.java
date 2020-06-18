package game;

public class GameModel {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 12 * 9;
	
	private boolean highscoreSet;
	private GameState state;
	private int level;
	private boolean running;

	public boolean isHighscoreSet() {
		return highscoreSet;
	}

	public void setHighscoreSet(boolean highscoreSet) {
		this.highscoreSet = highscoreSet;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}


}
