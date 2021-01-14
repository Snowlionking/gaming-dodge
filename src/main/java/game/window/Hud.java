package game.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import controlls.KeyInput;
import controlls.MouseInput;
import entities.Handler;
import game.GameVariables;
import services.InboundService;

public class Hud {

    private InboundService inboundService = new InboundService();

    private boolean cleared = false;

    public void tick() {
        GameVariables.setHealth(inboundService.clamp(GameVariables.getHealth(), 0, 100));
    }

    public void render(Window window, Handler handler) {

        if (!cleared) {
            window.getFrame().getContentPane().removeAll();
            window.getFrame().requestFocus();
            window.getFrame().addKeyListener(new KeyInput(handler));
            window.getFrame().addMouseListener(new MouseInput());
            cleared = true;
        }

        BufferStrategy bs = window.getFrame().getBufferStrategy();
        if (bs == null) {
            window.getFrame().createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, GameVariables.getWIDTH(), GameVariables.getHEIGHT());

        handler.render(g);

        g.setColor(Color.gray);
        g.fillRect(1, 1, 100, 24);
        g.setColor(Color.green);
        g.fillRect(1, 1, GameVariables.getHealth(), 24);
        g.setColor(Color.white);
        g.drawRect(1, 1, 100, 24);
        g.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
        g.drawString("Health: " + Integer.toString(GameVariables.getHealth()), 2, 44);
        g.drawString("Level: " + Integer.toString(GameVariables.getLevel()), 2, 84);
        g.drawString("Score: " + Integer.toString(GameVariables.getScore()), 2, 64);

        g.dispose();
        bs.show();

    }
}
