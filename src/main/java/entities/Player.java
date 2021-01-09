package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

import entities.enemies.Enemy;
import game.GameVariables;
import game.music.Music;
import services.InboundService;
import services.Spawner;

public class Player extends GameObject {

    private static final int PLAYER_SIZE = 32;

    private Handler handler;

    private Music music = new Music();
    private Spawner spawner = new Spawner();
    private InboundService inboundService = new InboundService();

    public Player(int x, int y, Id id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = inboundService.clamp(x, 1, GameVariables.getWIDTH() - 49);
        y = inboundService.clamp(y, 1, GameVariables.getHEIGHT() - 72);

        collisionCheck();
    }

    private void collisionCheck() {

        checkForEnemyCollision();

        checkForPointCollision();
    }

    private void checkForEnemyCollision() {
        Iterator<Enemy> enemyIterator = handler.getEnemyList().iterator();

        checkForBasicAndFastEnemyCollision(enemyIterator);

        checkForTrackingAndTeleportEnemyCollision();
    }

    private void checkForBasicAndFastEnemyCollision(Iterator<Enemy> enemyIterator) {
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            if (doesPlayerCollideWithEntity(enemy)) {
                music.playSound("hurt.wav");
                GameVariables.setHealth(GameVariables.getHealth() - enemy.getDamage());
                enemyIterator.remove();
            }
        }
    }

    private void checkForTrackingAndTeleportEnemyCollision() {
        if (GameVariables.getLevel() >= 3) {
            if (doesPlayerCollideWithEntity(handler.getTrackEnemy())) {
                GameVariables.setHealth(GameVariables.getHealth() - handler.getTrackEnemy().getDamage());
                spawner.spawnTrackEnemy(handler, this);
            }

            if (GameVariables.getLevel() >= 4 && doesPlayerCollideWithEntity(handler.getTeleportEnemy())) {
                GameVariables.setHealth(GameVariables.getHealth() - handler.getTeleportEnemy().getDamage());
                spawner.spawnTeleportEnemy(handler);
            }

        }
    }

    private void checkForPointCollision() {
        if (doesPlayerCollideWithEntity(handler.getPoint())) {
            music.playSound("pop.wav");
            GameVariables.setScore(GameVariables.getScore() + handler.getPoint().getPoints());
            GameVariables.setHealth(GameVariables.getHealth() + handler.getPoint().getRegeneration());
            switch (GameVariables.getLevel()) {
                case 1:
                    spawner.spawnBasicEnemy(handler);
                    break;
                case 2:
                    spawner.spawnFastEnemy(handler);
                    break;
                case 3:
                    if (handler.getTrackEnemy() == null) {
                        spawner.spawnTrackEnemy(handler, this);
                    }
                    break;
                case 4:
                    if (handler.getTeleportEnemy() == null) {
                        spawner.spawnTeleportEnemy(handler);
                    }
                    break;
                default:
                    break;
            }
            spawner.spawnPoint(handler);
        }
    }

    private boolean doesPlayerCollideWithEntity(GameObject object) {
        if (object == null) {
            return false;
        }
        return getBounds().intersects(object.getBounds());
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, PLAYER_SIZE, PLAYER_SIZE);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, PLAYER_SIZE, PLAYER_SIZE);
    }
}
