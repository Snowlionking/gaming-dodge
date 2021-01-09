package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Point extends GameObject {

    private int points;
    private int regeneration;

    public Point(int x, int y, Id id) {
        super(x, y, id);
        setPoints(10);
        setRegeneration(2);
    }

    @Override
    public void tick() {
        // implement tick for points
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 8, 8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRegeneration() {
        return regeneration;
    }

    public void setRegeneration(int regeneration) {
        this.regeneration = regeneration;
    }

}
