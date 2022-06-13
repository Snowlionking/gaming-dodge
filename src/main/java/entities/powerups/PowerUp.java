package entities.powerups;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import entities.GameObject;
import entities.Id;
import services.Randomizer;

public class PowerUp extends GameObject {

    private final List<PowerUpId> powerUpIds = Collections.unmodifiableList(Arrays.asList(PowerUpId.values()));

    private PowerUpId powerUpId;
    private Randomizer randomizer = new Randomizer();

    public PowerUp(int x, int y, PowerUpId powerUpId) {
        super(x, y, Id.POWERUP);
        this.powerUpId = powerUpId;
    }

    public PowerUp getRandomPowerUp(int x, int y) {
        return new PowerUp(x, y, powerUpIds.get(randomizer.randomNumber(powerUpIds.size())));
    }

    @Override
    public void tick() {
        // implement tick for powerups
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillOval(x, y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public PowerUpId getPowerUpId() {
        return powerUpId;
    }

    public void setPowerUpId(PowerUpId powerUpId) {
        this.powerUpId = powerUpId;
    }

}
