package entities;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import entities.enemies.Enemy;
import entities.enemies.TeleportEnemy;
import entities.enemies.TrackEnemy;
import entities.powerups.PowerUp;

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

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList = (LinkedList<Enemy>) enemyList;
    }

    public void addPowerUp(PowerUp powerUp) {
        this.powerUpList.add(powerUp);
    }

    public List<PowerUp> getPowerUpList() {
        return powerUpList;
    }

    public void setPowerUpList(List<PowerUp> powerUpList) {
        this.powerUpList = (LinkedList<PowerUp>) powerUpList;
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
