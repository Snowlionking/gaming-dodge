package entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Id;
import services.InboundService;
import services.Randomizer;

public class BasicEnemy extends Enemy {

    private Randomizer randomizer = new Randomizer();
    private InboundService inboundService = new InboundService();
    private static final int SPEED = 3;

    public BasicEnemy(int x, int y) {
        super(x, y, Id.BASIC_ENEMY);
        damage = 10;

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
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

}
