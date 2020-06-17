package entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import entities.Id;
import game.Game;

public class FastEnemy extends Enemy {

	private Random random = new Random();
	private static final int SPEED = 6;

	public FastEnemy(int x, int y, Id id) {
		super(x, y, id);
		damage = 20;

		int direction = random.nextInt(4);

		if (direction == 0) {
			velX = SPEED;
			velY = SPEED;
		} else if (direction == 1) {
			velX = -SPEED;
			velY = -SPEED;
		} else if (direction == 2) {
			velX = -SPEED;
			velY = SPEED;
		} else if (direction == 3) {
			velX = SPEED;
			velY = -SPEED;
		}
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (x <= 0 || x >= Game.WIDTH - 32) {
			velX *= -1;
		}
		if (y <= 0 || y >= Game.HEIGHT - 48) {
			velY *= -1;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 12, 12);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 12, 12);
	}

}
