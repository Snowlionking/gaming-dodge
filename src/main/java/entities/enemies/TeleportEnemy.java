package entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Id;
import game.GameModel;
import services.InboundService;
import services.Randomizer;

public class TeleportEnemy extends Enemy {

	private Randomizer randomizer = new Randomizer();
	private InboundService inboundService = new InboundService();
	
	private long now;
	private long lastTime;

	public TeleportEnemy(int x, int y, Id id) {
		super(x, y, id);
		damage = randomizer.randomNumber(42) + 1;
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		now = System.currentTimeMillis();
		if (now - lastTime >= 4000.0) {
			x = inboundService.keepInboundOnXAxe(randomizer.randomNumber(GameModel.WIDTH));
			y = inboundService.keepInboundOnYAxe(randomizer.randomNumber(GameModel.HEIGHT));
			lastTime = now;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(255, 0, 255));
		g.fillRect(x, y, 24, 24);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 24, 24);
	}

}
