package game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class GameModel {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 12 * 9;
	
	private boolean highscoreSet;
	private GameState state;
	private int level;
	private boolean running;
	private boolean musicRunning;
	private Clip clip;
	
	public GameModel() {
		try {
			setClip(AudioSystem.getClip());
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

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

	public boolean isMusicRunning() {
		return musicRunning;
	}

	public void setMusicRunning(boolean musicRunning) {
		this.musicRunning = musicRunning;
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

}
