package entities.powerups;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.GameObject;
import entities.Id;

public class PowerUp extends GameObject {

    public PowerUp(int x, int y, Id id, PowerUps powerUps) {
        super(x, y, id);
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

}
