package entities;

import java.awt.Graphics;
import java.util.LinkedList;

import entities.enemies.Enemy;
import entities.enemies.TrackEnemy;
import entities.powerups.PowerUp;

public class Handler {
	
	private LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	private LinkedList<PowerUp> powerUpList = new LinkedList<PowerUp>();
	private Player player;
	private Point point;
	private TrackEnemy trackEnemy;
	
	public void tick() {
		getPlayer().tick();
		getTrackEnemy().tick();
		for (int i = 0; i < getEnemyList().size(); i++) {
			Enemy enemy = getEnemyList().get(i);
			enemy.tick();
		}
	}

	public void render(Graphics g) {
		for(Enemy enemy: getEnemyList()) {
			enemy.render(g);
		}
		for(PowerUp powerUp: getPowerUpList()) {
			powerUp.render(g);
		}
		getPlayer().render(g);
		getPoint().render(g);
		getTrackEnemy().render(g);
	}
	
	public void addEnemy(Enemy enemy) {
		this.getEnemyList().add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		this.getEnemyList().remove(enemy);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public LinkedList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(LinkedList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public void addPowerUp(PowerUp powerUp) {
		this.getPowerUpList().add(powerUp);
	}

	public LinkedList<PowerUp> getPowerUpList() {
		return powerUpList;
	}

	public void setPowerUpList(LinkedList<PowerUp> powerUpList) {
		this.powerUpList = powerUpList;
	}

	public TrackEnemy getTrackEnemy() {
		return trackEnemy;
	}

	public void setTrackEnemy(TrackEnemy trackEnemy) {
		this.trackEnemy = trackEnemy;
	}
}
