package services;

import java.util.Random;

public class Randomizer {
	
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

}
