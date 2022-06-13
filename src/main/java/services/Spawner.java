package services;

import entities.Handler;
import entities.Player;
import entities.Point;
import entities.enemies.BasicEnemy;
import entities.enemies.FastEnemy;
import entities.enemies.TeleportEnemy;
import entities.enemies.TrackEnemy;
import game.GameVariables;

public class Spawner {

    private Randomizer randomizer = new Randomizer();

    public void spawnBasicEnemy(Handler handler) {
        handler.addEnemy(new BasicEnemy(randomizer.randomNumber(GameVariables.getWIDTH() - 120),
            randomizer.randomNumber(GameVariables.getHEIGHT() - 120)));
    }

    public void spawnFastEnemy(Handler handler) {
        handler.addEnemy(new FastEnemy(randomizer.randomNumber(GameVariables.getWIDTH() - 120),
            randomizer.randomNumber(GameVariables.getHEIGHT() - 120)));
    }

    public void spawnTrackEnemy(Handler handler, Player player) {
        handler.setTrackEnemy(new TrackEnemy(randomizer.randomNumber(GameVariables.getWIDTH() - 120),
            randomizer.randomNumber(GameVariables.getHEIGHT() - 120), player));
    }

    public void spawnTeleportEnemy(Handler handler) {
        handler.setTeleportEnemy(new TeleportEnemy(randomizer.randomNumber(GameVariables.getWIDTH() - 120),
            randomizer.randomNumber(GameVariables.getHEIGHT() - 120)));
    }

    public void spawnPoint(Handler handler) {
        handler.setPoint(new Point(randomizer.randomNumber(GameVariables.getWIDTH() - 120),
            randomizer.randomNumber(GameVariables.getHEIGHT() - 120)));
    }

    public void spawnPlayer(Handler handler) {
        handler.setPlayer(new Player(GameVariables.getWIDTH() / 2 - 32, GameVariables.getHEIGHT() / 2 - 32, handler));
    }

    public void spawnPowerUp(Handler handler) {
        handler.addPowerUp(randomizer.randomPowerUp(randomizer.randomNumber(GameVariables.getWIDTH() - 120),
            randomizer.randomNumber(GameVariables.getHEIGHT() - 120)));
    }

}
