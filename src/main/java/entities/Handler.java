package entities;

import java.awt.Graphics;
import java.util.LinkedList;

import entities.enemies.Enemy;
import entities.enemies.TeleportEnemy;
import entities.enemies.TrackEnemy;
import entities.powerups.PowerUp;

public class Handler {
	
	private LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	private LinkedList<PowerUp> powerUpList = new LinkedList<PowerUp>();
	private Player player;
	private Point point;
	private TrackEnemy trackEnemy;
	private TeleportEnemy teleportEnemy;
	
	public void tick() {
		player.tick();
		trackEnemy.tick();
		teleportEnemy.tick();
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.tick();
		}
	}

	public void render(Graphics g) {
		for(Enemy enemy: enemyList) {
			enemy.render(g);
		}
		for(PowerUp powerUp: powerUpList) {
			powerUp.render(g);
		}
		player.render(g);
		point.render(g);
		trackEnemy.render(g);
		teleportEnemy.render(g);
	}
	
	public void addEnemy(Enemy enemy) {
		this.enemyList.add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		this.enemyList.remove(enemy);
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
		this.powerUpList.add(powerUp);
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

	public TeleportEnemy getTeleportEnemy() {
		return teleportEnemy;
	}

	public void setTeleportEnemy(TeleportEnemy teleportEnemy) {
		this.teleportEnemy = teleportEnemy;
	}
}
