package game;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

    private static final long serialVersionUID = -4369730830015653927L;
    private JFrame frame;

    public Window(int width, int height, String title, Game game) {
        setFrame(new JFrame(title));
        getFrame().setPreferredSize(new Dimension(width, height));
        getFrame().setMaximumSize(new Dimension(width, height));
        getFrame().setMinimumSize(new Dimension(width, height));

        getFrame().setDefaultCloseOperation(EXIT_ON_CLOSE);
        getFrame().setResizable(false);
        getFrame().setLocationRelativeTo(null);
        getFrame().add(game);
        getFrame().setVisible(true);
        game.start();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

}
