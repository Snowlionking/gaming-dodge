package entities;

import java.awt.Graphics;
import java.util.LinkedList;

import entities.enemies.Enemy;
import entities.enemies.TeleportEnemy;
import entities.enemies.TrackEnemy;
import entities.powerups.PowerUp;
import lombok.Data;

@Data
public class Handler {

    private LinkedList<Enemy> enemyList = new LinkedList<>();
    private LinkedList<PowerUp> powerUpList = new LinkedList<>();

    private Player player;
    private Point point;
    private TrackEnemy trackEnemy;
    private TeleportEnemy teleportEnemy;

    public void tick() {
        player.tick();

        if (trackEnemy != null) {
            trackEnemy.tick();
        }

        if (teleportEnemy != null) {
            teleportEnemy.tick();
        }
        for (int i = 0; i < enemyList.size(); i++) {
            Enemy enemy = enemyList.get(i);
            enemy.tick();
        }
    }

    public void render(Graphics g) {
        for (Enemy enemy : enemyList) {
            enemy.render(g);
        }
        for (PowerUp powerUp : powerUpList) {
            powerUp.render(g);
        }
        player.render(g);
        point.render(g);

        if (trackEnemy != null) {
            trackEnemy.render(g);
        }

        if (teleportEnemy != null) {
            teleportEnemy.render(g);
        }
    }

    public void resetAllEntities() {
        enemyList = new LinkedList<>();
        trackEnemy = null;
        teleportEnemy = null;
    }

    public void addEnemy(Enemy enemy) {
        this.enemyList.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        this.enemyList.remove(enemy);
    }

    public void addPowerUp(PowerUp powerUp) {
        this.powerUpList.add(powerUp);
    }

    public void removePowerUp(PowerUp powerUp) {
        this.powerUpList.remove(powerUp);
    }
}
