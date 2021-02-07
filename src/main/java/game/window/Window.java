package game.window;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

    private JFrame frame;

    public Window(int width, int height, String title) {
        setFrame(new JFrame(title));
        getFrame().setPreferredSize(new Dimension(width, height));
        getFrame().setMaximumSize(new Dimension(width, height));
        getFrame().setMinimumSize(new Dimension(width, height));

        getFrame().setDefaultCloseOperation(EXIT_ON_CLOSE);
        getFrame().setResizable(false);
        getFrame().setLocationRelativeTo(null);
        getFrame().setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

}
