package entities.enemies;

import java.awt.Color;
import java.awt.Font;
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
	private int deltaTime;

	public TeleportEnemy(int x, int y, Id id) {
		super(x, y, id);
		damage = randomizer.randomNumber(42) + 1;
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		now = System.currentTimeMillis();
		deltaTime = (int) ((now - lastTime) / 1000);
		if (deltaTime >= 3) {
			x = inboundService.clamp(randomizer.randomNumber(GameModel.WIDTH), 32, GameModel.WIDTH);
			y = inboundService.clamp(randomizer.randomNumber(GameModel.HEIGHT), 32, GameModel.HEIGHT);
			lastTime = now;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(100, 0, 255));
		g.fillRect(x, y, 36, 36);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString(Integer.toString(2 - deltaTime), x + 10, y + 29);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 36, 36);
	}

}
