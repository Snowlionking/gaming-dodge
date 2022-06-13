package services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import entities.powerups.PowerUp;
import entities.powerups.PowerUpId;

public class Randomizer {

    private final List<PowerUpId> powerUpIds = Collections.unmodifiableList(Arrays.asList(PowerUpId.values()));

    private Random random;

    public Randomizer() {
        random = new Random();
    }

    public int randomStartDirection(int speed) {
        int direction = random.nextInt(2);

        if (direction == 0) {
            return speed;
        }
        return -speed;
    }

    public int randomNumber(int upperBound) {
        return random.nextInt(upperBound);
    }

    public PowerUp randomPowerUp(int x, int y) {
        return new PowerUp(x, y, powerUpIds.get(randomNumber(powerUpIds.size())));
    }

}
