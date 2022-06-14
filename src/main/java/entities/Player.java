package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

import entities.enemies.Enemy;
import entities.powerups.PowerUp;
import game.GameVariables;
import game.music.Music;
import lombok.extern.slf4j.Slf4j;
import services.InboundService;
import services.Randomizer;
import services.Spawner;

@Slf4j
public class Player extends GameObject {

    private static final int PLAYER_SIZE = 32;

    private long now;
    private long lastTime;
    private int deltaTime;

    private Handler handler;

    private Music music = new Music();
    private Spawner spawner = new Spawner();
    private InboundService inboundService = new InboundService();
    private Randomizer randomizer = new Randomizer();

    public Player(int x, int y, Handler handler) {
        super(x, y, Id.PLAYER);
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
        if (!GameVariables.isInvincible()) {
            checkForEnemyCollision();
        } else {
            now = System.currentTimeMillis();
            deltaTime = (int) ((now - lastTime) / 1000);
            if (deltaTime >= 5) {
                GameVariables.setInvincible(false);
            }
        }

        checkForPointCollision();

        checkForPowerUpCollision();
    }

    private void checkForPowerUpCollision() {
        Iterator<PowerUp> powerUpIterator = handler.getPowerUpList().iterator();

        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();
            if (doesPlayerCollideWithEntity(powerUp)) {
                log.info(powerUp.getPowerUpId().name());
                music.playSound("PowerUp.wav");
                powerUpIterator.remove();
                determinePowerUpAndExecuteIt(powerUp);
            }
        }
    }

    private void determinePowerUpAndExecuteIt(PowerUp powerUp) {
        switch (powerUp.getPowerUpId()) {
            case INVINCIBLE: {
                GameVariables.setInvincible(true);
                lastTime = System.currentTimeMillis();
                return;
            }
            case TIME_STOP: {
                return;
            }
            case CLEAR_ALL_ENEMIES: {
                handler.resetAllEntities();
                return;
            }
            case HEALTH_BOOST: {
                GameVariables.setHealth(GameVariables.getHealth() + 20);
                return;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + powerUp.getPowerUpId());
        }
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
        if (doesPlayerCollideWithEntity(handler.getTrackEnemy())) {
            GameVariables.setHealth(GameVariables.getHealth() - handler.getTrackEnemy().getDamage());
            spawner.spawnTrackEnemy(handler, this);
        }

        if (doesPlayerCollideWithEntity(handler.getTeleportEnemy())) {
            GameVariables.setHealth(GameVariables.getHealth() - handler.getTeleportEnemy().getDamage());
            spawner.spawnTeleportEnemy(handler);
        }
    }

    private void checkForPointCollision() {
        if (doesPlayerCollideWithEntity(handler.getPoint())) {
            music.playSound("pop.wav");
            GameVariables.setScore(GameVariables.getScore() + handler.getPoint().getPoints());
            GameVariables.setHealth(GameVariables.getHealth() + handler.getPoint().getRegeneration());
            if (randomizer.randomNumber(10) == 1) {
                spawner.spawnPowerUp(handler);
            }
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
        if (GameVariables.isInvincible()) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.yellow);
        }
        g.fillRect(x, y, PLAYER_SIZE, PLAYER_SIZE);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, PLAYER_SIZE, PLAYER_SIZE);
    }
}
