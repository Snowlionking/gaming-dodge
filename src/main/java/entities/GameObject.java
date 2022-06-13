package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import lombok.Data;

@Data
public abstract class GameObject {

    protected int x;
    protected int y;

    protected int velX;
    protected int velY;

    protected Id id;

    protected GameObject(int x, int y, Id id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

}
