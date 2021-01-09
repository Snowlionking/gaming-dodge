package services;

import java.util.Random;

import entities.Handler;
import entities.Id;
import entities.Player;
import entities.Point;
import entities.enemies.BasicEnemy;
import entities.enemies.FastEnemy;
import entities.enemies.TeleportEnemy;
import entities.enemies.TrackEnemy;
import entities.powerups.PowerUp;
import entities.powerups.PowerUps;
import game.GameVariables;

public class Spawner {

	private Random r = new Random();

	public void spawnBasicEnemy(Handler handler) {
		handler.addEnemy(
				new BasicEnemy(r.nextInt(GameVariables.WIDTH - 120), r.nextInt(GameVariables.HEIGHT - 120), Id.BASIC_ENEMY));
	}

	public void spawnFastEnemy(Handler handler) {
		handler.addEnemy(
				new FastEnemy(r.nextInt(GameVariables.WIDTH - 120), r.nextInt(GameVariables.HEIGHT - 120), Id.FAST_ENEMY));
	}

	public void spawnTrackEnemy(Handler handler, Player player) {
		handler.setTrackEnemy(new TrackEnemy(r.nextInt(GameVariables.WIDTH - 120), r.nextInt(GameVariables.HEIGHT - 120),
				Id.TRACK_ENEMY, player));
	}

	public void spawnTeleportEnemy(Handler handler) {
		handler.setTeleportEnemy(
				new TeleportEnemy(r.nextInt(GameVariables.WIDTH - 120), r.nextInt(GameVariables.HEIGHT - 120), Id.TRACK_ENEMY));
	}

	public void spawnPoint(Handler handler) {
		handler.setPoint(new Point(r.nextInt(GameVariables.WIDTH - 120), r.nextInt(GameVariables.HEIGHT - 120), Id.POINT));
	}

	public void spawnPlayer(Handler handler) {
		handler.setPlayer(new Player(GameVariables.WIDTH / 2 - 32, GameVariables.HEIGHT / 2 - 32, Id.PLAYER, handler));
	}

	public void spawnPowerUp(Handler handler) {
		handler.addPowerUp(new PowerUp(r.nextInt(GameVariables.WIDTH - 120), r.nextInt(GameVariables.HEIGHT - 120), Id.POWERUP,
				PowerUps.INVINCIBLE));
	}

}
