package entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Id;
import entities.Player;

public class TrackEnemy extends Enemy {

	private Player player;

	public TrackEnemy(int x, int y, Id id, Player player) {
		super(x, y, id);
		this.player = player;
		damage = 50;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		float diffX = x - player.getX();
		float diffY = y - player.getY();
		float distance = (float) Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2));

		velX = (int) Math.round((-2.0 / distance) * diffX);
		velY = (int) Math.round((-2.0 / distance) * diffY);

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect(x, y, 24, 24);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 24, 24);
	}

}
