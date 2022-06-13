package entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Id;
import entities.Player;

public class TrackEnemy extends Enemy {

    private Player player;

    public TrackEnemy(int x, int y, Player player) {
        super(x, y, Id.TRACK_ENEMY);
        this.player = player;
        damage = 50;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        float diffX = (float) x - player.getX();
        float diffY = (float) y - player.getY();
        float distance =
            (float) Math.sqrt(Math.pow((double) x - player.getX(), 2) + Math.pow((double) y - player.getY(), 2));

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
