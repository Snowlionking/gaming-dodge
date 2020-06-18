package entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Id;
import services.InboundService;
import services.Randomizer;

public class FastEnemy extends Enemy {

	private Randomizer randomizer = new Randomizer();
	private InboundService inboundService = new InboundService();
	private static final int SPEED = 6;

	public FastEnemy(int x, int y, Id id) {
		super(x, y, id);
		damage = 20;

		velX = randomizer.randomStartDirection(SPEED);
		velY = randomizer.randomStartDirection(SPEED);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		velX *= inboundService.keepInboundOnXAxe(x);
		velY *= inboundService.keepInboundOnYAxe(y);
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
