package game.window;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Dimension;

import javax.swing.JFrame;

import game.Game;

public class Window {

    private JFrame frame;

    public Window(int width, int height, String title, Game game) {
        setFrame(new JFrame(title));
        getFrame().setPreferredSize(new Dimension(width, height));
        getFrame().setMaximumSize(new Dimension(width, height));
        getFrame().setMinimumSize(new Dimension(width, height));

        getFrame().setDefaultCloseOperation(EXIT_ON_CLOSE);
        getFrame().setResizable(false);
        getFrame().setLocationRelativeTo(null);
        // getFrame().add(game);
        getFrame().setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

}
