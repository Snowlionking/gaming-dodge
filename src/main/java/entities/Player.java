package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

import entities.enemies.Enemy;
import game.Game;
import game.hud.Hud;
import services.InboundService;
import services.Spawner;

public class Player extends GameObject {

	private InboundService inboundService = new InboundService();
	private Handler handler;
	private Spawner spawner = new Spawner();

	public Player(int x, int y, Id id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		x = inboundService.clamp(x, 1, Game.WIDTH - 49);
		y = inboundService.clamp(y, 1, Game.HEIGHT - 72);

		collisionCheck();
	}

	private void collisionCheck() {

		Iterator<Enemy> enemyIterator = handler.getEnemyList().iterator();

		while (enemyIterator.hasNext()) {
			Enemy enemy = enemyIterator.next();
			if (doesPlayerCollideWithSomething(enemy)) {
				Hud.HEALTH -= enemy.getDamage();
				enemyIterator.remove();
			}
		}
		
		if(doesPlayerCollideWithSomething(handler.getTrackEnemy())) {
			Hud.HEALTH -= handler.getTrackEnemy().getDamage();
			spawner.spawnTrackEnemy(handler, this);
		}

		if (doesPlayerCollideWithSomething(handler.getPoint())) {
			Hud.SCORE += handler.getPoint().getPoints();
			Hud.HEALTH += handler.getPoint().getRegeneration();
			spawner.spawnBasicEnemy(handler);
			spawner.spawnPoint(handler);
		}
	}

	private boolean doesPlayerCollideWithSomething(GameObject object) {
		return getBounds().intersects(object.getBounds());
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}
