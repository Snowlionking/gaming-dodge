package services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import entities.Handler;
import entities.Id;
import entities.Player;
import entities.Point;
import entities.enemies.Enemy;

public class SpawnerTest {
	
	private Spawner spawner = new Spawner();
	
	@Test
	public void doesSpawnerAddObjectsCorrectly() {
		
		Handler handler = createHandler();
		
		spawner.spawnPlayer(handler);
		spawner.spawnBasicEnemy(handler);
		spawner.spawnFastEnemy(handler);
		spawner.spawnTrackEnemy(handler, handler.getPlayer());
		spawner.spawnPoint(handler);
		
		assertEquals(handler.getEnemyList().size(), 3);
		assertEquals(handler.getPlayer().getId(), Id.PLAYER);
		assertEquals(handler.getPoint().getId(), Id.POINT);
		
	}

	private Handler createHandler() {
		Handler handler = new Handler();
		handler.setPlayer(new Player(0, 0, null, handler));
		handler.setPoint(new Point(0, 0, null));
		handler.setEnemyList(new LinkedList<Enemy>());
		return handler;
	}
}
